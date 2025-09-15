# Scope anedi Enti Mawa? 🤔

Mawa, Spring lo "scope" anedi oka bean entha kalam బ్రతుకుతుంది (live), and adi okate instance untunda leda kottha kottha instances create avthaya anedi define chestundi. Simple ga cheppalante, adi bean yokka "lifecycle" and "visibility" ni control chestundi.

Spring Framework lo manaki konni built-in scopes unnayi. Vaatillo most important and default aindi **Singleton Scope**.

## 🚀 1. Singleton Scope: Okade Okkadu!

"Singleton" ante "okate" ani artham. Ee scope lo, Spring IoC container oka specific bean definition ki **okate okka object instance** create chestundi, mawa. Enni sarlu aa bean ni adigina, container ade same object ni return chestundi.

Imagine our favorite `Biryani` shop. Aa shop lo `Master Chef` okkade untadu. Nuvvu velli biryani adigina, nenu velli adigina, inkokaru velli adigina, andhariki aa okka Master Chef ae vandi pedathadu. Aa Master Chef ye mana singleton bean anamata!

### Key Points:
- **One Instance Per Container**: Oka Spring container lo oka bean ID ki okate object.
- **Default Scope**: Nuvvu emi scope specify cheyyakapothe, Spring default ga singleton scope theeskuntundi.
- **Stateful Beans tho Jagratha**: Singleton beans stateful ga unte (ante, data ni store cheskunte), adi thread-safety issues ki dari teeyochu. Endukante, multiple requests/threads ade object ni share cheskuntayi kabatti. So, singleton beans ni stateless ga unchadam best practice.

### Example Diagram:

Ee diagram chudu mawa, clear ga ardham avthundi. `AppConfig` lo define chesina `DataSource` bean okate untundi. `CustomerRepository` and `ProductRepository` rendu aa okka `DataSource` instance ne use cheskuntayi.

```mermaid
%%{init: {'theme': 'dark'}}%%
graph TD
    subgraph Spring IoC Container
        DS["`DataSource` Bean
        (Singleton Instance)"];
    end

    subgraph Application Components
        CR[CustomerRepository];
        PR[ProductRepository];
        AS[AnalyticsService];
    end

    CR -- uses --> DS;
    PR -- uses --> DS;
    AS -- uses --> DS;
```

### Java Code Example:
Ippudu ee concept ni chuseyadaniki `spring-pro-developer/src/main/java/io/mawa/spring/core/scopes/singleton/` lo unna code chudu mawa.

`SingletonScopeDemoApp.java` lo, manam container nunchi `SingletonBean` ni two times request chestunnam.

```java
// SingletonScopeDemoApp.java
var bean1 = context.getBean(SingletonBean.class);
bean1.setMessage("Hello Mawa from Bean 1");

System.out.println("Bean 1 Message: " + bean1.getMessage());

// Request the bean again
var bean2 = context.getBean(SingletonBean.class);
System.out.println("Bean 2 Message: " + bean2.getMessage());

System.out.println("Are both beans the same object? " + (bean1 == bean2));
```

**Output:**
```
Bean 1 Message: Hello Mawa from Bean 1
Bean 2 Message: Hello Mawa from Bean 1
Are both beans the same object? true
```
Chusava mawa? `bean1` and `bean2` rendu okate object ki point chestunnayi. `bean1` lo message set chesthe, adi `bean2` lo kuda reflect ayyindi. Idhe singleton magic! ✨

Next, manam **Prototype Scope** gurinchi matladukundam. Stay tuned! 🤙
