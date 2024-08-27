package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * @author：allen.wang
 * @date：2024/8/27 17:27
 * @description：Card卡片测试
 */
object CardTest {
    /**
     * 生成所有种类的Card
     */
    @Composable
    fun generateCards(innerPaddingValues: PaddingValues = PaddingValues(0.dp)) {
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .padding(10.dp)
        ) {
            //常规Card
            Card(
                //颜色
                colors = CardDefaults.cardColors(
                    containerColor = Color.Blue,
                )
                //onClick，通常，Card 不接受点击事件，需要点击就使用这个
                , onClick = {}
                //card是否响应外部用户输入
                , enabled = true
                //高度
                , elevation = CardDefaults.cardElevation(6.dp)
                //大小
                , modifier = Modifier.size(200.dp, 150.dp)) {
                Text(text = "First Card", modifier = Modifier.padding(12.dp), color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            //上层卡片，会产生阴影
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ), modifier = Modifier.size(width = 240.dp, height = 100.dp)
            ) {
                Text(
                    text = "Elevated",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            //轮廓卡片
            OutlinedCard(
                modifier = Modifier.size(width = 240.dp, height = 100.dp),
                //边框颜色
                border = BorderStroke(2.dp, Color.Blue)
            ) {
                Text(
                    text = "Outlined",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}