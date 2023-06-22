package com.codegym.service.exportService;

import com.codegym.entity.exportEntity.ExportOrderDetails;

import java.util.List;

public interface ExportOrderDetailsService {
    void saveAll(List<ExportOrderDetails> entityList);
}
