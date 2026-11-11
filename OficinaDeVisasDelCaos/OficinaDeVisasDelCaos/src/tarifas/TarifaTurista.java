package tarifas;

public class TarifaTurista implements EstrategiaDeTarifa {
    public static final double FACTOR = 1.0;
    @Override public double aplicar(double subtotal) { return subtotal * FACTOR; }
}
