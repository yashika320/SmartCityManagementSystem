package dao;

import database.DBConnection;
import model.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComplaintDAO {

    public boolean registerComplaint(Complaint complaint) {

        String query = "INSERT INTO complaints(user_email, category, title, description, location, status) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, complaint.getUserEmail());
            ps.setString(2, complaint.getCategory());
            ps.setString(3, complaint.getTitle());
            ps.setString(4, complaint.getDescription());
            ps.setString(5, complaint.getLocation());
            ps.setString(6, complaint.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
