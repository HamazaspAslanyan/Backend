package com.example.backend;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "kuku", version = "1.0", description = "mumu"))
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new TelegramBot(new BotConfig()));
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
    }


}
