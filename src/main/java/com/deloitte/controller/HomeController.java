package com.deloitte.controller;
//thisd class handles http reqs
import com.deloitte.model.User;
import com.deloitte.service.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class HomeController {

	//dependency injection used
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home() {
        return "home";
    }

    //\login page
    @GetMapping("/login")
    public String loginPage(Model model) {   //model-  send data from controller to jsp
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        // Try USER role first
        User user = userService.validateUser(email, password, "USER");
        
        // If not found as USER, try ADMIN role
        if(user == null) {
            user = userService.validateUser(email, password, "ADMIN");
        }

        if(user == null){
            model.addAttribute("error","Invalid email or password!");
            return "login";
        }

        // Save user in session
        session.setAttribute("loggedInUser", user);

        // Check if there's a redirect URL stored in session
        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if(redirectUrl != null) {
        	session.removeAttribute("redirectAfterLogin");
        	return "redirect:" + redirectUrl;
        }
        
        // redirect based on user role
        if("ADMIN".equalsIgnoreCase(user.getRole())){
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    //called when user click sign up the
    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user,   //modelattrivute-automatically maps
    		//from fields to user obj
                               @RequestParam String confirmPassword,
                               Model model) {

        if(!user.getPassword().equals(confirmPassword)){
            model.addAttribute("error","Passwords do not match!");
            return "signup";
        }

        if(userService.isEmailExists(user.getEmail())){
            model.addAttribute("error","Email already exists!");
            return "signup";
        }

        userService.registerUser(user);
        model.addAttribute("message","Registration successful! Please login.");
        return "login";
    }
  
    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        // Get logged-in user from session
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        model.addAttribute("username", loggedInUser.getEmail()); // show email as welcome message
        return "user/dashboard"; // JSP page
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}