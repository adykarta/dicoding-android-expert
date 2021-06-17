package com.dicoding.film.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.film.R
import com.dicoding.film.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return super.onCreateOptionsMenu(menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fav) {
            val uri = Uri.parse("film://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }


}