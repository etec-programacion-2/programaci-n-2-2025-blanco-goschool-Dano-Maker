package org.example

/**
 * Clase Alumno que hereda de Persona e implementa Evaluable.
 * Demuestra Herencia y Polimorfismo.
 */
class Alumno(
    nombre: String,
    apellido: String,
    id: Int
) : Persona(nombre, apellido, id), Evaluable {

    private val evaluaciones = mutableListOf<Evaluaciones>()

    fun agregarEvaluacion(evaluacion: Evaluaciones) {
        evaluaciones.add(evaluacion)
        println("✓ Evaluación agregada a ${obtenerNombreCompleto()}: ${evaluacion.descripcion}")
    }

    /**
     * Implementación de la interface Evaluable (polimorfismo)
     */
    override fun calcularPromedio(): Double {
        return if (evaluaciones.isEmpty()) {
            0.0
        } else {
            evaluaciones.map { it.nota }.average()
        }
    }

    /**
     * Implementación de la interface Evaluable (polimorfismo)
     */
    override fun cantidadEvaluaciones(): Int = evaluaciones.size

    fun obtenerEvaluaciones(): List<Evaluaciones> {
        return evaluaciones.toList()
    }

    /**
     * Implementación del método abstracto de Persona (polimorfismo por herencia)
     */
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

    /**
     * Sobreescritura del método de Persona (polimorfismo)
     */
    override fun toString(): String {
        return "${obtenerNombreCompleto()} (ID: $id) - Promedio: ${String.format("%.2f", calcularPromedio())}"
    }
}