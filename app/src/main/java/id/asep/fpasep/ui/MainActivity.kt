package id.asep.fpasep.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.asep.fpasep.R
import id.asep.fpasep.databinding.ActivityMainBinding
import id.asep.fpasep.utils.extension.setImagesToGlide

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBarWithNavigation()
    }

    private fun setupActionBarWithNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(binding.containerToolbar.toolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.splashFragment,
                R.id.loginFragment,
                R.id.mainMenuFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    fun setupToolbar(type: Int, title: String, description: String, imgPath: String = "") {
        when (type) {
            COMMON_TOOLBAR -> {
                initializeToolbar(title, description)
            }
            DASHBOARD_TOOLBAR -> {
                initializeToolbar(title, description)
                if (imgPath.isNotBlank()) {
                    binding.containerToolbar.avatarImgBar.setImagesToGlide(imgPath)
                } else {
                    binding.containerToolbar.avatarImgBar.setImagesToGlide(R.drawable.ic_add_photo)
                }
            }
        }
    }

    private fun initializeToolbar(title: String, description: String) {
        binding.containerToolbar.headerTitle.text = title
        binding.containerToolbar.headerDescription.text = description
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        private const val DESTINATION_PAGE = "DESTINATION_PAGE"
        const val Food_HOME_PAGE = "Food_HOME_PAGE"
        const val FOOD_LOGIN_PAGE = "NEWS_DETAIL"
        const val COMMON_TOOLBAR = 1
        const val DASHBOARD_TOOLBAR = 2
    }
}