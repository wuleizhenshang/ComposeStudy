package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

/**
 * @author：allen.wang
 * @date：2024/8/28 10:54
 * @description：SnackBar测试
 */
@Preview
@Composable
fun generateSnackBarDemo() {

    val scope = rememberCoroutineScope()

    val hostState = remember {
        SnackbarHostState()
    }

    Scaffold (
        //需要先创建一个SnackBarHost，Scaffold提供这些基础组件支出
        snackbarHost = {
            SnackbarHost(hostState = hostState)
        }
    ){ innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Button(onClick = {
                //协程中使用
                scope.launch {
                    hostState.showSnackbar("snackbar content")
                }
            }) {
                Text(text = "Show SnackBar")
            }

            Button(onClick = {
                scope.launch {
                    hostState.showSnackbar(
                        message = "Show SnackBar With Option",
                        //右边的按钮文案
                        actionLabel = "Action",
                        //直到点击actionLabel才关闭
                        duration = SnackbarDuration.Indefinite)
                }

                /**
                 * 时长可选择
                 * enum class SnackbarDuration {
                 *     /** Show the Snackbar for a short period of time */
                 *     Short,
                 *
                 *     /** Show the Snackbar for a long period of time */
                 *     Long,
                 *
                 *     /** Show the Snackbar indefinitely until explicitly dismissed or action is clicked */
                 *     Indefinite
                 * }
                 */
            }) {
                Text(text = "Show SnackBar With Option")
            }
        }
    }

}