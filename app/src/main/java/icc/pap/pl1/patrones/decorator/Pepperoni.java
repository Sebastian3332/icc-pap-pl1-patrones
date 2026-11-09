package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Pepperoni extends DecoradorIngrediente {
    public Pepperoni(Pedido pedido) {
        super(pedido, "Pepperoni", new BigDecimal("1.10"));
    }
}
