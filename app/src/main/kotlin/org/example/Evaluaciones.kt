class Evaluaciones (val descripción: String, val calificación: Double) {
    init {
        if calificación < 0.0 || calificación > 10.0 {
            throw IllegalArgumentException("La calificación debe estar entre 0.0 y 10.0")
        }
    if (descripción.isEmpty()) {
        throw IllegalArgumentException("La descripción no puede estar vacía")
    }    
    }
    override fun toString(): String {
        return "$descripción: $calificación"
    }
}
