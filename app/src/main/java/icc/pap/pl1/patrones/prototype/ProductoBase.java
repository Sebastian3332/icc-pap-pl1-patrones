package icc.pap.pl1.patrones.prototype;

import java.math.BigDecimal;
import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.modelo.TipoProducto;

public abstract class ProductoBase implements Pedido, Prototipo<ProductoBase> {
    protected String id;
    protected TipoProducto tipo;
    protected String descripcion;
    protected BigDecimal precio;

    public ProductoBase(String id, TipoProducto tipo, String descripcion, BigDecimal precio) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public TipoProducto getTipo() {
        return tipo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public BigDecimal getPrecio() {
        return precio;
    }

    @Override
    public abstract ProductoBase copiar(String nuevoId);
}
