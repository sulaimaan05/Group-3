package Service;

import Model.User;
import Repository.UserRepo;

public class AuthService {

    private UserRepo userRepository;

    public AuthService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String email, String password, String role) {
        // validation + hashing later
        return userRepository.save(username, email, password, role);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        // check password
        return user;
    }

    public void logout(User user) {
        // session handling
    }
}