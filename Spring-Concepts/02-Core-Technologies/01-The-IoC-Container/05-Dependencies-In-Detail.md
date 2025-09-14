# Dependencies in Detail: Bean ki Friends ni Ela Ivvali? 🤝

Mawa, so oka bean ki inkoka bean (oka sidekick) ela ivvalo chusam. Simple. Kani mana bean ki okkade friend saripodu. Daaniki oka gang kavali (`List`), konni rules kavali (`Set`), and oka secret contact book (`Map`) kuda kavali anuko. Appudu em chestam? 🤔

Ee episode lo, aa panine cheddam. Mana bean ki కావలసిన anni rakala friends ni, gadgets ni ela ivvalo chuddam.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-properties-detailed.html](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-properties-detailed.html)

### Why it Matters
Real-world applications lo, beans ki simple `String`s or `int`s matrame kaadu, full-fledged `List`s, `Map`s, and `Set`s lanti complex data ni inject cheyalsi vastundi. Idi a master cheste, nuvvu entha pedda configuration ni ayina pindi chesesthav. It's the difference between giving your friend a pen vs. giving them a full stationery kit! ✍️➡️🧰

---

### The Arsenal: Injecting Different Types of Data

#### 1. Simple Values (Literals)
Idi manam already chusam. Oka `String` or `int` lanti simple value ni ivvadam. Java config lo, idi oka method lo direct ga return cheyadam antha easy.

#### 2. A Single Friend (Bean References)
Vere bean ni inject cheyali ante, daanini `@Bean` method lo parameter ga adigithe chalu. Spring gadu vethiki pampistadu. 😂

```java
// Spring will see this parameter and find a bean of type 'Sidekick'
@Bean
public Hero myHero(Sidekick sidekick) {
    return new Hero(sidekick);
}
```

#### 3. The Whole Gang! (Injecting Collections 👨‍👩‍👧‍👦)
Ippudu আসলమైన మ్యాటర్ ki vacham. `List`, `Set`, `Map` lanti collections ni ela inject cheyali?

**The secret is:** Spring lo, collections kuda beans ye! Manam oka `List` ni return chese `@Bean` method rayochu. Vere bean ki aa `List` kavali anukunte, Spring automatic ga daanini inject chestundi. How cool is that?! 😎

Let's see this in action with a `ComplexBean` that needs a whole bunch of stuff.

---

### Code Reference: Let's Assemble the `ComplexBean`!
Ee magic ni live lo chudadaniki, `Spring-Project` lo `io.mawa.spring.core.dependencies` package chudu.

1.  **The Gang Members & Gadgets:**
    -   `AnotherService.java`: Mana simple sidekick bean.
    -   `DependencyDetailConfig.java`: Ikkada, manam `List<String>`, `Set<String>`, and `Map<String, Integer>` ni return chese separate `@Bean` methods create chesam.

2.  **The `ComplexBean`:**
    -   Ee bean constructor lo chudu, adi direct ga `List`, `Set`, `Map`, and `AnotherService` ni adugutundi.

3.  **The Master Plan (`DependencyDetailConfig.java`):**
    -   Ikkade manam antha kaluputunnam. `complexBean()` ane method chudu. Spring ki telusu, ee bean create cheyadaniki, daaniki vere 4 beans (mana collections and service) kavali ani. So, adi vaatini mundu create chesi, tarvata `complexBean` constructor ki pass chestundi. Everything is automatic!

    ```java
    // Path: .../dependencies/DependencyDetailConfig.java
    @Configuration
    public class DependencyDetailConfig {

        @Bean
        public List<String> superheroGadgets() {
            return List.of("Super-Boot", "Grapple-Hook", "Smoke-Pellets");
        }

        // ... and so on for Set, Map, and AnotherService

        @Bean
        public ComplexBean complexBean(
                AnotherService anotherService,
                List<String> superheroGadgets, // Spring injects the List bean here!
                Set<String> superheroRules,     // Spring injects the Set bean here!
                Map<String, Integer> superheroContacts // And the Map bean here!
        ) {
            return new ComplexBean(anotherService, superheroGadgets, superheroRules, superheroContacts);
        }
    }
    ```

4.  **The Demo (`DependencyDetailDemoApp.java`):**
    -   Ee main class `complexBean` ni container nunchi teeskuni, daani details anni print chestundi.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.dependencies.DependencyDetailDemoApp"
```
**Output:**
```
--- Super Bean Assembled! 🦸‍♂️ ---
Simple Dependency: I am a simple dependency bean!

--- Gadget List (Utility Belt) ---
- Super-Boot
- Grapple-Hook
- Smoke-Pellets

...and so on for the rest!
```

Chusava! Anni dependencies, collections tho సహా, perfect ga inject ayyayi.

### A Quick Note on the Old Ways (XML Shortcuts 📜)
Mawa, paatha rojullo XML tho config రాసేటప్పుడు, collections ni `<list>`, `<set>`, `<map>` lanti tags tho define chesevaru. Inka properties ni easy ga set cheyadaniki `p-namespace` and `c-namespace` ani konni shortcuts undevi. Just "ila undedi" ani telsukunte chalu, manam vaati gurinchi ekkuva tension avasaram ledu. Mana focus antha modern, clean Java-based configuration meedha ne! 😉

Ippudu neeku oka bean ki enni rakala friends ni, gadgets ni ivvacho oka clear idea vachindi anukuntunna. Let's get ready for the next topic! 💪
