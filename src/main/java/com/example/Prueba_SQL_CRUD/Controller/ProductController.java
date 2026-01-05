package com.example.Prueba_SQL_CRUD.Controller;

import com.example.Prueba_SQL_CRUD.Models.ProductModel;
import com.example.Prueba_SQL_CRUD.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id){
        Optional<ProductModel> productModel = productService.getProductById(id);

        return  productModel.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductModel productModel){
        try {
            ProductModel newProduct = productService.createProduct(productModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        }catch (RuntimeException ERROR){
            return ResponseEntity.badRequest().body(ERROR.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductModel productDetails){
        try {
            ProductModel updatedProduct = productService.updateProduct(id,productDetails);
            return ResponseEntity.ok(updatedProduct);
        }catch (RuntimeException ERROR){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException ERROR){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ERROR.getMessage());
        }
    }

}
