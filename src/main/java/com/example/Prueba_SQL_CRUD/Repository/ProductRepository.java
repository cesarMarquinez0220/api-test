package com.example.Prueba_SQL_CRUD.Repository;

import com.example.Prueba_SQL_CRUD.Models.ProductModel;
import com.example.Prueba_SQL_CRUD.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel,Long> {

}
