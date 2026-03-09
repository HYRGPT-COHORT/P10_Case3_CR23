package com.deloitte.test;

import com.deloitte.model.User;
import com.deloitte.service.UserService;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.deloitte.config.AppConfig;

@SpringJUnitConfig(classes = AppConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    public void testUserLogin() {
        logger.info("Testing USER login");

        String email = "user1@gmail.com"; // user in DB
        String password = "password";      // actual password
        String role = "USER";

        User user = userService.validateUser(email, password, role);
        Assertions.assertNotNull(user, "User login should succeed");
        Assertions.assertEquals(role, user.getRole(), "User role must match");

        logger.info("USER login successful: {}", user.getEmail());
    }

    @Test
    @Order(2)
    public void testAdminLogin() {
        logger.info("Testing ADMIN login");

        String email = "admin@gmail.com"; // admin in DB
        String password = "adminpass";    // actual password
        String role = "ADMIN";

        User admin = userService.validateUser(email, password, role);
        Assertions.assertNotNull(admin, "Admin login should succeed");
        Assertions.assertEquals(role, admin.getRole(), "Admin role must match");

        logger.info("ADMIN login successful: {}", admin.getEmail());
    }
}