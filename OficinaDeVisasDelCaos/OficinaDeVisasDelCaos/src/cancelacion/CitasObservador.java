package cancelacion;

public class CitasObservador implements ObservadorDeSolicitud {
    private final AgendaDeCitas agenda;

    public CitasObservador(AgendaDeCitas agenda) { this.agenda = agenda; }

    @Override public void alCancelarse(Solicitud s) { agenda.liberarCupo(s.getId()); }
}
