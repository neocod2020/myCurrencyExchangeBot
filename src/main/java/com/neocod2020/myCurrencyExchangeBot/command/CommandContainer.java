package com.neocod2020.myCurrencyExchangeBot.command;

import com.google.common.collect.ImmutableMap;
import static com.neocod2020.myCurrencyExchangeBot.command.CommandName.EUR;
import static com.neocod2020.myCurrencyExchangeBot.command.CommandName.USD;
import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesService;
import com.neocod2020.myCurrencyExchangeBot.service.SendMessageService;

/**
 *
 * @author ААФ
 */
public class CommandContainer {

    //  @Cacheable(value = "commandMap")
    private ImmutableMap<String, Command> commandMap;
    private Command unknownCommand;

    public CommandContainer(ExchangeRatesService exchangeRatesService, SendMessageService sendMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(USD.getCommandName().split(" ")[0], new UsdCommand(exchangeRatesService, sendMessageService))
                .put(EUR.getCommandName().split(" ")[0], new EurCommand(exchangeRatesService, sendMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command retreiveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
