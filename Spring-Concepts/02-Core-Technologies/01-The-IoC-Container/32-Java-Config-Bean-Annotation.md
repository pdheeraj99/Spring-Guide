# The @Bean Annotation: More Than Just a Method* 🧰

Mawa, manam `@Bean` annotation ni "oka bean ni create chey" ani Spring ki cheppadaniki vadatam. But it's much more than that. It's a powerful tool with several attributes that let us customize our beans precisely.

\* **(Important. Knowing these attributes is key for managing beans from third-party libraries or legacy code where you can't change the source.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html](https://docs.spring.io/spring-framework/reference/core/beans/java/bean-annotation.html)

### The Custom Car Order Form Analogy 📝
Imagine a `@Bean` method is a car order form.
-   **Simple Form:** `public Car car() { return new Car(); }`. This is like ordering the base model car.
-   **Custom Form (`@Bean` attributes):** This is like filling out the options on the form:
    -   `name = "mawaRacer"`: "I want the car's nameplate to be 'mawaRacer'."
    -   `initMethod = "startEngine"`: "When the car is delivered (initialized), run the 'startEngine' sequence."
    -   `destroyMethod = "shutdownSystem"`: "When the car is scrapped (destroyed), run the 'shutdownSystem' sequence."

### Key Attributes of `@Bean`

```mermaid
graph TD
    A["@Bean Annotation"] --> B["name/value"];
    A --> C["initMethod"];
    A --> D["destroyMethod"];
    A --> E["autowire (legacy)"];

    subgraph B_Desc
        direction LR
        B1["Give one or more custom names to the bean.<br/>e.g., @Bean({\"bean1\", \"beanOne\"})"];
    end

    subgraph C_Desc
        direction LR
        C1["Specify a custom initialization method.<br/>Alternative to @PostConstruct."];
    end

    subgraph D_Desc
        direction LR
        D1["Specify a custom destruction method.<br/>Alternative to @PreDestroy."];
    end

    subgraph E_Desc
        direction LR
        E1["Legacy autowiring mode.<br/>Not recommended now."];
    end

    B --> B_Desc;
    C --> C_Desc;
    D --> D_Desc;
    E --> E_Desc;
```

#### 1. `name` (or `value`)
By default, the bean's name is the method name. But you can give it a custom name, or even multiple names (aliases).
`@Bean(name = "myCustomName")` or `@Bean({"car", "myCar", "bestCar"})`.

#### 2. `initMethod`
This is a very useful attribute. It tells Spring to call a specific method on your bean right after it has been constructed and all dependencies are set. This is the **third** way to handle initialization logic!
1.  `@PostConstruct` (Modern, standard way)
2.  `InitializingBean` interface (Old, couples code to Spring)
3.  **`@Bean(initMethod = "...")`** (Best for when you can't modify the bean's source code, e.g., it's from a library).

#### 3. `destroyMethod`
Similarly, this tells Spring to call a specific method when the container is shutting down and the bean is being destroyed. This is the **third** way to handle destruction logic.
- **Important Note:** Spring can only manage destroy methods for singleton beans. For other scopes like `prototype`, it's the client's responsibility to call the destroy method. Also, Spring automatically infers destroy methods for beans that implement `java.lang.AutoCloseable` or `java.io.Closeable`. You can disable this with `@Bean(destroyMethod = "")`.

---
### Code Reference: The Custom-Ordered Bean
The code for this is in the `io.mawa.spring.core.javaconfig.bean` package.

1.  **`CustomBean.java`**: A simple POJO with `customInit()` and `customDestroy()` methods, but no Spring annotations.
2.  **`BeanDetailsConfig.java`**: Our `@Configuration` class that uses `@Bean(name = "mawaRacer", initMethod = "customInit", destroyMethod = "customDestroy")` to configure the bean.
3.  **`BeanDetailsApp.java`**: The main application that starts the context, retrieves the bean, and closes the context.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.javaconfig.bean.BeanDetailsApp"
```
**Expected Output:**
```
--- Starting the Spring Container ---
CustomBean: Constructor called.
CustomBean: The custom init-method was called!

--- Container started. Init method should have been called. ---

--- Retrieving bean by its custom name 'mawaRacer' ---
CustomBean: Doing my work...

--- Closing the container to trigger destroy method... ---
CustomBean: The custom destroy-method was called!
```
Chusava! Manam bean class ni touch cheyakunda ne, `@Bean` annotation attributes tho daani name and lifecycle methods ni control chesukunnam. This is super useful for third-party libraries!

Ready for the next topic? Let's look at how to combine multiple configuration classes together. 💪
