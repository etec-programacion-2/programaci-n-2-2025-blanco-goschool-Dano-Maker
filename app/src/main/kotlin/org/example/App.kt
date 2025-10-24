package org.example
import Evaluaciones
import Alumno
import SistemaGestionEscolar
import Materia
import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage

class App : Application() {
    companion object {
        val sistema = SistemaGestionEscolar()
    }

    override fun start(stage: Stage) {
        mostrarPantallaInicio(stage)
    }

    private fun mostrarPantallaInicio(stage: Stage) {
        // Crear las etiquetas con fuente Times New Roman
        val label = Label("Bienvenido a...").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val label_2 = Label("GRADEQL").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 32px; -fx-font-weight: bold;"
        }

        // Crear los botones
        val botonProfesor = Button("Entrar como profesor").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 200.0
            setOnAction {
                mostrarLoginProfesor(stage) // ✅ Navegar al login
            }
        }

        val botonEstudiante = Button("Entrar como estudiante").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 200.0
            setOnAction {
                println("Acceso como estudiante...")
                // Aquí puedes abrir una nueva ventana para estudiantes
            }
        }

        // Configurar el layout con todos los elementos
        val root = VBox().apply {
            children.addAll(label, label_2, botonProfesor, botonEstudiante)
            alignment = Pos.CENTER
            spacing = 15.0
            style = "-fx-padding: 20;"
        }

        // Crear la escena
        val scene = Scene(root, 800.0, 600.0)

        // Configurar la ventana
        stage.title = "Sistema escolar - GRADEQL"
        stage.scene = scene
        stage.isResizable = false
        stage.show()
    }

    private fun mostrarLoginProfesor(stage: Stage) {
        val loginScreen = LoginProfesor(stage, ::mostrarPantallaInicio)
        loginScreen.mostrar()
    }
}

fun main(args: Array<String>) {
    // Inicializar el sistema con datos de ejemplo
    val sistema = App.sistema

    // Crear materias
    val idMatematicas = sistema.crearMateria("Matemáticas")
    val idHistoria = sistema.crearMateria("Historia")
    val idFisica = sistema.crearMateria("Física")
    val idTeleinformatica = sistema.crearMateria("Teleinformática")

    // Registrar alumnos
    val idMati = sistema.registrarAlumno("Matias", "Blanco")
    val idCande = sistema.registrarAlumno("Candela", "Delacruz")
    val idMaxi = sistema.registrarAlumno("Maximo", "Aznar")
    val idIvo = sistema.registrarAlumno("Ivo", "Giovarruscio")

    // Inscribir alumnos
    sistema.inscribirAlumnoEnMateria(idMati, idMatematicas)
    sistema.inscribirAlumnoEnMateria(idCande, idHistoria)
    sistema.inscribirAlumnoEnMateria(idMaxi, idTeleinformatica)
    sistema.inscribirAlumnoEnMateria(idIvo, idFisica)

    // Cargar notas
    sistema.cargarNota(idMati, idMatematicas, Evaluaciones("Trabajo Práctico integrador", 8.5))
    sistema.cargarNota(idCande, idHistoria, Evaluaciones("Examen escrito revolución francesa", 9.0))
    sistema.cargarNota(idIvo, idFisica, Evaluaciones("Examen MRUV", 9.5))
    sistema.cargarNota(idMaxi, idTeleinformatica, Evaluaciones("Evaluación redes WAN", 10.0))

    println("\n=== Sistema inicializado correctamente ===\n")

    // Lanzar la aplicación JavaFX
    Application.launch(App::class.java, *args)
}



//Hola profe, no se porque no me deja pushear, asique aca hay un commit para que me deje
