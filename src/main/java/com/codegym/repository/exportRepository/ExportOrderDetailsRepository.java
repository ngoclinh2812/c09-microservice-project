package com.codegym.repository.exportRepository;

import com.codegym.entity.exportEntity.ExportOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportOrderDetailsRepository extends JpaRepository<ExportOrderDetails, Long> {
}
