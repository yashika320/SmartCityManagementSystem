package ui;
import dao.ComplaintDAO;
import model.User;
import java.awt.*;
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
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setBounds(120,20,300,35);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title);

        JButton viewComplaints = new JButton("View All Complaints");
        viewComplaints.setBounds(130,160,220,40);
        viewComplaints.setFont(new Font("Arial", Font.BOLD,14));
        viewComplaints.setBackground(new Color(52,152,219));
        viewComplaints.setForeground(Color.WHITE);
        viewComplaints.setFocusPainted(false);
        add(viewComplaints);

        viewComplaints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminViewComplaints();
            }
        });

        JButton updateStatus = new JButton("Update Complaint Status");
        updateStatus.setBounds(130,210,220,40);
        updateStatus.setFont(new Font("Arial", Font.BOLD,14));
        updateStatus.setBackground(new Color(241,196,15));
        updateStatus.setForeground(Color.BLACK);
        updateStatus.setFocusPainted(false);
        add(updateStatus);

        updateStatus.addActionListener(e -> {
            new AdminUpdateStatus();
        });

        JButton deleteButton = new JButton("Delete Complaint");
        deleteButton.setBounds(130,260,220,40);
        deleteButton.setFont(new Font("Arial", Font.BOLD,14));
        deleteButton.setBackground(new Color(231,76,60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        add(deleteButton);
        deleteButton.addActionListener(e -> {
            new DeleteComplaintForm();
        });

        JButton searchButton = new JButton("Search Complaint");
        searchButton.setBounds(130,310,220,40);
        searchButton.setFont(new Font("Arial", Font.BOLD,14));
        searchButton.setBackground(new Color(155,89,182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        add(searchButton);
        searchButton.addActionListener(e -> {
            new SearchComplaintForm();
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(130,360,220,40);
        logout.setFont(new Font("Arial", Font.BOLD,14));
        logout.setBackground(new Color(127,140,141));
        logout.setForeground(Color.WHITE);
        logout.setFocusPainted(false);
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
        totalLabel.setBounds(40,70,300,25);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(totalLabel);

        JLabel pendingLabel = new JLabel("Pending Complaints : " + dao.getPendingComplaints());
        pendingLabel.setBounds(40,95,300,25);
        pendingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(pendingLabel);

        JLabel resolvedLabel = new JLabel("Resolved Complaints : " + dao.getResolvedComplaints());
        resolvedLabel.setBounds(40,120,300,25);
        resolvedLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resolvedLabel);

        setVisible(true);
    }
}
