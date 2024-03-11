package com.neocod2020.myCurrencyExchangeBot.config;

import com.neocod2020.myCurrencyExchangeBot.bot.MyCurrencyExchangeBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 *
 * @author ААФ
 */
@Component
public class BotInitializer {
    
    @Autowired
    MyCurrencyExchangeBot bot;
    
    //@EventListener({ContextRefreshedEvent.class})
    @Bean
    public TelegramBotsApi telegramBotsApi(MyCurrencyExchangeBot bot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
    
}
