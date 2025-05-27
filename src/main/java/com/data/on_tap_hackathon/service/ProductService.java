package com.data.on_tap_hackathon.service;

import com.data.on_tap_hackathon.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(int id);
    void save(Product product);
    void update(Product product);
    void delete(int id);
    List<Product> search(String keyword);
}
