package view;

import controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    ProductController productController ;


    public LoginScreen() {

        setTitle("Inventory Management - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(45, 52, 54)); // Dark theme
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(99, 110, 114));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        gbc.gridy = 4;
        panel.add(messageLabel, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        add(panel);
        setVisible(true);
        this.productController=new ProductController();
    }

    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Field Validations
        if (username.isEmpty()) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Username cannot be empty.");
            return;
        }
        if (username.length() < 3) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Username must be at least 3 characters.");
            return;
        }
        if (password.isEmpty()) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Password cannot be empty.");
            return;
        }
        if (password.length() < 6) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Password must be at least 6 characters.");
            return;
        }

        // Hardcoded authentication (Replace with database check later)
        if (username.equals("admin") && password.equals("password")) {
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Login Successful!");
            JOptionPane.showMessageDialog(this, "Welcome " + username + "!", "Login", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close login window
           new MainDashboard(productController);
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid username or password.");
        }
    }
}
