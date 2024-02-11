package com.neocod2020.myCurrencyExchangeBot.client;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.client.methods.RequestBuilder;
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

    @Value("${cbr.currency.rates.xml.url}")
    private String cbrCurrencyRatesXmlUrl;

    public Optional<String> getCurrencyRatesXML() {

        Request request = new Request.Builder()
                .url(cbrCurrencyRatesXmlUrl)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            return body == null ? Optional.empty() : Optional.of(body.string());
        } catch (IOException ex) {
            log.error(CbrClient.class.getName() + ": " + ex.getMessage());
        }
        return Optional.empty();
    }
}
