package com.azharkova.test_kmm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.azharkova.test_kmm.data.NewsItem
import com.azharkova.test_kmm.di.KoinDIFactory
import com.azharkova.test_kmm.di.resolve
import com.azharkova.test_kmm.network.Coder
import com.azharkova.test_kmm.network.NetworkClient
import com.azharkova.test_kmm.service.NewsService
import com.azharkova.test_kmm.ui.NewsItemDataView
import com.azharkova.test_kmm.ui.NewsListScreen
import com.azharkova.test_kmm.usecase.NewsUseCase
import com.azharkova.test_kmm.viewmodel.NewsItemVM
import com.azharkova.test_kmm.viewmodel.NewsViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

enum class NewsScreen {
    NewsList,
    NewsItem
}

@Composable
fun AppBar(
    currentScreen: NewsScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { currentScreen.name },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        })
}


        @OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        val vm: NewsViewModel = KoinDIFactory.resolve(NewsViewModel::class)!!
        val backStackEntry by navigator.currentEntry.collectAsState(null)
        // Get the name of the current screen
        val currentScreen = NewsScreen.valueOf(
            backStackEntry?.route?.route ?: NewsScreen.NewsList.name
        )
        val canNavigateBack by navigator.canGoBack.collectAsState(false)
        Scaffold(
            topBar = {
                AppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navigator.goBack() }
                )
            }
        ) { paddingValues ->

            NavHost(
                navigator = navigator,
                navTransition = NavTransition(),
                // The start destination
                initialRoute = NewsScreen.NewsList.name
            ) {
                scene(
                    route = NewsScreen.NewsList.name,
                    navTransition = NavTransition()
                ) {
                    NewsListScreen(vm) { item ->
                        Coder.save("data", item)

                        navigator.navigate(NewsScreen.NewsItem.name)
                    }
                }
                scene(route = NewsScreen.NewsItem.name) {
                    Coder.get<NewsItem>("data")?.let { item ->
                        NewsItemDataView(NewsItemVM(item))
                    }
                }
            }
        }
    }
    }
