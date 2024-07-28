package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example_ca_jetpackcompose.cryptocurrencyapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailsTopBar(
    marked: Boolean,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(imageVector = setMark(marked), contentDescription = null)
            }
        }
    )
}

fun setMark(marked: Boolean) : ImageVector {
    val imageVector: ImageVector = if (marked) {
        Icons.Default.Email
    } else {
        Icons.Default.MailOutline
    }
    return imageVector
}