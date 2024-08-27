package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * @author：allen.wang
 * @date：2024/8/27 17:57
 * @description：BottomSheet相关的底部工作栏测试
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun generateBottomSheet() {
    //是否显示
    val isShow = remember {
        mutableStateOf(false)
    }
    //可提供对 show 和 hide 函数，以及与当前工作表相关的属性 状态
    val state = rememberModalBottomSheetState(
        //设置全屏工作
        skipPartiallyExpanded = false
    )
    //协程
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            isShow.value = !isShow.value
        },
        enabled = !isShow.value
    ) {
        Text(text = "Show BottomSheet")
    }

    if (isShow.value) {
        //展示BottomSheet
        ModalBottomSheet(
            //设置高度，开启skipPartiallyExpanded后可以撑满屏幕
            modifier = Modifier.fillMaxHeight(),
            onDismissRequest = {
                //关闭时的回调
                isShow.value = false
            },
            //保存状态
            sheetState = state
        ) {

            //这里可以填充里面的内容
            Button(onClick = {
                scope.launch {
                    //调用之前保存的状态关闭
                    state.hide()
                }.invokeOnCompletion {
                    if (!state.isVisible) {
                        isShow.value = false
                    }
                }
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(30.dp)
                    .padding(top = 50.dp)
            ) {
                Text("Hide bottom sheet")
            }
        }
    }
}

