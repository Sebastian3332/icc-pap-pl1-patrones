package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;

public class Tomate extends DecoradorIngrediente {
    public Tomate(Pedido pedido) {
        super(pedido, "Tomate", new BigDecimal("1.10"));
    }
}