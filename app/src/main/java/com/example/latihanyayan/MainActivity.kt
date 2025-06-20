@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.latihanyayan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.latihanyayan.ui.theme.LatihanYayanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LatihanYayanTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Input Data Mahasiswa")
                                    Text(
                                        text = "Yayan Supriyatna - 202211012",
                                        fontSize = 12.sp,
                                        style = MaterialTheme.typography.bodySmall,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors()
                        )
                    }
                ) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var inputText1 by remember { mutableStateOf("") }
    var inputText2 by remember { mutableStateOf("") }
    var inputText3 by remember { mutableStateOf("") }
    val itemList = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = inputText1,
            onValueChange = { inputText1 = it },
            label = { Text("Nama Mahasiswa") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = inputText2,
            onValueChange = { inputText2 = it },
            label = { Text("Jurusan") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = inputText3,
            onValueChange = { inputText3 = it },
            label = { Text("Semester") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (inputText1.isNotBlank() && inputText2.isNotBlank()) {
                    itemList.add("Nama Mahasiswa: $inputText1, Jurusan: $inputText2, Semester: $inputText3")
                    inputText1 = ""
                    inputText2 = ""
                    inputText3 = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Tambah Data")
        }

        if (itemList.isNotEmpty()) {
            Text(
                text = "Daftar Data Mahasiswa",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(itemList) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContent() {
    LatihanYayanTheme {
        MainContent()
    }
}
