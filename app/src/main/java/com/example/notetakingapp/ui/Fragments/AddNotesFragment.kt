package com.example.notetakingapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.FragmentAddNotesBinding


class AddNotesFragment : Fragment() {


    lateinit var binding: FragmentAddNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNotesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}