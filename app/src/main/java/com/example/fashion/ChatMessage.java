package com.example.fashion;

import java.util.List;

public class ChatMessage {
    List<MessageContent> messages;

    public ChatMessage(List<MessageContent> question) {
        this.messages = question;
    }
}
