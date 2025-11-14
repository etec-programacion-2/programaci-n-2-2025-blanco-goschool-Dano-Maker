package org.example

// Clase base abstracta para todas las personas del sistema escolar
abstract class Persona(
    val nombre: String,
    val apellido: String,
    val id: Int
) {
    init {
        if (id <= 0) throw IllegalArgumentException("El ID debe ser mayor a 0")
        if (nombre.isBlank() || apellido.isBlank()) {
            throw IllegalArgumentException("Nombre y apellido no pueden estar vacíos")
        }
    }

    abstract fun mostrarInformacion()

    open fun obtenerNombreCompleto(): String = "$nombre $apellido"

    override fun toString(): String = "${obtenerNombreCompleto()} (ID: $id)"
}