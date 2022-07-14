package com.example.notetakingapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.FragmentEditNotesBinding
import com.example.notetakingapp.model.Notes
import com.example.notetakingapp.viewmodel.NotesViewModel
import java.util.*


class EditNotesFragment : Fragment() {


    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    val viewModel : NotesViewModel by viewModels()
    var priority : String = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        binding.editTitle.setText(oldNotes.notesData.title)
        binding.editSubTitle.setText(oldNotes.notesData.subTitle)
        binding.editNotes.setText(oldNotes.notesData.notes)

        when(oldNotes.notesData.priority){
            "1" -> {
                priority = "1"
                binding.pred.setImageResource(R.drawable.ic_done_btn)
                binding.pgreen.setImageResource(0)
                binding.pyellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pyellow.setImageResource(R.drawable.ic_done_btn)
                binding.pgreen.setImageResource(0)
                binding.pred.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pgreen.setImageResource(R.drawable.ic_done_btn)
                binding.pred.setImageResource(0)
                binding.pyellow.setImageResource(0)
            }
        }


        //for editing text priority
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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()

        //getting current date
        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy ",d.time)
        val notesData = Notes(
            oldNotes.notesData.id,
            title = title,
            subTitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority = priority
        )
        viewModel.updateNote(notesData)
        Toast.makeText(this.context,"Note updated successfully ", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }


}