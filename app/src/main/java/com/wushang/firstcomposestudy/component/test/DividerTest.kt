package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * @author：allen.wang
 * @date：2024/8/28 11:21
 * @description：分割线测试
 */
@Preview
@Composable
fun generateDividerDemo() {
    Column {

        Row(modifier = Modifier.height(100.dp)) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = "Button1")
            }

            VerticalDivider()

            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = "Button2")
            }
        }

        HorizontalDivider(modifier = Modifier.height(10.dp), color = androidx.compose.ui.graphics.Color.Red)

        Row(modifier = Modifier.height(100.dp)) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = "Button1")
            }

            VerticalDivider()

            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = "Button2")
            }
        }

    }
}
