package Logica;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Fnac extends Tienda {
    public Fnac(){
        super();
    }
    public ArrayList<FilaResultado> getCafetera(String url, ArrayList<String> options) {
        ArrayList<FilaResultado> resultados = new ArrayList<FilaResultado>();
        driver.get(url);
        if (options.size() != 0) {
            for (String o : options) {
                WebElement we = driver.findElements(By.className("Filters-content")).get(1);
                resultados.addAll(getBusquedaConFiltros(we, o));
                driver.navigate().to(url);
            }
        } else {
            resultados = getListaArticulos();
        }
        return resultados;
    }

    public ArrayList<FilaResultado> getBusquedaConFiltros(WebElement e, String options) {
        try {
            WebElement aux = e.findElement(By.partialLinkText(options));
            aux.click();
        } catch (Exception err) {
            return new ArrayList<FilaResultado>();
        }
        tryClick(driver.findElement(By.className("Item-onPage")));
        tryClick(driver.findElement(By.className("hovered")));
        driver.navigate().refresh();
        return getListaArticulos();
    }

    public ArrayList<FilaResultado> getCafeterasMonodosis(ArrayList<String> options) {
        return getCafetera("https://www.fnac.es/n97713/Desayuno-y-cafe/Cafeteras-monodosis?ItemPerPage=100", options);
    }
    public ArrayList<FilaResultado> getCafeterasAutomaticas(ArrayList<String> options) {
        return getCafetera("https://www.fnac.es/n97714/Desayuno-y-cafe/Cafeteras-expreso-y-automaticas?ItemPerPage=100", options);
    }
    public ArrayList<FilaResultado> getCafeterasGoteo(ArrayList<String> options) {
        return getCafetera("https://www.fnac.es/n97715/Desayuno-y-cafe/Cafeteras-de-goteo?ItemPerPage=100", options);
    }

    public ArrayList<FilaResultado> getListaArticulos() {
        ArrayList<FilaResultado> resultados = new ArrayList<FilaResultado>();
        List<WebElement> list = driver.findElements(By.className("Article-item"));
        int i = 1;
        for (WebElement elemento : list) {
            i++;
            try {
                WebElement article = elemento.findElement(By.className("Article-desc"));
                String nombre = article.getText();
                article = elemento.findElement(By.className("userPrice"));
                String precio = article.getText();
                resultados.add(new FilaResultado(nombre, precio, "Fnac"));
            } catch (Exception err) {

            }
        }
        return resultados;
    }
}
