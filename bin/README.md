# AutomationEx — Selenium + Cucumber + TestNG

End-to-end automation suite for [automationexercise.com](https://automationexercise.com), built with Java 17, Selenium 4, Cucumber 7, TestNG, and WebDriverManager.

## Tech stack

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 17 | Language |
| Selenium | 4.20.0 | Browser automation |
| Cucumber | 7.15.0 | BDD / Gherkin |
| TestNG | 7.10.2 | Test runner & reporting |
| Selenium Manager | built-in | Automatic driver management (bundled with Selenium 4.6+) |
| DataFaker | 2.3.1 | Unique test data generation |

## Features covered

| Feature | Scenarios |
|---------|-----------|
| Registration | Happy (full form) · Bad (empty password) |
| Login | Successful · Wrong credentials |
| Shopping Cart | Add product (name + price + qty) · Remove product |

## Execution order

Tests run sequentially in this order (enforced by `testng.xml`):

1. **Registration** — creates a new account
2. **Login** — verifies login with valid and invalid credentials
3. **Cart** — adds/removes products as a logged-in user

## Project structure

```
src/
├── main/java/com/automationEx/
│   └── framework/        # BasePage · DriverFactory · ConfigReader
└── test/
    ├── java/com/automationEx/
    │   ├── hooks/        # Cucumber @Before/@After (setup, login, screenshot)
    │   ├── listeners/    # TestNG ScreenshotListener (saves PNG on failure)
    │   ├── pages/        # Page Object classes
    │   │   ├── Login/    # LoginPage
    │   │   ├── SignUp/   # SignUp · AccountInfoPage
    │   │   └── Cart/     # CartPage
    │   ├── runners/      # RunCukes_SignUp · RunCukes_Login · RunCukes_Cart
    │   └── stepdefs/     # Cucumber step definitions
    └── resources/
        ├── features/     # Gherkin feature files
        └── config/       # config.properties (baseUrl, browser, credentials)
```

## Configuration

All settings live in `src/test/resources/config/config.properties`:

```properties
baseUrl=https://automationexercise.com/
browser=chrome
headless=false
login.email=your@email.com
login.password=yourPassword
```

Credentials are never hardcoded in test code.

## Running tests

**Via Maven (uses testng.xml — recommended):**
```bash
mvn clean test
```

**Override browser or headless mode:**
```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

**Run a specific tag only:**
```bash
mvn clean test -Dcucumber.filter.tags=@cart
```

## Tags

| Tag | Scope |
|-----|-------|
| `@smoke` | All critical scenarios |
| `@signup` | Registration scenarios |
| `@login` | Login scenarios |
| `@cart` | Shopping cart scenarios |
| `@happy` | Positive flows |
| `@negative` | Negative / edge cases |
| `@requiresLogin` | Scenarios that need a logged-in session (auto-handled by hook) |

## Screenshots on failure

- **Cucumber hook** — attaches PNG inline to the HTML report (`target/report-*.html`)
- **TestNG listener** — saves PNG files to `target/screenshots/<testName>.png`
