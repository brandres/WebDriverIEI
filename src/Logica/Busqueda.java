package Logica;
import Interfaz.BusquedaProductos;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Busqueda {
    public static final String EXE_PATH = "C:\\Users\\Brandon\\Documents\\ideaProjects\\IEI\\lib\\chromedriver_win32\\chromedriver.exe";
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        BusquedaProductos app = new BusquedaProductos();
        app.run();
    }
    public static ArrayList<FilaResultado> getCafeteraFnac(String url, ArrayList<String> options){
        System.setProperty("webdriver.chrome.driver",EXE_PATH);
        WebDriver driver  = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(url);
        List<WebElement> we = driver.findElements(By.className("Filters-choice"));
        we.get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = driver.findElements(By.className("Article-item"));
        ArrayList<FilaResultado> resultados = new ArrayList<FilaResultado>();
        int i = 1;
        for(WebElement elemento : list) {
            i++;
            try{
                WebElement e =  elemento.findElement(By.className("Article-desc"));
                String nombre = e.getText();
                e = elemento.findElement(By.className("userPrice"));
                String precio = e.getText();
                resultados.add(new FilaResultado(nombre,precio,"Fnac"));
            }catch(StaleElementReferenceException err){

            }

        }
        return resultados;
    }
    public static ArrayList<FilaResultado> getCafeterasMonodosisFnac(ArrayList<String> options){
        return getCafeteraFnac("https://www.fnac.es/n97713/Desayuno-y-cafe/Cafeteras-monodosis?ItemPerPage=100",options);
    }
}
