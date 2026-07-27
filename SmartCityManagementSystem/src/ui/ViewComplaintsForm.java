package ui;

import dao.ComplaintDAO;
import model.Complaint;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewComplaintsForm extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewComplaintsForm(User user) {

        setTitle("My Complaints");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Category");
        model.addColumn("Title");
        model.addColumn("Location");
        model.addColumn("Status");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);

        ComplaintDAO dao = new ComplaintDAO();

        List<Complaint> complaints = dao.getComplaintsByUser(user.getEmail());

        for (Complaint c : complaints) {

            model.addRow(new Object[]{
                    c.getId(),
                    c.getCategory(),
                    c.getTitle(),
                    c.getLocation(),
                    c.getStatus()
            });
        }

        setVisible(true);
    }
}
