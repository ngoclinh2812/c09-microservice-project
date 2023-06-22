package com.codegym.dto.exportDto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportOrderConfirmDto implements Serializable {
    private Long id;
    private String status;
}
