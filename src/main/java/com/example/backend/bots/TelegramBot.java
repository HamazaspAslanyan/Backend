package com.example.backend.bots;

import com.example.backend.config.BotConfig;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.dtos.telegram.TelegramFile;
import com.example.backend.entities.Music;
import com.example.backend.handlers.CommandsHandler;
import com.example.backend.handlers.MessageHandler;
import com.example.backend.mappers.TelegramMapper;
import com.example.backend.services.ArmenianLanguageService;
import com.example.backend.services.DanceService;
import com.example.backend.utils.TelegramUtil;
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

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;

    @Autowired
    public CommandsHandler commandsHandler;

    @Autowired
    public MessageHandler messageHandler;
    @Autowired
    TelegramUtil telegramUtil;
    @Autowired
    DanceService danceService;
    @Autowired
    TelegramMapper telegramMapper;

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
                handleMessage(update);
            }
        } else if (update.hasCallbackQuery()) {
            deleteLastCallback(update);
            handleCallbacks(update);
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


    public void handleCallbacks(Update update) {

        String[] callBackData = update.getCallbackQuery().getData().split("_");
        String command = callBackData[0];
        UUID itemId = UUID.fromString(callBackData[callBackData.length - 1]);

        long chatId = update.getCallbackQuery().getMessage().getChatId();

        switch (command){
            case "dance" :  sendDanceMusic(update, itemId); break;
//            case "event" : return sendEvent(update, itemId);
//            case "class" : return sendLesson(update, itemId);
//            case "group" : return sendGroup(update, itemId);
//            case "support" : return sendSupport(update, itemId);
            default:
                sendMessage(new SendMessage(String.valueOf(chatId), "Consts.UNKNOWN_COMMAND"));
        }
    }

    public void sendDanceMusic(Update update, UUID danceid) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        DanceOutDto danceOutDto = danceService.getDance(danceid);
        Set<Music> musicList = danceOutDto.getMusicList();
        List<TelegramFile> audioList = telegramMapper.musicToFileList(musicList);

        if (audioList.size() > 1) {
            sendMessage(telegramUtil.getMediaGroup(chatId, audioList));
        }else if (!audioList.isEmpty()){
            sendMessage(telegramUtil.getAudio(chatId, danceOutDto.getName(), audioList.get(0)));
        }
    }


    public void handleMessage(Update update) {


        String messageText = update.getMessage().getText();
        String command = messageText.split(" ")[0];
        long chatId = update.getMessage().getChatId();

        List<DanceOutDto> similarDances = danceService.searchDance(messageText);

        if (similarDances != null && !similarDances.isEmpty()){

            List<TelegramButton> buttons = telegramMapper.toDanceButtons(similarDances);
            deleteLastMessage(update);
            sendMessage(telegramUtil.getButtons(chatId, "«" + messageText + "» որոնման արդյունքը", buttons));

        }else {
            sendMessage(new SendMessage(String.valueOf(chatId), "Ներողություն, այս պահին «" + messageText + "» անունով պար չեմ կարողանում գտնել"));
        }
    }
}


