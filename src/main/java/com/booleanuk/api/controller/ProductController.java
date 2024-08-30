package com.booleanuk.api.controller;

import com.booleanuk.api.model.Product;
import com.booleanuk.api.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepository) {
        this.productRepo = productRepository;

        // Dummy data.
        this.productRepo.create("PC", "Electronic", 2000);
        this.productRepo.create("PS5", "Electronic", 3500);
        this.productRepo.create("Table", "Furniture", 1500);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return this.productRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        this.productRepo.create(
                product.getName(),
                product.getCategory(),
                product.getPrice()
        );
        return product;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable int id) {
        return this.productRepo.findById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product productWithId = new Product(
                id,
                product.getName(),
                product.getCategory(),
                product.getPrice()
        );
        this.productRepo.updateProduct(productWithId);
        return productWithId;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable int id) {
        boolean success = this.productRepo.deleteProduct(id);
        if (success) {
            return "Product successfully deleted!";
        }
        return "Failed to delete product...";
    }
}
