# @PropertySource: Loading Your App's "Secret Settings"* ⚙️

Mawa, manam ippati varaku configuration values ni direct ga code lo or `@Value` lo hardcode chesam. Idi chala bad practice. ❌ Database password lanti sensitive information ni code lo pettakudadu.

The correct way is to keep these values in external `.properties` files. Mari, aa files ni Spring ki ela chupinchali? How do we make Spring read them?

Daaniki answer eh **`@PropertySource`**.

\* **(Extremely important. All real-world applications use external properties. This is a must-know topic.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-property-source](https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-property-source)

### The Restaurant Menu Analogy 📜
Imagine you are a Chef (`Your Bean`) in a restaurant.
-   **Bad Way (Hardcoding):** You memorize all the prices for all the dishes. Okavela manager prices maristhe, nuvvu malli antha gurtu pettukovali. Chala kashtam.
-   **Good Way (`@PropertySource`):** The manager puts all the prices on a Menu (`app.properties` file). Nuvvu chef ga, aa menu ni matrame chustav.
    -   **`@PropertySource("classpath:menu.properties")`**: This tells you, the chef, where the menu is located.
    -   **`@Value("${dish.pizza.price}")`**: This is you looking at the menu and finding the price for "pizza".

`@PropertySource` loads all the key-value pairs from a file into the Spring `Environment`. From there, `@Value` can easily access them.

```mermaid
graph TD
    A["app.properties<br/>app.name=Mawa App<br/>app.version=1.0"];

    subgraph "Spring Configuration"
        B["@Configuration<br/>@PropertySource(\"classpath:app.properties\")"];
    end

    subgraph "Spring's Internal Memory"
        C{Environment};
    end

    subgraph "Your Bean"
        D["@Component<br/>@Value(\"${app.name}\")<br/>private String appName;"];
    end

    A -- "is loaded by" --> B;
    B -- "populates" --> C;
    C -- "injects value into" --> D;
```

### How to Use `@PropertySource`
It's very simple. You just add this annotation to any of your `@Configuration` classes.
`@PropertySource("classpath:my-app.properties")`

You can even load multiple files:
`@PropertySource({"classpath:app.properties", "classpath:db.properties"})`

And you can use prefixes like `classpath:` or `file:` to specify the location.

---
### Code Reference: Reading the App's Info
The code for this is in the `io.mawa.spring.core.env.propertysource` package.

1.  **`app-info.properties`**: Our custom properties file in `src/main/resources`.
2.  **`AppInfo.java`**: The `@Component` that injects the properties by using `@Value("${...}")`.
3.  **`PropertySourceConfig.java`**: The `@Configuration` class that uses `@PropertySource("classpath:app-info.properties")` to load our file.
4.  **`PropertySourceApp.java`**: The main application to run the demo.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.env.propertysource.PropertySourceApp"
```
**Expected Output:**
```
--- Starting the Spring Container ---
--- Container started successfully! ---
--- Application Information ---
Name: Mawa Spring Guide
Version: 2.0
Author: Jules
```
Chusava! Mana `AppInfo` bean, `app-info.properties` file lo unna values ni successful ga load cheskuni, print chesindi. And it was all configured with just two annotations: `@PropertySource` and `@Value`.

With this and `@Profile`, you have now mastered the two most important tools for environment-specific configuration in Spring. Excellent work, Mawa! 🎉 What's the next topic on our list?
