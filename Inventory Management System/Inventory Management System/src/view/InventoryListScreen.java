package view;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InventoryListScreen extends JFrame {
    private JTable productTable;
    private ProductController productController;

    public InventoryListScreen(ProductController controller) {
        this.productController = controller;
        
        setTitle("Inventory List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table Model (Non-Editable)
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Product ID", "Name", "Category", "Price", "Quantity"});

        // JTable
        productTable = new JTable(tableModel);
        productTable.setEnabled(false); // Makes table non-editable
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Load Product Data
        loadProductData(tableModel);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadProductData(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // Clear table before loading

        List<Product> products = productController.getProducts();
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getQuantity()
            });
        }
    }


}
