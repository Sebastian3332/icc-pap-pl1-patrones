package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Pollo extends DecoradorIngrediente {
    public Pollo(Pedido pedido) {
        super(pedido, "Pollo", new BigDecimal("1.50"));
    }
}
