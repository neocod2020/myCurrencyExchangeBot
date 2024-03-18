package com.neocod2020.myCurrencyExchangeBot.command;

import com.neocod2020.myCurrencyExchangeBot.service.SendMessageService;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author ААФ
 */
public class UnknownCommand implements Command {
    
    private final SendMessageService messageService;
    
    @Getter
    public static  String UNKNOWN_MSG = "Use command menue please";

    public UnknownCommand(SendMessageService messageService) {
        this.messageService = messageService;
    }    

    @Override
    public void execute(Update update) {
        messageService.sendMessage(String.valueOf(update.getMessage().getChatId()), UNKNOWN_MSG);
        }
    
}
