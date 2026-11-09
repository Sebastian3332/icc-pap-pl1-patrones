package icc.pap.pl1.patrones.prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroPrototipos {
    private final Map<String, ProductoBase> prototipos = new HashMap<>();

    public void registrarPrototipo(String clave, ProductoBase prototipo) {
        prototipos.put(clave, prototipo);
    }

    public ProductoBase clonar(String clave, String nuevoId) {
        ProductoBase prototipo = prototipos.get(clave);
        if (prototipo == null) {
            throw new IllegalArgumentException("Clave inexistente en el registro de prototipos: " + clave);
        }
        return prototipo.copiar(nuevoId);
    }
}