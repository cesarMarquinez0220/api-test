package com.example.Prueba_SQL_CRUD.Repository;

import com.example.Prueba_SQL_CRUD.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);
}
