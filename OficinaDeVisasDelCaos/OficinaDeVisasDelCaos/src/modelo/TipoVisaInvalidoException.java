package modelo;

public class TipoVisaInvalidoException extends Exception {
    public TipoVisaInvalidoException(int codigo) {
        super("Tipo de visa desconocido: " + codigo + ". Validos: " + listarValidos());
    }

    private static String listarValidos() {
        StringBuilder sb = new StringBuilder();
        for (TipoVisa t : TipoVisa.values()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(t.getCodigo()).append("=").append(t.name());
        }
        return sb.toString();
    }
}
