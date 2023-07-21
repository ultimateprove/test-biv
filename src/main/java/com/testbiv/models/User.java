package com.testbiv.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User{

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Имя должно содержать только буквы")
    private String name;

    @Column(unique = true)
    @NotEmpty(message = "Электронная почта не может быть пустой")
    @Email
    private String email;

    @Column
    @Size(min = 8, message = "Пароль должен содержать не менее {min} символов")
    @NotEmpty(message = "Пароль не может быть пустым")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Пароль должен содержать хотя бы одну букву верхнего регистра, одну букву нижнего регистра и одну цифру")
    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
