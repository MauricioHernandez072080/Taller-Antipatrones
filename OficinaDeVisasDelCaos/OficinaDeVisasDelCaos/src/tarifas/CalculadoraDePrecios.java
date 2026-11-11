package tarifas;

import java.util.EnumMap;
import java.util.Map;
import modelo.TipoVisa;

public class CalculadoraDePrecios {
    private final ConfiguracionDeTarifas config;
    private final Map<TipoVisa, EstrategiaDeTarifa> estrategias = new EnumMap<>(TipoVisa.class);

    public CalculadoraDePrecios(ConfiguracionDeTarifas config) {
        this.config = config;
        estrategias.put(TipoVisa.TURISTA, new TarifaTurista());
        estrategias.put(TipoVisa.ESTUDIANTE, new TarifaEstudiante());
        estrategias.put(TipoVisa.TRABAJO, new TarifaTrabajo());
    }

    public double calcular(TipoVisa tipo, int meses) {
        double subtotal = config.getPrecioPorMes() * meses + config.getCargoFijo();
        return estrategias.get(tipo).aplicar(subtotal);
    }
}
