package tarifas;

public class ConfiguracionDeTarifas {
    public static final double PRECIO_POR_MES_INICIAL = 50;
    public static final double CARGO_FIJO = 10;

    private double precioPorMes = PRECIO_POR_MES_INICIAL;
    private int ediciones = 0;

    public void setPrecioPorMes(double precio) { this.precioPorMes = precio; ediciones++; }
    public double getPrecioPorMes() { return precioPorMes; }
    public double getCargoFijo() { return CARGO_FIJO; }
    public int getEdiciones() { return ediciones; }
}
