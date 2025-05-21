package com.janteadebowale.feature.auth.register

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.janteadebowale.core.ui.ConsumeNavEvents
import com.janteadebowale.core.ui.supportWideScreen
import com.janteadebowale.designsystem.component.AppButton
import com.janteadebowale.designsystem.component.DialogState
import com.janteadebowale.designsystem.component.DialogType
import com.janteadebowale.designsystem.component.PasswordComponent
import com.janteadebowale.designsystem.component.TextFieldComponent
import com.janteadebowale.designsystem.component.ToastDialog
import com.janteadebowale.designsystem.component.UsernamePhoneComponent
import com.janteadebowale.designsystem.theme.MTMTheme
import org.koin.androidx.compose.koinViewModel

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
fun RegisterRoute(
    onNavUp: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    var openAlertDialog by remember { mutableStateOf(false) }

    var dialogState by remember {
        mutableStateOf(DialogState())
    }
    when {
        openAlertDialog -> {
            ToastDialog(state = dialogState) {
                openAlertDialog = false
            }
        }
    }

    ConsumeNavEvents(viewModel.registerNavChannel,lifecycleOwner) {
        when (it) {
            is RegisterNavEvent.Error -> {
                keyBoardController?.hide()
                dialogState = DialogState(
                    message = it.errorMessage,
                    type = DialogType.Error
                )
                openAlertDialog = true
            }
            is RegisterNavEvent.Success -> {
                keyBoardController?.hide()
                dialogState = DialogState(
                    message = it.message,
                    type = DialogType.Success
                )
                openAlertDialog = true
            }
        }
    }

    RegisterScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onBackToLogin = {
            onNavUp()
        }
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterUiEvent) -> Unit,
    onBackToLogin: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()
    val phoneFocusRequester = FocusRequester()
    val confirmPasswordFocusRequester = FocusRequester()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .supportWideScreen()
            .padding(vertical = 40.dp, horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {


        Text(
            text = stringResource(id = com.janteadebowale.core.ui.R.string.register_new_user),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
        )


        TextFieldComponent(
            label = stringResource(com.janteadebowale.core.ui.R.string.full_name),
            modifier = Modifier
                .fillMaxWidth(),
            value = state.name,
            onValueChanged = {
                onEvent(RegisterUiEvent.OnNameChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Unspecified
            ),
            keyboardActions = KeyboardActions(onNext = {
                phoneFocusRequester.requestFocus()
            })
        )

        UsernamePhoneComponent(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(phoneFocusRequester),
            value = state.phone,
            onValueChanged = {
                onEvent(RegisterUiEvent.OnPhoneChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone
            ),
            keyboardActions = KeyboardActions(onNext = {
                passwordFocusRequester.requestFocus()
            })
        )

        PasswordComponent(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester),
            value = state.password,
            onValueChanged = {
                onEvent(RegisterUiEvent.OnPasswordChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onNext = {
                confirmPasswordFocusRequester.requestFocus()
            })
        )

        PasswordComponent(
            label = stringResource(com.janteadebowale.core.ui.R.string.confirm_password),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(confirmPasswordFocusRequester),
            value = state.confirmPassword,
            onValueChanged = {
                onEvent(RegisterUiEvent.OnConfirmPasswordChanged(it))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onEvent(RegisterUiEvent.OnRegisterClicked)
            })
        )

        Spacer(modifier = Modifier.height(5.dp))


        AppButton(
            isLoading = state.isLoading,
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = com.janteadebowale.core.ui.R.string.register_btn_label),
            enableState = state.canRegister
        ) {
            if (!state.isLoading) {
                focusManager.clearFocus()
                onEvent(RegisterUiEvent.OnRegisterClicked)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(onClick = {
                if(!state.isLoading) {
                    onBackToLogin()
                }
            }) {
                Text(text = "Already have an Account?")
            }
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun RegisterScreenPreview() {
    MTMTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {},
            onBackToLogin = {}
        )
    }
}