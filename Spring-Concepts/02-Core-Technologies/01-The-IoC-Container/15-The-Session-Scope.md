# Session Scope: The "User's Shopping Cart" 🛒

Mawa, manam request scope gurinchi nerchukunnam (prati request ki kotha bean). Ippudu inko important web scope gurinchi nerchukundam: **Session Scope**.

Perulo ne undi, oka bean ni `session` scope lo define cheste, prathi user session ki, Spring oka **kotha, separate instance** ni create chestundi. User browser close chese varaku, or session expire ayye varaku, aa user ki ade bean instance malli malli vastundi.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-session](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-session)

### The Shopping Cart Analogy 🛍️
Idi session scope ki perfect analogy, and real-world use case kuda.
-   **User A (You):** Nuvvu oka e-commerce website open chesav. Nuvvu oka `Milk` packet add chesav. Ee `Milk` packet nee personal **Shopping Cart** (Session Bean) loki veltundi.
-   **User B (Your Friend):** At the same time, mee friend vere computer lo ade website open chesadu. Atanu `Bread` add chesadu. Ee `Bread` packet atani personal **Shopping Cart** (vere Session Bean) loki veltundi.
-   Nee cart lo `Bread` kanipinchadu, mee friend cart lo `Milk` kanipinchadu. Prati user ki వాళ్ళ own separate, private cart untundi for their entire browsing session.

The same **scope mismatch** problem and **proxy solution** that we saw for request scope applies here too. A singleton controller needs a proxy to talk to a temporary session bean.

**The Flow:**
```mermaid
%%{init: {'theme': 'dark', 'themeVariables': { 'primaryColor': '#2d2d2d', 'primaryTextColor': '#fff'}}}%%
graph TD
    subgraph "User A's Session"
        U1[User A] -->|Request 1| C(Controller);
        C -->|uses proxy to get| S1["🛒 Cart A<br/>(Milk)"];
        U1 -->|Request 2| C;
        C -->|uses proxy to get| S1;
    end

    subgraph "User B's Session"
        U2[User B] -->|Request 1| C;
        C -->|uses proxy to get| S2["🛒 Cart B<br/>(Bread)"];
    end

    C -- is a --> Singleton;
    S1 -- is a --> SessionBean;
    S2 -- is a --> SessionBean;

    style C fill:#333,stroke:#8f8,color:#fff
    style S1 fill:#552,stroke:#ff8,color:#fff
    style S2 fill:#255,stroke:#8ff,color:#fff
```
Ee diagram lo, oke Singleton Controller, iddari veru veru users ki, veru veru shopping carts ni serve chestondi, thanks to the magic of proxies.

---
### Code Reference: Let's Go Shopping!
Ee concept ni chudadaniki, manam `io.mawa.spring.core.scopes.session` package lo code create cheddam.
1.  **`ShoppingCartBean.java`:** Mana session-scoped bean. `@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)` tho untundi. Deenilo items `List` untundi.
2.  **`SessionDemoController.java`:** Oka REST controller with two endpoints: `/session/add` and `/session/cart`.

### How to Run: The `curl` Session Trick
Session ni test cheyali ante, manam cookies ni manage cheyali. `curl` lo ee pani cheyadaniki manaki two flags unnayi:
-   `-c cookie-jar.txt`: Server nunchi vachina cookies ni ee file lo save cheyi.
-   `-b cookie-jar.txt`: Ee file lo unna cookies ni server ki malli pampinchu.

**Test Plan:**
1.  Start the app: `mvn spring-boot:run`
2.  **First Request (User A):** Add milk. Ee command `JSESSIONID` ane cookie ni `cookies.txt` lo save chestundi.
    ```bash
    curl -c cookies.txt http://localhost:8080/session/add?item=Milk
    ```
3.  **Second Request (User A):** View the cart. Manam save chesina cookie ni pampistunnam, so Spring ki telustundi idi ade user ani.
    ```bash
    curl -b cookies.txt http://localhost:8080/session/cart
    ```
    *Output should contain "Milk".*
4.  **Third Request (User A):** Add bread. Again, using the same cookie.
    ```bash
    curl -b cookies.txt -c cookies.txt http://localhost:8080/session/add?item=Bread
    ```
5.  **Fourth Request (User A):** View the cart again.
    ```bash
    curl -b cookies.txt http://localhost:8080/session/cart
    ```
    *Output should contain "Milk" and "Bread".*
6.  **Fifth Request (User B):** A new user. Manam cookie em pampatledu, so Spring kotha session, kotha cart create chestundi.
    ```bash
    curl http://localhost:8080/session/cart
    ```
    *Output should be an empty cart!*

Ee code antha manam next steps lo create cheddam! Ready aa? 🛒🔥
