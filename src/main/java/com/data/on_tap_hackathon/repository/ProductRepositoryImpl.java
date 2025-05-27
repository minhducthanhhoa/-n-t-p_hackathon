package com.data.on_tap_hackathon.repository;

import com.data.on_tap_hackathon.connection.ConnectionDB;
import com.data.on_tap_hackathon.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Product p) {
        String sql = "INSERT INTO product(product_name, price, description, image, status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getPrice());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getImage());
            ps.setString(5, p.getStatus());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product p) {
        String sql = "UPDATE product SET product_name=?, price=?, description=?, image=?, status=? WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getPrice());
            ps.setString(3, p.getDescription());
            ps.setString(4, p.getImage());
            ps.setString(5, p.getStatus());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id=?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_name LIKE ?";
        try (Connection conn = ConnectionDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Product mapResultSet(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setProductName(rs.getString("product_name"));
        p.setPrice(rs.getInt("price"));
        p.setDescription(rs.getString("description"));
        p.setImage(rs.getString("image"));
        p.setStatus(rs.getString("status"));
        p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return p;
    }
}
