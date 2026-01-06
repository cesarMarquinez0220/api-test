package com.example.Prueba_SQL_CRUD.Service;

import com.example.Prueba_SQL_CRUD.Dto.PurchaseItemDto;
import com.example.Prueba_SQL_CRUD.Dto.PurchaseRequestDto;
import com.example.Prueba_SQL_CRUD.Models.OrderItemModel;
import com.example.Prueba_SQL_CRUD.Models.OrderModel;
import com.example.Prueba_SQL_CRUD.Models.ProductModel;
import com.example.Prueba_SQL_CRUD.Models.UserModel;
import com.example.Prueba_SQL_CRUD.Repository.OrderItemRepository;
import com.example.Prueba_SQL_CRUD.Repository.OrderRepository;
import com.example.Prueba_SQL_CRUD.Repository.ProductRepository;
import com.example.Prueba_SQL_CRUD.Repository.UserRepository;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(ProductRepository productRepo, UserRepository userRepo,
                        OrderItemRepository orderItemRepo, OrderRepository orderRepo) {
        this.productRepository = productRepo;
        this.userRepository = userRepo;
        this.orderItemRepository = orderItemRepo;
        this.orderRepository = orderRepo;
    }

    @Transactional
    public OrderModel createOrder(PurchaseRequestDto request){




        UserModel user= userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        OrderModel order = new OrderModel();
        order.setDate(LocalDateTime.now());
        order.setState(OrderModel.STATE.PENDING);
        order.setUser(user);

        order = orderRepository.save(order);

        for (PurchaseItemDto itemDto : request.getItems()){
            ProductModel product= productRepository.findById(itemDto.getProductId()).
                    orElseThrow(()->new RuntimeException("Producto no encontrado"+itemDto.getProductId()));

            if (product.getStock() < itemDto.getCantidad()){
                throw new RuntimeException("Stock insuficiente");
            }

            int nuevoStock = product.getStock() - itemDto.getCantidad();
            product.setStock(nuevoStock);
            productRepository.save(product);

            OrderItemModel orderItem = new OrderItemModel();

            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setCantidad(itemDto.getCantidad());
            orderItem.setPrecio(product.getPrice());

            orderItemRepository.save(orderItem);


        }
        return order;
    }

}
