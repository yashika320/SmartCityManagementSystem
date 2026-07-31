package ui;
import dao.UserDAO;
import model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.*;

public class LoginForm extends JFrame {

    private JLabel titleLabel, emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {

        setTitle("Smart City Management System - Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        titleLabel = new JLabel("USER LOGIN");
        titleLabel.setBounds(140, 20, 200, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 80, 100, 25);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 80, 200, 25);
        add(emailField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 200, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(80, 220, 120, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(46,204,113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (emailField.getText().trim().isEmpty() ||
                        String.valueOf(passwordField.getPassword()).trim().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            LoginForm.this,
                            "Please fill all fields!"
                    );
                    return;
                }
                if (!emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                    JOptionPane.showMessageDialog(
                            LoginForm.this,
                            "Enter a valid email address!"
                    );

                    return;
                }
                if (String.valueOf(passwordField.getPassword()).length() < 6) {

                    JOptionPane.showMessageDialog(
                            LoginForm.this,
                            "Password must be at least 6 characters!"
                    );

                    return;
                }
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                UserDAO dao = new UserDAO();

                User user = dao.loginUser(email, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(null,
                            "Welcome " + user.getFullName());

                    dispose(); // Login window close


                    if (user.getRole().equalsIgnoreCase("Admin")) {

                        new AdminDashboard(user);

                    } else {

                        new Dashboard(user);

                    }

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Email or Password!");
                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.setBounds(220, 220, 120, 40);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(52,152,219));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        add(registerButton);

        registerButton.addActionListener(e -> {
            dispose();
            new RegistrationForm();
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
