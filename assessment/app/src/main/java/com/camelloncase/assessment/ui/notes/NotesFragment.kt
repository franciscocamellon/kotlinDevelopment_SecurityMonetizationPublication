package com.camelloncase.assessment.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camelloncase.assessment.adapter.NoteClickDeleteInterface
import com.camelloncase.assessment.adapter.NoteClickInterface
import com.camelloncase.assessment.adapter.NoteRVAdapter
import com.camelloncase.assessment.databinding.FragmentNotesBinding
import com.camelloncase.assessment.model.Note
import com.camelloncase.assessment.util.showMessageToUser
import com.camelloncase.assessment.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NotesFragment : Fragment(), NoteClickInterface, NoteClickDeleteInterface {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: NoteViewModel
    private lateinit var notesRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]

        notesRV = binding.notesRV
        addFAB = binding.idFAB

        notesRV.layoutManager = LinearLayoutManager(context)
        val noteRVAdapter = NoteRVAdapter(requireContext(), this, this)
        notesRV.adapter = noteRVAdapter

        viewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            navigateToCreateScreen()
        }

        return binding.root
    }

    private fun navigateToCreateScreen() {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToManagementNotesFragment()
        )
    }

    private fun goToManagementScreen(note: Note) {

        val action = NotesFragmentDirections.actionNotesFragmentToManagementNotesFragment()
        action.actionType = "update"
        action.id = note.id
        action.noteTitle = note.noteTitle
        action.noteDescription = note.noteDescription
        action.timeStamp = note.timeStamp

        findNavController().navigate(action)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun onNoteClick(note: Note) {
        goToManagementScreen(note)
        showMessageToUser(requireContext(), "${note.noteTitle} deleted")
    }


}