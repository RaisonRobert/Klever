package io.klever.fragment

import android.os.Bundle
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
import kotlinx.android.synthetic.main.dialog_exibir_dados.view.*
import kotlinx.android.synthetic.main.layout_fragment_menu.*

class FragmentMenu : Fragment() {

    private lateinit var loading: AlertDialog
    private val titulo = "Menu"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loading = Load.createLoadDialog(requireContext(), false)
        val view = inflater.inflate(R.layout.layout_fragment_menu, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = titulo
//        Salvar.arquivosDados.add(Salvar.dados1)
//        Salvar.arquivosDados.add(Salvar.dados2)
//        Salvar.arquivosDados.add(Salvar.dados3)
//        view.findViewById<EditText>(R.id.editTextPesquisar).setText("Raison")
//        Alterar
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
            var pesquisa = view.findViewById<EditText>(R.id.editTextPesquisar).text.toString().isEmpty()
            Salvar.pesquisa = view.findViewById<EditText>(R.id.editTextPesquisar).text.toString()

            Log.i("botao", "Pesquisa: $pesquisa")
            if(true == pesquisa){
                Toast.makeText(
                    requireContext(),
                    "Campo Vazio",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                Log.i("botao", "Pesquisa Dados:  ${Salvar.pesquisa}")
                findNavController().navigate(R.id.action_menuInicial_to_dialog_visualização)
//                val navController = findNavController()
//                navController.popBackStack(R.id.menuInicial, false)
            }


          //  findNavController().navigate(R.id.menuInicial)
        }
    }


}