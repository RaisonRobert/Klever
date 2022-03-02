package io.klever.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.klever.R
import io.klever.`object`.Load
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.dialog_exibir_dados.view.*

class FragmentLista : Fragment(), RecyclerViewListaAdapter.itemClickListener {
    lateinit var loading: androidx.appcompat.app.AlertDialog
    private val titulo = "Lista de Todos Cadastrado"
    lateinit var recycler_lista: RecyclerView
    lateinit var adapterLista: RecyclerViewListaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_fragment_lista, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = titulo
        loading = Load.createLoadDialog(this.requireContext(), false)
        adapterLista = RecyclerViewListaAdapter(this)
        setupRecyclerView(view)
        addDados(view)

    }

    private fun addDados(view: View) {
        loading.show()
        adapterLista.popularLista(Salvar.arquivosDados)
        loading.dismiss()
    }

    private fun setupRecyclerView(view: View) {
        recycler_lista = view.findViewById<RecyclerView>(R.id.lista_recyclerView)
        recycler_lista.setLayoutManager(LinearLayoutManager(requireContext()))
        recycler_lista.adapter = adapterLista
        adapterLista.apply {
            itemListener = object : RecyclerViewListaAdapter.itemClickListener {
                override fun itemClick(dado: DadosBanco,btnVisualizar: Button, btnExcluir: Button, position: Int) {
                    btnVisualizar.setOnClickListener {
                        Log.i("lista", "botao Visualizar >> posição: $position")
                        exibir(view, dado)
//                        viewTest.editTextTextPersonName.text = dado.NOME
                    }
                    btnExcluir.setOnClickListener {
                        abrirExcluir(view, dado, position)
                        Log.i("lista", "botao Excluir >> posição: $position")
                        Log.i("lista", "botao Excluir >> posição: $dado")
                        Log.i("lista", "botao Excluir >> posição: ${Salvar.arquivosDados}")
                        if (Salvar.arquivosDados.isEmpty() == false) {
                            findNavController().navigate(R.id.fragmentLista)
                        }
                        else{
                            val navController = findNavController()
                            Toast.makeText(requireContext(),"Lista Vazia Cadastre Primeiro",Toast.LENGTH_SHORT).show()
                            navController.popBackStack(R.id.menuInicial, false)
                        }

                    }
                }
            }
        }

    }
    private fun exibir(view: View, dado: DadosBanco) {
        val alertDialogExibir = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_exibir_dados, null)
        alertDialogExibir.setView(view).show()
        view.visualizacao_nome.text = dado.NOME
        view.visualizacao_cpf.text = dado.CPF
        view.visualizacao_data.text = dado.DATA
        view.visualizacao_email.text = dado.EMAIL
        view.visualizacao_telefone.text = dado.TELEFONE
        view.btnAlterar.setOnClickListener{
            Salvar.pesquisa = dado.CPF
            findNavController().navigate(R.id.fragmentAlterar)
        }
    }
    private fun abrirExcluir(view: View, dado: DadosBanco, position: Int) {
        Salvar.arquivosDados.remove(dado)
        val alertDialogPerguntas = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_excluir, null)
        alertDialogPerguntas.setView(view).show()
    }
    override fun itemClick(dado: DadosBanco, btnVisualizar: Button, btnExcluir: Button,position: Int) {
        TODO("Not yet implemented")
    }

}
