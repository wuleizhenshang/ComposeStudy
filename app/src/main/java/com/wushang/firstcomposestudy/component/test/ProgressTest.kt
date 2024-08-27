package com.wushang.firstcomposestudy.component.test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author：allen.wang
 * @date：2024/8/27 15:50
 * @description：Progress进度条测试
 */
object ProgressTest {

    /**
     * 生成进度条
     */
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun generateProgress() {

        /**
         * 请考虑以下三个可能会用到进度指示器的用例：
         *
         * 加载内容：从网络中提取内容时，例如加载时 用户个人资料的图片或数据
         * 文件上传：向用户提供关于上传所需时间的反馈。
         * 处理时间较长：当应用处理大量数据时， 向用户传达总数中有多少内容已完成。
         * 在 Material Design 中，有两种类型的进度指示器：
         *
         * 确定：显示具体进度。
         * 不确定：动画持续播放，而不考虑进度。
         * 同理，进度指示器可以采用以下两种形式之一：
         *
         * 线性：从左到右填充的水平条。
         * 圆形：描边长度延长至包含 圆周长。
         */

        val progress = remember { mutableFloatStateOf(0f) }
        //协程
        val scope = rememberCoroutineScope()

        scope.launch { //启动协程
            for (i in 0..100) {
                progress.value = i / 100f
                kotlinx.coroutines.delay(100)
            }
        }

        Column {
            //线性进度条
            LinearProgressIndicator(
                //进度，0f-1f之间
                progress = { progress.floatValue },
                //指示器颜色
                color = Color.Red,
                //滑道颜色
                trackColor = Color.Gray,

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            //圆形进度条
            CircularProgressIndicator(
                //进度，0f-1f之间
                progress = { progress.floatValue },
                //指示器颜色
                color = Color.Red,
                //滑道颜色
                trackColor = Color.Gray,
                //边框宽度
                strokeWidth = 2.dp
            )

            Spacer(modifier = Modifier.height(30.dp))

            //生成不确定进度
            generateUncertainProgress()
        }
    }

    /**
     * 生成不确定进度
     * 不传入进度即可
     */
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun generateUncertainProgress(){
        val loading = remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()

        Button(onClick = { loading.value = true }, enabled = !loading.value) {
            Text("Start loading")
        }

        //不显示下面的界面了
        if (!loading.value) return


        Column {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )

            Spacer(modifier = Modifier.height(40.dp))

            LinearProgressIndicator(
                modifier = Modifier.width(300.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }


        scope.launch {
            delay(5000)
            loading.value = false
        }
    }
}