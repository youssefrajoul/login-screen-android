package com.g59939.mobg6.ui

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.g59939.mobg6.R
import com.g59939.mobg6.ui.theme.Mobg6Theme

/**
 * Composable that displays the email address input,
 * [loginButtonClick] lambda that triggers the navigation to next screen
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = viewModel(),
    loginButtonClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Column(
        modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailTextField(
            inputType = stringResource(id = R.string.email),
            appViewModel = appViewModel,
        )
        PasswordTextField(
            inputType = stringResource(id = R.string.password),
            appViewModel = appViewModel,
        )
        Row {
            Button(onClick = {
                if (appViewModel.email.isEmpty() || appViewModel.password.isEmpty()) {
                    Toast.makeText(context,"Please provide your email & password",Toast.LENGTH_SHORT).show()
                } else if (!Patterns.EMAIL_ADDRESS.matcher(appViewModel.email).matches()) {
                    Toast.makeText(context, "Invalid email address", Toast.LENGTH_SHORT).show()
                }
                if (Patterns.EMAIL_ADDRESS.matcher(appViewModel.email).matches() && appViewModel.password.isNotEmpty()
                ) {
                    appViewModel.checkEmail(
                        loginButtonClick,
                        errorLogin = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        })
                    Log.d("MainViewModel", "OnOkButtonClicked")
                }
            }) {
                Text(text = stringResource(id = R.string.log_in_button))
            }
        }
    }
}

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    inputType: String = "",
    appViewModel: AppViewModel = viewModel(),
) {
    TextField(
        modifier = modifier
            .padding(5.dp),
        value = appViewModel.email,
        onValueChange = {
            appViewModel.email = it
        },
        label = { Text(text = inputType) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { }
        ),
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    inputType: String = "",
    appViewModel: AppViewModel,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        modifier = modifier
            .padding(5.dp),
        value = appViewModel.password,
        onValueChange = {
            appViewModel.password = it
        },
        label = { Text(text = inputType) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {}
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                )
            }
        }

    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    Mobg6Theme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(appViewModel = viewModel(), loginButtonClick = {})
        }
    }
}