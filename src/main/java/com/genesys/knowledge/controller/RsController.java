package com.genesys.knowledge.controller;

import com.genesys.knowledge.domain.Request;
import com.genesys.knowledge.domain.Response;
import com.genesys.knowledge.service.knowledge.KnowledgeService;
import com.genesys.knowledge.service.rivescript.RsService;
import com.genesys.knowledge.service.rivescript.RsServicePool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by RomanH on 22.03.2017.
 */
@Slf4j
@RestController
@RequestMapping("/")
public class RsController {

    @Autowired
    private RsServicePool rsServicePool;
    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping(value = "/chatbot", method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Response replyToMessage(@RequestBody Request request, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            username = session.getId();
        }

        String language = (String) session.getAttribute("lang");
        if (language == null) {
            language = "eng";
        }
        RsService rsService = rsServicePool.getRsService(language);

        String requestMessage = request.getMessage();
        log.info("request=" + requestMessage);

        String rsResponseMessage = rsService.reply(username, requestMessage);
        log.info("response=" + rsResponseMessage);
        String keyWord = "knowledge ";
        if (rsResponseMessage.contains(keyWord)) {
            String knowledgeResponseMessage = knowledgeService.processRequest(username, rsResponseMessage);
            return new Response(knowledgeResponseMessage);
        } else {
            return new Response(rsResponseMessage);
        }
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void registerUserData(@RequestHeader("username") String username,
                                 @RequestHeader("lang") String language,
                                 @RequestHeader("topic") String topic, HttpSession session) {
        RsService rsService;
        if (!language.equals("")) {
            session.setAttribute("lang", language.toLowerCase().trim());
            rsService = rsServicePool.getRsService(language.toLowerCase());
        } else {
            session.setAttribute("lang", "eng");
            rsService = rsServicePool.getRsService("eng");
        }

        session.setAttribute("username", username);

        if (!topic.equals("")) {
            rsService.reply(username, "set topic " + topic);
        }
    }
}
