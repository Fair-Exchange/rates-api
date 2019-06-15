package org.safecoin.service;

import org.safecoin.config.Coins;
import org.safecoin.dto.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RatesService {
    @Autowired
    RestClientService restClientService;

    private Map<String, List<Rate>> rates = new HashMap<>();

    private Long lastUpdated;

    public List<Rate> getRates(String coin){
        if (lastUpdated == null || (System.currentTimeMillis() - lastUpdated) > 1200000){
            refreshRates();
        }
        return rates.get(coin);
    }

    private void refreshRates(){
        System.out.println("Refreshing data...");
        Map<String, BigDecimal> btcRates = restClientService.getCoinLibBtcRates();
        btcRates.putAll(restClientService.getSafeTradeBtcRates());
        Rate[] bitpayRates = restClientService.getBitpayData();
        rates.put("BTC", Arrays.asList(bitpayRates));
        for (String key : btcRates.keySet()){
            List<Rate> currencyRates = new ArrayList<>();
            Rate ownRate = new Rate.RateBuilder()
                    .withCode(key)
                    .withName(Coins.valueOf(key).getName())
                    .atRate(new BigDecimal(1))
                    .build();
            currencyRates.add(ownRate);
            BigDecimal btcRate = btcRates.get(key);
            for (Rate r : bitpayRates){
                Rate rate = new Rate.RateBuilder()
                        .withCode(r.getCode())
                        .withName(r.getName())
                        .atRate(r.getRate().multiply(btcRate))
                        .build();
                currencyRates.add(rate);
            }
            rates.put(key, currencyRates);
        }
        lastUpdated = System.currentTimeMillis();
    }
}
