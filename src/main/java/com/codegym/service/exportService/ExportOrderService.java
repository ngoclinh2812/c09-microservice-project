package com.codegym.service.exportService;

import com.codegym.dto.exportDto.request.ExportOrderRequestDto;
import com.codegym.entity.exportEntity.ExportOrder;

public interface ExportOrderService {
    void save(ExportOrder order);

    ExportOrder findById(Long orderId);
}
