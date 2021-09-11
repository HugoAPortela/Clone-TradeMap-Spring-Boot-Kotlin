package br.com.dio.trademapclone.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.dio.trademapclone.R
import br.com.dio.trademapclone.domain.UsuarioLogado
import kotlinx.android.synthetic.main.dialog_acao.view.*
import kotlinx.android.synthetic.main.fragment_acao_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class AcaoListFragment : Fragment(R.layout.fragment_acao_list) {

    private val viewModel: AcaoViewModel by viewModel()
    private val adapter: AcaoAdapter by lazy {
        AcaoAdapter(UsuarioLogado.usuario.acoesFavoritas.toMutableList()) {
            val direcao = AcaoListFragmentDirections.actionFirstFragmentToAcaoDetalhesFragment(it)
            findNavController().navigate(direcao)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        observaUltimaAcao()
        configuraBotaoAdicionarAcao()
        observaAcaoAdicionada()
    }

    private fun observaAcaoAdicionada() {
        viewModel.acaoAdicionada.observe(viewLifecycleOwner, {
            adapter.adicionar(it)
        })
    }

    private fun configuraBotaoAdicionarAcao() {
        floatingActionButton.setOnClickListener {
            val dialogView: View = LayoutInflater.from(context)
                .inflate(R.layout.dialog_acao, view as ViewGroup?, false)
            val dialog = AlertDialog.Builder(context)
                .setTitle("Adicionar")
                .setView(dialogView)
                .create()
            dialog.show()
            dialogView.buttonAdicionarAcao.setOnClickListener {
                val codigo = dialogView.input.text.toString()
                viewModel.adicionarAcao(codigo)
                dialog.dismiss()
            }
        }
    }

    private fun observaUltimaAcao() {
        viewModel.getUltimo().observe(viewLifecycleOwner, {
            adapter.atualizar(it)
        })
    }

    private fun configuraRecyclerView() {
        recyclerView.adapter = adapter
    }

}