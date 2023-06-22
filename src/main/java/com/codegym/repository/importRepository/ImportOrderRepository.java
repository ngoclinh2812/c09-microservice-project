package com.codegym.repository.importRepository;

import com.codegym.entity.importEntity.ImportOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportOrderRepository extends JpaRepository<ImportOrder, Long> {

//    @Query("select * from import_order where codeName = ?")
//    Optional<Order> findByOrderCode(String string);
}
