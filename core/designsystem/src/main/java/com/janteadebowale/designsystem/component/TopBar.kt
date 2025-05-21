package com.janteadebowale.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.janteadebowale.designsystem.R
import com.janteadebowale.designsystem.icon.ArrowLeftIcon
import com.janteadebowale.designsystem.theme.MTMTheme
import com.janteadebowale.designsystem.theme.Poppins

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidMultiModuleArchitecture
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String,
    modifier: Modifier = Modifier,
    isLightMode: Boolean = false,
    subTitle: String? = null,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null,
    onActionClick: (ActionBarOptions) -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                startContent?.invoke()
                startContent?.let {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = Poppins,
                        fontSize = 16.sp
                    )
                    subTitle?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.ExtraLight,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = Poppins,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        actions = {
            IconButton(onClick = {
                onActionClick(ActionBarOptions.OnLogout)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }

            IconButton(onClick = {
                onActionClick(ActionBarOptions.OnRefresh)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(onClick = {
                onActionClick(ActionBarOptions.OnThemeChange)
            }) {
                Icon(
                    imageVector = if (isLightMode) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(onClick = {
                onActionClick(ActionBarOptions.OnSetting)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    subTitle: String? = null,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onBackClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = Poppins,
                        fontSize = 16.sp
                    )
                    subTitle?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.ExtraLight,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = Poppins,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = ArrowLeftIcon,
                    contentDescription = stringResource(id = R.string.go_back),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@ScreenPreview
@Composable
private fun OnePallyToolBarPreview() {
    MTMTheme {
        HomeTopBar(
            title = "SalesPally",
            modifier = Modifier.fillMaxWidth(),
            startContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                )
            },
            onActionClick = {}
        )
    }
}