/*
 * Los objetos Alumno y Evaluaciones fueron creados en class y no data class porque class puede tener variables privadas  y puede validar datos como en el caso de evaluaciones que necesita que las notas de evaluaciones sean mayores de 1 al 10 por dar un ejemplo.
 */
package org.example
import Alumno
import Evaluaciones
fun main() {
    //Alumnos
    val mati = Alumno("Matias", "Blanco", 1)
    val cande = Alumno(nombre="Candela", apellido="Delacruz", id= 2)
    val ivo = Alumno(nombre="Ivo", apellido="Giovarruscio", id= 3)
    val maxi = Alumno(nombre="Maximo", apellido = "Aznar", id= 4)
    val bruno= Alumno(nombre= "Bruno", apellido= "Levatino", id = 5)
    //evaluaciones
    val eval = Evaluaciones("Trabajo Practico", 8.5)
    //codigo
    mati.agregarEvaluacion(eval)
    mati.mostrarInformacion()
    cande.mostrarInformacion()
    ivo.mostrarInformacion()
    maxi.mostrarInformacion()
    bruno.mostrarInformacion()
    println("\nEvaluación recién agregada:")
    println(eval)
}