package com.pskwiercz.jdd.tool;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherToolController {

    private ChatClient chatClient;

    public WeatherToolController(ChatClient.Builder chatBuilder) {
        this.chatClient = chatBuilder.build();
    }

    @GetMapping("/temp")
    public String temp(@RequestParam(name = "city", defaultValue = "Bydgoszcz") String city) {
        return chatClient
                .prompt()
                .user("What temperature is in " + city)
                .tools(new WeatherTools())
                .call()
                .content();
    }
}

