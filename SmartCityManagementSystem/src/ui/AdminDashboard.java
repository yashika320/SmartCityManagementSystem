package ui;
import dao.ComplaintDAO;
import model.User;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JLabel totalCount;
    private JLabel pendingCount;
    private JLabel resolvedCount;

    public AdminDashboard(User user) {

        setTitle("Admin Dashboard");
        setSize(700,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("ADMIN DASHBOARD");
        title.setBounds(180,15,350,40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(44,62,80));
        add(title);

        JButton viewComplaints = new JButton("View All Complaints");
        viewComplaints.setBounds(240,170,220,40);
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
        updateStatus.setBounds(240,220,220,40);
        updateStatus.setFont(new Font("Arial", Font.BOLD,14));
        updateStatus.setBackground(new Color(241,196,15));
        updateStatus.setForeground(Color.BLACK);
        updateStatus.setFocusPainted(false);
        add(updateStatus);

        updateStatus.addActionListener(e -> {
            new AdminUpdateStatus();
        });

        JButton deleteButton = new JButton("Delete Complaint");
        deleteButton.setBounds(240,270,220,40);
        deleteButton.setFont(new Font("Arial", Font.BOLD,14));
        deleteButton.setBackground(new Color(231,76,60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        add(deleteButton);
        deleteButton.addActionListener(e -> {
            new DeleteComplaintForm();
        });

        JButton searchButton = new JButton("Search Complaint");
        searchButton.setBounds(240,320,220,40);
        searchButton.setFont(new Font("Arial", Font.BOLD,14));
        searchButton.setBackground(new Color(155,89,182));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        add(searchButton);
        searchButton.addActionListener(e -> {
            new SearchComplaintForm();
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(520,390,100,40);
        refreshButton.setFont(new Font("Arial", Font.BOLD,14));
        refreshButton.setBackground(new Color(52,152,219));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        add(refreshButton);


        refreshButton.addActionListener(e -> {

            ComplaintDAO dao1 = new ComplaintDAO();

            totalCount.setText(String.valueOf(dao1.getTotalComplaints()));
            pendingCount.setText(String.valueOf(dao1.getPendingComplaints()));
            resolvedCount.setText(String.valueOf(dao1.getResolvedComplaints()));

            JOptionPane.showMessageDialog(
                    null,
                    "Dashboard Updated!"
            );

        });

        JButton logout = new JButton("Logout");
        logout.setBounds(240,370,220,40);
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



        JPanel totalPanel = new JPanel();
        totalPanel.setBounds(30,70,190,80);
        totalPanel.setBackground(new Color(52,152,219));
        totalPanel.setLayout(new GridLayout(2,1));

        JLabel totalTitle = new JLabel("Total Complaints",SwingConstants.CENTER);
        totalTitle.setForeground(Color.WHITE);

         totalCount = new JLabel(String.valueOf(dao.getTotalComplaints()),SwingConstants.CENTER);
        totalCount.setForeground(Color.WHITE);
        totalCount.setFont(new Font("Arial",Font.BOLD,24));

        totalPanel.add(totalTitle);
        totalPanel.add(totalCount);
        add(totalPanel);


        JPanel pendingPanel = new JPanel();
        pendingPanel.setBounds(250,70,190,80);
        pendingPanel.setBackground(new Color(241,196,15));
        pendingPanel.setLayout(new GridLayout(2,1));

        JLabel pendingTitle = new JLabel("Pending",SwingConstants.CENTER);
        pendingTitle.setForeground(Color.BLACK);

        pendingCount = new JLabel(String.valueOf(dao.getPendingComplaints()),SwingConstants.CENTER);
        pendingCount.setFont(new Font("Arial",Font.BOLD,24));

        pendingPanel.add(pendingTitle);
        pendingPanel.add(pendingCount);
        add(pendingPanel);


        JPanel resolvedPanel = new JPanel();
        resolvedPanel.setBounds(470,70,190,80);
        resolvedPanel.setBackground(new Color(46,204,113));
        resolvedPanel.setLayout(new GridLayout(2,1));

        JLabel resolvedTitle = new JLabel("Resolved",SwingConstants.CENTER);
        resolvedTitle.setForeground(Color.WHITE);

         resolvedCount = new JLabel(String.valueOf(dao.getResolvedComplaints()),SwingConstants.CENTER);
        resolvedCount.setForeground(Color.WHITE);
        resolvedCount.setFont(new Font("Arial",Font.BOLD,24));

        resolvedPanel.add(resolvedTitle);
        resolvedPanel.add(resolvedCount);
        add(resolvedPanel);

        JLabel categoryTitle = new JLabel("Category Wise Complaints");
        categoryTitle.setBounds(230,440,300,30);
        categoryTitle.setFont(new Font("Arial",Font.BOLD,18));
        add(categoryTitle);


        ComplaintDAO categoryDao = new ComplaintDAO();

        String[] categories = {
                "Road",
                "Water",
                "Electricity",
                "Garbage",
                "Street Light"
        };

        int y = 480;

        for(String category : categories){

            JLabel label = new JLabel(
                    category + " : " + categoryDao.getCategoryCount(category)
            );

            label.setBounds(250,y,250,25);
            label.setFont(new Font("Arial",Font.PLAIN,15));
            add(label);

            y += 25;
        }

        setVisible(true);
    }
}
