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

        String query = "INSERT INTO complaints(user_email, category, title, description, location, status, image_path, complaint_date, complaint_time, priority) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, complaint.getUserEmail());
            ps.setString(2, complaint.getCategory());
            ps.setString(3, complaint.getTitle());
            ps.setString(4, complaint.getDescription());
            ps.setString(5, complaint.getLocation());
            ps.setString(6, complaint.getStatus());
            ps.setString(7, complaint.getImagePath());
            ps.setString(8, complaint.getComplaintDate());
            ps.setString(9, complaint.getComplaintTime());
            ps.setString(10, complaint.getPriority());
            System.out.println("Date = " + complaint.getComplaintDate());
            System.out.println("Time = " + complaint.getComplaintTime());
            System.out.println("Priority = " + complaint.getPriority());

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
                complaint.setImagePath(rs.getString("image_path"));
                complaint.setComplaintDate(rs.getString("complaint_date"));
                complaint.setComplaintTime(rs.getString("complaint_time"));
                complaint.setPriority(rs.getString("priority"));

                list.add(complaint);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Complaint> getAllComplaints() {

        List<Complaint> list = new ArrayList<>();

        String query = "SELECT * FROM complaints";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

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
                complaint.setImagePath(rs.getString("image_path"));
                complaint.setComplaintDate(rs.getString("complaint_date"));
                complaint.setComplaintTime(rs.getString("complaint_time"));
                complaint.setPriority(rs.getString("priority"));

                list.add(complaint);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateComplaintStatus(int id, String status) {

        String query = "UPDATE complaints SET status=? WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, status);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
        public Complaint searchComplaintById ( int id){

            String query = "SELECT * FROM complaints WHERE id=?";

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    Complaint complaint = new Complaint();

                    complaint.setId(rs.getInt("id"));
                    complaint.setUserEmail(rs.getString("user_email"));
                    complaint.setCategory(rs.getString("category"));
                    complaint.setTitle(rs.getString("title"));
                    complaint.setDescription(rs.getString("description"));
                    complaint.setLocation(rs.getString("location"));
                    complaint.setStatus(rs.getString("status"));
                    complaint.setImagePath(rs.getString("image_path"));
                    complaint.setComplaintDate(rs.getString("complaint_date"));
                    complaint.setComplaintTime(rs.getString("complaint_time"));
                    complaint.setPriority(rs.getString("priority"));
                    return complaint;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    public boolean deleteComplaint(int id) {

        String query = "DELETE FROM complaints WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    public int getTotalComplaints() {

        String query = "SELECT COUNT(*) FROM complaints";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int getPendingComplaints() {

        String query = "SELECT COUNT(*) FROM complaints WHERE status='Pending'";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int getResolvedComplaints() {

        String query = "SELECT COUNT(*) FROM complaints WHERE status='Resolved'";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public List<Complaint> getComplaintsByStatus(String status) {

        List<Complaint> list = new ArrayList<>();

        String query;

        if (status.equals("All")) {
            query = "SELECT * FROM complaints";
        } else {
            query = "SELECT * FROM complaints WHERE status=?";
        }

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            if (!status.equals("All")) {
                ps.setString(1, status);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Complaint complaint = new Complaint();

                complaint.setId(rs.getInt("id"));
                complaint.setUserEmail(rs.getString("user_email"));
                complaint.setCategory(rs.getString("category"));
                complaint.setTitle(rs.getString("title"));
                complaint.setLocation(rs.getString("location"));
                complaint.setStatus(rs.getString("status"));
                complaint.setPriority(rs.getString("priority"));
                complaint.setComplaintDate(rs.getString("complaint_date"));
                complaint.setComplaintTime(rs.getString("complaint_time"));
                complaint.setImagePath(rs.getString("image_path"));

                list.add(complaint);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public int getTotalComplaintsByUser(String email) {

        String query = "SELECT COUNT(*) FROM complaints WHERE user_email=?";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    public int getResolvedComplaintsByUser(String email) {

        String query = "SELECT COUNT(*) FROM complaints WHERE user_email=? AND status='Resolved'";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int getCategoryCount(String category) {

        String query = "SELECT COUNT(*) FROM complaints WHERE category=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    }



