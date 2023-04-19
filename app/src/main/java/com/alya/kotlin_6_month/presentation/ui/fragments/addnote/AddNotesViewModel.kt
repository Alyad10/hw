package com.alya.kotlin_6_month.presentation.ui.fragments.addnote
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.usecases.CreateNoteUseCase
import com.alya.kotlin_6_month.domain.usecases.EditNoteUseCase
import com.alya.kotlin_6_month.presentation.ui.base.BaseViewModel
import com.alya.kotlin_6_month.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AddNotesViewModel @Inject constructor(
    private val createNoteUseCase : com.alya.kotlin_6_month.domain.usecases.CreateNoteUseCase,
    private val editNoteUseCase: com.alya.kotlin_6_month.domain.usecases.EditNoteUseCase
) :BaseViewModel() {
    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()
    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNotes(note: com.alya.kotlin_6_month.domain.model.Note) {
        createNoteUseCase(note).collectData(_createNoteState)
    }

    fun editNote(note: com.alya.kotlin_6_month.domain.model.Note) {
        editNoteUseCase(note).collectData(_editNoteState)
    }
}



