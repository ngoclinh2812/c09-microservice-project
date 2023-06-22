package com.codegym.model.exportModel.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportOrderConfirmModel implements Serializable {
    private Long orderId;
//    private String deliveryDate;
    private String status;
    private Double totalPrice;
}
