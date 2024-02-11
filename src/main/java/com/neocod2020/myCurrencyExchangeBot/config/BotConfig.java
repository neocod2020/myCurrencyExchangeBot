package com.neocod2020.myCurrencyExchangeBot.config;

import com.neocod2020.myCurrencyExchangeBot.bot.MyCurrencyExchangeBot;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 *
 * @author ААФ
 */
@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    
    @Value("${bot.name}")
    private String username;

    @Value("${bot.token}")
    private String token;
    
    @Bean
    //@EventListener({ContextRefreshedEvent.class})
    public TelegramBotsApi telegramBotsApi(MyCurrencyExchangeBot bot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
    
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
