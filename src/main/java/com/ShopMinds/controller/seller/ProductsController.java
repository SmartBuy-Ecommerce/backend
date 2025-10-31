package com.ShopMinds.controller.seller;

import com.ShopMinds.dto.ProductDto;
import com.ShopMinds.model.Product;
import com.ShopMinds.service.seller.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
        try{
           Product product = productService.addProduct(productDto);
           return ResponseEntity.ok(product);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, ProductDto productDto){
        try{
            Product product = productService.updateProduct(id,productDto);
            return ResponseEntity.ok(product);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

}
