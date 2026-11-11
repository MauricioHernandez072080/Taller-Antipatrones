package tramite;

public class ValidadorDePasaporte {
    public static final int LONGITUD_MINIMA = 6;

    public boolean esValido(String pasaporte) {
        return pasaporte != null && pasaporte.length() >= LONGITUD_MINIMA;
    }
}
