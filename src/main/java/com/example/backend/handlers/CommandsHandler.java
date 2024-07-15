package com.example.backend.handlers;

import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.mappers.TelegramMapper;
import com.example.backend.services.DanceService;
import com.example.backend.utils.TelegramUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class CommandsHandler {

    @Autowired
    DanceService danceService;

    @Autowired
    TelegramUtil telegramUtil;
    @Autowired
    private TelegramMapper telegramMapper;


    public SendMessage handleCommands(Update update) {
        String command = update.getMessage().getText();
        switch (command){
            case "/dances" : return sendDanceList(update);
            case "/events" : return sendEventsList(update);
            case "/classes" : return sendClassesList(update);
            case "/groups" : return sendGroupsList(update);
            case "/support" : return sendSupportList(update);
            default:
                Long chatId = update.getMessage().getChatId();
                return new SendMessage(String.valueOf(chatId), "Consts.UNKNOWN_COMMAND");
        }
    }

    public SendMessage sendDanceList(Update update) {
        List<TelegramButton> buttons = telegramMapper.toDanceButtons(danceService.getDanceNameListAm());
        return telegramUtil.getButtons(update.getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
    public SendMessage sendEventsList(Update update) {
        return null;
//        List<TelegramButton> buttons = danceService.getDanceList();
//        return telegramUtil.getButtons(update.getMessage().getChatId(), "Պարեղանակներ", buttons);
    }
    public SendMessage sendClassesList(Update update) {
//        List<TelegramButton> buttons = danceService.getDanceList();
//        return telegramUtil.getButtons(update.getMessage().getChatId(), "Պարեղանակներ", buttons);
        return null;
    }
    public SendMessage sendGroupsList(Update update) {
//        List<TelegramButton> buttons = danceService.getDanceList();
//        return telegramUtil.getButtons(update.getMessage().getChatId(), "Պարեղանակներ", buttons);
        return null;
    }
    public SendMessage sendSupportList(Update update) {
//        List<TelegramButton> buttons = danceService.getDanceList();
//        return telegramUtil.getButtons(update.getMessage().getChatId(), "Պարեղանակներ", buttons);
        return null;
    }
}