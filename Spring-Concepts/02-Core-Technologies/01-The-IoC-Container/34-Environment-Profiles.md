# @Profile: Beans for Different Occasions* 🎭

Mawa, manam ippudu రాసే code mana local machine lo run avutundi. Kani, ade code ni testing server lo, production server lo kuda run cheyali. Prati environment ki konni settings veru ga untayi. For example:
-   **Local Machine:** Manam H2 ane in-memory database vadatam.
-   **Production Server:** Manam powerful aina PostgreSQL database vadali.

Application code ni change cheyakunda, just environment batti correct `DataSource` bean ni ela activate cheyali? Daaniki answer eh **`@Profile`**.

\* **(Extremely important for real-world applications and interviews.)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-definition-profiles](https://docs.spring.io/spring-framework/reference/core/beans/environment.html#beans-definition-profiles)

### The Wardrobe Analogy 👕👖🧥
Imagine your Spring `ApplicationContext` is your wardrobe. You have different clothes for different seasons.
-   **Winter Coat:** `@Profile("winter")` - Ee coat nuvvu winter lo matrame veskuntav.
-   **Swimsuit:** `@Profile("summer")` - Ee suit nuvvu summer lo matrame veskuntav.
-   **Jeans:** (no profile) - Ee jeans nuvvu ഏ season lo ayina veskuntav.

Nuvvu bayatiki velle mundu, "Ee roju season enti?" ani chustav. "winter" anukunte, nuvvu Jeans and Winter Coat veskuni velthav. "summer" anukunte, Jeans and Swimsuit tho velthav.

`@Profile` anedi ide pani chestundi. Nuvvu Spring ki active profile enti anedi cheptav, adi aa profile ki match ayye beans ni matrame create chestundi.

```mermaid
graph TD
    A["Active Profile = 'dev'"] --> B{Spring Container};

    subgraph "All Bean Definitions"
        C["DevDataSource<br/>(@Profile(\"dev\"))"];
        D["ProdDataSource<br/>(@Profile(\"prod\"))"];
        E["CommonService<br/>(no profile)"];
    end

    B -- "scans" --> C;
    B -- "scans" --> D;
    B -- "scans" --> E;

    subgraph "Beans Created in Context ✅"
        C_active["devDataSource"];
        E_active["commonService"];
    end

    subgraph "Beans NOT Created ❌"
        D_inactive["prodDataSource"];
    end

    C --> C_active;
    E --> E_active;
    D --> D_inactive;

    style D_inactive fill:#666,color:#ccc,stroke-dasharray: 5 5
```

### How to Use `@Profile`
You can put this annotation on any `@Component` or `@Bean` method.
`@Profile("dev")`
`@Component`

You can also use profile expressions:
-   `@Profile("!prod")`: Not in production.
-   `@Profile("dev & local-storage")`: Both "dev" AND "local-storage" profiles must be active.
-   `@Profile("dev | test")`: Either "dev" OR "test" profile must be active.

### How to Activate a Profile
The most common way is to set the `spring.profiles.active` property. You can do this in:
1.  `application.properties` file.
2.  As a command-line argument (this overrides the properties file).

---
### Code Reference: The Environment-Specific DataSource
The code for this is in the `io.mawa.spring.core.env.profiles` package.

1.  **`DataSource.java`**: Our simple interface.
2.  **`DevDataSource.java`**: The implementation for the "dev" profile.
3.  **`ProdDataSource.java`**: The implementation for the "prod" profile.
4.  **`DataSourceService.java`**: A service that autowires the `DataSource` interface.
5.  **`ProfileConfig.java`**: A configuration class that just enables component scanning.
6.  **`ProfileDemoApp.java`**: The main application that starts the context.

### How to Run
Ee demo ki manam active profile ni command line nunchi pass cheddam.

**Scenario 1: Running with the "dev" profile**
```bash
# We use -Dspring.profiles.active=dev to activate the dev profile
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.env.profiles.ProfileDemoApp" -Dspring.profiles.active=dev
```
**Expected Output:**
```
--- Starting the Spring Container ---
...
--- DataSource Service ---
Using DataSource: Connected to DEV H2 Database!
...
```

**Scenario 2: Running with the "prod" profile**
```bash
# This time, we activate the prod profile
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.env.profiles.ProfileDemoApp" -Dspring.profiles.active=prod
```
**Expected Output:**
```
--- Starting the Spring Container ---
...
--- DataSource Service ---
Using DataSource: Connected to PRODUCTION PostgreSQL Database!
...
```
Chusava! Oke okka command-line argument change cheste, mana application behavior antha maaripoindi, without touching a single line of code. That is the power of Spring Profiles!

Ready to learn how to load properties from external files using `@PropertySource`? Let's go! 💪
