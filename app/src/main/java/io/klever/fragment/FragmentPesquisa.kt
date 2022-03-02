package io.klever.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import io.klever.R
import io.klever.`object`.Load
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.dialog_exibir_dados.view.*

class FragmentPesquisa: Fragment(), RecyclerViewListaAdapter.itemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogPesquisa()
//        val view = inflater.inflate(R.layout.dialog_exibir_dados, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // dialogPesquisa(view)
        //pesquisa(view)
    }

    private fun dialogPesquisa() {
        val alertDialogPerguntas = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_exibir_dados, null)
        alertDialogPerguntas.setView(view).show()
    }

    private fun pesquisa(view: View){
//        val alertDialogPerguntas = AlertDialog.Builder(requireContext())
//        val inflater = layoutInflater
//        val view = inflater.inflate(R.layout.dialog_exibir_dados, null)
//        alertDialogPerguntas.setView(view).show()
        var pesquisa: String = view.findViewById<EditText>(R.id.editTextPesquisar).text.toString()
        Salvar.arquivosDados.forEach{
            if(pesquisa == it.CPF){
                Toast.makeText(requireContext(),"achou", Toast.LENGTH_SHORT).show()
                view.visualizacao_nome.text = it.NOME
                view.visualizacao_cpf.text = it.CPF
                view.visualizacao_data.text = it.DATA
                view.visualizacao_email.text = it.EMAIL
                view.visualizacao_telefone.text = it.TELEFONE
            }


        }
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