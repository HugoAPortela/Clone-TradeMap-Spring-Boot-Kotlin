package br.com.dio.trademapclone.ui

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.trademapclone.R
import br.com.dio.trademapclone.domain.Acao
import br.com.dio.trademapclone.extension.formatarMoeda
import kotlinx.android.synthetic.main.item_acao.view.*

class AcaoAdapter(
    private val acoes: MutableList<Acao> = mutableListOf(),
    private val onClick: (Acao) -> Unit
) : RecyclerView.Adapter<AcaoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_acao, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val acao = acoes[position]
        holder.bind(acao)
    }

    override fun getItemCount(): Int = acoes.size

    fun adicionar(acao: Acao) {
        val contemAcao = acoes.any { it.codigo == acao.codigo }
        if (!contemAcao) {
            acoes.add(acao)
            notifyDataSetChanged()
        }
    }

    fun atualizar(acao: Acao?) {
        if (acao == null) return
        acoes.find { it.codigo == acao.codigo }?.let {
            val status = if (acao.valor >= it.valor) {
                Acao.UP
            } else {
                Acao.DOWN
            }
            it.valor = acao.valor
            it.status = status
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(acao: Acao) {
            with(itemView) {
                textViewCodigo.text = acao.codigo
                textViewValor.text = acao.valor.formatarMoeda()
                if (acao.status == Acao.UP) {
                    animacaoBackground(itemView, R.color.green)
                    imageViewSeta.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_baseline_arrow_drop_up_green
                        )
                    )
                } else if (acao.status == Acao.DOWN) {
                    animacaoBackground(itemView, R.color.red)
                    imageViewSeta.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_baseline_arrow_drop_down_red
                        )
                    )
                }
                setOnClickListener { onClick(acao) }
            }
        }

        fun animacaoBackground(itemView: View, color: Int) {
            with(itemView) {
                constraintLayout.setBackgroundColor(ContextCompat.getColor(context, color))
                Handler(Looper.getMainLooper()).postDelayed({
                    constraintLayout.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.gray
                        )
                    )
                }, 1000)
            }
        }
    }

}