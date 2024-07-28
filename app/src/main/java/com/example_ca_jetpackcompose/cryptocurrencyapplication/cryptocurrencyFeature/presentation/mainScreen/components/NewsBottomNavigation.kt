package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.mainScreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example_ca_jetpackcompose.cryptocurrencyapplication.R
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util.Dimensions

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 18.dp
    ) {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = bottomNavigationItem.icon),
                            contentDescription = null,
                            modifier = Modifier.size(Dimensions.IconSize)
                        )
                        Spacer(modifier = Modifier.height(Dimensions.SmallPadding))
                        Text(text = bottomNavigationItem.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.gray),
                    unselectedTextColor = colorResource(id = R.color.gray),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}
