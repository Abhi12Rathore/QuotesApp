package com.example.postsapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postsapp.models.QuotesListItem
import com.example.postsapp.viewModels.AuthorViewModels
import com.example.postsapp.viewModels.QuotesViewModels

@Composable
fun QuotesScreen(authorName:String){
    val quotesViewModels: QuotesViewModels = hiltViewModel()
    val authors: State<List<QuotesListItem>> =quotesViewModels.quotes.collectAsState()
    print("AuthorName------>${authorName}")

    if(authors.value.isEmpty()){
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else{
        LazyColumn(content = {
            items(authors.value){
                QuotesListItem(quotes=it)
            }

        })
    }


}

@Composable
fun QuotesListItem(quotes:QuotesListItem){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = quotes.quote,
                modifier = Modifier.padding(20.dp), style = MaterialTheme.typography.body2
            )
        }
    )

}