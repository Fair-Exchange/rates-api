package org.safecoin.controller;

import org.safecoin.dto.Rate;
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

    @RequestMapping("/{symbol}")
    public List<Rate> getRates(@PathVariable("symbol") String symbol){
        return ratesService.getRates(symbol.toUpperCase());
    }
}
