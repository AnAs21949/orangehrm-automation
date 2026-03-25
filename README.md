
## Table of Contents

- [Tech Stack](#-tech-stack)
- [Project Architecture](#-project-architecture)
- [Test Coverage](#-test-coverage)
- [Setup & Installation](#-setup--installation)
- [Running Tests](#-running-tests)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Test Reports](#-test-reports)
- [Key Design Decisions](#-key-design-decisions)
- [Author](#-author)

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| **Java 21+** | Programming language |
| **Selenium WebDriver 4.18** | Browser automation |
| **TestNG 7.9** | Test framework & assertions |
| **Maven** | Build & dependency management |
| **WebDriverManager** | Automatic driver management |
| **ExtentReports 5** | HTML test reporting |
| **Jenkins** | CI/CD pipeline |
| **Page Object Model** | Design pattern |

---

## Project Architecture

```
orangehrm-automation/
├── src/
│   └── test/
│       ├── java/com/orangehrm/
│       │   ├── base/
│       │   │   └── BaseTest.java            # Setup/teardown, driver init, report hooks
│       │   ├── pages/
│       │   │   ├── LoginPage.java            # Login page actions & locators
│       │   │   ├── DashboardPage.java        # Dashboard navigation & verification
│       │   │   └── EmployeePage.java         # Employee CRUD operations (PIM module)
│       │   ├── tests/
│       │   │   ├── LoginTest.java            # Authentication tests
│       │   │   ├── DashboardTest.java        # Dashboard UI & navigation tests
│       │   │   └── EmployeeTest.java         # Full CRUD lifecycle test
│       │   └── utils/
│       │       ├── ConfigReader.java         # Properties file reader
│       │       └── ExtentManager.java        # Report configuration
│       └── resources/
│           ├── config.properties             # Environment config (URL, credentials)
│           └── testng.xml                    # Test suite definition
├── Jenkinsfile                               # CI/CD pipeline definition
├── pom.xml                                   # Maven dependencies
└── README.md
```

---

## Test Coverage

### Login Module
- Valid login with admin credentials
- Dashboard URL verification post-login

### Dashboard Module
- Dashboard title verification
- User dropdown & logout flow
- PIM module navigation

### Employee Module (Full CRUD)
- **Create** — Add new employee, capture auto-generated ID
- **Read** — Search employee by ID, verify existence
- **Update** — Edit employee name, verify changes persisted
- **Delete** — Remove employee, verify record no longer exists

---

## Setup & Installation

### Prerequisites

- Java JDK 21 or higher
- Maven 3.9+
- Chrome browser installed
- Git

### Clone & Run

```bash
# Clone the repository
git clone https://github.com/AnAs21949/orangehrm-automation.git
cd orangehrm-automation

# Run all tests
mvn clean test
```

No manual driver download needed — **WebDriverManager** handles ChromeDriver automatically.

---

## Running Tests

```bash
# Run the full test suite
mvn clean test

# Run a specific test class
mvn clean test -Dtest=LoginTest

# Run with a custom TestNG suite
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

---

## CI/CD Pipeline

The project includes a `Jenkinsfile` for automated pipeline execution:

```
┌──────────────┐    ┌──────────────┐    ┌──────────────────┐
│   Checkout    │───▶│  Run Tests   │───▶│  Publish Report  │
│  (from Git)   │    │ (mvn clean   │    │ (ExtentReport    │
│               │    │    test)     │    │  archived)       │
└──────────────┘    └──────────────┘    └──────────────────┘
```

### Jenkins Setup

1. Create a new **Pipeline** job in Jenkins
2. Set **Pipeline script from SCM** → Git
3. Repository URL: `https://github.com/AnAs21949/orangehrm-automation.git`
4. Branch: `*/main`
5. Script Path: `Jenkinsfile`
6. Configure **Maven** and **JDK** in Jenkins Global Tools

---

## Test Reports

Tests generate an **ExtentReports** HTML report in the `reports/` directory with:

- Pass/Fail status per test
- Execution timestamps & duration
- Environment details (Tester, Environment)
- Timeline visualization

After running tests, open `reports/ExtentReport.html` in any browser.

---

## Key Design Decisions

**Page Object Model (POM)** — Each page of the application has its own class encapsulating locators and actions. This keeps tests readable and maintenance low — if a locator changes, you fix it in one place.

**Explicit Waits over Implicit Waits** — The framework uses `WebDriverWait` with `ExpectedConditions` instead of relying on implicit waits. This ensures stability across different environments (local machine vs CI server).

**Centralized BaseTest** — All setup (driver initialization, config loading) and teardown (browser close, report logging) are in `BaseTest.java`. Test classes inherit from it, following DRY principles.

**Config Separation** — Credentials and URLs live in `config.properties`, not hardcoded in tests. This makes environment switching effortless.

---

## Author

**Anas Abid**

- ENSA Berrechid — State Engineer
- ISTQB CTFL 4.0 Certified
- QA Automation Engineer — Java | Python | Selenium | TestNG | pytest

