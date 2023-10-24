package com.bintics.products.repository;

import com.bintics.products.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductModel, String> {
}
