package com.ITE.parallel_project.controller;
import com.ITE.parallel_project.dto.AddUserRequest;
import com.ITE.parallel_project.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    final private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/add")
    public String addUser( @RequestBody AddUserRequest addUserRequest){
        userService.createUser(addUserRequest.getName());

        return "User Created Successfully";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam int id){
        userService.deleteUser(id);

        return "User Deleted Successfully";
    }
}
