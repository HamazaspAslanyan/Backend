package com.example.backend;

import com.example.backend.config.AdminBotConfig;
import com.example.backend.config.BotConfig;
import com.example.backend.services.BotFactory;
import com.example.backend.services.TelegramBot;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "kuku", version = "1.0", description = "mumu"))
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(new BotConfig()));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
