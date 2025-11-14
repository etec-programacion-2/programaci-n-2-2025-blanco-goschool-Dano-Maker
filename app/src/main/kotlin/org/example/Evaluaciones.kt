package org.example
class Evaluaciones(val descripcion: String, val nota: Double) {
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