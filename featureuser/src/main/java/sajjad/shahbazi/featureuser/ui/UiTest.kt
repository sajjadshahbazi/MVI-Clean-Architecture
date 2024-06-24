package sajjad.shahbazi.featureuser.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UiTest (){

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }

}

@Preview
@Composable
fun MessageCard(message : Message){
    Text(text = "Sajjad")
}


data class Message(val author : String, val body : String)