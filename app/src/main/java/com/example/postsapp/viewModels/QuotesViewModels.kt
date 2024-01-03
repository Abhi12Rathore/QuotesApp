package com.example.postsapp.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.models.QuotesListItem
import com.example.postsapp.repository.AuthorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class QuotesViewModels @Inject constructor(
    private val repository: AuthorRepository, private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val quotes: StateFlow<List<QuotesListItem>>
        get() = repository.quotesList


    init {
        viewModelScope.launch {
            val authorName=savedStateHandle.get<String>("authorName")
            authorName?.let { repository.getQuotes(it) }

        }

    }

}