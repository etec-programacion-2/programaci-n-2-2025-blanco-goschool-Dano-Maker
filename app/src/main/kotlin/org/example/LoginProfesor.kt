package org.example

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
        val titulo = Label("Inicio de Sesión - Profesor").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 24px; -fx-font-weight: bold;"
        }

        val labelUsuario = Label("Usuario:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val campoUsuario = TextField().apply {
            promptText = "Ingrese usuario"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0
        }

        val labelContraseña = Label("Contraseña:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val campoContraseña = PasswordField().apply {
            promptText = "Ingrese contraseña"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0
        }

        val labelError = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: red;"
            isVisible = false
        }

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

        val botonVolver = Button("Volver").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                volverInicio(stage)
            }
        }

        val layoutBotones = HBox(10.0).apply {
            children.addAll(botonIngresar, botonVolver)
            alignment = Pos.CENTER
        }

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

        val botonCrearMateria = Button("Crear Materia").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 180.0
            setOnAction { mostrarFormularioCrearMateria() }
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
                botonCrearMateria,
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

        val textArea = TextArea().apply {
            isEditable = false
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefHeight = 400.0

            val sistema = App.sistema
            val alumnos = sistema.obtenerTodosLosAlumnos()
            val texto = StringBuilder()

            texto.append("=== ALUMNOS REGISTRADOS ===\n\n")

            if (alumnos.isEmpty()) {
                texto.append("No hay alumnos registrados.\n")
            } else {
                alumnos.forEach { alumno ->
                    texto.append("ID: ${alumno.id}\n")
                    texto.append("Nombre: ${alumno.nombre} ${alumno.apellido}\n")
                    texto.append("Promedio: ${String.format("%.2f", alumno.calcularPromedio())}\n")
                    texto.append("Evaluaciones: ${alumno.cantidadEvaluaciones()}\n")

                    if (alumno.cantidadEvaluaciones() > 0) {
                        texto.append("Notas:\n")
                        alumno.obtenerEvaluaciones().forEach { eval ->
                            texto.append("  - ${eval.descripcion}: ${eval.nota}\n")
                        }
                    }
                    texto.append("\n" + "=".repeat(40) + "\n\n")
                }
            }

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

        // ComboBox para seleccionar alumno
        val labelAlumno = Label("Seleccionar Alumno:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val comboAlumnos = ComboBox<String>().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 300.0

            val alumnos = App.sistema.obtenerTodosLosAlumnos()
            items.addAll(alumnos.map { "ID: ${it.id} - ${it.nombre} ${it.apellido}" })

            if (items.isNotEmpty()) {
                selectionModel.selectFirst()
            }
        }

        // ComboBox para seleccionar materia
        val labelMateria = Label("Seleccionar Materia:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val comboMaterias = ComboBox<String>().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 300.0

            val materias = App.sistema.obtenerTodasLasMaterias()
            items.addAll(materias.map { "ID: ${it.id} - ${it.nombre}" })

            if (items.isNotEmpty()) {
                selectionModel.selectFirst()
            }
        }

        val labelDescripcion = Label("Descripción:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoDescripcion = TextField().apply {
            promptText = "Ej: Examen final"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 300.0
        }

        val labelNota = Label("Nota (0-10):").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }
        val campoNota = TextField().apply {
            promptText = "Ej: 8.5"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val labelMensaje = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px;"
            isVisible = false  // Esta está bien
            isWrapText = true  // ✅ Cambio: wrapText → isWrapText
            maxWidth = 350.0
        }

        grid.add(labelAlumno, 0, 0)
        grid.add(comboAlumnos, 1, 0)
        grid.add(labelMateria, 0, 1)
        grid.add(comboMaterias, 1, 1)
        grid.add(labelDescripcion, 0, 2)
        grid.add(campoDescripcion, 1, 2)
        grid.add(labelNota, 0, 3)
        grid.add(campoNota, 1, 3)

        val botonAgregar = Button("Agregar").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                try {
                    val alumnoSeleccionado = comboAlumnos.value
                    val materiaSeleccionada = comboMaterias.value

                    if (alumnoSeleccionado == null || materiaSeleccionada == null) {
                        throw IllegalArgumentException("Debe seleccionar alumno y materia")
                    }

                    // Extraer IDs de los strings
                    val idAlumno = alumnoSeleccionado.substringAfter("ID: ").substringBefore(" -").toInt()
                    val idMateria = materiaSeleccionada.substringAfter("ID: ").substringBefore(" -").toInt()
                    val descripcion = campoDescripcion.text
                    val nota = campoNota.text.toDouble()

                    val evaluacion = Evaluaciones(descripcion, nota)
                    val exito = App.sistema.cargarNota(idAlumno, idMateria, evaluacion)

                    if (exito) {
                        labelMensaje.text = "✓ Evaluación agregada exitosamente"
                        labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: green;"
                        labelMensaje.isVisible = true

                        campoDescripcion.clear()
                        campoNota.clear()
                    } else {
                        labelMensaje.text = "✗ Error: El alumno no está inscrito en esta materia"
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

            val sistema = App.sistema
            val materias = sistema.obtenerTodasLasMaterias()
            val texto = StringBuilder()

            texto.append("=== MATERIAS DISPONIBLES ===\n\n")

            if (materias.isEmpty()) {
                texto.append("No hay materias registradas.\n")
            } else {
                materias.forEach { materia ->
                    texto.append("ID: ${materia.id} - ${materia.nombre}\n")
                    texto.append("Alumnos inscriptos: ${materia.obtenerAlumnosInscriptos().size}\n")

                    if (materia.obtenerAlumnosInscriptos().isNotEmpty()) {
                        texto.append("Lista de alumnos:\n")
                        materia.obtenerAlumnosInscriptos().forEach { alumno ->
                            texto.append("  - ${alumno.nombre} ${alumno.apellido}\n")
                        }
                    }
                    texto.append("\n" + "=".repeat(40) + "\n\n")
                }
            }

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

        val labelMateria = Label("Materia (opcional):").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val comboMaterias = ComboBox<String>().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 250.0

            items.add("--- Sin materia ---")
            val materias = App.sistema.obtenerTodasLasMaterias()
            items.addAll(materias.map { "ID: ${it.id} - ${it.nombre}" })

            selectionModel.selectFirst()
        }

        val labelMensaje = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px;"
            isVisible = false  // Esta está bien
            isWrapText = true  // ✅ Cambio: wrapText → isWrapText
            maxWidth = 350.0
        }
        grid.add(labelNombre, 0, 0)
        grid.add(campoNombre, 1, 0)
        grid.add(labelApellido, 0, 1)
        grid.add(campoApellido, 1, 1)
        grid.add(labelMateria, 0, 2)
        grid.add(comboMaterias, 1, 2)

        val botonRegistrar = Button("Registrar").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                try {
                    val nombre = campoNombre.text.trim()
                    val apellido = campoApellido.text.trim()

                    if (nombre.isBlank() || apellido.isBlank()) {
                        throw IllegalArgumentException("Nombre y apellido son obligatorios")
                    }

                    val idAlumno = App.sistema.registrarAlumno(nombre, apellido)

                    // Inscribir en materia si se seleccionó una
                    val materiaSeleccionada = comboMaterias.value
                    if (materiaSeleccionada != null && !materiaSeleccionada.startsWith("---")) {
                        val idMateria = materiaSeleccionada.substringAfter("ID: ").substringBefore(" -").toInt()
                        App.sistema.inscribirAlumnoEnMateria(idAlumno, idMateria)

                        val materia = App.sistema.obtenerMateriaPorId(idMateria)
                        labelMensaje.text = "✓ Alumno registrado con ID: $idAlumno e inscrito en ${materia?.nombre}"
                    } else {
                        labelMensaje.text = "✓ Alumno registrado con ID: $idAlumno"
                    }

                    labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: green;"
                    labelMensaje.isVisible = true

                    campoNombre.clear()
                    campoApellido.clear()
                    comboMaterias.selectionModel.selectFirst()

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

    private fun mostrarFormularioCrearMateria() {
        val titulo = Label("Crear Nueva Materia").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-font-weight: bold;"
        }

        val labelNombre = Label("Nombre de la Materia:").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val campoNombre = TextField().apply {
            promptText = "Ej: Química"
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 300.0
        }

        val labelMensaje = Label().apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px;"
            isVisible = false
        }

        val botonCrear = Button("Crear").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 120.0
            setOnAction {
                try {
                    val nombre = campoNombre.text.trim()

                    if (nombre.isBlank()) {
                        throw IllegalArgumentException("El nombre de la materia no puede estar vacío")
                    }

                    val idMateria = App.sistema.crearMateria(nombre)

                    labelMensaje.text = "✓ Materia creada con ID: $idMateria"
                    labelMensaje.style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: green;"
                    labelMensaje.isVisible = true

                    campoNombre.clear()

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
            children.addAll(botonCrear, botonVolver)
            alignment = Pos.CENTER
        }

        val root = VBox(20.0).apply {
            children.addAll(titulo, labelNombre, campoNombre, labelMensaje, layoutBotones)
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        val scene = Scene(root, 800.0, 600.0)
        stage.scene = scene
    }
}