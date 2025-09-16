# @Resource: The "Find by Name" Specialist* 🔎

Mawa, manam ippati varaku Spring-specific aina `@Autowired` gurinchi nerchukunnam. Kani, Spring anedi standard Java specifications ni kuda support chestundi. Alaanti oka standard annotation eh **`@Resource`**. Idi JSR-250 specification nunchi vachindi (same family as `@PostConstruct` and `@PreDestroy`).

\* **(Very Important for interviews. The difference between `@Autowired` and `@Resource` is a classic question!)**

### Source URL
[https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/resource.html](https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/resource.html)

### The Office Supply Analogy 🗄️
Imagine nuvvu office manager (`Spring Container`) daggarki velli supplies adugutunnav.
-   **`@Autowired`:** "Naaku oka *pen* kavali." (You ask by **type**). Manager velli, pen section lo vethiki, primary pen edo okati istadu.
-   **`@Resource`:** "Naaku aa '*blueGelPen*' ane label unna box lo nunchi pen kavali." (You ask by **name**). Manager direct ga aa specific box ki velli, andulo unna pen ni istadu.

**The Golden Rule of `@Resource`:**
`@Resource` tries to find a bean by its **name** first.
-   If you do `@Resource(name = "myBean")`, it will look for a bean with the ID "myBean".
-   If you just do `@Resource`, it will try to match by the **field or method name**. For example, `@Resource private DataSource dataSource;` will look for a bean named "dataSource".
-   Only if it cannot find a match by name, it will then fall back to matching by **type**.

This is the exact opposite of `@Autowired`, which matches by **type** first.

### `@Autowired` vs. `@Resource`: The Ultimate Comparison
| Feature         | `@Autowired` (Spring Specific)              | `@Resource` (Java Standard - JSR-250)       |
| --------------- | ------------------------------------------- | ------------------------------------------- |
| **Default Match** | By **Type**                                 | By **Name**                                 |
| **Fallback Match**| Matches by name if type fails (rarely used) | Matches by **Type** if name match fails.    |
| **How to be Specific**| `@Qualifier("someName")`                  | `@Resource(name = "someName")`              |
| **`required` attribute?** | Yes, `@Autowired(required=false)`       | No. If the bean is not found, it throws an error. Optionality is not built-in. |

**The Main Takeaway:** Use `@Autowired` for type-safe, flexible injection. Use `@Resource` when you want to be very explicit about injecting a bean by its unique name.

---
### Code Reference: The Export Specialist
The code for this is in the `io.mawa.spring.core.annotationconfig.resource` package.

1.  **`ExportService.java`**: Our simple interface.
2.  **`CsvExportService.java`**, **`JsonExportService.java`**: The two implementations.
3.  **`ResourceConfig.java`**: Our configuration class, which defines the two beans with explicit names: `csvExporter` and `jsonExporter`. It also enables component scanning for our service.
4.  **`ReportService.java`**: This service uses `@Resource(name = "jsonExporter")` to inject the specific bean it wants.
5.  **`ResourceApp.java`**: The main application to run the demo.

### How to Run
Project root `Spring-Project` folder lo undi, ee command run cheyi:
```bash
mvn compile exec:java -Dexec.mainClass="io.mawa.spring.core.annotationconfig.resource.ResourceApp"
```
**Expected Output:**
```
--- Starting the Spring Container ---
--- Container started successfully! ---
--- Report Service ---
Generating report...
Exporting 'Monthly Sales Data' to JSON format. {}
```
Chusava! `ReportService` lo manam `jsonExporter` ni name tho adigam kabatti, Spring correct ga `JsonExportService` ni inject chesindi, even though another `ExportService` (the CSV one) was also available. That's the power of asking by name!

You've now mastered `@Autowired`, `@Primary`, `@Qualifier`, and `@Resource`. You are a pro at handling dependency injection ambiguity! What's next on our list? 💪
