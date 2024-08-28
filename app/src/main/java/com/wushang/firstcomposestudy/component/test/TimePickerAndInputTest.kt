package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

/**
 * @author：allen.wang
 * @date：2024/8/28 9:57
 * @description：TimePicker和TimeInput测试
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun generateTimePickerAndInputDemo() {
    val currentTime = Calendar.getInstance()
    //保存状态
    val timePickerState = rememberTimePickerState(
        //设置初始时间
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        //设置是否24小时制，默认为false
        is24Hour = true
    )
    Column {
        //时间选择器
        TimePicker(state = timePickerState)

        //时间输入框
        TimeInput(state = timePickerState)
    }
}