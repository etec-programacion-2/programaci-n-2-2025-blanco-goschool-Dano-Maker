package org.example

class Alumno(
    nombre: String,
    apellido: String,
    id: Int
) : Persona(nombre, apellido, id), Evaluable {

    private val evaluaciones = mutableListOf<Evaluaciones>()

    // Agrega una nueva evaluación al historial del alumno
    fun agregarEvaluacion(evaluacion: Evaluaciones) {
        evaluaciones.add(evaluacion)
        println("✓ Evaluación agregada a ${obtenerNombreCompleto()}: ${evaluacion.descripcion}")
    }

    override fun calcularPromedio(): Double {
        return if (evaluaciones.isEmpty()) 0.0 else evaluaciones.map { it.nota }.average()
    }

    override fun cantidadEvaluaciones(): Int = evaluaciones.size

    fun obtenerEvaluaciones(): List<Evaluaciones> = evaluaciones.toList()

    override fun mostrarInformacion() {
        println("\n=== INFORMACIÓN DEL ALUMNO ===")
        println("ID: $id")
        println("Nombre: ${obtenerNombreCompleto()}")
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
        return "${obtenerNombreCompleto()} (ID: $id) - Promedio: ${String.format("%.2f", calcularPromedio())}"
    }
}