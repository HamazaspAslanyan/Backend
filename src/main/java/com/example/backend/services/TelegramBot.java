package com.example.backend.services;

import com.example.backend.config.BotConfig;
import com.example.backend.handlers.CallbacksHandler;
import com.example.backend.handlers.CommandsHandler;
import com.example.backend.handlers.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    @Autowired
    public CommandsHandler commandsHandler;

    @Autowired
    public CallbacksHandler callbacksHandler;

    @Autowired
    public MessageHandler messageHandler;

    ArmenianLanguageService armenianLanguageService;

    public TelegramBot(BotConfig config) {
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

        if (update.hasMessage() && update.getMessage().hasText()) {
//            String chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().getText().startsWith("/")) {
                deleteLastMessage(update);
                sendMessage(commandsHandler.handleCommands(update));
            } else {
                sendMessage(messageHandler.handleMessage(update));
            }
        } else if (update.hasCallbackQuery()) {
            deleteLastCallback(update);
            sendMessage(callbacksHandler.handleCallbacks(update));
        }
    }

    void sendMessage(SendAudio message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void sendMessage(SendMediaGroup message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void sendMessage(DeleteMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void sendMessage(SendPhoto message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    void deleteLastMessage(Update update){

        deleteMessage(update.getMessage().getChatId(), update.getMessage().getMessageId());

    }

    void deleteLastCallback(Update update){

        deleteMessage(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getMessageId());

    }

    void deleteMessage(Long chatId, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);
        sendMessage(deleteMessage);
    }
}


