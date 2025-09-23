/*
 * Los objetos Alumno y Evaluaciones fueron creados en class y no data class porque class puede tener variables privadas  y puede validar datos como en el caso de evaluaciones que necesita que las notas de evaluaciones sean mayores de 1 al 10 por dar un ejemplo.
 */
package org.example
import Alumno
import Evaluaciones
fun main() {
    val sistema = SistemaGestionEscolar()
    
    // Crear materias
    val idMatematicas = sistema.crearMateria("Matemáticas")
    val idHistoria = sistema.crearMateria("Historia")
    
    // Registrar alumnos
    val idMati = sistema.registrarAlumno("Matias", "Blanco")
    val idCande = sistema.registrarAlumno("Candela", "Delacruz")
    
    // Inscribir alumnos en materias
    sistema.inscribirAlumnoEnMateria(idMati, idMatematicas)
    sistema.inscribirAlumnoEnMateria(idCande, idMatematicas)
    
    // Cargar notas
    val eval = Evaluaciones("Trabajo Practico", 8.5)
    sistema.cargarNota(idMati, idMatematicas, eval)
}