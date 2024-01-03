package com.example.postsapp.repository

import com.example.postsapp.api.ApiInterface
import com.example.postsapp.models.QuotesListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthorRepository @Inject constructor(private val apiInterface: ApiInterface) {
    private val _authorList = MutableStateFlow<List<String>>(emptyList())
    val authorList: StateFlow<List<String>>
        get() = _authorList


    private val _quotesList = MutableStateFlow<List<QuotesListItem>>(emptyList())
    val quotesList: StateFlow<List<QuotesListItem>>
        get() = _quotesList

    suspend fun getAuthorNames() {
        val response = apiInterface.getAuthorsList("quotes..author");

        if(response.isSuccessful && response.body()!=null){
            _authorList.emit(response.body()?: listOf());
        }
    }

    suspend fun getQuotes(authorName:String) {
        val response = apiInterface.getQuotes("quotes[?(@.author==\"$authorName\")]");

        if(response.isSuccessful && response.body()!=null){
            _quotesList.emit(response.body()?: listOf());
        }
    }
}