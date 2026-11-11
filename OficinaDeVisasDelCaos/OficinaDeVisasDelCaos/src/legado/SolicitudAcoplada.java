package legado;


public class SolicitudAcoplada {

    private String estado = "ACTIVA";
    private CorreoServicio correo = new CorreoServicio();
    private CitasServicio citas = new CitasServicio();
    private PagosServicio pagos = new PagosServicio();

    public void cancelar() {
        estado = "CANCELADA";
        correo.avisarCancelacion();
        citas.liberarCupo();
        // OLVIDADO: pagos.reembolsar();  <- el viernes que se perdio el reembolso
    }

    public String getEstado() { return estado; }
    public boolean reembolsoRealizado() { return pagos.isReembolsado(); }
}
