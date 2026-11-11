package tramite;

import java.util.HashMap;
import java.util.Map;

public class CajaDeCobros {
    private final Map<String, Double> cobros = new HashMap<>();

    public void cobrar(String idSolicitud, String viajero, double monto) {
        cobros.put(idSolicitud, monto);
        System.out.println("  [CajaDeCobros] Cobrando $" + monto + " a " + viajero);
    }

    public void reembolsar(String idSolicitud) {
        Double monto = cobros.remove(idSolicitud);
        if (monto != null) {
            System.out.println("  [CajaDeCobros] Reembolso de $" + monto + " (solicitud " + idSolicitud + ")");
        } else {
            System.out.println("  [CajaDeCobros] Nada que reembolsar (solicitud " + idSolicitud + ")");
        }
    }
}
