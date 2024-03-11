package com.neocod2020.myCurrencyExchangeBot.config;

import com.neocod2020.myCurrencyExchangeBot.bot.MyCurrencyExchangeBot;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
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
    private String botname;

    @Value("${bot.token}")
    private String token;
    
    //@Bean
    
    
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
