package com.example.jotitdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.jotitdown.database.Note
import com.example.jotitdown.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    val db by lazy { NoteDatabase.getInstance(requireActivity().applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    var bundle: Bundle? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bundle = this.arguments
        if (bundle != null) {
            note.uid = bundle!!.getInt("uid")
            note.title = bundle!!.getString("title") ?: ""
            note.content = bundle!!.getString("content")?: ""
            note.image = bundle!!.getString("image")?: ""
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }
    lateinit var t1: EditText
    lateinit var t2: EditText
    lateinit var b1: Button
    var note = Note(0, "", "", "")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        t1 = view.findViewById(R.id.titleinput)
        t2 = view.findViewById(R.id.descriptioninput)

        b1 = view.findViewById(R.id.buttonIn)

        t1.setText(note.title)
        t2.setText(note.content)
        b1.setOnClickListener {
            var app = view.context

            lifecycleScope.launch(Dispatchers.IO) {
                note.title = t1.text.toString()
                note.content = t2.text.toString()
                if (note.uid == 0) {
                    db.noteDao().insertAll(note)

                } else {
                    db.noteDao().updateAll(note)
                }


            }

            Toast.makeText(app, "Your Note has been saved: " + note.title, Toast.LENGTH_SHORT).show()
        }
    }

    fun saveNote() {

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {

            }
    }
}