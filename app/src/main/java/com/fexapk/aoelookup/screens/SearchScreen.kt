package com.fexapk.aoelookup.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fexapk.aoelookup.R

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {

    val viewModel = remember { AoeViewModel() }
    val currentState = viewModel.uiState

    var playerQuery by remember { mutableStateOf("") }

    val boxModifier = Modifier.fillMaxSize()

    Column(modifier = modifier) {
        SearchBar(
            query = playerQuery,
            onQueryChange = {
                playerQuery = it
                viewModel.searchPlayers(it)
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(4.dp))
        when(currentState) {
            is UiState.Home -> HomeBox(boxModifier)
            is UiState.Error -> ErrorBox(boxModifier)
            is UiState.Loading -> LoadingBox(boxModifier)
            is UiState.Success -> PlayerList(currentState.players)
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = { onQueryChange(it) },
        placeholder = { Text(text = "Search player...") },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier
    )
}

@Composable
fun CenteredBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        content()
    }
}

@Composable
fun LoadingBox(modifier: Modifier = Modifier) {
    CenteredBox(
        modifier = modifier
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun HomeBox(modifier: Modifier = Modifier) {

    val isDark = isSystemInDarkTheme()
    val imageResource = if (isDark) R.drawable.roman_four_white else R.drawable.roman_four

    CenteredBox(modifier = modifier) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .alpha(0.5f)
        )
    }
}

@Composable
fun ErrorBox(modifier: Modifier = Modifier) {
    CenteredBox(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error)
            , contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    // Example usage of the SearchBar composable
    var query by remember { mutableStateOf("") }
    SearchBar(
        query = query,
        onQueryChange = { query = it },
        modifier = Modifier.padding(16.dp)
    )
}




