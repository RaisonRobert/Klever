package io.klever.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.klever.R
import io.klever.`object`.Load
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.dialog_exibir_dados.view.*

class FragmentPesquisa : Fragment(), RecyclerViewListaAdapter.itemClickListener {
    lateinit var loading: androidx.appcompat.app.AlertDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        loading = Load.createLoadDialog(this.requireContext(), false)
        val view = inflater.inflate(R.layout.layout_fragment_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogPesquisa()
        val navController = findNavController()
        navController.popBackStack(R.id.menuInicial, false)
    }

    private fun dialogPesquisa() {
        val alertDialogExibir = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_exibir_dados, null)
        alertDialogExibir.setView(view)
        val dialog = alertDialogExibir.create()
        var i = false
         loading.show()
        Salvar.arquivosDados.forEach {
            if (Salvar.pesquisa == it.CPF) {
                Toast.makeText(requireContext(), "Pesquisa Encontrada", Toast.LENGTH_SHORT).show()
                i = true
                view.visualizacao_nome.text = it.NOME
                view.visualizacao_cpf.text = it.CPF
                view.visualizacao_data.text = it.DATA
                view.visualizacao_email.text = it.EMAIL
                view.visualizacao_telefone.text = it.TELEFONE

            }
        }
        loading.dismiss()
        if (i == false) {
            Toast.makeText(requireContext(), "Pesquisa Não Encontrada", Toast.LENGTH_SHORT).show()
        } else {
            dialog.show()
            view.btnAlterar.setOnClickListener {
                dialog.dismiss()
                findNavController().navigate(R.id.fragmentAlterar)
            }
        }
       // findNavController().navigate(R.id.action_dialog_visualização_to_menuInicial)
//        val navController = findNavController()
//        navController.popBackStack(R.id.menuInicial, false)
    }

    override fun itemClick(
        dado: DadosBanco,
        btnVisualizar: Button,
        btnExcluir: Button,
        position: Int,
    ) {
        TODO("Not yet implemented")
    }

}