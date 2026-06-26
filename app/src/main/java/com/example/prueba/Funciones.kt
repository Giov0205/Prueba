package com.example.prueba

class Participante(val nombre: String) {
    var puntos: Int = 0

    fun obtenerCategoria(): String {
        val pts = this.puntos
        if (pts >= 1000) return "Leyenda"
        if (pts in 500..999) return "Experto"
        if (pts in 200..499) return "Competidor"
        return "Novato"
    }
}

fun main() {
    val listaJugadores = mutableListOf<Participante>()
    var opc = 0

    do {
        println("\n--- SISTEMA DE TORNEO DE VIDEOJUEGOS ---")
        println("1. Registrar participante")
        println("2. Registrar puntos")
        println("3. Consultar participante")
        println("4. Mostrar estadisticas del torneo")
        println("5. Finalizar programa")
        print("Seleccione una opcion: ")

        val inputOpc = readln()
        opc = inputOpc.toIntOrNull() ?: 0

        when (opc) {
            1 -> {
                print("Nombre del nuevo participante: ")
                val nombreInput = readln().trim()

                if (nombreInput == "") {
                    println("Error: No puedes dejar el nombre vacio.")
                } else {
                    val yaExiste = listaJugadores.any { it.nombre.equals(nombreInput, ignoreCase = true) }
                    if (yaExiste) {
                        println("Ese jugador ya esta en la lista.")
                    } else {
                        listaJugadores.add(Participante(nombreInput))
                        println("Listo! $nombreInput fue registrado.")
                    }
                }
            }
            2 -> {
                print("A que participante le vas a sumar puntos?: ")
                val buscarNom = readln().trim()
                val player = listaJugadores.find { it.nombre.equals(buscarNom, ignoreCase = true) }

                if (player == null) {
                    println("No encontramos a ese participante.")
                } else {
                    print("Puntos a sumarle: ")
                    val pEntre = readln()
                    val ptsAñadir = pEntre.toIntOrNull() ?: 0

                    if (ptsAñadir <= 0) {
                        println("Pon una cantidad mayor a 0.")
                    } else {
                        player.puntos += ptsAñadir
                        println("Modificado. Ahora ${player.nombre} tiene ${player.puntos} puntos.")
                    }
                }
            }
            3 -> {
                print("Escribe el nombre a buscar: ")
                val buscarNom = readln().trim()
                val player = listaJugadores.find { it.nombre.equals(buscarNom, ignoreCase = true) }

                if (player == null) {
                    println("Error: El participante no existe.")
                } else {
                    println("\n--- Datos del Participante ---")
                    println("Nombre: ${player.nombre}")
                    println("Puntos en total: ${player.puntos}")
                    println("Rango/Categoria: ${player.obtenerCategoria()}")
                }
            }
            4 -> {
                if (listaJugadores.size == 0) {
                    println("No hay datos que mostrar porque la lista esta vacia.")
                } else {
                    var sumaPuntos = 0
                    var elMejor = listaJugadores[0]
                    var elPeor = listaJugadores[0]

                    var cuentaNovatos = 0
                    var cuentaCompetidores = 0
                    var cuentaExpertos = 0
                    var cuentaLeyendas = 0

                    for (j in listaJugadores) {
                        sumaPuntos += j.puntos

                        if (j.puntos > elMejor.puntos) elMejor = j
                        if (j.puntos < elPeor.puntos) elPeor = j

                        val cat = j.obtenerCategoria()
                        if (cat == "Novato") cuentaNovatos++
                        if (cat == "Competidor") cuentaCompetidores++
                        if (cat == "Experto") cuentaExpertos++
                        if (cat == "Leyenda") cuentaLeyendas++
                    }

                    val prom = sumaPuntos.toDouble() / listaJugadores.size

                    println("\n--- ESTADISTICAS GENERALES ---")
                    println("Total de registrados: ${listaJugadores.size}")
                    println("Puntos totales del torneo: $sumaPuntos")
                    println("Promedio de puntos por persona: $prom")
                    println("Top 1 (Mayor puntaje): ${elMejor.nombre} con ${elMejor.puntos} pts")
                    println("Ultimo lugar (Menor puntaje): ${elPeor.nombre} con ${elPeor.puntos} pts")
                    println("\n--- Conteo por rangos ---")
                    println("Novatos: $cuentaNovatos")
                    println("Competidores: $cuentaCompetidores")
                    println("Expertos: $cuentaExpertos")
                    println("Leyendas: $cuentaLeyendas")
                }
            }
            5 -> println("Saliendo... Bye.")
            else -> println("Esa opcion no sirve, intenta de nuevo.")
        }
    } while (opc != 5)
}