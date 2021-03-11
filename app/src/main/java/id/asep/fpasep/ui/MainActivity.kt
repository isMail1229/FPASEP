package id.asep.fpasep.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.asep.fpasep.R
import id.asep.fpasep.databinding.ActivityMainBinding
import id.asep.fpasep.utils.extension.invisible
import id.asep.fpasep.utils.extension.setImagesToGlide
import id.asep.fpasep.utils.extension.visible

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
        supportActionBar?.show()
        binding.containerToolbar.toolbar.visible()
        binding.containerToolbar.avatarImgBar.visible()
        when (type) {
            COMMON_TOOLBAR -> {
                initializeToolbar(title, description)
                binding.containerToolbar.avatarImgBar.invisible()
            }
            DASHBOARD_TOOLBAR -> {
                initializeToolbar(title, description)
                if (imgPath.isNotBlank()) {
                    binding.containerToolbar.avatarImgBar.setImagesToGlide(imgPath)
                } else {
                    binding.containerToolbar.avatarImgBar.setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.ic_add_photo)
                    )
                }
            }
            WITHOUT_TOOLBAR -> {
                supportActionBar?.hide()
            }
        }
    }

    fun setupBottomNavigation(
        bottomNavigationView: BottomNavigationView,
        navHostFragment: NavHostFragment
    ) {
        val navHostController = navHostFragment.findNavController()
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.orderFragment, R.id.accountFragment))
        setupActionBarWithNavController(navHostController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navHostController)
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
        const val WITHOUT_TOOLBAR = 3
    }
}