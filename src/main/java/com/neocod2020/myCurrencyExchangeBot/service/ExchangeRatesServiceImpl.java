package com.neocod2020.myCurrencyExchangeBot.service;

import com.neocod2020.myCurrencyExchangeBot.client.CbrClient;
import java.io.StringReader;
import java.util.Optional;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Slf4j
@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    public static final String USD_XPATH = "/ValCurs/Valute[@ID='R01235']/Value";
    private static final String EUR_XPATH = "/ValCurs/Valute[@ID='R01239']/Value";

    private CbrClient client;

    @Autowired
    public ExchangeRatesServiceImpl(CbrClient client) {
        this.client = client;
    }

    @Override
    @Cacheable(value = "usd", unless = "#result == null or #result.isEmpty()")
    public String getUSDExchangeRate() throws Exception {
        log.info("getUSDExchangeRate");
        return extractCurrencyValueFromXML(getXML(), USD_XPATH);
    }

    @Override
    @Cacheable(value = "eur", unless = "#result == null or #result.isEmpty()")
    public String getEURExchangeRate() throws Exception {
        log.info("getEURExchangeRate");
        return extractCurrencyValueFromXML(getXML(), EUR_XPATH);
    }

    private String getXML() {
        Optional<String> xmlOptional = client.getCurrencyRatesXML();        
        String xml = xmlOptional.orElseThrow(() -> new RuntimeException("Exception extracting string xml"));        
        return xml;
    }

    private static String extractCurrencyValueFromXML(String xml, String xpathExpression) {
        log.info("extractCurrencyValueFromXML");
        InputSource source = new InputSource(new StringReader(xml));
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            Document document = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
            return xpath.evaluate(xpathExpression, document);
        } catch (XPathExpressionException ex) {
            log.error(ExchangeRatesServiceImpl.class.getName() + ": error parsing XML " + ex.getMessage());
        }
        return "";
    }
//      the same method created "document" via DocumentBuilder
//    private static String extractCurrencyValueFromXML(String xml, String xpathExpression) {
//        try {
//            InputSource source = new InputSource(new StringReader(xml));
//            XPath xpath = XPathFactory.newInstance().newXPath();
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//
//            Document document = builder.parse(source);
//            return xpath.evaluate(xpathExpression, document);
//
//        } catch (XPathExpressionException ex) {
//            log.error(XPathExpressionException.class.getName() + ": error parsing XML " + ex.getMessage());
//        } catch (ParserConfigurationException ex) {
//            log.error(ParserConfigurationException.class.getName() + ": error parsing XML " + ex.getMessage());
//        } catch (SAXException ex) {
//            log.error(SAXException.class.getName() + ": error parsing XML " + ex.getMessage());
//        } catch (IOException ex) {
//            log.error(IOException.class.getName() + ": error parsing XML " + ex.getMessage());
//        }
//        return null;
//    }

    @Override
    @CacheEvict("usd")
    public void clearUSDcache() {
        log.info("Cache \"usd\" cleared!");
    }

    @Override
    @CacheEvict("eur")
    public void clearEURcache() {
        log.info("Cache \"eur\" cleared!");
    }

}
