package com.alya.kotlin_6_month.presentation.ui.fragments.notes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
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
import com.alya.kotlin_6_month.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class NoteFragment : BaseFragment(R.layout.fragment_note) {
    private var _binding: FragmentNoteBinding? = null
    private val adapterNotes by lazy { NotesAdapter(this::onClick, this::onLongClick) }
    private val viewModel: NoteViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        binding.recyclerView.adapter = adapterNotes
    }


    override fun setupRequests() {
        viewModel.getAllNotes()
    }

    override fun setupSubscribers() {
        viewModel.getAllNotesState.collectUIState(state = {
            binding.progressCircular.isVisible = it is UIState.Loading
        }, onSuccess = {
            adapterNotes.submitList(it)
        })
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.deleteNotesState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                        }
                        is UIState.Success -> {
                            setupRequests()

                        }
                    }
                }
            }
        }
    }

    override fun setupListeners() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.addNotesFragment)
        }
    }

    private fun onClick(note: Note) {
        findNavController().navigate(R.id.addNotesFragment, bundleOf("key" to Note))
    }

    private fun onLongClick(note: Note) {
        AlertDialog.Builder(context).setTitle("Are you want to delete ${note.title}?")
            .setMessage("Are you sure you want to delete it?")
            .setNegativeButton("NO") { dialog, which ->

            }.setPositiveButton("YES") { dialog, which ->
                viewModel.deleteNote(note)

            }
    }


}