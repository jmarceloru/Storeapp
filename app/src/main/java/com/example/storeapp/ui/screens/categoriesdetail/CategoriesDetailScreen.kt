package com.example.storeapp.ui.screens.categoriesdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.storeapp.R
import com.example.storeapp.Result
import com.example.domain.models.Product
import com.example.storeapp.ui.screens.AppBarScreenWithIcon
import com.example.storeapp.ui.screens.LoadingCircularIndicator
import com.example.storeapp.ui.theme.colorRating

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesDetailScreen(
    title: String,
    vm: CategoryDetailViewModel,
    onBackPressed: () -> Unit,
    onClic: (Product) -> Unit
) {
    val categoryDetailState = rememberCategoryDetailState()
    categoryDetailState.ShowScreen {
        vm.loadProducts(title)
    }
    Scaffold(
        topBar = {
            AppBarScreenWithIcon(
                title = title,
                scrollBehavior = categoryDetailState.scrollBehavior,
                onBackPressed
            )
        },
        modifier = Modifier.nestedScroll(categoryDetailState.scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        val state by vm.state.collectAsState()
        when(state){
            is Result.Error -> {
                val errorState = state as Result.Error
                Text(text = errorState.exception.message.toString(),
                    color = Color.Red,
                    modifier = Modifier.padding(paddingValues))
            }
            Result.Loading -> LoadingCircularIndicator(modifier = Modifier.padding(paddingValues))
            is Result.Success -> {
                val successState = state as Result.Success
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = paddingValues
                ) {
                    items(successState.data) { p ->
                        ItemCardProduct(product = p, onClic = { onClic(p) })
                    }
                }
            }
        }
    }
}


@Composable
fun ItemCardProduct(product: Product, onClic: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable(onClick = onClic),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(CornerSize(16.dp))
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
        }
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround) {
            Text(
                text = "US$ ${product.price}",
                modifier = Modifier
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold),
            )
            Icon(
                imageVector = if (product.favorite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = stringResource(R.string.star),
            )
        }
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Rounded.Star,
                tint = colorRating,
                contentDescription = stringResource(R.string.star),
                modifier = Modifier
                    .fillMaxHeight()
            )
            Text(
                text = product.rating.rate.toString(),
                modifier = Modifier
                    .fillMaxHeight(),
            )
            Text(
                text = "|", modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, end = 8.dp)
            )
            Text(
                text = "${product.rating.count}+ sold",
                modifier = Modifier
                    .fillMaxHeight(),
            )
        }
    }
}



