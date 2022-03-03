package io.klever.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.klever.R
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.layout_fragment_alterar.*

class FragmentAlterar : Fragment(), RecyclerViewListaAdapter.itemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.layout_fragment_alterar, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAlterar(view)
        btnSalvarAlteracao.setOnClickListener {
            abrirOk(view)
            findNavController().navigate(R.id.action_fragmentAlterar_to_menuInicial)
            val navController = findNavController()
            navController.popBackStack(R.id.menuInicial, false)
        }

    }

    fun addAlterar(view: View) {
        Salvar.arquivosDados.forEach {
            if (Salvar.pesquisa == it.CPF) {
                view.findViewById<EditText>(R.id.editTextAlterarNome).setText(it.NOME)
                view.findViewById<EditText>(R.id.editTextAlterarEmail).setText(it.EMAIL)
                view.findViewById<EditText>(R.id.editTextAlterarCpf).setText(it.CPF)
                view.findViewById<EditText>(R.id.editTextAlterarPhone).setText(it.TELEFONE)
                view.findViewById<EditText>(R.id.editTextAlterarDate).setText(it.DATA)
            }
        }
    }

    private fun abrirOk(view: View) {
        var i = false
        Salvar.arquivosDados.forEach {
            i = false
            if (Salvar.pesquisa == it.CPF) {
                i = true
                Log.i("pesquisa", "antes : ${it.NOME}")
                it.NOME = view.findViewById<EditText>(R.id.editTextAlterarNome).text.toString()
                it.EMAIL = view.findViewById<EditText>(R.id.editTextAlterarEmail).text.toString()
                it.CPF = view.findViewById<EditText>(R.id.editTextAlterarCpf).text.toString()
                it.TELEFONE = view.findViewById<EditText>(R.id.editTextAlterarPhone).text.toString()
                it.DATA = view.findViewById<EditText>(R.id.editTextAlterarDate).text.toString()
                Log.i("pesquisa", "valor de i: ${i}")
            }
        }
        Log.i("pesquisa", "valor de i: ${i}")
        val alertDialogExibir = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_cadastro, null)
        view.findViewById<TextView>(R.id.textViewDialog).text = "Alteração Feita com Sucesso"
        alertDialogExibir.setView(view).show()
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