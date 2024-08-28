package com.wushang.firstcomposestudy.component.test

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.random.Random


/**
 * @author：allen.wang
 * @date：2024/8/28 15:28
 * @description：TODO
 */
@Preview
@Composable
fun generatePagerDemo1() {

    //协程
    val coroutineScope = rememberCoroutineScope()

    /**
     * 记录pager状态，提供几个属性
     * currentPage：当前页，用户滑动的时候动画未结束也更新
     * settledPage：表示上一次停留的页面索引，动画结束才更新这个值
     * targetPage：当用户或程序开始滚动到一个新的页面时，targetPage 会设置为目标页面。这是滚动动画结束时最终应达到的页面
     */
    val pagerState = rememberPagerState(pageCount = {
        10
    })

    /**
     * 同List，使用snapshotFlow来监听变化，进行其他异步操作
     */
    LaunchedEffect(pagerState) {
        // Collect from the a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Do something with each page change, for example:
            // viewModel.sendPageSelectedEvent(page)
            Log.d("Page change", "Page changed to $page")
        }
    }

    //修改手势行为
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(10)
    )


    Box(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .size(400.dp,500.dp),

            //HorizontalPager 和 VerticalPager 会占据整个宽度或 和整个高度。您可以将 pageSize 变量设置为 Fixed, Fill （默认）或自定义尺寸计算
            //pageSize = PageSize.Fixed(200.dp),
            /**
             * 若要根据视口大小调整页面大小，请使用自定义页面大小 计算。创建自定义 PageSize 对象，并将 availableSpace 除以 3，同时将间距因素考虑在内 之间
             * private val threePagesPerViewport = object : PageSize {
             *     override fun Density.calculateMainAxisPageSize(
             *         availableSpace: Int,
             *         pageSpacing: Int
             *     ): Int {
             *         return (availableSpace - 2 * pageSpacing) / 3
             *     }
             * }
             */

            //内容边距， 影响页面的最大尺寸和对齐方式，start和end相等就居中，这样设置上一张和这一张之间有距离，上下一张都会显示部分
            //contentPadding = PaddingValues(start = 64.dp, end = 64.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),

            //默认的 HorizontalPager 和 VerticalPager 可组合项会指定 滚动手势适用于分页器。不过，您可以自定义和更改 默认值，例如 pagerSnapDistance 或 flingBehavior

            //设置窗口之外预加载数量
            beyondViewportPageCount = 10,

            //手势行为fling行为
            flingBehavior = fling

        ) { page ->

            val color = remember {
                generateRandomColor()
            }

            Text(
                text = "Page: $page \n Page: $page \n Page: $page \n Page: $page \n Page: $page",
                color = Color.White,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .background(color)
                    .fillMaxSize()
                    .padding(30.dp)
                    //允许直接做一些简单的视图动画操作
                    .graphicsLayer {
                        // 直接使用 pagerState.currentPageOffsetFraction 控制 alpha，它表示当前页面相对于其初始位置的偏移量，范围在-0.5到0.5之间
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pagerState.currentPageOffsetFraction.coerceIn(
                                -0.5f,
                                0.5f
                            ).absoluteValue / 0.5f
                        )
                    }
            )
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            //设置指示器
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 18.dp)
            ) {
                //查看pager状态位置，看是不是当前位置，是就变色，不是就原本颜色，重复绘制
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }

            //按钮，点击跳转到指定位置
            Button(onClick = {
                coroutineScope.launch {
                    // 滚动到具体位置
                    //pagerState.scrollToPage(5)

                    //动画滚动到具体位置
                    pagerState.animateScrollToPage(5)
                }
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Jump to Page 5")
            }
        }

    }
}

/**
 * 生成随机颜色
 */
fun generateRandomColor(): Color {
    val red = Random.nextInt(0, 256) // 随机生成0到255之间的红色值
    val green = Random.nextInt(0, 256) // 随机生成0到255之间的绿色值
    val blue = Random.nextInt(0, 256) // 随机生成0到255之间的蓝色值

    return Color(red, green, blue)
}