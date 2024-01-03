package com.example.postsapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.postsapp.R
import com.example.postsapp.viewModels.AuthorViewModels
import kotlinx.coroutines.flow.collect

@Composable
fun AuthorsScreen(onClick: (authorName: String) -> Unit) {
    val authorViewModel: AuthorViewModels = hiltViewModel()
    val authors: State<List<String>> = authorViewModel.authors.collectAsState()
    val gridView = rememberSaveable() {
        mutableStateOf(false);
    }

    if (authors.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column() {
            Row(modifier = Modifier.padding(5.dp, 5.dp)) {
                Text(text = "Famous Authors", style = MaterialTheme.typography.h6)
                Spacer(Modifier.weight(1f))
                Image(
                    modifier = Modifier.clickable {
                        gridView.value = !gridView.value;
                    }, painter = if (gridView.value) {
                        painterResource(id = R.drawable.grid_icon)
                    } else painterResource(id = R.drawable.list_icon), contentDescription = ""
                )

            }

            if (gridView.value) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    items(authors.value.distinct()) {
                        AuthorGridItem(authorName = it, onClick = onClick)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(vertical = 5.dp),
                    content = {
                        items(authors.value.distinct()) {
                            AuthorItems(authorName = it, onClick = onClick)
                        }
                    })

            }

        }


    }

}

@Composable
fun AuthorItems(authorName: String, onClick: (authorName: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(authorName) }
            .padding(5.dp),
        border = BorderStroke(1.dp, Color(0xFFCCCCCC)),
        content = {
            Text(
                text = authorName,
                modifier = Modifier.padding(20.dp), style = MaterialTheme.typography.body2
            )
        }
    )

}

@Composable
fun AuthorGridItem(authorName: String, onClick: (authorName: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clickable { onClick(authorName) }
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = authorName,
            style = MaterialTheme.typography.body2.copy(fontSize = 15.sp),
            modifier = Modifier.padding(0.dp, 20.dp)
        )
    }
}