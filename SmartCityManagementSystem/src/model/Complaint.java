package model;

public class Complaint {

    private int id;
    private String userEmail;
    private String category;
    private String title;
    private String description;
    private String location;
    private String status;

    public Complaint() {
    }

    public Complaint(int id, String userEmail, String category,
                     String title, String description,
                     String location, String status) {
        this.id = id;
        this.userEmail = userEmail;
        this.category = category;
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
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
}
