package com.neocod2020.myCurrencyExchangeBot.service;

import com.neocod2020.myCurrencyExchangeBot.bot.MyCurrencyExchangeBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author ААФ
 */
public class SendMessageServiceTest {

    private SendMessageService sendMessageService;
    private MyCurrencyExchangeBot myCurrencyExchangeBot;

    @BeforeEach
    public void setUp() {
        myCurrencyExchangeBot = Mockito.mock(MyCurrencyExchangeBot.class);
        sendMessageService = new SendMessageServiceImpl(myCurrencyExchangeBot);
    }

    /**
     * Test of sendMessage method, of class SendMessageService.
     */
    @Test
    public void testProperlySendMessage() throws TelegramApiException {
        
        // given
        String chatId = "test_chatID";
        String message = "test_msg_text";

        SendMessage sm = new SendMessage(chatId, message);
        sm.enableHtml(true);
        // when
        sendMessageService.sendMessage(chatId, message);
        // then
        Mockito.verify(myCurrencyExchangeBot).execute(sm);
        
        System.out.println("testProperlySendMessage ok");

    }

}
