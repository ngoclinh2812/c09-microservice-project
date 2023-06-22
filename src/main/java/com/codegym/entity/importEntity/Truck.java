package com.codegym.entity.importEntity;

import com.codegym.model.importModel.request.ImportOrderModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "import_truck")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ImportOrder order;

    private String licensePlates;

    private String name;

    private Long weight;
}
