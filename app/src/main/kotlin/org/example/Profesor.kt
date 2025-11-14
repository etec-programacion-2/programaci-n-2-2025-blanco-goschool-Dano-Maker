package org.example

class Profesor(
    nombre: String,
    apellido: String,
    id: Int,
    val especialidad: String
) : Persona(nombre, apellido, id) {

    private val materiasAsignadas = mutableListOf<Materia>()

    // Asigna una materia al profesor si no la tiene ya asignada
    fun asignarMateria(materia: Materia) {
        if (!materiasAsignadas.contains(materia)) {
            materiasAsignadas.add(materia)
            println("✓ Materia ${materia.nombre} asignada a ${obtenerNombreCompleto()}")
        }
    }

    fun obtenerMaterias(): List<Materia> = materiasAsignadas.toList()

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

    override fun obtenerNombreCompleto(): String = "Prof. ${super.obtenerNombreCompleto()}"

    override fun toString(): String {
        return "${obtenerNombreCompleto()} - $especialidad (${materiasAsignadas.size} materias)"
    }
}
