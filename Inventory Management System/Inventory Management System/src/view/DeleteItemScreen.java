package view;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteItemScreen extends JFrame {
    private JComboBox<String> productDropdown;
    private ProductController productController;

    public DeleteItemScreen(ProductController controller) {
        this.productController = controller;

        setTitle("Delete Product");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Delete Product", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        productDropdown = new JComboBox<>();
        updateProductDropdown(); // Load product names
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(productDropdown, gbc);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        gbc.gridx = 1;
        add(deleteButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        setVisible(true);
    }

    private void updateProductDropdown() {
        productDropdown.removeAllItems();
        List<Product> products = productController.getProducts();
        for (Product product : products) {
            productDropdown.addItem(product.getName());
        }
    }

    private void deleteProduct() {
        String selectedProduct = (String) productDropdown.getSelectedItem();
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(this, "No product selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete " + selectedProduct + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            boolean success = productController.deleteProductByName(selectedProduct);
            if (success) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateProductDropdown(); // Refresh dropdown
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting product.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
