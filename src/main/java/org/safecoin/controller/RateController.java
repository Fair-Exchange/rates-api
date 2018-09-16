package org.safecoin.controller;

import org.safecoin.dto.Rate;
import org.safecoin.exception.CoinNotFoundException;
import org.safecoin.service.RatesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RateController {
    @Autowired
    RatesService ratesService;

    @RequestMapping("/rates/{symbol}")
    public List<Rate> getRates(@PathVariable("symbol") String symbol){
        List<Rate> l = ratesService.getRates(symbol.toUpperCase());
        if (l == null){
            throw new CoinNotFoundException(symbol);
        }
        return l;
    }
}
