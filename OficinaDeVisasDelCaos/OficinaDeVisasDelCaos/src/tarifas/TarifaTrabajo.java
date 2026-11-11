package tarifas;

public class TarifaTrabajo implements EstrategiaDeTarifa {
    public static final double FACTOR_RECARGO = 1.5;
    @Override public double aplicar(double subtotal) { return subtotal * FACTOR_RECARGO; }
}
