package com.codegym.dto.importDto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderConfirmResponseDto implements Serializable {
    private Long id;

    private Long totalPrice;

    private Date delivery;

    private List<TruckDto> transportationModels;

    public OrderConfirmResponseDto() {
    }

}
