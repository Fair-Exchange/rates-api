package org.safecoin.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.safecoin.dto.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class RestClientService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpEntity httpEntity;

    public BigDecimal getSafeBtcRate(){
        String jsonResponse = restTemplate.exchange("https://safe.trade/api/v2/tickers/safebtc", HttpMethod.GET, httpEntity, Object.class).getBody().toString();
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonResponse).getAsJsonObject();
        JsonObject tickerObject = rootObject.getAsJsonObject("ticker");
        return tickerObject.get("last").getAsBigDecimal();
    }

    public Rate[] getBitpayData(){
        return restTemplate.getForObject("https://bitpay.com/api/rates", Rate[].class);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpEntity<String> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return entity;
    }
}
