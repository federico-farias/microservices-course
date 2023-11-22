package com.bintics.orders.gateway;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ProductsErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        // TODO: implementar lógica según necesidades.
        return exception;
    }
}
