package model;

public class Complaint {

    private int id;
    private String userEmail;
    private String category;
    private String title;
    private String description;
    private String location;
    private String status;
    private String imagePath;
    private String complaintDate;
    private String complaintTime;
    private String priority;

    public Complaint() {
    }

    public Complaint(int id, String userEmail, String category,
                     String title, String description,
                     String location, String status,
                     String imagePath,
                     String complaintDate,
                     String complaintTime,
                     String priority) {

        this.id = id;
        this.userEmail = userEmail;
        this.category = category;
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
        this.imagePath = imagePath;
        this.complaintDate = complaintDate;
        this.complaintTime = complaintTime;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(String complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
