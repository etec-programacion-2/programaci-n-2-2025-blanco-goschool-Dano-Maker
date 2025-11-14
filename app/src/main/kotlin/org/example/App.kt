package org.example

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
        val label = Label("Bienvenido a...").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
        }

        val label_2 = Label("GRADEQL").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 32px; -fx-font-weight: bold;"
        }

        val botonProfesor = Button("Entrar como profesor").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 200.0
            setOnAction {
                mostrarLoginProfesor(stage)
            }
        }

        val botonEstudiante = Button("Entrar como estudiante").apply {
            style = "-fx-font-family: 'Times New Roman'; -fx-font-size: 13px;"
            prefWidth = 200.0
            setOnAction {
                println("Acceso como estudiante...")
            }
        }

        val root = VBox().apply {
            children.addAll(label, label_2, botonProfesor, botonEstudiante)
            alignment = Pos.CENTER
            spacing = 15.0
            style = "-fx-padding: 20;"
        }

        val scene = Scene(root, 800.0, 600.0)
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

/**
 * Función main con MENOS DE 10 LÍNEAS (cumple requisito)
 * Toda la lógica de inicialización se delegó a InicializadorSistema
 */
fun main(args: Array<String>) {
    InicializadorSistema.inicializar(App.sistema)
    Application.launch(App::class.java, *args)
}