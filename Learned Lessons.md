# PicoContainer - Complete Q&A Summary

## Table of Contents
1. What is PicoContainer?
2. How Does Constructor Injection Work?
3. Is PicoContainer Related Only to Cucumber?
4. Is TestContext Related to PicoContainer?
5. What Design Pattern is TestContext?
6. Best Practices Summary

---

## 1. What is PicoContainer?

### Definition
**PicoContainer** is a lightweight **Dependency Injection (DI) container** that Cucumber uses to manage object creation and sharing between step definition classes.

### What It Does
It automatically:
- Creates objects (like TestContext, page objects, etc.)
- Injects dependencies into constructors
- Shares objects between different step definition classes in the same scenario
- Manages object lifecycle (creates new instances per scenario)

### Key Characteristics
- **Lightweight**: ~500KB
- **Simple**: Constructor-based injection
- **No annotations needed**
- **No XML configuration**
- **Perfect for small to medium projects**

### Maven Dependency
```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-picocontainer</artifactId>
    <version>7.14.0</version>
    <scope>test</scope>
</dependency>
```

---

## 2. How Does Constructor Injection Work?

### Without PicoContainer (Manual Approach)
```java
public class LoginSteps {
    // You manually access static variables
    public void userLogsIn() {
        Hooks.loginPage.login("user", "pass"); 
    }
}
```

### With PicoContainer (Automatic Injection)
```java
public class LoginSteps {
    private TestContext context;
    
    // PicoContainer automatically calls this constructor
    // and provides a TestContext instance
    public LoginSteps(TestContext context) {
        this.context = context; // Injected automatically!
    }
    
    public void userLogsIn() {
        context.getLoginPage().login("user", "pass");
    }
}
```

### How It Works Internally

**Step 1**: Add the dependency to your project

**Step 2**: Cucumber detects PicoContainer automatically when it runs

**Step 3**: Cucumber:
1. Scans all your step definition classes
2. Sees they have constructors with parameters
3. Automatically creates instances of those parameters
4. Injects them into the constructors

**Step 4**: You just use the injected objects - no manual instantiation needed!

### Do You Need to Instantiate Anything?

**NO!** PicoContainer does everything automatically.

You DON'T write:
```java
TestContext context = new TestContext(); ❌
LoginSteps loginSteps = new LoginSteps(context); ❌
```

You ONLY write:
```java
public class LoginSteps {
    private TestContext context;
    
    // Just declare the constructor - PicoContainer handles the rest!
    public LoginSteps(TestContext context) {
        this.context = context;
    }
}
```

---

## 3. Is PicoContainer Related Only to Cucumber?

### Short Answer
**No, PicoContainer is NOT only for Cucumber**. It's a standalone, general-purpose Dependency Injection (DI) container.

### What is PicoContainer Really?
PicoContainer is a **standalone Java DI framework** that can be used in any Java project. It's been around since the early 2000s.

### You Can Use It In:
- Any Java application (console apps, web apps, etc.)
- Cucumber BDD projects (has special integration)
- JUnit tests (without Cucumber)
- Standalone projects
- Microservices

### Cucumber's DI Container Options

| DI Container | Cucumber Dependency | Usage |
|--------------|-------------------|--------|
| **PicoContainer** | `cucumber-picocontainer` | Most lightweight, simple |
| **Spring** | `cucumber-spring` | If you use Spring Framework |
| **Guice** | `cucumber-guice` | Google's DI framework |
| **CDI (Weld)** | `cucumber-weld` | Java EE standard |
| **OpenEJB** | `cucumber-openejb` | Enterprise Java Beans |

### PicoContainer Standalone Example (Non-Cucumber)
```java
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class PicoExample {
    public static void main(String[] args) {
        MutablePicoContainer container = new DefaultPicoContainer();
        
        // Register classes
        container.addComponent(Database.class);
        container.addComponent(UserService.class);
        
        // Get instance with dependencies injected
        UserService service = container.getComponent(UserService.class);
        service.createUser("John");
    }
}
```

### Why Cucumber Recommends PicoContainer

For test automation projects, PicoContainer is ideal because:
1. **Lightweight** - Test projects don't need Spring's heavy features
2. **Simple** - No complex configuration
3. **Fast** - Quick startup time for tests
4. **Constructor injection** - Clear dependencies, easy to understand
5. **Zero magic** - No annotations, no surprises

### When to Use Each with Cucumber

**Use cucumber-picocontainer when:**
- Your project is pure test automation (no application code)
- You want something simple and lightweight
- You're not using Spring in your application

**Use cucumber-spring when:**
- Your application already uses Spring
- You want to reuse Spring beans in tests
- You need Spring features (like @Transactional tests)

**Use cucumber-guice when:**
- Your application uses Guice
- You want annotation-based DI but lighter than Spring

### Can You Use Cucumber WITHOUT Any DI Container?

**YES!** You can use static variables (your current approach), but this has limitations:
- ❌ Not thread-safe for parallel execution
- ❌ Tight coupling (steps depend on Hooks class)
- ❌ Harder to test in isolation

---

## 4. Is TestContext Related to PicoContainer?

### Short Answer
**No, TestContext is NOT related to PicoContainer.** TestContext is just a **regular Java class that YOU create** to hold shared test data.

### What is TestContext?

**TestContext** is simply a **design pattern** - a custom class you create to store and share objects between your step definition classes.

**Key Points:**
- You create it - It's YOUR class, not part of any framework
- Just a POJO (Plain Old Java Object) - No special annotations needed
- Works with or without PicoContainer
- Common naming alternatives: World, ScenarioContext, TestWorld, SharedContext

### TestContext Example
```java
// Your custom class - nothing special about it
public class TestContext {
    private WebDriver driver;
    private LoginPage loginPage;
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
}
```

### TestContext WITHOUT PicoContainer
You'd share it using static variables:

```java
public class Hooks {
    public static TestContext context = new TestContext(); // Manual creation
    
    @Before
    public void setUp() {
        WebDriver driver = new FirefoxDriver();
        context.setDriver(driver);
    }
}

public class LoginSteps {
    @Given("User logs in")
    public void userLogsIn() {
        Hooks.context.getLoginPage().login("user", "pass");
    }
}
```

### TestContext WITH PicoContainer
No static variables needed - PicoContainer manages sharing:

```java
// Same TestContext class - no changes!
public class Hooks {
    private TestContext context; // Instance variable, not static!
    
    public Hooks(TestContext context) { // PicoContainer injects it
        this.context = context;
    }
    
    @Before
    public void setUp() {
        WebDriver driver = new FirefoxDriver();
        context.setDriver(driver);
    }
}

public class LoginSteps {
    private TestContext context; // Same instance as in Hooks!
    
    public LoginSteps(TestContext context) { // PicoContainer injects it
        this.context = context;
    }
    
    @Given("User logs in")
    public void userLogsIn() {
        context.getLoginPage().login("user", "pass");
    }
}
```

### The Relationship Explained

```
TestContext (YOUR class)
- Just a container for shared data
- Works with ANY approach
            ↓
PicoContainer (Framework)
- Creates ONE TestContext per scenario
- Injects it into all step classes
- Ensures everyone gets the SAME instance
```

### Analogy

**TestContext** = A backpack that holds your test items (driver, pages, data)

**PicoContainer** = A smart assistant that:
- Creates ONE backpack per test
- Hands the SAME backpack to everyone who needs it
- Throws it away after the test finishes

**Without PicoContainer**, you'd use a static variable (like a shared locker that everyone accesses).

### Summary Table

| Question | Answer |
|----------|--------|
| Is TestContext part of PicoContainer? | No, it's YOUR custom class |
| Is TestContext part of Cucumber? | No, it's a design pattern |
| Do I need PicoContainer to use TestContext? | No, but it makes sharing easier |
| What does PicoContainer do with TestContext? | Creates and shares ONE instance per scenario |
| Can I name it something else? | Yes! World, Context, SharedState, etc. |

---

## 5. What Design Pattern is TestContext?

### Primary Pattern: Context Object Pattern

Also known as:
- Context Pattern
- Shared Context Pattern
- Session Context Pattern

**Definition:** A pattern that encapsulates shared state and provides a single point of access to related objects within a specific context.

**Purpose:**
- Avoid global/static variables
- Share data across different layers/classes
- Maintain state throughout a workflow

### Secondary Pattern: Service Locator Pattern

When TestContext retrieves page objects:
```java
public LoginPage getLoginPage() {
    if (loginPage == null) {
        loginPage = new LoginPage(driver);
    }
    return loginPage;
}
```

### Supporting Pattern: Lazy Initialization Pattern

Initializing page objects only when first accessed:
```java
public LoginPage getLoginPage() {
    if (loginPage == null) {  // ← Lazy initialization
        loginPage = new LoginPage(driver);
    }
    return loginPage;
}
```

### Integration Pattern: Dependency Injection Pattern

When PicoContainer injects TestContext:
```java
public class LoginSteps {
    private TestContext context;
    
    // Constructor Injection
    public LoginSteps(TestContext context) {
        this.context = context;
    }
}
```

### Common Names in Testing Community

| Perspective | Pattern Name |
|------------|--------------|
| Primary Pattern | Context Object Pattern |
| Cucumber Community | World Object Pattern |
| Testing Community | Scenario Context Pattern |
| Gang of Four | Combination of Facade + Registry |
| Your Code | TestContext Pattern |

### Pattern Classification

| Pattern Category | Pattern Name | Where It's Used |
|-----------------|--------------|-----------------|
| Structural Pattern | Context Object | TestContext class itself |
| Creational Pattern | Lazy Initialization | Page object creation |
| Behavioral Pattern | Service Locator | Retrieving page objects |
| Integration Pattern | Dependency Injection | Sharing via PicoContainer |

---

## 6. Best Practices Summary

### Current Approach (Static Variables)
```java
public class Hooks { 
    public static WebDriver driver; 
    public static CartPage cartPage; 
    // ... other static variables
    
    @Before 
    public void setUp() { 
        driver = new FirefoxDriver(); 
        cartPage = new CartPage(driver); 
    } 
}
```

**Issues:**
- ❌ Not thread-safe for parallel execution
- ❌ Tight coupling between classes
- ❌ All pages initialized upfront (even if not used)
- ❌ Hard to test in isolation

### Recommended Approach (PicoContainer + TestContext)

**Step 1: Create TestContext Class**
```java
public class TestContext {
    private WebDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;
    // ... other pages
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
    // Lazy initialization
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
    
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }
}
```

**Step 2: Update Hooks Class**
```java
public class Hooks {
    private TestContext context;
    
    // Constructor injection by PicoContainer
    public Hooks(TestContext context) {
        this.context = context;
    }
    
    @Before
    public void setUp() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("base.url"));
        
        context.setDriver(driver);
    }
    
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = context.getDriver();
        
        if (driver != null) {
            String screenshotName = scenario.getName() + "_" + scenario.getStatus();
            ScreenShotUtils.takeScreenshot(driver, screenshotName);
            ScreenShotUtils.AttachScreenshotToAllureReport(screenshotName);
            
            driver.quit();
        }
    }
}
```

**Step 3: Update Step Definitions**
```java
public class LoginSteps {
    private TestContext context;
    
    // Constructor injection by PicoContainer
    public LoginSteps(TestContext context) {
        this.context = context;
    }
    
    @Given("User navigates to login page")
    public void userNavigatesToLoginPage() {
        context.getLoginPage().verifyLoginPageDisplayed();
    }
    
    @When("User logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        String username = JsonReader.getAttributeName("user_data", "standard_user");
        String password = JsonReader.getAttributeName("user_data", "password");
        
        context.getLoginPage().login(username, password);
    }
}
```

### Benefits Comparison

| Aspect | Static Variables | PicoContainer + TestContext |
|--------|-----------------|---------------------------|
| Thread Safety | ❌ Not safe | ✅ Safe |
| Parallel Execution | ❌ Not supported | ✅ Supported |
| Memory Efficiency | ❌ All pages loaded | ✅ Lazy loading |
| Coupling | ❌ Tight | ✅ Loose |
| Testability | ❌ Hard | ✅ Easy |
| Maintenance | ❌ Difficult | ✅ Easy |

### Key Advantages

1. **Thread-safe** - Each scenario gets its own context instance
2. **Lazy initialization** - Pages created only when needed
3. **Parallel execution ready** - No static variables
4. **Clean separation** - Test data retrieval in steps, not hooks
5. **Testable** - Easy to mock and unit test
6. **Scalable** - Easy to add new pages or context data

---

## Quick Reference

### To Implement PicoContainer in Your Project:

1. **Add dependency** to pom.xml
2. **Create TestContext class** (your custom POJO)
3. **Update Hooks** - Use constructor injection
4. **Update Step Definitions** - Use constructor injection
5. **Remove all static variables**
6. **Run tests** - PicoContainer handles the rest automatically!

### Key Concepts to Remember:

- **PicoContainer** = DI framework (manages object creation and sharing)
- **TestContext** = Your custom class (holds shared test data)
- **Constructor Injection** = Automatic (PicoContainer does it)
- **No Instantiation Needed** = PicoContainer creates everything
- **One Instance Per Scenario** = Automatic isolation

---

*Document created for UI Automation Testing Project using Selenium, TestNG, Cucumber, and Allure Report*