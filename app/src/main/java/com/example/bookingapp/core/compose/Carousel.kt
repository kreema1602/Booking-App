package com.example.bookingapp.core.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookingapp.R
import com.example.bookingapp.core.ui.theme.OrangePrimary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    itemList: List<String>,
    pageCount: Int = itemList.size,
    pagerState: PagerState = rememberPagerState(pageCount = { itemList.size }),
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = OrangePrimary,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
    ) {
        HorizontalPager(
            state = pagerState,
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
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp),
        contentAlignment = Alignment.Center
    ) {
        val imgUrl = if (imageUrl.contains("http")) imageUrl else imageUrl.toInt()
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
            error = painterResource(id = R.drawable.placeholder),
            placeholder = painterResource(id = R.drawable.placeholder)
        )
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

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditCarousel(
    initialItems: List<String>
): List<String> {
    var itemList by remember { mutableStateOf(initialItems.toMutableList()) }
    var pageCount by remember { mutableIntStateOf(itemList.size) }
    val pagerState = rememberPagerState(pageCount = { pageCount })

    fun onAdd(): MutableList<String> {
        val newList = if (itemList.size == 1 && itemList[0] == R.drawable.placeholder.toString()) {
            mutableListOf(R.drawable.hotel3.toString())
        } else {
            itemList.toMutableList().apply {
                add(R.drawable.hotel3.toString())
            }
        }
        pageCount = newList.size
        return newList
    }

    fun onRemove(): MutableList<String> {
        val index = pagerState.currentPage
        itemList.removeAt(index)
        pageCount = itemList.size

        if (itemList.size == 0) {
            itemList.add(R.drawable.placeholder.toString())
            pageCount = itemList.size
        }

        return itemList.toMutableList()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = OrangePrimary,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
        ) {
            HorizontalPager(
                state = pagerState,
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            FilledClipButton(
                text = "Add",
                onClick = { itemList = onAdd() },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            FilledClipButton(
                text = "Remove",
                onClick = { itemList = onRemove() },
                color = Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
    }

    return itemList
}

