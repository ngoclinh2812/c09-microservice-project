package com.codegym.service.importService;

import com.codegym.entity.importEntity.ImportOrder;
import com.codegym.repository.importRepository.ImportOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ImportOrderServiceImpl implements ImportOrderService {

    @Autowired
    private ImportOrderRepository importOrderRepository;

    @Override
    public void save(ImportOrder entity) {
        importOrderRepository.save(entity);
    }

    public ImportOrder findById(Long id) {
        Optional<ImportOrder> importOrderOptional = importOrderRepository.findById(id);
        if (importOrderOptional.isPresent()) {
            return importOrderOptional.get();
        }
        return null;
    }



    // Lưu order nhập kho vào database
//    public void save(ImportOrder entity) {
//        importOrderRepository.save(entity);
//    }

}
