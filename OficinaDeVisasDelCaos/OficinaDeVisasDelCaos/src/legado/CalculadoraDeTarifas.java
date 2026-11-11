package legado;


public class CalculadoraDeTarifas {

    private double baseTurista = 50;
    private double baseEstudiante = 50;
    private double baseTrabajo = 50;
    private int ediciones = 0;

    public void editarBaseTurista(double v)    { baseTurista = v;    ediciones++; }
    public void editarBaseEstudiante(double v) { baseEstudiante = v; ediciones++; }
    public void editarBaseTrabajo(double v)    { baseTrabajo = v;    ediciones++; }
    public int getEdiciones() { return ediciones; }

    public double visaTurista(int meses) {
        double total = baseTurista * meses;
        total = total + 10;
        return total;
    }

    public double visaEstudiante(int meses) {
        double total = baseEstudiante * meses;
        total = total + 10;
        return total * 0.5;
    }

    public double visaTrabajo(int meses) {
        double total = baseTrabajo * meses;
        total = total + 10;
        return total * 1.5;
    }
}
