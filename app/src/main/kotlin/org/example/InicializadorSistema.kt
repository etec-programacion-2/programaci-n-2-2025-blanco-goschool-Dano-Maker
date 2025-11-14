package org.example

// Inicializa el sistema con datos de ejemplo para mantener main() simple
class InicializadorSistema {
    companion object {
        fun inicializar(sistema: SistemaGestionEscolar) {
            val idMatematicas = sistema.crearMateria("Matemáticas")
            val idHistoria = sistema.crearMateria("Historia")
            val idFisica = sistema.crearMateria("Física")
            val idTeleinformatica = sistema.crearMateria("Teleinformática")
            val idProgramacion = sistema.crearMateria("Programación II")

            val idMati = sistema.registrarAlumno("Matias", "Blanco")
            val idCande = sistema.registrarAlumno("Candela", "Delacruz")
            val idMaxi = sistema.registrarAlumno("Maximo", "Aznar")
            val idIvo = sistema.registrarAlumno("Ivo", "Giovarruscio")

            sistema.inscribirAlumnoEnMateria(idMati, idMatematicas)
            sistema.inscribirAlumnoEnMateria(idCande, idHistoria)
            sistema.inscribirAlumnoEnMateria(idMaxi, idTeleinformatica)
            sistema.inscribirAlumnoEnMateria(idIvo, idFisica)

            sistema.cargarNota(idMati, idMatematicas, Evaluaciones("Trabajo Práctico integrador", 8.5))
            sistema.cargarNota(idCande, idHistoria, Evaluaciones("Examen escrito revolución francesa", 9.0))
            sistema.cargarNota(idIvo, idFisica, Evaluaciones("Examen MRUV", 9.5))
            sistema.cargarNota(idMaxi, idTeleinformatica, Evaluaciones("Evaluación redes WAN", 10.0))

            val idProf1 = sistema.registrarProfesor("Daniel", "Quintero", "Programación")
            val idProf2 = sistema.registrarProfesor("María", "González", "Matemáticas")

            sistema.asignarMateriaAProfesor(idProf1, idProgramacion)
            sistema.asignarMateriaAProfesor(idProf2, idMatematicas)

            println("\n=== Sistema inicializado correctamente ===\n")
        }
    }
}