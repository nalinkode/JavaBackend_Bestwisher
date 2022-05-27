package com.bestwisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.bestwisher.model.AppChatMessage;

@Controller
public class AppChatController {
	
	 @Autowired
	    private SimpMessagingTemplate simpMessagingTemplate;

	    @MessageMapping("/message")
	    @SendTo("/chatroom/public")
	    public AppChatMessage receiveMessage(@Payload AppChatMessage message){
	        return message;
	    }

	    @MessageMapping("/private-message")
	    public AppChatMessage recMessage(@Payload AppChatMessage message){
	        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
	        System.out.println(message.toString());
	        return message;
	    }


	
	
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public AppChatMessage send(@Payload AppChatMessage chatMessage) {
		return chatMessage;
	}

}
