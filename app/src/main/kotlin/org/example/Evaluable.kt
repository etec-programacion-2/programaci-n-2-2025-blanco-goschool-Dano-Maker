package org.example

/**
 * Interface que define el comportamiento de entidades evaluables.
 * Implementa el principio de Polimorfismo mediante interfaces.
 */
interface Evaluable {
    /**
     * Calcula el promedio de evaluaciones
     */
    fun calcularPromedio(): Double

    /**
     * Obtiene la cantidad de evaluaciones
     */
    fun cantidadEvaluaciones(): Int

    /**
     * Verifica si la entidad tiene evaluaciones
     */
    fun tieneEvaluaciones(): Boolean {
        return cantidadEvaluaciones() > 0
    }
}