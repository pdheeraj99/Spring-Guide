# 🚀 2. Prototype Scope: Prathi Sari Kothaga!

Mawa, manam `Singleton` scope gurinchi nerchukunnam ga, adi okate object ni isthundi ani. Ippudu daaniki opposite aindi **Prototype Scope**.

"Prototype" ante "oka sample" or "mould" anamata. Ee scope lo, Spring IoC container ki manam oka bean kosam request chesinappudu alla, adi oka **prajna kottha instance** ni create chesi isthundi. Ante, prathi `getBean()` call ki oka new object!

Imagine going to a `Chai` stall. Prathi sari nuvvu chai adiginappudu, stall owner kottha glass lo, kottha chai posthadu. Anthe kani, andhariki oke glass lo ivvadu ga! 😂 Aa prathi kottha chai glass ye mana prototype bean anamata.

### Key Points:
- **New Instance Per Request**: Prathi sari bean adiginappudu, kottha object create avthundi.
- **Stateful Beans ki Perfect**: Prototype scope stateful beans ki chala baga suit avthundi. Endukante, prathi client (or requesting object) ki oka separate object vasthundi, so okari state inkokariki interfere avadhu.
- **Lifecycle Responsibility**: Mawa, important point! Spring container prototype bean ni create chesi, configure chesi, dependencies inject chesi neeku ichestundi. Kani, daani tarvatha aa bean lifecycle ni manage cheyyadu. Ante, `@PreDestroy` lanti destruction lifecycle callbacks call avvavu. Aa object ni clean up chese badhyatha manade.

### Example Diagram:

Ee diagram chudu mawa. Prathi component `Command` bean ni adiginappudu, container oka kottha instance create chesi isthundi.

```mermaid
%%{init: {'theme': 'dark'}}%%
graph TD
    subgraph Spring IoC Container
        PBD["`Command` Bean Definition
        (Scope: Prototype)"];
    end

    subgraph Application Components
        C1[Client 1];
        C2[Client 2];
    end

    subgraph Created Instances
        I1["`Command` Instance 1"];
        I2["`Command` Instance 2"];
    end

    C1 -- requests bean --> PBD;
    PBD -- creates --> I1;
    I1 -- returns to --> C1;

    C2 -- requests bean --> PBD;
    PBD -- creates --> I2;
    I2 -- returns to --> C2;
```

### Java Code Example:
Ippudu ee concept ni chuseyadaniki `spring-pro-developer/src/main/java/io/mawa/spring/core/scopes/prototype/` lo unna code chudu mawa.

`PrototypeScopeDemoApp.java` lo, manam container nunchi `PrototypeBean` ni two times request chestunnam.

```java
// PrototypeScopeDemoApp.java
var bean1 = context.getBean(PrototypeBean.class);
bean1.setMessage("Hello from Bean 1");

System.out.println("Bean 1 Message: " + bean1.getMessage());
System.out.println("Bean 1 Object: " + bean1);

// Request the bean again
var bean2 = context.getBean(PrototypeBean.class);
System.out.println("Bean 2 Message: " + bean2.getMessage()); // Will be null!
System.out.println("Bean 2 Object: " + bean2);

System.out.println("Are both beans the same object? " + (bean1 == bean2));
```

**Output:**
```
Bean 1 Message: Hello from Bean 1
Bean 1 Object: com.mavencode.scopes.prototype.PrototypeBean@4554617c
Bean 2 Message: null
Bean 2 Object: com.mavencode.scopes.prototype.PrototypeBean@74a14482
Are both beans the same object? false
```
Chusava mawa? `bean1` and `bean2` veru veru objects (`false` vachindi). Anduke, `bean1` lo set chesina message `bean2` lo `null` ga undi. Prathi sari kottha object create ayyindi anadaniki idhe proof! ✨

Next, manam web-specific scopes gurinchi matladukundam. First stop: **Request Scope**! Ready ga undu mawa! 🤙
