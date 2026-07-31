package ui;
import javax.swing.JComboBox;
import dao.ComplaintDAO;
import model.Complaint;
import java.awt.Image;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class AdminViewComplaints extends JFrame {
    private JComboBox<String> statusFilter;
    private JTable table;
    private DefaultTableModel model;

    public AdminViewComplaints() {

        setTitle("All Complaints");
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        model = new DefaultTableModel();
        statusFilter = new JComboBox<>(new String[]{
                "All",
                "Pending",
                "Resolved"
        });

        statusFilter.setBounds(20, 20, 150, 30);
        add(statusFilter);
        statusFilter.addActionListener(e -> {

            String status = statusFilter.getSelectedItem().toString();

            loadTable(status);

        });

        model.addColumn("ID");
        model.addColumn("User");
        model.addColumn("Category");
        model.addColumn("Title");
        model.addColumn("Location");
        model.addColumn("Status");
        model.addColumn("Priority");
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Image");


        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 1140, 370);
        add(scrollPane);

        loadTable("All");
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {

                    int row = table.rowAtPoint(e.getPoint());

                    if (row == -1) {
                        return;
                    }

                    String imagePath = String.valueOf(model.getValueAt(row, 9));

                    if (imagePath == null || imagePath.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Image Available!");
                        return;
                    }

                    File file = new File(imagePath);

                    if (!file.exists()) {
                        JOptionPane.showMessageDialog(null, "Image File Not Found!\n" + imagePath);
                        return;
                    }

                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage().getScaledInstance(
                            500, 350, Image.SCALE_SMOOTH);

                    JLabel label = new JLabel(new ImageIcon(img));

                    JOptionPane.showMessageDialog(
                            null,
                            label,
                            "Complaint Image",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            }
        });
                setVisible(true);
    }
    private void loadTable(String status) {

        model.setRowCount(0);

        ComplaintDAO dao = new ComplaintDAO();

        java.util.List<Complaint> list = dao.getComplaintsByStatus(status);

        for (Complaint c : list) {

            model.addRow(new Object[]{
                    c.getId(),
                    c.getUserEmail(),
                    c.getCategory(),
                    c.getTitle(),
                    c.getLocation(),
                    c.getStatus(),
                    c.getPriority(),
                    c.getComplaintDate(),
                    c.getComplaintTime(),
                    c.getImagePath()
            });
        }
    }
}