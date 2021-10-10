package com.resurrection.cryptoassistant.ui.auth

import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.resurrection.cryptoassistant.databinding.FragmentLoginBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.HomeActivity

import com.resurrection.cryptoassistant.R


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.loginBtn.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.editLoginEmail.editableText.toString(),
                binding.editLoginPasswd.editableText.toString()
            ).addOnSuccessListener {
                startActivity(Intent(activity, HomeActivity::class.java))
            }.addOnFailureListener {

            }
        }
        binding.orRegisterBtn.setOnClickListener {
            this.parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.right_to_left_first, R.anim.right_to_left_second,)
            .replace(R.id.authFrameLayout, RegisterFragment())
            .addToBackStack(null)
            .commit()
        }
    }
}