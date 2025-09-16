# Java Config: The "Official Blueprint" for Your Beans* 📜

Mawa, manam component scanning gurinchi nerchukunnam. Adi chala convenient. Kani, konni sarlu, manaki beans meeda full control kavali. For example, third-party library nunchi vachina class ki manam `@Component` pettalem. Alaantappudu, or complex creation logic unnapudu, manam **Java-based Configuration** vadatam.

This is the explicit way of defining beans, using `@Configuration` classes and `@Bean` methods.

\* **(Extremely important. This is the primary way of configuring Spring in most applications, alongside component scanning.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/java/basic-concepts.html](https://docs.spring.io/spring-framework/reference/core/beans/java/basic-concepts.html)

### The LEGO Instruction Manual Analogy 🧱
Imagine you are building a LEGO set.
-   **`@Configuration` class:** The entire instruction manual.
-   **`@Bean` method:** A specific step in the manual, like "Step 5: Build the engine." The object you build in that step (`Engine` object) is the bean. The name of the step (`engine()`) is the bean's ID.

### The Two Core Annotations
1.  **`@Bean`**: Idi oka method-level annotation. Nuvvu oka method meeda idi pedithe, nuvvu Spring ki cheptunnav: "Ee method return chese object ni 'bean' ga theesko, and container lo register chey." By default, the method name becomes the bean's name.
2.  **`@Configuration`**: Idi oka class-level annotation. Idi normal `@Component` kanna special. It tells Spring that this class is not just a regular bean, but a source of *other* bean definitions.

### The REAL Magic: `@Configuration` (Full) vs. "Lite" Mode
Mawa, idi chala important and tricky concept. Jagrattha.
-   **Full Mode (`@Configuration`):** Nuvvu oka class meeda `@Configuration` pedithe, Spring aa class ki CGLIB ane library use chesi, oka **proxy subclass** ni create chestundi.
-   **Lite Mode (`@Component` or no annotation):** Oka class meeda just `@Bean` methods unte, kani `@Configuration` lekunte, adi "lite" mode lo pani chestundi. No proxy is created.

**So what? What's the difference?**
The difference is how **inter-bean dependency calls** are handled.

```mermaid
graph TD
    subgraph "Full Mode: @Configuration (The Smart Way ✅)"
        A["@Configuration class (Proxied by Spring)"];
        B["@Bean Car car() {<br>  return new Car(engine()); // Calling another @Bean method<br>}"];
        C["@Bean Engine engine() {<br>  return new Engine();<br>}"];

        A --> B;
        A --> C;

        B -- "calls engine()" --> D{CGLIB Proxy};
        D -- "intercepts call" --> E{Container};
        E -- "returns existing singleton engine" --> D;
        D -- "returns engine" --> B;

        note "Result: Car gets the SAME shared engine instance."
    end

    subgraph "Lite Mode: @Component (The Naive Way ⚠️)"
        F["@Component class (Plain Java Object)"];
        G["@Bean Car car() {<br>  return new Car(engine());<br>}"];
        H["@Bean Engine engine() {<br>  return new Engine();<br>}"];

        F --> G;
        F --> H;

        G -- "calls engine()" --> H;
        H -- "is a normal method call" --> I["new Engine() is called AGAIN"];

        note "Result: Car gets a NEW, different engine instance.<br/>This is usually a bug!"
    end
```

**The Golden Rule:** Always use `@Configuration` on classes that contain `@Bean` methods, especially if those beans depend on each other. This guarantees correct singleton behavior.

---
### Code Reference: The CGLIB Magic Show
The code for this is in the `io.mawa.spring.core.javaconfig.basics` package.

1.  **`Engine.java`**, **`Car.java`**: Our simple POJOs.
2.  **`AppConfig.java`**: The `@Configuration` class where the `car()` bean method calls the `engine()` method directly.
3.  **`JavaConfigApp.java`**: The main application that starts the context and compares the hash codes to prove the proxy worked.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.javaconfig.basics.JavaConfigApp"
```
**Expected Output:**
```
--- Starting the Spring Container ---
Engine constructor called. HashCode: 123456789
Car constructor called with Engine: 123456789
--- Container started successfully! ---

--- Retrieving beans ---

--- Verifying Hashes ---
Engine instance from Car:      123456789
Engine instance from Context:  123456789

Success! The Car has the same singleton Engine instance from the context. The @Configuration proxy worked! ✅
```
Chusava! `car()` method lo manam `engine()` ni call chesina, kotha `Engine` create avvaledu. Spring proxy aa call ni intercept chesi, already container lo unna singleton `engine` bean ni return chesindi. That is the magic of `@Configuration`!

Ready to continue our deep dive into Java Config? Let's go! 💪
