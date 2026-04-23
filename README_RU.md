# 🧪 QA Automation Pet Project

Проект автоматизации тестирования, демонстрирующий навыки UI и API тестирования.  
Покрывает веб-приложение [demoqa.com](https://demoqa.com) (UI + API) и REST API [reqres.in](https://reqres.in).

**124 теста** — 90 UI, 14 DemoQA API, 19 Reqres API, 1 Disabled.

📊 **[Allure Report (GitHub Pages)](https://brolanv.github.io/QA-PetProject/)**

---

## 📑 Оглавление

- [Стек технологий](#-стек-технологий)
- [Покрытие тестами](#-покрытие-тестами)
- [Архитектура проекта](#-архитектура-проекта)
- [Особенности реализации](#-особенности-реализации)
- [Запуск тестов](#-запуск-тестов)
- [Allure Report](#-allure-report)

---

## 🛠 Стек технологий

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

| Категория | Технологии |
|-----------|---------|
| Язык | Java 17 |
| Сборка | Maven |
| Тестовый фреймворк | JUnit 5 · JUnit Pioneer |
| UI тесты | Selenide |
| API тесты | REST Assured |
| Данные | Datafaker · Lombok |
| Отчётность | Allure Report |
| CI/CD | GitHub Actions · Jenkins |
| Инфраструктура  Docker · Selenium Grid |

---

## ✅ Покрытие тестами

### UI тесты (demoqa.com)

| Раздел | Страницы |
|--------|----------|
| **Elements** | TextBox, CheckBox, RadioButton, WebTables, Buttons, Links, BrokenLinks, Upload/Download, DynamicProperties |
| **Forms** | Practice Form (позитивные и негативные сценарии) |
| **Alerts, Frame & Windows** | BrowserWindows, Alerts, Frames, NestedFrames, ModalDialogs |
| **Widgets** | Accordion, AutoComplete, DatePicker, ProgressBar, Slider, Tabs, ToolTips, Menu, SelectMenu |
| **Interactions** | Sortable, Selectable, Resizable, Droppable, Draggable |

### API тесты

| API | Покрытие |
|-----|----------|
| **DemoQA BookStore** | Регистрация, авторизация, генерация токена, CRUD книг и пользователей |
| **Reqres.in** | CRUD пользователей, регистрация, логин, валидация списков и ресурсов |

---

## 📁 Архитектура проекта

```
src/test/java/
├── helpers/
│   └── AllureAttachmentsExtension   # Скриншоты и page source при падении
├── tests/
│   ├── demoqa/
│   │   ├── data/                    # Тестовые данные (PracticeFormData, WebTablesData)
│   │   ├── pages/                   # Page Objects
│   │   │   ├── alertsFrameWindowsPage/
│   │   │   ├── elementsPage/
│   │   │   ├── formsPage/
│   │   │   ├── interactionsPage/
│   │   │   └── widgetsPage/
│   │   └── tests/                   # UI и BookStore API тесты
│   │       ├── alertsFrameWindows/
│   │       ├── bookStoreApplication/
│   │       │   ├── baseTest/        # BaseApiTest для BookStore
│   │       │   └── pojo/            # BookData, UserData, DefaultData
│   │       ├── elements/
│   │       ├── forms/
│   │       ├── interactions/
│   │       └── widgets/
│   ├── reqres/
│   │   ├── pojo/                    # UserData, ResourcesData, RootData
│   │   ├── tests/                   # API тесты Reqres
│   │   ├── APIReader                # Чтение API-ключа (env → file fallback)
│   │   └── BaseApiTest              # Базовый класс с RequestSpec
│   └── specifications/
│       └── Specifications           # RequestSpec / ResponseSpec для всех API
```

---

## 💡 Особенности реализации

- **Page Object Pattern** — UI тесты построены на страничных объектах с Selenide, каждая страница инкапсулирует локаторы и действия
- **Allure интеграция** — `@Epic`, `@Feature`, `@Story`, `@Step`, `@Severity`, `@Description`; скриншоты при падении через `AllureAttachmentsExtension`; логирование HTTP-запросов через `AllureRestAssured`; Selenide-шаги через `AllureSelenide`
- **Параллельный запуск** — JUnit 5 parallel execution (3 потока), API тесты Reqres изолированы в `SAME_THREAD` для защиты от rate limiting
- **CI/CD** — GitHub Actions с автоматической генерацией Allure-отчёта на GitHub Pages; Jenkins pipeline с Selenium Grid в Docker
- **Безопасность** — API-ключи не коммитятся в репозиторий; хранятся в GitHub Secrets и Jenkins Credentials; `APIReader` реализует fallback: переменная окружения → локальный файл
- **Retry** — нестабильные тесты (drag-and-drop, hover, dynamic properties) помечены `@RetryingTest` + `@Flaky` через JUnit Pioneer
- **Тестовые данные** — генерируются через Datafaker, модели описаны с помощью Lombok (`@Builder`, `@Getter`, `@EqualsAndHashCode`)

---

## 🚀 Запуск тестов

### Локально

```bash
# Клонировать репозиторий
git clone https://github.com/BRolaNV/QA-PetProject.git
cd QA-PetProject

# Создать config.properties из примера
cp src/test/resources/config.properties.example src/test/resources/config.properties
# Вписать свой API-ключ от reqres.in

# Запустить все тесты
mvn test

# Сгенерировать Allure-отчёт локально
allure serve target/allure-results
```

### GitHub Actions (CI)

Тесты запускаются автоматически при push в `main`. Allure-отчёт публикуется на [GitHub Pages](https://brolanv.github.io/QA-PetProject/).

### Jenkins + Selenium Grid (Docker)

```bash
# Поднять инфраструктуру
cd infrastructure
docker compose up -d

# Jenkins: http://localhost:9090
# Selenium Grid: http://localhost:4444
```

Pipeline: `Checkout` → `Build` → `Test` (API-ключ через `withCredentials`) → `Allure Report`

---

## 📊 Allure Report

<p align="center">
  <img src="src/test/resources/allure-report.png" alt="Allure Report" width="1000"/>
</p>

