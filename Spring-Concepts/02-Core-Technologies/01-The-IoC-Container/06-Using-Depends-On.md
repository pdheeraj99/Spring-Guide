# `depends-on`: The "You First!" Rule 🚦

Mawa, manam ippati varaku beans ki direct ga friends ni (dependencies) ela ivvalo chusam. `constructor(Friend f)` or `setFriend(Friend f)` ani unte, Spring ki telusu, aa friend ni mundu create cheyali ani.

But what if there is a secret friendship? 🤔

Oka bean inko bean meeda depend avutundi, kani direct ga reference ledu. Appudu Spring ki aa vishayam ela cheppali?

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-dependson.html](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-dependson.html)

### Why it Matters
Idi oka specific kani chala powerful feature mawa. Konni sarlu, manam oka bean create avvakamunde, vere konni setup activities jaragali. For example:
-   Database drivers register avvali.
-   Oka messaging queue ki connection establish avvali.
-   Konni initial data or cache load avvali.

Ee panulu chese beans ni, main logic unna beans kanna mundu load cheyali. `depends-on` ee initialization order ni guarantee chestundi. Idi lekunte, mana application start avvagane fail avvochu! 🤯

---

### The Secret Handshake: How `depends-on` Works

**The Analogy: Morning Coffee Routine ☕️**
Imagine, nuvvu `Person` ane oka bean. Neeku `CoffeeMaker` ane inko bean undi. Nuvvu work start cheyali ante (ante `Person` bean initialize avvali ante), mundu coffee ready ga undali (`CoffeeMaker` bean initialize avvali).

Kani, nuvvu rojantha coffee machine ni pattukuni tiragavu kada? 😂 So, `Person` class lo `CoffeeMaker` anedi direct dependency ga (field ga) undadu. But still, the dependency exists!

`@DependsOn` anedi ee secret handshake anamata. `Person` bean tho manam Spring ki cheptunnam, "Hey, nannu create chese mundu, first aa 'coffeeMaker' bean pani kanivvu. Adi ayyake nenu scene loki vasta."

**How to use it in Java Config?**
Simple ga `@DependsOn` annotation ni `@Bean` method meeda pettadame.

```java
@Configuration
public class MyConfig {

    @Bean
    public CoffeeMaker coffeeMaker() {
        return new CoffeeMaker();
    }

    @Bean
    @DependsOn("coffeeMaker") // <-- The Magic Word! ✨
    public Person me() {
        return new Person();
    }
}
```
Ikkada, `me` ane bean create avvakamunde, `coffeeMaker` ane bean 100% create ayyi untundi ani Spring chuskuntundi.

---

### Code Reference: Let's See the Order!
Ee concept ni live lo chudadaniki, `Spring-Project` lo `io.mawa.spring.core.dependson` package chudu.

1.  **The Prerequisite (`EventLogger.java`):** Idi mana `CoffeeMaker` lantiది. First idi initialize avvali. Constructor lo oka message print chestundi.
2.  **The Dependent (`SystemInitializer.java`):** Idi mana `Person` bean. Deeniki `EventLogger` tho direct connection ledu, kani daani pani ayyake idi start avvali.
3.  **The Configuration (`DependsOnConfig.java`):** Ikkada manam `@DependsOn("eventLogger")` use chesi, `systemInitializer` bean ki secret instruction istunnam.
4.  **The Demo (`DependsOnDemoApp.java`):** Ee app lo, manam just container ni start chestam. Beans ni retrieve kuda cheyanakkarledu. Singleton beans default ga container start appude create avutayi kabatti, manam console lo output chusi order confirm cheyochu.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.dependson.DependsOnDemoApp"
```
**Expected Output:**
```
Starting the Spring Container...
Step 1: EventLogger has been initialized! ✍️
Step 2: SystemInitializer has been initialized! 🚀
Spring Container has been started. Check the console for initialization order.
```
Chusava! `EventLogger` (Step 1) anedi *eppudu* `SystemInitializer` (Step 2) kanna mundhe initialize avutundi, thanks to `@DependsOn`.

**Bonus Point:** `depends-on` anedi shutdown order ni kuda control chestundi, but in reverse! Ante, `SystemInitializer` mundu destroy ayyi, aa tarvata `EventLogger` destroy avutundi.

That's it for this short but powerful concept! Ready for the next one? 💪
