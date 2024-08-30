package com.booleanuk.api.model;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int id = 1;
    private List<Product> data = new ArrayList<>();

    public void create(String name, String category, int price) {
        this.data.add(new Product(
                this.id++,
                name,
                category,
                price
        ));
    }
}
