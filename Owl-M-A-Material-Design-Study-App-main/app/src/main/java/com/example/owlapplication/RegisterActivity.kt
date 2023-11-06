package com.example.owlapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.owlapplication.ui.theme.OwlApplicationTheme

class RegisterActivity : ComponentActivity() {
    private lateinit var databaseHelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = UserDatabaseHelper(this)
        setContent {
            RegistrationScreen(this, databaseHelper)
        }
    }
}

@Composable
fun RegistrationScreen(context: Context, databaseHelper: UserDatabaseHelper) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0x90000000), // Customize the tinted box color here
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(170.dp)
                )

                Text(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    text = "Register",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = username,
                    shape = AbsoluteRoundedCornerShape(100.dp),
                    onValueChange = { username = it },
                    label = { Text(color = Color.White, text = "Enter your username") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        cursorColor = Color.LightGray,
                        textColor = Color.White
                    ),

                    leadingIcon = {
                        Icon(
                            Icons.Default.Person, contentDescription = "person", tint = Color.White
                        )
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(350.dp)
                )

                OutlinedTextField(
                    value = email,
                    shape = AbsoluteRoundedCornerShape(200.dp),
                    onValueChange = { email = it },
                    label = { Text(color = Color.White, text = "Enter your email") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        cursorColor = Color.LightGray,
                        textColor = Color.White
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.MailOutline,
                            contentDescription = "email",
                            tint = Color.White
                        )
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(350.dp)
                )

                OutlinedTextField(
                    value = password,
                    shape = AbsoluteRoundedCornerShape(200.dp),
                    onValueChange = { password = it },
                    label = { Text(color = Color.White, text = "Enter your password") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        cursorColor = Color.LightGray,
                        textColor = Color.White
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock, contentDescription = "password", tint = Color.White
                        )
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(350.dp)
                )


                if (error.isNotEmpty()) {
                    Text(
                        text = error,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                Button(
                    onClick = {
                        if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
                            val user = User(
                                id = null,
                                firstName = username,
                                lastName = null,
                                email = email,
                                password = password
                            )
                            databaseHelper.insertUser(user)
                            error = "User registered successfully"
                            // Start LoginActivity using the current context
                            context.startActivity(
                                Intent(
                                    context,
                                    LoginActivity::class.java
                                )
                            )

                        } else {
                            error = "Please fill all fields"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green.copy(alpha = 0.4f)),
                    shape = AbsoluteRoundedCornerShape(10.dp),
                    border = BorderStroke(width = 2.dp, color = Color.Black),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(140.dp)
                ) {
                    Text(text = "REGISTER", color = Color.White)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Spacer(modifier = Modifier.height(10.dp))

                Row() {
                    Text(
                        modifier = Modifier.padding(top = 14.dp),
                        text = "Have an account?",
                        color = Color.White
                    )
                    TextButton(onClick = {
                        context.startActivity(
                            Intent(
                                context,
                                LoginActivity::class.java
                            )
                        )
                    })

                    {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Log in", color = Color.Cyan)
                    }
                }
            }
        }
    }
}

private fun startLoginActivity(context: Context) {
    val intent = Intent(context, LoginActivity::class.java)
    ContextCompat.startActivity(context, intent, null)
}
