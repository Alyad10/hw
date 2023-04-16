package com.alya.kotlin_6_month.presentation.ui.fragments.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alya.kotlin_6_month.domain.model.Note
import com.alya.kotlin_6_month.domain.usecases.DeleteNoteUseCase
import com.alya.kotlin_6_month.domain.usecases.GetAllNotesUseCase
import com.alya.kotlin_6_month.domain.utils.Resource
import com.alya.kotlin_6_month.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _getAllNotesState =
        MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    fun getAllNotes(){
        viewModelScope.launch {
            getAllNotesUseCase.getAllNotes().collect{res ->
                when(res){
                    is Resource.Error ->{
                         _getAllNotesState.value = UIState.Error(res.message!!)}
                    is Resource.Loading ->{
                        _getAllNotesState.value = UIState.Loading()}
                    is Resource.Success -> {
                        if (res.data != null){
                            _getAllNotesState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
}