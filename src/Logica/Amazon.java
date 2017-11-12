package Logica;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

import static Logica.Busqueda.waitBusqueda;

public class Amazon extends Tienda {
    public Amazon() {
        super();
    }

    @Override
    public ArrayList<FilaResultado> getCafetera(String url, ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        driver.get(url);
        System.out.println(url);
        System.out.println(options.size());
        if (options.size() != 0) {
            for (String o : options) {
                driver.navigate().refresh();
                res.addAll(getBusquedaConFiltros(null, o));
                tryClick(By.className("s-ref-overlap-up"));
            }
        } else {
            res.addAll(getArticulosDePaginas(5));
        }
        driver.navigate().refresh();
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getBusquedaConFiltros(WebElement e, String options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        tryClick(By.partialLinkText("Ver más"));
        if (options.equalsIgnoreCase("Delonghi")) {
            options = "DeLonghi";
        }
        tryClick(By.partialLinkText(options));
        return getArticulosDePaginas(5);
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasMonodosis(ArrayList<String> options) {
        return getCafetera("https://www.amazon.es/b/ref=sr_aj?node=3595682031&bbn=13713554031&ajr=0", options);

    }

    @Override
    public ArrayList<FilaResultado> getCafeterasAutomaticas(ArrayList<String> options) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        res.addAll(getCafetera("https://www.amazon.es/b/ref=sr_aj?node=2165187031&bbn=13713554031&ajr=0", options));
        res.addAll(getCafetera("https://www.amazon.es/b/ref=sr_aj?node=2165182031&bbn=13713554031&ajr=0", options));
        return res;
    }

    @Override
    public ArrayList<FilaResultado> getCafeterasGoteo(ArrayList<String> options) {
        return getCafetera("https://www.amazon.es/b/ref=sr_aj?node=2165180031&bbn=13713554031&ajr=0", options);
    }

    @Override
    public ArrayList<FilaResultado> getListaArticulos() {
        ArrayList<FilaResultado> resultados = new ArrayList<FilaResultado>();
        waitBusqueda(1000);
        List<WebElement> list = driver.findElements(By.className("s-result-item"));
        for (WebElement elemento : list) {
            boolean listo = false;
            while (!listo) {
                try {
                    String nombre = elemento.findElement(By.className("a-size-base")).getText();
                    System.out.println(nombre);
                    String precio = elemento.findElement(By.className("a-price-whole")).getText() +
                            "," + elemento.findElement(By.className("a-price-fraction")).getText() + " €";
                    resultados.add(new FilaResultado(nombre, precio, "Amazon"));
                    listo = true;
                } catch (NoSuchElementException err) {
                    try {
                        String nombre = elemento.findElement(By.className("a-size-base")).getText();
                        String precio = elemento.findElement(By.partialLinkText("EUR")).findElement(By.className("a-size-base")).getText();
                        resultados.add(new FilaResultado(nombre, precio, "Amazon"));
                        listo = true;
                    } catch (NoSuchElementException error) {
                        listo = true;
                    } catch (WebDriverException error) {
                        listo = false;
                    }
                } catch (WebDriverException err) {
                    listo = false;
                }
            }
        }
        return resultados;
    }

    //Paginas no deberia ser mayor que 5 pòrque si no amazon bloquea el acceso
    public ArrayList<FilaResultado> getArticulosDePaginas(int paginas) {
        ArrayList<FilaResultado> res = new ArrayList<FilaResultado>();
        for (int i = 1; i < paginas; i++) {
            res.addAll(getListaArticulos());
            tryClick(By.id("pagnNextString"));
            driver.navigate().refresh();
            try {
                driver.findElement(By.id("pagnNextLink")).getText();
            } catch (NoSuchElementException err) {
                i = 6;
            }
        }
        return res;
    }
}
