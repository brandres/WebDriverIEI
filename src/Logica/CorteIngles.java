package Logica;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CorteIngles extends Tienda {

    @Override
    public ArrayList<FilaResultado> getCafetera(String url, ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        driver.get(url);
        if (options.size() != 0) {
            for (String o : options) {
                res.addAll(getBusquedaConFiltros(driver.findElement(By.className("dimensions")), o));
                driver.findElement(By.partialLinkText("Desmarcar todos")).click();
            }
        } else {
            res.addAll(getTotalArticulos());
        }
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getBusquedaConFiltros(WebElement e, String options) {
        if (options.equalsIgnoreCase("Delonghi")) {
            options = "De'Longhi";
        }
        try {
            tryClick(e,By.partialLinkText(options));
        } catch (NoSuchElementException err) {
            return new ArrayList<FilaResultado>();
        }
        return getTotalArticulos();
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasMonodosis(ArrayList<String> options) {
        return getCafetera("https://www.elcorteingles.es/electrodomesticos/cafeteras/cafeteras-de-capsulas/?level=6", options);
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasAutomaticas(ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        res.addAll(getCafetera("https://www.elcorteingles.es/electrodomesticos/cafeteras/superautomaticas/?level=6", options));
        res.addAll(getCafetera("https://www.elcorteingles.es/electrodomesticos/cafeteras/cafetera-espresso-manual/?level=6", options));
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasGoteo(ArrayList<String> options) {
        return getCafetera("https://www.elcorteingles.es/electrodomesticos/cafeteras/cafeteras-de-goteo/?level=6", options);
    }

    @Override
    public ArrayList<FilaResultado> getListaArticulos() {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        List<WebElement> elementList = driver.findElement(By.className("product-list")).findElements(By.className("product"));
        for (WebElement e : elementList) {
            String nombre = e.findElement(By.className("product-name")).getText();
            String precio = e.findElement(By.className("current")).getText();
            res.add(new FilaResultado(nombre, precio, "El Corte Inglés"));
        }
        return res;
    }

    public ArrayList<FilaResultado> getTotalArticulos() {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        boolean haySiguiente = true;
        while (haySiguiente) {
            try {
                res.addAll(getListaArticulos());
                driver.findElement(By.partialLinkText("Siguiente")).click();
            } catch (NoSuchElementException e) {
                System.out.println(e);
                haySiguiente = false;
            }
        }
        return res;
    }
}
