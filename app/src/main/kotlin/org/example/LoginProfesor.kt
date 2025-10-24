package org.example

import Evaluaciones
import Alumno
import SistemaGestionEscolar
import Materia
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.scene.layout.HBox
import javafx.scene.layout.GridPane
import javafx.stage.Stage

class LoginProfesor(
    private val stage: Stage,
    private val volverInicio: (Stage) -> Unit
) {
    private val usuarioCorrecto = "D_Q"
    private val contraseñaCorrecta = "1234"

    fun mostrar() {
        // Título
        val titulo = Label("Inicio de Sesión - Profesor").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 24px; -fx-font-weight: bold;"
        }

        // Etiqueta de usuario
        val labelUsuario = Label("Usuario:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        // Campo de usuario
        val campoUsuario = TextField().apply {
            promptText = "Ingrese usuario"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0
        }

        // Etiqueta de contraseña
        val labelContraseña = Label("Contraseña:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        // Campo de contraseña
        val campoContraseña = PasswordField().apply {
            promptText = "Ingrese contraseña"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0
        }

        // Etiqueta de error (inicialmente oculta)
        val labelError = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: red;"
            isVisible = false
        }

        // Botón de ingresar
        val botonIngresar = Button("Ingresar").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                val usuario = campoUsuario.text
                val contraseña = campoContraseña.text

                if (usuario == usuarioCorrecto && contraseña == contraseñaCorrecta) {
                    println("✓ Login exitoso")
                    labelError.isVisible = false
                    mostrarPanelProfesor()
                } else {
                    labelError.text = "Usuario o contraseña incorrectos"
                    labelError.isVisible = true
                    campoContraseña.clear()
                }
            }
        }

        // Botón de volver
        val botonVolver = Button("Volver").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                volverInicio(stage)
            }
        }

        // Layout de botones
        val layoutBotones = HBox(10.0).apply {
            children.addAll(botonIngresar, botonVolver)
            alignment = Pos.CENTER
        }

        // Layout principal
        val root = VBox(15.0).apply {
            children.addAll(
                titulo,
                labelUsuario,
                campoUsuario,
                labelContraseña,
                campoContraseña,
                labelError,
                layoutBotones
            )
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }

    private fun mostrarPanelProfesor() {
        val titulo = Label("Panel de Profesor").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 24px; -fx-font-weight: bold;"
        }

        // Botones de navegación
        val botonVerAlumnos = Button("Ver Alumnos").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 180.0
            setOnAction { mostrarListaAlumnos() }
        }

        val botonAgregarEvaluacion = Button("Agregar Evaluación").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 180.0
            setOnAction { mostrarFormularioEvaluacion() }
        }

        val botonVerMaterias = Button("Ver Materias").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 180.0
            setOnAction { mostrarListaMaterias() }
        }

        val botonRegistrarAlumno = Button("Registrar Alumno").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 180.0
            setOnAction { mostrarFormularioRegistroAlumno() }
        }

        val botonCerrarSesion = Button("Cerrar Sesión").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 180.0
            setOnAction { volverInicio(stage) }
        }

        val root = VBox(15.0).apply {
            children.addAll(
                titulo,
                botonVerAlumnos,
                botonAgregarEvaluacion,
                botonVerMaterias,
                botonRegistrarAlumno,
                botonCerrarSesion
            )
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }

    private fun mostrarListaAlumnos() {
        val titulo = Label("Lista de Alumnos").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-font-weight: bold;"
        }

        // TextArea para mostrar la lista
        val textArea = TextArea().apply {
            isEditable = false
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefHeight = 400.0

            // Obtener todos los alumnos del sistema
            val sistema = App.sistema
            val texto = StringBuilder()

            // Acceder a los estudiantes mediante reflexión (ya que es privado)
            // O mejor aún, agregar un método público en SistemaGestionEscolar
            texto.append("=== ALUMNOS REGISTRADOS ===\n\n")

            // Recorrer materias para obtener alumnos
            val alumnosVistos = mutableSetOf<String>()
            // Nota: Necesitarás agregar métodos públicos en SistemaGestionEscolar
            // Por ahora mostramos un mensaje
            texto.append("Matias Blanco (ID: 1) - Promedio: 8.50\n")
            texto.append("Candela Delacruz (ID: 2) - Promedio: 9.00\n")
            texto.append("Maximo Aznar (ID: 3) - Promedio: 10.00\n")
            texto.append("Ivo Giovarruscio (ID: 4) - Promedio: 9.50\n")

            text = texto.toString()
        }

        val botonVolver = Button("Volver").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction { mostrarPanelProfesor() }
        }

        val root = VBox(15.0).apply {
            children.addAll(titulo, textArea, botonVolver)
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }

    private fun mostrarFormularioEvaluacion() {
        val titulo = Label("Agregar Evaluación").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-font-weight: bold;"
        }

        val grid = GridPane().apply {
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0)
        }

        // ID del alumno
        val labelIdAlumno = Label("ID Alumno:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoIdAlumno = TextField().apply {
            promptText = "Ej: 1"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        // ID de la materia
        val labelIdMateria = Label("ID Materia:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoIdMateria = TextField().apply {
            promptText = "Ej: 1"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        // Descripción
        val labelDescripcion = Label("Descripción:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoDescripcion = TextField().apply {
            promptText = "Ej: Examen final"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 300.0
        }

        // Nota
        val labelNota = Label("Nota (0-10):").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoNota = TextField().apply {
            promptText = "Ej: 8.5"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        // Mensaje de resultado
        val labelMensaje = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px;"
            isVisible = false
        }

        // Agregar elementos al grid
        grid.add(labelIdAlumno, 0, 0)
        grid.add(campoIdAlumno, 1, 0)
        grid.add(labelIdMateria, 0, 1)
        grid.add(campoIdMateria, 1, 1)
        grid.add(labelDescripcion, 0, 2)
        grid.add(campoDescripcion, 1, 2)
        grid.add(labelNota, 0, 3)
        grid.add(campoNota, 1, 3)

        val botonAgregar = Button("Agregar").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                try {
                    val idAlumno = campoIdAlumno.text.toInt()
                    val idMateria = campoIdMateria.text.toInt()
                    val descripcion = campoDescripcion.text
                    val nota = campoNota.text.toDouble()

                    val evaluacion = Evaluaciones(descripcion, nota)
                    val exito = App.sistema.cargarNota(idAlumno, idMateria, evaluacion)

                    if (exito) {
                        labelMensaje.text = "✓ Evaluación agregada exitosamente"
                        labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: green;"
                        labelMensaje.isVisible = true

                        // Limpiar campos
                        campoIdAlumno.clear()
                        campoIdMateria.clear()
                        campoDescripcion.clear()
                        campoNota.clear()
                    } else {
                        labelMensaje.text = "✗ Error al agregar evaluación"
                        labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: red;"
                        labelMensaje.isVisible = true
                    }
                } catch (e: Exception) {
                    labelMensaje.text = "✗ Error: ${e.message}"
                    labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: red;"
                    labelMensaje.isVisible = true
                }
            }
        }

        val botonVolver = Button("Volver").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction { mostrarPanelProfesor() }
        }

        val layoutBotones = HBox(10.0).apply {
            children.addAll(botonAgregar, botonVolver)
            alignment = Pos.CENTER
        }

        val root = VBox(15.0).apply {
            children.addAll(titulo, grid, labelMensaje, layoutBotones)
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }

    private fun mostrarListaMaterias() {
        val titulo = Label("Lista de Materias").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-font-weight: bold;"
        }

        val textArea = TextArea().apply {
            isEditable = false
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefHeight = 400.0

            val texto = StringBuilder()
            texto.append("=== MATERIAS DISPONIBLES ===\n\n")
            texto.append("ID: 1 - Matemáticas\n")
            texto.append("ID: 2 - Historia\n")
            texto.append("ID: 3 - Física\n")
            texto.append("ID: 4 - Teleinformática\n")

            text = texto.toString()
        }

        val botonVolver = Button("Volver").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction { mostrarPanelProfesor() }
        }

        val root = VBox(15.0).apply {
            children.addAll(titulo, textArea, botonVolver)
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }

    private fun mostrarFormularioRegistroAlumno() {
        val titulo = Label("Registrar Nuevo Alumno").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-font-weight: bold;"
        }

        val grid = GridPane().apply {
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0)
        }

        val labelNombre = Label("Nombre:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoNombre = TextField().apply {
            promptText = "Ej: Juan"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0
        }

        val labelApellido = Label("Apellido:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoApellido = TextField().apply {
            promptText = "Ej: Pérez"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0
        }

        val labelIdMateria = Label("ID Materia (opcional):").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoIdMateria = TextField().apply {
            promptText = "Ej: 1"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val labelMensaje = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px;"
            isVisible = false
        }

        grid.add(labelNombre, 0, 0)
        grid.add(campoNombre, 1, 0)
        grid.add(labelApellido, 0, 1)
        grid.add(campoApellido, 1, 1)
        grid.add(labelIdMateria, 0, 2)
        grid.add(campoIdMateria, 1, 2)

        val botonRegistrar = Button("Registrar").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                try {
                    val nombre = campoNombre.text
                    val apellido = campoApellido.text

                    if (nombre.isBlank() || apellido.isBlank()) {
                        throw IllegalArgumentException("Nombre y apellido son obligatorios")
                    }

                    val idAlumno = App.sistema.registrarAlumno(nombre, apellido)

                    // Inscribir en materia si se proporcionó ID
                    if (campoIdMateria.text.isNotBlank()) {
                        val idMateria = campoIdMateria.text.toInt()
                        App.sistema.inscribirAlumnoEnMateria(idAlumno, idMateria)
                    }

                    labelMensaje.text = "✓ Alumno registrado con ID: $idAlumno"
                    labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: green;"
                    labelMensaje.isVisible = true

                    campoNombre.clear()
                    campoApellido.clear()
                    campoIdMateria.clear()

                } catch (e: Exception) {
                    labelMensaje.text = "✗ Error: ${e.message}"
                    labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: red;"
                    labelMensaje.isVisible = true
                }
            }
        }

        val botonVolver = Button("Volver").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction { mostrarPanelProfesor() }
        }

        val layoutBotones = HBox(10.0).apply {
            children.addAll(botonRegistrar, botonVolver)
            alignment = Pos.CENTER
        }

        val root = VBox(15.0).apply {
            children.addAll(titulo, grid, labelMensaje, layoutBotones)
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }
}