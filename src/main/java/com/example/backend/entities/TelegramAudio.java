package com.example.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelegramAudio {
    String path;
    String name; /* երգի անունը որպես ֆայլ */

    String caption; // Տեքստ որպես նկարագրություն

    String title; // Ինչ որ երևում ա որպես երգի անուն

}