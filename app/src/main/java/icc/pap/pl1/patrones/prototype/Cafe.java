package icc.pap.pl1.patrones.prototype;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.TipoProducto;

public class Cafe extends ProductoBase {

    public Cafe(String id) {
        super(id, TipoProducto.CAFE, "Cafe", new BigDecimal("2.20"));
    }

    @Override
    public ProductoBase copiar(String nuevoId) {
        return new Cafe(nuevoId);
    }
}
