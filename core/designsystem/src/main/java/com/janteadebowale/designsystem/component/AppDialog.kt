package com.janteadebowale.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.janteadebowale.designsystem.R
import com.janteadebowale.designsystem.theme.Poppins
import com.janteadebowale.designsystem.theme.infoContainer
import com.janteadebowale.designsystem.theme.onInfoContainer
import com.janteadebowale.designsystem.theme.onSuccessContainer
import com.janteadebowale.designsystem.theme.successContainer
import kotlinx.coroutines.delay

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

@Composable
fun LogoutDialog(
    info: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    title: String? = null,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {

    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        icon = icon,
        title = {
            title?.let {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins,
                    fontSize = 15.sp
                )
            }

        }, text = {
            Text(text = info)
        }, onDismissRequest = {
            // onCancel()
        }, confirmButton = {

            TextButton(onClick = { onConfirm() }) {
                Text(
                    text = stringResource(R.string.confirm),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {

            TextButton(onClick = { onCancel() }) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        })
}


@Composable
fun ToastDialog(
    state: DialogState,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false
        ), onDismissRequest = {

        }) {
        LaunchedEffect(Unit) {
            delay(4000L)
            onDismissRequest()
        }
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = when (state.type) {
                DialogType.Success -> MaterialTheme.colorScheme.successContainer
                DialogType.Error -> MaterialTheme.colorScheme.errorContainer
                DialogType.Info -> MaterialTheme.colorScheme.infoContainer
            },
            tonalElevation = 8.dp,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(45.dp),
                    imageVector = state.type.icon,
                    contentDescription = null,
                    tint = when (state.type) {
                        DialogType.Success -> MaterialTheme.colorScheme.onSuccessContainer
                        DialogType.Error -> MaterialTheme.colorScheme.onErrorContainer
                        DialogType.Info -> MaterialTheme.colorScheme.onInfoContainer
                    }
                )
                Text(
                    text = state.message,
                    textAlign = TextAlign.Center,
                    color = when (state.type) {
                        DialogType.Success -> MaterialTheme.colorScheme.onSuccessContainer
                        DialogType.Error -> MaterialTheme.colorScheme.onErrorContainer
                        DialogType.Info -> MaterialTheme.colorScheme.onInfoContainer
                    }
                )
            }
        }

    }
}

enum class DialogType(val icon: ImageVector) {
    Success(Icons.Filled.Check),
    Error(Icons.Filled.Cancel),
    Info(Icons.Filled.Info)
}

data class DialogState(
    val message: String = "",
    val type: DialogType = DialogType.Success
)


@Composable
fun InfoDialog(
    info: String,
    modifier: Modifier = Modifier,
    delayValue:Long = 4000L,
    onDismissRequest: () -> Unit
) {

    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false
        ), onDismissRequest = {

        }) {
        LaunchedEffect(Unit) {
            delay(delayValue)
            onDismissRequest()
        }
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.infoContainer,
            tonalElevation = 8.dp,
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(45.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onInfoContainer
                )
                Text(
                    text = info,
                    textAlign = TextAlign.Center,
                    color =  MaterialTheme.colorScheme.onInfoContainer
                )

                TextButton(onClick = { onDismissRequest() }) {
                    Text(
                        text = stringResource(R.string.ok),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

    }
}
