package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import com.example.artspace.ui.theme.LightGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    var currentArtwork by remember {
        mutableStateOf(0)
    }

    var artworkList = listOf<Map<String, Any>>(
        mapOf(
            "image" to R.drawable.girl_with_red,
            "title" to "Girl With Red Dress",
            "artist" to "ZMH",
            "year" to 2023
        ),
        mapOf(
            "image" to R.drawable.test,
            "title" to "Test Card",
            "artist" to "Zin Min Htike",
            "year" to 2022
        ),
    )

    var changeImage: (String) -> Unit = {
        if (it == "Prev") {
            if (currentArtwork != 0) {
                currentArtwork--
            }
        } else {
            if (currentArtwork < artworkList.size -1) {
                currentArtwork++
            } else {
                currentArtwork = 0
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(40.dp)
    ) {

        Surface(modifier = Modifier, shadowElevation = 5.dp) {
            Image(
                painter = painterResource(id = artworkList[currentArtwork]["image"] as Int),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 40.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        ArtworkTitle(
            title = artworkList[currentArtwork]["title"] as String,
            artist = artworkList[currentArtwork]["artist"] as String,
            year = artworkList[currentArtwork]["year"] as Int
        )
        Spacer(modifier = Modifier.height(30.dp))
        ActionButton(action = changeImage)
    }
}

@Composable
fun ArtworkTitle(title: String, artist: String, year: Int) {
    Column(
        modifier = Modifier
            .background(LightGray)
            .fillMaxWidth()
            .padding(10.dp),

        ) {
        Text(text = "$title", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "$artist ($year)", color = Color.Gray)
    }
}

@Composable
fun ActionButton(action: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            action("Prev")
        }, modifier = Modifier.weight(1f)) {
            Text(text = "Previous")
        }

        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = { action("Next") }, modifier = Modifier.weight(1f)) {
            Text(text = "Next")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}