package com.example.unihub.uniclub.presentation.authen.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {newValue ->
            onValueChange(newValue)
        },
        label = {
            Text(text = label)
        },
//        maxLines = 1,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Email,
//            imeAction = imeAction
//        ),
//        isError = isError,
//        supportingText = {
//            if (isError) {
//                ErrorTextInputField(text = errorText)
//            }
//        }
    )
}

@Composable
fun ErrorTextInputField(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.error
    )
}