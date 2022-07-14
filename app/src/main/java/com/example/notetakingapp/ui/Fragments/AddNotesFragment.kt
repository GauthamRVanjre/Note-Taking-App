package com.example.notetakingapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.FragmentAddNotesBinding
import com.example.notetakingapp.model.Notes
import com.example.notetakingapp.viewmodel.NotesViewModel
import java.util.*


class AddNotesFragment : Fragment() {


    lateinit var binding: FragmentAddNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNotesBinding.inflate(layoutInflater,container,false)
        binding.pred.setImageResource(R.drawable.ic_done_btn)
        binding.pred.setOnClickListener {
            priority = "1"
            binding.pred.setImageResource(R.drawable.ic_done_btn)
            binding.pgreen.setImageResource(0)
            binding.pyellow.setImageResource(0)
        }
        binding.pgreen.setOnClickListener {
            priority = "3"
            binding.pgreen.setImageResource(R.drawable.ic_done_btn)
            binding.pred.setImageResource(0)
            binding.pyellow.setImageResource(0)
        }
        binding.pyellow.setOnClickListener {
            priority = "2"
            binding.pyellow.setImageResource(R.drawable.ic_done_btn)
            binding.pgreen.setImageResource(0)
            binding.pred.setImageResource(0)
        }
        binding.btnSaveNotes.setOnClickListener { 
            createNotes(it)

        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()

        //getting current date
        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy ",d.time)
        val notesData = Notes(
             null,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority = priority
        )
        viewModel.addNotes(notesData)
        Toast.makeText(this.context,"Note created successfully ",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_addNotesFragment_to_homeFragment)
    }



}