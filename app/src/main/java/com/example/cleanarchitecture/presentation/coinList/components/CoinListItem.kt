package com.example.cleanarchitecture.presentation.coinList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecture.domin.model.Coin

@Composable
fun CoinListItem(
    coin:Coin,
    onItemCLick :(Coin) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .clickable { onItemCLick(coin) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = if(coin.isActive) "active" else "inactive",
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            color = if(coin.isActive) Color.Green else Color.Red,
            modifier = Modifier.align(CenterVertically))
    }
}