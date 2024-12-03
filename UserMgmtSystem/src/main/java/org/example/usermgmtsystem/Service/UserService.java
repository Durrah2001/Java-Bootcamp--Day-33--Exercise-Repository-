package org.example.usermgmtsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.usermgmtsystem.ApiResponse.ApiException;
import org.example.usermgmtsystem.Model.User;
import org.example.usermgmtsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){

        User u = userRepository.findUserById(id);

        if(u == null)
            throw new ApiException("User not found!");

        u.setId(user.getId());
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setAge(user.getAge());

        userRepository.save(u);

    }


    public void deleteUser(Integer id){

        User u = userRepository.findUserById(id);

        if(u == null)
            throw new ApiException("User not found!");

        userRepository.delete(u);
    }
   //End CRUD

    public void checkUsernameAndPass(String username, String password){

        User u = userRepository.getByUserName(username);

        if(u == null)
            throw new ApiException(("Invalid username!"));

        if(!u.getPassword().equals(password))
            throw new ApiException(("Invalid password!"));

        userRepository.save(u);

    }


    public User getByEmail(String email){

        User u = userRepository.findUserByEmail(email);

        if(u == null)
            throw new ApiException(("User with this email not found!"));

        return u;

    }

    public List<User> getByRole(String role){

        List<User> users = userRepository.getByRole(role);

        if(users.isEmpty())
            throw new ApiException(("No user in this role"));

        return users;
    }

    public List<User> getByAgeAndAbove(Integer age){

        List<User> users = userRepository.getByAgeAndAbove(age);

        if(users.isEmpty())
            throw new ApiException(("No user in this specific age range!"));

        return users;

    }



}
