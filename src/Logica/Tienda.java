package Logica;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public abstract class Tienda {
    public static final String EXE_PATH = "C:\\Users\\Brandon\\Documents\\ideaProjects\\IEI\\lib\\chromedriver_win32\\chromedriver.exe";
    public WebDriverWait wait;
    public WebDriver driver;
    Tienda(){
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

    public void tryClick(WebElement element){
        boolean listo = false;
        while (!listo) {
            try {
                element.click();
                listo = true;
            } catch (WebDriverException err) {
                listo = false;
            }
        }
    }
}
