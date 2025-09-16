# MessageSource: Speaking Your User's Language* 🌍

Mawa, manam ippati varaku application lo messages ni direct ga code lo or properties file lo English lo pettam. Kani, mana app ni France lo unna vallu, or Japan lo unna vallu vadali anukunte? Vallaki valla language lo messages chupinchali kada?

Ee process ni **Internationalization (i18n)** antaru. Spring ee pani ni chala easy chestundi, using the `MessageSource` interface.

\* **(Important for any application that needs to support multiple languages.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-functionality-messagesource](https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-functionality-messagesource)

### The Multilingual Restaurant Menu Analogy 🌐
Imagine you have a restaurant that is popular with tourists.
-   **The Keys:** The dishes on your menu have codes: `dish.curry`, `dish.pizza`.
-   **The Menus (`.properties` files):** You have multiple versions of your menu:
    -   `menu_en.properties`: `dish.curry=Spicy Chicken Curry`
    -   `menu_fr.properties`: `dish.curry=Curry de Poulet Épicé`
    -   `menu_te.properties`: `dish.curry=మసాలా చికెన్ కూర`
-   **The Host (`MessageSource`):** When a customer walks in, the host checks their language preference (`Locale`).
-   **The Result:** If the customer is French, the host gives them the French menu. If they are Telugu, the host gives them the Telugu menu.

The `ApplicationContext` itself can act as this host because it extends the `MessageSource` interface. You just need to configure it with the location of your menu files.

```mermaid
graph TD
    subgraph "Your Message Files (Menus)"
        A["messages_en.properties<br/>greeting=Hello"];
        B["messages_fr.properties<br/>greeting=Bonjour"];
        C["messages_te.properties<br/>greeting=నమస్కారం"];
    end

    subgraph "Your Code"
        D["messageSource.getMessage(\"greeting\", null, Locale.FRENCH)"];
    end

    subgraph "Spring MessageSource (The Host)"
        E{MessageSource};
    end

    D -- asks --> E;
    E -- "sees Locale.FRENCH" --> B;
    B -- "finds value" --> E;
    E -- "returns 'Bonjour'" --> D;
```

### How to Use `MessageSource`
1.  **Create Properties Files:** Create your message files with a common base name, followed by `_` and the language/country code (e.g., `messages_en_US.properties`, `messages_fr.properties`).
2.  **Configure the `MessageSource` Bean:** Create a bean of type `ResourceBundleMessageSource` and tell it the base name of your files.
3.  **Use it:** Get the `MessageSource` (or the `ApplicationContext`) and call the `getMessage(String code, Object[] args, Locale locale)` method.

---
### Code Reference: The Multilingual App
The code for this is in the `io.mawa.spring.core.context.i18n` package.

1.  **Properties Files**: I have created `messages.properties` (default), `messages_fr.properties`, and `messages_te.properties` in `src/main/resources`.
2.  **`I18nConfig.java`**: This class configures the `ResourceBundleMessageSource` bean and tells it to use "messages" as the base name.
3.  **`I18nDemoApp.java`**: The main application that starts the context and uses the `MessageSource` to fetch greetings for different `Locale`s.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.context.i18n.I18nDemoApp"
```
**Expected Output:**
```
--- Starting the Spring Container ---
--- Container started successfully! ---

--- Retrieving messages for different languages ---
In English: Hello, Mawa!
In French: Bonjour, Mawa!
In Telugu: నమస్కారం, Mawa!
```
Chusava! Oke message key (`greeting`) use chesi, manam different `Locale`s pass cheyadam valla, Spring automatic ga correct properties file nunchi correct message ni thechi ichindi. That's how you build world-class applications!

Next up, we'll explore another powerful capability of the `ApplicationContext`: Application Events. Ready? 🔥
