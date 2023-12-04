package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.theme.AmphibiansTheme
import com.example.amphibians.ui.viewmodel.AmphibiansUiState

@Composable
fun HomeScreen(amphibiansUiState: AmphibiansUiState, modifier: Modifier = Modifier) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Success -> DisplayScreen(amphibiansUiState.photo)
        else -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun DisplayScreen(data: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn(
    modifier = modifier.fillMaxWidth(),
    contentPadding = PaddingValues(4.dp)
    ) {
        items(data) { data ->
            AmphibiansCard(
                data,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Composable
fun AmphibiansCard(data: Amphibian, modifier: Modifier) {
    Card {
        Column {
            Text(
                text = data.name
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(data.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = data.description,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = data.description
            )
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) { Amphibian("$it", "$it", "$it", "$it") }
        DisplayScreen(mockData)
    }
}