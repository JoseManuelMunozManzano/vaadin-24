package com.example.application.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class OpenAIService {

//    final ChatClient chatClient;
    final ChatModel chatModel;

//    public OpenAIService(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }


    public OpenAIService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String sendMessage(String userMessage, String filePath) {
        var imageResource = new ClassPathResource(filePath);

        UserMessage message = UserMessage.builder()
                .text(userMessage)
                .media(new Media(MimeTypeUtils.IMAGE_PNG, imageResource))
                .build();

        var prompt = new Prompt(message, OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .temperature(0.9)
                .build());

        ChatResponse chatResponse = chatModel.call(prompt);

        return chatResponse.getResult().getOutput().getText();

//        return chatClient.prompt()
//                .user(userMessage)
//                .call()
//                .content();
    }
}
