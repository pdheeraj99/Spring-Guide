# JSR-330 Annotations: The "Java Standard" Way* 💉

Mawa, manam ippati varaku Spring-specific annotations (`@Autowired`, `@Qualifier`) gurinchi chala nerchukunnam. Kani, Java community lo, dependency injection kosam oka standard create chesaru. Adi JSR-330. Ee standard lo konni annotations unnayi, and Spring vaatini kuda fully support chestundi.

\* **(Important for interviews to show you know industry standards, not just one framework.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/standard-annotations.html](https://docs.spring.io/spring-framework/reference/core/beans/standard-annotations.html)

### The International Power Adapter Analogy 🔌
Imagine nuvvu oka world tour ki velthunnav.
-   **`@Autowired`:** Idi mana Indian 3-pin plug lantiది. India (Spring projects) lo perfect ga pani chestundi.
-   **`@Inject` (JSR-330):** Idi oka **Universal Travel Adapter**. Idi India lo pani chestundi, US lo pani chestundi, Europe lo pani chestundi (ante, Spring lo, Google Guice lo, or vere ye DI framework lo ayina pani chestundi).

Using JSR-330 makes your code less coupled to Spring and more portable.

### The Standard Annotations
Rendu main annotations unnayi:
1.  **`@Inject`**: Idi Spring's `@Autowired` ki almost identical alternative. It's the main injection annotation.
2.  **`@Named`**: Idi Spring's `@Qualifier` ki standard alternative. Idi bean ki oka unique name istundi, so manam ambiguity ni resolve cheyochu.

### `@Autowired` vs. `@Inject`: The Final Comparison

| Feature                 | `@Autowired` (Spring)                       | `@Inject` (JSR-330 Standard)                |
| ----------------------- | ------------------------------------------- | ------------------------------------------- |
| **`required` attribute?** | Yes, `@Autowired(required=false)` is possible. | **No.** `@Inject` has no `required` attribute. It's always mandatory. |
| **Provider?**           | Can inject Spring's `ObjectProvider`.       | Can inject the standard `Provider` interface. |
| **Origin**              | Spring Framework.                           | Standard Java (`jakarta.inject` package).   |

The biggest difference is the `required` attribute. With `@Inject`, the dependency must always be present.

### `@Qualifier` vs. `@Named`
Veetini kuda compare cheddam.
-   `@Qualifier("myBean")` anedi Spring-specific way to label a bean.
-   `@Named("myBean")` anedi standard way to label a bean.

Rendu oke pani chestayi. At the injection point:
-   `@Autowired @Qualifier("myBean")` is the Spring way.
-   `@Inject @Named("myBean")` is the standard way.

---
### Code Reference: The Standard Notification System
The code for this is in the `io.mawa.spring.core.jsr330` package.

1.  **`pom.xml`**: We have added the `jakarta.inject-api` dependency.
2.  **`MessageService.java`**: Our simple interface.
3.  **`EmailService.java`** and **`SmsService.java`**: Two implementations, labeled with `@Named("emailSvc")` and `@Named("smsSvc")` respectively.
4.  **`NotificationService.java`**: This service uses `@Inject` and `@Named("smsSvc")` to get the specific SMS service.
5.  **`Jsr330Config.java`**: A configuration class that just enables component scanning.
6.  **`Jsr330App.java`**: The main application to run the demo.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.jsr330.Jsr330App"
```
**Expected Output:**
```
--- Starting the Spring Container ---
--- Container started successfully! ---
--- Notification Service ---
Sending SMS: 'Your order has been shipped!' 📱
```
Chusava! Mana code ippudu Spring-specific annotations meeda depend avvatledu, kani antha perfect ga pani chestondi. This makes our code more portable.

You've now completed a full tour of annotation-based dependency injection, including Spring's way and the standard way. This is a huge topic, and you've nailed it. Congratulations! What's next on our list of missed topics? 🤔
