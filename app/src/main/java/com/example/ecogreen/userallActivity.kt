
package com.example.ecogreen;

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class userallActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var items: Button
    lateinit var ideas: Button
    lateinit var centres: Button
    lateinit var shops: Button

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_userall)

        items = findViewById(R.id.items)
        shops = findViewById(R.id.shops);
        centres = findViewById(R.id.centres);
        ideas = findViewById(R.id.ideas);

        val toolbar: Toolbar= findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);


        drawer= findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //go to shops if shops button is clicked
        shops.setOnClickListener{
            val intent = Intent(this@userallActivity, shopsActivity::class.java)
            startActivity(intent)
        }

        //go to items if items button is clicked
        items.setOnClickListener{
            val intent = Intent(this@userallActivity, allActivity::class.java)
            startActivity(intent)
        }

        //go to ideas if ideas button is clicked
        ideas.setOnClickListener{
            val intent = Intent(this@userallActivity, ideasActivity::class.java)
            startActivity(intent)
        }

        //go to centres if centres button is clicked
        centres.setOnClickListener{
            val intent = Intent(this@userallActivity, centresActivity::class.java)
            startActivity(intent)
        }




    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_item_one -> {
                val intent = Intent(this, postideaActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_two -> {
                val intent = Intent(this, addcentreActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_item_four -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }
}

/*

 */