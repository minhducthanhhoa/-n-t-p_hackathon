package com.data.on_tap_hackathon.repository;
import java.util.List;
import com.data.on_tap_hackathon.model.Product;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(Product product);
    void delete(int id);
    List<Product> searchByName(String keyword);
}
