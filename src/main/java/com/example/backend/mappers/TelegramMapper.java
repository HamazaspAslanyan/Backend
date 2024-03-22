package com.example.backend.mappers;

import com.example.backend.comparators.ArmenianLanguageComparatorTelegramButtons;
import com.example.backend.dtos.DanceDto;
import com.example.backend.dtos.telegram.TelegramButton;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramMapper {

    public List<TelegramButton> toDanceButtons(List<DanceDto> dances){

        List<TelegramButton> buttons = new ArrayList<>();

        for (DanceDto dance : dances){
            String text = dance.getName();
            if(dance.getDescription() != null){
                text += "\uD83D\uDCC4";
            }
            if(dance.getMusicList() != null){
                text += "\uD83C\uDFA7";
            }
            String callBack = "dance_" + dance.getId();
            buttons.add(new TelegramButton(text, callBack));
        }

        buttons.sort(new ArmenianLanguageComparatorTelegramButtons<>());

        return buttons;
    }
}
