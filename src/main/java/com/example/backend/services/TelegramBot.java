package com.example.backend.services;//package com.example.backend.services;
//
import com.example.backend.comparators.ArmenianLanguageComparatorTelegramButtons;
import com.example.backend.config.BotConfig;
import com.example.backend.entities.TelegramButton;
import com.example.backend.entities.TelegramFile;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig config;
    ArmenianLanguageService armenianLanguageService = new ArmenianLanguageService();

    private BotFactory botFactory = new BotFactory();

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

        if(update.hasMessage()){
            Message message = update.getMessage();

            if (message.hasText()){
                String messageText = message.getText();

                switch (messageText){
                    case "/dances" : sendDanceList(update);
                        break;
                    case "/events" : sendEventsList(update);
                        break;
                    case "/classes" : sendClassesList(update);
                        break;
                    case "/groups" : sendGroupsList(update);
                        break;
                    case "/support" : sendSupportList(update);
                        break;
                    default: answerMessage(update);
                        break;

                }
            }

        }else if (update.hasCallbackQuery()) {

            deleteLastCallback(update);


            String[] callBackData = update.getCallbackQuery().getData().split("_");
            String listType = callBackData[0];
            String itemId = callBackData[callBackData.length - 1];
            switch (listType){
                case "dance" : sendDance(update.getCallbackQuery().getMessage().getChatId(), itemId);
                    break;
                case "event" : sendEventsList(update);
                    break;
                case "class" : sendClassesList(update);
                    break;
                case "group" : sendGroupsList(update);
                    break;
                case "support" : sendSupportList(update);
                    break;
                default: answerMessage(update);
                    break;
            }
        }
    }



    private void sendDance(Long chatId, String danceId){
        List<TelegramFile> audioList = new ArrayList<>();

        if (audioList.size() > 1) {
            send(botFactory.getMediaGroup(chatId, audioList));
        }else if (!audioList.isEmpty()){
            send(botFactory.getAudio(chatId, "header", audioList.get(0)));
        }
    }

    private void sendDanceList(Update update){

        deleteLastMessage(update);

        List<TelegramButton> buttons = new ArrayList<>();

        buttons.add(new TelegramButton( "Աստվածածնա \uD83D\uDD0A\uD83D\uDCAC",                  "dance_001"));
        buttons.add(new TelegramButton( "Արզումանի",                   "dance_002"));
        buttons.add(new TelegramButton( "Բիճո",                        "dance_003"));
        buttons.add(new TelegramButton( "Գյովնդ",                      "dance_005"));
        buttons.add(new TelegramButton( "Գյովնդ Թագավորի",             "dance_006"));
        buttons.add(new TelegramButton( "Գորանի",                      "dance_007"));
        buttons.add(new TelegramButton( "Դաբկի",                       "dance_008"));
        buttons.add(new TelegramButton( "Դերիկո",                      "dance_009"));
        buttons.add(new TelegramButton( "Ետ ու առաջ",                  "dance_010"));
        buttons.add(new TelegramButton( "Երեք ոտք - ալաշկերտի",        "dance_011"));
        buttons.add(new TelegramButton( "Երեք ոտք - կարնո",            "dance_012"));
        buttons.add(new TelegramButton( "Երեք ոտք - սեբաստիո",         "dance_013"));
        buttons.add(new TelegramButton( "Զաքենի",                      "dance_014"));
        buttons.add(new TelegramButton( "Էջմիածին",                    "dance_015"));
        buttons.add(new TelegramButton( "Էրիշտա",                      "dance_016"));
        buttons.add(new TelegramButton( "Թագվորապար",                  "dance_017"));
        buttons.add(new TelegramButton( "Թակ թանզարա",                 "dance_018"));
        buttons.add(new TelegramButton( "Թամզարա",                     "dance_019"));
        buttons.add(new TelegramButton( "Թամիր աղա",                   "dance_030"));
        buttons.add(new TelegramButton( "Թաշկինակներով պար",           "dance_031"));
        buttons.add(new TelegramButton( "Թարս պար",                    "dance_032"));
        buttons.add(new TelegramButton( "Թոփալ պար",                   "dance_033"));
        buttons.add(new TelegramButton( "Թրթռուկ",                     "dance_034"));
        buttons.add(new TelegramButton( "Ինը ոտք",                     "dance_035"));
        buttons.add(new TelegramButton( "Իշխանաց պար - շեյխանի",       "dance_036"));
        buttons.add(new TelegramButton( "Լաչին",                       "dance_037"));
        buttons.add(new TelegramButton( "Լե լե աշե",                   "dance_038"));
        buttons.add(new TelegramButton( "Լիկիլի",                      "dance_039"));
        buttons.add(new TelegramButton( "Լորկե",                       "dance_040"));
        buttons.add(new TelegramButton( "Լորկե - Մուսալեռան",          "dance_041"));
        buttons.add(new TelegramButton( "Լուտկի",                      "dance_042"));
        buttons.add(new TelegramButton( "Խամ խամա",                    "dance_043"));
        buttons.add(new TelegramButton( "Խնկի ծառ",                    "dance_044"));
        buttons.add(new TelegramButton( "Խոշ բիլազիկ",                 "dance_045"));
        buttons.add(new TelegramButton( "Ծաղկաձորի",                   "dance_046"));
        buttons.add(new TelegramButton( "Ծափիկ",                       "dance_047"));
        buttons.add(new TelegramButton( "Կայնե ֆիդան",                 "dance_048"));
        buttons.add(new TelegramButton( "Կարմիր ֆստան",                "dance_049"));
        buttons.add(new TelegramButton( "Կռնկավե",                     "dance_050"));
        buttons.add(new TelegramButton( "Կտկտիկ",                      "dance_051"));
        buttons.add(new TelegramButton( "Մայրոքե",                     "dance_052"));
        buttons.add(new TelegramButton( "Ճոճք",                        "dance_053"));
        buttons.add(new TelegramButton( "Մածուն",                      "dance_054"));
        buttons.add(new TelegramButton( "Մարալոյ աղջիկ",               "dance_055"));
        buttons.add(new TelegramButton( "Մինինդուր",                   "dance_056"));
        buttons.add(new TelegramButton( "Մտրակներով պար",              "dance_058"));
        buttons.add(new TelegramButton( "Յարխուշտա",                   "dance_059"));
        buttons.add(new TelegramButton( "Յիլմաճուգ",                   "dance_060"));
        buttons.add(new TelegramButton( "Նարե",                        "dance_061"));
        buttons.add(new TelegramButton( "Նեյ բար",                     "dance_062"));
        buttons.add(new TelegramButton( "Շավալի",                      "dance_063"));
        buttons.add(new TelegramButton( "Խոշ բիլազիկ",                 "dance_064"));
        buttons.add(new TelegramButton( "Շարանի",                      "dance_065"));
        buttons.add(new TelegramButton( "Շորոր - Կարնո",               "dance_066"));
        buttons.add(new TelegramButton( "Շորոր - Սասնա",               "dance_067"));
        buttons.add(new TelegramButton( "Շորոր - Կոմիտասի",            "dance_068"));
        buttons.add(new TelegramButton( "Ուրֆանե",                     "dance_069"));
        buttons.add(new TelegramButton( "Չալ մը դանգի",                "dance_070"));
        buttons.add(new TelegramButton( "Չաչանե",                      "dance_071"));
        buttons.add(new TelegramButton( "Պոզարե",                      "dance_072"));
        buttons.add(new TelegramButton( "Պուդուդի Ջամբար ամի",         "dance_073"));
        buttons.add(new TelegramButton( "Ջումլամեր",                   "dance_074"));
        buttons.add(new TelegramButton( "Ռոստամ բազի",                 "dance_075"));
        buttons.add(new TelegramButton( "Սնջանե",                      "dance_076"));
        buttons.add(new TelegramButton( "Սրապար",                      "dance_077"));
        buttons.add(new TelegramButton( "Վանա ձկնորս",                 "dance_078"));
        buttons.add(new TelegramButton( "Վերվերի",                     "dance_079"));
        buttons.add(new TelegramButton( "Տալտալա",                     "dance_080"));
        buttons.add(new TelegramButton( "Տասներկու ոտք",               "dance_081"));
        buttons.add(new TelegramButton( "Ցորեն եմ ցանե",               "dance_082"));
        buttons.add(new TelegramButton( "Փափուռի",                     "dance_083"));
        buttons.add(new TelegramButton( "Քեչին վրեն",                  "dance_084"));
        buttons.add(new TelegramButton( "Քերծի",                       "dance_085"));
        buttons.add(new TelegramButton( "Քոչարի - աղքտի",              "dance_086"));
        buttons.add(new TelegramButton( "Քոչարի - ալաշկերտի",          "dance_087"));
        buttons.add(new TelegramButton( "Քոչարի - բուլանուխի",         "dance_088"));
        buttons.add(new TelegramButton( "Քոչարի - Կարնո",              "dance_089"));
        buttons.add(new TelegramButton( "Քոչարի - մշու խռ, սասնո",     "dance_090"));
        buttons.add(new TelegramButton( "Քոչարի - Մշո",                "dance_091"));
        buttons.add(new TelegramButton( "Քոչարի - Սղերդի",             "dance_092"));
        buttons.add(new TelegramButton( "Քոչարի - Վանա",               "dance_093"));
        buttons.add(new TelegramButton( "Ֆնջան",                       "dance_094"));
        buttons.add(new TelegramButton( "Բուլուլ",                     "dance_004"));

        buttons.sort(new ArmenianLanguageComparatorTelegramButtons<>());

        send(botFactory.getButtons(update.getMessage().getChatId(), "Պարեղանակներ", buttons));
    }
    private void sendEventsList(Update update){

    }
    private void sendClassesList(Update update){

    }
    private void sendGroupsList(Update update){

    }
    private void sendSupportList(Update update){

    }

    private void answerMessage(Update update){

    }

    void send(SendAudio message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void send(SendMediaGroup message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void send(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void send(DeleteMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    void send(SendPhoto message) {
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
        send(deleteMessage);
    }
}


