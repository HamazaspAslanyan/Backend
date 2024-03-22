package com.example.backend.dtos.telegram;


import com.example.backend.services.ArmenianLanguageService;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelegramButton{
    String text;
    String callBack;

}
