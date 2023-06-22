package com.codegym.dto.exportDto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportOrderDetailRequestDto implements Serializable {
    // order id
    private Long id;
    private String productName;
    private Integer quantity;
}