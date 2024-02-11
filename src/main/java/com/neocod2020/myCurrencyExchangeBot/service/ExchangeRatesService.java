package com.neocod2020.myCurrencyExchangeBot.service;

/**
 *
 * @author ААФ
 */
public interface ExchangeRatesService {
    String getUSDExchangeRate() throws Exception;
    String getEURExchangeRate() throws Exception;
}
