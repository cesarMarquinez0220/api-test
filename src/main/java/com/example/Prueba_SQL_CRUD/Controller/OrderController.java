package com.example.Prueba_SQL_CRUD.Controller;

import com.example.Prueba_SQL_CRUD.Dto.PurchaseRequestDto;
import com.example.Prueba_SQL_CRUD.Models.OrderModel;
import com.example.Prueba_SQL_CRUD.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody PurchaseRequestDto request){
        try {
            OrderModel newOrder = orderService.createOrder(request);
            return ResponseEntity.ok(newOrder);

        }catch (RuntimeException ERROR){
            return ResponseEntity.badRequest().body(ERROR.getMessage());
        }
        catch (Exception ERROR){
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }



}
