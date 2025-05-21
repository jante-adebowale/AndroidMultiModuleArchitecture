package com.janteadebowale.feature.home

import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.janteadebowale.core.model.ThemeConfig
import com.janteadebowale.core.ui.ConsumeNavEvents
import com.janteadebowale.core.ui.showToast
import com.janteadebowale.core.ui.supportWideScreen
import com.janteadebowale.designsystem.component.ActionBarOptions
import com.janteadebowale.designsystem.component.AppButton
import com.janteadebowale.designsystem.component.DialogState
import com.janteadebowale.designsystem.component.DialogType
import com.janteadebowale.designsystem.component.HomeTopBar
import com.janteadebowale.designsystem.component.InfoDialog
import com.janteadebowale.designsystem.component.LogoutDialog
import com.janteadebowale.designsystem.component.ToastDialog
import com.janteadebowale.designsystem.theme.MTMTheme
import com.janteadebowale.designsystem.theme.Poppins
import com.janteadebowale.feature.home.model.TransactionUi
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
fun HomeRoute(
    onLogout: () -> Unit,
    onPurchaseClick: () -> Unit,
    onCashClick : () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyBoardController = LocalSoftwareKeyboardController.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    var openAlertDialog by remember { mutableStateOf(false) }

    var showLogoutDialog by remember { mutableStateOf(false) }

    var forceLogout by remember { mutableStateOf(false) }

    if (showLogoutDialog) {
        LogoutDialog(
            title = stringResource(id = com.janteadebowale.core.ui.R.string.logout),
            info = stringResource(id = com.janteadebowale.core.ui.R.string.confirm_logout_message),
            onCancel = {
                showLogoutDialog = false
            },
            onConfirm = {
                showLogoutDialog = false
                viewModel.onEvent(HomeUiEvent.Logout)
            })
    }

    if (forceLogout) {
        InfoDialog(info = "Token Expired. Login required!", delayValue = 6000L) {
            forceLogout = false
            viewModel.onEvent(HomeUiEvent.Logout)
        }
    }


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

    HomeScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onLogout = {
            showLogoutDialog = true
        }, onPurchaseClick = {
            onPurchaseClick()
        },
        onCashClick = {
            onCashClick()
        }
    )

    ConsumeNavEvents(eventFlow = viewModel.homeNavChannel, lifecycleOwner = lifecycleOwner) {
        when (it) {
            is HomeNavEvent.OnError -> {
                keyBoardController?.hide()
                if (it.code.isNotEmpty() && it.code == "403") {
                    forceLogout = true
                } else {
                    context.showToast(it.code)
                    dialogState = DialogState(
                        message = it.errorMessage,
                        type = DialogType.Error
                    )
                    openAlertDialog = true
                }
            }

            HomeNavEvent.OnLogout -> {
                onLogout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    state: HomeState,
    onEvent: (HomeUiEvent) -> Unit,
    onLogout: () -> Unit,
    onPurchaseClick: () -> Unit = {},
    onCashClick: () -> Unit = {},
    onRefundClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                title = stringResource(com.janteadebowale.core.ui.R.string.home_title),
                subTitle = "Jante Adebowale",
                isLightMode = state.selectedTheme == ThemeConfig.LIGHT,
                startContent = {
                    Image(
                        painter = painterResource(id = if (LocalContentColor.current.luminance() < 0.5f) com.janteadebowale.designsystem.R.drawable.dark_logo else com.janteadebowale.designsystem.R.drawable.light_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },
                onActionClick = {
                    when (it) {
                        ActionBarOptions.OnLogout -> {
                            onLogout()
                        }

                        ActionBarOptions.OnRefresh -> onEvent(HomeUiEvent.Refresh)
                        ActionBarOptions.OnThemeChange -> {
                            onEvent(HomeUiEvent.ThemeChange(if (state.selectedTheme == ThemeConfig.LIGHT) ThemeConfig.DARK else ThemeConfig.LIGHT))
                        }

                        ActionBarOptions.OnSetting -> {

                        }

                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .supportWideScreen()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                MenuItem(
                    title = stringResource(com.janteadebowale.core.ui.R.string.purchase),
                    icon = Icons.Filled.ShoppingCart,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    onPurchaseClick()
                }
                MenuItem(
                    title = stringResource(com.janteadebowale.core.ui.R.string.cash),
                    icon = Icons.Outlined.CheckCircle,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    onCashClick()
                }
                MenuItem(
                    title = stringResource(com.janteadebowale.core.ui.R.string.refund),
                    icon = Icons.Filled.ShoppingCart,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    onRefundClick()
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Recent Transactions",
                            fontFamily = Poppins,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        TextButton(onClick = {}) {
                            Text(
                                text = "See all",
                                fontFamily = Poppins,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }

                items(state.recentTransactions) {
                    TransactionItem(data = it)
                }
            }

        }
    }
}


@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {


    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .fillMaxHeight()
            .width(110.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colorScheme.inverseOnSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = color)
        }


        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            fontFamily = Poppins,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}


@Composable
fun TransactionItem(data: TransactionUi, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = data.color),
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = data.type,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = data.time,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                )
            }

            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(data.color.copy(alpha = 0.1f)), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = data.amount,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
private fun TransactionUiPreview() {
    MTMTheme {
        TransactionItem(
            data = TransactionUi(
                type = "Purchase",
                amount = "10,000.00",
                time = "4 Nov, 2023 8:15pm"
            )
        )
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    MTMTheme {
        HomeScreen(
            state = HomeState(),
            onEvent = {},
            onLogout = {}
        )
    }
}
