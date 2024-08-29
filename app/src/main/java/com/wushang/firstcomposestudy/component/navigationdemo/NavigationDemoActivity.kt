package com.wushang.firstcomposestudy.component.navigationdemo

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

/**
 * 入口
 */
class NavigationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //官方侵入状态栏
        enableEdgeToEdge()
        //去掉底部导航遮罩 android10以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        setContent {
            generateNavigationDemo()
        }
    }
}

/**
 * 主框架，设置路由
 */
@Preview
@Composable
fun generateNavigationDemo() {
    //context
    val context = LocalContext.current
    //Controller
    val navController = rememberNavController()
    //使用NavHost容器，传入controller和startDestination（主页面）
    NavHost(navController = navController, startDestination = NavigationRoute.HOME) {
        //每一个composable函数用以创建一个导航页面，包含路由参数（路由是String）
        composable(NavigationRoute.HOME) {
            //具体页面内容，调用其他组合即可
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Column(modifier = Modifier.align(Alignment.Center)) {
                            Button(onClick = {
                                //根据路由跳转到具体位置
                                navController.navigate(NavigationRoute.DETAIL)
                            }) {
                                Text(text = "普通跳转")
                            }
                            Button(onClick = {
                                navController.navigate(NavigationRoute.SETTING)
                            }) {
                                Text(text = "带参数实例")
                            }

                            //深度链接到深度链接界面
                            Button(onClick = {
                                //深度链接
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data =
                                        Uri.parse("https://www.firstComposeStudy.com/deepLink/tt")
                                }
                                context.startActivity(intent)
                            }) {
                                Text(text = "假设是深层链接go to depp link")
                            }

                            //深度链接到深度链接界面并携带可选参数
                            Button(onClick = {
                                //深度链接
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data =
                                        Uri.parse("https://www.firstComposeStudy.com/deepLink/tt?username=LL")
                                }
                                context.startActivity(intent)
                            }) {
                                Text(text = "假设是深层链接go to depp link并携带可选参数")
                            }

                            //------分割线-----------------------------------------------------------//
                            HorizontalDivider(thickness = 5.dp, color = Color.Red)

                            Button(onClick = {
                                navController.navigate(See)
                            }) {
                                Text(text = "Object类型作为路由跳转")
                            }

                            Button(onClick = {
                                navController.navigate(Profile("123", null))
                            }) {
                                Text(text = "data class类型作为路由跳转")
                            }
                        }
                    }
                }
            )
        }

        composable(NavigationRoute.DETAIL) {
            Text(text = "Detail Page", modifier = Modifier.safeDrawingPadding())
        }

        composable(NavigationRoute.SETTING) {
            generateSettingFragment(
                toDeepLink = {
                    //跳转到DeepLink界面，携带必须参数，不携带可选参数
                    navController.navigate("${NavigationRoute.DEEP_LINK}/从Setting过来")
                },
                toDeepLinkWithSP = {
                    //跳转到DeepLink界面，携带必须参数，携带可选参数
                    navController.navigate("${NavigationRoute.DEEP_LINK}/从Setting过来?username=Allen")
                }
            )
        }

        //测试DeepLink
        composable(
            "${NavigationRoute.DEEP_LINK}/{text}?username={username}",
            //传参
            arguments = listOf(
                navArgument("text") {
                    //设置类型，可省略，默认为String
                    //type = NavType.StringType
                    //可以设置默认值
                    //defaultValue = "user1234"
                },
                navArgument("username") {
                    //设置类型，可省略
                    //type = NavType.StringType
                    //可以设置默认值
                    defaultValue = "user1234"
                }),
            //深层链接和应用内路由可以不同，区分内部外部
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "${NavigationRoute.APP_URL}/${NavigationRoute.DEEP_LINK}/{text}"
                },
                navDeepLink {
                    uriPattern =
                        "${NavigationRoute.APP_URL}/${NavigationRoute.DEEP_LINK}/{text}?username={username}"
                }
            )
        ) { backStackEntry ->
            //这是路由内部提供的，可以在跳转的时候携带参数，通过backStackEntry获取
            generateDeepLinkFragment(
                back = {
                    navController.popBackStack()
                }, data = backStackEntry.arguments?.getString("text")
                        + "\n" + backStackEntry.arguments?.getString("username")
            )
        }

        //类型检查，这里是object作为路由
        composable<See> {
            Text(text = "See Page", modifier = Modifier.safeDrawingPadding())
        }

        //类型检查，这里以data class作为路由，可以传入参数，但是不能太多，这这是给予支持了，但不应该传入复杂数据
        composable<Profile> { backStackEntry ->
            // 获取参数的时候，用toRoute来获得Route对象，类型就是我们定义的那个，那类型检查直接置空就好，并且深度链接用不了这种
            val profile: Profile =
                backStackEntry.toRoute()
            Text(text = profile.id + profile.name, modifier = Modifier.safeDrawingPadding())
        }
    }
}

/**
 * 生成设置页面
 * 不能直接传递NavController，遵循单向数据原则
 */
@Preview
@Composable
fun generateSettingFragment(
    toDeepLink: (() -> Unit)? = null,
    toDeepLinkWithSP: (() -> Unit)? = null
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Button(onClick = {
                        toDeepLink?.invoke()
                    }) {
                        Text(text = "携带必须参数，不携带可选参数 跳转DeepLink界面")
                    }

                    Button(onClick = {
                        toDeepLinkWithSP?.invoke()
                    }) {
                        Text(text = "携带必须参数，携带可选参数 跳转DeepLink界面")
                    }
                }
            }
        }
    )
}

/**
 * 生成测试DeepLink的界面
 * 深层链接的作用是能直接定位到这个为止，如果需要对外部开发就需要在AndroidManifest.xml中配置，见那里
 * 主要设计就是为了让外部快速定位打开这个界面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun generateDeepLinkFragment(
    back: (() -> Unit)? = null,
    data: String? = null
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "DeepLink Fragment")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        //返回，回调外部完成
                        back?.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                })
        }
    ) { innerPadding ->
        Text(
            text = data ?: "DeepLink Fragment",
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp),
            fontSize = 20.sp
        )
    }
}

//-----以@Serializable作为路由可以安全判断类型，还可以结合封闭类使用，要导入2.8.0的库------------------------------------//

//https://developer.android.com/guide/navigation/design?hl=zh-cn#compose

/**
 * See界面，这里用object作为路由
 */
@Serializable
object See

/**
 * 用户界面，不用String作为路由，用类作为路由，这样可以让idea帮忙检查，避免路由填错
 * 不要误解这里传入很复杂的内容，复杂数据应该去model获取，这里依然是简单数据
 * 什么可空，默认值确实更方便了
 */
@Serializable
data class Profile(val id: String, val name: String?="")
