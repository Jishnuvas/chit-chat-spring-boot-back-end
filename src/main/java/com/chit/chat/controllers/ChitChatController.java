package com.chit.chat.controllers;

import com.chit.chat.listenerEvents.SocketEventListener;
import com.chit.chat.models.ChitChatPojoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChitChatController {

    private static final Logger logger = LoggerFactory.getLogger ( SocketEventListener.class );
    @MessageMapping("sendMessage")
    @SendTo("/topic/group")
    public ChitChatPojoModel sendMessage( @Payload ChitChatPojoModel message,SimpMessageHeaderAccessor headerAccessor  ){
        String username = (String) headerAccessor.getSessionAttributes ().get("username");
        if(username != null) {
            logger.info ( "User" + username );
            ChitChatPojoModel newMessage = new ChitChatPojoModel ();
            newMessage.setUser ( username );
            newMessage.setType ( ChitChatPojoModel.MessageType.CHAT );
            newMessage.setContent (message.getContent ()  );
            return newMessage;
        }else{
            return null;
        }
    }


    @MessageMapping("addUser")
    @SendTo("/topic/group")
    public ChitChatPojoModel addUser( @Payload ChitChatPojoModel message, SimpMessageHeaderAccessor headerAccessor ){
        // adding username in socket session
        headerAccessor.getSessionAttributes ().put ( "username" ,message.getUser ());
        return message;
    }

}
