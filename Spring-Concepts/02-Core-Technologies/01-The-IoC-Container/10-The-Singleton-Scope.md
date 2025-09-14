# Singleton Scope: The "Okate Piece, Master Piece!" 😎👑

Mawa, welcome to the world of **Bean Scopes**! Idi Spring lo oka fundamental kani chala important topic. Okko bean ela puttali, entha kalam bratakali anedi ee scopes eh decide chestayi.

Andulo mana first hero, the one and only, the default king: **Singleton Scope**.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-singleton](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-singleton)

### What is Singleton Scope?
Perulo ne undi kada, "Single" ani. Ante, oka particular bean type ki, Spring IoC container antha, **oke okka object instance** create chestundi. Nuvvu aa bean ni enni sarlu adigina, ekkada adigina, Spring neeku ade original, "master piece" object ni malli malli istundi.

Idi Spring lo **default scope**. Ante, nuvvu emi cheppakapothe, Spring prathi bean ni singleton ga ne treat chestundi.

### The College Principal Analogy 👨‍🏫
Imagine mana Spring Container anedi oka college anuko.
*   **Singleton Bean (`Principal`):** College ki entha mandi students unna, HODs unna, principal okkare untaru. Evaru vachi "Principal evaru?" ani adigina, manam ade okka person ni chupistam. He is the single, shared instance for the entire college.
*   **Other Beans (`Student`):** Students chala mandi untaru, prathi student veru. Idi vere scope (like prototype), daani gurinchi tarvata matladukundam.

```mermaid
graph TD
    subgraph "Spring Container (Our College)"
        P(Principal Office)
        P -- "One & Only" --> Principal["👨‍🏫 Principal Bean (Singleton)"];
    end

    subgraph "Different Departments"
        S1["Student Affairs Office"] -->|asks for| P;
        S2["Admissions Office"] -->|asks for| P;
        S3["Exam Branch"] -->|asks for| P;
    end

    style Principal fill:#f9f,stroke:#333,stroke-width:2px
```
Ee diagram lo, college lo unna anni departments ki, oke okka Principal bean instance serve chestondi.

### Why is it the Default?
Most of the time, mana application lo beans ki state undadu (stateless). For example, services, repositories, controllers. Veetiki data ni store cheskune pani undadu, just logic execute chestayi. Alantappudu, prathi request ki kotha object create cheyadam anavasaram. Oke object ni andaru share cheskunte, memory save avutundi, performance better avutundi. Anduke, singleton anedi chala efficient and default choice.

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
