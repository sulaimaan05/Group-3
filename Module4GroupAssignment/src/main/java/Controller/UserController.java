package Controller;

import Service.*;
import Model.User;

public class UserController {

    private service.UserService userService;

    public UserController(service.UserService userService) {
        this.userService = userService;
    }

    public void updateEmail(int userId, String newEmail) {
        userService.updateEmail(userId, newEmail);
    }

    public void updatePassword(int userId, String newPassword) {
        userService.updatePassword(userId, newPassword);
    }

    public void requestRoleChange(int userId, String newRole) {
        userService.requestRoleChange(userId, newRole);
    }

    public User getUserProfile(int userId) {
        return userService.getUserById(userId);
    }
}