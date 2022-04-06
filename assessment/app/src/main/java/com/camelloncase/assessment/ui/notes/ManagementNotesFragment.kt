package com.camelloncase.assessment.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.assessment.R
import com.camelloncase.assessment.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ManagementNotesFragment : Fragment() {

    lateinit var viewModel: NoteViewModel
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_management_notes, container, false)
    }


}