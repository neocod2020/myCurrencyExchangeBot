package com.neocod2020.myCurrencyExchangeBot.service;

import com.neocod2020.myCurrencyExchangeBot.bot.MyCurrencyExchangeBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private MyCurrencyExchangeBot myCurrencyExchangeBot;

    public SendMessageServiceImpl(MyCurrencyExchangeBot myCurrencyExchangeBot) {
        this.myCurrencyExchangeBot = myCurrencyExchangeBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);

        try {
            myCurrencyExchangeBot.execute(sendMessage);
        } catch (TelegramApiException ex) {
            log.error("Error sending message " + ex.getMessage());
        }
    }

}
