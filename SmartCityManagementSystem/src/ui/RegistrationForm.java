package ui;
import dao.UserDAO;
import model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.*;

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

        titleLabel = new JLabel("USER REGISTRATION");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 70, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(170, 70, 200, 25);
        add(nameField);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 110, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(170, 110, 200, 25);
        add(emailField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 150, 200, 25);
        add(passwordField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 190, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(170, 190, 200, 25);
        add(phoneField);

        roleLabel = new JLabel("Role:");
        roleLabel.setBounds(50, 230, 100, 25);
        add(roleLabel);

        roleBox = new JComboBox<>(new String[]{"Citizen", "Admin"});
        roleBox.setBounds(170, 230, 200, 25);
        add(roleBox);

        registerButton = new JButton("Register");
        registerButton.setBounds(90, 300, 120, 35);
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                User user = new User();

                user.setFullName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(String.valueOf(passwordField.getPassword()));
                user.setPhone(phoneField.getText());
                user.setRole(roleBox.getSelectedItem().toString());

                UserDAO dao = new UserDAO();

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
        add(loginButton);

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

        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
