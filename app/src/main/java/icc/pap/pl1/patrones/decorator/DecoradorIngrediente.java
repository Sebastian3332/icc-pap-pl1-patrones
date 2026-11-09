package icc.pap.pl1.patrones.decorator;

import java.math.BigDecimal;

import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.modelo.TipoProducto;

public abstract class DecoradorIngrediente implements Pedido {
    protected Pedido pedidoEnvuelto;
    protected String nombreIngrediente;
    protected BigDecimal costoIngrediente;

    public DecoradorIngrediente(Pedido pedidoEnvuelto, String nombreIngrediente, BigDecimal costoIngrediente) {
        this.pedidoEnvuelto = pedidoEnvuelto;
        this.nombreIngrediente = nombreIngrediente;
        this.costoIngrediente = costoIngrediente;
    }

    @Override
    public String getId() {
        return pedidoEnvuelto.getId();
    }

    @Override
    public TipoProducto getTipo() {
        return pedidoEnvuelto.getTipo();
    }

    @Override
    public String getDescripcion() {
        return pedidoEnvuelto.getDescripcion() + " + " + nombreIngrediente;
    }

    @Override
    public BigDecimal getPrecio() {
        return pedidoEnvuelto.getPrecio().add(costoIngrediente);
    }
}
