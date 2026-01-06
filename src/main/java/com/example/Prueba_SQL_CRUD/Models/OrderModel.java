package com.example.Prueba_SQL_CRUD.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Orders")
@AllArgsConstructor
public class OrderModel {

    public enum STATE {
        PENDING,
        COMPLETED,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private STATE state;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

}
