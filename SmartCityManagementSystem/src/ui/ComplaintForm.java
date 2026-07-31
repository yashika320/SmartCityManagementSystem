package ui;
import dao.ComplaintDAO;
import model.Complaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ComplaintForm extends JFrame {
    private JLabel categoryLabel, titleLabel, descriptionLabel, locationLabel, priorityLabel;

    private JComboBox<String> categoryBox, priorityBox;
    private JLabel dateLabel, timeLabel;

    private JTextField titleField, locationField;

    private JTextArea descriptionArea;

    private JButton submitButton, uploadButton;

    private JLabel imageLabel;

    private String imagePath = "";

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

        priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(50, 330, 100, 25);
        add(priorityLabel);

        priorityBox = new JComboBox<>(new String[]{
                "Low",
                "Medium",
                "High"
        });
        priorityBox.setBounds(180, 330, 250, 25);
        add(priorityBox);

        imageLabel = new JLabel("No Image Selected");
        imageLabel.setBounds(180, 370, 250, 25);
        add(imageLabel);

        uploadButton = new JButton("Choose Image");
        uploadButton.setBounds(440, 370, 120, 25);
        add(uploadButton);

        uploadButton.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                imagePath = file.getAbsolutePath();

                imageLabel.setText(file.getName());

            }

        });

        submitButton = new JButton("Submit Complaint");
        submitButton.setBounds(180, 420, 180, 35);
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String description = descriptionArea.getText().trim();
                String location = locationField.getText().trim();

                if(title.isEmpty() || description.isEmpty() || location.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please fill all required fields!"
                    );

                    return;
                }

                Complaint complaint = new Complaint();

                complaint.setUserEmail(currentUser.getEmail());
                complaint.setCategory(categoryBox.getSelectedItem().toString());
                complaint.setTitle(titleField.getText());
                complaint.setDescription(descriptionArea.getText());
                complaint.setLocation(locationField.getText());
                complaint.setStatus("Pending");
                complaint.setImagePath(imagePath);
                complaint.setComplaintDate(LocalDate.now().toString());
                complaint.setComplaintTime(LocalTime.now().withNano(0).toString());
                complaint.setPriority(priorityBox.getSelectedItem().toString());

                complaint.setComplaintDate(
                        java.time.LocalDate.now().toString());

                complaint.setComplaintTime(
                        java.time.LocalTime.now().withNano(0).toString());

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
