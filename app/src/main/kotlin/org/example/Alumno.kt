class Alumno(val nombre: String, val apellido: String, val id: Int) {
    private val evaluaciones = mutableListOf<Evaluaciones>()

    init {
        if (id <= 0) {
            throw IllegalArgumentException("El ID es incorrecto, vuelva a intentarlo")
        }
        if (nombre.isEmpty() || apellido.isEmpty()) {
            throw IllegalArgumentException("El nombre o apellido no pueden estar vacíos")
        }
    }

    fun agregarEvaluacion(evaluacion: Evaluaciones) {
        evaluaciones.add(evaluacion)
        println("✓ Evaluación agregada a $nombre $apellido: ${evaluacion.descripcion}")
    }

    fun calcularPromedio(): Double {
        return if (evaluaciones.isEmpty()) {
            0.0
        } else {
            evaluaciones.map { it.nota }.average()
        }
    }

    fun obtenerEvaluaciones(): List<Evaluaciones> {
        return evaluaciones.toList()
    }

    fun cantidadEvaluaciones(): Int = evaluaciones.size

    fun mostrarInformacion() {
        println("\n=== INFORMACIÓN DEL ALUMNO ===")
        println("ID: $id")
        println("Nombre: $nombre $apellido")
        println("Cantidad de evaluaciones: ${cantidadEvaluaciones()}")
        println("Promedio: ${String.format("%.2f", calcularPromedio())}")

        if (evaluaciones.isNotEmpty()) {
            println("\nEvaluaciones:")
            evaluaciones.forEachIndexed { index, eval ->
                println("  ${index + 1}. $eval")
            }
        } else {
            println("No hay evaluaciones registradas")
        }
    }

    override fun toString(): String {
        return "$nombre $apellido (ID: $id) - Promedio: ${String.format("%.2f", calcularPromedio())}"
    }
}