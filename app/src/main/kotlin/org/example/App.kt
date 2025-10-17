/*
 * Los objetos Alumno y Evaluaciones fueron creados en class y no data class porque class puede tener variables privadas  y puede validar datos como en el caso de evaluaciones que necesita que las notas de evaluaciones sean mayores de 1 al 10 por dar un ejemplo.
 */
package org.example
import Alumno
import Evaluaciones
import SistemaGestionEscolar
fun main() {
    val sistema = SistemaGestionEscolar()
    
    // Crear materias
    val idMatematicas = sistema.crearMateria("Matemáticas")
    val idHistoria = sistema.crearMateria("Historia")
    val idFisica = sistema.crearMateria("Fisica")
    val idTeleinformatica = sistema.crearMateria("Teleinformatica")
    
    // Registrar alumnos
    val idMati = sistema.registrarAlumno("Matias", "Blanco")
    val idCande = sistema.registrarAlumno("Candela", "Delacruz")
    val idMaxi = sistema.registrarAlumno("Maximo", "Aznar")
    val idIvo = sistema.registrarAlumno("Ivo", "Giovarruscio")
    
    // Inscribir alumnos en materias
    sistema.inscribirAlumnoEnMateria(idMati, idMatematicas)
    sistema.inscribirAlumnoEnMateria(idCande, idHistoria)
    sistema.inscribirAlumnoEnMateria(idMaxi, idTeleinformatica)
    sistema.inscribirAlumnoEnMateria(idIvo, idFisica)
    
    // Cargar notas
    val eval_M = Evaluaciones("Trabajo Practico integrador", 8.5)
    sistema.cargarNota(idMati, idMatematicas, eval_M)
    val eval_H = Evaluaciones ("Examen escrito revolución francesa",9.0)
    sistema.cargarNota(idCande, idHistoria, eval_H)
    val eval_F = Evaluaciones("Examen MRUV", 9.5)
    sistema.cargarNota(idIvo, idFisica, eval_F)
    val eval_T = Evaluaciones("Evaluación redes WAN", 10.0)
    sistema.cargarNota(idMaxi, idTeleinformatica, eval_T)

}