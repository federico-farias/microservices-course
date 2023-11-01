package com.bintics.products.controller;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import com.bintics.products.dto.ProductRequest;
import com.bintics.products.dto.ProductResponse;
import com.bintics.products.service.ProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService service;

    @PostMapping
    @CaptureSpan
    @CaptureTransaction
    public ResponseEntity create(@RequestBody ProductRequest request) {
        log.info("create-product");
        var id = this.service.create(request);
        return ResponseEntity.created(URI.create(String.format("/products/%s", id))).build();
    }

    @GetMapping
    @CaptureSpan
    @CaptureTransaction
    public ResponseEntity getAll() {
        log.info("get-all-product");
        var list = this.service.getAll();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
    @CaptureSpan
    @CaptureTransaction
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody ProductRequest request) {
        request.setId(id);
        this.service.update(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CaptureSpan
    @CaptureTransaction
    public ResponseEntity get(@PathVariable("id") String id) {
        ProductResponse product = this.service.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
