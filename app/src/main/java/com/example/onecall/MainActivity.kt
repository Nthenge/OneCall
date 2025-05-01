package com.example.onecall
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onecall.ui.theme.OneCallTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.foundation.text.*
import androidx.compose.ui.text.input.PasswordVisualTransformation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneCallTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "landing") {
                    composable("landing") { LandingScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("signup") { SignupScreen(navController) }
                    composable("home") { OneCallHomeScreen(navController) }
                    composable("garage") { ServiceScreen("Garage Services") }
                    composable("hospital") { ServiceScreen("Hospital Services") }
                    composable("fire") { ServiceScreen("Fire Services") }
                    composable("police") { ServiceScreen("Police Services") }
                }
            }
        }
    }
}


@Composable
fun OneCallHomeScreen(navController: NavHostController) {
    val services = listOf(
        Triple("Garage", Color(0xFF4CAF50), "garage"),
        Triple("Hospital", Color(0xFFADD8E6), "hospital"),
        Triple("Fire", Color(0xFFFFA500), "fire"),
        Triple("Police", Color(0xFFBDBDBD), "police")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "OneCall", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            services.chunked(2).forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { (service, color, route) ->
                        ServiceButton(serviceName = service, color = color) { navController.navigate(route) }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* Handle Emergency Call */ },
            colors = ButtonDefaults.buttonColors(Color.Green),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Emergency Call", color = Color.White)
        }
    }
}

@Composable
fun ServiceButton(serviceName: String, color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(color, shape = RoundedCornerShape(12.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = serviceName, fontSize = 18.sp, color = Color.White)
    }
}

@Composable
fun ServiceScreen(serviceName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = serviceName, fontSize = 24.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun OneCallPreview() {
    OneCallTheme {
        val navController = rememberNavController()
        OneCallHomeScreen(navController)
    }
}

@Composable
fun LandingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to OneCall",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 30.dp)
        )

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
        ) {
            Text(text = "Login")
        }

        Button(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }
    }
}


@Composable
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("home") }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Don't have an account? Sign Up",
            color = Color.Blue,
            modifier = Modifier.clickable { navController.navigate("signup") }
        )
    }
}

@Composable
fun SignupScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Full Name") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("home") }) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Already have an account? Login",
            color = Color.Blue,
            modifier = Modifier.clickable { navController.navigate("login") }
        )
    }
}


