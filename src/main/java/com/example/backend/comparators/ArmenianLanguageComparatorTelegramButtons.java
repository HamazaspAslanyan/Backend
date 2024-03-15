package com.example.backend.comparators;

import com.example.backend.entities.TelegramButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArmenianLanguageComparatorTelegramButtons<T extends TelegramButton> implements Comparator<T> {


    private final List<String> priorityList = new ArrayList<>(
            List.of("Ա", "ա", "Բ", "բ", "Գ", "գ", "Դ", "դ", "Ե", "ե", "Զ", "զ", "Է", "է", "Թ", "թ",
                    "Ժ", "ժ", "Ի", "ի", "Լ", "լ", "Խ", "խ", "Ծ", "ծ", "Կ", "կ", "Հ", "հ", "Ձ", "ձ",
                    "Ղ", "ղ", "Ճ", "ճ", "Մ", "մ", "Յ", "յ", "Ն", "ն", "Շ", "շ", "Ո", "ո", "Չ", "չ",
                    "Պ", "պ", "Ջ", "ջ", "Ռ", "ռ", "Ս", "ս", "Վ", "վ", "Տ", "տ", "Ր", "ր", "Ց", "ց",
                    "Ու", "ՈՒ", "ու", "Փ", "փ", "Ք", "ք", "ԵՎ", "Եվ", "և", "Օ", "օ", "Ֆ", "ֆ"));

    @Override
    public int compare(T button1, T button2) {

        int minLength = Math.min(button1.getText().length(), button2.getText().length());
        for (int i = 0, j = 0; i < minLength; i++, j++) {
            String letter1 = getArmenianLetter(button1.getText(), i);
            if (letter1 == "ՈՒ" || letter1 == "Ու" || letter1 == "ու" || letter1 == "ԵՎ" || letter1 == "Եվ"){
                i++;
            }
            String letter2 = getArmenianLetter(button2.getText(), j);

            if (letter2 == "ՈՒ" || letter2 == "Ու" || letter2 == "ու" || letter2 == "ԵՎ" || letter2 == "Եվ"){
                j++;
            }

            int result = compareArmenianLetters(letter1, letter2);
            if (result != 0) {
                return result;
            }
        }
        return Integer.compare(button1.getText().length(), button2.getText().length());
    };

    private static String getArmenianLetter(String word, int index) {
        // Handle special case for 'Ու', 'ՈՒ', 'ԵՎ' and 'Եվ'
        if (index < word.length() - 1 && word.charAt(index) == 'Ո') {
            if(word.charAt(index + 1) == 'ւ') {
                return "Ու";
            } else if (word.charAt(index + 1) == 'Ւ'){
                return "ՈՒ";
            }
        } else if (index < word.length() - 1 && word.charAt(index) == 'Ե') {
            if(word.charAt(index + 1) == 'վ') {
                return "Եվ";
            } else if (word.charAt(index + 1) == 'Վ'){
                return "ԵՎ";
            }
        } else if (index < word.length() - 1 && word.charAt(index) == 'ո' && word.charAt(index + 1) == 'ւ') {
            return "ու";
        }
        return String.valueOf(word.charAt(index));
    }
    private int compareArmenianLetters(String letter1, String letter2) {
        // Define the priority of Armenian letters

        // Compare the indices of the letters in the priority list
        int index1 = priorityList.indexOf(letter1);
        int index2 = priorityList.indexOf(letter2);

        // Compare the indices
        return Integer.compare(index1, index2);
    }
}
