package com.ShopMinds.service.seller;

import com.ShopMinds.dto.ProductDto;
import com.ShopMinds.model.Product;
import com.ShopMinds.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(product.getCreatedAt());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    public Product updateProduct(int id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id : " + id));
        if(productDto.getName() != null) {
            existingProduct.setName(productDto.getName());
        }
        if(productDto.getPrice() != null) {
            existingProduct.setPrice(productDto.getPrice());
        }
//        if(productDto.getCategory() != null) {
//            existingProduct.setCategory(productDto.getCategory());
//        }
        if(productDto.getDescription() != null) {
            existingProduct.setDescription(productDto.getDescription());
        }
        if(productDto.getQuantity() > 0){
            existingProduct.setQuantity(productDto.getQuantity());
        }

        return productRepository.save(existingProduct);
    }

    public ResponseEntity<?> deleteProduct(int id) {
        try{
            if(!productRepository.existsById(id)){
                return new ResponseEntity<>("Product not found with id : " + id, HttpStatus.NOT_FOUND);
            }
            productRepository.deleteById(id);;
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
