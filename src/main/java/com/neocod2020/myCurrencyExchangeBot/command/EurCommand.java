package com.neocod2020.myCurrencyExchangeBot.command;

import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesService;
import com.neocod2020.myCurrencyExchangeBot.service.SendMessageService;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * @author ААФ
 */
@Slf4j
public class EurCommand implements Command {

    private final ExchangeRatesService exchangeRatesService;
    private final SendMessageService sendMessageService;

    private String EUR_COMMAND_TEXT = "%s EUR exchange rate is %s RUR";
    private String NO_EUR_RATES = "Failed to retreive EUR exchange rate. Try later";

    public EurCommand(ExchangeRatesService exchangeRatesService, SendMessageService sendMessageService) {
        this.exchangeRatesService = exchangeRatesService;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        String msg = EUR_COMMAND_TEXT;
        Long chatId = update.getMessage().getChatId();
        try {
            String eurRate = exchangeRatesService.getEURExchangeRate();
            sendMessageService.sendMessage(String.valueOf(chatId), String.format(msg, LocalDate.now(), eurRate));
        } catch (Exception ex) {
            log.error(NO_EUR_RATES + ": " + ex.getMessage());
            msg = NO_EUR_RATES;
            sendMessageService.sendMessage(String.valueOf(chatId), msg);
        }
    }
}
