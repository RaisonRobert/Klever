package io.klever.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import io.klever.R
import io.klever.`object`.Load
import io.klever.`object`.Salvar
import kotlinx.android.synthetic.main.layout_fragment_menu.*

class FragmentMenu : Fragment() {
    private lateinit var loading: AlertDialog
    private val titulo = "Menu"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        loading = Load.createLoadDialog(requireContext(), false)
        return inflater.inflate(R.layout.layout_fragment_menu, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = titulo
        cadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_menuInicial_to_fragmentCadastro)
            Log.i("botao", "cadastro")
        }
        ler.setOnClickListener {
            if (Salvar.arquivosDados.isEmpty() == true) {
                Toast.makeText(
                    requireContext(),
                    "Lista Vazia Cadastre Primeiro",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                findNavController().navigate(R.id.action_menuInicial_to_fragmentLista)
                Log.i("botao", "clicar")
            }
        }

        pesquisar.setOnClickListener {
            val pesquisa =
                view.findViewById<EditText>(R.id.editTextPesquisar).text.toString().isEmpty()
            Salvar.pesquisa = view.findViewById<EditText>(R.id.editTextPesquisar).text.toString()
            Log.i("botao", "Pesquisa: $pesquisa")
            if (pesquisa) {
                validadaDados()
//                Toast.makeText(
//                    requireContext(),
//                    "Campo Vazio",
//                    Toast.LENGTH_SHORT
//                ).show()
            } else if (validaCPF(view)) {
                Log.i("botao", "Pesquisa Dados:  ${Salvar.pesquisa}")
                findNavController().navigate(R.id.action_menuInicial_to_dialog_visualização)
            }
        }
    }

    private fun validadaDados(): Boolean {
        if (TextUtils.isEmpty(editTextPesquisar.text)) {
            editTextPesquisar.error = "Digite o CPF"
            editTextPesquisar.requestFocus()
            return false
        }
        return true
    }

    private fun validaCPF(view: View): Boolean {
        val cpf: String = view.findViewById<EditText>(R.id.editTextPesquisar).text.toString()
        if (cpf.length < 11) {
            editTextPesquisar.error = "Digite os 11 digitos de seu CPF"
            editTextPesquisar.requestFocus()
            return false
        }
        return true
    }
}