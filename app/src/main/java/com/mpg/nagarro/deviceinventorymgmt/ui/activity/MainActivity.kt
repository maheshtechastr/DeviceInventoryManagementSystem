package com.mpg.nagarro.deviceinventorymgmt.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mpg.nagarro.deviceinventorymgmt.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    /*
     * Rather than injecting the ViewModelFactory
     * in the activity, we are going to implement the
     * HasActivityInjector and inject the ViewModelFactory
     * into our Fragments
     * */
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment?>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment?>? {
        return dispatchingAndroidInjector
    }

    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
       * We still need to inject this method
       * into our activity so that our fragment can
       * inject the ViewModelFactory
       * */
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationDrawer()

        setSupportActionBar(findViewById(R.id.toolbar))

        val navController: NavController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration =
            AppBarConfiguration.Builder(
                R.id.device_list_fragment,
                R.id.employee_list_fragment,
                R.id.device_allotted_list_fragment
            )
                .setDrawerLayout(drawerLayout)
                .build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setupNavigationDrawer() {
        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout))
            .apply {
                setStatusBarBackground(R.color.colorPrimaryDark)
            }
    }
}

// Keys for navigation
const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3