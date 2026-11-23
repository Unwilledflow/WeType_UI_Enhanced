package com.xposed.wetypehook

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
internal fun SwipeSymbolKeyboard(
    mappings: Map<String, String>,
    selectedKey: String?,
    onSelectKey: (String) -> Unit,
    onSelectSymbol: (String) -> Unit
) {
    val rows = listOf("1234567890", "qwertyuiop", "asdfghjkl", "zxcvbnm")
    val symbols = "!@#$%^&*()-_=+[]{};:'?,./<>|\\~`".map(Char::toString)
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(stringResource(R.string.settings_swipe_symbols_title))
        Spacer(Modifier.height(4.dp))
        Text(stringResource(R.string.settings_swipe_symbols_desc), color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        Spacer(Modifier.height(8.dp))
        rows.forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                row.forEach { key ->
                    val selected = key.toString() == selectedKey
                    Card(
                        modifier = Modifier.weight(1f).clickable { onSelectKey(key.toString()) },
                        insideMargin = androidx.compose.foundation.layout.PaddingValues(4.dp)
                    ) {
                        Column {
                            Text(key.toString())
                            Text(mappings[key.toString()] ?: "↑", color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                        }
                    }
                }
            }
            Spacer(Modifier.height(4.dp))
        }
        if (selectedKey != null) {
            Text(stringResource(R.string.settings_swipe_symbols_choose, selectedKey))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                symbols.take(12).forEach { symbol ->
                    Card(
                        modifier = Modifier.weight(1f).clickable { onSelectSymbol(symbol) },
                        insideMargin = androidx.compose.foundation.layout.PaddingValues(4.dp)
                    ) { Text(symbol) }
                }
            }
        }
    }
}
