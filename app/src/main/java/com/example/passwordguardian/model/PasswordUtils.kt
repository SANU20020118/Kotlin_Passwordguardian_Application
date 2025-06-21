package com.example.passwordguardian.model


object PasswordUtils {
    // Function to calculate password strength
    fun calculatePasswordStrength(password: String): Int {
        var score = 0
        if (password.length >= 8) score += 1
        if (password.any { it.isUpperCase() }) score += 1
        if (password.any { it.isLowerCase() }) score += 1
        if (password.any { it.isDigit() }) score += 1
        if (password.any { "!@#\$%^&*()_-+=<>?/".contains(it) }) score += 1
        return score
    }

    // Function to generate a strong password
    fun generateSecurePassword(length: Int = 12): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#\$%^&*()"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}
