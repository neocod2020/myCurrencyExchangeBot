package com.neocod2020.myCurrencyExchangeBot.client;

import java.io.IOException;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ААФ
 */
@Slf4j
@Component
public class CbrClient {

    @Autowired
    private OkHttpClient okHttpClient;

    @Getter
    @Value("${cbr.currency.rates.xml.url}")
    public String cbrCurrencyRatesXmlUrl;

    public Optional<String> getCurrencyRatesXML() {

        Request request = new Request.Builder()
                .url(cbrCurrencyRatesXmlUrl)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            var bodyString = body.string();
            Optional<String> optionalBody = Optional.of(bodyString);
            return optionalBody;
        } catch (IOException ex) {
            log.error(CbrClient.class.getName() + ": " + ex.getMessage());
        }
        return Optional.empty();
    }
}
