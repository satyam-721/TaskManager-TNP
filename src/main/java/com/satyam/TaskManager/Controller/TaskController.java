package com.satyam.TaskManager.Controller;

import com.satyam.TaskManager.Model.Task;
import com.satyam.TaskManager.Repo.TaskRepo;
import com.satyam.TaskManager.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TokenUtil tokenUtil;

    @PostMapping
    public Object createNote(@RequestBody Task note,
                             @RequestHeader("Authorization")String token){
        String username = tokenUtil.validateToken(token);

        if(username == null){
            return "Unauthorized";
        }
        note.setUsername(token);

        return taskRepo.save(note);

    }

    @GetMapping
    public Object getNotes(@RequestHeader("Authorization")String token){
        String username = tokenUtil.validateToken(token);
        if(username == null){
            return "Unauthorized";
        }
        return taskRepo.findByUsername(username);
    }



}
