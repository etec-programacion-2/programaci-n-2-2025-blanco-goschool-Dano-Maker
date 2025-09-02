class Materia(val id: Int, val nombre: String) {
    // Propiedad privada que almacena los alumnos inscriptos
    private val alumnosInscriptos = mutableListOf<Alumno>()
    // Método público para inscribir un alumno
    fun inscribirAlumno(alumno: Alumno) {
        alumnosInscriptos.add(alumno)
    }
    // Método público que devuelve una copia de solo lectura de la lista de alumnos inscriptos
    fun obtenerAlumnosInscriptos(): List<Alumno> {
        return alumnosInscriptos.toList() // Devuelve una copia inmutable
    }
}