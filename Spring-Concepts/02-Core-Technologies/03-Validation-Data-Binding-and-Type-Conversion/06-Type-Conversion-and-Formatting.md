# 06. Type Conversion and Formatting

Mawa, we've built a solid wall of validation around our application. But there's one last problem. What if the data we receive isn't in the right *format*? A user might type "€1,000.00" for a price, but our `Double` field only understands `1000.0`.

This is where Spring's powerful Type Conversion and Formatting system comes in.

## The Currency Exchange Analogy 💶

Imagine you're traveling. You have US Dollars, but you need Euros.
*   **Type Conversion (`ConversionService`)** is the currency exchange office. Its job is to convert one thing into another (Dollars to Euros, `String` to `Integer`).
*   **Field Formatting (`@NumberFormat`)** is the helpful bank teller. He knows that even though the *value* is 1000, people in different countries write it differently (`1,000.00` or `1.000,00`). He can parse these formats into a standard number that the exchange office can use.

Spring's `core.convert` package is the modern, powerful way to handle all of this. It's much simpler and more robust than the old `PropertyEditor` system.

## The `ConversionService`: The Head Office

The `ConversionService` is the main entry point for all conversion logic.
*   Spring Boot configures a default one for us automatically.
*   It knows how to perform common conversions (e.g., `String` -> `int`, `String` -> `boolean`).
*   The `DataBinder` we learned about in the last topic uses this service automatically to convert values before binding them to your objects!

## Creating a Custom `Converter`

What if you need to convert a `String` to your own custom object, like a `Money` object? You can easily teach the `ConversionService` new tricks by creating your own `Converter`.

You just implement the `Converter<S, T>` interface, where `S` is the source type and `T` is the target type.

```java
// A converter that turns "100 USD" into a Money object
public class StringToMoneyConverter implements Converter<String, Money> {
    @Override
    public Money convert(String source) {
        // ... parsing logic ...
        return new Money(amount, currency);
    }
}
```
You then register this converter with the `ConversionService`.

## Field Formatting with Annotations

For common types like numbers and dates, Spring gives us powerful annotations that handle parsing complex formats.

*   `@NumberFormat`: Tells Spring how to parse a `String` into a numeric field (`Double`, `Integer`, etc.). It can handle currency, percentages, and different patterns.
    *   `@NumberFormat(style = Style.CURRENCY)`
*   `@DateTimeFormat`: Tells Spring how to parse a `String` into a date/time field (`LocalDate`, `Date`, etc.).
    *   `@DateTimeFormat(pattern = "dd-MM-yyyy")`

## The Complete Flow: A Visual

Here is how all the pieces work together inside the `DataBinder`.

```mermaid
graph TD
    A[Raw String Input <br> e.g., "€1,234.56"] --> B(DataBinder);
    B -- "I need to set a 'price' field (BigDecimal)" --> C(ConversionService);
    C -- "Do I have a converter for String -> BigDecimal?" --> D{Converter Registry};
    D -- "Yes, the default Number converter handles this!" --> C;
    C -- "It sees the @NumberFormat annotation" --> B;
    C -- "Parses the String and returns a BigDecimal" --> B;
    B -- "Binds the BigDecimal to the object" --> E[Target Object];
```

Mastering this system allows you to build incredibly flexible applications that can accept data in many different formats.

***

### Mawa's Cliffhanger 🧗 (Chapter Finale!)

Mawa, you've done it! You've journeyed through the three pillars of data integrity. You've set up bouncers (`Validator`), hired diligent assistants (`DataBinder`), and worked with expert translators (`ConversionService`). You now have the skills to build applications that are not just functional, but truly robust, secure, and professional. This is a massive step towards becoming a top 1% Spring developer. Take a moment to celebrate this victory! Up next, we'll explore another powerful part of the Spring Core: the Spring Expression Language (SpEL)! 🚀
