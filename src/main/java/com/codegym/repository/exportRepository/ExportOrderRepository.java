package com.codegym.repository.exportRepository;

import com.codegym.entity.exportEntity.ExportOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportOrderRepository extends CrudRepository<ExportOrder, Long> {
}
