package io.klever.`object`

import io.klever.fragment.FragmentPesquisa
import io.klever.model.DadosBanco

object Salvar {
    lateinit var pesquisa: String
    var alterar: MutableList<DadosBanco> = mutableListOf()
    var arquivosDados: MutableList<DadosBanco> = mutableListOf()
    val dados1 =
        DadosBanco(
            NOME = "Raison",
            EMAIL = "rayson@gmail.com",
            CPF = "1",
            DATA = "26/09/1994",
            TELEFONE = "(92) 995260040"
        )
    val dados2 =
        DadosBanco(
            NOME = "Robert",
            EMAIL = "rayson@gmail.com",
            CPF = "2",
            DATA = "26/09/1994",
            TELEFONE = "(92) 995260040"
        )
    val dados3 =
        DadosBanco(
            NOME = "Monteiro",
            EMAIL = "rayson@gmail.com",
            CPF = "3",
            DATA = "26/09/1994",
            TELEFONE = "(92) 995260040"
        )

}