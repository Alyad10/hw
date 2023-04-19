package com.alya.kotlin_6_month.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alya.kotlin_6_month.domain.utils.Resource
import com.alya.kotlin_6_month.presentation.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected fun <T> Flow<Resource<T>>.collectData(_state: MutableStateFlow<UIState<T>>) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectData.collect { res ->
                when (res) {
                    is Resource.Error -> {
                        if (res.message != null) {
                            _state.value = UIState.Error(res.message!!)
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (res.data != null) {
                            _state.value = UIState.Success(res.data!!)
                        }
                    }
                }
            }
        }
    }
}