package ui;

import dao.ComplaintDAO;
import model.Complaint;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminViewComplaints extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public AdminViewComplaints() {

        setTitle("All Complaints");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("User");
        model.addColumn("Category");
        model.addColumn("Title");
        model.addColumn("Location");
        model.addColumn("Status");

        table = new JTable(model);

        add(new JScrollPane(table));

        ComplaintDAO dao = new ComplaintDAO();

        List<Complaint> list = dao.getAllComplaints();

        for (Complaint c : list) {

            model.addRow(new Object[]{
                    c.getId(),
                    c.getUserEmail(),
                    c.getCategory(),
                    c.getTitle(),
                    c.getLocation(),
                    c.getStatus()
            });
        }

        setVisible(true);
    }
}