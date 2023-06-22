package com.codegym.dto.importDto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportOrderRequestDto {
    private Long id;
    private String status;
    List<ImportOrderDetailRequestDto> orderDetailRequestDtos;
}
