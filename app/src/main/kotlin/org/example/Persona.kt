package org.example

/**
 * Clase abstracta base que representa a una persona en el sistema escolar.
 * Implementa el principio de Herencia - todas las personas del sistema heredan de esta clase.
 */
abstract class Persona(
    val nombre: String,
    val apellido: String,
    val id: Int
) {
    init {
        if (id <= 0) {
            throw IllegalArgumentException("El ID debe ser mayor a 0")
        }
        if (nombre.isBlank() || apellido.isBlank()) {
            throw IllegalArgumentException("Nombre y apellido no pueden estar vacíos")
        }
    }

    /**
     * Método abstracto que debe ser implementado por las subclases.
     * Esto demuestra polimorfismo.
     */
    abstract fun mostrarInformacion()

    /**
     * Método concreto que puede ser sobreescrito (polimorfismo por sobreescritura)
     */
    open fun obtenerNombreCompleto(): String {
        return "$nombre $apellido"
    }

    override fun toString(): String {
        return "${obtenerNombreCompleto()} (ID: $id)"
    }
}