package com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.domain.model.Coin
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen.components.CoinDetailsTopBar
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen.components.CoinTag
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.coinDetailsScreen.components.TeamListItem
import com.example_ca_jetpackcompose.cryptocurrencyapplication.cryptocurrencyFeature.presentation.util.Dimensions
import com.google.accompanist.flowlayout.FlowRow

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun CoinDetailsScreen(
    navigateUp: () -> Unit,
    coin: Coin
) {
    val viewModel: CoinDetailsViewModel = hiltViewModel()
    viewModel.getCoinDetails(coin.id)
    viewModel.checkMarkCondition(coin)
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        CoinDetailsTopBar(
            marked = state.marked,
            onBookmarkClick = {
                viewModel.onEvent(CoinDetailsEvent.UpsertDeleteArticleEvent(coin = coin))
            },
            onBackClick = navigateUp
        )
        state.coinDetail?.let { coinDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(Dimensions.SmallPadding)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if(coinDetail.isActive) "active" else "inactive",
                            color = if(coinDetail.isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coinDetail.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coinDetail.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Team members",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coinDetail.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }
    }
}