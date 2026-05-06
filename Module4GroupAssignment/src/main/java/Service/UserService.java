package service;

import Model.User;
import Repository.UserRepo;

import java.util.Optional;

public class UserService {

    private UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    // 🔍 Get user by ID
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✏️ Update email
    public void updateEmail(int userId, String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        // Optional: check if email already exists
        Optional<User> existing = userRepository.findByEmail(newEmail);
        if (existing.isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        userRepository.updateEmail(userId, newEmail);
    }

    // 🔐 Update password
    public void updatePassword(int userId, String newPassword) {
        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }

        // TODO: hash password before saving
        userRepository.updatePassword(userId, newPassword);
    }

    // 🔄 Request role change (upgrade/downgrade)
    public void requestRoleChange(int userId, String newRole) {
        User user = getUserById(userId);

        if (user.getRole().equals(newRole)) {
            throw new IllegalArgumentException("User already has this role");
        }

        // Business rule example:
        // Moderator role might require approval (optional extension)
        if (newRole.equalsIgnoreCase("MODERATOR")) {
            // Could store as pending request instead
            userRepository.updateRole(userId, "PENDING_MODERATOR");
        } else {
            userRepository.updateRole(userId, newRole.toUpperCase());
        }
    }

    // 📋 Get user by username (useful for profile views)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ❌ Delete account (optional feature)
    public void deleteUser(int userId) {
        userRepository.delete(userId);
    }
}