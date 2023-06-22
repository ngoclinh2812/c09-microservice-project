package com.codegym.service.exportService;

import com.codegym.entity.exportEntity.ExportOrderDetails;
import com.codegym.repository.exportRepository.ExportOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportOrderDetailsServiceImpl implements ExportOrderDetailsService{
    @Autowired
    private ExportOrderDetailsRepository exportOrderDetailsRepository;

    @Override
    public void saveAll(List<ExportOrderDetails> entityList) {
        exportOrderDetailsRepository.saveAll(entityList);
    }
}
