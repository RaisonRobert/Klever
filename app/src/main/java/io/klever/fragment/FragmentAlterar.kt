package io.klever.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.klever.R
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.dialog_exibir_dados.view.*
import kotlinx.android.synthetic.main.layout_fragment_alterar.*
import kotlinx.android.synthetic.main.layout_fragment_alterar.view.*

class FragmentAlterar: Fragment(), RecyclerViewListaAdapter.itemClickListener{
    //lateinit var loading: androidx.appcompat.app.AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //loading = Load.createLoadDialog(this.requireContext(), false)
        val view = inflater.inflate(R.layout.layout_fragment_alterar, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alterar(view)

        btnSalvarAlteracao.setOnClickListener{
            var nome: String = view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            var email: String = view.findViewById<EditText>(R.id.editTextTextEmail).text.toString()
            var cpf: String = view.findViewById<EditText>(R.id.editTextCpf).text.toString()
            var telefone: String = view.findViewById<EditText>(R.id.editTextPhone).text.toString()
            var data: String = view.findViewById<EditText>(R.id.editTextDate).text.toString()
            //it.NOME = nome
            //Log.i("pesquisa", "nome Alterado: ${it.NOME}")
            Log.i("pesquisa", "nome local: ${nome}")
            findNavController().navigate(R.id.menuInicial)
        }
//        val navController = findNavController()
//        Toast.makeText(requireContext(),"Lista Vazia Cadastre Primeiro",Toast.LENGTH_SHORT).show()
//        navController.popBackStack(R.id.menuInicial, false)

    }
    fun alterar(view: View) {
        Log.i("alterar", "${Salvar.alterar}")
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


    //findNavController().navigate(R.id.action_dialog_visualização_to_fragmentAlterar)

    override fun itemClick(
        dado: DadosBanco,
        btnVisualizar: Button,
        btnExcluir: Button,
        position: Int,
    ) {
        TODO("Not yet implemented")
    }
}