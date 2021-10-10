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
                .addOnSuccessListener { }
                .addOnFailureListener { }
        }
        binding.orLoginBtn.setOnClickListener {
            this.parentFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_to_right_first, R.anim.left_to_right_second)
                .replace(R.id.authFrameLayout, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}