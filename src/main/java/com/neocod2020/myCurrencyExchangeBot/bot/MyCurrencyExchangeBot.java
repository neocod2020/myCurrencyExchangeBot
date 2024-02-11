package com.neocod2020.myCurrencyExchangeBot.bot;

import com.neocod2020.myCurrencyExchangeBot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ААФ
 */
@Slf4j
@Component
public class MyCurrencyExchangeBot extends TelegramLongPollingBot {
    
    final BotConfig config;

    public MyCurrencyExchangeBot(BotConfig config) {        
        this.config = config;
    }    
    
     @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public String getBotUsername() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String textOfMessage = update.getMessage().getText();
    long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage(String.valueOf(chatId), textOfMessage);
        try {
            execute(message);
        } catch (TelegramApiException ex) {
            log.error(MyCurrencyExchangeBot.class.getName() + ": " + ex.getMessage());
        }}
    
}
