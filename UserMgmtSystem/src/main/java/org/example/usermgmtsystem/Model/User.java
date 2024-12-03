package org.example.usermgmtsystem.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Check(constraints = "role= 'user' or role= 'admin'")
public class User {

    @Id
    private Integer id;

    @NotEmpty(message = "Name can't be empty!")
    @Size(min= 5, message = "Name length must be more than 4 characters!")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @NotEmpty(message = "Username can't be empty!")
    @Size(min= 5, message = "Username length must be more than 4 characters!")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "Password can't be empty!")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @NotEmpty(message = "Email can't be empty!")
    @Email(message = "Email must be at a valid email format!")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotEmpty(message = "Role can't be empty!")
    @Pattern(regexp = "^(user|admin)$", message = "Role must be either \"user\" or \"admin\" only!")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @Positive(message = "Age must be an integer!")
    @Column(columnDefinition = "int not null")
    private Integer age;





}
