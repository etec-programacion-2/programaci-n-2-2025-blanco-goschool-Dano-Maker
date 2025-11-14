package org.example

/**
 * Sistema central de gestión escolar.
 * Gestiona alumnos, profesores y materias.
 */
class SistemaGestionEscolar {
    private val materias = mutableListOf<Materia>()
    private val estudiantes = mutableListOf<Alumno>()
    private val profesores = mutableListOf<Profesor>()

    private var siguienteIdMateria = 1
    private var siguienteIdAlumno = 1
    private var siguienteIdProfesor = 1

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

    fun registrarProfesor(nombre: String, apellido: String, especialidad: String): Int {
        val nuevoProfesor = Profesor(nombre, apellido, siguienteIdProfesor, especialidad)
        profesores.add(nuevoProfesor)
        val id = siguienteIdProfesor
        siguienteIdProfesor++
        return id
    }

    fun asignarMateriaAProfesor(idProfesor: Int, idMateria: Int): Boolean {
        val profesor = buscarProfesorPorId(idProfesor)
        val materia = buscarMateriaPorId(idMateria)

        return if (profesor != null && materia != null) {
            profesor.asignarMateria(materia)
            true
        } else {
            println("✗ Error: Profesor o materia no encontrados")
            false
        }
    }

    fun inscribirAlumnoEnMateria(idAlumno: Int, idMateria: Int): Boolean {
        val alumno = buscarAlumnoPorId(idAlumno)
        val materia = buscarMateriaPorId(idMateria)

        return if (alumno != null && materia != null) {
            materia.inscribirAlumno(alumno)
            println("✓ ${alumno.obtenerNombreCompleto()} inscrito en ${materia.nombre}")
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
                println("✓ Nota cargada para ${alumno.obtenerNombreCompleto()} en ${materia.nombre}")
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

    /**
     * Demuestra polimorfismo - recibe cualquier Evaluable
     */
    fun mostrarPromedioEntidad(entidad: Evaluable, nombre: String) {
        println("$nombre - Promedio: ${String.format("%.2f", entidad.calcularPromedio())}")
        println("Evaluaciones: ${entidad.cantidadEvaluaciones()}")
    }

    /**
     * Demuestra polimorfismo - recibe cualquier Persona
     */
    fun mostrarInformacionPersona(persona: Persona) {
        persona.mostrarInformacion()
    }

    private fun buscarAlumnoPorId(id: Int): Alumno? {
        return estudiantes.find { it.id == id }
    }

    private fun buscarMateriaPorId(id: Int): Materia? {
        return materias.find { it.id == id }
    }

    private fun buscarProfesorPorId(id: Int): Profesor? {
        return profesores.find { it.id == id }
    }

    // Métodos públicos para obtener datos
    fun obtenerTodosLosAlumnos(): List<Alumno> = estudiantes.toList()
    fun obtenerTodasLasMaterias(): List<Materia> = materias.toList()
    fun obtenerTodosLosProfesores(): List<Profesor> = profesores.toList()
    fun obtenerAlumnoPorId(id: Int): Alumno? = buscarAlumnoPorId(id)
    fun obtenerMateriaPorId(id: Int): Materia? = buscarMateriaPorId(id)
    fun obtenerProfesorPorId(id: Int): Profesor? = buscarProfesorPorId(id)
}