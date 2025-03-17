package com.example.unihub.uniclub.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.unihub.R
import com.example.unihub.core.presentation.util.component.AnimatedShimmerProduct
import com.example.unihub.ui.theme.ComposeTheme
import com.example.unihub.uniclub.presentation.home.HomeAction
import com.example.unihub.uniclub.presentation.home.HomeViewModel
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun TabBrand(
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val paperState = rememberPagerState(pageCount = {
        state.brand.size
    })

    if (state.brand.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
        ) {
            Text(
                text = stringResource(R.string.no_brand)
            )
        }
        return
    }


    val coroutineScope = rememberCoroutineScope()


        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ScrollableTabRow(
                selectedTabIndex = paperState.currentPage,
                edgePadding = 16.dp,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
                divider = {}
            ) {
                state.brand.forEachIndexed { index, brand ->
                    Tab(
                        text = {
                            Text(
                                text = brand.name,
                                fontFamily = poppinsFontFamily,
                                textAlign = TextAlign.Center,

                                )
                        },
                        selected = paperState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                paperState.animateScrollToPage(index)
                            }
                        },
                    )
                }
            }

            HorizontalPager(
                state = paperState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                if (state.products.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(300.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.no_product),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(380.dp)
                    ) {
                        items(state.products) { product ->
                            if (state.isLoading) {
                                AnimatedShimmerProduct()
                            } else {
                                ProductCard(
                                    image = product.link,
                                    title = product.name,
                                    rating = "3.0",
                                    price = "$${product.price}",
                                    categories = product.categories,
                                    addToCart = {
                                        viewModel.onAction(HomeAction.AddToCart(product))

                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(
                                                message = "Added to cart",
                                                duration = SnackbarDuration.Short
                                            )
                                        }
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }

}


@Preview(showBackground = true)
@Composable
private fun TabCategoryPreview() {
    ComposeTheme {
        TabBrand(
//            navigateToDetail = {},
            snackbarHostState = SnackbarHostState(),
//            scope = rememberCoroutineScope()
        )
    }
}