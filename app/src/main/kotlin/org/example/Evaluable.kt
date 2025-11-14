package org.example

// Define el comportamiento de entidades que pueden ser evaluadas
interface Evaluable {
    fun calcularPromedio(): Double
    fun cantidadEvaluaciones(): Int
    fun tieneEvaluaciones(): Boolean = cantidadEvaluaciones() > 0
}