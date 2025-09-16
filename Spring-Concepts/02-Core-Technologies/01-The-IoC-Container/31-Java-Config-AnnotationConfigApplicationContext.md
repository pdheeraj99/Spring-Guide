# AnnotationConfigApplicationContext: The Modern Ignition Key* 🚀

Mawa, manam ippati varaku Spring container ni start cheyadaniki, eppudu oke line rastunnam: `new AnnotationConfigApplicationContext(...)`.

Idi asalu enti? Idi Spring container ki oka modern, Java-config-friendly entry point. Paatha rojullo `ClassPathXmlApplicationContext` undedi, ippudu manam deenni ekkuva vadatam.

\* **(Important. This is the standard entry point for standalone Spring applications that use Java-based configuration.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/java/instantiating-container.html](https://docs.spring.io/spring-framework/reference/core/beans/java/instantiating-container.html)

### The Car Ignition Analogy 🚗🔑
Imagine mana `@Configuration` classes anevi car engine, transmission, antha.
-   **The Car:** The fully assembled, ready-to-run Spring application.
-   **`AnnotationConfigApplicationContext`:** The ignition system.

Car ni start cheyadaniki different ways unnayi:
1.  **Simple Key Turn (Constructor):** `new AnnotationConfigApplicationContext(AppConfig.class)`. Key petti tippadam antha simple.
2.  **Push-Button Start (`register()`):** `context.register(AppConfig.class); context.refresh();`. First button press chesi electronics on chesi (`register`), tarvata engine start (`refresh`) cheyadam lantiది. This gives you a chance to do something in between.
3.  **Remote Start (`scan()`):** `context.scan("io.mawa"); context.refresh();`. Direct ga key or button kakunda, remote tho "aa area lo unna car ni start chey" ani cheppadam lantiది.

### The Three Ways to Instantiate
Let's look at the three main ways to use `AnnotationConfigApplicationContext`.

```mermaid
graph TD
    subgraph "Your Configuration"
        A["AppConfig.class<br/>(@Configuration)"];
        B["MyBean.class<br/>(@Component)"];
    end

    subgraph "Ignition System: AnnotationConfigApplicationContext"
        C{Context};
    end

    subgraph "Three Ways to Start"
        way1["1. Pass to Constructor<br/>`new A...(AppConfig.class)`"];
        way2["2. Register Programmatically<br/>`context.register(AppConfig.class)`<br/>`context.refresh()`"];
        way3["3. Scan Programmatically<br/>`context.scan(\"io.mawa\")`<br/>`context.refresh()`"];
    end

    A --> way1;
    A --> way2;
    B --> way3;

    way1 --> C;
    way2 --> C;
    way3 --> C;

    style C fill:#f9f,stroke:#333,stroke-width:2px
```

1.  **Constructor (The Easiest):** `new AnnotationConfigApplicationContext(AppConfig.class, AnotherConfig.class, ...)`
    -   Manam ekkuva use chesedi ide. Simple and direct.

2.  **`register()` and `refresh()` (The Flexible):**
    ```java
    var context = new AnnotationConfigApplicationContext();
    context.register(AppConfig.class);
    // You can do other things here before starting
    context.refresh(); // The container actually starts here
    ```
    -   Idi manaki konchem control istundi. `refresh()` call chese mundu, manam context tho vere panulu cheyochu.

3.  **`scan()` and `refresh()` (The Dynamic):**
    ```java
    var context = new AnnotationConfigApplicationContext();
    context.scan("io.mawa.spring.core"); // No need for @ComponentScan
    context.refresh();
    ```
    -   Idi `@ComponentScan` ki programmatic alternative. Ee approach lo, manaki `@Configuration` class kuda avasaram ledu!

---
### Code Reference: Starting the Engine
The code for this is in the `io.mawa.spring.core.javaconfig.instantiation` package.

1.  **`MyBean.java`**: A simple `@Component` that will be discovered by our context.
2.  **`AppConfig.java`**: A `@Configuration` class with `@ComponentScan` enabled.
3.  **`InstantiationDemoApp.java`**: The main application that demonstrates starting the context in all three ways.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.javaconfig.instantiation.InstantiationDemoApp"
```
**Expected Output:**
```
--- DEMO 1: Using the constructor with a @Configuration class ---
Context 1 contains MyBean: true

--- DEMO 2: Using register() and refresh() ---
Context 2 contains MyBean: true

--- DEMO 3: Using scan() and refresh() ---
Context 3 contains MyBean: true
```
Chusava! Moondu rakalu ga start chesina, manaki కావలసిన bean anedi container lo create aindi. This shows the flexibility of `AnnotationConfigApplicationContext`.

Next, let's dive even deeper into the `@Bean` annotation itself and see what other powers it holds. Ready? 💪
