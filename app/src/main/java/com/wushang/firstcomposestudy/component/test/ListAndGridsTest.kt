package com.wushang.firstcomposestudy.component.test

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random


/**
 * @author：allen.wang
 * @date：2024/8/28 11:33
 * @description：列表和网格测试
 */

/**
 * 如果消息列表不多，可以使用Column，需要滚动可以使用verticalScroll()修饰符，横向就用horizontalScroll()
 * 但是这样是会加载所有的item，如果item很多，会导致性能问题，这时候就需要类似RecyclerView的方式
 */
@Composable
fun MessageList(messages: List<String>) {

    val state = rememberScrollState()



    Column(
        //滚动
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state)
    ) {
        messages.forEach { message ->
            MessageRow(message)
        }
    }
}

/**
 * LazyListScope DSL
 * LazyRow和LazyColumn
 * contentPadding 内容边距
 * verticalArrangement 竖直边距
 * items和itemsIndexed
 */
@Composable
fun generateLazyColumn1() {
    LazyColumn(
        //内容边距
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        //内部item之间距离 竖直如下，水平同理 horizontalArrangement = Arrangement.spacedBy(4.dp)，网格两个都可以接受
        verticalArrangement = Arrangement.spacedBy(10.dp),

        modifier = Modifier.fillMaxSize()
    ) {
        items(30) {
            Text("Item #$it", modifier = Modifier.padding(10.dp))
        }
        itemsIndexed(
            items = listOf("A", "B", "C"),
            //为避免出现数据变化导致位置不对的情况，您可以为每个列表项提供一个稳定的唯一键，为 key 参数提供一个块。提供稳定的键可使项状态在发生数据集更改后保持一致，
            // 不过，对于可用作项键的类型有一条限制。键的类型必须受 Bundle 支持，这是 Android 的机制，旨在当重新创建 activity 时保持相应状态。Bundle 支持基元、枚举或 Parcelable 等类型
            // rememberSaveable 同理
            key = { index, item ->
                index
            }
        ) { index, item ->

            Text("Item #$index: $item", modifier = Modifier.padding(10.dp))

            //设置key后可以为这项设置记忆状态，位置变化但是状态维持之前设置的状态
            val rememberedValue = remember {
                Random.nextInt()
            }
        }
    }
}

/**
 * LazyVerticalGrid 和 LazyHorizontalGrid
 * span 指定一个网格项在网格中跨越的列或行数
 */
@Composable
fun generateGrid1() {
    LazyVerticalGrid(
        //宽度至少为128dp columns = GridCells.Adaptive(minSize = 128.dp)
        //您可以指定列表项的宽度，然后网格将适应尽可能多的列。计算列数后，系统会将剩余的宽度平均分配给各列。这种自适应尺寸调整方式非常适合在不同尺寸的屏幕上显示多组列表项
        columns = GridCells.Adaptive(minSize = 30.dp)
        //columns = GridCells.Fixed(3)
    ) {
        //如果您的设计只需要某些列表项具有非标准尺寸，您可以使用网格支持功能为列表项提供自定义列 span。
        // 使用 LazyGridScope DSL item 和 items 方法的 span 参数指定列 span。maxLineSpan 是 span 范围的值之一，在您使用自适应尺寸调整功能时特别有用，因为此情况下列数不固定。
        items(63,
            //用于指定一个网格项在网格中跨越的列或行数，可以不指定
            span = {
                GridItemSpan(2)
            }) {
            Text("Item #$it", modifier = Modifier.padding(10.dp))
        }
        //拼接其他的items
        items(30,
            //用于指定一个网格项在网格中跨越的列或行数
            span = {
                GridItemSpan(3)
            }) {
            Text("Item #$it", modifier = Modifier.padding(10.dp))
        }
    }
}

/**
 * LazyVerticalStaggeredGrid 和 LazyHorizontalStaggeredGrid
 */
@Composable
fun generateStaggeredGrid() {
    LazyVerticalStaggeredGrid(
        //也可以同前面用固定的列
        columns = StaggeredGridCells.Adaptive(200.dp),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(36) { photo ->
                AsyncImage(
                    model = photo,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

/**
 * 粘性标题列表
 * 监听变化
 * 滚动到指定位置
 * SnapshotFlow
 */
@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun generateStickyHeaderList() {
    // 生成数据，一个按字母分组的 Map
    val map = generateData()

    // 记住列表状态 LazyListState 还可以通过 layoutInfo 属性提供有关当前显示的所有列表项以及这些项在屏幕上的边界的信息。如需了解详情，请参阅 LazyListLayoutInfo 类
    val listState = rememberLazyListState()

    //是否显示按钮
    val showButton = remember {
        //根据列表状态修改这个数据
        derivedStateOf {
            //第一个可见的item的索引
            listState.firstVisibleItemIndex > 0
            //firstVisibleItemScrollOffset第一个可见项的滚动偏移量
        }
    }

    //为方便处理出现的快速api
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> index > 0 }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                //收集到做一些事情，异步
            }
    }


    //协程
    val scope = rememberCoroutineScope()

    Box {
        LazyColumn(
            // 保存状态
            state = listState
        ) {
            // 遍历 map，生成每个字母组的联系人列表
            map.forEach { (initial, contactsForInitial) ->
                stickyHeader {
                    // 生成字母分组的标题
                    Text(
                        text = initial.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(Color.Gray),
                        fontSize = 10.sp,
                        color = Color.White
                    )
                    //CharacterHeader(initial)
                }

                items(contactsForInitial) { contact ->
                    // 每个联系人的条目视图
                    Row {
                        Text(contact.firstName, modifier = Modifier.padding(8.dp), fontSize = 16.sp)
                        Text(contact.lastName, modifier = Modifier.padding(8.dp), fontSize = 16.sp)
                        Text(
                            contact.phoneNumber,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        if (showButton.value) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        // 滚动到指定位置
                        listState.scrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            ) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
            }
        }
    }


}

/**
 * 生成测试数据
 */
fun generateData(): Map<Char, List<Contact>> {

    val result = mutableMapOf<Char, List<Contact>>()

    for (i in 0..10) {
        val list = mutableListOf<Contact>()

        for (j in 0..Random.nextInt(8)) {
            list.add(Contact("Allen", "Wang", "123456789"))
        }

        result[('a'.code + i).toChar()] = list
    }

    return result
}

/**
 * 联系人数据bean
 */
data class Contact(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)

//pagging3库使用。。。分页加载

@Composable
fun MessageRow(message: String) {
    Text(text = message, modifier = Modifier.padding(8.dp))
}