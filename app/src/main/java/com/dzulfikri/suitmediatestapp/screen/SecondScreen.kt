package com.dzulfikri.suitmediatestapp.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dzulfikri.suitmediatestapp.R
import com.dzulfikri.suitmediatestapp.databinding.ActivitySecondScreenBinding

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setToolbar()
        setDataName()

    }

    private fun setToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.elevation = 8f
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnChoseUser.setOnClickListener {
            val intent = Intent(this@SecondScreen, ThirdScreen::class.java)
            startActivity(intent)
        }
    }


    private fun setDataName(){
        val name = intent.getStringExtra("KEY_NAME") ?: "Not Found"
        binding.tvUserName.text = name

        val labelName = intent.getStringExtra("EXTRA_SELECTED_NAME") ?: "Selected User Name"
        binding.tvSelectedUserName.text = labelName

    }

}