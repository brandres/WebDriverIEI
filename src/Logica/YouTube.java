package Logica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YouTube {
    public static final String EXE_PATH = "lib/chromedriver";
    public WebDriverWait wait;
    public WebDriver driver;
    public YouTube(){
            System.setProperty("webdriver.chrome.driver", EXE_PATH);
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
    }

    public void abrirYoutube(String url){
        driver.get(url);
    }
    public void abirListaReproduccion(String url){
        waitYoutube(70000);
        driver.get(url);
    }
    public void eliminarDuplicados(){
        abrirYoutube("https://www.youtube.com/?gl=ES&hl=es");
        abirListaReproduccion("https://www.youtube.com/playlist?list=PLSZfdaD94am6JB_hNEX0WyzYDKbiUbFz6&disable_polymer=true");
        waitYoutube(5000);
        while (true) {
            driver.findElement(By.className("remove-duplicate-button")).click();
            waitYoutube(2000);
        }

    }
    public void waitYoutube(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
