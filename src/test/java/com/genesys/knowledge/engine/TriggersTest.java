package com.genesys.knowledge.engine;

import org.junit.Test;

public class TriggersTest extends TestBase {

	@Override
	public String replies() {
		return "triggers";
	}

	@Test
	public void testAtomicTriggers() {
		this.setUp("atomic.rive");

		this.reply("Hello bot", "Hello human.");
		this.reply("What are you?", "I am a RiveScript bot.");
	}

	@Test
	public void testWildcardTriggers() {
		this.setUp("wildcard.rive");

		this.reply("my name is Bob", "Nice to meet you, bob.");
		this.reply("bob told me to say hi", "Why did bob tell you to say hi?");
		this.reply("i am 5 years old", "A lot of people are 5.");
		this.reply("i am five years old", "Say that with numbers.");
		this.reply("i am twenty five years old", "Say that with fewer words.");
	}

	@Test
	public void testAlternativesAndOptionals() {
		this.setUp("alternatives.rive");

		String NO_MATCH = "ERR: No Reply Matched";

		this.reply("What are you?", "I am a robot.");
		this.reply("What is you?", "I am a robot.");

		this.reply("What is your home phone number?", "It is 555-1234.");
		this.reply("What is your home number?", "It is 555-1234.");
		this.reply("What is your cell phone number?", "It is 555-1234.");
		this.reply("What is your office number?", "It is 555-1234.");

		this.reply("Can you ask me a question?", "Why is the sky blue?");
		this.reply("Please ask me a question?", "Why is the sky blue?");
		this.reply("Ask me a question?", "Why is the sky blue?");

		this.reply("aa", "Matched.");
		this.reply("bb", "Matched.");
		this.reply("aa bogus", "Matched.");
		this.reply("aabogus", NO_MATCH);
		this.reply("bogus", NO_MATCH);

		this.reply("hi Aiden", "Matched.");
		this.reply("hi bot how are you?", "Matched.");
		this.reply("yo computer what time is it?", "Matched.");
		this.reply("yoghurt is yummy", NO_MATCH);
		this.reply("hide and seek is fun", NO_MATCH);
		this.reply("hip hip hurrah", NO_MATCH);
	}

	@Test
	public void testTriggerArrays() {
		this.setUp("arrays.rive");

		this.reply("What color is my red shirt?", "Your shirt is red.");
		this.reply("What color is my blue car?", "Your car is blue.");
		this.reply("What color is my pink house?", "ERR: No Reply Matched");
		this.reply("What color is my dark blue jacket?", "Your jacket is dark blue.");
		this.reply("What color was Napolean's white horse?", "It was white.");
		this.reply("What color was my red shirt?", "It was red.");
		this.reply("I have a blue car.", "Tell me more about your car.");
		this.reply("I have a cyan car.", "ERR: No Reply Matched");
	}

	@Test
	public void testWeightedTriggers() {
		this.setUp("weighted.rive");

		this.reply("Hello robot.", "Hi there!");
		this.reply("Hello or something.", "Hi there!");
		this.reply("Can you run a Google search for Java?", "Sure!");
		this.reply("Can you run a Google search for Java or something?", "Or something. Sure!");
	}
}
