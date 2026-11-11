package tramite;

public class TramiteDeVisa {
    public static final String MSG_VISA_EN_CAMINO = "su visa va en camino";

    private final ValidadorDePasaporte validador;
    private final CajaDeCobros caja;
    private final ArchivoDeExpedientes archivo;
    private final NotificadorDeCorreo correo;

    public TramiteDeVisa(ValidadorDePasaporte validador, CajaDeCobros caja,
                         ArchivoDeExpedientes archivo, NotificadorDeCorreo correo) {
        this.validador = validador;
        this.caja = caja;
        this.archivo = archivo;
        this.correo = correo;
    }

    public boolean tramitar(String idSolicitud, String viajero, String pasaporte, double precio) {
        if (!validador.esValido(pasaporte)) {
            System.out.println("  [ValidadorDePasaporte] Pasaporte invalido para " + viajero);
            return false;
        }
        caja.cobrar(idSolicitud, viajero, precio);
        archivo.guardar(viajero);
        correo.enviar(viajero, MSG_VISA_EN_CAMINO);
        return true;
    }
}
