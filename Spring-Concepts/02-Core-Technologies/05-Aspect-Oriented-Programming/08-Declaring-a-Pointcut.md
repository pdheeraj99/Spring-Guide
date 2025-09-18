# 🎯 8. Declaring a Pointcut (Where to Intervene?)

Hey Mawa! Last time, we hired our "Director" (the `@Aspect`). Now, we need to give them a "Shooting Schedule". A director doesn't just film everything, right? They film specific scenes at specific locations. A **Pointcut** is exactly that – it's our detailed shooting schedule. It tells our Aspect *exactly where* to apply its logic.

A Pointcut is a powerful expression that matches specific "join points" (in Spring, this means specific method executions).

#### How to Define a Pointcut?

You define a pointcut with a `@Pointcut` annotation on a method inside your `@Aspect` class. This method itself is the "signature" of the pointcut.

*   **The Method Signature:** It must have a `void` return type. Its name is the name of the pointcut. It's just a placeholder; the method body is always empty.
*   **The `@Pointcut` Annotation:** This holds the powerful expression that does the actual matching.

```java
@Aspect
@Component
public class LoggingAspect {

    // This is a pointcut signature
    @Pointcut("execution(* io.mawa.spring.core.aop.aspectj.pointcuts.BusinessService.getData(..))")
    public void forGetData() {} // The method body is empty

    // We will later use this named pointcut "forGetData()" in our advice.
}
```

---

### The Pointcut Designators (The "Language" of the Shooting Schedule)

These are the special keywords you use in your pointcut expressions. Spring AOP supports the most useful ones from AspectJ.

#### 1. `execution` - The Superstar 🌟

This is the most common and powerful designator. You'll use it 90% of the time. It matches method executions based on their signature.

**Syntax:** `execution(modifiers? return-type package.class.method(params) throws?)`

*   `modifiers`: `public`, `private`, etc. (optional)
*   `return-type`: The method's return type. `*` means any return type.
*   `package.class.method`: The full method path. You can use wildcards:
    *   `*`: matches one word (e.g., `*Service`, `get*`)
    *   `..`: matches any number of packages (e.g., `io.mawa..*`)
*   `(params)`: The method parameters.
    *   `()`: matches a method with no parameters.
    *   `(..)`: matches a method with zero or more parameters of any type.
    *   `(*)`: matches a method with exactly one parameter of any type.
    *   `(*, String)`: matches a method with two parameters, the first of any type, the second a `String`.

**Example:**
`execution(public String io.mawa.spring..*Service.get*(..))`
*This matches any public method starting with "get", returning a `String`, in any class ending with "Service" inside the `io.mawa.spring` package or its sub-packages, with any number of parameters.*

#### 2. `within` - The Location Scout 🗺️

This is simpler than `execution`. It matches all methods **within** a certain class or package.

**Example:**
`within(io.mawa.spring.core.aop..*)`
*This matches every single method execution inside any class in the `io.mawa.spring.core.aop` package or its sub-packages.*

#### 3. `@annotation` - The Tag Hunter 🏷️

This matches methods that have a specific annotation. This is super useful for creating custom markers.

**Example:**
Let's say you have a custom annotation `@Loggable`.
`@annotation(io.mawa.spring.core.aop.Loggable)`
*This pointcut will match any method that you annotate with `@Loggable`.*

#### 4. `bean` - The Spring Special ✨

This is a special designator provided only by Spring AOP. It lets you target beans by their name in the Spring container.

**Example:**
`bean(*Service)`
*This matches all methods on any bean whose name ends with "Service".*

---

### Combining Pointcuts (Creating the Master Plan)

You can combine these simple pointcuts to create very powerful and precise rules, just like a real shooting schedule. Use standard logical operators:

*   `&&` (AND)
*   `||` (OR)
*   `!` (NOT)

**Best Practice:** Define small, named pointcuts and combine them. It makes your code so much cleaner!

```java
@Aspect
@Component
public class LoggingAspect {

    // 1. Pointcut for all methods in the BusinessService
    @Pointcut("within(io.mawa.spring..*Service)")
    public void inServiceLayer() {}

    // 2. Pointcut for all getter methods
    @Pointcut("execution(* get*(..))")
    public void anyGetter() {}

    // 3. Combined Pointcut!
    @Pointcut("inServiceLayer() && anyGetter()")
    public void allGettersInServiceLayer() {}
}
```

### Visualizing a Pointcut 📊

Imagine your application has tons of methods. A pointcut acts like a filter to select only the ones you care about.

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
        P["@Pointcut('execution(* io.mawa..BusinessService.*(..))')"]
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

That's the essence of it, mawa! A pointcut is our "WHERE". We haven't said *what* to do yet. That's the job of **Advice**, which is our next exciting topic! 😉
