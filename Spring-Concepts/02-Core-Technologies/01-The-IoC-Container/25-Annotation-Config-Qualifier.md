# @Qualifier: "I Want THAT One Specifically!"* 🎯

Mawa, manam last time `@Primary` gurinchi nerchukunnam. Adi ambiguity unnapudu, oka "default" or "favorite" bean ni select chestundi.

Kani, okavela manaki aa default వద్దు, specific ga vere option kavali anukunte? Appude manam mana next weapon, `@Qualifier`, ni vadatam.

\* **(Very Important for interviews and daily work)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired-qualifiers.html](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired-qualifiers.html)

### The Ice Cream Shop Analogy - Part 2 🍦
Let's go back to our ice cream shop (`Spring Container`).
-   `IceCream` bean ki `@Primary` sticker undi.
-   `Cake` bean ki emi ledu.

**Scenario 1 (The Default):**
-   **You:** "Naaku oka dessert kavali." (`@Autowired Dessert dessert;`)
-   **Shopkeeper (Spring):** "Default ga Ice Cream istam." -> You get `IceCream`.

**Scenario 2 (The Specific Choice):**
-   **You:** "Naaku aa *cake* kavali." (You point specifically at the cake).
    (`@Autowired @Qualifier("cakeBean") Dessert dessert;`)
-   **Shopkeeper (Spring):** "Oh, meeku specific ga cake kavala? Okay!" -> You get `Cake`, even though `IceCream` was the primary.

`@Qualifier` ante ide. It lets you be specific and override the default choice by matching a name.

```mermaid
graph TD
    subgraph "Your Code"
        A["@Autowired<br/>@Qualifier(\"cakeQualifier\")<br/>Dessert dessert;"];
    end

    subgraph "Spring Container"
        B["IceCream Bean<br/>(⭐ @Primary)"];
        C["Cake Bean<br/>(@Qualifier(\"cakeQualifier\"))"];

        B -- implements --> I(Dessert);
        C -- implements --> I;
    end

    A -- asks for --> I;
    I -- "resolves to a specific qualifier" --> C;

    style B fill:#ccf,stroke:#333
    style C fill:#f9f,stroke:#333
```

### How to Use `@Qualifier`
It's a two-step process:
1.  **Label the Bean:** Nuvvu ye bean ni specific ga identify cheyali anukuntunnavo, daaniki `@Qualifier("someUniqueName")` ane label veyyi. Ee label ni nuvvu `@Component` tho paatu class meeda or `@Bean` method meeda pettachu.
2.  **Ask for the Labeled Bean:** Injection point lo, `@Autowired` tho paatu, `@Qualifier("someUniqueName")` use chesi, aa specific labeled bean ni adugu.

Spring will then match the qualifier names and inject the correct bean.

### `@Primary` vs. `@Qualifier`: The Final Showdown
| Feature         | `@Primary` ⭐                               | `@Qualifier` 🎯                             |
| --------------- | ------------------------------------------- | ------------------------------------------- |
| **Purpose**     | Sets the **default** choice.                | Selects a **specific** choice.              |
| **How it works**| Marks one bean out of many as the favorite. | Gives a unique name (label) to a bean.      |
| **At Injection**| Just use `@Autowired`.                      | Use `@Autowired` + `@Qualifier("name")`.    |
| **Flexibility** | Less flexible. Only one primary per type.   | More flexible. You can have many qualifiers. |
| **Winner?**     | Both are winners! Use `@Primary` for the most common, sensible default. Use `@Qualifier` when you need to specifically choose a non-default option. They can even be used together! |

---
### Code Reference: Hot and Cold Desserts
The code for this is in the `io.mawa.spring.core.annotationconfig.qualifier` package.

1.  **`Dessert.java`**: Our simple interface.
2.  **`HotDessert.java`**: An implementation with `@Qualifier("hot")`.
3.  **`ColdDessert.java`**: An implementation with `@Qualifier("cold")` and `@Primary`.
4.  **`DessertService.java`**: This service autowires `Dessert` three times, using no qualifier, `@Qualifier("hot")`, and `@Qualifier("cold")` to demonstrate the different outcomes.
5.  **`QualifierConfig.java`**: A configuration class that just enables component scanning.
6.  **`QualifierApp.java`**: The main application to run the demo.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.annotationconfig.qualifier.QualifierApp"
```
**Expected Output:**
```
--- Starting the Spring Container ---
--- Container started successfully! ---
--- Dessert Service ---
Default Dessert (using @Primary): Cold IceCream 🍦
Requested Hot Dessert (using @Qualifier('hot')): Hot Brownie 🍫
Requested Cold Dessert (using @Qualifier('cold')): Cold IceCream 🍦
```
Chusava! Just `@Autowired` adiginappudu, primary aina `ColdDessert` vachindi. Kani, manam specific ga `@Qualifier("hot")` adiginappudu, adi primary ni ignore chesi, correct ga `HotDessert` ni a inject chesindi. That's the power of `@Qualifier`!

You have now mastered the two most important tools for resolving autowiring ambiguity. Great job, Mawa! What's next? 💪
