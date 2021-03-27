package com.philippo.tp3.ui.user.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.philippo.tp3.R
import kotlinx.android.synthetic.main.user_profile_fragment.*

class UserProfileFragment : Fragment() {

    private lateinit var profileViewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        profileViewModel.firebaseUser.observe(viewLifecycleOwner){
            textViewPerfilEmail.text = it?.email
        }

        profileViewModel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                textViewPerfilNome.text = it.nome
                textViewPerfilUsername.text = it.username
                textViewPerfilDataDeNascimento.text = it.dataNascimento
            }
        }

        return inflater.inflate(R.layout.user_profile_fragment, container, false)
    }
}