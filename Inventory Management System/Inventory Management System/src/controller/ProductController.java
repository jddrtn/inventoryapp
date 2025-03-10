package controller;

import database.DBConnection;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private List<Product> productList;

    public ProductController() {
        productList = new ArrayList<>();
        getProductsFromDatabase();
    }

    public List<Product> getProducts() {
        return productList;
    }

    public boolean addProduct(long id, String name, String category, double price, int quantity, JLabel messageLabel) {
        if (name.isEmpty() || category.isEmpty() || price < 0 || quantity < 0) {
            return false; // Validation failed
        }

        Product newProduct = new Product(id, name, category, price, quantity);

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                messageLabel.setText("Database connection failed!");
                return false;
            }

            String sql = "INSERT INTO products (id,name, category, price, quantity) VALUES (?,?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
               stmt.setLong(1, id);
                stmt.setString(2, name);
                stmt.setString(3, category);
                stmt.setDouble(4, price);
                stmt.setInt(5, quantity);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    messageLabel.setForeground(Color.GREEN);
                    messageLabel.setText("Product added successfully!");
                    productList.add(newProduct);

                } else {
                    messageLabel.setText("Failed to add product.");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteProductByName(String name) {
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                productList.remove(product);

                String sql = "DELETE FROM products WHERE name = ?";

                try (Connection conn = DBConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, name);
                    int rowsAffected = stmt.executeUpdate();
                    return rowsAffected > 0;  // Returns true if deletion was successful
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }

            }
        }
        return false;
    }

    private void getProductsFromDatabase() {
        this.productList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateProduct(long productId, String name, String category, double price, int quantity) {



        String sql = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setLong(5, productId);


            int rowsAffected = stmt.executeUpdate(); // Execute update once

            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully!");

                return true;
            } else {
                System.out.println("No product found with ID: " + productId);
                return false;
            }

        } catch (SQLException e) {
           new RuntimeException(e);
        }
        return false;
    }

    // Search Products by Name
    public List<Product> searchProductsByName(String name) {
        return searchProducts("name", name);
    }

    // Search Products by Category
    public List<Product> searchProductsByCategory(String category) {
        return searchProducts("category", category);
    }

    // Generalized search method
    private List<Product> searchProducts(String column, String value) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE " + column + " LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + value + "%");  // Allow partial matches
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productList.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
