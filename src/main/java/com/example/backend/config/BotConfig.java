package com.example.backend.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
public class BotConfig {

    String botName = "ariparitelegram_bot";
    String token = "6722461889:AAHOwoTCAuhayN9IVqgyk0ohd1qJ72PNiRg";
}
