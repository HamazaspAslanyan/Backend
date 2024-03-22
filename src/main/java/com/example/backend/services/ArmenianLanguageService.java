package com.example.backend.services;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ArmenianLanguageService {


    private final Map<String, String> ARMENIAN_TO_ENGLISH_MAP;

    {
        ARMENIAN_TO_ENGLISH_MAP = new HashMap<>();

        ARMENIAN_TO_ENGLISH_MAP.put("ու", "u");
        ARMENIAN_TO_ENGLISH_MAP.put("ՈՒ", "U");
        ARMENIAN_TO_ENGLISH_MAP.put("Ու", "U");
        ARMENIAN_TO_ENGLISH_MAP.put(" ով", " oվ");
        ARMENIAN_TO_ENGLISH_MAP.put(" Ով", " Ով");
        ARMENIAN_TO_ENGLISH_MAP.put(" ո", " vo");
        ARMENIAN_TO_ENGLISH_MAP.put(" Ո", " Vo");
        ARMENIAN_TO_ENGLISH_MAP.put("ԵՎ", "Yev");
        ARMENIAN_TO_ENGLISH_MAP.put("Եվ", "yev");
        ARMENIAN_TO_ENGLISH_MAP.put("և", "yev");
        ARMENIAN_TO_ENGLISH_MAP.put("Ա", "A");
        ARMENIAN_TO_ENGLISH_MAP.put("ա", "a");
        ARMENIAN_TO_ENGLISH_MAP.put("Բ", "B");
        ARMENIAN_TO_ENGLISH_MAP.put("բ", "b");
        ARMENIAN_TO_ENGLISH_MAP.put("Գ", "G");
        ARMENIAN_TO_ENGLISH_MAP.put("գ", "g");
        ARMENIAN_TO_ENGLISH_MAP.put("Դ", "D");
        ARMENIAN_TO_ENGLISH_MAP.put("դ", "d");
        ARMENIAN_TO_ENGLISH_MAP.put(" Ե", "Ye");
        ARMENIAN_TO_ENGLISH_MAP.put(" ե", "ye");
        ARMENIAN_TO_ENGLISH_MAP.put("Ե", "E");
        ARMENIAN_TO_ENGLISH_MAP.put("ե", "e");
        ARMENIAN_TO_ENGLISH_MAP.put("Զ", "Z");
        ARMENIAN_TO_ENGLISH_MAP.put("զ", "z");
        ARMENIAN_TO_ENGLISH_MAP.put("Է", "e");
        ARMENIAN_TO_ENGLISH_MAP.put("է", "e");
        ARMENIAN_TO_ENGLISH_MAP.put("Ը", "E");
        ARMENIAN_TO_ENGLISH_MAP.put("ը", "e");
        ARMENIAN_TO_ENGLISH_MAP.put("Թ", "T");
        ARMENIAN_TO_ENGLISH_MAP.put("թ", "t");
        ARMENIAN_TO_ENGLISH_MAP.put("Ժ", "Zh");
        ARMENIAN_TO_ENGLISH_MAP.put("ժ", "zh");
        ARMENIAN_TO_ENGLISH_MAP.put("Ի", "I");
        ARMENIAN_TO_ENGLISH_MAP.put("ի", "i");
        ARMENIAN_TO_ENGLISH_MAP.put("Լ", "L");
        ARMENIAN_TO_ENGLISH_MAP.put("լ", "l");
        ARMENIAN_TO_ENGLISH_MAP.put("Խ", "Kh");
        ARMENIAN_TO_ENGLISH_MAP.put("խ", "kh");
        ARMENIAN_TO_ENGLISH_MAP.put("Ծ", "Ts");
        ARMENIAN_TO_ENGLISH_MAP.put("ծ", "ts");
        ARMENIAN_TO_ENGLISH_MAP.put("Կ", "K");
        ARMENIAN_TO_ENGLISH_MAP.put("կ", "k");
        ARMENIAN_TO_ENGLISH_MAP.put("Հ", "H");
        ARMENIAN_TO_ENGLISH_MAP.put("հ", "h");
        ARMENIAN_TO_ENGLISH_MAP.put("Ձ", "Dz");
        ARMENIAN_TO_ENGLISH_MAP.put("ձ", "dz");
        ARMENIAN_TO_ENGLISH_MAP.put("Ղ", "Gh");
        ARMENIAN_TO_ENGLISH_MAP.put("ղ", "gh");
        ARMENIAN_TO_ENGLISH_MAP.put("Ճ", "Tch");
        ARMENIAN_TO_ENGLISH_MAP.put("ճ", "tch");
        ARMENIAN_TO_ENGLISH_MAP.put("Մ", "M");
        ARMENIAN_TO_ENGLISH_MAP.put("մ", "m");
        ARMENIAN_TO_ENGLISH_MAP.put("Յ", "Y");
        ARMENIAN_TO_ENGLISH_MAP.put("յ", "y");
        ARMENIAN_TO_ENGLISH_MAP.put("Ն", "N");
        ARMENIAN_TO_ENGLISH_MAP.put("ն", "n");
        ARMENIAN_TO_ENGLISH_MAP.put("Շ", "Sh");
        ARMENIAN_TO_ENGLISH_MAP.put("շ", "sh");
        ARMENIAN_TO_ENGLISH_MAP.put("Ո", "Vo");
        ARMENIAN_TO_ENGLISH_MAP.put("ո", "o");
        ARMENIAN_TO_ENGLISH_MAP.put("Չ", "Ch");
        ARMENIAN_TO_ENGLISH_MAP.put("չ", "ch");
        ARMENIAN_TO_ENGLISH_MAP.put("Պ", "P");
        ARMENIAN_TO_ENGLISH_MAP.put("պ", "p");
        ARMENIAN_TO_ENGLISH_MAP.put("Ջ", "J");
        ARMENIAN_TO_ENGLISH_MAP.put("ջ", "j");
        ARMENIAN_TO_ENGLISH_MAP.put("Ռ", "R");
        ARMENIAN_TO_ENGLISH_MAP.put("ռ", "r");
        ARMENIAN_TO_ENGLISH_MAP.put("Ս", "S");
        ARMENIAN_TO_ENGLISH_MAP.put("ս", "s");
        ARMENIAN_TO_ENGLISH_MAP.put("Վ", "V");
        ARMENIAN_TO_ENGLISH_MAP.put("վ", "v");
        ARMENIAN_TO_ENGLISH_MAP.put("Տ", "T");
        ARMENIAN_TO_ENGLISH_MAP.put("տ", "t");
        ARMENIAN_TO_ENGLISH_MAP.put("Ր", "R");
        ARMENIAN_TO_ENGLISH_MAP.put("ր", "r");
        ARMENIAN_TO_ENGLISH_MAP.put("Ց", "Ts");
        ARMENIAN_TO_ENGLISH_MAP.put("ց", "ts");
        ARMENIAN_TO_ENGLISH_MAP.put("Փ", "P");
        ARMENIAN_TO_ENGLISH_MAP.put("փ", "p");
        ARMENIAN_TO_ENGLISH_MAP.put("Ք", "Q");
        ARMENIAN_TO_ENGLISH_MAP.put("ք", "q");
        ARMENIAN_TO_ENGLISH_MAP.put("Օ", "O");
        ARMENIAN_TO_ENGLISH_MAP.put("օ", "o");
        ARMENIAN_TO_ENGLISH_MAP.put("Ֆ", "F");
        ARMENIAN_TO_ENGLISH_MAP.put("ֆ", "f");
    }

    public String fromArmToEng(String armenianStr) {
        for (Map.Entry<String, String> entry : ARMENIAN_TO_ENGLISH_MAP.entrySet()) {
            armenianStr = armenianStr.replaceAll("(?i)" + entry.getKey(), entry.getValue());
        }
        return armenianStr;
    }
}
