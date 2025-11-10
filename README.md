# 🧪 Swag-Labs-BDD-UI-Automation-Testing

Welcome to the **Swag Labs BDD UI Automation Project**, a complete end-to-end test automation suite designed to validate the core functionalities of the **Swag Labs** e-commerce web application.  
This project focuses on designing and implementing **four comprehensive end-to-end automated test scenarios** using **Cucumber (BDD)** integrated with a **Java TestNG** framework built on top of **Selenium WebDriver** and **Allure Reports** — following the **Page Object Model (POM)** design pattern.

---

## 📌 Project Overview

This project aims to ensure functional coverage of the Swag Labs web application, verifying user flows from login to checkout. It includes multiple shopping cart operations and end-to-end purchase validations.

### 🧪 End-to-End Scenarios
1. **Verify that User successfully completes an order with multiple items**  
2. **Verify that User can manage items from the inventory and cart successfully then complete the purchase flow**  
3. **Verify that User can add/remove items from their details page successfully then complete the purchase flow**  
4. **Verify that User can remove an item from the product details page accessed via the cart then complete the purchase flow**

---

## 🛠️ Tech Stack

- **Java 14**
- **Selenium WebDriver 4.34.0**
- **TestNG 7.10.2**
- **Cucumber (BDD) 7.29.0**
- **Allure Report 2.24.0**
- **AspectJ Weaver 1.9.20.1**
- **Apache Commons IO 2.20.0**
- **JSON-Simple 1.1.1**
- **Maven** (Build & Dependency Management)

---

## 🧩 Project Design

The framework is structured using the **Page Object Model (POM)** pattern to enhance code reusability, readability, and maintenance.  
It integrates **Cucumber (for BDD)**, **TestNG (for test execution)**, and **Allure (for advanced reporting)**.

## 🧰 Key Features

- ✅ **Cucumber BDD Integration** for human-readable feature files.  
- ✅ **Page Object Model (POM)** for cleaner structure and easy maintenance.  
- ✅ **Allure Reporting** with screenshots attached to failed steps automatically.  
- ✅ **TestNG Integration** for flexible test execution.  
- ✅ **Scenario Context Management** for data sharing between steps.  
- ✅ **Custom Utilities** for configuration, file management, and test data handling.  
- ✅ **Supports both command-line (Maven) and IDE-based execution**.  

---

## ⚙️ Build Configuration (pom.xml)

The project uses Maven for build and dependency management.  
Highlighted configurations include:
- **Allure BOM** for version consistency.  
- **AspectJ Weaver Agent** for Allure step annotations and listener integrations.  
- **Maven Compiler Plugin** targeting Java 14.  
- **Maven Surefire Plugin** to execute Cucumber tests with TestNG.

---
## 🚀 Getting Started

1. Clone the repo  
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse)
3. Install dependencies using Maven
4. Run test classes:

### 🧩 Using Maven
```bash
mvn clean test
```

### 🧩 Using TestNG XML
Run the corresponding `testng.xml` file directly from your IDE (e.g., IntelliJ IDEA or Eclipse).

---

## 📊 Allure Report Generation

### Generate & Serve Report
```bash
allure serve test-outputs/allure-results
```

### Or Generate Static Report
```bash
allure generate test-outputs/allure-results --clean -o allure-report
```

Then open the report manually:
```bash
allure open allure-report
```

---


## 🧠 Lessons Learned

I've included a separate file named `Learned_Lessons.md` where I documented concepts strengthened during this project.  

This project strengthened my understanding of **BDD frameworks**, **test design architecture**, and **reporting integration**.  
Some key takeaways include:

- How to integrate **Cucumber (BDD)** with **TestNG** and **Allure** in a single hybrid framework.  
- Implementing **hooks and reusable utilities** to simplify setup, teardown, and common actions.
- Using **PicoContainer** to Creates objects, Injects dependencies into constructors, Shares objects between different step definition classes in the same scenario, and Manages object lifecycle (creates new instances per scenario).  
- Managing test data dynamically using JSON and utility classes.  
- Capturing **screenshots for failed test steps** and attaching them to **Allure reports** for better traceability.  
- Handling **Scenario Context** to share runtime data between steps in multi-step workflows.  
- Building a **clean reporting pipeline** with Allure for visually rich and interactive test results.  

This project significantly improved my ability to design **enterprise-level automation frameworks** and apply **best testing practices** for real-world web applications.

---

## 👨‍💻 Author

**Mohamed Samir**  
Jr. Software QC Engineer | ISTQB-CTFL Certified  
📧 [mohamed.samir22422@gmail.com](mailto:mohamed.samir22422@gmail.com)  
🔗 [LinkedIn Profile](https://www.linkedin.com/in/mohammed-samir-2a6544243/)  
💻 [GitHub Repository](https://github.com/Eng-Mohammed-Samir/Swag-Labs-BDD-UI-Automation-Testing.git)
