package ua.bolshak.automatization;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static WebDriver driver;


//    @BeforeClass
//    public static void setup() {
//
//        System.setProperty("webdriver.chrome.driver", "tmp/chromedriver.exe");
//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://localhost:8081/CruiseCompany_war_exploded/");
//
//    }


    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "tmp/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8081/CruiseCompany_war_exploded/");

    }

    @Test
    public void validLoginAndPassword() {

        WebElement loginField = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[1]/td[2]/input"));
        loginField.sendKeys("admin");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[2]/td[2]/input"));
        passwordField.sendKeys("1234");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[3]/td[1]/input"));
        loginButton.click();


        WebElement exitButton = driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[2]/a"));
        exitButton.click();
        driver.navigate().refresh();



    }

    @Test
    public void notValidLoginAndValidPassword() {


        WebElement loginField = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[1]/td[2]/input"));
        loginField.sendKeys("admin777");

        WebElement passwordField = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[2]/td[2]/input"));
        passwordField.sendKeys("1234");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/form/div/table/tbody/tr[3]/td[1]/input"));
        loginButton.click();

        WebElement wrongLogin= driver.findElement(By.xpath("/html/body/p[2]"));
        String mailUser = wrongLogin.getText();
        Assert.assertEquals("Wrong login or password!", mailUser);
        driver.navigate().refresh();

    }

    @After
    public void after(){
        driver.quit();
    }
}
