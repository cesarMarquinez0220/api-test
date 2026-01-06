package com.example.Prueba_SQL_CRUD.Repository;

import com.example.Prueba_SQL_CRUD.Models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {


}
