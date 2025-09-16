package io.mawa.spring.core.validation.beanvalidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Username cannot be blank!")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters.")
    private String username;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @Min(value = 18, message = "User must be at least 18 years old.")
    private int age;

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
