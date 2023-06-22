package com.codegym.service.exportService;

import com.codegym.entity.Product;

import java.util.Optional;

public interface ExportProductService {
    Optional<Product> findProductById(Long productId);
}
