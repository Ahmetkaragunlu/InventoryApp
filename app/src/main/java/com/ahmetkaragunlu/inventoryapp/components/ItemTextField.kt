package com.ahmetkaragunlu.inventoryapp.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
        colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFe7e0eb)),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    )
}