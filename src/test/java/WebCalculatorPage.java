import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebCalculatorPage {

    public static void pressFive(){
        SingletonDriver.getDriverInstance().findElement(By.id("five")).click();
    }

    public static void pressAdd(){
        SingletonDriver.getDriverInstance().findElement(By.id("add")).click();
    }

    public static void pressSeven(){
        SingletonDriver.getDriverInstance().findElement(By.id(Constants.SEVEN)).click();
    }
    public static void pressEqual(){
        SingletonDriver.getDriverInstance().findElement(By.id("equal")).click();
    }
    public static String  getResult(){
        return SingletonDriver.getDriverInstance().findElement(By.id("screen")).getText();
    }


    public static void setFirstName(WebDriver driver){
        WebElement firstName = driver.findElement(By.cssSelector("input[ng-model='firstName']"));
        firstName.clear();
        firstName.sendKeys("Alex");

    }
    public static void setLastName(WebDriver driver){
        WebElement lastName = driver.findElement(By.cssSelector("input[ng-model='lastName']"));
        lastName.clear();
        lastName.sendKeys("Kuzmin");
    }
}
