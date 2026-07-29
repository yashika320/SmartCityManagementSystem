package ui;
import dao.UserDAO;
import model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JFrame {
    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel, phoneLabel, roleLabel;

    private JTextField nameField, emailField, phoneField;

    private JPasswordField passwordField;

    private JComboBox<String> roleBox;

    private JButton registerButton, loginButton;

    public RegistrationForm() {

        setTitle("Smart City Management System");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        titleLabel = new JLabel("USER REGISTRATION");
        titleLabel.setBounds(150, 20, 200, 30);
        titleLabel.setFont(new Font("Arial", java.awt.Font.BOLD, 20));
        add(titleLabel);

        nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 70, 100, 25);
        nameLabel.setFont(new Font("Arial", java.awt.Font.PLAIN, 14));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 70, 200, 25);
        add(nameField);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 100, 25);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(170, 110, 200, 25);
        add(emailField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 25);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 150, 200, 25);
        add(passwordField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 190, 100, 25);
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(170, 190, 200, 25);
        add(phoneField);

        roleLabel = new JLabel("Role:");
        roleLabel.setBounds(50, 230, 100, 25);
        add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Citizen", "Admin"});
        roleBox.setBounds(170, 230, 200, 25);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(roleBox);

        registerButton = new JButton("Register");
        registerButton.setBounds(90, 300, 120, 35);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        add(registerButton);
        registerButton.setBackground(new Color(46, 204, 113));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (nameField.getText().trim().isEmpty() ||
                        emailField.getText().trim().isEmpty() ||
                        String.valueOf(passwordField.getPassword()).trim().isEmpty() ||
                        phoneField.getText().trim().isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please fill all fields!"
                    );
                    return;
                }
                User user = new User();

                user.setFullName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(String.valueOf(passwordField.getPassword()));
                user.setPhone(phoneField.getText());
                user.setRole(roleBox.getSelectedItem().toString());

                if (!user.getPhone().matches("[0-9]{10}")) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Phone number must be exactly 10 digits!"
                    );
                    return;
                }

                if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Enter a valid email address!"
                    );
                    return;
                }

                UserDAO dao = new UserDAO();
                if (dao.isEmailExists(user.getEmail())) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Email already registered!"
                    );

                    return;
                }

                if (dao.registerUser(user)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    nameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");
                    phoneField.setText("");
                    roleBox.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed!");
                }
            }
        });

        loginButton = new JButton("Login");
        loginButton.setBounds(240, 300, 120, 35);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        add(loginButton);
        loginButton.setBackground(new Color(52, 152, 219));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(e -> {
            dispose();
            new LoginForm();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
