package ui;

import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard(User user) {

        setTitle("Admin Dashboard");
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("WELCOME ADMIN");
        title.setBounds(170,20,200,30);
        add(title);

        JButton viewComplaints = new JButton("View All Complaints");
        viewComplaints.setBounds(130,80,220,35);
        add(viewComplaints);
        viewComplaints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminViewComplaints();
            }
        });

        JButton updateStatus = new JButton("Update Complaint Status");
        updateStatus.setBounds(130,140,220,35);
        add(updateStatus);
        updateStatus.addActionListener(e -> {
            new AdminUpdateStatus();
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(130,200,220,35);
        add(logout);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm();
            }
        });

        setVisible(true);
    }
}
