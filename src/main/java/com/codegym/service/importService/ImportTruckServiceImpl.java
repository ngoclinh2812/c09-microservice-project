package com.codegym.service.importService;

import com.codegym.entity.importEntity.Truck;
import com.codegym.repository.importRepository.ImportTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportTruckServiceImpl implements ImportTruckService {
    @Autowired
    private ImportTruckRepository importTruckRepository;

    @Override
    public void save(Truck truck) {
        importTruckRepository.save(truck);
    }

    @Override
    public void saveAll(List<Truck> truckList) {
        importTruckRepository.saveAll(truckList);
    }
}
