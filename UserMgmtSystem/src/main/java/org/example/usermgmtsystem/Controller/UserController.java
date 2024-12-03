package org.example.usermgmtsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usermgmtsystem.ApiResponse.ApiResponse;
import org.example.usermgmtsystem.Model.User;
import org.example.usermgmtsystem.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-system")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    Logger logger= LoggerFactory.getLogger(UserController.class);


    @GetMapping("/get")
    public ResponseEntity getUsers(){

        logger.info("Inside get all users");
        return ResponseEntity.status(200).body(userService.getUsers());

    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){

        logger.info("Inside add an user");

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);

        return ResponseEntity.status(200).body(new ApiResponse("User added successfully!"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){

        logger.info("Inside update a specific user");

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.updateUser(id, user);

        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully!"));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){

        logger.info("Inside delete a specific user");

        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully!"));

    }

    ///////////////////////////

    @GetMapping("/check/{username}/{password}")
    public ResponseEntity checkUsernameAndPass(@PathVariable String username, @PathVariable String password){


        userService.checkUsernameAndPass(username, password);

        return ResponseEntity.status(200).body(new ApiResponse("Username and password are correct!"));

    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){

        User user = userService.getByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get/role/{role}")
    public ResponseEntity getByRole(@PathVariable String role){

        List<User> users = userService.getByRole(role);
        return ResponseEntity.status(200).body(users);

    }

    @GetMapping("/get/age-above/{age}")
    public ResponseEntity getByAgeAndAbove(@PathVariable Integer age){

        List<User> users = userService.getByAgeAndAbove(age);
        return ResponseEntity.status(200).body(users);

    }









}
