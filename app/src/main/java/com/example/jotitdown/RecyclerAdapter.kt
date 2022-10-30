package com.example.jotitdown

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.jotitdown.database.Note
import com.example.jotitdown.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class RecyclerAdapter(var notes: MutableList<Note>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var db: NoteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = notes[position].title
        holder.itemImage.setImageResource(R.drawable.howardlogo)


        holder.itemView.setOnLongClickListener {


            deleteItem(position);
            Toast.makeText(it.context, "Note Deleted", Toast.LENGTH_SHORT).show()

            return@setOnLongClickListener true
        }
    }


    fun changeDataSet(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return notes.size
    }

    fun deleteItem(position: Int) {
        delFunc(notes[position])
        notes.removeAt(position)
        notifyItemRangeRemoved(position, 1)
    }
    var delFunc: (Note) -> Unit = {}
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
        }

    }
}