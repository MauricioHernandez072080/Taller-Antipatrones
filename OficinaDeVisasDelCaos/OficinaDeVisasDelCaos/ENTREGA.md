# Taller para la Casa – Antipatrones: La Oficina de Visas del Caos (Java)

Modelos de Programación – Universidad Distrital Francisco José de Caldas

## Parte 1. Cacería de antipatrones

| Fragmento | Antipatrón | Síntoma que lo delata | Consecuencia en el consulado |
|---|---|---|---|
| 1. `OficinaDeTodo` (Ramiro) | **God Object** | Una sola clase valida el pasaporte, cobra, guarda el expediente y envía el correo. | Si Ramiro falla o se enferma, todo el consulado se detiene; no se puede cambiar una tarea sin riesgo de romper las otras ni probarlas por separado. |
| 2. `CalculadoraDeTarifas` | **Copy-Paste Programming** | `50 * meses + 10` está copiado en `visaTurista`, `visaEstudiante` y `visaTrabajo`. | Al subir la tarifa base se corrigieron dos copias y la tercera quedó con el precio viejo: cobros inconsistentes. |
| 3. `EvaluadorMagico` | **Magic Numbers** | `tipo == 3`, `edad > 17`, `edad < 66`, `0.5`, sin nombre ni significado. | Nadie sabe qué significan (¿3 es trabajo?); un tipo inexistente como 9 se rechaza en silencio y los límites de edad se cambian a ciegas. |
| 4. `SolicitudAcoplada` | **Acoplamiento fuerte** | La solicitud crea con `new` a los tres servicios y debe llamarlos uno por uno. | Un viernes se olvidó llamar a Pagos y un viajero perdió su reembolso; agregar un aviso nuevo obliga a editar la solicitud. |

## Parte 2. El rescate (paquetes)

| Paquete | Contenido | Soluciona |
|---|---|---|
| `legado` | Los 4 fragmentos originales (evidencia) | — |
| `modelo` | `TipoVisa` (enum con código y rango de edad), `ResultadoEvaluacion`, `EstadoSolicitud`, `TipoVisaInvalidoException` | Magic Numbers |
| `tramite` | `ValidadorDePasaporte`, `CajaDeCobros`, `ArchivoDeExpedientes`, `NotificadorDeCorreo`, `TramiteDeVisa` (solo coordina) | God Object |
| `tarifas` | `ConfiguracionDeTarifas`, `EstrategiaDeTarifa`, `TarifaTurista/Estudiante/Trabajo`, `CalculadoraDePrecios` | Copy-Paste (Strategy) |
| `evaluacion` | `EvaluadorDeVisa` | Magic Numbers |
| `cancelacion` | `Solicitud` (subject), `ObservadorDeSolicitud`, `CorreoObservador`, `CitasObservador`, `PagosObservador` | Acoplamiento (Observer) |
| `main` | `Main` con los 5 casos de prueba | — |

## Parte 3. Salida en consola

Ver `salida_consola.txt` (generada con `java -cp out main.Main`).

## Cómo la implementación evita caer de nuevo en los antipatrones

Cada clase nueva tiene una sola razón para cambiar: el validador, la caja, el archivo y el correo son clases separadas, y `TramiteDeVisa` solo coordina, así que no hay un nuevo Ramiro. La fórmula del precio existe una única vez en `CalculadoraDePrecios` y el precio base vive en `ConfiguracionDeTarifas`; cada tipo de visa solo aporta su factor mediante una estrategia, por lo que subir la tarifa o agregar un tipo nuevo se hace en un solo lugar. Los números y textos con significado son constantes con nombre o enums (`TipoVisa`, `EstadoSolicitud`, `ResultadoEvaluacion`), y un código inválido lanza un error claro en lugar de rechazar en silencio. Finalmente, `Solicitud` solo conoce la interfaz `ObservadorDeSolicitud`: agregar un aviso nuevo es registrar otro observador, sin tocar la solicitud y sin que nadie tenga que acordarse de llamar al servicio.

## Compilar y ejecutar

```
mkdir out
javac -d out $(find src -name '*.java')
java -cp out main.Main
```
