package com.codegym.model.importModel.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportOrderModel {
    private Long id;
    List<ImportOrderDetailModel> detailModelList;
}
