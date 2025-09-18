# 📜 8. Declaring a Pointcut

Mawa, manam aspect create chesam. Ippudu aa aspect ni ekkada apply cheyalo cheppali. Danike **Pointcut** vadatam. Choodu, pointcut anedi oka simple rule. Deeni pani enti ante, "ee advice ni ekkada (at which join points) apply cheyali?" ani cheppadam.

### How to Define a Pointcut

`@Pointcut` annotation ni oka empty method meeda pettu. Aa method name eh pointcut peru. Manam tarvata ee peru ni mana advice lo use cheskuntam.

```java
@Aspect
@Component
public class LoggingAspect {

    // Choodu, ikkada forServicePackage() anedi oka pointcut.
    // Daaniki unna expression, "execution(...)" anedi asal rule.
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void forServicePackage() {} // Deeni body eppudu empty ga untundi.

    // Ee forServicePackage() ni manam @Before("forServicePackage()") lanti advice lo vadukuntam.
}
```

### Pointcut Designators (PCDs)

Eevi pointcut expressions lo vaade main keywords. Chala unnayi, kani ivi most important.

1.  `execution` **(The Superstar 🌟)**
    *   Idi chala powerful and most commonly used. Method signature ni full ga match chestundi.
    *   **Syntax:** `execution(modifiers? return-type package.class.method(params) throws?)`
    *   **Wildcards:**
        *   `*`: `*` anedi దేనికైనా match avtundi (e.g., `*` return type, `*Service` class name).
        *   `..`: `(..)` anedi zero or more parameters ki match avtundi. Package lo `..` pedithe, aa package and daani sub-packages anni match avtayi.
    *   **Example:** `execution(* com.example.service..*.*(..))`
        *   **Meaning:** `com.example.service` package lo or daani sub-packages lo unna ఏ class loನైనా, ఏ method (any method name, any return type, any number of arguments) ayina match avtundi.

2.  `within`
    *   Idi `execution` kanna konchem simple. Idi oka particular class or package lo unna anni methods ni match chestundi.
    *   **Example:** `within(com.example.service..*)`

3.  `@annotation`
    *   Idi chala useful mawa. Oka specific annotation unna methods ni matrame match chestundi.
    *   **Example:** `@annotation(com.example.annotations.Loggable)` - `@Loggable` annotation unna methods anni match avtayi.

4.  `bean` **(Spring Special)**
    *   Idi Spring matrame iche special designator. Bean name tho match chestundi.
    *   **Example:** `bean(*ServiceImpl)` - `ServiceImpl` tho end ayye anni bean names ni match chestundi.

### Combining Pointcuts

Chinna chinna pointcuts ni `&&` (AND), `||` (OR), `!` (NOT) lanti logical operators tho combine chesi, chala powerful and specific rules rayochu.

```java
@Aspect
@Component
public class SystemArchitecture {

    @Pointcut("within(com.example.web..*)")
    public void inWebLayer() {}

    @Pointcut("within(com.example.service..*)")
    public void inServiceLayer() {}

    // Combined Pointcut: inWebLayer OR inServiceLayer
    @Pointcut("inWebLayer() || inServiceLayer()")
    public void webOrServiceLayer() {}
}
```

**Best Practice:** Reusable pointcuts ni ila oka separate class lo petti, vere aspects lo use cheskovachu. Idi code ni chala clean ga unchutundi.

### Visualizing a Pointcut
Ee diagram choodu, pointcut ela pani chestundo clear ga ardam avtundi.
```mermaid
graph TD
    subgraph "All Methods in Your Application"
        A[UserService.createUser()]
        B[BusinessService.getData()]
        C[DataRepository.save()]
        D[BusinessService.processData()]
        E[Helper.privateMethod()]
        F[UserService.getUsers()]
    end

    subgraph "Pointcut Expression"
        P["@Pointcut('within(..*BusinessService)')"]
    end

    subgraph "Selected Join Points (Methods)"
        B_Selected[BusinessService.getData()]
        D_Selected[BusinessService.processData()]
    end

    P -- selects --> B_Selected;
    P -- selects --> D_Selected;

    P -- ignores --> A;
    P -- ignores --> C;
    P -- ignores --> E;
    P -- ignores --> F;
```

---
### Mawa's Next Step
Super! Ippudu manaki "WHERE" (Pointcut) telusu. Next step lo, manam "WHAT" and "WHEN" define cheddam. Ante, **Advice** gurinchi matladukundam. Real action akkade start avtundi!
