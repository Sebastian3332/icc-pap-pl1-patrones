package icc.pap.pl1.patrones.modelo;

import java.math.BigDecimal;

public interface Pedido {
    String getId();
    TipoProducto getTipo();
    String getDescripcion();
    BigDecimal getPrecio();
}
