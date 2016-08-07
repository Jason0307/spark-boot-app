package org.zhubao.spark.bootapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@RequestMapping(value = "/message/send", method = RequestMethod.POST)
	public String sendMessage() {
		return "hello, jason";
	}
}
