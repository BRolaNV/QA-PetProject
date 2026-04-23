# 🧪 QA Automation Pet Project

A test automation project showcasing UI and API testing skills.  
Covers the [demoqa.com](https://demoqa.com) web application (UI + API) and the [reqres.in](https://reqres.in) REST API.

**124 tests** — 90 UI, 14 DemoQA API, 19 Reqres API, 1 Disabled.

📊 **[Allure Report (GitHub Pages)](https://brolanv.github.io/QA-PetProject/)**

---

## 📑 Table of Contents

- [Tech Stack](#-tech-stack)
- [Test Coverage](#-test-coverage)
- [Project Structure](#-project-structure)
- [Key Features](#-key-features)
- [How to Run](#-how-to-run)
- [Allure Report](#-allure-report)

---

## 🛠 Tech Stack

<p align="center">
  <a href="https://www.java.com/"><img src="https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/></a>
  <a href="https://maven.apache.org/"><img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/></a>
  <a href="https://junit.org/junit5/"><img src="https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5"/></a>
  <a href="https://selenide.org/"><img src="https://img.shields.io/badge/Selenide-00B4AB?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenide"/></a>
  <a href="https://rest-assured.io/"><img src="https://img.shields.io/badge/REST_Assured-4EAA25?style=for-the-badge&logo=java&logoColor=white" alt="REST Assured"/></a>
  <a href="https://allurereport.org/"><img src="https://img.shields.io/badge/Allure_Report-FF6600?style=for-the-badge&logo=allure&logoColor=white" alt="Allure"/></a>
  <a href="https://github.com/features/actions"><img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white" alt="GitHub Actions"/></a>
  <a href="https://www.jenkins.io/"><img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white" alt="Jenkins"/></a>
  <a href="https://www.docker.com/"><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/></a>
  <a href="https://www.selenium.dev/documentation/grid/"><img src="https://img.shields.io/badge/Selenium_Grid-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium Grid"/></a>
  <a href="https://projectlombok.org/"><img src="https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok"/></a>
  <a href="https://www.datafaker.net/"><img src="https://img.shields.io/badge/Datafaker-6C3483?style=for-the-badge&logo=java&logoColor=white" alt="Datafaker"/></a>
</p>

| Category | Technologies |
|----------|-------------|
| Language | Java 17 |
| Build | Maven |
| Test Framework | JUnit 5, JUnit Pioneer |
| UI Tests | Selenide |
| API Tests | REST Assured |
| Test Data | Datafaker, Lombok |
| Reporting | Allure Report |
| CI/CD | GitHub Actions, Jenkins |
| Infrastructure | Docker, Selenium Grid |

---

## ✅ Test Coverage

### UI Tests (demoqa.com)

| Section | Pages |
|---------|-------|
| **Elements** | TextBox, CheckBox, RadioButton, WebTables, Buttons, Links, BrokenLinks, Upload/Download, DynamicProperties |
| **Forms** | Practice Form (positive and negative scenarios) |
| **Alerts, Frame & Windows** | BrowserWindows, Alerts, Frames, NestedFrames, ModalDialogs |
| **Widgets** | Accordion, AutoComplete, DatePicker, ProgressBar, Slider, Tabs, ToolTips, Menu, SelectMenu |
| **Interactions** | Sortable, Selectable, Resizable, Droppable, Draggable |

### API Tests

| API | Coverage |
|-----|----------|
| **DemoQA BookStore** | Registration, authorization, token generation, CRUD operations for books and users |
| **Reqres.in** | CRUD operations for users, registration, login, list and resource validation |

---

## 📁 Project Structure

```
src/test/java/
├── helpers/
│   └── AllureAttachmentsExtension   # Screenshots and page source on failure
├── tests/
│   ├── demoqa/
│   │   ├── data/                    # Test data (PracticeFormData, WebTablesData)
│   │   ├── pages/                   # Page Objects
│   │   │   ├── alertsFrameWindowsPage/
│   │   │   ├── elementsPage/
│   │   │   ├── formsPage/
│   │   │   ├── interactionsPage/
│   │   │   └── widgetsPage/
│   │   └── tests/                   # UI and BookStore API tests
│   │       ├── alertsFrameWindows/
│   │       ├── bookStoreApplication/
│   │       │   ├── baseTest/        # BaseApiTest for BookStore
│   │       │   └── pojo/            # BookData, UserData, DefaultData
│   │       ├── elements/
│   │       ├── forms/
│   │       ├── interactions/
│   │       └── widgets/
│   ├── reqres/
│   │   ├── pojo/                    # UserData, ResourcesData, RootData
│   │   ├── tests/                   # Reqres API tests
│   │   ├── APIReader                # API key reader (env → file fallback)
│   │   └── BaseApiTest              # Base class with RequestSpec
│   └── specifications/
│       └── Specifications           # RequestSpec / ResponseSpec for all APIs
```

---

## 💡 Key Features

- **Page Object Pattern** — UI tests are built on page objects with Selenide, each page encapsulates locators and actions
- **Allure Integration** — `@Epic`, `@Feature`, `@Story`, `@Step`, `@Severity`, `@Description`; failure screenshots via `AllureAttachmentsExtension`; HTTP request logging via `AllureRestAssured`; Selenide steps via `AllureSelenide`
- **Parallel Execution** — JUnit 5 parallel execution (3 threads), Reqres API tests isolated with `SAME_THREAD` to prevent rate limiting
- **CI/CD** — GitHub Actions with automatic Allure report generation on GitHub Pages; Jenkins pipeline with Selenium Grid in Docker
- **Security** — API keys are never committed to the repository; stored in GitHub Secrets and Jenkins Credentials; `APIReader` implements env → file fallback
- **Retry** — flaky tests (drag-and-drop, hover, dynamic properties) are annotated with `@RetryingTest` + `@Flaky` via JUnit Pioneer
- **Test Data** — generated with Datafaker, models defined with Lombok (`@Builder`, `@Getter`, `@EqualsAndHashCode`)

---

## 🚀 How to Run

### Locally

```bash
# Clone the repository
git clone https://github.com/BRolaNV/QA-PetProject.git
cd QA-PetProject

# Create config.properties from the example
cp src/test/resources/config.properties.example src/test/resources/config.properties
# Add your reqres.in API key

# Run all tests
mvn test

# Generate Allure report locally
allure serve target/allure-results
```

### GitHub Actions (CI)

Tests run automatically on push to `main`. Allure report is published to [GitHub Pages](https://brolanv.github.io/QA-PetProject/).

### Jenkins + Selenium Grid (Docker)

```bash
# Start infrastructure
cd infrastructure
docker compose up -d

# Jenkins: http://localhost:9090
# Selenium Grid: http://localhost:4444
```

Pipeline: `Checkout` → `Build` → `Test` (API key via `withCredentials`) → `Allure Report`

---

## 📊 Allure Report

<p align="center">
  <img src="src/test/resources/allure-report.png" alt="Allure Report" width="1000"/>
</p>

---

<p align="center">
  <b><a href="https://github.com/BRolaNV">BRolaNV</a></b>
</p>
