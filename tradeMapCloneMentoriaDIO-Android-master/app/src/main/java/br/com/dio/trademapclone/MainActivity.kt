package br.com.dio.trademapclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupNavController()
        mainViewModel.consumirAcoes()
    }

    private fun setupNavController() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.setNavigationOnClickListener {
                val popped = navController.popBackStack()
                if (!popped) {
                    finish()
                }
            }
            when (destination.id) {
                R.id.loginFragment -> {
                    esconderActionBar()
                }
                else -> {
                    mostrarActionBar()
                }
            }
        }
    }

    fun esconderActionBar() {
        supportActionBar?.hide()
    }

    fun mostrarActionBar() {
        supportActionBar?.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavHostFragment.findNavController(nav_host_fragment).navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.pararCosumirAcoes()
    }

}