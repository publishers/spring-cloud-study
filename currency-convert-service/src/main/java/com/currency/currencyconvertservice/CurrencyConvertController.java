package com.currency.currencyconvertservice;

import com.currency.currencyconvertservice.bean.CurrencyConversionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConvertController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversion(@PathVariable String from,
                                                     @PathVariable String to,
                                                     @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversionBean.class,
                        uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();

        return new CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(),
                response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionFeign(@PathVariable String from,
                                                     @PathVariable String to,
                                                     @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        return new CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(),
                response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
