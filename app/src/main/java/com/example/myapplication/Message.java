package com.example.myapplication;

public class Message {
    private String message;
    private int message_type;

    public Message(String message) {
        setMessage(message);
    }

    public Message(String message, int message_type){
        setMessage(message);
        setMessage_type(message_type);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public int getMessage_type() {
        return message_type;
    }

    private void setMessage_type(int message_type) {
        this.message_type = message_type;
    }


}
