package com.wushang.firstcomposestudy.component.demo

import android.graphics.Picture
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.wushang.firstcomposestudy.component.demo.ui.theme.FirstComposeStudyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DemoMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            generateMainFramework()
        }
    }
}

@Preview
@Composable
fun generateMainFramework() {

    //scope
    val coroutineScope = rememberCoroutineScope()

    //底部导航条目
    val items = remember {
        listOf(
            NavItem.Home,
            NavItem.Profile,
            NavItem.Settings
        )
    }

    //记录Pager状态
    val pagerState = rememberPagerState() { items.size }

    //整体框架
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //底部导航
        bottomBar = {
            generateBottomNavigation(
                items = items,
                pagerState = pagerState,
                coroutineScope = coroutineScope
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(innerPadding)
        ) { pager ->
            when (items[pager]) {
                NavItem.Home -> Home()
                NavItem.Profile -> Profile()
                NavItem.Settings -> Setting()
            }
        }
    }
}

@Composable
fun generateBottomNavigation(
    items: List<NavItem>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    //底部导航
    NavigationBar {
        items.forEachIndexed { index, navItem ->
            NavigationBarItem(selected = pagerState.currentPage == index, onClick = {
                coroutineScope.launch { pagerState.animateScrollToPage(index) }
            }, icon = {
                Icon(imageVector = navItem.icon, contentDescription = navItem.title)
            })
        }
    }
}


@Preview
@Composable
fun Home() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(text = "Home", fontSize = 20.sp, color = Color.Black)
    }
}

@Preview
@Composable
fun Profile() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(text = "Picture", fontSize = 20.sp, color = Color.Black)
    }
}


@Preview
@Composable
fun Setting() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Text(text = "Setting", fontSize = 20.sp, color = Color.White)
    }
}

/**
 * 底部导航封闭类
 */
sealed class NavItem(var route: String, var icon: ImageVector, var title: String) {
    data object Home : NavItem("home", Icons.Filled.Home, "Home")
    data object Profile : NavItem("profile", Icons.Filled.Person, "Profile")
    data object Settings : NavItem("settings", Icons.Filled.Settings, "Settings")
}


