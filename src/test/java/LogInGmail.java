import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;

public class LogInGmail {

    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void driverSetProperty() {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Web Drivers\\ChromeDrivers\\chromedriver 80.0.3987.106.exe");
        driver = new ChromeDriver();
     }

    @BeforeMethod
    public void initializeWebDriver() {
        driver.get("https://www.google.com/gmail/about/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in"))).click();
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId"))).sendKeys("finalseleniumproject@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierNext"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"))).sendKeys("Final7eleniumproject");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Compose')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
        driver.findElement(By.name("to")).sendKeys("kristina.markova@seavus.com");
        driver.findElement(By.name("subjectbox")).sendKeys("Final Selenium Project Ana Siljanoska");
        driver.findElement(By.cssSelector(".Am.Al.editable.LW-avf")).sendKeys("We are just starting with Selenium!");
        driver.findElement(By.xpath("//div[text()='Send']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Message sent')]")));
    }

    @AfterMethod
    public void checkIfSent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'Sent')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@email, 'kristina.markova@seavus.com')]")));
        WebElement sentMail = driver.findElement(By.xpath("//span[contains(text(), 'Ana Siljanoska')]"));
        softAssert.assertEquals(true, sentMail.isDisplayed());
        System.out.println("Mail is sent successfully");
    }

    @AfterClass
    public void quitDriver () {
        driver.quit();
    }
}
