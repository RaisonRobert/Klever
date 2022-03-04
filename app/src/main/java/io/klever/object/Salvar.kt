package io.klever.`object`

import io.klever.model.DadosBanco

object Salvar {
    var pesquisa: String? = null
    var arquivosDados: MutableList<DadosBanco> = mutableListOf()
    fun verificaDadosNoBanco(cpf: String): Boolean {
        arquivosDados.forEach {
            if (cpf == it.CPF) {
                return true
            }
        }
        return false
    }
}
