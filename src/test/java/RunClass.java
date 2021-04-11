import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertEquals;

public class RunClass {
    private static WebDriver driver;
    private static NgWebDriver ngWebDriver;

    @BeforeClass
    public static void runOnceBeforeClass(){

        driver = SingletonDriver.getDriverInstance();
        ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
        driver.get("https://dgotlieb.github.io/Selenium/synchronization.html");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void test_1_ImplicitlyWait(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("checkbox")).isDisplayed();
        driver.findElement(By.id("btn")).click();
        driver.findElement(By.id("message")).isDisplayed();
    }


    @Test
    public void test_1_ThreadSleep() throws InterruptedException {
        driver.findElement(By.id("hidden")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("div[id='finish1']"));
    }

    @Test
    public void test_1_ExplicitWait(){
        driver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
        String str = driver.findElement(By.cssSelector("div[id='finish2']")).getText();
        String strForAssert = "This is a new element";
        assertEquals(str,strForAssert);
    }



    @Test
    public void test_2(){
        driver.navigate().to("https://dgotlieb.github.io/AngularJS/main.html");
        ngWebDriver.waitForAngularRequestsToFinish();
        WebElement firstName = driver.findElement(ByAngular.model("firstName"));
        firstName.clear();
        firstName.sendKeys("Alex");
        assertEquals(driver.findElement(By.xpath("//input")).getAttribute("value"),"Alex");
    }

    @Test
    public void test_4_Pom_Constants(){
        driver.navigate().to(Constants.URL_CALCULATE);
        System.out.println(driver.findElement(By.id(Constants.SEVEN)).getSize());
        System.out.println(driver.findElement(By.id("six")).isDisplayed());
        String number = "12";
        WebCalculatorPage.pressFive();
        WebCalculatorPage.pressAdd();
        WebCalculatorPage.pressSeven();
        WebCalculatorPage.pressEqual();
        assertEquals(number,WebCalculatorPage.getResult());
    }



    @Test
    public void test_5_TheMarket(){
        driver.navigate().to("https://www.themarker.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int count = 0;
        String in = driver.getPageSource();
        Pattern p = Pattern.compile("news");
        Matcher m = p.matcher(in);
        while (m.find()){
            count++;
        }
        System.out.println(count + "times");
    }



    @Test
    public void test_6_JSExecutor(){
        ((JavascriptExecutor)driver).executeScript("window.print();");
    }


    @Test
    public void test_7_NG(){
        driver.navigate().to("https://dgotlieb.github.io/AngularJS/main.html");
        WebCalculatorPage.setFirstName(driver);
        WebCalculatorPage.setLastName(driver);
    }
}
