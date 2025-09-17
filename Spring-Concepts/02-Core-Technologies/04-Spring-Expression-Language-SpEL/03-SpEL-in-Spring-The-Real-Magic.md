# 03. SpEL in Spring: The Real Magic! ✨

Mawa, manam last topic lo SpEL API gurinchi nerchukunnam. Adi foundation kosam manchide, kani adi chala verbose. Real-world Spring projects lo, manam SpEL ni antha verbose ga vadamu.

Instead, we use its most powerful feature: **integration with bean definitions**. Idi SpEL yokka asalu magic, and 95% of the time, this is how you will use it. This is a top-tier interview topic!

### Source URL
[https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-beandef](https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-beandef)

## The Magic Syntax: `#{ ... }`

Spring lo, manam oka property value ni SpEL expression dwara ivvali anukunte, we use this special syntax: `#{ <SpEL-expression> }`.

Ee `#{}` anedi Spring ki oka signal. "Hey Spring, idi normal string kadu, idi oka SpEL expression. Please evaluate chesi, result ni ikkada inject cheyi" ani cheppinattu.

Compare this with `${...}`, which is used for property placeholders from `application.properties`.

*   `@Value("${my.property}")`: Injects a value from a properties file.
*   `@Value("#{someSpelExpression}")`: Evaluates a SpEL expression and injects the result.

## The Most Common Use Case: `@Value`

`@Value` annotation tho SpEL vaadadam anedi most common scenario. Deenitho manam enni adbhutalu cheyochu ante:

### 1. Referring to Other Beans
Nuvvu direct ga inkoka bean ni, daani name tho refer cheyochu.

```java
@Component("anotherBean")
public class AnotherBean {
    public String getName() {
        return "Mawa from AnotherBean";
    }
}

@Component
public class MyBean {
    // SpEL calls the 'anotherBean' and gets its 'name' property
    @Value("#{anotherBean.name}")
    private String nameFromAnotherBean;
    // Result: "Mawa from AnotherBean"
}
```

### 2. Calling Methods on Other Beans
Nuvvu vere bean meeda methods ni kuda call cheyochu.

```java
@Value("#{anotherBean.getName().toUpperCase()}")
private String nameInUpperCase;
// Result: "MAWA FROM ANOTHERBEAN"
```

### 3. Accessing System Properties
System properties ni kuda direct ga access cheyochu. The `systemProperties` is a predefined variable in SpEL.

```java
// Gets the user's country from system properties
@Value("#{systemProperties['user.country']}")
private String userCountry;
```

### 4. Performing Operations
Nuvvu mathematical, logical, or relational operations kuda cheyochu.

```java
@Value("#{ T(java.lang.Math).PI * 2 }")
private double piTimesTwo;

@Value("#{ someService.price > 100 ? 'Expensive' : 'Cheap' }")
private String priceCategory;
```
The `T(...)` operator is special. It lets us access static methods and constants from a class.

## SpEL for Conditional Bean Creation
SpEL anedi kevalam value injection kosame kadu. Oka bean ni create cheyala, vadda anedi kuda SpEL tho decide cheyochu! `@ConditionalOnExpression` annotation tho idi sadhyam.

```java
// This bean will only be created if the system property 'my.feature.enabled' is 'true'
@Component
@ConditionalOnExpression("#{systemProperties['my.feature.enabled'] == 'true'}")
public class MyConditionalFeature {
    // ...
}
```
This is incredibly powerful for enabling or disabling features without changing code.

***

### Mawa's Cliffhanger 🧗
Okay, ippudu manaki SpEL ni Spring tho ela vadalo telisindi. Kani asalu, aa `#{...}` madhyalo em rayali? What is the full syntax? How do we access list elements? How do we call methods with arguments? Next topic lo, manam SpEL language syntax ni detail ga chuddam. Let's become fluent in SpEL!
