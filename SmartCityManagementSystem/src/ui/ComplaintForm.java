package ui;
import dao.ComplaintDAO;
import model.Complaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;

import javax.swing.*;

public class ComplaintForm extends JFrame {
    private JLabel categoryLabel, titleLabel, descriptionLabel, locationLabel;

    private JComboBox<String> categoryBox;

    private JTextField titleField, locationField;

    private JTextArea descriptionArea;

    private JButton submitButton;

    private User currentUser;

    public ComplaintForm(User user) {

        this.currentUser = user;
        setTitle("Register Complaint");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 40, 100, 25);
        add(categoryLabel);

        categoryBox = new JComboBox<>(new String[]{
                "Road",
                "Water",
                "Electricity",
                "Garbage",
                "Street Light",
                "Other"
        });
        categoryBox.setBounds(180, 40, 250, 25);
        add(categoryBox);

        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 90, 100, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(180, 90, 250, 25);
        add(titleField);

        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 140, 100, 25);
        add(descriptionLabel);

        descriptionArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setBounds(180, 140, 250, 120);
        add(scroll);

        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(50, 290, 100, 25);
        add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(180, 290, 250, 25);
        add(locationField);

        submitButton = new JButton("Submit Complaint");
        submitButton.setBounds(180, 360, 170, 35);
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Complaint complaint = new Complaint();

                complaint.setUserEmail(currentUser.getEmail());
                complaint.setCategory(categoryBox.getSelectedItem().toString());
                complaint.setTitle(titleField.getText());
                complaint.setDescription(descriptionArea.getText());
                complaint.setLocation(locationField.getText());
                complaint.setStatus("Pending");

                ComplaintDAO dao = new ComplaintDAO();

                if (dao.registerComplaint(complaint)) {

                    JOptionPane.showMessageDialog(null,
                            "Complaint Registered Successfully!");

                    categoryBox.setSelectedIndex(0);
                    titleField.setText("");
                    descriptionArea.setText("");
                    locationField.setText("");

                } else {

                    JOptionPane.showMessageDialog(null,
                            "Failed to Register Complaint!");
                }
            }
        });

        setVisible(true);
    }
}
