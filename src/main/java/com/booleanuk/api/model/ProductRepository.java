package com.booleanuk.api.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private int id = 1;
    private final List<Product> data = new ArrayList<>();

    public void create(String name, String category, int price) {
        this.data.add(new Product(
                this.id++,
                name,
                category,
                price
        ));
    }

    public List<Product> findAll() { return this.data; }

    public Product findById(int id) {
        return this.data.stream().filter(
                    prod -> prod.getId() == id
                )
                .findFirst().orElseThrow();
    }

    public void updateProduct(Product updated) {
        this.data.replaceAll(prod -> prod.getId() == updated.getId() ? updated : prod);
    }

    public Boolean deleteProduct(int id) {
        return this.data.removeIf(product -> product.getId() == id);
    }
}
