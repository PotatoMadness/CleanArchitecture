package com.example.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint

enum class Pages(
    @ColorRes val colorResId: Int,
) {
    FIRST(R.color.purple_500),
    SECOND(R.color.purple_200),
    THIRD(R.color.teal_700)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    pages: Array<Pages> = Pages.values()) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    HorizontalPager(state = pagerState) {page ->
        Row(modifier = Modifier.fillMaxSize().background(Color(pages[page].colorResId))) {}
    }
//    Text(text = "main")
}