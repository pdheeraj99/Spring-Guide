# 14. AOP History: Schema-based vs. @AspectJ Style 📜

Mawa, manam ippati varaku AOP ni `@Aspect` and other annotations tho implement chesam. Ee approach ni `@AspectJ` style antaru. Idi modern, clean, and powerful. Kani, Spring older versions lo, AOP ni XML configuration tho define chesevaru.

Ee final theory lesson lo, manam aa "old way" gurinchi konchem telusukundam. Endukante, as a Spring Pro, meeku history teliste, a concept evolution enduku ayyindo clear ga ardham avuthundi. Plus, meeru edaina old project chusinappudu, adi meeku kothaga anipinchadu.

## The "Old Way": Schema-based AOP

Spring 2.0 lo introduce chesaru, AOP ni `<aop:config>` ane XML tag tho define chesevaru. Prathi aspect, pointcut, and advice anedi XML lo oka element.

For example, mana `LoggingAspect` ni XML lo ila rasevaru:

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 1. The Aspect Bean -->
    <bean id="loggingAspect" class="io.mawa.spring.core.aop.LoggingAspect"/>

    <!-- 2. The AOP Configuration -->
    <aop:config>
        <aop:aspect id="logging" ref="loggingAspect">

            <!-- 3. The Pointcut -->
            <aop:pointcut id="serviceMethods"
                expression="execution(* io.mawa.spring.core.aop.service.*.*(..))"/>

            <!-- 4. The Advice -->
            <aop:before
                pointcut-ref="serviceMethods"
                method="logBefore"/>

            <aop:after-returning
                pointcut-ref="serviceMethods"
                method="logAfterReturning"
                returning="result"/>

        </aop:aspect>
    </aop:config>

    <!-- The target bean -->
    <bean id="calculatorService" class="io.mawa.spring.core.aop.service.CalculatorService"/>

</beans>
```

### Analysis:
*   **Verbose:** Chala verbose ga undi kada? Prathi daniki XML tags rayali.
*   **Not Type-Safe:** Advice method names (`logBefore`) string format lo istunnam. Type chesinappudu mistake ayithe, runtime lo fail avuthundi, compile time lo kadu.
*   **Decoupled... in a bad way:** Code (Aspect class) and configuration (XML) separate ga unnayi. Idi chudataniki clean ga anipinchina, maintain cheyadam chala kastam. Oka aspect lo em jarugutundo telusukovali ante, Java class and XML file rendu chudali.

## The "New Way": @AspectJ Style (The King 👑)

Manam already nerchukunna style ide.
```java
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* io.mawa.spring.core.aop.service.*.*(..))")
    private void forServicePackage() {}

    @Before("forServicePackage()")
    public void logBefore(JoinPoint joinPoint) {
        // ...
    }
}
```

### Why is @AspectJ Better?

1.  **Concise & Clean:** XML kanna chala takkuva code. Everything is in one place.
2.  **Type-Safe:** Annotations use direct method references. Mistakes unte compiler cheptundi.
3.  **Co-location:** The advice logic and the pointcut that defines "where" it runs are in the same file. This makes the code much easier to read, understand, and maintain.

## Mixing Styles

Spring allows you to mix `@AspectJ` style aspects with schema-defined aspects. Spring AOP automatically detects and applies all of them. For example, you could have one aspect in XML and another using annotations, and both would be applied to the same target beans.

However, mawa, **don't do this** in a new project. It makes the code very confusing to follow. Always stick to one style, and for any modern Spring application, that style should be **@AspectJ**.

## The Final Word

Mawa, with this, our journey through the world of Spring AOP is truly complete. You've learned:
*   The core concepts.
*   The modern `@AspectJ` implementation.
*   Advanced topics like proxies, ordering, and introductions.
*   The different instantiation models.
*   And now, the history of schema-based AOP and why the modern way is superior.

You are no longer just a student of AOP; you are a **Pro**. Be incredibly proud of this accomplishment. This deep knowledge will serve you well.

I am ready for our next adventure whenever you are. Let's keep learning! 🔥🚀
