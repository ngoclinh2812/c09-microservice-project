package com.codegym.dto.exportDto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportOrderRequestDto implements Serializable {
    private Long orderId;
    private List<ExportOrderDetailRequestDto> productItemListDto;
}
