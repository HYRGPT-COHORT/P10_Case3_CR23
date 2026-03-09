//package com.deloitte.service;
//
//import com.deloitte.dao.UserDao;
//import com.deloitte.model.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @Autowired
//    private UserDao userDao;
//
//    // register user with encrypted password
//    public void registerUser(User user) {
//        String encryptedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encryptedPassword);
//        userDao.save(user);
//        logger.info("User registered successfully: {}", user.getEmail());
//    }
//
//    // Check if email exists
//    public boolean isEmailExists(String email) {
//        return userDao.isEmailExists(email);
//    }
//
//    // validate login with encrypted password
//    public User validateUser(String email, String password, String role) {
//        User user = userDao.findByEmail(email);
//        
//        if (user != null && user.getRole().equalsIgnoreCase(role)) {
//            // check if password matches (handles both encrypted and plain passwords for backward compatibility)
//            if (passwordEncoder.matches(password, user.getPassword()) || user.getPassword().equals(password)) {
//                logger.info("User logged in successfully: {}", email);
//                return user;
//            }
//        }
//        
//        logger.warn("Login failed for user: {}", email);
//        return null;
//    }
//    
//    // get user by id
//    public User getUserById(Long userId) {
//        return userDao.findById(userId);
//    }
//}



package com.deloitte.service;

import com.deloitte.dao.UserDao;
import com.deloitte.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    // REGISTER USER (No encryption - plain text)
    public void registerUser(User user) {
        // ✅ Store password as plain text
        // NO BCrypt hashing needed
        userDao.save(user);
        logger.info("User registered: {}", user.getEmail());
    }
    
    // VALIDATE USER ON LOGIN (Plain text comparison)
    public User validateUser(String email, String password, String role) {
        User user = userDao.findByEmail(email);
        
        if (user != null && user.getRole().equalsIgnoreCase(role)) {
            // ✅ Direct string comparison (no BCrypt)
            if (user.getPassword().equals(password)) {
                logger.info("User logged in: {}", email);
                return user;
            }
        }
        
        logger.warn("Login failed: {}", email);
        return null;
    }
    
    // CHECK IF EMAIL EXISTS
    public boolean isEmailExists(String email) {
        return userDao.findByEmail(email) != null;
    }
}