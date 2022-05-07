package com.chit.chat.controllers;

import com.chit.chat.models.ChitChatPojoModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChitChatController {

    @MessageMapping("chat.sendMessage")
    @SendTo("/topic/group")
    public ChitChatPojoModel sendMessage( @Payload ChitChatPojoModel message,SimpMessageHeaderAccessor headerAccessor ){
        return message;
    }


    @MessageMapping("chat.addUser")
    @SendTo("/topic/group")
    public ChitChatPojoModel addUser( @Payload ChitChatPojoModel message, SimpMessageHeaderAccessor headerAccessor ){
        // adding username in socket session
        headerAccessor.getSessionAttributes ().put ( "username" ,message.getSender ());
        return message;
    }

}
