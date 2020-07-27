package com.potter.triwizard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.potter.triwizard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        val navController = findNavController(R.id.fragNavHost)

        binding.bottomNavView.setupWithNavController(navController)

        setupActionBarWithNavController(this, navController)
    }

}