package ui;

import dao.ComplaintDAO;

import javax.swing.*;

public class DeleteComplaintForm extends JFrame {

    private JTextField idField;
    private JButton deleteButton;

    public DeleteComplaintForm() {

        setTitle("Delete Complaint");
        setSize(350,200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Complaint ID:");
        idLabel.setBounds(30,40,100,25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(140,40,150,25);
        add(idField);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100,100,120,35);
        add(deleteButton);

        deleteButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this complaint?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if(choice == JOptionPane.YES_OPTION){

                ComplaintDAO dao = new ComplaintDAO();

                if(dao.deleteComplaint(id)){

                    JOptionPane.showMessageDialog(null,
                            "Complaint Deleted Successfully");

                }else{

                    JOptionPane.showMessageDialog(null,
                            "Complaint Not Found!");

                }

            }

        });

        setVisible(true);
    }
}