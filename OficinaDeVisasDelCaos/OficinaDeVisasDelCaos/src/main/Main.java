package main;

import cancelacion.*;
import evaluacion.EvaluadorDeVisa;
import legado.*;
import modelo.*;
import tarifas.*;
import tramite.*;

public class Main {

    private static final double PRECIO_BASE_NUEVO = 60;
    private static final int MESES = 3;

    public static void main(String[] args) {
        caso1GodObject();
        caso2CopyPaste();
        caso3MagicNumbers();
        caso4Acoplamiento();
        caso5Integracion();
    }

    private static void titulo(String t) {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println(t);
        System.out.println("==============================================================");
    }

    private static void sub(String t) { System.out.println("-- " + t); }

    private static void caso1GodObject() {
        titulo("CASO 1 - God Object");
        sub("HEREDADA: OficinaDeTodo (Ramiro lo hace todo)");
        new OficinaDeTodo().procesar("Ana Torres", "AB123456", 190.0);

        sub("RESCATADA: una clase por responsabilidad");
        TramiteDeVisa tramite = new TramiteDeVisa(new ValidadorDePasaporte(), new CajaDeCobros(),
                new ArchivoDeExpedientes(), new NotificadorDeCorreo());
        boolean ok = tramite.tramitar("S-1", "Ana Torres", "AB123456", 190.0);
        System.out.println("Ambas aprueban el tramite: " + ok);
    }

    private static void caso2CopyPaste() {
        titulo("CASO 2 - Copy-Paste Programming (base 50 -> " + (int) PRECIO_BASE_NUEVO + ")");

        CalculadoraDeTarifas clonada = new CalculadoraDeTarifas();
        sub("HEREDADA: precios con base 50 (" + MESES + " meses)");
        System.out.printf("  turista=%.1f  estudiante=%.1f  trabajo=%.1f%n",
                clonada.visaTurista(MESES), clonada.visaEstudiante(MESES), clonada.visaTrabajo(MESES));
        clonada.editarBaseTurista(PRECIO_BASE_NUEVO);
        clonada.editarBaseEstudiante(PRECIO_BASE_NUEVO);
        clonada.editarBaseTrabajo(PRECIO_BASE_NUEVO);
        System.out.println("  Lugares editados en la version clonada: " + clonada.getEdiciones());
        System.out.printf("  turista=%.1f  estudiante=%.1f  trabajo=%.1f%n",
                clonada.visaTurista(MESES), clonada.visaEstudiante(MESES), clonada.visaTrabajo(MESES));

        ConfiguracionDeTarifas config = new ConfiguracionDeTarifas();
        CalculadoraDePrecios strategy = new CalculadoraDePrecios(config);
        sub("RESCATADA (Strategy): precios con base 50 (" + MESES + " meses)");
        System.out.printf("  turista=%.1f  estudiante=%.1f  trabajo=%.1f%n",
                strategy.calcular(TipoVisa.TURISTA, MESES), strategy.calcular(TipoVisa.ESTUDIANTE, MESES),
                strategy.calcular(TipoVisa.TRABAJO, MESES));
        config.setPrecioPorMes(PRECIO_BASE_NUEVO);
        System.out.println("  Lugares editados en la version Strategy: " + config.getEdiciones());
        System.out.printf("  turista=%.1f  estudiante=%.1f  trabajo=%.1f%n",
                strategy.calcular(TipoVisa.TURISTA, MESES), strategy.calcular(TipoVisa.ESTUDIANTE, MESES),
                strategy.calcular(TipoVisa.TRABAJO, MESES));

        boolean coinciden = clonada.visaTurista(MESES) == strategy.calcular(TipoVisa.TURISTA, MESES)
                && clonada.visaEstudiante(MESES) == strategy.calcular(TipoVisa.ESTUDIANTE, MESES)
                && clonada.visaTrabajo(MESES) == strategy.calcular(TipoVisa.TRABAJO, MESES);
        System.out.println("El precio final coincide en ambas: " + coinciden);
    }

 
    private static void caso3MagicNumbers() {
        titulo("CASO 3 - Magic Numbers");
        EvaluadorMagico viejo = new EvaluadorMagico();
        EvaluadorDeVisa nuevo = new EvaluadorDeVisa();

        sub("HEREDADA: evaluar(2, 20)");
        System.out.println("  " + viejo.evaluar(2, 20));
        sub("RESCATADA: evaluar(TipoVisa.ESTUDIANTE, 20)");
        System.out.println("  " + nuevo.evaluar(TipoVisa.ESTUDIANTE, 20));

        sub("HEREDADA: evaluar(9, 20)  (tipo inexistente)");
        System.out.println("  " + viejo.evaluar(9, 20) + "   <- rechazo en silencio, sin saber por que");
        sub("RESCATADA: tipo 9");
        try {
            nuevo.evaluar(9, 20);
        } catch (TipoVisaInvalidoException e) {
            System.out.println("  ERROR CLARO: " + e.getMessage());
        }
    }

    private static void caso4Acoplamiento() {
        titulo("CASO 4 - Acoplamiento fuerte");
        sub("HEREDADA: SolicitudAcoplada.cancelar()");
        SolicitudAcoplada vieja = new SolicitudAcoplada();
        vieja.cancelar();
        System.out.println("  Estado: " + vieja.getEstado());
        if (!vieja.reembolsoRealizado()) {
            System.out.println("  !!! REEMBOLSO OLVIDADO: nadie llamo a Pagos");
        }

        sub("RESCATADA: Solicitud + Observer");
        CajaDeCobros caja = new CajaDeCobros();
        caja.cobrar("S-4", "Luis Perez", 190.0);
        Solicitud nueva = new Solicitud("S-4", "Luis Perez");
        nueva.agregarObservador(new CorreoObservador(new NotificadorDeCorreo()));
        nueva.agregarObservador(new CitasObservador(new AgendaDeCitas()));
        nueva.agregarObservador(new PagosObservador(caja));
        nueva.cancelar();
        System.out.println("  Estado: " + nueva.getEstado() + " (la solicitud nunca conocio a los servicios)");
    }

    private static void caso5Integracion() {
        titulo("CASO 5 - Integracion: solicitar, evaluar, cobrar y cancelar");

        ConfiguracionDeTarifas config = new ConfiguracionDeTarifas();
        CalculadoraDePrecios precios = new CalculadoraDePrecios(config);
        EvaluadorDeVisa evaluador = new EvaluadorDeVisa();
        CajaDeCobros caja = new CajaDeCobros();
        NotificadorDeCorreo correo = new NotificadorDeCorreo();
        TramiteDeVisa tramite = new TramiteDeVisa(new ValidadorDePasaporte(), caja,
                new ArchivoDeExpedientes(), correo);

        sub("1) Solicitar");
        Solicitud s = new Solicitud("S-5", "Marta Gomez");
        s.agregarObservador(new CorreoObservador(correo));
        s.agregarObservador(new CitasObservador(new AgendaDeCitas()));
        s.agregarObservador(new PagosObservador(caja));
        System.out.println("  Solicitud " + s.getId() + " creada para " + s.getViajero() + " [" + s.getEstado() + "]");

        sub("2) Evaluar (TRABAJO, 30 anios)");
        ResultadoEvaluacion r = evaluador.evaluar(TipoVisa.TRABAJO, 30);
        System.out.println("  Resultado: " + r);

        if (r == ResultadoEvaluacion.APROBADA) {
            sub("3) Cobrar (" + MESES + " meses)");
            double precio = precios.calcular(TipoVisa.TRABAJO, MESES);
            tramite.tramitar(s.getId(), s.getViajero(), "XY987654", precio);
        }

        sub("4) Cancelar");
        s.cancelar();
        System.out.println("  Estado final: " + s.getEstado());

        System.out.println();
        System.out.println("Tabla final de antipatrones capturados");
        String linea = "+----------------------+------------------------------------+------------------------------+";
        System.out.println(linea);
        System.out.printf("| %-20s | %-34s | %-28s |%n", "Antipatron", "Sintoma", "Solucion");
        System.out.println(linea);
        fila("God Object", "Ramiro valida+cobra+guarda+avisa", "Responsabilidad unica");
        fila("Copy-Paste", "Formula x3; base editada 3 veces", "Strategy (1 punto de cambio)");
        fila("Magic Numbers", "tipo == 3, edad > 65, 0.5", "Enum + constantes con nombre");
        fila("Acoplamiento fuerte", "cancelar() olvido el reembolso", "Observer (3 observadores)");
        System.out.println(linea);
    }

    private static void fila(String a, String b, String c) {
        System.out.printf("| %-20s | %-34s | %-28s |%n", a, b, c);
    }
}
