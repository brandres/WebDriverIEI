package Logica;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MediaMarkt extends Tienda {
    @Override
    public ArrayList<FilaResultado> getCafetera(String url, ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        driver.get(url);
        if (options.size() != 0) {
            for (String o : options) {
                tryClick(By.className("categoryFilterViewMore"));
                res.addAll(getBusquedaConFiltros(driver.findElement(By.id("brandsFilterElements")), o));
                driver.get(url);
                driver.navigate().refresh();
            }
        } else {
            res.addAll(getListaArticulos());
        }
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getBusquedaConFiltros(WebElement e, String options) {
        if (options.equalsIgnoreCase("Delonghi")) {
            options = "De Longhi";
        }
        try {
            tryClick(e,By.partialLinkText(options));
        } catch (NoSuchElementException err) {
            return new ArrayList<FilaResultado>();
        }
        driver.navigate().refresh();
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>(getListaArticulos());
        tryClick(By.className("categoryFilterViewMore"));
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasMonodosis(ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        if (options.size() != 0) {
            return getCafetera("https://tiendas.mediamarkt.es/cafeteras-monodosis?view=1&perPage=64", options);
        } else {
            res.addAll(getCafetera("https://tiendas.mediamarkt.es/cafeteras-monodosis?view=1&perPage=64", options));
            res.addAll(getCafetera("https://tiendas.mediamarkt.es/cafeteras-monodosis/pagina2?view=1&perPage=64", options));
            return res;
        }

    }

    @Override
    public ArrayList<FilaResultado> getCafeterasAutomaticas(ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        res.addAll(getCafetera("https://tiendas.mediamarkt.es/cafeteras-express?view=1&perPage=64", options));
        res.addAll(getCafetera("https://tiendas.mediamarkt.es/cafeteras-super-automaticas?view=1&perPage=64", options));
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasGoteo(ArrayList<String> options) {
        return getCafetera("https://tiendas.mediamarkt.es/cafeteras-de-goteo?view=1&perPage=64", options);
    }

    @Override
    public ArrayList<FilaResultado> getListaArticulos() {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        boolean estaListo = false;
        List<WebElement> elementList = null;
        while (!estaListo) {
            try {
                elementList = driver.findElement(By.id("categoryContainerProducts")).findElements(By.id("categoryProductContainer"));
                estaListo = true;
            } catch (StaleElementReferenceException err) {
                System.out.println(err);
            }
        }
        for (WebElement e : elementList) {
            estaListo= false;
            while (!estaListo) {
                try {
                    System.out.println(e.getText());
                    String nombre = e.findElement(By.className("product1Description")).getText();
                    String precio = e.findElement(By.className("mm-price")).findElements(By.tagName("div")).get(1).getText();
                    System.out.println(nombre);
                    if(!precio.contains("€")){
                        precio = precio + "€";
                    }
                    res.add(new FilaResultado(nombre, precio, "Media Markt"));
                    estaListo = true;
                } catch (StaleElementReferenceException err) {
                    System.out.println(err);
                }
            }
        }
        return res;
    }
}
