package com.wushang.firstcomposestudy.component.test

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * @author：allen.wang
 * @date：2024/8/28 10:17
 * @description：DataPicker日期选择
 */
/**
 * 静态日期选择器
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    val showDatePicker = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        //一个输入框
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        if (showDatePicker.value) {
            //显示一个弹出窗口
            Popup(
                onDismissRequest = { showDatePicker.value = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        //弹出的位置，上面输入框高度为64dp，这里偏移这么多就行
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

/**
 * 对话框样式日期选择器
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
) {

    val showDateDialog = remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    Button(
        onClick = { showDateDialog.value = !showDateDialog.value },
        enabled = !showDateDialog.value, modifier =
        Modifier.padding(top = 20.dp)
    ) {
        Text(text = "Show Date Picker")
    }

    if (showDateDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDateDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showDateDialog.value = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDateDialog.value = false
                }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

/**
 * 输入样式日期选择器
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(
) {
    val showDateDialog = remember {
        mutableStateOf(false)
    }

    //设置样式为输入样式
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    Button(
        onClick = { showDateDialog.value = !showDateDialog.value },
        enabled = !showDateDialog.value, modifier =
        Modifier.padding(top = 20.dp)
    ) {
        Text(text = "Show Input Date Picker")
    }

    if (showDateDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDateDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showDateDialog.value = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDateDialog.value = false
                }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
) {
    val showDateDialog = remember {
        mutableStateOf(false)
    }

    //选择的日期范围
    val dateRangePickerState = rememberDateRangePickerState()

    Button(
        onClick = { showDateDialog.value = !showDateDialog.value },
        enabled = !showDateDialog.value, modifier =
        Modifier.padding(top = 20.dp)
    ) {
        Text(text = "Show Range Date Picker")
    }

    if (showDateDialog.value) {
        DatePickerDialog(
            onDismissRequest = {showDateDialog.value = false},
            confirmButton = {
                TextButton(
                    onClick = {
//                        onDateRangeSelected(
//                            Pair(
//                                dateRangePickerState.selectedStartDateMillis,
//                                dateRangePickerState.selectedEndDateMillis
//                            )
//                        )
                        showDateDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {showDateDialog.value = false}) {
                    Text("Cancel")
                }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                title = {
                    Text(
                        text = "Select date range"
                    )
                },
                showModeToggle = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(16.dp)
            )
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}