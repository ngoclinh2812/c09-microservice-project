package com.codegym.entity.importEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@ToString
@Table(name = "import_order")
public class ImportOrder {
    @Id
    private Long id;

//    @OneToMany(mappedBy = "id")
//    private List<ImportOrderDetails> orderDetailsList;

    @OneToMany(mappedBy = "order")
    private List<Truck> truckList;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "status")
    private String status; // Trạng thái của đơn hàng
}
