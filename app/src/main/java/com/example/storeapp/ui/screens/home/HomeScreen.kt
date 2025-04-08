package com.example.storeapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.storeapp.R
import com.example.storeapp.Result
import com.example.domain.models.Category
import com.example.storeapp.ui.screens.AppBarScreen
import com.example.storeapp.ui.screens.LoadingCircularIndicator
import com.example.storeapp.ui.screens.home.models.CategoryModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onClick: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            AppBarScreen(
                title = stringResource(id = R.string.categories),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        val state by homeViewModel.state.collectAsState()
        when(state){
            is Result.Error ->{
                val errorState = state as Result.Error
                Text(text = errorState.exception.message.toString(),
                    color = Color.Red)
            }
            Result.Loading -> {
                LoadingCircularIndicator(
                    modifier = Modifier
                        .padding(paddingValues)
                )
            }
            is Result.Success -> {
                val successState = state as Result.Success
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = paddingValues
                ) {
                    items(successState.data) { category->
                        ItemCardCategory(category = category, onClick = { onClick(category.title) })
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCardCategory(
    category: CategoryModel,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(CornerSize(16.dp))
    ) {
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                CategoryImage(category.image)
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = category.title.toUpperCase(Locale.current),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun CategoryImage(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = "",
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(CornerSize(8.dp)))
    )
}