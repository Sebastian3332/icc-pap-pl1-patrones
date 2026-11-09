package icc.pap.pl1.patrones.servicio;

import java.util.ArrayList;
import java.util.List;

import icc.pap.pl1.patrones.decorator.Azucar;
import icc.pap.pl1.patrones.decorator.Crema;
import icc.pap.pl1.patrones.decorator.Pepperoni;
import icc.pap.pl1.patrones.decorator.Pollo;
import icc.pap.pl1.patrones.decorator.Queso;
import icc.pap.pl1.patrones.decorator.Tomate;
import icc.pap.pl1.patrones.modelo.Pedido;
import icc.pap.pl1.patrones.modelo.TipoProducto;
import icc.pap.pl1.patrones.prototype.Cafe;
import icc.pap.pl1.patrones.prototype.Ensalada;
import icc.pap.pl1.patrones.prototype.Pizza;
import icc.pap.pl1.patrones.prototype.RegistroPrototipos;

public class ServicioPedidos {
    private final RegistroPrototipos registro = new RegistroPrototipos();
    private final List<Pedido> pedidosCreados = new ArrayList<>();
    private int contadorId = 1;

    public ServicioPedidos() {
        inicializarRegistro();
    }

    private void inicializarRegistro() {
        registro.registrarPrototipo("cafe", new Cafe("BASE-CAFE"));
        registro.registrarPrototipo("ensalada", new Ensalada("BASE-ENSALADA"));
        registro.registrarPrototipo("pizza", new Pizza("BASE-PIZZA"));
    }

    public Pedido clonarProducto(String clave) {
        String nuevoId = String.format("P-%03d", contadorId++);
        Pedido nuevoPedido = registro.clonar(clave, nuevoId);
        pedidosCreados.add(nuevoPedido);
        return nuevoPedido;
    }

    public boolean esIngredienteCompatible(TipoProducto tipo, String ingrediente) {
        switch (tipo) {
            case CAFE:
                return ingrediente.equals("Azucar") || ingrediente.equals("Crema");
            case ENSALADA:
                return ingrediente.equals("Tomate") || ingrediente.equals("Pollo");
            case PIZZA:
                return ingrediente.equals("Queso") || ingrediente.equals("Pepperoni");
            default:
                return false;
        }
    }

    public Pedido agregarIngrediente(Pedido pedidoActual, String ingrediente) {
        if (pedidoActual == null) {
            throw new IllegalArgumentException("No hay ningun pedido seleccionado.");
        }
        if (!esIngredienteCompatible(pedidoActual.getTipo(), ingrediente)) {
            throw new IllegalArgumentException("El ingrediente " + ingrediente + " no es compatible con el producto " + pedidoActual.getTipo().getNombre());
        }

        Pedido pedidoDecorado;
        switch (ingrediente) {
            case "Azucar":
                pedidoDecorado = new Azucar(pedidoActual);
                break;
            case "Crema":
                pedidoDecorado = new Crema(pedidoActual);
                break;
            case "Tomate":
                pedidoDecorado = new Tomate(pedidoActual);
                break;
            case "Pollo":
                pedidoDecorado = new Pollo(pedidoActual);
                break;
            case "Queso":
                pedidoDecorado = new Queso(pedidoActual);
                break;
            case "Pepperoni":
                pedidoDecorado = new Pepperoni(pedidoActual);
                break;
            default:
                throw new IllegalArgumentException("Ingrediente desconocido: " + ingrediente);
        }

        // Reemplazar en la lista de pedidos
        int index = pedidosCreados.indexOf(pedidoActual);
        if (index != -1) {
            pedidosCreados.set(index, pedidoDecorado);
        }

        return pedidoDecorado;
    }

    public void reiniciarPedidos() {
        pedidosCreados.clear();
        contadorId = 1;
    }

    public List<Pedido> getPedidosCreados() {
        return pedidosCreados;
    }
}