package com.alya.kotlin_6_month.presentation.ui.fragments.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.usecases.CreateNoteUseCase
import com.alya.kotlin_6_month.domain.usecases.EditNoteUseCase
import com.alya.kotlin_6_month.domain.utils.Resource
import com.alya.kotlin_6_month.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddNotesViewModel @Inject constructor(
    private val createNoteUseCase : CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) :ViewModel() {
    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()
    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNotes(note: Note) {
        viewModelScope.launch {
            createNoteUseCase.createNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _createNoteState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _createNoteState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            _createNoteState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }

    fun editNote(note: Note) {
        viewModelScope.launch {
            editNoteUseCase.editNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _editNoteState.value = UIState.Error(res.message!!)
                    }
                    is Resource.Loading -> {
                        _editNoteState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            _editNoteState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
}




