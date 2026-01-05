package com.example.Prueba_SQL_CRUD.Service;

import com.example.Prueba_SQL_CRUD.Models.ProductModel;
import com.example.Prueba_SQL_CRUD.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<ProductModel> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<ProductModel> getProductById(Long id){
        return productRepository.findById(id);
    }

    //crear
    public ProductModel createProduct(ProductModel productModel){
        if (productModel.getPrice() == null || productModel.getPrice().compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (productModel.getStock() == null || productModel.getStock() <0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        return  productRepository.save(productModel);
    }

    //actualizar
    public ProductModel updateProduct(Long id, ProductModel productDetails){

        return productRepository.findById(id).map(existingProduct ->{
            existingProduct.setName(productDetails.getName());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setStock(productDetails.getStock());

            return  productRepository.save(existingProduct);
        }).orElseThrow(()-> new RuntimeException("Producto no encontrado con id: "+ id));

    }

    //eliminar
    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("no se puede eliminar el producto, no existe");
        }
        productRepository.deleteById(id);
    }


}
