package com.example.jotitdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jotitdown.database.Note
import com.example.jotitdown.database.NoteDatabase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){

    private var layoutManager: RecyclerView.LayoutManager? = null
    private val adapter: RecyclerAdapter = RecyclerAdapter(mutableListOf())
    val db by lazy { NoteDatabase.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter

        var notes = mutableListOf<Note>()
        lifecycleScope.launch(IO) {
            notes = db.noteDao().getAll().toMutableList()
            Log.v("notes", notes.toString())
            adapter.changeDataSet(notes)
        }

    }
}