package com.bintics.orders.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class ProductsGatewayFallback implements ProductsGateway {

    @Override
    public List<ProductGatewayReponse> getAllProducts() {
        log.info("getAllProducts no available");
        return new LinkedList<>();
    }

    @Override
    public ProductGatewayReponse findById(String productsId) {
        log.info("findById no available");
        return null;
    }
}
