# 🌐 3. Request Scope: Oka Request ki Oka Life!

Mawa, manam ippudu web-specific scopes loki enter avthunnam. Ivanni web application context lo matrame pani chestayi. Vaatillo first di and most commonly used di **Request Scope**.

Ee scope lo, Spring container prathi HTTP request ki oka kottha bean instance ni create chestundi. Aa request process avvatam complete ayyi, response pampagane, aa bean instance destroy aipotundi. Simple ga, oka bean yokka life antha aa single HTTP request varake, mawa!

Imagine nuvvu oka restaurant ki vellav. Nuvvu order chesinappudu, waiter vachi nee order theeskoni kitchen ki isthadu. Nee food prepare ayyi, neeku serve chesi, nuvvu bill pay chesesaka aa waiter pani aipotundi (aa specific "request" ki). Next customer ki malli kottha ga serve chestaru. Aa waiter for that specific order is like a request-scoped bean.

### Key Points:
- **One Instance Per HTTP Request**: Prathi HTTP request ki oka separate bean instance.
- **Web-Aware Applications Only**: Ee scope kevalam `WebApplicationContext` unna applications lo matrame pani chestundi. Normal Spring IoC container lo work avadhu.
- **Perfect for Request Data**: User request ki sambandhinchina data (e.g., user details, tracking ID) hold cheyadaniki idi perfect scope. Ee data aa single request duration lo multiple components ki share cheyochu.
- **Automatic Cleanup**: Request aipogane Spring ye aa bean ni destroy chestundi, so manam memory gurinchi worry avvalsina avasaram ledu.

### Example Diagram:

Ee sequence diagram chudu mawa. User nunchi дఒక HTTP request vachinappudu `RequestData` bean ela create avthundo, and response vellipoyaka adi ela destroy avthundo chupistundi.

```mermaid
%%{init: {'theme': 'dark'}}%%
sequenceDiagram
    participant User
    participant WebServer
    participant SpringContainer
    participant Controller
    participant RequestBean

    User->>WebServer: Sends HTTP GET Request
    WebServer->>SpringContainer: Forwards request
    SpringContainer->>+RequestBean: Creates `RequestBean` instance
    SpringContainer->>+Controller: Injects `RequestBean` into `Controller`
    Controller->>RequestBean: Uses the bean to process data
    Controller-->>-SpringContainer: Processing finished
    SpringContainer-->>-WebServer: Returns HTTP Response
    WebServer-->>User: Sends response
    SpringContainer->>-RequestBean: Destroys `Request-Scoped` Bean
```

### Java Code Example:
Ippudu ee concept ni chuseyadaniki `spring-pro-developer/src/main/java/io/mawa/spring/core/scopes/request/` lo unna code chudu mawa.

Manam `MySingletonController` (idi singleton) lo `RequestScopeBean` ni inject chestunnam. Idi ela sadhyam? **Scoped Proxy** ane magic valla. Adi manam tarvatha deep dive cheddam.

`MySingletonController.java`:
```java
@RestController
public class MySingletonController {

    @Autowired
    private Provider<RequestScopeBean> requestScopeBeanProvider;

    @GetMapping("/request")
    public String handleRequest(HttpServletRequest request) {
        RequestScopeBean bean = requestScopeBeanProvider.get();
        bean.setTrackingId(request.getHeader("X-Tracking-ID"));
        // ... use the bean
        return "Request handled! Tracking ID from bean: " + bean.getTrackingId();
    }
}
```
`RequestScopeBean.java`:
```java
@Component
@RequestScope // The magic annotation!
public class RequestScopeBean {
    private String trackingId;
    // ... getters and setters
}
```

Prathi sari `/request` endpoint ki call vachinappudu, Spring oka kottha `RequestScopeBean` ni create chesi, `Provider` ద్వారా `Controller` ki andistundi. Request aipogane aa bean `destroy` aipotundi.

Next, inko interesting web scope, **Session Scope** gurinchi theluskundam! 🤙
