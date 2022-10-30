package com.example.jotitdown

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jotitdown.database.Note
import com.example.jotitdown.database.NoteDatabase

class RecyclerAdapter(var notes: MutableList<Note>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = notes[position].title
        holder.itemImage.setImageResource(R.drawable.howardlogo)
    }
    fun changeDataSet(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return notes.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
        }

    }
}