package com.example.jotitdown

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jotitdown.database.NoteDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(){
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.theme_button, menu)
        return true
    }
    var isNightModeOn: Boolean = false;



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.themeButton) {
            //process your onClick here
            Log.d("abcdef", id.toString());

            if(isNightModeOn){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                isNightModeOn = true
            }

            Log.d(isNightModeOn.toString(), id.toString());
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private var layoutManager: RecyclerView.LayoutManager? = null
    private val adapter: RecyclerAdapter = RecyclerAdapter(mutableListOf())
    val db by lazy { NoteDatabase.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

//        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//
//        layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//
//        recyclerView.adapter = adapter
//        var app = this
//        var notes = mutableListOf<Note>()
//        lifecycleScope.launch(IO) {
//            notes = db.noteDao().getAll().toMutableList()
//
//            adapter.changeDataSet(notes)
//
//        }
//        adapter.db = db
//
//        adapter.delFunc = fun(note) {
//            lifecycleScope.launch(IO) {
//                    db.noteDao().delete(note)
//
//            }
//        }
//        Toast.makeText(app, "Notes Loaded from Database", Toast.LENGTH_SHORT).show()

    }
}