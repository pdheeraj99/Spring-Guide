# Request Scope: "One Request, One Instance" Rule 🌐

Mawa, manam ippudu web world loki enter avutunnam! Ee web scopes lo first and most common di **Request Scope**. Perulo ne undi, oka bean ni `request` scope lo define cheste, prathi HTTP request ki, Spring oka **kotha, separate instance** ni create chestundi. Aa request aipogane, aa bean instance kuda destroy aipotundi.

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-request](https://docs.spring.io/spring-framework/reference/core/beans/factory-scopes.html#beans-factory-scopes-request)

---
### The Core Puzzle: Singleton Controller meets Request-Scoped Bean 🤔

Let's get straight to the point that causes confusion.
*   **Fact 1:** Mana `@RestController` (e.g., `MySingletonController`) anedi **Singleton**. Application start ayinappudu **oke okka sari** create avutundi.
*   **Fact 2:** Mana `@RequestScope` bean anedi **prati kotha HTTP request ki** create avvali.
*   **The Puzzle:** Controller create ayye time ki, asalu `RequestScopeBean` anedi ledhu! Mari Spring, `MySingletonController` lo unna `private final RequestScopeBean myBean;` lanti field ni ela fill chestundi?

The answer is a powerful, behind-the-scenes trick: **The Scoped Proxy**.

---
### Doubt 1: Who Creates the Proxy and When?

Ee question ki answer, "Assembly Line" lo chuddam.

*   **You (The Programmer):** Nuvvu `MySingletonController.java` ane blueprint design chesav. You also created the `RequestScopeBean.java` blueprint and marked it with `@RequestScope(proxyMode = ...)`. Your job is done.
*   **Spring (The Factory Foreman):** Application start ayye time lo, the Foreman (Spring) starts the assembly line.

**The Full Story: From Startup to Multiple Requests**

This diagram shows the complete lifecycle.
```mermaid
%%{init: {'theme': 'dark', 'themeVariables': { 'primaryColor': '#2d2d2d', 'primaryTextColor': '#fff'}}}%%
sequenceDiagram
    participant Spring as Spring Container 🏭
    participant Controller as MySingletonController 🤖
    participant Proxy as Injected Proxy ✨
    participant BeanA as Real Bean A
    participant BeanB as Real Bean B

    box "Application Startup"
        Spring->>Controller: new MySingletonController()
        Spring->>Proxy: new RequestScopeBean_Proxy()
        Spring->>Controller: Injects Proxy into Controller
    end

    box "User 1's Request"
        Controller->>Proxy: getUserAgent()
        Proxy->>Spring: Get me the bean for this request.
        Spring->>BeanA: new RequestScopeBean()
        Proxy->>BeanA: getUserAgent()
        BeanA-->>Proxy: "User-Agent: curl"
        Proxy-->>Controller: "User-Agent: curl"
    end

    box "User 2's Request"
        Controller->>Proxy: getUserAgent()
        Proxy->>Spring: Get me the bean for this request.
        Spring->>BeanB: new RequestScopeBean()
        Proxy->>BeanB: getUserAgent()
        BeanB-->>Proxy: "User-Agent: Chrome"
        Proxy-->>Controller: "User-Agent: Chrome"
    end
```
**Conclusion:** The **Controller** and the **Proxy** are created only once at startup. A **new Real Bean** is created for each and every HTTP request.

---
### What is a Request-Scoped Bean For?

So, why do we even need this? What's its purpose?

Think of the `RequestScopeBean` as a **clean, request-specific data carrier 📦**. Its main job is to hold information related to a *single* HTTP request, keeping it separate from other requests.

The most powerful use is to **pull data *from* the `HttpServletRequest` and store it in a clean, object-oriented way.** Our example does exactly this: it injects the `HttpServletRequest` and pulls the `User-Agent` header from it.

**The Benefit:** Our `MySingletonController` doesn't need to know about the messy `HttpServletRequest`. It only knows about the clean `RequestScopeBean` object, which makes our code much cleaner and easier to test.

---
### Code and How to Run

Ee concepts ni live lo chudadaniki, manam create chesina code chudu:
*   **`WebApp.java`**: The main application entry point that now prints all bean names.
*   **`RequestScopeBean.java`**: Our request-scoped bean that now holds the User-Agent.
*   **`MySingletonController.java`**: The controller (renamed for clarity!) that uses the bean.

**How to Run:**
1.  `cd Spring-Project`
2.  `mvn spring-boot:run`
3.  Open another terminal and run `curl http://localhost:8080/request-scope`. Check the server logs and the JSON response!

I hope this new, detailed, evidence-based explanation makes the concept crystal clear, mawa! Let me know.
