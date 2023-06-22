package com.codegym.model.importModel.request;

import com.codegym.model.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportOrderDetailModel {
    private Long order_id;
    private ProductModel productModel;
    private Long quantity;
}
