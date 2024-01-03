package com.example.postsapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.repository.AuthorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewModels @Inject constructor(private val repository: AuthorRepository) : ViewModel() {

    val authors:StateFlow<List<String>>
        get()=repository.authorList


    init {
        viewModelScope.launch {
            repository.getAuthorNames()
        }

    }

}