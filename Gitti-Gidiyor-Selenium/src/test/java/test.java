import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class test {
    public static WebDriver webDriver(){
        System.setProperty("webdriver.chrome.driver" ,"C:/Selenium/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.gittigidiyor.com/");
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriver driver = webDriver();
    WebDriverWait wait = new WebDriverWait(driver,10,1000);

   // private WebDriver driver;
JavascriptExecutor js;
@Before
public void setUp(){
    System.setProperty("webdriver.chrome.driver" ,"C:/Selenium/chromedriver/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    js = (JavascriptExecutor) driver;

}

@Test
public void Test() throws InterruptedException {

    String baseURL = "https://www.gittigidiyor.com/";

    //gittigidiyor açılır, anasayfa açıldı mı kontrol edilir
    driver.get(baseURL);
    Thread.sleep(2000);
    Assert.assertEquals( "https://www.gittigidiyor.com/", driver.getCurrentUrl());


    //siteye login olunur
   driver.get("https://www.gittigidiyor.com/uye-girisi");
    driver.findElement(By.id("L-UserNameField")).sendKeys("mlkzrnc@gmail.com");
    driver.findElement(By.id("L-PasswordField")).sendKeys("password1");
    driver.findElement(By.id("gg-login-enter")).click();


    //arama çubuğuna "bilgisayar" yazılır ve arama yapılır.
    driver.findElement(By.name("k")).sendKeys("Bilgisayar");
    Thread.sleep(1000);
    driver.findElement(By.cssSelector(".qjixn8-0.sc-1bydi5r-0.hKfdXF")).click();
    Thread.sleep(1000);
   js.executeScript("window.scrollBy(0,1200)");
    //arama sonuçları sayfasından 2.sayfa açılır, 2. sayfa olup olmadığı kontrol edilir
    driver.get(baseURL +"arama/?k=bilgisayar&sf=2");
    Thread.sleep(1000);
    Assert.assertEquals("Arama sonuçlarından 2. sayfa açılamadı!" ,"2", driver.findElement(By.className("current")).getText());



    //rastgele ürün seçilir, seçilen ürünün sayfası açılır
    Random random = new Random();
    int rand = random.nextInt(49) + 1;

    driver.findElement(By.xpath("//div[@class='clearfix']/ul[@class='catalog-view clearfix products-container']/li["+rand+"]/a[@class='product-link']")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//*[@id='add-to-basket']")).click();
    Thread.sleep(2000);


    // ürünün fiyatını karşılaştırma için fiyat1 e atıyoruz.
    String firstPrice= driver.findElement(By.id("sp-price-lowPrice")).getText();
    System.out.println("fiyat 1 : "+ firstPrice);
    Thread.sleep(4000);
    js.executeScript("window.scrollBy(0,3200)");
    driver.findElement(By.xpath("//*[@id='header_wrapper']/div[4]/div[3]")).click();



    String price = driver.findElement(By.cssSelector("p[class='new-price']")).getText();
    System.out.println("fiyat 2 : " + price);
    Thread.sleep(2000);
    Assert.assertEquals("Sepetteki fiyat ürün sayfasındaki fiyata eşit değildir.", firstPrice,price);
    Thread.sleep(2000);

    //ürün adedi arttırılarak 2 yapılır, ürün adedinin 2 olup olmadığı kontrol edilir


//   Select productAmount = new Select(driver.findElement(By.xpath("//*[@id='cart-item-464525581']/div[2]/div[4]/div/div[2]")));
//   productAmount.selectByVisibleText("2");
//
//    driver.findElement(By.cssSelector("option[value='2']"));
//    Assert.assertEquals("Ürün adedi 2 değildir.", "2",driver.findElement(By.className("amount")).getText());
//    Thread.sleep(2000);


    //ürün sepetten silinir ve silinip silinmediği kontrol edilir
    driver.findElement(By.xpath("//*[@class='btn-delete btn-update-item hidden-m']")).click();
    System.out.println("silindi");
    String bildirim =driver.findElement(By.xpath("//*[@class='gg-w-22 gg-d-22 gg-t-21 gg-m-18']/h2")).getText();
    Assert.assertEquals("Sepet boş değil!", "Sepetinizde ürün bulunmamaktadır.", bildirim);
}



    @After
public void tearDown(){
    driver.quit();
}
}
