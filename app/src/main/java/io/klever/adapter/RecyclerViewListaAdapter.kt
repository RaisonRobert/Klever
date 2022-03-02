package io.klever.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import io.klever.R
import io.klever.model.DadosBanco
import kotlinx.android.synthetic.main.item_fragment_list.view.*

class RecyclerViewListaAdapter(var clickListener: itemClickListener) :
    RecyclerView.Adapter<RecyclerViewListaAdapter.ViewHolder>() {
    private var listar: MutableList<DadosBanco> = mutableListOf()
    var itemListener: itemClickListener? = null

    interface itemClickListener {
        fun itemClick(dado: DadosBanco, btnVisualizar : Button, btnExcluir: Button, position: Int)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(listAdapter: DadosBanco, action: RecyclerViewListaAdapter.itemClickListener) {
            itemView.textViewNome.text = listAdapter.NOME.toString()
            itemView.textViewCpf.text = listAdapter.CPF.toString()
            itemView.textViewData.text = listAdapter.DATA.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listar.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listr = listar[position]
        holder.bindView(listr, clickListener)
        holder.let {
            itemListener?.itemClick(listar[position], it.itemView.btnVisualizar, it.itemView.btnExcluir, position)
        }
    }

    public fun popularLista(dado: MutableList<DadosBanco>) {
        this.listar.clear()
        this.listar.addAll(dado)
        notifyDataSetChanged()
    }
}