package com.alya.kotlin_6_month.presentation.ui.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alya.kotlin_6_month.R
import com.alya.kotlin_6_month.databinding.FragmentNoteBinding
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.presentation.ui.fragments.notes.adapter.NotesAdapter
import com.alya.kotlin_6_month.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class NoteFragment : Fragment(), NotesAdapter.Listener {
    private var _binding: FragmentNoteBinding? = null
    private lateinit var adapter: NotesAdapter
    private val viewModel : NoteViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NotesAdapter(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequests()
        setupSubscribers()
        initListeners()

    }
    private fun initialize() {

    }
    private fun setupRequests() {
        viewModel.getAllNotes()
    }
    private fun setupSubscribers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getAllNotesState.collect{state ->
                   when(state){
                       is UIState.Empty -> {}
                       is UIState.Error -> {
                           Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                       }
                       is UIState.Loading -> {
                       }
                       is UIState.Success -> {
                           binding.recyclerView.adapter = adapter
                           val newdata = arrayListOf<Note>()
                           adapter.addTasks(newdata)

                           }
                   }
                }
            }
        }
    }
    @Suppress("DEPRECATION")
    private fun initListeners() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.addNotesFragment, bundleOf("key" to Note))
            //requireArguments().getSerializable("key")
        }
    }
    override fun onTaskDeleteClickListener(note: Note, position: Int) {
    }

    override fun onClick(note: Note) {

    }


}