package org.example

/**
 * Clase responsable de inicializar el sistema con datos de ejemplo.
 * Esto permite que la función main tenga menos de 10 líneas.
 */
class InicializadorSistema {
    companion object {
        fun inicializar(sistema: SistemaGestionEscolar) {
            // Crear materias
            val idMatematicas = sistema.crearMateria("Matemáticas")
            val idHistoria = sistema.crearMateria("Historia")
            val idFisica = sistema.crearMateria("Física")
            val idTeleinformatica = sistema.crearMateria("Teleinformática")
            val idProgramacion = sistema.crearMateria("Programación II")

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
            sistema.cargarNota(idMati, idMatematicas,
                Evaluaciones("Trabajo Práctico integrador", 8.5))
            sistema.cargarNota(idCande, idHistoria,
                Evaluaciones("Examen escrito revolución francesa", 9.0))
            sistema.cargarNota(idIvo, idFisica,
                Evaluaciones("Examen MRUV", 9.5))
            sistema.cargarNota(idMaxi, idTeleinformatica,
                Evaluaciones("Evaluación redes WAN", 10.0))

            // Registrar profesores de ejemplo
            val idProf1 = sistema.registrarProfesor("Daniel", "Quintero", "Programación")
            val idProf2 = sistema.registrarProfesor("María", "González", "Matemáticas")

            // Asignar materias a profesores
            sistema.asignarMateriaAProfesor(idProf1, idProgramacion)
            sistema.asignarMateriaAProfesor(idProf2, idMatematicas)

            println("\n=== Sistema inicializado correctamente ===\n")
        }
    }
}