package com.neocod2020.myCurrencyExchangeBot.command;

import com.neocod2020.myCurrencyExchangeBot.bot.MyCurrencyExchangeBot;
import com.neocod2020.myCurrencyExchangeBot.client.CbrClient;
import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesService;
import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesServiceImpl;
import com.neocod2020.myCurrencyExchangeBot.service.SendMessageService;
import com.neocod2020.myCurrencyExchangeBot.service.SendMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ААФ
 */
public abstract class AbstractCommandTest {

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();
    
    protected MyCurrencyExchangeBot myCurrencyExchangeBot = Mockito.mock(MyCurrencyExchangeBot.class);
    protected CbrClient client = Mockito.mock(CbrClient.class);
    SendMessageService sendMessageService = new SendMessageServiceImpl(myCurrencyExchangeBot);
    ExchangeRatesService exchangeRatesService = new ExchangeRatesServiceImpl(client);
    
    public Update prepareUpdate(Long chatId, String commandName) {
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(commandName);
        update.setMessage(message);
        return update;
    }
    
    /**
     * Test of retrieveCommand method, of class CommandContainer.
     */
    @Test
    public void properlyExecuteCommandTest() throws TelegramApiException {
        System.out.println("testExecuteCommand " + getCommandName());
        // given
        Long chatId = 123456789L;
        Update update = prepareUpdate(chatId, getCommandName());
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);
        
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId.toString());
        sm.setText(getCommandMessage());
        sm.enableHtml(true);
        
        // when
        getCommand().execute(update);
        
        //then
        Mockito.verify(myCurrencyExchangeBot).execute(sm);
        
    }
}
