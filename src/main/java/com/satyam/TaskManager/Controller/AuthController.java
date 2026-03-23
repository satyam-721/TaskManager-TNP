package com.satyam.TaskManager.Controller;


import com.satyam.TaskManager.Model.Task;
import com.satyam.TaskManager.Model.Users;
import com.satyam.TaskManager.Repo.TaskRepo;
import com.satyam.TaskManager.Repo.UserRepo;
import com.satyam.TaskManager.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo  taskRepo;

    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("/admin")
    public Object getAllTasks(@RequestHeader("Authorization") String token){
        String username = tokenUtil.validateToken(token);

        if (username == null){
            return "UnAuthorized";
        }

        Users user =  userRepo.findByUsername(username);

        if( !user.getRole().equals("ADMIN")){
            return "Access Denied";
        }
        return userRepo.findAll();

    }

    @PostMapping("/register")
    public String register(@RequestBody Users user){
        System.out.println("Registered");
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());
        userRepo.save(user);
        return "Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        Users dbUser = userRepo.findByUsername(user.getUsername());
        if(dbUser != null && dbUser.getPassword().equals(user.getPassword())){
            return tokenUtil.generateToken(user.getUsername());
        }
        return "Invalid";
    }
}
