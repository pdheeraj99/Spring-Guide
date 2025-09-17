# 06. SpEL Advanced Operators: The Pro-Level Toolkit 🛠️

Mawa, manam SpEL lo chala ground cover chesam. Ippudu, konni final "pro-level" operators chuddam. Ee operators chinnavi ga kanipinchina, daily coding lo chala time save chestayi and mana code ni chala clean ga unchutayi.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-language-ref](https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-language-ref)

## 1. The Safe Navigation Operator (`?.`)

Idi oka lifesaver. Java lo manam `NullPointerException` tho entha visigipothamo telusu kada. For example, `user.getAddress().getCity()` ane code lo, `getAddress()` null return cheste, app antha crash avutundi.

The **safe navigation operator** (`?.`) ee problem ni solve chestundi.

*   **How it works:** `user.getAddress()?.getCity()` ani raste, SpEL first `getAddress()` ni check chestundi. Adi `null` aite, expression akkade aagipoyi, `null` return chestundi. App crash avvadu! Adi `null` kakapothe matrame, `getCity()` ni call chestundi.

```mermaid
graph TD
    A["Expression: `user.address?.city`"] --> B{Is user.address null?};
    B -- "Yes" --> C[Return null <br> (No NPE! ✅)];
    B -- "No" --> D[Call getCity() on address <br> (Normal execution)];
```
Idi chala elegant way lo null checks ni handle chestundi.

## 2. The Elvis Operator (`?:`)

Ee operator peru konchem funny ga unna, idi chala useful. Idi ternary operator ki oka shorthand. Oka value `null` aite, oka default value ivvadaniki vadatam.

*   **Ternary Operator:** `user.name != null ? user.name : "Guest"`
*   **Elvis Operator:** `user.name ?: "Guest"`

Rendu oke pani chestayi, kani Elvis operator chala clean ga undi kada? If the value on the left is not null, it's returned. Otherwise, the value on the right is returned.

## 3. The Type Operator (`T(...)`)

SpEL lo static methods or static constants ni access cheyalante, manam `T()` operator vadali. Idi `java.lang.Class` ni represent chestundi.

```java
// Accessing a static constant
'T(java.lang.Math).PI'

// Calling a static method
'T(java.lang.System).currentTimeMillis()'
```
This is useful for performing calculations or accessing utility methods directly in your expressions.

## 4. Bean Reference (`@...`)

SpEL expression lo, manam Spring container lo unna vere beans ni direct ga refer cheyochu. Idi `@Value` lanti annotations lo chala powerful.

```java
// Assuming you have a bean named 'someService' in your context
@Value("@someService.someProperty")
private String propertyFromAnotherBean;
```
Note: The `#` is used for the expression itself, and the `@` is used inside it to refer to a bean. The full expression is `#{ @someService.someProperty }`.

***

### Chapter Finale! 🏆

Mawa, congratulations! You have officially completed the deep dive into the Spring Expression Language. You started from the basics of the API, learned its powerful integration with `@Value`, mastered the core syntax, and explored advanced features like collection processing and special operators.

You now have a skill that many Spring developers lack. Use this power wisely! 😉 Next time you see `#{...}` in code, you won't be confused; you'll be in control.
