# 13. Aspect Instantiation Models: Okate Aspect aa, leka Prathi Sari Kothadā? 🤔

Mawa, manam ippati varaku create chesina aspects anni (`LoggingAspect`, `SecurityAspect`, etc.) by default **singleton** behavior lo untai. Ante, entha application run aina, Spring container lo prathi aspect ki okate okka instance create avuthundi. All advised objects share this single aspect instance.

Idi 99% of the time manaki saripotundi and it's the most efficient way. Kani, AOP lo konni advanced scenarios untai, akkada manaki prathi advised object (target object) ki oka kotha aspect instance kavali anipinchachu.

Ee concept ne **Aspect Instantiation Models** antam. `@AspectJ` lo, `@Aspect` annotation tho patu manam instantiation model ni specify cheyochu.

## The Models

1.  **Singleton (Default):**
    *   **How it works:** The entire application uses a single instance of the aspect.
    *   **When to use:** Almost always. It's simple, efficient, and stateless aspects ki perfect.
    *   **How to declare:** Just use `@Aspect`. Idi default behavior.

2.  **`perthis` and `pertarget`:**
    *   **How it works:**
        *   `perthis(Pointcut)`: Spring creates a new aspect instance for every unique *proxy object* that matches the pointcut.
        *   `pertarget(Pointcut)`: Spring creates a new aspect instance for every unique *target object* that matches the pointcut.
    *   **Difference:** Ee rendu chala similar. Proxy mode lo unnapudu, `perthis` and `pertarget` are effectively the same. The difference is subtle and usually matters only in advanced AspectJ weaving scenarios outside of Spring AOP.
    *   **When to use:** Ee models stateful aspects ki use avuthai. For example, oka aspect lo counter maintain cheyali, and aa counter prathi advised object ki separate ga undali anukunnapudu.
    *   **How to declare:**
        ```java
        @Aspect("perthis(execution(* com.example.service.*.*(..)))")
        public class MyStatefulAspect {
            private int counter = 0;
            // ...
        }
        ```

## The `perthis` Model in Action

To prove this, we have created a new `StatefulAnalyticsAspect` and configured it with the `perthis` model. We also created a `SecondCalculatorService` so we have two distinct beans for the aspect to advise.

The aspect looks like this:
```java
// Note: No @Component annotation!
@Aspect("perthis(io.mawa.spring.core.aop.LoggingAspect.forServicePackage())")
public class StatefulAnalyticsAspect {
    private int callCount = 0;
    @Before("io.mawa.spring.core.aop.LoggingAspect.forServicePackage()")
    public void gatherAnalytics() {
        callCount++;
        System.out.println("======> 📊 [Stateful] Analytics! [Instance: " + this.hashCode() + ", Count: " + callCount + "]");
    }
    // ...
}
```
And in our `AopConfig.java`, we define it as a prototype bean:
```java
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    @Scope("prototype")
    public StatefulAnalyticsAspect statefulAnalyticsAspect() {
        return new StatefulAnalyticsAspect();
    }
}
```

### The Proof is in the Logs! 🕵️‍♂️

Now, when you run the `AopDemoApp`, you will see the following output for the calculator demo:

```text
--- Calling methods on 'calculatorService' ---
This should use one instance of the stateful aspect.
...
======> 📊 [Stateful] Analytics! [Instance: 1139817507, Count: 1]
...
======> 📊 [Stateful] Analytics! [Instance: 1139817507, Count: 2]

--- Calling methods on 'secondCalculatorService' ---
This should use a DIFFERENT instance of the stateful aspect.
...
======> 📊 [Stateful] Analytics! [Instance: 1689169705, Count: 1]

--- Calling method that throws an exception ---
...
======> 📊 [Stateful] Analytics! [Instance: 1139817507, Count: 3]
```

**Analysis:**
*   Notice the `Instance` hash code. For all calls to `calculatorService`, the instance is the same (`1139817507`), and its internal counter increments from 1 to 3.
*   But for the call to `secondCalculatorService`, a **completely new instance** (`1689169705`) is created, and its counter starts from 1.

This is the `perthis` model in action! Each advised bean gets its own private, stateful aspect instance.

### A Very Important Note on `@Scope("prototype")`

Mawa, you might think, "Why not just put `@Scope("prototype")` on the aspect and forget the `perthis` stuff?" Excellent question!

I tried that first, and it **does not work** as you'd expect. Spring AOP's default weaving mechanism will still only use a single cached instance of a prototype-scoped aspect. The `perthis` or `pertarget` clause is the **official AspectJ mechanism** to tell the weaver to create new instances. This is a subtle but critical point that trips up many developers!

---

Mawa, now you understand not just the "what" but also the "why" and "how" of aspect instantiation. This is advanced knowledge!

Next, let's take a quick look at the history of AOP in Spring.

**Next Step:** [Schema-based AOP (The Old Way) & Why @AspectJ is King](./14-Schema-vs-AspectJ-and-Mixing-Types.md)
