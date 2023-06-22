package com.codegym.service.exportService;

import com.codegym.entity.exportEntity.ExportOrder;
import com.codegym.repository.exportRepository.ExportOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExportOrderServiceImpl implements ExportOrderService{

    @Autowired
    private ExportOrderRepository exportOrderRepository;

    @Override
    public void save(ExportOrder order) {
        exportOrderRepository.save(order);
    }

    @Override
    public ExportOrder findById(Long orderId) {
        Optional<ExportOrder> exportOrder =  exportOrderRepository.findById(orderId);
        if (exportOrder.isPresent()) {
            return exportOrder.get();
        }
        return null;
    }


}
