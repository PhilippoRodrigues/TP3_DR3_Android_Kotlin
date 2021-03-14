package com.philippo.tp3.ui.user.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.philippo.tp3.R
import kotlinx.android.synthetic.main.list_reminder_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.status.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_loginFragment_to_listReminderFragment)
            }
        }

        loginViewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank())
                Snackbar.make(
                    requireContext(), this.requireView(), it, Snackbar
                        .LENGTH_LONG
                ).show()
        }

        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener{
            val email = editTextLoginEmail.text.toString()
            val password = editTextLoginSenha.text.toString()
            loginViewModel.verifyUser(email, password)


        }

        btnLoginCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}