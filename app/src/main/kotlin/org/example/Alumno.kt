class Alumno(val nombre: String, val apellido: String, val id: Int){
    private val Evaluaciones = mutableListOf<Evaluaciones>()

    init{ (if id <= 0) {
        throw IllegalArgumentException("El ID es incorrecto, vuelva a intentarlo")
    }
    if (nombre.isEmpty || apellido.isEmpty){
        throw IllegalArgumentException("El nombre o apellido no pueden estar vacíos")
    }
    fun agregarEvaluaciones(evaluaciones: Evaluaciones){
        evaluaciones.add(evaluaciones)
         println("✓ Evaluación agregada a $nombre $apellido: ${evaluacion.descripcion}")
    }
    
    // Método público para calcular promedio - Lógica de negocio
    fun calcularPromedio(): Double {
        return if (evaluaciones.isEmpty()) {
            0.0
        } else {
            evaluaciones.map { it.nota }.average()
        }
    }
    
    // Método adicional para obtener información (solo lectura)
    fun obtenerEvaluaciones(): List<Evaluacion> {
        // Retorna una copia de solo lectura - no permite modificaciones externas
        return evaluaciones.toList()
    }
    
    // Método para obtener cantidad de evaluaciones
    fun cantidadEvaluaciones(): Int = evaluaciones.size
    
    // Método para mostrar información completa
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
}
    }






}