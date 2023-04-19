package com.alya.kotlin_6_month.presentation.ui.fragments.addnote
import android.os.Bundle
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
import com.alya.kotlin_6_month.presentation.ui.base.BaseFragment


@AndroidEntryPoint
class AddNotesFragment : BaseFragment(R.layout.fragment_add_notes) {

    private var _binding: FragmentAddNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel : AddNotesViewModel by viewModels()
    private var note : com.alya.kotlin_6_month.domain.model.Note? = null
    private var isNull = true




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNotesBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun setupRequests() {
        initClickers()
    }
    override fun initSubscribers() {
        setupSubscribers()
        collectEditNote()
    }
    private fun getNote() {
        if (arguments?.getSerializable("key") == null) {
            note = com.alya.kotlin_6_month.domain.model.Note()
        } else {
            note = arguments?.getSerializable("key") as com.alya.kotlin_6_month.domain.model.Note
            binding.etTitle.setText(note!!.title)
            binding.etDesc.setText(note!!.description)
            binding.btnSave.text = "Update"
            isNull = false
        }
    }


    private fun initClickers() {
        with(binding) {
            btnSave.setOnClickListener {
                if (binding.etTitle.text.isNotEmpty() && binding.etDesc.text.isNotEmpty()) {
                    note?.title = etTitle.text.toString()
                    note?.description = etDesc.text.toString()
                    if (isNull) {
                        viewModel.createNotes(note!!)
                    } else {
                        viewModel.editNote(note!!)
                    }
                }
            }
        }

    }
    override fun setupSubscribers() {
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
                            findNavController().navigateUp()

                        }

                        }
                    }
                }
            }
        }

    override fun initialize() {
        getNote()
    }
    private fun collectEditNote(){
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
                            findNavController().navigateUp()

                        }
                    }
                }
            }
        }
    }
}
