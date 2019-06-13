package org.safecoin.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.safecoin.config.Coins;
import org.safecoin.config.RateDataSource;
import org.safecoin.dto.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestClientService {

    public static final String SAFE_TRADE_TICKER_URL = "https://safe.trade/api/v2/tickers";

    public static final String COINLIB_API_URL = "https://coinlib.io/api/v1/coin?key=%s&pref=BTC&symbol=%s";

    public static final String COINLIB_API_KEY = "a519b1c7fd9c2893";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpEntity httpEntity;

    public Map<String, BigDecimal> getCoinLibBtcRates(){
        String url = String.format(COINLIB_API_URL, COINLIB_API_KEY, getCoinLibParam());
        String jsonResponse = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        JsonObject rootObject = new JsonParser().parse(jsonResponse).getAsJsonObject();
        JsonArray jsonArray = rootObject.getAsJsonArray("coins");
        Map<String, BigDecimal> rates = new HashMap<>();
        for (JsonElement element : jsonArray){
            JsonObject jsonObject = element.getAsJsonObject();
            String coin = jsonObject.get("symbol").getAsString();
            BigDecimal btcRate = jsonObject.get("price").getAsBigDecimal();
            rates.put(coin, btcRate);
        }
        return rates;
    }

    public Map<String, BigDecimal> getSafeTradeBtcRates(){
        String jsonResponse = restTemplate.exchange(SAFE_TRADE_TICKER_URL, HttpMethod.GET, httpEntity, String.class).getBody();
        JsonObject rootObject = new JsonParser().parse(jsonResponse).getAsJsonObject();
        Map<String, BigDecimal> rates = new HashMap<>();
        for (Coins coin : Coins.values()){
            if (coin.getRateDataSource().equals(RateDataSource.SAFE_TRADE)){
                JsonObject coinObject = rootObject.getAsJsonObject(coin.toString().toLowerCase().concat("btc"));
                JsonObject tickerObject = coinObject.getAsJsonObject("ticker");
                rates.put(coin.toString(), tickerObject.get("last").getAsBigDecimal());
            }
        }
        return rates;
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
	headers.add("Access-Control-Allow-Origin", "*");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return entity;
    }

    private String getCoinLibParam(){
        StringBuilder sb = new StringBuilder();
        for (Coins coin : Coins.values()){
            if (coin.getRateDataSource().equals(RateDataSource.COINLIB)){
                sb.append(coin.toString());
                sb.append(",");
            }
        }
        int idx = sb.toString().lastIndexOf(',');
        return sb.toString().substring(0, idx);
    }
}
