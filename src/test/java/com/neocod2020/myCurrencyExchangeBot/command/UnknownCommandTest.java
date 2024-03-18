package com.neocod2020.myCurrencyExchangeBot.command;

/**
 *
 * @author ААФ
 */
public class UnknownCommandTest extends AbstractCommandTest {
    
    @Override
    String getCommandName() {
        return "UnknownCommand";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_MSG;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendMessageService);
    }

}
