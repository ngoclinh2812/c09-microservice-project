package com.codegym.dto.importDto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TruckDto {

    private String licensePlates;

    private String name;

    private Long weight;
}
