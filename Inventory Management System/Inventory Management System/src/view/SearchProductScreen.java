package view;

import controller.ProductController;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchProductScreen extends JFrame {
    private JTextField searchField;
    private JRadioButton nameRadioButton, categoryRadioButton;
    private JButton searchButton;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ProductController productController;

    public SearchProductScreen(ProductController controller) {
        this.productController = controller;

        setTitle("Search Product");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel for Search Criteria
        JPanel searchPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));

        // Search Field
        searchPanel.add(new JLabel("Enter Search:"));
        searchField = new JTextField();
        searchPanel.add(searchField);

        // Radio Buttons for Search Type
        nameRadioButton = new JRadioButton("Search by Name");
        categoryRadioButton = new JRadioButton("Search by Category");

        ButtonGroup group = new ButtonGroup();
        group.add(nameRadioButton);
        group.add(categoryRadioButton);

        searchPanel.add(nameRadioButton);
        searchPanel.add(categoryRadioButton);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(52, 152, 219));
        searchButton.setForeground(Color.WHITE);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);

        // Table Model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Product ID", "Name", "Category", "Price", "Quantity"});

        // JTable
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Search Button Action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProducts();
            }
        });

        setVisible(true);
    }

    private void searchProducts() {
        String searchText = searchField.getText().trim();

        // Validate that a search type is selected
        if (!nameRadioButton.isSelected() && !categoryRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a search type (Name or Category).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate that the search text is not empty
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Product> products;
        if (nameRadioButton.isSelected()) {
            products = productController.searchProductsByName(searchText);
        } else {
            products = productController.searchProductsByCategory(searchText);
        }

        tableModel.setRowCount(0); // Clear previous results

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No products found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
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


}
