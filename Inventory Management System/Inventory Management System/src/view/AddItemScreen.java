package view;

import controller.ProductController;
import database.DBConnection;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddItemScreen extends JFrame {
    private JTextField nameField, categoryField, priceField, quantityField;
    private JLabel messageLabel;
   private ProductController productController;

    public AddItemScreen(ProductController productController) {
        this.productController = productController;
        setTitle("Add New Product");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and Fields
        JLabel titleLabel = new JLabel("Add New Product", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel nameLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        JLabel categoryLabel = new JLabel("Category:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(categoryLabel, gbc);

        categoryField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(categoryField, gbc);

        JLabel priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(priceLabel, gbc);

        priceField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(priceField, gbc);

        JLabel quantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(quantityLabel, gbc);

        quantityField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(quantityField, gbc);

        // Message Label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);

        // Buttons
        JButton addButton = new JButton("Add Product");
        addButton.setBackground(new Color(99, 110, 114));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 6;
        panel.add(addButton, gbc);

        // Button Action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void addProduct() {
        try {
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            // Basic validation
            if (name.isEmpty() || category.isEmpty()) {
                messageLabel.setText("Name and Category cannot be empty.");
                return;
            }
            if (price < 0 || quantity < 0) {
                messageLabel.setText("Price and Quantity cannot be negative.");
                return;
            }


            if(productController.addProduct(System.currentTimeMillis(),name,category,price,quantity,messageLabel))
                resetFields();

        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid input. Enter correct values.");
        }
    }

    private void resetFields() {
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }



}
