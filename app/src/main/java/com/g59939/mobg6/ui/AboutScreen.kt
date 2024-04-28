package com.g59939.mobg6.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.g59939.mobg6.R
import com.g59939.mobg6.ui.theme.Mobg6Theme

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.developer_name),
            modifier = Modifier.padding(10.dp)
        )
        Text(text = stringResource(id = R.string.developer_id), modifier = Modifier.padding(10.dp))
        Text(text = stringResource(id = R.string.group_id), modifier = Modifier.padding(10.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutPreview() {
    Mobg6Theme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            AboutScreen()
        }
    }
}