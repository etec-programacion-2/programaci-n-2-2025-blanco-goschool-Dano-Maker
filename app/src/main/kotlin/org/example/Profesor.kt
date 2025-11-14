package org.example

/**
 * Clase Profesor que hereda de Persona.
 * Demuestra Herencia - otra implementación de Persona.
 */
class Profesor(
    nombre: String,
    apellido: String,
    id: Int,
    val especialidad: String
) : Persona(nombre, apellido, id) {

    private val materiasAsignadas = mutableListOf<Materia>()

    fun asignarMateria(materia: Materia) {
        if (!materiasAsignadas.contains(materia)) {
            materiasAsignadas.add(materia)
            println("✓ Materia ${materia.nombre} asignada a ${obtenerNombreCompleto()}")
        }
    }

    fun obtenerMaterias(): List<Materia> {
        return materiasAsignadas.toList()
    }

    /**
     * Implementación del método abstracto de Persona (polimorfismo)
     */
    override fun mostrarInformacion() {
        println("\n=== INFORMACIÓN DEL PROFESOR ===")
        println("ID: $id")
        println("Nombre: ${obtenerNombreCompleto()}")
        println("Especialidad: $especialidad")
        println("Materias asignadas: ${materiasAsignadas.size}")

        if (materiasAsignadas.isNotEmpty()) {
            println("\nMaterias:")
            materiasAsignadas.forEachIndexed { index, materia ->
                println("  ${index + 1}. ${materia.nombre}")
            }
        }
    }

    /**
     * Sobreescritura del método de Persona (polimorfismo)
     */
    override fun obtenerNombreCompleto(): String {
        return "Prof. ${super.obtenerNombreCompleto()}"
    }

    override fun toString(): String {
        return "${obtenerNombreCompleto()} - $especialidad (${materiasAsignadas.size} materias)"
    }
}