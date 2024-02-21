package com.example.backend.services;//package com.example.backend.services;
//
import com.example.backend.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config){
        this.config = config;
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {


        System.out.println(update.getChannelPost().getText());

        if(update.getChannelPost().hasText()){
            String messageText = update.getChannelPost().getText();

            long chatId = update.getChannelPost().getChat().getId();

            startCommandReceived(chatId, update.getChannelPost().getChat().getUserName(), messageText);


//            switch (messageText) {
//                case "/start":
//                    startCommandReceived(chatId, update.getChannelPost().getChat().getUserName(), messageText);
//            }
        }

    }

    private void startCommandReceived(long chatId, String name, String messageText) {
        TextConverter textConverter = new TextConverter();

        String answer = "Hargeli, " + name + ", xndrem latinatar " + textConverter.fromArmToEng(messageText);

        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        TextConverter textConverter = new TextConverter();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
