package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Crema extends DecoradorIngrediente {
    public Crema(Pedido pedido) {
        super(pedido, "Crema", new BigDecimal("1.20"));
    }
}