package io.klever.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

import io.klever.R
import io.klever.`object`.Load
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.dialog_exibir_pesquisa.view.*

class FragmentPesquisa : Fragment(), RecyclerViewListaAdapter.itemClickListener {
    lateinit var loading: AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        loading = Load.createLoadDialog(this.requireContext(), false)
        return inflater.inflate(R.layout.layout_fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogPesquisa()
    }

    @SuppressLint("SetTextI18n")
    private fun dialogPesquisa() {
        val alertDialogExibir = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_exibir_pesquisa, null)
        alertDialogExibir.setView(view)
        val dialog = alertDialogExibir.create()
        var i = false
        loading.show()
        Salvar.arquivosDados.forEach {
            if (Salvar.pesquisa == it.CPF) {
                Toast.makeText(requireContext(), "Pesquisa Encontrada", Toast.LENGTH_SHORT).show()
                i = true
                view.visualizacao_nome_pesquisa.text = it.NOME
                view.visualizacao_cpf_pesquisa.text = "CPF: " + it.CPF
                view.visualizacao_data_pesquisa.text = "Data: " + it.DATA
                view.visualizacao_email_pesquisa.text = "Email: " + it.EMAIL
                view.visualizacao_telefone_pesquisa.text = "Telefone: " + it.TELEFONE
            }
        }
        loading.dismiss()
        if (!i) {
            Toast.makeText(requireContext(), "Pesquisa Não Encontrada", Toast.LENGTH_SHORT).show()
            val navController = findNavController()
            navController.popBackStack(R.id.menuInicial, false)
        } else {
            //limpa a tela da pesquisa
            findNavController().navigate(R.id.action_dialog_visualização_to_menuInicial)
            dialog.show()
        }
    }

    override fun itemClick(
        dado: DadosBanco,
        btnVisualizar: ImageButton,
        btnExcluir: ImageButton,
        position: Int,
    ) {
        TODO("Not yet implemented")
    }

}