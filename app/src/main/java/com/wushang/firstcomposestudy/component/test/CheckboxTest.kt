package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.state.ToggleableState

/**
 * @author：allen.wang
 * @date：2024/8/27 17:08
 * @description：Checkbox复选框测试
 */
object CheckboxTest {
    @Composable
    fun generateCheckboxSample() {
        //记录孩子的选中状态
        val childCheckState = remember {
            mutableStateListOf(false, false, false)
        }

        //根据孩子的状态设置父亲的状态
        val parentState = when {
            childCheckState.all { it } -> ToggleableState.On
            childCheckState.none { it } -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }

        Column {
            //生成父亲
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Select all")
                TriStateCheckbox(
                    state = parentState,
                    onClick = {
                        // 选择全部就修改孩子的状态
                        val newState = parentState != ToggleableState.On
                        childCheckState.forEachIndexed { index, _ ->
                            childCheckState[index] = newState
                        }
                    }
                )
            }

            //生成所有孩子
            childCheckState.forEachIndexed { index, isCheck ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Option ${index + 1}")
                    Checkbox(checked = isCheck, onCheckedChange ={
                        //修改孩子数据，更新即可
                        childCheckState[index] = it
                    } )
                }
            }
        }
    }
}