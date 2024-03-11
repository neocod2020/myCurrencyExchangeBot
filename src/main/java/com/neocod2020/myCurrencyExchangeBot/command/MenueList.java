package com.neocod2020.myCurrencyExchangeBot.command;

import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

/**
 *
 * @author ААФ
 */
public class MenueList {
    
    private final List<BotCommand> commands = new ArrayList<>();
    
    public List<BotCommand> getBotCommands(){        
                
        for(CommandName s : CommandName.values()){
            commands.add(new BotCommand(s.getCommandName().split(" ")[0], s.getCommandName().split(" ")[0]));
        }
        return commands;
    }
    
}
