package com.example.backend.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
//@PropertySource("application.properties")
public class BotConfig {

//    @Value("${bot.name}")
//    String botName;
//
//    @Value("${bot.token}")
//    String token;

    String botName = "ariparitelegram_bot";
    String token = "6722461889:AAHOwoTCAuhayN9IVqgyk0ohd1qJ72PNiRg";
}
