package com.akira.island.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** NEXUS cyberpunk glass palette: obsidian, neon cyan and violet. */
object Ui {
    val Bg = Color(0xFF070A12)
    val Card = Color(0xFF111827)
    val IconBg = Color(0xFF182338)
    val Primary = Color(0xFF00E5FF)
    val OnPrimary = Color(0xFF001C25)
    val Text = Color(0xFFEAFBFF)
    val Sub = Color(0xFF9AAFC4)
    val Divider = Color(0xFF263B52)
    val Nav = Color(0xFF0B1220)
    val Pink = Color(0xFFB99AFF)
    val Outline = Color(0xFF52758B)
}

object Tile {
    val Blue = Color(0xFF0A84FF)
    val Green = Color(0xFF34C759)
    val Orange = Color(0xFFFF9F0A)
    val Red = Color(0xFFFF453A)
    val Purple = Color(0xFF9B5CFF)
    val Pink = Color(0xFFFF2D78)
    val Teal = Color(0xFF00D9FF)
    val Indigo = Color(0xFF5E5CE6)
    val Yellow = Color(0xFFFFB800)
    val Gray = Color(0xFF8E8E93)
}

@Composable
fun IslandTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Ui.Primary, onPrimary = Ui.OnPrimary, secondary = Ui.Primary,
            background = Ui.Bg, onBackground = Ui.Text, surface = Ui.Card,
            onSurface = Ui.Text, surfaceVariant = Ui.IconBg, onSurfaceVariant = Ui.Sub,
            surfaceContainerHigh = Ui.Card, outline = Ui.Outline,
        ),
        typography = InterTypography,
        content = content,
    )
}