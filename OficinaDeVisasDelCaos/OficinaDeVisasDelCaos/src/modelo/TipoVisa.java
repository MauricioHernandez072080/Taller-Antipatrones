package modelo;

public enum TipoVisa {
    TURISTA(1, 18, TipoVisa.SIN_LIMITE),
    ESTUDIANTE(2, 16, 30),
    TRABAJO(3, 18, 65);

    public static final int SIN_LIMITE = Integer.MAX_VALUE;

    private final int codigo;
    private final int edadMinima;
    private final int edadMaxima;

    TipoVisa(int codigo, int edadMinima, int edadMaxima) {
        this.codigo = codigo;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public int getCodigo() { return codigo; }

    public boolean admiteEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    public static TipoVisa desdeCodigo(int codigo) throws TipoVisaInvalidoException {
        for (TipoVisa t : values()) {
            if (t.codigo == codigo) return t;
        }
        throw new TipoVisaInvalidoException(codigo);
    }
}
