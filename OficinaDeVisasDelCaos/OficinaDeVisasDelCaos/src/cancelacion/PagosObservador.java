package cancelacion;

import tramite.CajaDeCobros;

public class PagosObservador implements ObservadorDeSolicitud {
    private final CajaDeCobros caja;

    public PagosObservador(CajaDeCobros caja) { this.caja = caja; }

    @Override public void alCancelarse(Solicitud s) { caja.reembolsar(s.getId()); }
}
