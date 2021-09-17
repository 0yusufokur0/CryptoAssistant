package com.resurrection.cryptoassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.resurrection.cryptoassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
    }
}