package com.currency.currencyexchangeservice;

import com.currency.currencyexchangeservice.bean.ExchangeValue;
import com.currency.currencyexchangeservice.jpa.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retriveExchageValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exceptionHandler = repository.findOne(
                Example.of(new ExchangeValue(null, from, to, null))).get();

        exceptionHandler.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));

        return exceptionHandler;
    }
}
