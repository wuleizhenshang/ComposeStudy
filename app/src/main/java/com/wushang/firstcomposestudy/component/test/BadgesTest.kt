package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author：allen.wang
 * @date：2024/8/27 17:31
 * @description：Badges标记测试
 */
object BadgesTest {

    @Composable
    fun generateBadges() {

        //记录加入购物车数量
        val count = remember {
            mutableStateOf(0)
        }

        Column {

            BadgedBox(
                //标识
                badge = {
                Badge()
            }) {
                //这里是具体的填充，默认Badge是一个小红点
                Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
            }

            Spacer(modifier = Modifier.height(20.dp))

            //仿一个购物车图标
            BadgedBox(badge = {
                if (count.value > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text("${count.value}")
                    }
                }
            }) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Shop")
            }

            Spacer(modifier = Modifier.height(20.dp))


            Row(modifier = Modifier.padding(10.dp)) {
                FloatingActionButton(onClick = {
                    count.value++
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }

                FloatingActionButton(onClick = {
                    count.value = 0
                }, modifier = Modifier.padding(start = 10.dp)) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
                }
            }
        }
    }

}

@Preview
@Composable
fun test(){
    BadgesTest.generateBadges()
}