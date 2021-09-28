package com.resurrection.cryptoassistant.ui.auth

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.resurrection.cryptoassistant.R
import com.resurrection.cryptoassistant.databinding.FragmentRegisterBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_register
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.loginBtn.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.editLoginEmail.editableText.toString(),
                binding.editLoginPasswd.editableText.toString()
            )
                .addOnSuccessListener {

                }.addOnFailureListener {

                }
        }

    }

}