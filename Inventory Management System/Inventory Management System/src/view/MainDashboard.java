package view;

import controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard extends JFrame {

    private ProductController productController;
    public MainDashboard(ProductController productController) {
        this.productController = productController;
        setTitle("Inventory Management - Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Layout
        setLayout(new BorderLayout());

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(7, 1, 10, 10));
        sidebar.setBackground(new Color(45, 52, 54));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // Sidebar Buttons
        JButton btnAdd = createSidebarButton("Add Item");
        JButton btnDelete = createSidebarButton("Delete Item");
        JButton btnEdit = createSidebarButton("Edit Item");
        JButton btnDisplay = createSidebarButton("Display Items");
        JButton btnSearch = createSidebarButton("Search Item");
        JButton btnLogout = createSidebarButton("Logout");

        // Add buttons to sidebar
        sidebar.add(btnAdd);
        sidebar.add(btnDelete);
        sidebar.add(btnEdit);
        sidebar.add(btnDisplay);
        sidebar.add(btnSearch);
        sidebar.add(btnLogout);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Inventory Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(welcomeLabel, BorderLayout.CENTER);


        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddItemScreen(productController);
            }
        });
        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryListScreen(productController);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteItemScreen(productController);
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditItemScreen(productController);
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchProductScreen(productController);
            }
        });

        // Logout functionality
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginScreen(); // Go back to login screen
                }
            }
        });


        // Add panels to frame
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(99, 110, 114));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        new MainDashboard(new ProductController());
    }
}
