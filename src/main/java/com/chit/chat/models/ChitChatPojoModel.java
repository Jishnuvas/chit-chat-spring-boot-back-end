package com.chit.chat.models;



public class ChitChatPojoModel {

    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType{
        JOIN,
        CHAT,
        LEAVE
    }
    public MessageType getType ( ) {
        return type;
    }

    public void setType ( MessageType type ) {
        this.type = type;
    }

    public String getContent ( ) {
        return content;
    }

    public void setContent ( String content ) {
        this.content = content;
    }

    public String getSender ( ) {
        return sender;
    }

    public void setSender ( String sender ) {
        this.sender = sender;
    }
}
