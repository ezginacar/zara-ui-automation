# 🛍️ Zara UI Test Automation Framework

Modern, robust ve scalable test automation framework for Zara e-commerce website testing.

## 📋 İçindekiler
- [Özellikler](#-özellikler)
- [Teknolojiler](#️-teknolojiler)
- [Proje Yapısı](#-proje-yapısı)
- [Kurulum](#-kurulum)
- [Kullanım](#-kullanım)
- [Test Çalıştırma](#-test-çalıştırma)
- [Screenshot Özelliği](#-screenshot-özelliği)
- [Konfigürasyon](#-konfigürasyon)
- [Best Practices](#-best-practices)
- [Katkıda Bulunma](#-katkıda-bulunma)

## ✨ Özellikler

- **Page Object Model (POM)** design pattern
- **Locators ayrı yönetimi** - maintainability için
- **ThreadLocal WebDriver** - parallel execution ready
- **Otomatik screenshot** - test failure durumlarında
- **Excel integration** - test data management
- **Robust wait strategies** - flaky test prevention
- **Comprehensive logging** - Log4j2 ile
- **Configuration management** - properties file ile
- **Cross-browser support ready** - WebDriverManager ile
- **JUnit 5** - modern test framework
- **Maven** - dependency management

## 🛠️ Teknolojiler

| Teknoloji | Versiyon | Amaç |
|-----------|----------|------|
| **Java** | 23 | Programming Language |
| **Selenium** | 4.20.0 | Web automation |
| **JUnit 5** | 5.10.0 | Test framework |
| **WebDriverManager** | 6.1.0 | Driver management |
| **Apache POI** | 5.2.3 | Excel operations |
| **Log4j2** | 2.20.0 | Logging |
| **Maven** | 3.x | Build management |

## 📁 Proje Yapısı

```
Zara-UI-Test/
├── src/
│   ├── main/java/
│   │   ├── pageobjects/          # Locator definitions
│   │   │   ├── BasketPageLocators.java
│   │   │   ├── HomePageLocators.java
│   │   │   └── ...
│   │   ├── pages/                # Page Object classes
│   │   │   ├── BasketPage.java
│   │   │   ├── HomePage.java
│   │   │   └── ...
│   │   └── utils/                # Utility classes
│   │       ├── DriverManager.java
│   │       ├── ScreenshotUtils.java
│   │       ├── CommonHelper.java
│   │       └── ...
│   └── test/
│       ├── java/tests/           # Test classes
│       │   ├── BaseTest.java
│       │   └── BasketTest.java
│       └── resources/            # Test resources
│           ├── config.properties
│           ├── log4j2.xml
│           └── ZaraUITestDatas.xlsx
├── target/screenshots/           # Auto-generated screenshots
├── pom.xml
└── README.md
```

## 🚀 Kurulum

### Gereksinimler
- **Java 23** or higher
- **Maven 3.6+**
- **Chrome browser** (default)

### Kurulum Adımları

1. **Repository'yi clone edin:**
   ```bash
   git clone https://github.com/ezginacar/zara-ui-automation.git
   cd Zara-UI-Test
   ```

2. **Dependencies'leri yükleyin:**
   ```bash
   mvn clean install
   ```

3. **Konfigürasyonu ayarlayın:**
   ```bash
   # src/test/resources/config.properties dosyasını düzenleyin
   cp src/test/resources/config.properties.example src/test/resources/config.properties
   ```

4. **Test data'sını hazırlayın:**
   - `ZaraUITestDatas.xlsx` dosyasını kontrol edin
   - Gerekirse test data'larını güncelleyin

## ⚡ Quick Start

```bash
# 1. Clone ve setup
git clone <repository-url>
cd Zara-UI-Test
mvn clean install

# 2. Config setup
cp src/test/resources/config.properties.example src/test/resources/config.properties
# Edit config.properties with your credentials

# 3. Run tests
mvn test

# 4. Check screenshots (if any tests failed)
ls target/screenshots/
```

## 💻 Kullanım

### Basic Test Yazma

```java
@DisplayName("My Custom Test")
public class MyTest extends BaseTest {
    private HomePage homePage;
    
    @BeforeAll
    public void setUpClass() throws IOException {
        homePage = new HomePage(driver);
    }
    
    @Test
    @Tag("smoke")
    public void testHomePage() {
        // Test steps
        homePage.clickLoginLinkText();
        
        // Manual screenshot
        homePage.takeScreenshot("homepage_loaded");
        
        // Assertions
        Assertions.assertTrue(condition, "Error message");
    }
}
```

### Page Object Kullanımı

```java
public class MyPage extends MyPageLocators {
    private CommonHelper helper;
    
    public MyPage(WebDriver driver) {
        this.helper = new CommonHelper(driver);
    }
    
    public MyPage clickElement() {
        helper.click(myElementLocator);
        return this;
    }
    
    public String getElementText() {
        return helper.getText(myElementLocator);
    }
}
```

## 🏃‍♂️ Test Çalıştırma

### Tüm Testleri Çalıştırma
```bash
mvn test
```

### Smoke Testleri Çalıştırma
```bash
mvn test -Dgroups="smoke"
```

### Belirli Test Class'ı Çalıştırma
```bash
mvn test -Dtest=BasketTest
```

### Parallel Execution (Gelecekte)
```bash
mvn test -Dparallel=true
```

## 📸 Screenshot Özelliği

### Otomatik Screenshot
- **Test fail olduğunda** otomatik screenshot alınır
- **Test abort olduğunda** otomatik screenshot alınır
- Screenshots: `target/screenshots/` klasöründe saklanır

### Manuel Screenshot
```java
// Test sırasında manuel screenshot
commonPage.takeScreenshot("step_description");

// Hata durumunda screenshot al ve test'i fail et
commonPage.takeScreenshotAndFail("Element not found", "error_state");
```

### Screenshot File Naming
- **Failed tests:** `FAILED_testName_2024-01-15_14-30-25.png`
- **Manual screenshots:** `manual_stepName_2024-01-15_14-30-25.png`
- **Aborted tests:** `ABORTED_testName_2024-01-15_14-30-25.png`

### Screenshot Cleanup
- Otomatik olarak 7 günden eski screenshot'lar silinir
- Manuel cleanup: `ScreenshotUtils.cleanOldScreenshots(days)`

## ⚙️ Konfigürasyon

### config.properties
```properties
browser=Chrome
baseUrl=https://www.zara.com/tr/
userEmail=your@email.com
userPassword=yourpassword
```

### Log Configuration (log4j2.xml)
```xml
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

### Test Data (Excel)
- File: `src/test/resources/ZaraUITestDatas.xlsx`
- Sheet: `SearchboxTest`
- Columns: Test data kategorilerine göre organize edilmiş

## 💡 Best Practices

### Test Yazma
```java
// ✅ İyi
@Test
@DisplayName("User can add product to basket successfully")
public void testAddProductToBasket() {
    // Given
    homePage.navigateToCategory("MEN");
    
    // When
    productPage.addFirstProductToBasket();
    
    // Then
    basketPage.validateProductAdded();
}

// ❌ Kötü
@Test
public void test1() {
    // unclear test purpose
}
```

### Screenshot Kullanımı
```java
// ✅ Meaningful names
commonPage.takeScreenshot("after_successful_login");
commonPage.takeScreenshot("product_added_to_cart");

// ❌ Generic names
commonPage.takeScreenshot("test1");
```

### Wait Strategies
```java
// ✅ Explicit waits
waitHelper.waitForElementClickable(locator);

// ❌ Thread.sleep
Thread.sleep(5000);
```

### Locator Management
```java
// ✅ Separate locator classes
public class HomePageLocators {
    protected By loginButton = By.xpath("//button[@data-qa='login']");
}

// ❌ Locators in page classes
By loginButton = By.xpath("//button[@data-qa='login']"); // in page class
```

## 🔧 Advanced Features

### Cross-Browser Support
```java
// Future implementation
@ParameterizedTest
@ValueSource(strings = {"chrome", "firefox", "edge"})
void testOnMultipleBrowsers(String browser) {
    // Test implementation
}
```

### Environment Configuration
```java
// Different environments
String env = System.getProperty("test.env", "staging");
String baseUrl = ConfigUtil.get("baseUrl." + env);
```

### Custom Exceptions
```java
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message, String screenshotPath) {
        super(message + " (Screenshot: " + screenshotPath + ")");
    }
}
```

## 🤝 Katkıda Bulunma

### Development Workflow
1. Feature branch oluşturun: `git checkout -b feature/new-feature`
2. Değişikliklerinizi commit edin: `git commit -m 'Add new feature'`
3. Branch'i push edin: `git push origin feature/new-feature`
4. Pull Request oluşturun

### Code Standards
- **Java 23** features kullanın
- **Page Object Model** pattern'ını takip edin
- **Meaningful naming** conventions kullanın
- **JavaDoc** comments ekleyin
- **Unit tests** yazın (utility classes için)

### Testing Guidelines
- Her test için `@DisplayName` kullanın
- `@Tag` ile test kategorilerini belirtin
- Screenshot'ları strategic points'lerde alın
- Assertions'da meaningful messages kullanın

## 📈 CI/CD Integration

### GitHub Actions Example
```yaml
name: Test Automation

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 23
      uses: actions/setup-java@v2
      with:
        java-version: '23'
    - name: Run tests
      run: mvn test
    - name: Upload screenshots
      uses: actions/upload-artifact@v2
      if: failure()
      with:
        name: screenshots
        path: target/screenshots/
```

## 🆘 Troubleshooting

### Common Issues

**Driver Issues:**
```bash
# WebDriverManager otomatik handle eder, manual driver gerekmez
```

**Screenshot Issues:**
```bash
# Screenshot directory permission
chmod 755 target/screenshots/
```

**Excel File Issues:**
```bash
# Excel file path
src/test/resources/ZaraUITestDatas.xlsx
```

## 📞 İletişim

- **Project Owner:** [Your Name]
- **Email:** [your.email@domain.com]
- **Issues:** GitHub Issues kullanın

---

## 📄 Lisans

Bu proje [MIT License](LICENSE) altında lisanslanmıştır.

---

**Happy Testing!** 🚀✨ 