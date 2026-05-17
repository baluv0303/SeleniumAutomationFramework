# Selenium Automation Framework

A comprehensive test automation framework built with Selenium WebDriver, TestNG, and ExtentReports for end-to-end testing of web applications using the Page Object Model (POM) design pattern.

## 📋 Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Test Suites](#test-suites)
- [Key Features](#key-features)
- [Test Reports](#test-reports)
- [Architecture](#architecture)
- [Troubleshooting](#troubleshooting)

## Overview

This framework provides a robust, scalable, and maintainable solution for automated testing with the following capabilities:

✅ **Page Object Model (POM)** - Clean separation of test logic and page interactions  
✅ **Data-Driven Testing** - Externalized test data in JSON format  
✅ **TestNG Framework** - Powerful test execution with groups and priorities  
✅ **ExtentReports** - Beautiful HTML test reports with screenshots  
✅ **Automatic Retry** - Retry mechanism for flaky tests  
✅ **WebDriverManager** - Automatic WebDriver management  
✅ **Maven Profiles** - Multiple test execution strategies  
✅ **TestNG Listeners** - Comprehensive test event logging  

## Project Structure

```
SeleniumAutomationFramework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/                      # Page Object Classes
│   │   │   │   ├── BasePage.java           # Base class with common methods
│   │   │   │   ├── LandingPage.java        # Login page interactions
│   │   │   │   ├── ProductCatalogue.java   # Product browsing
│   │   │   │   ├── CartPage.java           # Shopping cart operations
│   │   │   │   ├── CheckOutPage.java       # Checkout process
│   │   │   │   ├── ThankYouOrderPage.java  # Order confirmation
│   │   │   │   └── OrderHistoryPage.java   # Order history
│   │   │   └── utils/
│   │   │       ├── ExtentReportsNG.java    # Report generation setup
│   │   │       └── GlobalData.properties   # Global configuration
│   │   └── data/
│   │       └── PurchaseOrder.json          # Test data
│   │
│   └── test/
│       └── java/
│           ├── BaseTest/
│           │   └── BaseTest.java           # Base test setup & teardown
│           ├── utils/
│           │   ├── Listeners.java          # TestNG listener for reporting
│           │   └── RetryTests.java         # Retry analyzer
│           ├── PlaceOrderTest.java         # Purchase workflow tests
│           ├── LoginInWithInvalidCredentialsTest.java  # Error validation tests
│           └── StandAloneTest.java         # Standalone execution
│
├── testSuites/
│   ├── testng.xml                          # Main test suite (all tests)
│   ├── purchase.xml                        # Purchase workflow tests
│   └── errorHandling.xml                   # Error handling tests
│
├── reports/
│   ├── index.html                          # ExtentReports dashboard
│   └── *.png                               # Failure screenshots
│
├── target/
│   ├── classes/                            # Compiled classes
│   ├── test-classes/                       # Compiled test classes
│   └── surefire-reports/                   # Maven Surefire reports
│
├── pom.xml                                 # Maven configuration
└── README.md                               # This file
```

## Prerequisites

- **Java Development Kit (JDK)**: 17 or higher
- **Maven**: 3.6 or higher
- **Browser**: Chrome (recommended) or Firefox
- **Git**: For version control

## Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd SeleniumAutomationFramework
```

### 2. Install Dependencies

```bash
mvn clean install
```

This will download all required dependencies including Selenium, TestNG, and ExtentReports.

### 3. Configure Global Settings

Edit `src/main/java/utils/GlobalData.properties`:

```properties
browser=chrome
```

Options: `chrome` or `firefox`

### 4. Configure Test Data

Edit `src/main/data/PurchaseOrder.json` with your test credentials:

```json
[
  {
    "email": "test_user1@gmail.com",
    "password": "YourPassword@123",
    "productName": "ZARA COAT 3"
  },
  {
    "email": "test_user2@gmail.com",
    "password": "YourPassword@123",
    "productName": "ADIDAS ORIGINAL"
  }
]
```

## Running Tests

### Option 1: Using Maven Profiles (Recommended)

**Run All Tests:**
```bash
mvn clean test -PPurchaseOrder
```

**Run Error Validation Tests:**
```bash
mvn clean test -PErrorvalidationsTest
```

**Run Regression Tests:**
```bash
mvn clean test -PRegressionTests
```

### Option 2: Run from IDE

1. Right-click on the desired test suite XML in `testSuites/` folder
2. Select **Run 'xmlFileName.xml'**

### Option 3: Run Specific Test Class

```bash
mvn clean test -Dtest=PlaceOrderTest
```

### Option 4: Run with Maven Directly

```bash
mvn clean test -Dsuites=testSuites/testng.xml
```

## Test Suites

### 1. **testng.xml** - Main Test Suite
Executes all test scenarios including:
- Place Order workflow
- Login with invalid credentials
- Product error validations
- Order history verification

**Usage:**
```bash
mvn clean test -PPurchaseOrder
```

### 2. **purchase.xml** - Purchase Workflow
Focuses on complete purchase process:
- Login with valid credentials
- Product selection and add to cart
- Checkout and order placement
- Order confirmation verification

**Usage:**
```bash
mvn clean test -PErrorvalidationsTest
```

### 3. **errorHandling.xml** - Error Handling Tests
Tests error scenarios:
- Login with invalid credentials
- Product error validations
- Error message verification

**Usage:**
```bash
mvn clean test -PRegressionTests
```

## Test Scenarios

### 1. Place Order Test (`PlaceOrderTest.java`)

**Test Methods:**
- `placeOrder()` - Complete purchase workflow with data-driven approach
- `OrderVerificationTest()` - Verify order in order history

**Flow:**
```
Login → Browse Products → Add to Cart → Verify Cart → Checkout → Place Order → Verify Confirmation
```

### 2. Login with Invalid Credentials (`LoginInWithInvalidCredentialsTest.java`)

**Test Methods:**
- `loginWithInvalidCredentials()` - Attempt login with wrong credentials
- `productErrorValidations()` - Validate product-related error handling

**Validations:**
- Error message display
- Invalid credential handling
- Product availability checks

## Test Reports

### 1. ExtentReports (Primary Report)

Located at: `reports/index.html`

**Open the report:**
```bash
# macOS
open reports/index.html

# Linux
xdg-open reports/index.html

# Windows
start reports/index.html
```

**Report includes:**
- ✅ Test execution summary (passed, failed, skipped)
- ✅ Detailed test results with timestamps
- ✅ Screenshots for failed tests
- ✅ Error messages and stack traces
- ✅ Dashboard with execution timeline
- ✅ Test category grouping

### 2. Maven Surefire Reports

Located at: `target/surefire-reports/index.html`

Open with:
```bash
open target/surefire-reports/index.html
```

## Key Features

### 1. Page Object Model (POM)

All page interactions are abstracted into dedicated page classes:

```java
// Clean and readable test code
ProductCatalogue productCatalogue = landingPage.performLogin(email, password);
productCatalogue.addItemToCart(productName);
CartPage cartPage = productCatalogue.goToCartPage();
```

### 2. Data-Driven Testing

Test data is externalized in JSON format, allowing easy test parameterization:

```java
@DataProvider
public Object[][] getData() throws IOException {
    List<HashMap<String, String>> data = 
        getJsonToHashmap(System.getProperty("user.dir") + 
        "//src//main//data//PurchaseOrder.json");
    return new Object[][] {{data.get(0)}, {data.get(1)}};
}

@Test(dataProvider = "getData")
public void placeOrder(HashMap<String, String> input) {
    // Test uses data from JSON
}
```

### 3. Automatic Retry for Flaky Tests

Automatically retry failed tests to handle intermittent failures:

```java
@Test(retryAnalyzer = RetryTests.class)
public void loginWithInvalidCredentials() {
    // This test will retry once if it fails
}
```

Configuration in `RetryTests.java`:
```java
int maxRetry = 1;  // Number of retries
```

### 4. TestNG Listeners for Reporting

`Listeners.java` captures all test events:

```
Test Start → Create test in report
Test Pass → Log PASS status
Test Fail → Log FAIL + capture screenshot
Suite Finish → Flush report to HTML
```

### 5. WebDriver Management

Automatic WebDriver download and setup:

```java
WebDriverManager.chromedriver().setup();  // Auto-downloads compatible driver
driver = new ChromeDriver();
```

### 6. Screenshot on Failure

Automatically captures screenshots when tests fail, visible in the report.

## Architecture

### Page Object Model (POM)

```
BasePage (Abstract Base Class)
    ↓
├── LandingPage (Login page)
├── ProductCatalogue (Product listing)
├── CartPage (Shopping cart)
├── CheckOutPage (Checkout)
├── ThankYouOrderPage (Order confirmation)
└── OrderHistoryPage (Order history)
```

### Test Execution Flow

```
TestNG Framework
    ↓
BaseTest (Setup/Teardown)
    ↓
Test Classes
    ↓
Page Objects
    ↓
Selenium WebDriver
    ↓
Browser
```

### Reporting Flow

```
Test Execution
    ↓
Listeners (ITestListener)
    ↓
ExtentReports
    ↓
HTML Report + Screenshots
```

## Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Selenium | 4.25.0 | Web automation |
| TestNG | 7.9.0 | Test framework |
| ExtentReports | 5.1.2 | HTML reporting |
| WebDriverManager | 6.3.4 | Driver management |
| Jackson | 2.21.3 | JSON parsing |
| Apache HttpComponents | 5.6.1 | HTTP client |

## Best Practices

✅ **Use Page Object Model** - Keep page logic separate from test logic  
✅ **Externalize Test Data** - Use JSON/Excel for test data  
✅ **Implement Waits Properly** - Use explicit waits instead of thread.sleep()  
✅ **Capture Screenshots** - Automatically capture on failure  
✅ **Use Meaningful Assertions** - Include descriptive assertion messages  
✅ **Keep Tests Independent** - Tests should not depend on execution order  
✅ **Descriptive Test Names** - Method names should explain what's being tested  
✅ **Use Groups** - Organize tests using @Test(groups={"..."})  

## Configuration

### Browser Configuration
**File:** `src/main/java/utils/GlobalData.properties`
```properties
browser=chrome
```

### Retry Configuration
**File:** `src/test/java/utils/RetryTests.java`
```java
int maxRetry = 1;  // Change to retry multiple times
```

### Report Configuration
**File:** `src/main/java/utils/ExtentReportsNG.java`
```java
reporter.config().setReportName("Web Automation Test Results");
reporter.config().setDocumentTitle("Test results");
```

## Troubleshooting

### Issue: Tests Not Running

**Solution:**
- Ensure test class names end with `Test`
- Verify test methods have `@Test` annotation
- Check `testng.xml` has correct class names
- Verify listener is properly configured: `<listener class-name = "utils.Listeners" />`

### Issue: WebDriver Issues

**Solution:**
- Ensure browser is installed and up-to-date
- WebDriverManager auto-downloads drivers; ensure internet connectivity
- Check Chrome/Firefox version compatibility
- Clear browser cache if needed

### Issue: Reports Not Generated

**Solution:**
- Verify `reports/` directory exists (auto-created by framework)
- Check ExtentReports dependency in `pom.xml`
- Ensure `Listeners.java` is in the classpath
- Check console for ExtentReports initialization errors

### Issue: Data Provider Failures

**Solution:**
- Verify JSON file path is correct
- Validate JSON syntax (use JSON validator)
- Check data keys match test usage
- Ensure `PurchaseOrder.json` is in `src/main/data/`

### Issue: Element Not Found

**Solution:**
- Increase implicit/explicit wait timeout
- Verify element locators are correct
- Check page has fully loaded before interaction
- Use WebDriverWait with ExpectedConditions

### Issue: Parallel Test Execution Issues

**Solution:**
- Ensure tests use unique data
- Avoid shared WebDriver instances
- Use thread-safe resources
- Check `pom.xml` parallel configuration

## Contributing

1. **Create a Feature Branch**
   ```bash
   git checkout -b feature/new-test-scenario
   ```

2. **Follow POM Pattern**
   - Create new page objects in `pages/` package
   - Keep page logic separate from test logic

3. **Add Test Data**
   - Update `PurchaseOrder.json` with necessary data
   - Keep data realistic and meaningful

4. **Write Descriptive Tests**
   - Use clear method names
   - Add comments for complex logic
   - Use meaningful assertions

5. **Update Documentation**
   - Document new test scenarios
   - Update this README if adding features

6. **Commit and Push**
   ```bash
   git add .
   git commit -m "Add new test scenario: [description]"
   git push origin feature/new-test-scenario
   ```

## Useful Commands

```bash
# Clean and install
mvn clean install

# Run all tests
mvn clean test

# Run tests with specific profile
mvn clean test -PPurchaseOrder

# Skip tests during build
mvn clean install -DskipTests

# Run tests in debug mode
mvn -Dmaven.surefire.debug test

# Generate site documentation
mvn site

# Check for outdated dependencies
mvn versions:display-outdated-report
```

## Support & Contact

For issues, questions, or contributions, please contact the QA automation team.

---

## Project Info

- **Project Name:** Selenium Automation Framework
- **Java Version:** 17
- **Maven Version:** 3.6+
- **Last Updated:** May 17, 2026
- **Status:** Active Development

---

**Happy Testing! 🚀**

