package com.chit.chat.listenerEvents;

import com.chit.chat.models.ChitChatPojoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
public class SocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger ( SocketEventListener.class );

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleSocketConnectListener ( SessionConnectedEvent event ) {
        logger.info ( "A new connection request has arrived" );
    }

    @EventListener
    public void handleSocketDisconnectListener ( SessionDisconnectEvent event ) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap ( event.getMessage () );
        String username = (String) headerAccessor.getSessionAttributes ().get("username");
        if(username != null){
            logger.info ( "User"+username+"Disconnected" );
            ChitChatPojoModel message = new ChitChatPojoModel ();
            message.setType ( ChitChatPojoModel.MessageType.LEAVE );
            message.setUser ( username );
            messagingTemplate.convertAndSend ( "/topic/group",message );
        }
    }
}

