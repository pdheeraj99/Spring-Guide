### 📜 My Notes: Declaring a Pointcut

Namaste! Welcome back to our AOP session. Last time, we set up the stage with Aspects. Now, let's learn how to tell our "gift wrapper" (the Aspect) *which* gifts to wrap. This is where **Pointcuts** come in.

Think of it like this: You're in a huge warehouse full of gifts (your application's methods). You don't want to wrap every single one. You only want to wrap specific gifts based on certain rules.

A **Pointcut** is that rule. It's a query that selects specific "moments" (Join Points) in your code. In Spring AOP, this "moment" is always a method execution.

A Pointcut has two parts:
1.  **Signature:** A name for the rule. It's just a regular Java method. `private void allToyOrders() {}`
2.  **Expression:** The actual rule itself, written inside the `@Pointcut` annotation. `@Pointcut("execution(* com.mytoys.service.*.order*(..))")`

#### Pointcut Designators (The Rule Language Keywords)

Spring AOP gives us a powerful set of keywords, called "Pointcut Designators" (PCDs), to write our rules. Here are the most important ones:

*   `execution`: The most powerful and common one. It's like giving a detailed description of the gift.
    *   `execution(public * com.mycompany.service.*.*(..))`
    *   **Translation:** "Wrap any `public` method (`public`), that can return anything (`*`), inside any class (`*`) in the `com.mycompany.service` package, with any name (`*`), that can take any number of arguments (`..`)."
*   `within`: This is simpler. It's like saying, "Wrap everything in that room."
    *   `within(com.mycompany.service..*)`
    *   **Translation:** "Wrap any gift (method) located anywhere inside the `com.mycompany.service` package or its sub-packages."
*   `@annotation`: This is like looking for a special sticker on the gift.
    *   `@annotation(com.mycompany.annotations.Loggable)`
    *   **Translation:** "Wrap any gift (method) that has the `@Loggable` sticker on it."
*   `bean`: A Spring-special keyword! It's like asking for a gift by its specific name tag.
    *   `bean(*Service)`
    *   **Translation:** "Wrap any gift (method) in a bean whose name ends with 'Service'."

#### Combining Rules

Just like with Lego, you can combine these rules to create more complex and precise ones!

*   `&&` (AND): `ruleA() && ruleB()` -> Must match BOTH rules.
*   `||` (OR): `ruleA() || ruleB()` -> Must match EITHER rule.
*   `!` (NOT): `!ruleA()` -> Must NOT match the rule.

It's a best practice to create small, reusable pointcuts and combine them.

```java
// Rule A: Any method in the service layer
@Pointcut("within(com.mycompany.service..*)")
public void inServiceLayer() {}

// Rule B: Any method with the @Loggable sticker
@Pointcut("@annotation(com.mycompany.annotations.Loggable)")
public void isLoggable() {}

// Combined Rule: Any method in the service layer AND has the @Loggable sticker
@Pointcut("inServiceLayer() && isLoggable()")
public void serviceOperationToLog() {}
```

This makes your rules clean, readable, and easy to manage.

#### 📈 Diagram: Pointcut Decision Tree

Here’s a Mermaid diagram to show the thought process of a pointcut.

```mermaid
graph TD
    A[Method Execution: placeOrder()] --> B{Is it in the service package?};
    B -- Yes --> C{Does it have a @Loggable annotation?};
    B -- No --> D[Ignore ❌];
    C -- Yes --> E[Apply Advice! ✅];
    C -- No --> F[Ignore ❌];
```

That's the essence of Pointcuts! They are the "WHERE" and "WHEN" for your AOP magic. Next up, we'll see how to actually *define the magic itself* by declaring Advice.
