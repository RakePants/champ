package com.example.hacktask.features.add_order

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.hacktask.features.add_order.presentation.AddOrderViewModel
import com.example.hacktask.features.add_order.view.AddressTextField
import com.example.hacktask.features.add_order.view.CreateBtn
import com.example.hacktask.features.add_order.view.DescriptionTextField
import com.example.hacktask.features.add_order.view.HeaderText
import com.example.hacktask.features.add_order.view.LatitudeTextField
import com.example.hacktask.features.add_order.view.LongitudeTextField
import com.example.hacktask.features.add_order.view.TakePhotoBtn
import com.example.hacktask.features.add_order.view.TypeSelect
import com.example.hacktask.features.add_order.view.VolumeTextField
import com.example.hacktask.ui.theme.dimens
import org.orbitmvi.orbit.compose.collectAsState
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrderBasic(
    modifier: Modifier = Modifier,
    viewModel: AddOrderViewModel,
    navHostController: NavHostController,
) {
    val localState = viewModel.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = modifier.fillMaxWidth(),
                title = { HeaderText() },
                navigationIcon = {
                    IconButton(onClick = {navHostController.popBackStack()}) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            "backBtn",
                            modifier = modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            )
        },
        content = {
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background,
            ) {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                        //.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap2))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.bottomGap)
                    ) {
                        LatitudeTextField(modifier = modifier.weight(1f), viewModel = viewModel)
                        LongitudeTextField(modifier = modifier.weight(1f), viewModel = viewModel)
                        Button(
                            modifier = modifier
                                .padding(end = MaterialTheme.dimens.gapBetweenComponentScreen)
                                .weight(0.4f),
                            onClick = { viewModel.getCoords(context) },
                            shape = RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius)
                        ) {
                            Icon(Icons.Rounded.LocationOn, contentDescription = null, modifier = modifier.size(30.dp))
                        }
                    }
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap))
                    AddressTextField(viewModel = viewModel)
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap))
                    TypeSelect(viewModel = viewModel)
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap))
                    VolumeTextField(viewModel = viewModel)
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap))
                    DescriptionTextField(viewModel = viewModel)
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap))
                    if (viewModel.picture.value != null) {
                        Column {
                            viewModel.picture.value?.let {
                                Image(
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(MaterialTheme.dimens.buttonCornerRadius))
                                        .size(170.dp)
                                )
                            }
                        }
                    }
                    else {
                        val cameraLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.TakePicturePreview(),
                            onResult = { newImage ->
                                viewModel.picture.value = newImage
                            }
                        )

                        val permissionLauncher = rememberLauncherForActivityResult(
                            ActivityResultContracts.RequestPermission()
                        ) { isGranted ->
                            if (isGranted) {
                                cameraLauncher.launch(null)
                            }
                        }
                        Column {
                            TakePhotoBtn(
                                onClick = {
                                    // Checks if the permission is granted
                                    val permissionCheckResult =
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            android.Manifest.permission.CAMERA
                                        )

                                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                        // The permission is already granted
                                        cameraLauncher.launch(null)
                                    } else {
                                        // Launches the permission request
                                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
                                    }
                                },
                            )
                        }
                    }
                    Spacer(modifier = modifier.padding(top = MaterialTheme.dimens.bottomGap))
                    Box(
                        modifier = modifier
                            .fillMaxSize()

                            .padding(bottom = MaterialTheme.dimens.bottomGap2),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        CreateBtn(
                            viewModel = viewModel,
                            navHostController = navHostController
                        )
                    }
                }
            }
        }
    )
}

fun convertBitmapToBase64(bitmap: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream) // PNG или JPEG в зависимости от формата
    val byteArray = outputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

//
//@Composable
//fun createButtonClick() {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//
//        var bitmap by remember {
//            mutableStateOf<Bitmap?>(null)
//        }
//
//        val cameraLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.TakePicturePreview(),
//            onResult = { newImage ->
//                bitmap = newImage
//            }
//        )
//
//        val permissionLauncher = rememberLauncherForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if (isGranted) {
//                cameraLauncher.launch(null)
//            }
//        }
//        Column {
//            bitmap?.let {
//                Image(
//                    bitmap = it.asImageBitmap(),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .size(100.dp)
//                )
//            }
//
//            val context = LocalContext.current
//
//            TextButton(
//                onClick = {
//                    // Checks if the permission is granted
//                    val permissionCheckResult =
//                        ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
//
//                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
//                        // The permission is already granted
//                        cameraLauncher.launch(null)
//                    } else {
//                        // Launches the permission request
//                        permissionLauncher.launch(android.Manifest.permission.CAMERA)
//                    }
//                }
//            ) {
//                Text(
//                    text = "Use camera"
//                )
//            }
////            Text(
////                text = a.value.toString()
////            )
////            Text(
////                text = b.value.toString()
////            )
//        }
//    }
//}