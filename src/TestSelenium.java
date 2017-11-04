import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
public class TestSelenium {
    public static void main(String[] args){
        String exePath = "C:\\Users\\Brandon\\Documents\\ideaProjects\\IEI\\lib\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver  = new ChromeDriver(options);
        driver.get("http://demo.guru99.com/");
        WebElement element=driver.findElement(By.xpath("//input[@name='emailid']"));
        element.sendKeys("revented@hotmail.com");

        WebElement button=driver.findElement(By.xpath("//input[@name='btnLogin']"));
        button.click();
    }
}
