package com.example.unihub.uniclub.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.unihub.R
import com.example.unihub.uniclub.navigation.model.Screen
import com.example.unihub.uniclub.presentation.home.components.HomeSection
import com.example.unihub.uniclub.presentation.home.components.TabCategory
import com.example.vibestore.ui.component.ImageSlider
import com.example.vibestore.ui.theme.poppinsFontFamily
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel(),
) {
//    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = it.visuals.message,
                        fontFamily = poppinsFontFamily
                    )
                }
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "",
                    )
                },
                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            text = "Vibe",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    top = 8.dp
                                ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Store",
                            fontFamily = poppinsFontFamily,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(
                                    start = 4.dp,
                                    top = 8.dp
                                ),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(16.dp))
                    BadgedBox(
                        badge = {
                            if (true){
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White,
                                    modifier = Modifier
                                        .offset(
                                            x = (-20).dp,
                                            y = 7.dp
                                        )
                                ) {
                                    Text(text = "2")
                                }
                            }
                        }) {
                        Icon(
                            painter = painterResource(R.drawable.icon_cart_outlined),
                            contentDescription = "Cart",
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .clickable {
                                    navController.navigate(Screen.MyCart.route)
                                }
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeSection(
                title = "",
                content = { ImageSlider() },
                navigateToSeeAll = {}
            )

            HomeSection(
                title = stringResource(R.string.categories),
                content = {
                    TabCategory(
                      snackbarHostState = snackbarHostState,
                    )
                },
                navigateToSeeAll = {
//                    navController.navigate(Screen.Categories.route)
                }
            )
        }
    }


}


@Composable
@PreviewLightDark
fun HomeScreenPreview() {
    HomeScreen(navController = NavHostController(LocalContext.current))
}