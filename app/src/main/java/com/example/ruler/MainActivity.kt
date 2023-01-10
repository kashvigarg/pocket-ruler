package com.example.ruler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ruler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(R.layout.activity_main)
    }
}