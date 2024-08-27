package com.wushang.firstcomposestudy.component.test

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wushang.firstcomposestudy.R

/**
 * @author：allen.wang
 * @date：2024/8/27 15:11
 * @description：Dialog对话框测试
 */
object DialogTest {

    @Composable
    fun generateDialog() {
        val openAlertDialog = remember { mutableStateOf(false) }

        // 按钮来打开 AlertDialog
        Button(onClick = {
            openAlertDialog.value = true
        }) {
            Text(text = "Open AlertDialog")
        }

        // 根据状态来显示 AlertDialog
        if (openAlertDialog.value) {
            generateAlertDialog { confirmed ->
                openAlertDialog.value = false
                Log.i("TAGG", "Dialog confirmed: $confirmed")
            }
        }

        val openDialog = remember { mutableStateOf(false) }

        // 按钮来打开 AlertDialog
        Button(onClick = {
            openDialog.value = true
        }) {
            Text(text = "Open Dialog")
        }

        // 根据状态来显示 AlertDialog
        if (openDialog.value) {
            generateCustomDialog { confirmed ->
                openDialog.value = false
                Log.i("TAGG", "Dialog confirmed: $confirmed")
            }
        }
    }

    /**
     * 生成一个AlertDialog，如果您的对话框需要一组更复杂的按钮，您可以 受益于使用 `Dialog` 可组合项并以更自由的形式填充它
     */
    @Composable
    fun generateAlertDialog(confirm: (Boolean) -> Unit) {
        AlertDialog(
            //图标
            icon = {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notification")
            },
            //标题
            title = {
                Text(text = "Notification")
            },
            //内容
            text = {
                Text(text = "This is a notification message.")
            },
            //形状
            shape = RoundedCornerShape(topStart = 20.dp),
            //当用户关闭对话框时调用的函数。 例如点按屏幕以外的地方
            onDismissRequest = {
                Log.i("TAGG", "close alertDialog!")
                confirm(false)
            },
            //确认按钮组合项
            confirmButton = {
                TextButton(onClick = {
                    confirm(true)
                }) {
                    Text(text = "OK")
                }
            },
            //取消按钮组合项
            dismissButton = {
                TextButton(onClick = { confirm(false) }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

    /**
     * 生成一个自己样式的dialog，需要自己填充内容
     * 与上一部分中的 AlertDialog示例不同，您需要来手动指定Dialog的大小和形状。您还需要提供内部容器。
     */
    @Composable
    fun generateCustomDialog(confirm: (Boolean) -> Unit) {
        Dialog(onDismissRequest = { confirm(false) }) {
            // 这里可以自由填充内容
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(20.dp), shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //填充一张图片
                    Image(
                        painter = painterResource(id = R.drawable.ic_pic_test_1),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, top = 8.dp, end = 30.dp)
                            .height(160.dp),
                        contentScale = ContentScale.Crop
                    )
                    //填充一段文字
                    Text(text = "This is a custom dialog.", modifier = Modifier.padding(top = 10.dp))
                    //取消和确认按钮
                    Row {
                        TextButton(onClick = { confirm(false) }, modifier = Modifier.weight(1f)) {
                            Text(text = "Cancel")
                        }
                        TextButton(onClick = { confirm(true) }, modifier = Modifier.weight(1f)) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}