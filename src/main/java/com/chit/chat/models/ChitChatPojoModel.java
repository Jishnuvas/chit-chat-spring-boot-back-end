package com.chit.chat.models;



public class ChitChatPojoModel {

    private MessageType type;
    private String content;
    private String user;

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

    public String getUser ( ) {
        return user;
    }

    public void setUser ( String user ) {
        this.user = user;
    }
}
