### My Notes: AOP Introductions with `@DeclareParents`

Introductions are a powerful AOP concept that allow an Aspect to declare that a target object should implement an interface it wasn't originally designed with. The aspect also provides the implementation for that new interface. This is a form of "inter-type declaration."

The primary use case is to add new, common functionality (especially stateful functionality) to a group of beans without modifying their source code.

The key annotation is `@DeclareParents`.

#### How It Works

To create an introduction, you need three pieces:

1.  **The Interface to Introduce**: This defines the new contract you want your beans to have.
    ```java
    // The new behavior we want to add
    public interface Lockable {
        void lock();
        void unlock();
        boolean isLocked();
    }
    ```

2.  **An Implementation of that Interface**: This class provides the actual logic and state for the new behavior.
    ```java
    // The class that implements the new behavior
    public class DefaultLockable implements Lockable {
        private boolean locked = false;

        @Override public void lock() { this.locked = true; }
        @Override public void unlock() { this.locked = false; }
        @Override public boolean isLocked() { return this.locked; }
    }
    ```

3.  **An Aspect with `@DeclareParents`**: This is the core of the introduction. It ties the other two pieces together and applies them to target beans.
    ```java
    @Aspect
    @Component
    public class LockableAspect {

        @DeclareParents(
            value = "com.example.service..*",   // Target: all beans in the service package
            defaultImpl = DefaultLockable.class // Implementation to use
        )
        public static Lockable mixin; // The interface to introduce
    }
    ```

#### Dissecting `@DeclareParents`

*   `value`: An AspectJ type pattern that specifies which beans will be affected. `com.example.service..*` means "any type in the `com.example.service` package or any of its sub-packages". A `+` at the end (e.g., `com.example.service.SomeInterface+`) means "any type that implements the given interface".
*   `defaultImpl`: Points to the class that will provide the implementation of the new interface. Spring will create an instance of this class for each bean that is advised.
*   `public static Lockable mixin;`: This is the declaration field.
    *   The `static` modifier is required.
    *   The type of the field (`Lockable`) determines the interface that is introduced.
    *   The name of the field (`mixin`) is arbitrary and does not matter.

#### Practical Usage

Once the aspect is active, any bean matching the `value` pattern will be wrapped in a proxy that now implements the new interface. You can then cast the bean to that interface and use its methods.

```java
// Get a service bean from the Spring container
SomeService someService = context.getBean(SomeService.class);

// Even though SomeService.java doesn't say "implements Lockable",
// this cast will succeed because of the aspect's introduction.
Lockable lockable = (Lockable) someService;

lockable.lock();
System.out.println("Service is locked: " + lockable.isLocked()); // true
```

This is extremely useful for adding cross-cutting concerns like auditing, caching, or in this case, a locking mechanism, without having to modify the original source code of dozens of classes. It keeps the core business logic separate from these infrastructural concerns.
