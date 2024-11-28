package com.example.storeapp.ui.screens.categoriesdetail

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.storeapp.R
import com.example.storeapp.domain.models.Product
import com.example.storeapp.ui.theme.colorRating

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesDetailScreen(
    title: String,
    vm: CategoryDetailViewModel,
    onBackPressed: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        vm.loadProducts(title)
    }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text(text = title.toUpperCase(Locale.current)) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        val state = vm.state
        if (state.loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = paddingValues
        ) {
            items(state.productList) {
                ItemCardProduct(product = it)
            }
        }
    }
}


@Composable
fun ItemCardProduct(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
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
        Text(
            text = "US$ ${product.price}",
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold),
        )
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
                Icon(imageVector = Icons.Rounded.Star,
                    tint = colorRating,
                    contentDescription = stringResource(R.string.star),
                    modifier = Modifier
                        .fillMaxHeight())
                Text(text = product.rating.rate.toString(),
                    modifier = Modifier
                        .fillMaxHeight(),
                )
                Text(text = "|", modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, end = 8.dp))
                Text(text = "${product.rating.count}+ sold",
                    modifier = Modifier
                        .fillMaxHeight(),
                )
        }
    }
}



