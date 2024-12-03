package com.mobile_programming

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun App() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var isDatePickerVisible by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var selectedPriority by remember { mutableStateOf("Seleccionar Prioridad") }
    var selectedCategory by remember { mutableStateOf("Seleccionar Categoría") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // DatePickerDialog para seleccionar la fecha
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dueDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de texto para el título
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titulo") },
            modifier = Modifier.fillMaxWidth(),
        )

        // Dropdown para la prioridad
        DropdownPriority(
            selectedPriority = selectedPriority,
            onPrioritySelected = { selectedPriority = it }
        )

        // Dropdown para la categoría
        DropdownCategory(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        // Campo de texto para la descripción
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
        )

        // Botón para seleccionar la fecha de vencimiento
        Button(
            onClick = { isDatePickerVisible = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Fecha de vencimiento: ${dueDate.ifEmpty { "Seleccionar fecha" }}")
        }

        // Mostrar el DatePickerDialog si es necesario
        if (isDatePickerVisible) {
            datePickerDialog.show()
            isDatePickerVisible = false
        }

        // Toggle para notificaciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Recibir notificaciones cuando la tarea esté vencida")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
        }

        // Botón para crear la tarea
        Button(
            onClick = { /* Acción para crear la tarea */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Crear tarea")
        }
    }
}
