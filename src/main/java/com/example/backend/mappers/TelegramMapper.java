package com.example.backend.mappers;

import com.example.backend.comparators.ArmenianLanguageComparatorTelegramButtons;
import com.example.backend.dtos.out.DanceOutDto;
import com.example.backend.dtos.telegram.TelegramButton;
import com.example.backend.dtos.telegram.TelegramFile;
import com.example.backend.entities.Music;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class TelegramMapper {

//    public List<TelegramButton> toDanceButtons(List<DanceOptionDto> dances){
//
//        List<TelegramButton> buttons = new ArrayList<>();
//
//        for (DanceOptionDto dance : dances){
//            String text = dance.getName();
////            if(dance.getDescription() != null){
////                text += "\uD83D\uDCC4";
////            }
////            if(dance.getMusicList() != null){
////                text += "\uD83C\uDFA7";
////            }
//            String callBack = "dance_" + dance.getId();
//            buttons.add(new TelegramButton(text, callBack));
//        }
//
//        buttons.sort(new ArmenianLanguageComparatorTelegramButtons<>());
//
//        return buttons;
//    }
    public List<TelegramButton> toDanceButtons(List<DanceOutDto> dances){

        List<TelegramButton> buttons = new ArrayList<>();

//        for (DanceOutDto dance : dances){
//            String text = dance.getName();
////            if(dance.getDescription() != null){
////                text += "\uD83D\uDCC4";
////            }
////            if(dance.getMusicList() != null){
////                text += "\uD83C\uDFA7";
////            }
//            String callBack = "dance_" + dance.getId();
//            buttons.add(new TelegramButton(text, callBack));
//        }

        buttons.sort(new ArmenianLanguageComparatorTelegramButtons<>());

        return buttons;
    }

    public List<TelegramFile> musicToFileList(Set<Music> musicList){
        List<TelegramFile> telegramFileList = new ArrayList<>();
        String fullPath = "C:\\Users\\Aslanyan\\Desktop\\Թամզարա - «Սասնա ծռեր համույթ» - Tamzara (Sasna Crer Folk Group).mp3";



        if (musicList != null && !musicList.isEmpty()){
            for(Music music : musicList) {
                TelegramFile file = new TelegramFile();
                // Քաշելուց հետո երգի անուն
                file.setName(music.getName());
                file.setTitle("mama");
                file.setPath(fullPath);
                //Երգի կողքին երևացող անուն
                file.setTitle("papa");
                // Նկարագրություն
                file.setCaption("pipi");
                telegramFileList.add(file);
            }
        }
        return telegramFileList;
    }

//    public List<TelegramButton> toDanceButtons(List<DanceDto> dances){
//
//        List<TelegramButton> buttons = new ArrayList<>();
//
//        for (DanceDto dance : dances){
//            String text = dance.getName();
//            if(dance.getDescription() != null){
//                text += "\uD83D\uDCC4";
//            }
//            if(dance.getMusicList() != null){
//                text += "\uD83C\uDFA7";
//            }
//            String callBack = "dance_" + dance.getId();
//            buttons.add(new TelegramButton(text, callBack));
//        }
//
//        buttons.sort(new ArmenianLanguageComparatorTelegramButtons<>());
//
//        return buttons;
//    }
}
