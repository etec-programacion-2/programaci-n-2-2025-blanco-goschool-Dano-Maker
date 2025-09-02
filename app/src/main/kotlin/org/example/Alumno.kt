class Alumno(val nombre: String, val apellido: String, val id: Int) {
    // ✅ Nombre consistente en minúscula
    private val evaluaciones = mutableListOf<Evaluacion>()

    // ✅ init con sintaxis correcta
    init {
        if (id <= 0) {
            throw IllegalArgumentException("El ID es incorrecto, vuelva a intentarlo")
        }
        if (nombre.isEmpty() || apellido.isEmpty()) {
            throw IllegalArgumentException("El nombre o apellido no pueden estar vacíos")
        }
    } // ✅ init termina aquí

    // ✅ Función FUERA del init, nombre singular, parámetro correcto
    fun agregarEvaluacion(evaluacion: Evaluacion) {
        evaluaciones.add(evaluacion) // ✅ lista.add(parámetro)
        println("✓ Evaluación agregada a $nombre $apellido: ${evaluacion.descripcion}")
    }

    // ✅ Método público para calcular promedio
    fun calcularPromedio(): Double {
        return if (evaluaciones.isEmpty()) {
            0.0
        } else {
            evaluaciones.map { it.nota }.average()
        }
    }

    // ✅ Método para obtener información (solo lectura)
    fun obtenerEvaluaciones(): List<Evaluacion> {
        return evaluaciones.toList()
    }

    // ✅ Método para obtener cantidad de evaluaciones
    fun cantidadEvaluaciones(): Int = evaluaciones.size

    // ✅ Método para mostrar información completa
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
        }
    }

    override fun toString(): String {
        return "$nombre $apellido (ID: $id) - Promedio: ${String.format("%.2f", calcularPromedio())}"
    }
} // ✅ Solo UNA llave de cierre

// También necesitas la clase Evaluacion:
class Evaluacion(val descripcion: String, val nota: Double) {
    init {
        if (nota < 0.0 || nota > 10.0) {
            throw IllegalArgumentException("La nota debe estar entre 0.0 y 10.0")
        }
        if (descripcion.isBlank()) {
            throw IllegalArgumentException("La descripción no puede estar vacía")
        }
    }

    override fun toString(): String {
        return "$descripcion: $nota"
    }
}