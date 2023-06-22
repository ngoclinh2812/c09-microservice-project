package com.codegym.repository.exportRepository;

import com.codegym.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportProductRepository extends JpaRepository<Product, Long> {
}
