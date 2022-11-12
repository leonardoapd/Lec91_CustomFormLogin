package ca.sheridancollege.perdomod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.perdomod.beans.User;
import ca.sheridancollege.perdomod.database.DatabaseAccess;

@Controller
public class HomeController {

    // Injecting the DatabaseAccess dependency
    @Autowired
    private DatabaseAccess da;

    // Injecting the BCryptPasswordEncoder dependency
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/secure")
    public String secureIndex() {
        return "/secure/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/permission-denied")
    public String permissionDenied() {
        return "/error/permission-denied";
    }

    @GetMapping("/secure/check")
    public String check() {
        return "/secure/check";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/addUser")
    public String addUser(User user, Model model) {
        user.setEnabled(true);

        // Encrypt the password
        String encodedPassword = passwordEncoder.encode(user.getEncryptedPassword());
        user.setEncryptedPassword(encodedPassword);
        da.addUser(user);
        model.addAttribute("user", new User());

        Boolean success = true;

        model.addAttribute("signup", success);
        return "login";
    }

}
