package ui;

import dao.ComplaintDAO;

import javax.swing.*;

public class AdminUpdateStatus extends JFrame {

    private JTextField idField;

    private JComboBox<String> statusBox;

    private JButton updateButton;

    public AdminUpdateStatus() {

        setTitle("Update Complaint Status");
        setSize(400,250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Complaint ID:");
        idLabel.setBounds(40,40,120,25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(170,40,150,25);
        add(idField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(40,90,120,25);
        add(statusLabel);

        statusBox = new JComboBox<>(new String[]{
                "Pending",
                "In Progress",
                "Resolved"
        });

        statusBox.setBounds(170,90,150,25);
        add(statusBox);

        updateButton = new JButton("Update");
        updateButton.setBounds(120,150,140,35);
        add(updateButton);

        updateButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            String status = statusBox.getSelectedItem().toString();

            ComplaintDAO dao = new ComplaintDAO();

            if(dao.updateComplaintStatus(id,status)){

                JOptionPane.showMessageDialog(null,
                        "Status Updated Successfully");

            }else{

                JOptionPane.showMessageDialog(null,
                        "Update Failed");

            }

        });

        setVisible(true);
    }
}
