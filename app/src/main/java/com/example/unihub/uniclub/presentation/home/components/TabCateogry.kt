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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.unihub.uniclub.presentation.home.HomeViewModel
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun TabCategory(
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val category by remember {
        mutableStateOf(
            listOf(
                "All",
                "Men",
                "Women",
                "Electronics",
                "Jewelery",
            )
        )
    }

    val allProducts = remember {
        listOf(
            Product(
                id = "1",
                name = "Fjallraven Backpack",
                price = 109.95,
                imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
                category = "Men"
            ),
            Product(
                id = "2",
                name = "Slim Fit T-Shirts",
                price = 22.3,
                imageUrl = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
                category = "Men"
            ),
            Product(
                id = "3",
                name = "Cotton Jacket",
                price = 55.99,
                imageUrl = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
                category = "Men"
            ),

            // Women's clothing
            Product(
                id = "4",
                name = "Women's Cotton T-Shirt",
                price = 19.95,
                imageUrl = "https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_.jpg",
                category = "Women"
            ),
            Product(
                id = "5",
                name = "Women's Raincoat",
                price = 39.99,
                imageUrl = "https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2.jpg",
                category = "Women"
            ),
            Product(
                id = "6",
                name = "Women's Removable Hooded Jacket",
                price = 29.95,
                imageUrl = "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_.jpg",
                category = "Women"
            ),

            // Electronics
            Product(
                id = "7",
                name = "WD 2TB External Hard Drive",
                price = 64.00,
                imageUrl = "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg",
                category = "Electronics"
            ),
            Product(
                id = "8",
                name = "SanDisk SSD 1TB",
                price = 109.00,
                imageUrl = "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg",
                category = "Electronics"
            ),
            Product(
                id = "9",
                name = "Gaming Monitor 27-inch",
                price = 999.99,
                imageUrl = "https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_.jpg",
                category = "Electronics"
            ),

            // Jewelery
            Product(
                id = "10",
                name = "Silver Dragon Bracelet",
                price = 695.00,
                imageUrl = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg",
                category = "Jewelery"
            ),
            Product(
                id = "11",
                name = "Gold Plated Necklace",
                price = 168.00,
                imageUrl = "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg",
                category = "Jewelery"
            ),
            Product(
                id = "12",
                name = "Diamond Ring",
                price = 9999.99,
                imageUrl = "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_.jpg",
                category = "Jewelery"
            )
        )
    }


    val paperState = rememberPagerState(pageCount = {
        category.size
    })


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
            category.forEachIndexed { index, category ->
                Tab(
                    text = {
                        Text(
                            text = category,
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
            val filteredProducts: List<Product> = if (category[page] == "All") {
                allProducts
            } else {
                allProducts.filter { it.category == category[page] }
            }

            if (filteredProducts.isEmpty()) {
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
                    items(filteredProducts) { product ->
                        if (state.isLoading) {
                            AnimatedShimmerProduct()
                        } else {
                            ProductCard(
                                image = product.imageUrl,
                                title = product.name,
                                rating = "3.0",
                                price = "$${product.price}",
                                category = product.category,
                                addToCart = {
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
        TabCategory(
//            navigateToDetail = {},
            snackbarHostState = SnackbarHostState(),
//            scope = rememberCoroutineScope()
        )
    }
}