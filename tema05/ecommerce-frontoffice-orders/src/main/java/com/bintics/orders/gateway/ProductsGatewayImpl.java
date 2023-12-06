package com.bintics.orders.gateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ProductsGatewayImpl implements ProductsGateway {

    private final ProductsFeignClient client;


    @Override
    public List<ProductGatewayReponse> getAllProducts() {
        return this.client.getAllProducts();
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "findById")
    public ProductGatewayReponse findById(String productsId) {
        return client.findById(productsId);
    }

    public ProductGatewayReponse findById(String productsId, Exception ex) {
        log.info("fallback: " + productsId + ", exeption: " + ex.getMessage());
        return null;
    }

}
