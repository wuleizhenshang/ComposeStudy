package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @author：allen.wang
 * @date：2024/8/27 16:29
 * @description：Slider滑动条测试
 */
object SliderTest {
    @Composable
    fun generateSlider() {

        val position1 = remember {
            mutableFloatStateOf(0f)
        }
        val position2 = remember {
            mutableFloatStateOf(0f)
        }
        val position3 = remember {
            mutableStateOf(0f..100f)
        }

        Column {
            //简单使用
            Slider(
                //值为简单的0f-1f
                value = position1.floatValue,
                //滑块变化，改变数据
                onValueChange = {
                    position1.floatValue = it
                },
                //是否运行用户操作
                enabled = true
            )
            Text(text = position1.floatValue.toString())

            Spacer(modifier = Modifier.height(30.dp))

            //一些高级使用
            Slider(
                value = position2.floatValue,
                onValueChange = {
                    position2.floatValue = it
                },
                //设置范围
                valueRange = 0f..100f,
                //滑块上让手指点击卡入槽位的数量
                steps = 3
            )
            Text(text = position2.floatValue.toString())

            Spacer(modifier = Modifier.height(30.dp))

            //范围滑块
            RangeSlider(
                value = position3.value,
                steps = 5,
                onValueChange = { range -> position3.value = range },
                valueRange = 0f..100f,
                onValueChangeFinished = {
                    // launch some business logic update with the state you hold
                    // viewModel.updateSelectedSliderValue(sliderPosition)
                },
            )
            Text(text = position3.value.toString())
        }
    }
}