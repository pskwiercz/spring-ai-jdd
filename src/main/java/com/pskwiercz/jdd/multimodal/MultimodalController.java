package com.pskwiercz.jdd.multimodal;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MultimodalController {

    @Value("classpath:/images/screenshot.png")
    Resource resource;

    private final ChatModel chatModel;

    public MultimodalController(ChatModel ChatModel) {
        this.chatModel = ChatModel;
    }

    @GetMapping("/imagedescribe")
    public String imageDescribe() throws IOException {

        var userMessage = UserMessage.builder()
                .text("This is a screenshot of some code. Can you provide a description of what this code does?") // content
                .media(new Media(MimeTypeUtils.IMAGE_PNG, resource)) // media
                .build();

        return chatModel.call(new Prompt(userMessage)).getResult().getOutput().getText();
    }
}


