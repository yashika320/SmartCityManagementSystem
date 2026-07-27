package ui;

import dao.UserDAO;
import model.User;

public class TestUser {

    public static void main(String[] args) {

        User user = new User();

        user.setFullName("Yashika Verma");
        user.setEmail("yashika26@gmail.com");
        user.setPassword("12345");
        user.setPhone("9876543210");
        user.setRole("Citizen");

        UserDAO userDAO = new UserDAO();

        boolean result = userDAO.registerUser(user);

        if (result) {
            System.out.println("User Registered Successfully!");
        } else {
            System.out.println("Registration Failed!");
        }
    }
}
