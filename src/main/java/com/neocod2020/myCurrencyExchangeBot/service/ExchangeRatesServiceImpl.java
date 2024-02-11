package com.neocod2020.myCurrencyExchangeBot.service;

import com.neocod2020.myCurrencyExchangeBot.client.CbrClient;
import java.io.StringReader;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Slf4j
@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {
    
    private static final String USD_XPATH = "/ValCurs//Valute[@ID='R01235']/Value";
    private static final String EUR_XPATH = "/ValCurs//Valute[@ID='R01239']/Value";
    
    @Autowired
    private CbrClient client;

    @Override
    public String getUSDExchangeRate() throws Exception {
        return extractCurrencyValueFromXML(getXML(), USD_XPATH);
    }

    @Override
    public String getEURExchangeRate() throws Exception {
        return extractCurrencyValueFromXML(getXML(), EUR_XPATH);
    }
    private String getXML(){
        Optional<String> xmlOptional = client.getCurrencyRatesXML();
        return xmlOptional.get();
    }
    private static String extractCurrencyValueFromXML(String xml, String xpathExpression){
        InputSource source = new InputSource(new StringReader(xml));
        XPath xpath = XPathFactory.newInstance().newXPath();
        log.info("xpath = " + xpath);
        try {
            Document document = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
            log.info("document = " + document);
            return xpath.evaluate(xpathExpression, document);
        } catch (XPathExpressionException ex) {
            log.error(ExchangeRatesServiceImpl.class.getName() + ": error parsing XML " + ex.getMessage());
        }
        return "";
    }    
}
