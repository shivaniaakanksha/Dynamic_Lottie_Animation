package com.example.dynamiclottieanimation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.example.dynamiclottieanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.menu
        setUpAnimation(navView.menu)
    }

    private fun setUpAnimation(menu: Menu) {
        var lottieUrl: String =
            "https://raw.githubusercontent.com/shivaniaakanksha/lottie_animation/main/141376-browser-click.json"

        for (menuItem in menu) {

            if (menuItem.title == "Home") {
                lottieUrl =
                    "https://raw.githubusercontent.com/shivaniaakanksha/lottie_animation/main/99515-work-home-woman.json"
            } else if (menuItem.title == "Notifications") {
                lottieUrl =
                    "https://raw.githubusercontent.com/shivaniaakanksha/lottie_animation/main/19114-notification-bell.json"
            } else if (menuItem.title == "Dashboard") {
                lottieUrl =
                    "https://raw.githubusercontent.com/shivaniaakanksha/lottie_animation/main/141376-browser-click.json"
            }

            menuItem.icon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_baseline_attach_money_24
            ) // Image drawable before fetching lottie animation from URL.
            val lottieDrawable: LottieDrawable = LottieDrawable()

            val task = LottieCompositionFactory.fromUrl(this, lottieUrl)

            task.addListener { result ->
                lottieDrawable.composition = result
                lottieDrawable.repeatCount = LottieDrawable.INFINITE
                lottieDrawable.playAnimation()
            }
            task.addFailureListener {
                Log.i("in Failure", it.localizedMessage)
                menuItem.icon = ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_attach_money_24
                ) //Image drawable for failure case
            }
            menuItem.icon = lottieDrawable

        }
    }

}