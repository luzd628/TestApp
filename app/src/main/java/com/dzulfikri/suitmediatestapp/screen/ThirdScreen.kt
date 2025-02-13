package com.dzulfikri.suitmediatestapp.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dzulfikri.suitmediatestapp.R
import com.dzulfikri.suitmediatestapp.databinding.ActivitySecondScreenBinding
import com.dzulfikri.suitmediatestapp.databinding.ActivityThirdScreenBinding

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}