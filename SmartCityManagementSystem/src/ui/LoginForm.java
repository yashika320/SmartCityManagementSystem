package ui;
import dao.UserDAO;
import model.User;
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

        titleLabel = new JLabel("USER LOGIN");
        titleLabel.setBounds(160, 20, 150, 30);
        add(titleLabel);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 80, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 80, 200, 25);
        add(emailField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 200, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(80, 220, 120, 35);
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
        registerButton.setBounds(220, 220, 120, 35);
        add(registerButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
