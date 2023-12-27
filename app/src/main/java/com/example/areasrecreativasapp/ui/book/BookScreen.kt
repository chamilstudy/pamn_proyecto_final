package com.example.areasrecreativasapp.ui.book

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowCircleDown
import androidx.compose.material.icons.rounded.ArrowCircleUp
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.House
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.areasrecreativasapp.R
import com.example.areasrecreativasapp.data.database.Book
import com.example.areasrecreativasapp.ui.common.Header
import com.example.areasrecreativasapp.ui.home.BookViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookScreen(viewModel: BookViewModel) {
    //val bookList by remember { viewModel.bookListFlow }.collectAsState(initial = emptyList())
    val bookList by viewModel.bookListFlow.collectAsState(emptyList())

    val isLoading by viewModel.isLoading.collectAsState(true)

    if (isLoading) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(ScrollState(0))
        ) {
            Header(name = stringResource(id = R.string.header_label_book))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                bookList.forEach { book ->
                    // Display each book
                    BookTag(viewModel = viewModel, book)
                }
            }
        }
    }
}

@Composable
fun BookTag(viewModel: BookViewModel, book : Book) {

    var showDialog by remember { mutableStateOf(false) }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showDialog = true
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BookTagHeader(book = book)
            BookTagBody(book = book)
        }
    }

    if (showDialog) {
        CancelDialog(onDismiss = { showDialog = false}, book, viewModel)
    }
}

@Composable
fun CancelDialog (onDismiss : () -> Unit, book : Book, viewModel : BookViewModel) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = book.name.uppercase(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.remove_text),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column (
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.removeBook(book.id)
                        onDismiss()
                              },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text(text = "Aceptar")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onDismiss() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

@Composable
fun BookTagHeader (book : Book) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Area Recreativa".uppercase(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = book.name.uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BookTagBody(book : Book) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BookTagDates(book = book)
        BookTagRequested(book = book)
    }
}

@Composable
fun BookTagDates(book : Book) {
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text (
            text = "Periodo de validez".uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Row (
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowCircleUp,
                contentDescription = "Navigation Icon"
            )
            Text(text = book.dayIn)
            Icon(
                imageVector = Icons.Rounded.ArrowCircleDown,
                contentDescription = "Navigation Icon"
            )
            Text(text = book.dayOut)
        }
    }
}

@Composable
fun BookTagRequested(book : Book) {
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text (
            text = "Permiso para".uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.DirectionsBus,
                contentDescription = "Navigation Icon"
            )
            Text(text = book.campers)
            Icon(
                imageVector = Icons.Rounded.House,
                contentDescription = "Navigation Icon"
            )
            Text(text = book.tents)
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Navigation Icon"
            )
            Text(text = book.persons)

        }
    }
}