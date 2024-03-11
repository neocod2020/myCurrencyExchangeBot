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
public class UsdCommand implements Command {

    private final ExchangeRatesService exchangeRatesService;
    private final SendMessageService sendMessageService;

    private String USD_COMMAND_TEXT = "%s USD exchange rate is %s RUR";
    private String NO_USD_RATES = "Failed to retreive USD exchange rate. Try later";

    public UsdCommand(ExchangeRatesService exchangeRatesService, SendMessageService sendMessageService) {
        this.exchangeRatesService = exchangeRatesService;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        String msg = USD_COMMAND_TEXT;
        Long chatId = update.getMessage().getChatId();
        try {
            String usdRate = exchangeRatesService.getUSDExchangeRate();
            sendMessageService.sendMessage(String.valueOf(chatId), String.format(msg, LocalDate.now(), usdRate));
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(NO_USD_RATES + ": " + ex.getMessage());
            msg = NO_USD_RATES;
            sendMessageService.sendMessage(String.valueOf(chatId), msg);
        }
    }
}
