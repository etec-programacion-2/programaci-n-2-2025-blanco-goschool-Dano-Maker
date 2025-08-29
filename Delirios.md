**Viernes 22/08/25**
Las tareas que lleve a cabo en este dia fueron, instalación de gradle y sdk más algunos problemas con las branches y avance en el diseño frontend

**Martes 26/08/25**
En el dia de hoysolucione los problemas con las branchs del dia anterior, logre pushear el repositorio a github y ademas empece a investigar el metodo que menciono el profe en la issue

charla con gradle
*// Clase Evaluacion - NO es data class porque necesita validación personalizada
class Evaluacion(val descripcion: String, val nota: Double) {
    
    // Bloque init: se ejecuta inmediatamente después del constructor
    init {
        // Validación de datos en tiempo de construcción
        if (nota < 0.0 || nota > 10.0) {
            throw IllegalArgumentException(
                "La nota debe estar entre 0.0 y 10.0. Valor recibido: $nota"
            )
        }
        
        // También podríamos validar la descripción
        if (descripcion.isBlank()) {
            throw IllegalArgumentException("La descripción no puede estar vacía")
        }
    }
    
    // Método personalizado para mostrar información
    override fun toString(): String {
        return "$descripcion: $nota"
    }
}

// Clase Alumno - NO es data class porque gestiona activamente sus evaluaciones
class Alumno(val id: Int, val nombre: String, val apellido: String) {
    
    // Propiedad PRIVADA - Encapsulamiento del estado interno
    // No se puede acceder directamente desde fuera de la clase
    private val evaluaciones: MutableList<Evaluacion> = mutableListOf()
    
    init {
        // Validaciones básicas del alumno
        if (id <= 0) {
            throw IllegalArgumentException("El ID debe ser mayor a 0")
        }
        if (nombre.isBlank() || apellido.isBlank()) {
            throw IllegalArgumentException("Nombre y apellido no pueden estar vacíos")
        }
    }
    
    // Método público para agregar evaluaciones - Control del estado
    fun agregarEvaluacion(evaluacion: Evaluacion) {
        evaluaciones.add(evaluacion)
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

// ============= DEMOSTRACIÓN Y PRUEBAS =============

fun main() {
    println("🎓 SISTEMA DE EVALUACIONES - DEMOSTRACIÓN")
    println("=" * 50)
    
    try {
        // 1. Crear alumno
        val alumno1 = Alumno(1, "Ana", "García")
        println("✓ Alumno creado: $alumno1")
        
        // 2. Crear evaluaciones válidas
        val eval1 = Evaluacion("Examen Parcial 1", 8.5)
        val eval2 = Evaluacion("Tarea 1", 9.0)
        val eval3 = Evaluacion("Examen Final", 7.8)
        
        // 3. Agregar evaluaciones
        alumno1.agregarEvaluacion(eval1)
        alumno1.agregarEvaluacion(eval2)
        alumno1.agregarEvaluacion(eval3)
        
        // 4. Mostrar información completa
        alumno1.mostrarInformacion()
        
        println("\n" + "=" * 50)
        
        // 5. Probar alumno sin evaluaciones
        val alumno2 = Alumno(2, "Carlos", "López")
        println("Alumno sin evaluaciones - Promedio: ${alumno2.calcularPromedio()}")
        
        println("\n" + "=" * 50)
        println("🧪 PROBANDO VALIDACIONES...")
        
        // 6. Probar validaciones - Nota fuera de rango
        println("\n1. Intentando crear evaluación con nota inválida...")
        try {
            val evalInvalida = Evaluacion("Test", 15.0) // Nota > 10.0
        } catch (e: IllegalArgumentException) {
            println("❌ Error capturado: ${e.message}")
        }
        
        // 7. Probar validaciones - Nota negativa
        println("\n2. Intentando crear evaluación con nota negativa...")
        try {
            val evalNegativa = Evaluacion("Test", -2.0) // Nota < 0.0
        } catch (e: IllegalArgumentException) {
            println("❌ Error capturado: ${e.message}")
        }
        
        // 8. Probar validaciones - Alumno con ID inválido
        println("\n3. Intentando crear alumno con ID inválido...")
        try {
            val alumnoInvalido = Alumno(-1, "Test", "Test")
        } catch (e: IllegalArgumentException) {
            println("❌ Error capturado: ${e.message}")
        }
        
        // 9. Demostrar encapsulamiento
        println("\n" + "=" * 50)
        println("🔒 DEMOSTRANDO ENCAPSULAMIENTO...")
        
        // No podemos acceder directamente a evaluaciones (es privada)
        // alumno1.evaluaciones // ❌ Esto daría error de compilación
        
        // Solo podemos acceder a través de métodos públicos
        val evaluacionesLectura = alumno1.obtenerEvaluaciones()
        println("Evaluaciones obtenidas (solo lectura): ${evaluacionesLectura.size} elementos")
        
        // La lista retornada es de solo lectura, no podemos modificarla
        // evaluacionesLectura.add(...) // ❌ Esto daría error
        
    } catch (e: Exception) {
        println("❌ Error no esperado: ${e.message}")
    }
}

// Función auxiliar para repetir strings
operator fun String.times(n: Int): String = this.repeat(n)*

**Viernes 29 de Agosto**
* En el dia de hoy avance con la issue declarando las clases Alumno.kt y Evaluación.kt en cree una funcion para valdiar alumnos mediante id y una forma de recibir el promedio de las evaluaciones, independientemente de eso estoy viendo como asignar los alumnos dentro del codigo para que todos tengan las mismas evaluaciones pero diferentes notas segun el alumno. Claude me dio una idea de como hacerlo pero no me queda muy claro de hacerlo de esa maner o de otra

charla con claude


