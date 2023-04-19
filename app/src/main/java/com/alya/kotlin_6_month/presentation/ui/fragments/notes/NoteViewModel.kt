package com.alya.kotlin_6_month.presentation.ui.fragments.notes

import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.usecases.DeleteNoteUseCase
import com.alya.kotlin_6_month.domain.usecases.GetAllNotesUseCase
import com.alya.kotlin_6_month.presentation.ui.base.BaseViewModel
import com.alya.kotlin_6_month.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotesUseCase: com.alya.kotlin_6_month.domain.usecases.GetAllNotesUseCase,
    private val deleteNoteUseCase: com.alya.kotlin_6_month.domain.usecases.DeleteNoteUseCase
) : BaseViewModel() {
    private val _getAllNotesState =
        MutableStateFlow<UIState<List<com.alya.kotlin_6_month.domain.model.Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()
    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNotesState = _deleteNoteState.asStateFlow()


    fun deleteNote(note: com.alya.kotlin_6_month.domain.model.Note) {
        deleteNoteUseCase(note).collectData(_deleteNoteState)
    }

    fun getAllNotes() {
        getAllNotesUseCase().collectData(_getAllNotesState)
    }
}
