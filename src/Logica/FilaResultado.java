package Logica;

public class FilaResultado {
    public String Nombre;
    public String Precio;
    public String Tienda;

    public FilaResultado(String nombre, String precio, String tienda) {
        Nombre = nombre;
        Precio = precio;
        Tienda = tienda;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public String getTienda() {
        return Tienda;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public void setTienda(String tienda) {
        Tienda = tienda;
    }
}
