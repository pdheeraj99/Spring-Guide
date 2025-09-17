# 02. The SpEL API: How It Works Under the Hood

Mawa, manam SpEL expressions ni Spring lo direct ga `@Value` lo vadatam ani cheppukunnam. Kani asalu, aa string expression ni Spring ela ardam cheskuntundi? How does it turn a string like `'Hello World'.length()` into the number `11`?

To understand that, we need to look at the core SpEL API. Manam ee API ni direct ga eppudu vadakapovachu, kani idi teliste, SpEL meeda neeku oka solid grip vastundi.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-evaluation](https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-evaluation)

## The Core Components of SpEL

SpEL evaluation anedi oka simple three-step process. Ee process lo 3 main components untayi.

1.  **`ExpressionParser`**: Idi mana expression string ni teesukuni, daanini "parse" chesi, oka `Expression` object ga istundi. Think of it as a compiler that turns your text into an executable program.
    *   The standard implementation is `SpelExpressionParser`.

2.  **`Expression`**: Idi mana compiled expression. Ee object meeda manam `getValue()` ane method ni call chesi, expression ni run chesi, result ni teeskuntam.

3.  **`EvaluationContext`**: Idi chala important. Mana expression lo object properties (`mawa.name`) or methods (`mawa.doSomething()`) unte, aa `mawa` object evaro SpEL ki cheppede ee `EvaluationContext`. It holds the "root object" against which the expression is evaluated.
    *   The standard implementation is `StandardEvaluationContext`, which is very powerful but less secure.
    *   `SimpleEvaluationContext` is a stripped-down version for better security when you don't trust the expression source.

## The Process: A Visual

Here's how these three components work together to produce a result.

```mermaid
graph TD
    A["Expression String <br> e.g., 'name.toUpperCase()'"] --> B(ExpressionParser);
    B -- ".parseExpression()" --> C(Expression Object);

    subgraph "Evaluation"
        direction LR
        C --> E{getValue(context)};
        D[EvaluationContext <br> (contains the 'User' object)] --> E;
    end

    E --> F[Result <br> e.g., "MAWA"];
```

### Example in Action 🎬

Here's a simple Java code snippet that shows these three components in action.

```java
// 0. Create an object to run expressions against
User mawa = new User("Mawa", "mawa@example.com");

// 1. Create a parser
ExpressionParser parser = new SpelExpressionParser();

// 2. Parse the expression string to get an Expression object
Expression exp = parser.parseExpression("name"); // We want to get the 'name' property

// 3. Create an evaluation context and set the root object
EvaluationContext context = new StandardEvaluationContext(mawa);

// 4. Evaluate the expression against the context
String name = (String) exp.getValue(context); // Result will be "Mawa"
System.out.println(name);
```
Ee code lo, manam `'name'` ane property ni `mawa` object nunchi extract chesam. Spring does exactly this behind the scenes when you use SpEL in annotations.

***

### Mawa's Cliffhanger 🧗

Okay, ee API antha choodaniki konchem verbose ga undi kada? Prati saari ee parser, context create cheyadam ante kashtam. Luckily, Spring lo manam ee pani cheyanavasaram ledu! Spring ee complexity antha hide chesi, manaki oka simple, elegant solution istundi. In our next topic, we will see the **real magic**: using SpEL directly inside the `@Value` annotation. Get ready to level up! 🚀
