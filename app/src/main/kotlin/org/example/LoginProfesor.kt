package org.example
import javafx.stage.Stage
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.scene.layout.HBox
import javafx.scene.layout.GridPane

class LoginProfesor(
    private val stage: Stage,
    private val volverInicio: (Stage) -> Unit
) {
    private val usuarioCorrecto = "D_Q"
    private val contraseñaCorrecta = "1234"

    // Estilos centralizados
    private val estiloTitulo = "-fx-font-family: 'Times New Roman'; -fx-font-size: 24px; -fx-font-weight: bold;"
    private val estiloSubtitulo = "-fx-font-family: 'Times New Roman'; -fx-font-size: 20px; -fx-font-weight: bold;"
    private val estiloTexto = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
    private val estiloError = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: red;"
    private val estiloExito = "-fx-font-family: 'Times New Roman'; -fx-font-size: 12px; -fx-text-fill: green;"

    fun mostrar() {
        val campoUsuario = crearCampoTexto("Ingrese usuario")
        val campoContraseña = crearCampoContraseña("Ingrese contraseña")
        val labelError = crearLabel("", estiloError, false)

        val botonIngresar = crearBoton("Ingresar") {
            validarLogin(campoUsuario.text, campoContraseña.text, labelError)
        }

        val root = VBox(15.0).apply {
            children.addAll(
                crearLabel("Inicio de Sesión - Profesor", estiloTitulo),
                crearLabel("Usuario:", estiloTexto),
                campoUsuario,
                crearLabel("Contraseña:", estiloTexto),
                campoContraseña,
                labelError,
                crearLayoutBotones(botonIngresar, crearBoton("Volver") { volverInicio(stage) })
            )
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        stage.scene = Scene(root, 800.0, 600.0)
    }

    // Valida las credenciales y redirige según el resultado
    private fun validarLogin(usuario: String, contraseña: String, labelError: Label) {
        if (usuario == usuarioCorrecto && contraseña == contraseñaCorrecta) {
            labelError.isVisible = false
            mostrarPanelProfesor()
        } else {
            labelError.text = "Usuario o contraseña incorrectos"
            labelError.isVisible = true
        }
    }

    private fun mostrarPanelProfesor() {
        val botones = listOf(
            "Ver Alumnos" to ::mostrarListaAlumnos,
            "Agregar Evaluación" to ::mostrarFormularioEvaluacion,
            "Ver Materias" to ::mostrarListaMaterias,
            "Registrar Alumno" to ::mostrarFormularioRegistroAlumno,
            "Crear Materia" to ::mostrarFormularioCrearMateria,
            "Cerrar Sesión" to { volverInicio(stage) }
        )

        val root = VBox(15.0).apply {
            children.add(crearLabel("Panel de Profesor", estiloTitulo))
            children.addAll(botones.map { (texto, accion) -> crearBoton(texto, accion) })
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        stage.scene = Scene(root, 800.0, 600.0)
    }

    private fun mostrarListaAlumnos() {
        val textArea = crearTextArea().apply {
            val alumnos = App.sistema.obtenerTodosLosAlumnos()
            text = generarTextoAlumnos(alumnos)
        }

        mostrarPantallaConTexto("Lista de Alumnos", textArea, ::mostrarPanelProfesor)
    }

    // Genera el texto formateado de la lista de alumnos
    private fun generarTextoAlumnos(alumnos: List<Alumno>): String {
        val texto = StringBuilder("=== ALUMNOS REGISTRADOS ===\n\n")

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
                texto.append("\n${"=".repeat(40)}\n\n")
            }
        }
        return texto.toString()
    }

    private fun mostrarFormularioEvaluacion() {
        val grid = GridPane().apply {
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0)
        }

        val comboAlumnos = crearComboAlumnos()
        val comboMaterias = crearComboMaterias()
        val campoDescripcion = crearCampoTexto("Ej: Examen final")
        val campoNota = crearCampoTexto("Ej: 8.5")
        val labelMensaje = crearLabel("", estiloTexto, false).apply {
            isWrapText = true
            maxWidth = 350.0
        }

        grid.add(crearLabel("Seleccionar Alumno:", estiloTexto), 0, 0)
        grid.add(comboAlumnos, 1, 0)
        grid.add(crearLabel("Seleccionar Materia:", estiloTexto), 0, 1)
        grid.add(comboMaterias, 1, 1)
        grid.add(crearLabel("Descripción:", estiloTexto), 0, 2)
        grid.add(campoDescripcion, 1, 2)
        grid.add(crearLabel("Nota (0-10):", estiloTexto), 0, 3)
        grid.add(campoNota, 1, 3)

        val botonAgregar = crearBoton("Agregar") {
            procesarEvaluacion(comboAlumnos, comboMaterias, campoDescripcion, campoNota, labelMensaje)
        }

        val root = VBox(15.0).apply {
            children.addAll(
                crearLabel("Agregar Evaluación", estiloSubtitulo),
                grid,
                labelMensaje,
                crearLayoutBotones(botonAgregar, crearBoton("Volver", ::mostrarPanelProfesor))
            )
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        stage.scene = Scene(root, 800.0, 600.0)
    }

    // Procesa y agrega una nueva evaluación al sistema
    private fun procesarEvaluacion(
        comboAlumnos: ComboBox<String>,
        comboMaterias: ComboBox<String>,
        campoDescripcion: TextField,
        campoNota: TextField,
        labelMensaje: Label
    ) {
        try {
            val idAlumno = extraerIdDeCombo(comboAlumnos.value)
            val idMateria = extraerIdDeCombo(comboMaterias.value)
            val evaluacion = Evaluaciones(campoDescripcion.text, campoNota.text.toDouble())

            if (App.sistema.cargarNota(idAlumno, idMateria, evaluacion)) {
                labelMensaje.text = "✓ Evaluación agregada exitosamente"
                labelMensaje.style = estiloExito
                campoDescripcion.clear()
                campoNota.clear()
            } else {
                labelMensaje.text = "✗ Error: El alumno no está inscrito en esta materia"
                labelMensaje.style = estiloError
            }
            labelMensaje.isVisible = true
        } catch (e: Exception) {
            labelMensaje.text = "✗ Error: ${e.message}"
            labelMensaje.style = estiloError
            labelMensaje.isVisible = true
        }
    }

    private fun mostrarListaMaterias() {
        val textArea = crearTextArea().apply {
            val materias = App.sistema.obtenerTodasLasMaterias()
            text = generarTextoMaterias(materias)
        }

        mostrarPantallaConTexto("Lista de Materias", textArea, ::mostrarPanelProfesor)
    }

    // Genera el texto formateado de la lista de materias
    private fun generarTextoMaterias(materias: List<Materia>): String {
        val texto = StringBuilder("=== MATERIAS DISPONIBLES ===\n\n")

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
                texto.append("\n${"=".repeat(40)}\n\n")
            }
        }
        return texto.toString()
    }

    private fun mostrarFormularioRegistroAlumno() {
        val grid = GridPane().apply {
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0)
        }

        val campoNombre = crearCampoTexto("Ej: Juan")
        val campoApellido = crearCampoTexto("Ej: Pérez")
        val comboMaterias = crearComboMateriasOpcional()
        val labelMensaje = crearLabel("", estiloTexto, false).apply {
            isWrapText = true
            maxWidth = 350.0
        }

        grid.add(crearLabel("Nombre:", estiloTexto), 0, 0)
        grid.add(campoNombre, 1, 0)
        grid.add(crearLabel("Apellido:", estiloTexto), 0, 1)
        grid.add(campoApellido, 1, 1)
        grid.add(crearLabel("Materia (opcional):", estiloTexto), 0, 2)
        grid.add(comboMaterias, 1, 2)

        val botonRegistrar = crearBoton("Registrar") {
            procesarRegistroAlumno(campoNombre, campoApellido, comboMaterias, labelMensaje)
        }

        val root = VBox(15.0).apply {
            children.addAll(
                crearLabel("Registrar Nuevo Alumno", estiloSubtitulo),
                grid,
                labelMensaje,
                crearLayoutBotones(botonRegistrar, crearBoton("Volver", ::mostrarPanelProfesor))
            )
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        stage.scene = Scene(root, 800.0, 600.0)
    }

    // Registra un nuevo alumno y opcionalmente lo inscribe en una materia
    private fun procesarRegistroAlumno(
        campoNombre: TextField,
        campoApellido: TextField,
        comboMaterias: ComboBox<String>,
        labelMensaje: Label
    ) {
        try {
            val nombre = campoNombre.text.trim()
            val apellido = campoApellido.text.trim()

            if (nombre.isBlank() || apellido.isBlank()) {
                throw IllegalArgumentException("Nombre y apellido son obligatorios")
            }

            val idAlumno = App.sistema.registrarAlumno(nombre, apellido)
            val materiaSeleccionada = comboMaterias.value

            labelMensaje.text = if (materiaSeleccionada != null && !materiaSeleccionada.startsWith("---")) {
                val idMateria = extraerIdDeCombo(materiaSeleccionada)
                App.sistema.inscribirAlumnoEnMateria(idAlumno, idMateria)
                val materia = App.sistema.obtenerMateriaPorId(idMateria)
                "✓ Alumno registrado con ID: $idAlumno e inscrito en ${materia?.nombre}"
            } else {
                "✓ Alumno registrado con ID: $idAlumno"
            }

            labelMensaje.style = estiloExito
            labelMensaje.isVisible = true
            campoNombre.clear()
            campoApellido.clear()
            comboMaterias.selectionModel.selectFirst()

        } catch (e: Exception) {
            labelMensaje.text = "✗ Error: ${e.message}"
            labelMensaje.style = estiloError
            labelMensaje.isVisible = true
        }
    }

    private fun mostrarFormularioCrearMateria() {
        val campoNombre = crearCampoTexto("Ej: Química").apply { prefWidth = 300.0 }
        val labelMensaje = crearLabel("", estiloTexto, false)

        val botonCrear = crearBoton("Crear") {
            procesarCreacionMateria(campoNombre, labelMensaje)
        }

        val root = VBox(20.0).apply {
            children.addAll(
                crearLabel("Crear Nueva Materia", estiloSubtitulo),
                crearLabel("Nombre de la Materia:", estiloTexto),
                campoNombre,
                labelMensaje,
                crearLayoutBotones(botonCrear, crearBoton("Volver", ::mostrarPanelProfesor))
            )
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        stage.scene = Scene(root, 800.0, 600.0)
    }

    // Crea una nueva materia en el sistema
    private fun procesarCreacionMateria(campoNombre: TextField, labelMensaje: Label) {
        try {
            val nombre = campoNombre.text.trim()

            if (nombre.isBlank()) {
                throw IllegalArgumentException("El nombre de la materia no puede estar vacío")
            }

            val idMateria = App.sistema.crearMateria(nombre)
            labelMensaje.text = "✓ Materia creada con ID: $idMateria"
            labelMensaje.style = estiloExito
            labelMensaje.isVisible = true
            campoNombre.clear()

        } catch (e: Exception) {
            labelMensaje.text = "✗ Error: ${e.message}"
            labelMensaje.style = estiloError
            labelMensaje.isVisible = true
        }
    }

    // Helpers de UI
    private fun crearLabel(texto: String, estilo: String, visible: Boolean = true) = Label(texto).apply {
        style = estilo
        isVisible = visible
    }

    private fun crearCampoTexto(placeholder: String) = TextField().apply {
        promptText = placeholder
        style = estiloTexto
        prefWidth = 250.0
    }

    private fun crearCampoContraseña(placeholder: String) = PasswordField().apply {
        promptText = placeholder
        style = estiloTexto
        prefWidth = 250.0
    }

    private fun crearBoton(texto: String, accion: () -> Unit = {}) = Button(texto).apply {
        style = estiloTexto
        prefWidth = if (texto.length > 15) 180.0 else 120.0
        setOnAction { accion() }
    }

    private fun crearLayoutBotones(vararg botones: Button) = HBox(10.0).apply {
        children.addAll(botones)
        alignment = Pos.CENTER
    }

    private fun crearTextArea() = TextArea().apply {
        isEditable = false
        style = estiloTexto
        prefHeight = 400.0
    }

    private fun crearComboAlumnos() = ComboBox<String>().apply {
        style = estiloTexto
        prefWidth = 300.0
        items.addAll(App.sistema.obtenerTodosLosAlumnos().map { "ID: ${it.id} - ${it.nombre} ${it.apellido}" })
        if (items.isNotEmpty()) selectionModel.selectFirst()
    }

    private fun crearComboMaterias() = ComboBox<String>().apply {
        style = estiloTexto
        prefWidth = 300.0
        items.addAll(App.sistema.obtenerTodasLasMaterias().map { "ID: ${it.id} - ${it.nombre}" })
        if (items.isNotEmpty()) selectionModel.selectFirst()
    }

    private fun crearComboMateriasOpcional() = ComboBox<String>().apply {
        style = estiloTexto
        prefWidth = 250.0
        items.add("--- Sin materia ---")
        items.addAll(App.sistema.obtenerTodasLasMaterias().map { "ID: ${it.id} - ${it.nombre}" })
        selectionModel.selectFirst()
    }

    private fun mostrarPantallaConTexto(titulo: String, textArea: TextArea, volver: () -> Unit) {
        val root = VBox(15.0).apply {
            children.addAll(crearLabel(titulo, estiloSubtitulo), textArea, crearBoton("Volver", volver))
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }
        stage.scene = Scene(root, 800.0, 600.0)
    }

    // Extrae el ID numérico de un string con formato "ID: X - Nombre"
    private fun extraerIdDeCombo(valor: String?): Int {
        if (valor == null) throw IllegalArgumentException("Debe seleccionar una opción")
        return valor.substringAfter("ID: ").substringBefore(" -").toInt()
    }
}