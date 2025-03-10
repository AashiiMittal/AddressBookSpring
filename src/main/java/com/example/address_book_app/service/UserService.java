package com.example.address_book_app.service;

import com.example.address_book_app.dto.UserDTO;
import com.example.address_book_app.model.User;
import com.example.address_book_app.repository.UserRepository;
import com.example.address_book_app.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register User
    public String registerUser(UserDTO userdto) {
        if (userRepository.existsByEmail(userdto.getEmail())) {
            return "Email is already in use!";
        }

        User user = new User();
        user.setUsername(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(passwordEncoder.encode(userdto.getPassword())); // Encrypt password

        userRepository.save(user);
        return "User registered successfully!";
    }

    // Authenticate User and Generate Token
    public String authenticateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "User not found!";
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid email or password!";
        }

        // Generate JWT Token using HMAC256
        return jwtUtil.generateToken(email);
    }
}
