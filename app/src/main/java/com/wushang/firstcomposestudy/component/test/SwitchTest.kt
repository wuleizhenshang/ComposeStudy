package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @author：allen.wang
 * @date：2024/8/27 16:46
 * @description：Switch开关测试
 */
object SwitchTest {
    @Composable
    fun generateSwitch() {

        val isChecked = remember {
            mutableStateOf(false)
        }

        Switch(checked = isChecked.value, onCheckedChange = {
            isChecked.value = it
        },
            //滑块缩略图外观颜色
            thumbContent = if (isChecked.value) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = Color.White
                    )
                }
            } else {
                null
            },
            //自定义颜色
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = Color.Gray,
                checkedTrackColor = Color.Blue,
                uncheckedThumbColor = Color.White,
                checkedThumbColor = Color.Black
            ))
    }
}