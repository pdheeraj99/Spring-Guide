# 03. AOP Proxies

Hey Mawa, let's get into the "how" of Spring AOP. We know Spring AOP intercepts method calls at runtime, but how does it physically do that? The answer is a classic software design pattern: the **Proxy Pattern**. 🕵️‍♂️

Imagine a celebrity (our **Target Object**) who is too busy to deal with every fan request directly. So, they hire a personal bodyguard (our **AOP Proxy**).

*   Fans (**Client Code**) don't talk to the celebrity directly. They go through the bodyguard.
*   The bodyguard decides what to do. They might say, "The celebrity is busy, come back later" (**Before Advice**), or they might pass on a fan's message and then get an autograph (**After Advice**).
*   The fan doesn't need to know about the bodyguard; they just think they are interacting with the celebrity.

This is exactly how Spring AOP works. It creates a "bodyguard" object, called a proxy, that wraps your actual bean. When other parts of your application call a method on your bean, they are actually calling the proxy.

### How Spring Creates Proxies

Spring doesn't just have one way to create these proxy bodyguards; it has two, and it chooses the best one for the job automatically.

#### 1. JDK Dynamic Proxies (The "Official" Bodyguard)

This is Spring's preferred method. It's a standard feature that comes with Java itself.

*   **How it works**: It can create a proxy for any class that implements an **interface**. The proxy itself will implement the same interface as the target object.
*   **Analogy**: Think of this as hiring a bodyguard who is a member of the official "Bodyguard Union" (the Java interface). They are certified to have the same skills (methods) as the person they are protecting.
*   **Limitation**: It only works if the target bean has an interface. If your class `PaymentService` implements the `Payable` interface, Spring will use a JDK proxy. If your class has no interface, this method can't be used.

Here's a simple diagram:

```
+-------------+      +-------------------------+      +-----------------+
| Client Code |----->|      JDK Proxy          |----->|  PaymentService |
| (Wants to    |      | (Implements Payable)    |      | (Target Object) |
| use Payable)  |      +-------------------------+      +-----------------+
+-------------+                ^
                               | (Advice is applied here)
                               +
```

#### 2. CGLIB Proxies (The "Undercover" Bodyguard)

What if your bean doesn't implement any interface? No problem! Spring switches to its other tool: a library called **CGLIB** (Code Generation Library).

*   **How it works**: CGLIB creates a proxy by creating a **subclass** of your target class at runtime. It overrides all the methods from the parent class and adds the AOP advice there.
*   **Analogy**: This is like hiring a bodyguard who is a master of disguise. They become a "child" of the celebrity's family, inheriting their traits (methods) and adding their own protective measures.
*   **Limitation**: Because it uses subclassing, it cannot advise `final` methods. A `final` method in Java cannot be overridden by a subclass, so CGLIB has no way to intercept the call.

Here's how that looks:

```
+-------------+      +----------------------------+      +-----------------+
| Client Code |----->| CGLIB Proxy                |----->|  ReportService  |
|             |      | (Subclass of ReportService)|      | (Target Object) |
+-------------+      +----------------------------+      +-----------------+
                                 ^
                                 | (Advice is applied here)
                                 +
```

### So, Which One Will Spring Use?

Spring has a simple decision tree:

1.  Does the target bean implement one or more interfaces?
    *   **Yes**: Spring will use a **JDK Dynamic Proxy**.
    *   **No**: Spring will use a **CGLIB Proxy**.

You can actually force Spring to always use CGLIB, even if there is an interface, by setting `proxy-target-class="true"` in your configuration. Why would you do this? Sometimes you want to advise a method that is on the class itself, not on the interface.

### The "Self-Invocation" Problem: A Common Gotcha! ⚠️

This is a classic "Mawa, listen up!" moment. What happens if a method *inside* your bean calls another method *on the same bean*?

```java
@Service
public class MyService {

    public void methodA() {
        // ... some logic ...
        this.methodB(); // Uh oh!
    }

    @Transactional // An AOP aspect for transactions
    public void methodB() {
        // ... database logic ...
    }
}
```

If you call `myService.methodA()` from outside, the proxy intercepts it. But the call from `methodA` to `methodB` using `this.methodB()` happens **inside the original target object**. It never goes through the proxy!

**The advice on `methodB` (like `@Transactional`) will NOT be executed.**

This is because the proxy is the "bodyguard" standing at the front door. Once you are inside the house, you can move from room to room without the bodyguard seeing you.

**How to solve this?**
1.  **Refactor**: The cleanest way is to move `methodB` to another bean and inject that bean into `MyService`.
2.  **Self-injection**: A clever trick is to inject the bean into itself. This ensures you are always calling the proxy.

We'll see practical examples of this later, but for now, just remember: **self-invocation bypasses the proxy!**

Okay, that was a deep dive! But understanding proxies is crucial to mastering Spring AOP. Next, we'll finally get our hands dirty and start enabling AOP in a project. Let's go! 🔥
