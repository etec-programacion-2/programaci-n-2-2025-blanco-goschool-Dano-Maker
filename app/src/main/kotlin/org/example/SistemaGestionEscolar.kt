class Sistema_G_Escolar {
    private val materias = mutablelistOf<Materia>()
    private val estudiantes = mutablelistOf<Alumno>()

    private var siguienteIdMateria = 1
    private var siguienteIdAlumno = 1

    fun crearMateria(nombre: String): Int {
    val nuevaMateria = Materia(siguienteIdMateria, nombre)
    materias.add(nuevaMateria)
    val id = siguienteIdMateria
    siguienteIdMateria++
    return id // Devuelve el ID para futuras referencias
    }

    fun inscribirAlumnoEnMateria(idAlumno: Int, idMateria: Int): Boolean {
    val alumno = buscarAlumnoPorId(idAlumno)
    val materia = buscarMateriaPorId(idMateria)
    
    return if (alumno != null && materia != null) {
        materia.inscribirAlumno(alumno)
        println("✓ ${alumno.nombre} ${alumno.apellido} inscrito en ${materia.nombre}")
        true
    } else {
        println("✗ Error: Alumno o materia no encontrados")
        false
    }
}

fun cargarNota(idAlumno: Int, idMateria: Int, evaluacion: Evaluaciones): Boolean {
    val alumno = buscarAlumnoPorId(idAlumno)
    val materia = buscarMateriaPorId(idMateria)
    
    return if (alumno != null && materia != null) {
        // Verificar que el alumno esté inscripto en la materia
        if (materia.obtenerAlumnosInscriptos().contains(alumno)) {
            alumno.agregarEvaluacion(evaluacion)
            println("✓ Nota cargada para ${alumno.nombre} en ${materia.nombre}")
            true
        } else {
            println("✗ El alumno no está inscripto en esta materia")
            false
        }
    } else {
        println("✗ Alumno o materia no encontrados")
        false
    }
}

private fun buscarAlumnoPorId(id: Int): Alumno? {
    return alumnos.find { it.id == id }
}

private fun buscarMateriaPorId(id: Int): Materia? {
    return materias.find { it.id == id }
}


}