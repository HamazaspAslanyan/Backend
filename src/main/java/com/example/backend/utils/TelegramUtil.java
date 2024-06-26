package com.example.backend.utils;

import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.dtos.telegram.TelegramFile;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.interfaces.Validable;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramUtil {

    SendAudio getAudio(long chatId, String Header, TelegramFile audio){

        URL url = getClass().getClassLoader().getResource(audio.getPath());
        File audioFile = new File(url.getFile());
        InputFile inputFile = new InputFile(audioFile, audio.getName());

        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(String.valueOf(chatId));
        sendAudio.setCaption(Header);
        sendAudio.setAudio(inputFile);
        sendAudio.setCaption(audio.getCaption());
        sendAudio.setTitle(audio.getTitle());

        return sendAudio;
    }


    private SendPhoto getPhoto(long chatId, TelegramFile file, String caption){

        URL url = getClass().getClassLoader().getResource(file.getPath());
        File imageFile = new File(url.getFile());
        InputFile inputFile = new InputFile(imageFile, file.getName());

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption(caption);

        return sendPhoto;

    }


    SendMediaGroup getMediaGroup(long chatId, List<TelegramFile> audioList){

        List<InputMedia> mediaList = new ArrayList<>();
        InputMediaAudio inputMediaAudio = new InputMediaAudio();

        for (TelegramFile audio : audioList){

            URL url = getClass().getClassLoader().getResource(audio.getPath());
            File audioFile = new File(url.getFile());

            inputMediaAudio.setMedia(audioFile, audio.getName());
            inputMediaAudio.setCaption(audio.getCaption());
            inputMediaAudio.setTitle(audio.getTitle());

            //TODO what is it
            inputMediaAudio.setMediaName("Kuku");

            mediaList.add(inputMediaAudio);
        }

        SendMediaGroup sendMediaGroup = new SendMediaGroup();
        sendMediaGroup.setChatId(String.valueOf(chatId));
        sendMediaGroup.setMedias(mediaList);

        return sendMediaGroup;
    }


    public SendMessage getButtons(long chatId, String header, List<TelegramButton> buttons){

        SendMessage sendMessage = new SendMessage();

        sendMessage.setReplyMarkup(getInlineKeyboardMarkup(buttons));
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(header);

        return  sendMessage;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup(List<TelegramButton> buttons){

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List <List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List <InlineKeyboardButton> row;
        InlineKeyboardButton button;

        for (TelegramButton buttonModel: buttons){
            row = new ArrayList< >();
            button = new InlineKeyboardButton();

            button.setText(buttonModel.getText());
            button.setCallbackData(buttonModel.getCallBack());

            row.add(button);
            rowList.add(row);
        }

        markupInline.setKeyboard(rowList);
        return markupInline;
    }

    public Validable getAudioList(Long chatId, List<TelegramFile> audioList, String header){

        if (audioList.size() > 1) {
            return this.getMediaGroup(chatId, audioList);
        }else if (!audioList.isEmpty()){
            return this.getAudio(chatId, header, audioList.get(0));
        }
        return null;
    }

}
