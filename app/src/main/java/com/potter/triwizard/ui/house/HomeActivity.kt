package com.potter.triwizard.ui.house

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.potter.triwizard.R
import com.potter.triwizard.databinding.ActivityMainBinding
import com.potter.triwizard.util.remove
import com.potter.triwizard.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
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

    fun showBottomNavigation() {
        binding.bottomNavView.show()
    }

    fun hideBottomNavigation() {
        binding.bottomNavView.remove()
    }

}