package com.data.on_tap_hackathon.service;
import java.util.List;
import com.data.on_tap_hackathon.model.Product;
import com.data.on_tap_hackathon.repository.ProductRepository;
import com.data.on_tap_hackathon.repository.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository = new ProductRepositoryImpl();

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void update(Product product) {
        repository.update(product);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Product> search(String keyword) {
        return repository.searchByName(keyword);
    }
}
