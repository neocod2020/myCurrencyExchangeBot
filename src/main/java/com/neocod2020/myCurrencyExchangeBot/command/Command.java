package com.neocod2020.myCurrencyExchangeBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author ААФ
 */
public interface Command {
    void execute(Update update);
}
