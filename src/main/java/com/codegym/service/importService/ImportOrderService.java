package com.codegym.service.importService;

import com.codegym.entity.importEntity.ImportOrder;

public interface ImportOrderService {
    void save(ImportOrder entity);
    ImportOrder findById(Long id);
}
