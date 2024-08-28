package com.wushang.firstcomposestudy.component.test

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet


/**
 * @author：allen.wang
 * @date：2024/8/28 17:07
 * @description：Compose中的相对布局
 */
@Preview
@Composable
fun generateConstraintLayoutDemo1() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        //在相对布局内部创建引用，默认有parent
        val (button, text) = createRefs()

        Button(onClick = { /*TODO*/ },
            //绑定引用名
            modifier = Modifier.constrainAs(button) {
                //设置button的位置
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
        ) {
            Text(text = "Button")
        }


        Text(text = "Hello",
            color = Color.Black,
            fontSize = 25.sp,
            modifier = Modifier.constrainAs(text){
                top.linkTo(button.bottom, margin = 30.dp)
                start.linkTo(button.start)
            })
    }


}

/**
 * 将约束和布局分离
 */
@Preview
@Composable
fun generateConstraintLayoutDemo2(){
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }
        
        ConstraintLayout(constraintSet = constraints) {
            Button(
                onClick = { /* Do something */ },
                //绑定id
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

/**
 * 约束关系独立出来
 */
private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        //寻找id
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}
