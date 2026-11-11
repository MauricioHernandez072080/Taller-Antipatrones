package cancelacion;

import java.util.ArrayList;
import java.util.List;
import modelo.EstadoSolicitud;

public class Solicitud {
    private final String id;
    private final String viajero;
    private EstadoSolicitud estado = EstadoSolicitud.ACTIVA;
    private final List<ObservadorDeSolicitud> observadores = new ArrayList<>();

    public Solicitud(String id, String viajero) { this.id = id; this.viajero = viajero; }

    public void agregarObservador(ObservadorDeSolicitud o) { observadores.add(o); }

    public void cancelar() {
        if (estado == EstadoSolicitud.CANCELADA) return;
        estado = EstadoSolicitud.CANCELADA;
        for (ObservadorDeSolicitud o : observadores) o.alCancelarse(this);
    }

    public String getId() { return id; }
    public String getViajero() { return viajero; }
    public EstadoSolicitud getEstado() { return estado; }
}
