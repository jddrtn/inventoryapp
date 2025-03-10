package view;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditItemScreen extends JFrame {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JButton saveButton;
    private ProductController productController;

    public EditItemScreen(ProductController controller) {
        this.productController = controller;

        setTitle("Edit Products");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table Model
        String[] columnNames = {"ID", "Name", "Category", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID column should not be editable
            }
        };
        productTable = new JTable(tableModel);
        loadProductDetails();
        add(new JScrollPane(productTable), BorderLayout.CENTER);

        // Save Button
        saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(46, 204, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadProductDetails() {
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

    private void saveChanges() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            long id = (long) tableModel.getValueAt(i, 0);
            String name = (String) tableModel.getValueAt(i, 1);
            String category = (String) tableModel.getValueAt(i, 2);
            double price = Double.parseDouble(tableModel.getValueAt(i, 3).toString());
            int quantity = Integer.parseInt(tableModel.getValueAt(i, 4).toString());

            productController.updateProduct(id, name, category, price, quantity);
        }
        JOptionPane.showMessageDialog(this, "Products updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
