package ui;

import dao.ComplaintDAO;
import model.Complaint;

import javax.swing.*;

public class SearchComplaintForm extends JFrame {

    private JTextField idField;
    private JTextArea resultArea;
    private JButton searchButton;

    public SearchComplaintForm() {

        setTitle("Search Complaint");
        setSize(500,400);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Complaint ID:");
        idLabel.setBounds(30,30,120,25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(150,30,150,25);
        add(idField);

        searchButton = new JButton("Search");
        searchButton.setBounds(320,30,100,25);
        add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(30,80,420,240);
        add(scrollPane);

        searchButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            ComplaintDAO dao = new ComplaintDAO();

            Complaint complaint = dao.searchComplaintById(id);

            if (complaint != null) {

                resultArea.setText(
                        "User: " + complaint.getUserEmail() +
                                "\nCategory: " + complaint.getCategory() +
                                "\nTitle: " + complaint.getTitle() +
                                "\nDescription: " + complaint.getDescription() +
                                "\nLocation: " + complaint.getLocation() +
                                "\nStatus: " + complaint.getStatus()
                );

            } else {

                resultArea.setText("Complaint Not Found!");

            }

        });

        setVisible(true);
    }
}
