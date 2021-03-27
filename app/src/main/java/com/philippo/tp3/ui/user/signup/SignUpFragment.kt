package com.philippo.tp3.ui.user.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.philippo.tp3.R
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner) {
            if (it)
                findNavController().popBackStack()
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }

        return inflater.inflate(
            R.layout.sign_up_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCadastro.setOnClickListener {
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

            if ((password == repeatPassword) && (password.isNotEmpty() &&
                        repeatPassword.isNotEmpty())) {
                val email = editTextCadastroEmail.text.toString()
                val nome = editTextCadastroNome.text.toString()
                val username = editTextCadastroUsername.text.toString()
                val dataNascimento = editTextCadastroDataNascimento.text.toString()

                when {
                    email.isEmpty() -> {
                        viewModel.changeMessage("Email field is wrong or empty")
                    }
                    nome.isEmpty() -> {
                        viewModel.changeMessage("Name field is wrong or empty")
                    }
                    username.isEmpty() -> {
                        viewModel.changeMessage("Username field is wrong or empty")
                    }
                    dataNascimento.isEmpty() -> {
                        viewModel.changeMessage("Birthdate field is wrong or empty")
                    }
                    else -> {
                        viewModel.saveRegister(
                            email, password, nome, username, dataNascimento)
                    }
                }
            } else if (password != repeatPassword) {
                viewModel.changeMessage("Passwords are not the same")
            } else if (password.isEmpty() || repeatPassword.isEmpty()) {
                viewModel.changeMessage("Password field is wrong or empty")
            } else if (password.isEmpty() && repeatPassword.isEmpty()) {
                viewModel.changeMessage("Password field is wrong or empty")
            }

            Snackbar.make(requireContext(), this.requireView(), "Usuário cadastrado com sucesso!", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}