package dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
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
         public List<Complaint> getComplaintsByUser(String email) {

            List<Complaint> list = new ArrayList<>();

            String query = "SELECT * FROM complaints WHERE user_email=?";

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Complaint complaint = new Complaint();

                    complaint.setId(rs.getInt("id"));
                    complaint.setUserEmail(rs.getString("user_email"));
                    complaint.setCategory(rs.getString("category"));
                    complaint.setTitle(rs.getString("title"));
                    complaint.setDescription(rs.getString("description"));
                    complaint.setLocation(rs.getString("location"));
                    complaint.setStatus(rs.getString("status"));

                    list.add(complaint);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
    }
}
