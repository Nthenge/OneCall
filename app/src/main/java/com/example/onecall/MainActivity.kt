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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneCallTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
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
        Triple("Garage", Color.Yellow, "garage"),
        Triple("Hospital", Color.Blue, "hospital"),
        Triple("Fire", Color.Red, "fire"),
        Triple("Police", Color.Gray, "police")
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
