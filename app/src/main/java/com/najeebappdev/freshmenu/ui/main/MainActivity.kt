package com.najeebappdev.freshmenu.ui.main

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.najeebappdev.freshmenu.ui.main.adapter.CategoriesAdapter
import com.najeebappdev.freshmenu.R
import com.najeebappdev.freshmenu.ui.main.adapter.SliderAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nav_view)

        // Set toolbar as support action bar
        setSupportActionBar(toolbar)

        // Create ActionBarDrawerToggle and set it as the DrawerListener
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item1 -> {
                    Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_item2 -> {
                    Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                }
                // Add more cases for other menu items if needed
            }
            drawerLayout.closeDrawers()
            true
        }

        viewModel = ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)

//        // Fetch data from view model
//        viewModel.getSliderImages()
//        viewModel.getFoodCategories()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val appBarLayout = findViewById<AppBarLayout>(R.id.appbar)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                appBarLayout.setExpanded(false, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing
            }
        })

        viewModel.sliderImages.observe(this) { sliderImages ->
            sliderAdapter.submitList(sliderImages)
        }

        viewModel.foodCategories.observe(this) { foodCategories ->
            recyclerView.adapter = CategoriesAdapter(this, foodCategories)

            // Populate tabs
            tabLayout.removeAllTabs()
            for (category in foodCategories) {
                tabLayout.addTab(tabLayout.newTab().setText(category.name))
            }

            // Sync RecyclerView with TabLayout
            recyclerView.post {
                TabbedListMediator(
                    recyclerView,
                    tabLayout,
                    foodCategories.indices.toList()
                ).attach()

                // Programmatically trigger an initial scroll
                recyclerView.scrollToPosition(0)
            }
        }

        viewModel.error.observe(this) { errorMessage ->
            // Handle error messages
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    // Override onOptionsItemSelected to handle options item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        tabLayout = findViewById(R.id.tabLayout)
        recyclerView = findViewById(R.id.recyclerView)
        viewPager = findViewById(R.id.viewPager)

        sliderAdapter = SliderAdapter()
        viewPager.adapter = sliderAdapter

        viewPager.clipToPadding = false
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val paddingToSet = width / 12 // Set this ratio according to how much of the next and previous screen you want to show

        viewPager.setPadding(paddingToSet, 0, paddingToSet, 0)
    }

    // Override onBackPressed to close drawer if it's open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView)
        } else {
            super.onBackPressed()
        }
    }
}