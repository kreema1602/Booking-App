package com.example.bookingapp.core.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookingapp.core.ui.theme.OrangePrimary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    itemList: List<Int>,
    pageCount: Int = itemList.size,
    pagerState: PagerState = rememberPagerState(pageCount = { itemList.size }),
) {
    Box {
        HorizontalPager(
            state = pagerState, contentPadding = PaddingValues(horizontal = 4.dp),
            pageSpacing = 16.dp
        ) { page ->
            CarouselItem(itemList[page])
        }

        DotIndicators(
            pageCount = pageCount,
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(16.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun CarouselItem(
    item: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Gray)
    ) {
        Image(painter = painterResource(id = item), contentDescription = null)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DotIndicators(
    pageCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val selectedColor = OrangePrimary
    val unselectedColor = Color.Gray
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) selectedColor else unselectedColor
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewCarousel() {
    Carousel(
        itemList = listOf(
            com.example.bookingapp.R.drawable.hotel3,
            com.example.bookingapp.R.drawable.hotel2
        )
    )
}

