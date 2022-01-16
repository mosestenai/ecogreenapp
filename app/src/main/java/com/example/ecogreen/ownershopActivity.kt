package com.example.ecogreen;


import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ecogreen.api.AbstractAPIListener
import com.google.android.material.navigation.NavigationView

class ownershopActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    lateinit var shop: TextView
    lateinit var description: TextView
    lateinit var paybill: TextView
    lateinit var location: TextView
    lateinit var phone: TextView



    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ownershop)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Fetching items...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val test = intent
        val usernamee = test.getStringExtra("username")



        shop = findViewById(R.id.shop);
        description = findViewById(R.id.description);
        paybill = findViewById(R.id.paybill);
        location = findViewById(R.id.location);
        phone = findViewById(R.id.phone);


       // Toast.makeText(this,usernamee,Toast.LENGTH_LONG).show()

        val model = Model.getInstance(this.application)
        model.getshop(usernamee, object : AbstractAPIListener() {
            override fun onGetshop(user: User) {
                model.user = user
                shop.text = user.shop
                paybill.text = user.paybill
                phone.text = user.phone
                description.text = user.description
                location.text = user.location
                progressDialog.dismiss()

            }
        })


        val toolbar: Toolbar= findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);


        drawer= findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)




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
        val test = intent
        val username = test.getStringExtra("username")

        when(item.itemId){
            R.id.nav_item_two -> {
                val intent = Intent(this, shopitems::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
            R.id.nav_item_three -> {
                val intent = Intent(this, transactions::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
            R.id.nav_item_four -> {
                val intent = Intent(this, additemActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
            R.id.nav_item_five -> {
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