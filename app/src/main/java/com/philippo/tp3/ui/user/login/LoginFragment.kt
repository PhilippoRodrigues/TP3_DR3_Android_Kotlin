package com.philippo.tp3.ui.user.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.philippo.tp3.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_reminder_fragment.*
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    private lateinit var callbackManager: CallbackManager
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var loginButtonFacebook: LoginButton
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        loginButtonFacebook = view.findViewById(R.id.login_button)

        loginButtonFacebook.setPermissions("email", "public_profile")
        loginButtonFacebook.fragment = this
        loginButtonFacebook.registerCallback(
            callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    handleFacebookAccessToken(result.accessToken)
                }

                override fun onCancel() {
                    Log.i("FacebookLogin", "onCancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.i("FacebookLogin", "${error?.message}")
                }
            }
        )

        auth = Firebase.auth

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().bottomNavigationView.visibility = View.VISIBLE
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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            val email = editTextLoginEmail.text.toString()
            val password = editTextLoginSenha.text.toString()
            loginViewModel.verifyUser(email, password)


        }

        btnLoginCadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    if (user != null) {
                        findNavController().navigate(R.id.action_loginFragment_to_listReminderFragment)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        requireContext(), "Authentication failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}