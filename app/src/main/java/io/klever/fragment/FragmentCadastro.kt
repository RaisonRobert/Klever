package io.klever.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.klever.R
import io.klever.`object`.Salvar
import io.klever.adapter.RecyclerViewListaAdapter
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.layout_fragment_cadastro.*
import java.text.SimpleDateFormat

class FragmentCadastro : Fragment(), RecyclerViewListaAdapter.itemClickListener {
    lateinit var adapterCadastro: RecyclerViewListaAdapter
    private val titulo = "Cadastro"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = titulo
        adapterCadastro = RecyclerViewListaAdapter(this)
        enviarCadastro.setOnClickListener {
            if (validadaDados()) {
                if (validaCPF(view)) {
                    cadastro(view)
                    abrirOk()
                    findNavController().navigate(R.id.action_fragmentCadastro_to_menuInicial)
                    val navController = findNavController()
                    navController.popBackStack(R.id.menuInicial, false)
                }
            }
        }
    }

    private fun validaCPF(view: View): Boolean {
        val cpf: String = view.findViewById<EditText>(R.id.editTextCpf).text.toString()
        if (view.findViewById<EditText>(R.id.editTextCpf).text.length < 11) {
            editTextCpf.error = "Digite os 11 digitos de seu CPF"
            editTextCpf.requestFocus()
            return false
        }
        if (view.findViewById<EditText>(R.id.editTextDate).text.length < 8) {
            editTextCpf.error = "dd/MM/yyyy"
            editTextCpf.requestFocus()
            return false
        }
        Salvar.arquivosDados.forEach {
            if (Salvar.pesquisa == cpf) {
                return true
            } else if (Salvar.verificaDadosNoBanco(cpf)) {
                editTextCpf.error = "CPF já Cadastrado"
                editTextCpf.requestFocus()
                return false
            }
        }
        return true
    }

    private fun validadaDados(): Boolean {
        if (TextUtils.isEmpty(editTextTextPersonName.text)) {
            editTextTextPersonName.error = "Nome Obrigatório"
            editTextTextPersonName.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(editTextCpf.text)) {
            editTextCpf.error = "CPF Obrigatório"
            editTextCpf.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(editTextDate.text)) {
            editTextDate.error = "Data Obrigatório"
            editTextDate.requestFocus()
            return false
        }

        return true
    }

    @SuppressLint("SimpleDateFormat")
    private fun cadastro(view: View) {
        val nome: String = view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        val email: String = view.findViewById<EditText>(R.id.editTextTextEmail).text.toString()
        val cpf: String = view.findViewById<EditText>(R.id.editTextCpf).text.toString()
        val telefone: String = view.findViewById<EditText>(R.id.editTextPhone).text.toString()
        val data: String = view.findViewById<EditText>(R.id.editTextDate).text.toString()
        SimpleDateFormat(data)
        Log.i("dada cadastro", "data: $data")
        val dadoCadastro = (
                DadosBanco(
                    NOME = nome,
                    EMAIL = email,
                    CPF = cpf,
                    TELEFONE = telefone,
                    DATA = data
                )
                )
        Log.i("dados", "$dadoCadastro")
        Salvar.arquivosDados.add(dadoCadastro)
    }

    private fun abrirOk() {
        val alertDialogExibir = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_cadastro, null)
        alertDialogExibir.setView(view).show()
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