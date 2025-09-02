/*
 * Los objetos Alumno y Evaluaciones fueron creados en class y no data class porque class puede tener variables privadas  y puede validar datos como en el caso de evaluaciones que necesita que las notas de evaluaciones sean mayores de 1 al 10 por dar un ejemplo.
 */
package org.example

import Alumno
import Evaluaciones

fun main(){
    val mati=Alumno("Matias","Blanco",1)
    val eval=Evaluaciones("Trabajo Practico", 8.5)
    println("Se ha agregado la evaluación exitosamente")
}
