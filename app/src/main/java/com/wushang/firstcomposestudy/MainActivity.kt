package com.wushang.firstcomposestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wushang.firstcomposestudy.component.test.BadgesTest
import com.wushang.firstcomposestudy.component.test.ButtonTest
import com.wushang.firstcomposestudy.component.test.CardTest
import com.wushang.firstcomposestudy.component.test.CheckboxTest
import com.wushang.firstcomposestudy.component.test.ChipTest
import com.wushang.firstcomposestudy.component.test.DialogTest
import com.wushang.firstcomposestudy.component.test.ProgressTest
import com.wushang.firstcomposestudy.component.test.SliderTest
import com.wushang.firstcomposestudy.component.test.SwitchTest
import com.wushang.firstcomposestudy.component.test.generateBottomSheet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            generateScaffold()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun generateScaffold() {
    //向下滚动收起，顶部到末尾收起
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    //整体框架
    Scaffold(bottomBar = {
        //新加底部Bar
        BottomAppBar(actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Check, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "Localized description",
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.Build,
                    contentDescription = "Localized description",
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Filled.ShoppingCart,
                    contentDescription = "Localized description",
                )
            }
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        })
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(15.dp)) {
            //简单滑动列表
            //ScrollContent(innerPadding, scrollBehavior)

            //Button集合
            //ButtonTest.generateButtons()

            //FloatingButton集合
            //ButtonTest.generateFloatingButtons()

            //生成所有种类的Card
            //CardTest.generateCards(innerPadding)

            //Chip条状标签
            //ChipTest.generateChips()

            //dialog
            //DialogTest.generateDialog()

            //progress
            //ProgressTest.generateProgress()

            //slider
            //SliderTest.generateSlider()

            //Switch
            //SwitchTest.generateSwitch()

            //checkbox
            //CheckboxTest.generateCheckboxSample()

            //Badge
            //BadgesTest.generateBadges()

            //BottomSheet
            generateBottomSheet()
        }
    }
}

/**
 * 生成一个list
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollContent(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp), scrollBehavior: TopAppBarScrollBehavior
) {
    Column(
        //不被应用栏遮挡
        modifier = Modifier.padding(
            innerPaddingValues
        )
    ) {
        LazyColumn(
            //填满宽度并绑定 nestedScroll 连接
            modifier = Modifier
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            items(100) {
                Text(text = "Item $it", fontSize = 24.sp)
            }
        }
    }
}
