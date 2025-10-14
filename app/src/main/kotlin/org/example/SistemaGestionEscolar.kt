class SistemaGestionEscolar {
    private val materias = mutableListOf<Materia>()
    private val estudiantes = mutableListOf<Alumno>()

    private var siguienteIdMateria = 1
    private var siguienteIdAlumno = 1

    fun crearMateria(nombre: String): Int {
        val nuevaMateria = Materia(siguienteIdMateria, nombre)
        materias.add(nuevaMateria)
        val id = siguienteIdMateria
        siguienteIdMateria++
        return id
    }

    fun registrarAlumno(nombre: String, apellido: String): Int {
        val nuevoAlumno = Alumno(nombre, apellido, siguienteIdAlumno)
        estudiantes.add(nuevoAlumno)
        val id = siguienteIdAlumno
        siguienteIdAlumno++
        return id
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
        return estudiantes.find { it.id == id }
    }

    private fun buscarMateriaPorId(id: Int): Materia? {
        return materias.find { it.id == id }
    }
}
