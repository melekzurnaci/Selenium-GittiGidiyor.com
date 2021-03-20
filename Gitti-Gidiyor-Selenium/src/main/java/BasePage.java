import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public  BasePage(WebDriver webDriver){
        this.driver = webDriver;
        this.wait = new WebDriverWait(webDriver,2000);
    }

    public WebElement findElement(By by){

      return  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
    }

}
