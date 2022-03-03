package io.klever.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.klever.R
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.layout_fragment_cadastro.*

class FragmentCadastro : Fragment(), RecyclerViewListaAdapter.itemClickListener {
    lateinit var adapterCadastro: RecyclerViewListaAdapter
    private lateinit var loading: AlertDialog
    private val titulo = "Cadastro"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = titulo
        adapterCadastro = RecyclerViewListaAdapter(this)
        enviarCadastro.setOnClickListener {
            cadastro(view)
            abrirOk(view)
            findNavController().navigate(R.id.action_fragmentCadastro_to_menuInicial)
        }
    }

    private fun cadastro(view: View) {
        var nome: String = view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        var email: String = view.findViewById<EditText>(R.id.editTextTextEmail).text.toString()
        var cpf: String = view.findViewById<EditText>(R.id.editTextCpf).text.toString()
        var telefone: String = view.findViewById<EditText>(R.id.editTextPhone).text.toString()
        var data: String = view.findViewById<EditText>(R.id.editTextDate).text.toString()
        val dadoCadastro = (
                DadosBanco(
                    NOME = nome,
                    EMAIL = email,
                    CPF = cpf,
                    TELEFONE = telefone,
                    DATA = data
                )
                )
        Log.i("dados", "${dadoCadastro}")
        Salvar.arquivosDados.add(dadoCadastro)
    }

    private fun abrirOk(view: View) {
        val alertDialogExibir = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_cadastro, null)
        alertDialogExibir.setView(view).show()
    }

    override fun itemClick(dado: DadosBanco, btnVisualizar: Button, btnExcluir: Button, position: Int) {
        TODO("Not yet implemented")
    }
}