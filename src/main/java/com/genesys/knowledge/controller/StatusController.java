package com.genesys.knowledge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by RomanH on 10.04.2017.
 */
@Controller
@RequestMapping("/")
public class StatusController {

    @ModelAttribute("status")
    public String status() {
        return "running";
    }

    @RequestMapping(value = "/status",
            method = RequestMethod.GET)
    public String getStatus(ModelMap model) {
        return "status";
    }
}
