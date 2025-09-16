# 02. Java Bean Validation (The Universal Standard)

Mawa, last time we discussed needing a bouncer for our application. Now, the question is, what kind of rules should our bouncer follow? Luckily, there's a universal standard that almost everyone in the Java world agrees on. Welcome to Java Bean Validation!

## The Traffic Signs Analogy 🚦

Imagine you're driving in a new country. You might not speak the language, but you still understand what a red octagonal "STOP" sign means. Why? Because it's a universal standard.

**Java Bean Validation annotations (`@NotNull`, `@Size`, etc.) are the traffic signs of programming.**

*   They are part of a standard specification (JSR-380).
*   You can use them in any modern Java framework (Spring, Quarkus, etc.), and they will work the same way.
*   This makes your code more portable and easier for other developers to understand. Spring integrates with this standard seamlessly.

## The "Must-Know" Annotations

For 90% of your validation needs, these standard annotations are all you need. Let's look at the most important ones.

*   `@NotNull`: The field cannot be null. It can be empty, though!
    *   `@NotNull String name;` // `name = null` is invalid, but `name = ""` is valid.
*   `@NotEmpty`: The field cannot be null AND its size/length must be greater than 0.
    *   `@NotEmpty String name;` // `name = null` and `name = ""` are both invalid.
*   `@NotBlank`: The field cannot be null AND must contain at least one non-whitespace character. This is often the most useful one for Strings.
    *   `@NotBlank String name;` // `null`, `""`, and `"   "` are all invalid.
*   `@Size(min=, max=)`: Checks if a String, Collection, Map, or array's size is within a certain range.
    *   `@Size(min = 2, max = 30) String username;`
*   `@Min(value)`: For numbers. The value must be greater than or equal to the specified minimum.
    *   `@Min(18) int age;`
*   `@Max(value)`: For numbers. The value must be less than or equal to the specified maximum.
    *   `@Max(100) int level;`
*   `@Email`: Checks if a String is a well-formed email address.
    *   `@Email String userEmail;`
*   `@Pattern(regexp=)`: For when you need the ultimate power of a regular expression.
    *   `@Pattern(regexp = "[a-zA-Z0-9]+") String username;`

## Example in Action 🎬

Here is a quick look at how these annotations come together in a simple `User` class. Notice how we can add a custom `message` to each annotation.

```java
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

    // Constructors, Getters, and Setters...
}
```
In our code demo, we'll create a program that actually validates this object and prints the error messages!

## How it Works: A Visual

Here’s a simple diagram showing how we apply these annotations to a bean. The validator then checks the bean against these rules.

```mermaid
graph TD
    subgraph Your Code
        A[User Bean] -- Has Rules --> B{Annotations};
        B -- `@NotBlank` --> C[username];
        B -- `@Email` --> D[email];
        B -- `@Min(18)` --> E[age];
    end

    subgraph Validation Engine
        F(Validator)
    end

    A -- "Let's check this object" --> F;
    F -- "Reads rules" --> B;
    F -- "Checks values" --> A;
    F -- "Valid or Invalid?" --> G[Result: Errors!];

```

## The Dependency You Need 📦

To use these annotations, you need to add the `spring-boot-starter-validation` dependency to your `pom.xml`. This brings in all the necessary libraries, including Hibernate Validator, which is the reference implementation of the Bean Validation spec.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

***

### Mawa's Cliffhanger 🧗

These standard annotations are amazing for checking single fields. But what if you have a rule that involves *multiple* fields? For example, "The 'confirmPassword' field must match the 'password' field." 🤷‍♂️ A simple annotation can't do that. For that, we need to call in a specialist. In our next topic, we'll learn how to write our own custom detective with Spring's `Validator` interface!
