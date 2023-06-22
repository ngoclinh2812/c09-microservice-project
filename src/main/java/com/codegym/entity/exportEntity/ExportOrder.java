package com.codegym.entity.exportEntity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "export_order")
@Data
public class ExportOrder {

    @Id
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "id")
    private List<ExportOrderDetails> orderDetailsList;

    @Column(name = "date")
    private String deliveryDate;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private Double total;

}
