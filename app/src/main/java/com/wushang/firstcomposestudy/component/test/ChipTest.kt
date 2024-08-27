package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author：allen.wang
 * @date：2024/8/27 14:31
 * @description：Chip标签测试
 */
object ChipTest {
    /**
     * 生成所有类型的标签Chip
     */
    @Composable
    fun generateChips() {

        val selected = remember { mutableStateOf(false) }
        val enable = remember { mutableStateOf(false) }

        //辅助条状标签
        AssistChip(onClick = {
            //点击事件
        }, label = {
            //条状显示的字符串
            Text(text = "AssistChip")
        }, leadingIcon = {
            //前置图标
            Icon(
                Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        })

        //根据条件显示不同样式的标签
        FilterChip(
            //默认选中情况
            selected = selected.value,
            onClick = { selected.value = !selected.value },
            label = { Text(text = "FilterChip") },
            leadingIcon = if (selected.value) {
                //这里内层使用lambda，而不是直接传入Icon对象
                {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            })

        //输入标签
        InputChip(
            selected = enable.value,
            onClick = { enable.value = !enable.value },
            label = {
                Text(
                    text = "InputChip"
                )
            },
            avatar = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            })

        //建议标签
        SuggestionChip(
            onClick = {  },
            label = { Text("Suggestion chip") }
        )
    }
}