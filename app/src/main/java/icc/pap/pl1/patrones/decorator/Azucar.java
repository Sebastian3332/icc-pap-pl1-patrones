package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Azucar extends DecoradorIngrediente {
    public Azucar(Pedido pedido) {
        super(pedido, "Azucar", new BigDecimal("0.50"));
    }
}
