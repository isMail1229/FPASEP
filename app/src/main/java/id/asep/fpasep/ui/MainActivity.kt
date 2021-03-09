package id.asep.fpasep.ui

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.ActionBar
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment, R.id.mainMenuFragment)
        )
        setupStartDestination(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    private fun setupStartDestination(navController: NavController) {

        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.food_navigation)
        navGraph.startDestination = R.id.splashFragment
        navController.graph = navGraph
    }

    fun hideActionBar() {
        supportActionBar?.hide()
    }

    fun showActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val theme = ContextThemeWrapper(this, R.style.FoodMobile).theme
            val color = ColorDrawable(resources.getColor(R.color.white, theme))
            supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            supportActionBar?.setDisplayShowCustomEnabled(true)
            supportActionBar?.setCustomView(R.layout.custom_toolbar)
            supportActionBar?.setBackgroundDrawable(color)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        } else {
            //deprecated since android M
            @Suppress("DEPRECATION")
            val color = ColorDrawable(resources.getColor(R.color.white))
            supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            supportActionBar?.setDisplayShowCustomEnabled(true)
            supportActionBar?.setCustomView(R.layout.custom_toolbar)
            supportActionBar?.setBackgroundDrawable(color)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
        supportActionBar?.show()
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