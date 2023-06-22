package com.codegym.service.importService;

import com.codegym.entity.importEntity.Truck;

import java.util.List;

public interface ImportTruckService {
    void save(Truck truck);
    void saveAll(List<Truck> truckList);
}
