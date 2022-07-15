package com.example.notetakingapp.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.FragmentHomeBinding
import com.example.notetakingapp.model.Notes
import com.example.notetakingapp.ui.Adapter.NotesAdapter
import com.example.notetakingapp.viewmodel.NotesViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var myNotes  = arrayListOf<Notes>()
    lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addNotesFragment)
        }
        viewModel.getNotes().observe(viewLifecycleOwner,{
            notesList ->
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                notesAdapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = notesAdapter
        })

        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner,{
                    notesList ->
                myNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                notesAdapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = notesAdapter
            })
        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner,{
                    notesList ->
                myNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                notesAdapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = notesAdapter
            })
        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{
                    notesList ->
                myNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner,{
                    notesList ->
                myNotes = notesList as ArrayList<Notes>
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes here... "
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)

    }

    private fun NotesFiltering(p0 : String?) {
        val newFilteredList = arrayListOf<Notes>()
        for(i in myNotes){
            if(i.title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
    }


}