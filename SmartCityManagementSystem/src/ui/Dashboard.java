package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;

import javax.swing.*;

public class Dashboard extends JFrame {

    public Dashboard(User user) {

        setTitle("Smart City Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getFullName());
        welcomeLabel.setBounds(180, 40, 250, 30);
        add(welcomeLabel);

        JLabel roleLabel = new JLabel("Role : " + user.getRole());
        roleLabel.setBounds(180, 80, 200, 30);
        add(roleLabel);
        JButton complaintButton = new JButton("Register Complaint");
        complaintButton.setBounds(180, 140, 180, 35);
        add(complaintButton);

        complaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ComplaintForm(user);
            }
        });
        setVisible(true);
    }
}
