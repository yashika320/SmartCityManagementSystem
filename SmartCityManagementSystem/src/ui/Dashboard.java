package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    public Dashboard(User user) {

        setTitle("Smart City Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getFullName());
        welcomeLabel.setBounds(150, 20, 320, 35);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(welcomeLabel);

        JLabel roleLabel = new JLabel("Role : " + user.getRole());
        roleLabel.setBounds(220, 60, 180, 30);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        add(roleLabel);

        JButton complaintButton = new JButton("Register Complaint");
        complaintButton.setBounds(180, 120, 220, 40);
        complaintButton.setFont(new Font("Arial", Font.BOLD, 14));
        complaintButton.setBackground(new Color(46,204,113));
        complaintButton.setForeground(Color.WHITE);
        complaintButton.setFocusPainted(false);
        add(complaintButton);

        JButton viewButton = new JButton("View My Complaints");
        viewButton.setBounds(180, 180, 220, 40);
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setBackground(new Color(52,152,219));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFocusPainted(false);
        add(viewButton);


        complaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ComplaintForm(user);
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewComplaintsForm(user);
            }
        });

        JButton profileButton = new JButton("My Profile");
        profileButton.setBounds(180,240,220,40);
        profileButton.setFont(new Font("Arial", Font.BOLD,14));
        profileButton.setBackground(new Color(155,89,182));
        profileButton.setForeground(Color.WHITE);
        profileButton.setFocusPainted(false);
        add(profileButton);


        profileButton.addActionListener(e -> {
            new ProfileForm(user);
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(180, 300, 220, 40);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(231,76,60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        add(logoutButton);

        logoutButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new LoginForm();
            }
        });
        setVisible(true);
    }
}
