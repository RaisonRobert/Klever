package io.klever.`object`

import io.klever.model.DadosBanco

object Salvar {
    var arquivosDados: MutableList<DadosBanco> = mutableListOf()
    val dados1 =
        DadosBanco(
            NOME = "Raison",
            EMAIL = "rayson@gmail.com",
            CPF = "01618090224",
            DATA = "26/09/1994",
            TELEFONE = "(92) 995260040"
        )
    val dados2 =
        DadosBanco(
            NOME = "Robert",
            EMAIL = "rayson@gmail.com",
            CPF = "016.180.902-24",
            DATA = "26/09/1994",
            TELEFONE = "(92) 995260040"
        )
    val dados3 =
        DadosBanco(
            NOME = "Monteiro",
            EMAIL = "rayson@gmail.com",
            CPF = "016.180.902-24",
            DATA = "26/09/1994",
            TELEFONE = "(92) 995260040"
        )

}