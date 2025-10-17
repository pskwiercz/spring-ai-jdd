package com.pskwiercz.jdd.tool;

import org.springframework.ai.tool.annotation.Tool;

public class WeatherTools {

    @Tool(description = "Get temperature for the provided city")
    String getTemperature(String city) {
        String temp = "10";
        if (city.equals("Bydgoszcz")) {
            temp = "27";
        }
        return temp;
    }
}
