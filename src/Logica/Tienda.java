package Logica;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public abstract class Tienda {
    public static final String EXE_PATH = "lib\\chromedriver_win32\\chromedriver.exe";
    public WebDriverWait wait;
    public WebDriver driver;
    public Tienda(){
        System.setProperty("webdriver.chrome.driver", EXE_PATH);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }
    public abstract ArrayList<FilaResultado> getCafetera(String url, ArrayList<String> options);
    public abstract ArrayList<FilaResultado> getBusquedaConFiltros(WebElement e, String options);
    public abstract ArrayList<FilaResultado> getCafeterasMonodosis(ArrayList<String> options);
    public abstract ArrayList<FilaResultado> getCafeterasAutomaticas(ArrayList<String> options);
    public abstract ArrayList<FilaResultado> getCafeterasGoteo(ArrayList<String> options);
    public abstract ArrayList<FilaResultado> getListaArticulos();

    public void tryClick(By by){
        boolean listo = false;
        while (!listo) {
            try {
                WebElement element = driver.findElement(by);
                element.click();
                listo = true;
            } catch (NoSuchElementException err){
                listo = true;
            } catch (WebDriverException err) {
                listo = false;
            }
        }
    }
    public void tryClick(WebElement e){
        boolean listo = false;
        while (!listo) {
            try {
                e.click();
                listo = true;
            } catch (NoSuchElementException err){
                listo = true;
            } catch (WebDriverException err) {
                listo = false;
            }
        }
    }
    public void isReady(By by){
        boolean listo = false;
        while (!listo) {
            try {
                WebElement element = driver.findElement(by);
                element.getText();
                System.out.println(element.getText());
                listo = true;
            } catch (NoSuchElementException err){
                System.out.println(err);
                listo = true;
            } catch (WebDriverException err) {
                listo = false;
            }
        }
    }
    public void waitForPageLoad() {
        boolean ready =false;
        while(!ready) {
            ready = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        }
    }
}
