package com.example.jotitdown

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jotitdown.database.Note
import com.example.jotitdown.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private val adapter: RecyclerAdapter = RecyclerAdapter(mutableListOf())
    val db by lazy { NoteDatabase.getInstance(requireActivity().applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
        var app = view.context
        var notes = mutableListOf<Note>()
        lifecycleScope.launch(Dispatchers.IO) {
            notes = db.noteDao().getAll().toMutableList()
/*            db.noteDao().insertAll(
                Note(0,"Chemistry Notes", "", ""),

                Note(0,"Physics Notes", "", ""),
                Note(0,"Math Notes", "", ""),
                Note(0,"Biology Notes", "", ""),
                Note(0,"Computer Science Notes", "", ""),
            )*/

            adapter.changeDataSet(notes)

        }
        adapter.db = db

        adapter.delFunc = fun(note) {
            lifecycleScope.launch(Dispatchers.IO) {
                db.noteDao().delete(note)

            }
        }
        Toast.makeText(app, "Notes Loaded from Database", Toast.LENGTH_SHORT).show()


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}