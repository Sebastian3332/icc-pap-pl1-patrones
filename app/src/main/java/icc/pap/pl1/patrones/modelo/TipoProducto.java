package icc.pap.pl1.patrones.modelo;

public enum TipoProducto {
    CAFE("Cafe"),
    ENSALADA("Ensalada sencilla"),
    PIZZA("Pizza sencilla");

    private final String nombre;

    TipoProducto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
