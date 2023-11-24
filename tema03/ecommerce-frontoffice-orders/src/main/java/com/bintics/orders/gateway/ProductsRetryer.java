package com.bintics.orders.gateway;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsRetryer implements Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new ProductsRetryer();
    }

}
