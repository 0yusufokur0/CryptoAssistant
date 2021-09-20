package com.resurrection.cryptoassistant.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.resurrection.cryptoassistant.databinding.FragmentLoginBinding
import com.resurrection.cryptoassistant.ui.base.BaseFragment
import com.resurrection.cryptoassistant.ui.main.HomeActivity
import com.resurrection.cryptoassistant.ui.main.MainActivity


class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val TAG: String
        get() = LoginFragment::class.java.simpleName


    override fun getLayoutRes(): Int {
        return com.resurrection.cryptoassistant.R.layout.fragment_login
    }

    override fun init(savedInstanceState: Bundle?) {



        binding.loginBtn.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.editLoginEmail.editableText.toString(),
                binding.editLoginPasswd.editableText.toString()
            ).addOnSuccessListener {
                startActivity(Intent(activity, HomeActivity::class.java))
                println("giriş yapıldı")

            }.addOnFailureListener {

            }
        }
    }


}