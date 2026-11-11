package evaluacion;

import modelo.ResultadoEvaluacion;
import modelo.TipoVisa;
import modelo.TipoVisaInvalidoException;

public class EvaluadorDeVisa {

    public ResultadoEvaluacion evaluar(TipoVisa tipo, int edad) {
        return tipo.admiteEdad(edad) ? ResultadoEvaluacion.APROBADA : ResultadoEvaluacion.RECHAZADA;
    }

    /** Puente para codigos externos: un codigo desconocido lanza un error claro, no un rechazo mudo. */
    public ResultadoEvaluacion evaluar(int codigo, int edad) throws TipoVisaInvalidoException {
        return evaluar(TipoVisa.desdeCodigo(codigo), edad);
    }
}
