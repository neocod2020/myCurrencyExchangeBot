package com.neocod2020.myCurrencyExchangeBot.command;

import lombok.Getter;

/**
 *
 * @author ААФ
 */
public enum CommandName {    
    USD("/usd USD exchange rate"),
    EUR("/eur EUR exchange rate");
    
    @Getter
    private String commandName;

    private CommandName(String commandName) {
        this.commandName = commandName;
    }   
    
}
