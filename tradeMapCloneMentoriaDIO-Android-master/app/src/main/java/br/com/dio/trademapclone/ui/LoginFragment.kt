package br.com.dio.trademapclone.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.dio.trademapclone.MainActivity
import br.com.dio.trademapclone.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observaUsuario()
        configuraBotaoLogin()
    }

    private fun configuraBotaoLogin() {
        button.setOnClickListener {
            val usuario = textInputLayout.editText?.text.toString()
            viewModel.login(usuario)
        }
    }

    private fun observaUsuario() {
        viewModel.usuario.observe(viewLifecycleOwner, {
            (activity as MainActivity).toolbar.visibility = View.VISIBLE
            val direcao = LoginFragmentDirections.actionLoginFragmentToFirstFragment()
            findNavController().navigate(direcao)
        })
    }

}