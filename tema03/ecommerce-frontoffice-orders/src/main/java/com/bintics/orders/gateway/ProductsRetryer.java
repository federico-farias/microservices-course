package com.bintics.orders.gateway;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsRetryer implements Retryer {

    private final int WAIT_TIME = 1000;
    private final int MAX_NUM_ATTEMPS = 5;
    private int count = 0;

    @Override
    public void continueOrPropagate(RetryableException e) {
        try {
            this.count++;
            Thread.sleep(WAIT_TIME * count); // Exponential back-off
            if (this.count == MAX_NUM_ATTEMPS) {
                throw new ProductsGatewayException("the maximum number of retries has been reached");
            }
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
