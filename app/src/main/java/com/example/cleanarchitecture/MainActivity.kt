package com.example.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class Pages(
    @ColorRes val colorResId: Int,
    val title: String
) {
    FIRST(R.color.teal_700, "first"),
    SECOND(R.color.purple_200, "second"),
    THIRD(R.color.purple_500, "third")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainView()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    pages: Array<Pages> = Pages.values()) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            pages.forEachIndexed { index, page ->
                val title = page.title
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = title) },
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
        HorizontalPager(modifier = Modifier.fillMaxSize(), state = pagerState) { page ->
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = pages[page].colorResId))
            ) {
                Text(pages[page].title, color = Color.White)
            }
        }
    }
}