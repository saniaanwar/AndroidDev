package com.example.owlapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyApp(this)
        }
    }
}
@Composable
fun StudyApp(context: Context) {
    val backgroundResId = R.drawable.bg
    val tintedCardColor = Color(0xFF101010) // Semi-transparent black

    Box(
        modifier = Modifier
            .background(Color.LightGray) // Set the background color to light blue
    ) {
        Image(
            painter = painterResource(id = backgroundResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "LATEST ARTICLES",
                fontSize = 36.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black,
                modifier = Modifier
                    .padding(18.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            for (index in 0 until 4) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clickable {
                            val targetActivity = when (index) {
                                0 -> MainActivity2::class.java
                                1 -> MainActivity3::class.java
                                2 -> MainActivity4::class.java
                                3 -> MainActivity5::class.java
                                else -> MainActivity2::class.java
                            }
                            context.startActivity(Intent(context, targetActivity))
                        },
                    elevation = 8.dp,
                    backgroundColor = tintedCardColor,
                    shape = AbsoluteRoundedCornerShape(16.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painterResource(id = when (index) {
                                0 -> R.drawable.ai
                                1 -> R.drawable.neom
                                2 -> R.drawable.meta
                                3 -> R.drawable.ar
                                else -> R.drawable.ai
                            }),
                            contentDescription = "Course Image",
                            modifier = Modifier
                                .height(150.dp)
                                .scale(scaleX = 1.2F, scaleY = 1.2F)
                        )

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
