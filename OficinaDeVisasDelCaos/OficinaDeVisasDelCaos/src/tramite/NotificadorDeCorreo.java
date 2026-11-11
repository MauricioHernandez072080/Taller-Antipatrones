package tramite;

public class NotificadorDeCorreo {
    public void enviar(String viajero, String mensaje) {
        System.out.println("  [NotificadorDeCorreo] Correo para " + viajero + ": " + mensaje);
    }
}
