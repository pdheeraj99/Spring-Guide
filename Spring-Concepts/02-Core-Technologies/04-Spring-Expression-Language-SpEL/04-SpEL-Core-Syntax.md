# 04. SpEL Core Syntax: The Basic Spells

Mawa, manam ippudu SpEL ni Spring tho ela vadalo chusam. Ippudu, aa `#{...}` madhyalo rayadaniki kavalsina basic language syntax nerchukundam. Just like learning the grammar of a new language, ee core syntax anedi SpEL tho fluent ga matladataniki chala avasaram.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-language-ref](https://docs.spring.io/spring-framework/reference/core/expressions.html#expressions-language-ref)

## Accessing Properties, Arrays, Lists, and Maps

Idi most common operation. Oka object lo unna data ni access cheyadaniki, manam simple dot notation (`.`) vadatam, just like in Java.

*   **Properties:** `inventor.name`
*   **Nested Properties:** `inventor.placeOfBirth.city`
*   **Arrays:** `myArray[0]`
*   **Lists:** `myList[0]`
*   **Maps:** `myMap['mawaKey']`

```java
// SpEL Expression
'inventor.name' // Accesses the 'name' property of the inventor object
'inventions[2]' // Accesses the 3rd element of the 'inventions' array
```

## Calling Methods

Properties lage, methods ni kuda direct ga call cheyochu.

```java
// SpEL Expression
'name.toUpperCase()' // Calls the toUpperCase() method on the name property
'isMember("Nikola Tesla")' // Calls a method with an argument
```

## SpEL Operators

Java lage, SpEL lo kuda operators unnayi, kani konni extra keywords kuda unnayi.

### 1. Relational Operators
Nuvvu symbolic names (`==`) or text-based names (`eq`) vadachu. Text-based names XML lo vadinappudu useful avtayi.

| Operator | Text Equivalent | Example |
| :--- | :--- | :--- |
| `==` | `eq` | `inventor.name == 'Nikola Tesla'` |
| `!=` | `ne` | `inventor.nationality != 'American'` |
| `<` | `lt` | `2 < 5` |
| `<=` | `le` | `2 <= 2` |
| `>` | `gt` | `inventions.length > 10` |
| `>=` | `ge` | `age >= 18` |

### 2. Logical Operators
Relational operators ni combine cheyadaniki logical operators vadatam.

| Operator | Text Equivalent | Example |
| :--- | :--- | :--- |
| `&&` | `and` | `age >= 18 and nationality == 'Serbian'` |
| `||` | `or` | `name == 'Tesla' or name == 'Edison'` |
| `!` | `not` | `!isMember('Einstein')` |

### 3. Mathematical Operators
Basic math operations anni supported.

| Operator | Purpose | Example |
| :--- | :--- | :--- |
| `+` | Addition (or String concatenation) | `2 + 2` or `'Mawa' + 'Developer'` |
| `-` | Subtraction | `10 - 6` |
| `*` | Multiplication | `5 * 5` |
| `/` | Division | `10 / 2` |
| `%` | Modulus (remainder) | `10 % 3` |
| `^` | Exponentiation (power) | `2 ^ 3` (2 to the power of 3) |

Ee core syntax tho, nuvvu chala powerful and dynamic expressions rayochu.

***

### Mawa's Cliffhanger 🧗
Okay, ippudu manaki basic syntax vachesindi. Kani SpEL asalu power collections tho pani chesinappude telustundi. Oka pedda `List` lo unna items ni filter cheyalanna, or list lo unna prati item nunchi oka specific property ni extract cheyalanna, SpEL lo oka line lo cheyochu! How? Next topic lo, manam SpEL yokka most powerful collection spells ni chuddam!
