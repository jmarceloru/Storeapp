package com.example.storeapp.ui.screens.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.storeapp.R
import com.example.domain.models.Result
import com.example.storeapp.ui.screens.AppBarScreenWithIcon
import com.example.storeapp.ui.screens.LoadingCircularIndicator
import com.example.storeapp.ui.theme.colorRating
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    idProduct: Int,
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val productDetailState = rememberProductDetailState()
    productDetailState.FetchProductData {
        viewModel.fetchProductById(idProduct)
    }
    Scaffold(
        topBar = {
            AppBarScreenWithIcon(
                title = "PRODUCT",
                scrollBehavior = productDetailState.scrollBehavior,
                onBackPressed = onBackPressed
            )
        },
        modifier = Modifier.nestedScroll(productDetailState.scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onFavoriteClick() }) {
                Icon(
                    imageVector = if (state is Result.Success){
                        val succesState = state as Result.Success
                        if (succesState.data.favorite){
                            Icons.Default.Favorite
                        }else{
                            Icons.Default.FavoriteBorder
                        }
                    }else{
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = stringResource(id = R.string.cart)
                )
            }
        }
    ) { paddingValues ->
        when (state) {
            is Result.Error -> {
                val errorState = state as Result.Error
                Text(
                    text = errorState.exception.message.toString(),
                    color = Color.Red,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            Result.Loading -> LoadingCircularIndicator(modifier = Modifier.padding(paddingValues))
            is Result.Success -> {
                val succesState = state as Result.Success
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = succesState.data.title,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            model = succesState.data.image, contentDescription = succesState.data.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                    }
                    Text(
                        text = "US$ ${succesState.data.price}",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "${succesState.data.rating.count}+sold")
                        Column {
                            Row {
                                Text(
                                    text = succesState.data.rating.rate.toString(),
                                )
                                var isHalfStar = (succesState.data.rating.rate.rem(1)) != 0.0
                                for (i in 1..5) {
                                    Icon(
                                        tint = colorRating,
                                        contentDescription = stringResource(R.string.star),
                                        imageVector = if (i <= succesState.data.rating.rate) {
                                            Icons.Rounded.Star
                                        } else {
                                            if (isHalfStar) {
                                                isHalfStar = false
                                                ImageVector.vectorResource(id = R.drawable.round_star_half_24)
                                            } else {
                                                ImageVector.vectorResource(id = R.drawable.outline_star_border_24)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = succesState.data.description,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

