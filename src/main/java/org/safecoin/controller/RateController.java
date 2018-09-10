package org.safecoin.controller;

import org.safecoin.dto.Rate;
import org.safecoin.service.RestClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class RateController {

    @Autowired
    RestClientService restClientService;

    @RequestMapping("/rates")
    public Rate[] getRates(){
        BigDecimal safeBtcRate = restClientService.getSafeBtcRate();
        Rate[] bitpayRates = restClientService.getBitpayData();

        for (Rate r : bitpayRates){
            BigDecimal btcRate = r.getRate();
            r.setRate(btcRate.multiply(safeBtcRate));
        }
        return bitpayRates;
    }
}
