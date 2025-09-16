# Resources as Dependencies: "Container, Give Me That File!" 📄

Mawa, manam ippati varaku `ResourceLoader` ni adigi, resources ni teeskunnam. Kani, adi konchem extra work. What if manam direct ga mana bean ki chepochu, "Naaku ee specific file kavali, nuvve a ব্যবস্থা chudu"?

Welcome to the easiest and most powerful way to handle resources: **Injecting `Resource` objects as dependencies**.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/resources.html#resources-as-dependencies](https://docs.spring.io/spring-framework/reference/core/resources.html#resources-as-dependencies)

### The Magic of `@Value`
Ee magic antha `@Value` annotation venaka undi. Manam `String`s and `int`s ni inject cheyadaniki `@Value` vadinatte, `Resource` objects ni kuda inject cheyochu.

How does it work?
Spring has a built-in "converter". Nuvvu `@Value("classpath:my-file.txt")` ani oka `String` isthe, Spring automatic ga aa string ni chusi, correct `Resource` implementation (`ClassPathResource`) ni create chesi, neeku inject chestundi. It's that simple!

```java
@Component
public class MyBean {

    // Spring converts the String path into a Resource object automatically!
    @Value("classpath:my-resource.txt")
    private Resource myTemplate;

    // ...
}
```

**The Flow:**
```mermaid
graph TD
    subgraph "Your Code"
        A["@Value(\"classpath:my-file.txt\")<br/>private Resource template;"];
    end

    subgraph "Spring Container's Brain 🧠"
        B["Sees String path"];
        C["Finds correct Resource implementation<br/>(e.g., ClassPathResource)"];
        D["Creates Resource object"];
        B --> C --> D;
    end

    subgraph "Your Bean Instance"
        E["myBean.template"];
    end

    D -- "Injects into" --> E;
```

### Injecting a Whole Bunch of Files!
Ee magic ikkaditho aagadu. Manam `ResourcePatternResolver` gurinchi nerchukunnam kada? Aa power ni kuda `@Value` tho use cheyochu!

Nuvvu `Resource[]` (an array of Resources) field meeda `@Value` pedithe, Spring automatic ga pattern ni resolve chesi, match ayina anni files ni oka array la inject chestundi.

```java
@Component
public class MyBean {

    // Spring finds all matching files and injects them as an array!
    @Value("classpath:config/**/*.xml")
    private Resource[] allConfigFiles;

    // ...
}
```

This is incredibly useful for loading all configuration files or all templates from a specific directory.

---
### Code Reference: The Ultimate Resource Injection
The code for this is in the `io.mawa.spring.core.resources.dependencies` package.

1.  **`application.properties`**: In `src/main/resources`, we have defined our resource paths as properties.
2.  **`ResourceDependencyBean.java`**: This `@Component` uses `@Value("${...}")` to inject both a single `Resource` and an array `Resource[]` from the properties file. It prints the details in a `@PostConstruct` method.
3.  **`ResourceDependencyApp.java`**: The main `@SpringBootApplication` class that launches our demo.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.resources.dependencies.ResourceDependencyApp"
```
**Expected Output:**
```
--- Injected Single Resource ---
Template resource: my-resource.txt
Template exists: true

--- Injected Multiple Resources ---
Found 2 XML files:
 - service-a.xml
 - service-b.xml
```
Chusava! Manam just `@Value` use chesam. Spring eh properties file nunchi path ni teeskuni, aa path ni `Resource` objects ga convert chesi, manaki inject chesindi. Even pattern matching kuda automatic ga handle chesindi. This is the power and convenience of Spring!

Mawa, with this, we have officially completed the **Core Technologies -> Resources** chapter. You now have a solid understanding of how Spring handles files and resources in a unified way. Another big milestone! Congratulations! 🥳🎉

Ready for the next chapter? Let me know! 💪
