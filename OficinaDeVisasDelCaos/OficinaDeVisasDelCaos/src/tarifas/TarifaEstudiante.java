package tarifas;

public class TarifaEstudiante implements EstrategiaDeTarifa {
    public static final double FACTOR_DESCUENTO = 0.5;
    @Override public double aplicar(double subtotal) { return subtotal * FACTOR_DESCUENTO; }
}
