package com.codegym.entity.importEntity;

import com.codegym.entity.Product;
import lombok.Data;

import javax.persistence.*;

// (1)
@Data
@Entity
@Table(name = "import_order_details")
public class ImportOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private ImportOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Long quantity;
}
