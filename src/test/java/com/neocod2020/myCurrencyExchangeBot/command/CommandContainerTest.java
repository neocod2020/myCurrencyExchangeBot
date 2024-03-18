package com.neocod2020.myCurrencyExchangeBot.command;

import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesService;
import com.neocod2020.myCurrencyExchangeBot.service.SendMessageService;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author ААФ
 */
public class CommandContainerTest {

    CommandContainer commandContainer;

    @BeforeEach
    public void setUp() {
        ExchangeRatesService exchangeRatesService = Mockito.mock(ExchangeRatesService.class);
        SendMessageService sendMessageService = Mockito.mock(SendMessageService.class);
        commandContainer = new CommandContainer(exchangeRatesService, sendMessageService);
    }

    /**
     * Test of retreiveCommand method, of class CommandContainer.
     */
    @Test
    public void testRetreiveCommand() {
        System.out.println("retreiveCommand");
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retreiveCommand(commandName.getCommandName().split(" ")[0]);
                    Assertions.assertNotSame(UnknownCommand.class, command.getClass());
                });
        System.out.println("first ok");
    }

    /**
     * Test of return UnknownCommand method, of class CommandContainer.
     */
    @Test
    public void shouldReturnUnknownCommand() {
        System.out.println("shouldReturnUnknownCommand");
        //given
        String unknownCommand = "/qwerty";
        //when
        Command command = commandContainer.retreiveCommand(unknownCommand);
        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
        System.out.println("second ok");
    }
}
