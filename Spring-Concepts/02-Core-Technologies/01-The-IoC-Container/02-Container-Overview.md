# Container Overview: The Magic Box 📦

Mawa, manam last time IoC ante ento chusam. Ippudu aa magic ni chese "Magic Box" gurinchi matladukundam. Ade **Spring IoC Container**.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/basics.html](https://docs.spring.io/spring-framework/reference/core/beans/basics.html)

### Why it Matters
Theory ni practice chese time vachindi! Ee section lo manam first time oka Spring application ni run chesi chustam. Container ni ela start cheyali, daaniki mana classes gurinchi ela cheppali, and daani nunchi mana objects (beans) ni ela aḍagali anedi ikkade nerchukuntam. Idi Spring app ki first building block.

---

### How Does the Container Work?
Container pani cheyadaniki, daaniki konni instructions kavali. "Evariని create cheyali? Valla dependencies enti?" lanti vishayalu manam daaniki cheppali. Ee instructions ne **Configuration Metadata** antam.

Ee metadata manam 3 ways lo ivvochu:
1.  **Annotation-based:** Class meeda ne `@Component`, `@Service` lanti annotations petti cheppadam. (Manam ekkuva ide vadatam).
2.  **Java-based:** Separate ga `@Configuration` classes petti, daantlo `@Bean` methods raasi cheppadam. (Idi kuda chala common).
3.  **XML-based:** Old style, separate XML files lo `<bean>` tags raasi cheppadam. (Ippudu takkuva use chestunnaru, but legacy projects lo kanipistundi).

The flow looks like this:
```mermaid
graph LR
    subgraph "Your Code"
        A[App Classes <br/>(POJOs like MyService)]
        B[Config Metadata <br/>(@Configuration, @ComponentScan)]
    end

    subgraph "Spring Framework"
        C{ApplicationContext}
    end

    subgraph "Result ✨"
        D[Fully Configured <br/> & Executable App]
    end

    A -- "feeds into" --> C
    B -- "feeds into" --> C
    C -- "creates & wires" --> D
```

### Instantiating the Container (Let's Do It! 🔥)
Manam standalone applications lo container ni start cheyadaniki `ApplicationContext` implementations ni use chestam. Modern, annotation-based configuration kosam manam use chesedi **`AnnotationConfigApplicationContext`**.

Let's see our first piece of real Spring code!

### Code Reference
Ee concept ni chudadaniki, `Spring-Project` lo `io.mawa.spring.core.container` package chudu.

1.  **`AppConfig.java` - The Configuration**
    - Ee class Spring ki cheptundi, "Ekkada scan cheyalo beans kosam" ani. `@ComponentScan` anedi aa panine chestundi.
    ```java
    // Path: Spring-Project/src/main/java/io/mawa/spring/core/container/AppConfig.java
    @Configuration
    @ComponentScan(basePackages = "io.mawa.spring.core.container")
    public class AppConfig {
    }
    ```

2.  **`MyService.java` - The Bean**
    - Idi mana simple Java object (POJO). `@Component` annotation tho manam Spring ki cheptunnam, "Ee class object ni create chesi, manage cheyi" ani.
    ```java
    // Path: Spring-Project/src/main/java/io/mawa/spring/core/container/MyService.java
    @Component
    public class MyService {
        public void doSomething() {
            System.out.println("Hello from MyService! The container is working! 🎉");
        }
    }
    ```

3.  **`ContainerDemoApp.java` - The Main App**
    - Ikkade asalu magic jarigedi.
    ```java
    // Path: Spring-Project/src/main/java/io/mawa/spring/core/container/ContainerDemoApp.java
    public class ContainerDemoApp {
        public static void main(String[] args) {
            // 1. Instantiate the container using our config class
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

            // 2. Ask the container for our bean
            MyService myService = context.getBean(MyService.class);

            // 3. Use the bean!
            myService.doSomething();
        }
    }
    ```

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.container.ContainerDemoApp"
```
**Output:**
```
Hello from MyService! The container is working! 🎉
```

Anthe mawa! We just created our first Spring application. Container start aindi, `MyService` bean ni find chesi create chesindi, and manam daanni adigithe ichesindi.

From now on, we will build on top of this foundation. Next, let's dive deeper into what a "bean" really is. Ready? 💪
