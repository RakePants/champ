package com.example.hacktask

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.hacktask.navigation.AppNavigation
import com.example.hacktask.ui.theme.HacktaskTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    val a = mutableStateOf<Double>(0.0)
    val b = mutableStateOf<Double>(0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        // Проверяем, есть ли у нас разрешение на доступ к местоположению
//
//        // Проверяем, есть ли у нас разрешение на доступ к местоположению
//        if (ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Если разрешение есть, запрашиваем местоположение
//            fusedLocationClient!!.lastLocation
//                .addOnSuccessListener(
//                    this
//                ) { location ->
//                    // Получаем местоположение
//                    if (location != null) {
//                        val latitude = location.latitude
//                        val longitude = location.longitude
//                        a.value = latitude
//                        b.value = longitude
//                        // Используем полученное местоположение
//                        // Например, выводим координаты на экран
//                        Toast.makeText(
//                            this@BaseActivity,
//                            "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//        } else {
//            // Если разрешения нет, запрашиваем разрешение у пользователя
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION),
//                1
//            )
//        }

        setContent {
            val navController = rememberNavController()
            HacktaskTheme {
                AppNavigation(navHostController = navController)
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//
//
//                    var bitmap by remember {
//                        mutableStateOf<Bitmap?>(null)
//                    }
//
//                    val cameraLauncher = rememberLauncherForActivityResult(
//                        contract = ActivityResultContracts.TakePicturePreview(),
//                        onResult = { newImage ->
//                            bitmap = newImage
//                        }
//                    )
//
//                    val permissionLauncher = rememberLauncherForActivityResult(
//                        ActivityResultContracts.RequestPermission()
//                    ) { isGranted ->
//                        if (isGranted) {
//                            cameraLauncher.launch(null)
//                        }
//                    }
//                    Column {
//                        bitmap?.let {
//                            Image(
//                                bitmap = it.asImageBitmap(),
//                                contentDescription = null,
//                                modifier = Modifier
//                                    .clip(CircleShape)
//                                    .size(100.dp)
//                            )
//                        }
//
//                        val context = LocalContext.current
//
//                        TextButton(
//                            onClick = {
//                                // Checks if the permission is granted
//                                val permissionCheckResult =
//                                    ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
//
//                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//                                    // The permission is already granted
//                                    cameraLauncher.launch(null)
//                                } else {
//                                    // Launches the permission request
//                                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
//                                }
//                            }
//                        ) {
//                            Text(
//                                text = "Use camera"
//                            )
//                        }
//                        Text(
//                            text = a.value.toString()
//                        )
//                        Text(
//                            text = b.value.toString()
//                        )
//                    }
//                }
            }
        }
    }
}