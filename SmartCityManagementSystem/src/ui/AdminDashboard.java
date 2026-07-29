package ui;
import dao.ComplaintDAO;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard(User user) {

        setTitle("Admin Dashboard");
        setSize(500,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("WELCOME ADMIN");
        title.setBounds(150,20,200,30);
        add(title);

        JButton viewComplaints = new JButton("View All Complaints");
        viewComplaints.setBounds(130,160,220,35);
        add(viewComplaints);
        viewComplaints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminViewComplaints();
            }
        });

        JButton updateStatus = new JButton("Update Complaint Status");
        updateStatus.setBounds(130,205,220,35);
        add(updateStatus);
        updateStatus.addActionListener(e -> {
            new AdminUpdateStatus();
        });

        JButton deleteButton = new JButton("Delete Complaint");
        deleteButton.setBounds(130, 250, 220, 35);
        add(deleteButton);
        deleteButton.addActionListener(e -> {
            new DeleteComplaintForm();
        });

        JButton searchButton = new JButton("Search Complaint");
        searchButton.setBounds(130, 295, 220, 35);
        add(searchButton);
        searchButton.addActionListener(e -> {
            new SearchComplaintForm();
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(130,340,220,35);
        add(logout);


        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

            }
        });
        ComplaintDAO dao = new ComplaintDAO();

        JLabel totalLabel = new JLabel("Total Complaints : " + dao.getTotalComplaints());
        totalLabel.setBounds(30, 60, 250, 25);
        add(totalLabel);

        JLabel pendingLabel = new JLabel("Pending Complaints : " + dao.getPendingComplaints());
        pendingLabel.setBounds(30, 90, 250, 25);
        add(pendingLabel);

        JLabel resolvedLabel = new JLabel("Resolved Complaints : " + dao.getResolvedComplaints());
        resolvedLabel.setBounds(30, 120, 250, 25);
        add(resolvedLabel);
        setVisible(true);
    }
}
