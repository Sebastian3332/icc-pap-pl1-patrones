package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Queso extends DecoradorIngrediente {
    public Queso(Pedido pedido) {
        super(pedido, "Queso", new BigDecimal("1.00"));
    }
}
