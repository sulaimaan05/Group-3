package Controller;

import Service.AuthService;
import Model.User;

public class AuthController {

    private service.AuthService authService;

    public AuthController(service.AuthService authService) {
        this.authService = authService;
    }

    public User register(String username, String email, String password, String role) {
        return authService.register(username, email, password, role);
    }

    public User login(String username, String password) {
        return authService.login(username, password);
    }

    public void logout(User user) {
        authService.logout(user);
    }
}