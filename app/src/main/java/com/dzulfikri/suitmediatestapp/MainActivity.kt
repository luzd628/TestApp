package com.dzulfikri.suitmediatestapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dzulfikri.suitmediatestapp.databinding.ActivityMainBinding
import com.dzulfikri.suitmediatestapp.screen.SecondScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCheck.setOnClickListener {
            val input = binding.tvPalindrome.text.toString()
            if (input.isNotEmpty()){
                val isPalindrome = palindromChecker(input)
                val messages = if (isPalindrome) "isPalindrome" else "not a Palindrome"
                showDialog(messages)
            }else{
                showDialog("Text Empty")
            }
        }

        nextButton()


    }

    private fun palindromChecker(text:String):Boolean {
        val textClean = text.lowercase().replace(Regex("[^a-zA-Z0-9]"), "")
        return textClean == textClean.reversed()
    }

    private fun showDialog(message:String){
        AlertDialog.Builder(this)
            .setTitle("Palindrome Checker")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun nextButton(){
        binding.btnNext.isEnabled = false
        binding.tvUserName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.btnNext.isEnabled = !s.isNullOrBlank()
            }

        })

        binding.btnNext.setOnClickListener {
            val name = binding.tvUserName.text.toString()
            val intent = Intent(this@MainActivity, SecondScreen::class.java)
            intent.putExtra("KEY_NAME",name)
            startActivity(intent)
        }

    }


}