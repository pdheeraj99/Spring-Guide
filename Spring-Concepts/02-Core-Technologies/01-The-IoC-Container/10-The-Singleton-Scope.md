# Singleton Scope: The "Okate Piece, Master Piece!" 😎👑

Mawa, welcome to the world of **Bean Scopes**! Idi Spring lo oka fundamental kani chala important topic. Okko bean ela puttali, entha kalam bratakali anedi ee scopes eh decide chestayi.

Andulo mana first hero, the one and only, the default king: **Singleton Scope**.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-singleton](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-singleton)

### What is Singleton Scope?
Perulo ne undi kada, "Single" ani. Ante, oka particular bean type ki, Spring IoC container antha, **oke okka object instance** create chestundi. Nuvvu aa bean ni enni sarlu adigina, ekkada adigina, Spring neeku ade original, "master piece" object ni malli malli istundi.

Idi Spring lo **default scope**. Ante, nuvvu emi cheppakapothe, Spring prathi bean ni singleton ga ne treat chestundi.

### The "One Shared Resource" Analogy
Imagine oka application lo, multiple services ki database tho matladali. Prati service ki kotha database connection pool create cheyadam chala expensive. Anduke, manam oke okka `DataSource` bean ni create chesi, daanni andari tho share chestam. This is the perfect use case for a singleton.

```mermaid
%%{init: {'theme': 'dark', 'themeVariables': { 'primaryColor': '#2d2d2d', 'primaryTextColor': '#fff'}}}%%
graph TD
    subgraph "Spring Container"
        DS["`DataSource` Bean<br>(Singleton)<br/>ID: #123"];

        subgraph "Other Beans"
            A["OrderService"];
            B["UserService"];
            C["ProductController"];
        end
    end

    A -- "injects" --> DS;
    B -- "injects" --> DS;
    C -- "injects" --> DS;

    style DS fill:#552,stroke:#ff8,color:#fff
```
Ee diagram lo, `OrderService`, `UserService`, and `ProductController` anni veru veru beans, kani vaati annitiki oke okka `DataSource` object (ID: #123) instance inject avtundi.

### Why is it the Default?
Most of the time, mana application lo beans ki state undadu (stateless). For example, services, repositories, controllers. Veetiki instance variables undavu (or final matrame untayi). They just have behavior (methods). Alantappudu, prathi sari kotha object create cheyadam anavasaramaina pani. Oke object ni andaru share cheskunte, memory save avutundi, performance better avutundi. Anduke, singleton anedi chala efficient and default choice.

### Spring Singleton vs. GoF Singleton (A Classic Interview Question!)
Mawa, idi chala important interview question.
*   **Gang of Four (GoF) Singleton:** Idi classic design pattern. Deenilo, aa class design eh ala untundi, antha JVM lo, aa class ki oke okka object create avvagaladu. Idi `private constructor` and `static getInstance()` method use chesi achieve chestaru. The scope is per `ClassLoader`.
*   **Spring Singleton:** Idi different. Spring lo, bean anedi POJO (Plain Old Java Object). Daaniki private constructor undalsina avasaram ledu. The "singleton-ness" is controlled by the **container**, not the class itself. Spring just makes sure to create only one instance **per container** for a given bean definition.

**The Main Difference:** Spring singleton is more flexible. The same class can be a singleton in one context and a prototype in another, just by changing the configuration. GoF singleton is hard-coded into the class design.

### How to Declare a Singleton?
As I said, idi default. So nuvvu emi cheyanakkarledu.
```java
@Configuration
public class AppConfig {

    @Bean // By default, this is a singleton
    public MyService myService() {
        return new MyService();
    }
}

// OR using component scanning

@Service // By default, this is a singleton
public class MyOtherService {
    // ...
}
```
Kani, explicit ga cheppali anukunte, `@Scope("singleton")` ani rayochu. But it's redundant (avasaram ledu).
```java
@Bean
@Scope("singleton") // This is redundant but valid
public MyService myService() {
    return new MyService();
}
```

---
### Code Reference: Let's Prove it!
Ee "Okate Piece" concept ni prove cheyadaniki, `Spring-Project` lo `io.mawa.spring.core.scopes.singleton` package create chesi, andulo code petta.

1.  **`SingletonBean.java`:** A simple bean that prints its hash code in the constructor, so we can see which object it is.
2.  **`SingletonScopeConfig.java`:** A configuration class that defines our singleton bean.
3.  **`SingletonScopeDemoApp.java`:** The main app. Ikkada manam container nunchi bean ni **rendu sarlu** adugudam.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.scopes.singleton.SingletonScopeDemoApp"
```
**Expected Output:**
```
--- Creating SingletonBean ---
Hash code of created SingletonBean: 123456789

--- Retrieving bean 'singletonBean' for the first time ---
Retrieved Bean Hash Code: 123456789

--- Retrieving bean 'singletonBean' for the second time ---
Retrieved Bean Hash Code: 123456789

Both hash codes are the same. Spring returned the SAME instance!
```
Chusava! Bean okkasare create ayyindi, and manaki rendu sarlu ade object vachindi. That's the singleton scope for you!

**Bonus Point (XML world):**
Old projects lo XML configuration unte, idi ila kanipistundi:
```xml
<bean id="myService" class="com.example.MyService" scope="singleton"/>
<!-- scope="singleton" is default, so it can be omitted -->
```
That's it for our first scope! Next, we'll see the "every time a new one" friend: The Prototype Scope. Ready aa? 🔥
