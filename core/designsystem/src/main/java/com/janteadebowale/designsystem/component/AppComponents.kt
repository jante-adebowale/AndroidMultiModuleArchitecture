package com.janteadebowale.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janteadebowale.designsystem.R
import com.janteadebowale.designsystem.theme.MTMTheme

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
fun PasswordComponent(
    label: String = "Password",
    modifier: Modifier = Modifier,
    value: String,
    showError: Boolean = false,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val isPasswordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        isError = showError,
        onValueChange = { onValueChanged(it) },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
            )
        },

        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            val icon =
                if (isPasswordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

            val description = if (isPasswordVisible.value) "Hide Password" else "Show Password"

            IconButton(onClick = {
                isPasswordVisible.value = !isPasswordVisible.value
            }) {
                Icon(imageVector = icon, contentDescription = description)
            }
        },
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )

}

@Composable
fun TextFieldComponent(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    showError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onValueChanged: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        isError = showError,
        enabled = enabled,
        readOnly = readOnly,
        onValueChange = { onValueChanged(it) },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}


@Composable
fun UsernamePhoneComponent(
    modifier: Modifier = Modifier,
    value: String,
    showError: Boolean = false,
    onValueChanged: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.width(110.dp),
            value = "+234",
            isError = showError,
            onValueChange = { onValueChanged(it) },
            label = {
                Text(
                    text = "Country",
                    style = MaterialTheme.typography.labelLarge,
                )
            },
            placeholder = {
                Text(text = "Country")
            },
            readOnly = true,
            leadingIcon = {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(R.drawable.nigeria),
                    contentDescription = null
                )
            },
            keyboardActions = keyboardActions,
            singleLine = true,
            maxLines = 1
        )

        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = value,
            isError = showError,
            onValueChange = { onValueChanged(it) },
            label = {
                Text(
                    text = "Phone",
                    style = MaterialTheme.typography.labelLarge,
                )
            },

            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            maxLines = 1

        )
    }


}


@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String,
    enableState: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {

    Button(
        enabled = enableState,
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Min),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .alpha(if (isLoading) 1f else 0f)
                    .size(20.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier.alpha(if (isLoading) 0f else 1f),
                text = text,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    .copy(fontSize = 14.sp)

            )
        }

    }
}



@Preview(
    showBackground = true
)
@Composable
fun UsernamePhoneComponentPreview(modifier: Modifier = Modifier) {
    MTMTheme {
        UsernamePhoneComponent(value = "Phone")
    }
}


