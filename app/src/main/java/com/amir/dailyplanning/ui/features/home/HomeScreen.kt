package com.amir.dailyplanning.ui.features.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavViewModel

@Composable
fun HomeScreen() {

    val viewModel = getNavViewModel<HomeScreenViewModel>()
    val context = LocalContext.current

    var message by remember { mutableStateOf("") }
    var sendMessage by remember { mutableStateOf("") }

    val color = listOf(
        Color(0, 184, 212, 255)
        , Color(0, 145, 234, 255)
    )


    LaunchedEffect(sendMessage) {
        if (sendMessage.isNotEmpty() && sendMessage.isNotBlank()) {
            viewModel.getData(sendMessage)
        }
    }

    val messages by viewModel.messages.collectAsState()



    var errore = viewModel.error.collectAsState().value

    LaunchedEffect(errore) {
        if (errore?.message != null) {
            if (errore.message!!.isNotEmpty() && errore.message!!.isNotBlank()) {
                Toast.makeText(context, errore.message, Toast.LENGTH_SHORT).show()
            }
        }
    }




    TopBar()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp, top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        val listState = rememberLazyListState()


        LazyColumn (
            modifier = Modifier.fillMaxWidth()
                .weight(1F)
                .padding(8.dp),
            reverseLayout = true
        ){
           items(messages.reversed()) { msg ->
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(4.dp),
                   contentAlignment = if (msg.isUser) Alignment.CenterEnd else Alignment.CenterStart

               ) {

                   Text(
                       text = msg.text,
                       modifier = Modifier
                           .clip(RoundedCornerShape(12.dp))
                           .background(if (msg.isUser) Color(0xCE80DEEA) else Color(0xD281D4FA))
                           .padding(12.dp),
                       style = TextStyle(textDirection = TextDirection.Rtl)
                   )
               }


               LaunchedEffect(messages.size) {
                   if (messages.isNotEmpty()) {
                       listState.animateScrollToItem(0)
                   }

               }
           }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .widthIn(max = 250.dp)
                    .clip(RoundedCornerShape(4.dp)),
                value = message,
                onValueChange = { message = it },
                label = { Text("Enter Message") }
            )




            GradientButton(
                text = "Send",
                gradient = color,
                onClick = {
                    sendMessage = message
                    message = ""
                }
            )
        }
    }
}


//////////////////////////////////////////////////////////////////////////////////////////////////////

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                textAlign = TextAlign.Center,
                text = "Daily Planning",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    )
}
//////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun GradientButton(text: String, gradient: List<Color>, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(90.dp, 50.dp),
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(gradient),
                    shape = RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}