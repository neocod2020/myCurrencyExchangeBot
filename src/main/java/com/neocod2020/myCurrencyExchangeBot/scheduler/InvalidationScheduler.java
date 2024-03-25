package com.neocod2020.myCurrencyExchangeBot.scheduler;

import com.neocod2020.myCurrencyExchangeBot.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author ААФ
 */
@Component
public class InvalidationScheduler {

    @Autowired
    private ExchangeRatesService service;

    @Scheduled(cron = "${cron.expression}")
    public void invalidateCache() {
        service.clearEURcache();
        service.clearUSDcache();
    }

}
