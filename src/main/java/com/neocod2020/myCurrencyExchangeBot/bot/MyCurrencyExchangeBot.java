package com.neocod2020.myCurrencyExchangeBot.bot;

import com.neocod2020.myCurrencyExchangeBot.command.CommandContainer;
import com.neocod2020.myCurrencyExchangeBot.command.MenueList;
import com.neocod2020.myCurrencyExchangeBot.config.BotConfig;
import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesService;
import com.neocod2020.myCurrencyExchangeBot.service.SendMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ААФ
 */
@Slf4j
@Component
public class MyCurrencyExchangeBot extends TelegramLongPollingBot {

    private final BotConfig config;
    private final CommandContainer commandContainer;
    private final MenueList menueList = new MenueList();

    public MyCurrencyExchangeBot(BotConfig config, ExchangeRatesService exchangeRatesService) {
        this.config = config;
        this.commandContainer = new CommandContainer(exchangeRatesService, new SendMessageServiceImpl(this));
        SetMyCommands smc = new SetMyCommands(menueList.getBotCommands(), new BotCommandScopeDefault(), null);
        try {
            this.execute(smc);
        } catch (TelegramApiException ex) {
            log.error("Error executing menue of command " + ex.getMessage());
        }
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotname();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) return;
        
        String message = update.getMessage().getText();      
        commandContainer.retreiveCommand(message).execute(update);
    }

}
