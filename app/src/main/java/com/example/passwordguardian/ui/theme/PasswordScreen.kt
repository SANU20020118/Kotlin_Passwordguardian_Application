package com.example.passwordguardian.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordguardian.model.PasswordUtils

@Composable
fun PasswordScreen() {
    var password by remember { mutableStateOf("") }
    var strength by remember { mutableStateOf(0) }
    var suggestedPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Video at the Top
        PasswordStrengthVideo(
            strength = strength,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Choose a Strong Password", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = password,
            onValueChange = {
                password = it
                strength = PasswordUtils.calculatePasswordStrength(it)
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { showPassword = !showPassword }) {
            Text(if (showPassword) "🙈 Hide" else "👁️ Show")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Password Strength Indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(strength / 5f)
                    .height(12.dp)
                    .background(
                        when {
                            strength < 3 -> Color.Red
                            strength < 4 -> Color.Yellow
                            else -> Color.Green
                        }
                    )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Password Strength: ${when {
                strength < 3 -> "Weak"
                strength < 4 -> "Fair"
                else -> "Strong"
            }}",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (strength < 3) {
            Button(onClick = {
                suggestedPassword = PasswordUtils.generateSecurePassword()
                password = suggestedPassword
                strength = PasswordUtils.calculatePasswordStrength(password)
            }) {
                Text("Use Suggested Password")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Suggested Password: $suggestedPassword")
        }
    }
}
