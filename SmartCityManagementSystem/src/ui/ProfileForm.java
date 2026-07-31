package ui;

import dao.ComplaintDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ProfileForm extends JFrame {

    public ProfileForm(User user) {

        setTitle("My Profile");
        setSize(450,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);


        JLabel title = new JLabel("MY PROFILE");
        title.setBounds(150,20,200,40);
        title.setFont(new Font("Arial",Font.BOLD,24));
        add(title);


        JLabel name = new JLabel("Name : " + user.getFullName());
        name.setBounds(60,90,300,30);
        name.setFont(new Font("Arial",Font.PLAIN,16));
        add(name);


        JLabel email = new JLabel("Email : " + user.getEmail());
        email.setBounds(60,130,300,30);
        email.setFont(new Font("Arial",Font.PLAIN,16));
        add(email);


        JLabel role = new JLabel("Role : " + user.getRole());
        role.setBounds(60,170,300,30);
        role.setFont(new Font("Arial",Font.PLAIN,16));
        add(role);


        ComplaintDAO dao = new ComplaintDAO();

        JLabel total = new JLabel(
                "Total Complaints : " + dao.getTotalComplaintsByUser(user.getEmail())
        );

        total.setBounds(60,220,300,30);
        total.setFont(new Font("Arial",Font.PLAIN,16));
        add(total);


        JLabel resolved = new JLabel(
                "Resolved Complaints : " + dao.getResolvedComplaintsByUser(user.getEmail())
        );

        resolved.setBounds(60,260,300,30);
        resolved.setFont(new Font("Arial",Font.PLAIN,16));
        add(resolved);


        setVisible(true);
    }
}
