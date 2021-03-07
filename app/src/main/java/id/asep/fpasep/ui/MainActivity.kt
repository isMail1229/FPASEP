package id.asep.fpasep.ui

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import id.asep.fpasep.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavigation()
    }

    private fun setupActionBarWithNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val theme = ContextThemeWrapper(this, R.style.FoodMobile).theme
            val color = ColorDrawable(resources.getColor(R.color.white, theme))
            supportActionBar?.setBackgroundDrawable(color)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        } else {
            //deprecated since android M
            @Suppress("DEPRECATION")
            val color = ColorDrawable(resources.getColor(R.color.white))
            supportActionBar?.setBackgroundDrawable(color)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration.Builder()
            .setFallbackOnNavigateUpListener { //
                finish() // Trigger the Activity's navigate up functionality
                true
            }.build()
        setupStartDestination(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    private fun setupStartDestination(navController: NavController) {
        val startDestination = intent.getStringExtra(DESTINATION_PAGE)

        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.food_navigation)

        navGraph.startDestination = when (startDestination) {
            Food_HOME_PAGE -> R.id.mainMenuFragment
            FOOD_LOGIN_PAGE -> R.id.loginFragment
            else -> R.id.mainMenuFragment
        }

        navController.graph = navGraph
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }

    companion object {
        private const val DESTINATION_PAGE = "DESTINATION_PAGE"
        const val Food_HOME_PAGE = "Food_HOME_PAGE"
        const val FOOD_LOGIN_PAGE = "NEWS_DETAIL"
    }
}