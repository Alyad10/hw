package com.alya.kotlin_6_month.presentation.ui.fragments.addnote

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alya.kotlin_6_month.R
import com.alya.kotlin_6_month.databinding.FragmentAddNotesBinding
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController


@AndroidEntryPoint
class AddNotesFragment : Fragment() {

    private var _binding: FragmentAddNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : AddNotesViewModel by viewModels()
    private var note : Note? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNotesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequests()
        setupSubscribers()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnSave.setOnClickListener {
                if (binding.etTitle.text.isNotEmpty() && binding.etDesc.text.isNotEmpty()) {
                    note?.title = etTitle.text.toString()
                    note?.description = etDesc.text.toString()
                    if (note == null) {
                        viewModel.createNotes(note!!)
                    } else {
                        viewModel.editNote(note!!)
                    }
                }
            }
        }

    }

    private fun setupRequests() {
        note?.let { viewModel.createNotes(it) }
        note?.let { viewModel.editNote(it) }
        findNavController().navigateUp()


    }


    private fun setupSubscribers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.createNoteState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {


                        }
                        is UIState.Success -> {
                            binding.etTitle.text.toString()
                            binding.etDesc.text.toString()

                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editNoteState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        is UIState.Loading -> {
                        }
                        is UIState.Success -> {
                            binding.etTitle.text.toString()
                            binding.etDesc.text.toString()

                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initialize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            note = arguments?.getSerializable("key") as Note?
            if (note == null) {
                binding.btnSave.text = getString(R.string.save)
            } else {
                note = arguments?.getSerializable("key", Note::class.java)
                binding.etTitle.setText(note!!.title)
                binding.etDesc.setText(note!!.description)
                binding.btnSave.text = "Edit"
            }
        }

    }

}