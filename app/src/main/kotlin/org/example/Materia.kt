package org.example

class Materia(val id: Int, val nombre: String) {
    private val alumnosInscriptos = mutableListOf<Alumno>()

    fun inscribirAlumno(alumno: Alumno) {
        alumnosInscriptos.add(alumno)
    }

    fun obtenerAlumnosInscriptos(): List<Alumno> = alumnosInscriptos.toList()
}
