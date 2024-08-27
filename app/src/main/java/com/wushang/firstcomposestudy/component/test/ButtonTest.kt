package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author：allen.wang
 * @date：2024/8/27 17:26
 * @description：Button按钮测试
 */
object ButtonTest {
    /**
     * 生成所有种类的悬浮按钮
     */
    @Composable
    fun generateFloatingButtons(innerPaddingValues: PaddingValues = PaddingValues(0.dp)) {
        Column(modifier = Modifier.padding(innerPaddingValues)) {
            //常规大小悬浮按钮
            FloatingActionButton(
                //点击事件
                onClick = {},
                //图标颜色
                contentColor = Color.White,
                //按钮颜色
                containerColor = Color.Blue
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
            //小悬浮按钮
            SmallFloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
            //大悬浮按钮
            LargeFloatingActionButton(
                onClick = {},
                //设置样式为圆形
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
            //扩展悬浮按钮
            ExtendedFloatingActionButton(onClick = {},
                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                text = { Text("Extended") })
        }
    }

    /**
     * 生成所有种类的按钮
     */
    @Composable
    fun generateButtons(innerPaddingValues: PaddingValues = PaddingValues(0.dp)) {
        Column(modifier = Modifier.padding(innerPaddingValues)) {
            //第一种按钮
            Button(onClick = {}) {
                Text("Button")
            }
            //第二种按钮
            FilledTonalButton(onClick = {}) {
                Text("FilledTonalButton")
            }
            //第三种按钮
            OutlinedButton(onClick = {}) {
                Text("OutlinedButton")
            }
            //第四种按钮
            ElevatedButton(onClick = {}) {
                Text("ElevatedButton")
            }
            //第五种按钮
            TextButton(onClick = {}) {
                Text("TextButton")
            }
        }
    }
}