package com.currency.currencyconvertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.currency.currencyconvertservice")
@EnableDiscoveryClient
public class CurrencyConvertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConvertServiceApplication.class, args);
	}

}
