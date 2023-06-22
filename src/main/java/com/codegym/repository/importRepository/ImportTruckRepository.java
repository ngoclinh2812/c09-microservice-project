package com.codegym.repository.importRepository;

import com.codegym.entity.importEntity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportTruckRepository extends JpaRepository<Truck, Long> {
}
