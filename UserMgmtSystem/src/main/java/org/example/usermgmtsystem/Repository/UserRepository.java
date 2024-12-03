package org.example.usermgmtsystem.Repository;

import org.example.usermgmtsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findUserById (Integer id);  //PK


    @Query("select u from User u where u.username=?1")
    User getByUserName(String username);  //Return type is obj because the username is unique

    User findUserByEmail(String email); //Return type is obj because the email is unique

    @Query("select u from User u where u.role= ?1")
    List<User> getByRole(String role);

    @Query("select u from User u where u.age>=?1")
    List<User> getByAgeAndAbove(Integer age);

}
