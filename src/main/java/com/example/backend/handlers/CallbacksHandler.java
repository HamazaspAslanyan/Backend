package com.example.backend.handlers;

import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.services.DanceService;
import com.example.backend.services.TelegramBot;
import com.example.backend.utils.TelegramUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class CallbacksHandler {

    @Autowired
    TelegramUtil telegramUtil;
    @Autowired
    DanceService danceService;


    TelegramBot telegramBot;

    public SendMessage handleCallbacks(Update update) {

        String[] callBackData = update.getCallbackQuery().getData().split("_");
        String command = callBackData[0];
        String itemId = callBackData[callBackData.length - 1];

        long chatId = update.getCallbackQuery().getMessage().getChatId();

        switch (command){
            case "dance" : return sendDance(update, itemId);
            case "event" : return sendEvent(update, itemId);
            case "class" : return sendLesson(update, itemId);
            case "group" : return sendGroup(update, itemId);
            case "support" : return sendSupport(update, itemId);
            default: return new SendMessage(String.valueOf(chatId), "Consts.UNKNOWN_COMMAND");
        }
    }

    public SendMessage sendDance(Update update, String danceid) {
        List<TelegramButton> buttons = danceService.getTelegramDanceList();
        return telegramUtil.getButtons(update.getCallbackQuery().getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
    public SendMessage sendEvent(Update update, String danceid) {
        List<TelegramButton> buttons = danceService.getTelegramDanceList();
        return telegramUtil.getButtons(update.getCallbackQuery().getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
    public SendMessage sendLesson(Update update, String danceid) {
        List<TelegramButton> buttons = danceService.getTelegramDanceList();
        return telegramUtil.getButtons(update.getCallbackQuery().getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
    public SendMessage sendGroup(Update update, String danceid) {
        List<TelegramButton> buttons = danceService.getTelegramDanceList();
        return telegramUtil.getButtons(update.getCallbackQuery().getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
    public SendMessage sendSupport(Update update, String danceid) {
        List<TelegramButton> buttons = danceService.getTelegramDanceList();
        return telegramUtil.getButtons(update.getCallbackQuery().getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
}

