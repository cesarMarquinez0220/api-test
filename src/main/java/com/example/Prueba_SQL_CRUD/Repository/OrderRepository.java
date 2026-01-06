package com.example.Prueba_SQL_CRUD.Repository;

import com.example.Prueba_SQL_CRUD.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    List<OrderModel> findByUser_Id(Long userId);
}
