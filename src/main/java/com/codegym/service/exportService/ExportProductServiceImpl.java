package com.codegym.service.exportService;

import com.codegym.entity.Product;
import com.codegym.repository.exportRepository.ExportProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExportProductServiceImpl implements ExportProductService{

    @Autowired
    private ExportProductRepository exportProductRepository;

    @Override
    public Optional<Product> findProductById(Long productId) {
        Optional<Product> product = exportProductRepository.findById(productId);
        if (product.isPresent()) {
            return product;
        }
        return null;
    }
}
