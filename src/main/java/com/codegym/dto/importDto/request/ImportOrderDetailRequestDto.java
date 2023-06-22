package com.codegym.dto.importDto.request;

import com.codegym.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportOrderDetailRequestDto {
//    private Long order_id;
    private String productId;
    private String quantity;

}
