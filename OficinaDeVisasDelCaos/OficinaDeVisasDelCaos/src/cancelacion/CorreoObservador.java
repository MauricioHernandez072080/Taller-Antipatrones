package cancelacion;

import tramite.NotificadorDeCorreo;

public class CorreoObservador implements ObservadorDeSolicitud {
    public static final String MSG_CANCELACION = "su solicitud fue cancelada";
    private final NotificadorDeCorreo correo;

    public CorreoObservador(NotificadorDeCorreo correo) { this.correo = correo; }

    @Override public void alCancelarse(Solicitud s) { correo.enviar(s.getViajero(), MSG_CANCELACION); }
}
