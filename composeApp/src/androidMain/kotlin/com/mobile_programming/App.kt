package com.mobile_programming

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import android.util.Log
import android.widget.DatePicker
import androidx.compose.material.Switch
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
@Preview
fun App() {
    MaterialTheme {
        var title by remember { mutableStateOf("") } // Input state
        var priorityExpanded by remember { mutableStateOf(false) }
        var priority by remember { mutableStateOf("") }
        var categoryExpanded by remember { mutableStateOf(false)}
        var category by remember { mutableStateOf("")}
        var description by remember { mutableStateOf("") }
        var shouldSendNotifications by remember { mutableStateOf(false)}
        var dueDate by remember { mutableStateOf("") }
        var calendarExpanded by remember { mutableStateOf(false) }
        val calendar = Calendar.getInstance()

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // title
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            // priority
            Box(modifier = Modifier.fillMaxWidth().clickable {
                Log.d("PriorityDropdown", "Box clicked")
                println("Box clicked")
                priorityExpanded = !priorityExpanded
            }) {
                OutlinedTextField(
                    value = priority,
                    onValueChange = { priority = it },
                    label = { Text("Select priority") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                )
            }
            DropdownMenu(expanded = priorityExpanded, onDismissRequest = { priorityExpanded = false }, modifier = Modifier.fillMaxWidth()) {
                DropdownMenuItem(onClick = {
                    priority = "high"
                    priorityExpanded = false // Collapse menu after selection
                }) {
                    Text("High")
                }
                DropdownMenuItem(onClick = {
                    priority = "medium"
                    priorityExpanded = false // Collapse menu after selection
                }) {
                    Text("Medium")
                }
                DropdownMenuItem(onClick = {
                    priority = "low"
                    priorityExpanded = false // Collapse menu after selection
                }) {
                    Text("Low")
                }
            }
            // category
            Box(modifier = Modifier.fillMaxWidth().clickable {
                Log.d("CategoryDropdown", "Box clicked")
                println("Box clicked")
                categoryExpanded = !categoryExpanded
            }) {
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Select category") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                )
            }
            DropdownMenu(expanded = categoryExpanded, onDismissRequest = { categoryExpanded = false }, modifier = Modifier.fillMaxWidth()) {
                DropdownMenuItem(onClick = {
                    category = "work"
                    categoryExpanded = false // Collapse menu after selection
                }) {
                    Text("Work")
                }
                DropdownMenuItem(onClick = {
                    category = "personal"
                    categoryExpanded = false // Collapse menu after selection
                }) {
                    Text("Personal")
                }
                DropdownMenuItem(onClick = {
                    category = "other"
                    categoryExpanded = false // Collapse menu after selection
                }) {
                    Text("Other")
                }
            }
            // description
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            // due date
            OutlinedTextField(
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due date: $dueDate")},
                modifier = Modifier.fillMaxWidth().clickable { calendarExpanded = true },
                readOnly = true,
                singleLine = true,
            )
            val calendarDialog = DatePickerDialog(LocalContext.current,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    dueDate = "$dayOfMonth/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
            LaunchedEffect(calendarExpanded) {
                if(calendarExpanded) {
                    calendarDialog.show()
                }
            }
            calendarDialog.setOnDismissListener {
                calendarExpanded = false
            }
            // notifications
            Text("Receive notifications when the task is due")
            Switch(checked = shouldSendNotifications, onCheckedChange = { shouldSendNotifications = !shouldSendNotifications })
            // button
            Button(onClick = { }) {
                Text("Create task")
            }
        }
    }
}