package com.akira.island.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akira.island.core.tr
import com.akira.island.ui.Screen
import com.akira.island.ui.Ui

@Composable
fun NexusBrandPanel(open: (Screen) -> Unit) {
    val pulse = rememberInfiniteTransition(label = "nexusPulse")
    val glow by pulse.animateFloat(0.18f, 0.52f, infiniteRepeatable(tween(1900, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "glow")
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(30.dp))
            .background(Brush.linearGradient(listOf(Color(0xFF111D32), Color(0xFF101020), Color(0xFF211637))))
            .border(1.dp, Brush.linearGradient(listOf(Ui.Primary.copy(alpha = .65f), Ui.Pink.copy(alpha = .42f), Ui.Divider)), RoundedCornerShape(30.dp))
            .drawBehind {
                drawCircle(Ui.Primary.copy(alpha = glow * .20f), size.width * .34f, Offset(size.width * .88f, size.height * .12f))
                drawCircle(Ui.Pink.copy(alpha = glow * .12f), size.width * .28f, Offset(size.width * .10f, size.height * .90f))
            }.padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            Box(Modifier.size(9.dp).clip(CircleShape).background(Ui.Primary))
            Text("N E X U S", color = Ui.Primary, fontSize = 12.sp, fontWeight = FontWeight.Black, letterSpacing = 3.sp)
            Spacer(Modifier.weight(1f))
            Text("01 / LIVE", color = Ui.Sub, fontSize = 10.sp, letterSpacing = 1.4.sp)
        }
        Spacer(Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(tr("ТВОЙ ТЕЛЕФОН. НОВЫЙ УРОВЕНЬ.", "YOUR PHONE. REIMAGINED."), color = Ui.Sub, fontSize = 10.sp, letterSpacing = 1.1.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(7.dp))
                Text("Beyond\nthe screen.", color = Ui.Text, fontSize = 31.sp, lineHeight = 34.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = (-1).sp)
                Spacer(Modifier.height(9.dp))
                Text(tr("Умная капсула для всего, что происходит.", "One intelligent capsule for everything happening."), color = Ui.Sub, fontSize = 12.sp, lineHeight = 17.sp)
            }
            NexusOrb(glow)
        }
        Spacer(Modifier.height(22.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            ActionChip("TRY THE ISLAND", Icons.Rounded.Bolt, Modifier.weight(1f)) { open(Screen.TESTS) }
            ActionChip("CUSTOMIZE", Icons.Rounded.Tune, Modifier.weight(1f), true) { open(Screen.COLORS) }
        }
        Spacer(Modifier.height(17.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("●", color = Ui.Primary, fontSize = 9.sp)
            Text("  SYSTEM READY", color = Ui.Sub, fontSize = 9.sp, letterSpacing = 1.4.sp)
            Spacer(Modifier.weight(1f))
            Text("CYBER GLASS / NEXUS", color = Ui.Sub.copy(alpha = .72f), fontSize = 8.sp, letterSpacing = 1.sp)
        }
    }
}

@Composable
private fun NexusOrb(glow: Float) {
    Box(Modifier.size(112.dp), contentAlignment = Alignment.Center) {
        Box(Modifier.size(106.dp).border(1.dp, Ui.Primary.copy(alpha = .18f + glow * .25f), CircleShape))
        Box(Modifier.size(82.dp).border(1.dp, Ui.Pink.copy(alpha = .28f), CircleShape))
        Box(Modifier.size(60.dp).clip(CircleShape).background(Brush.radialGradient(listOf(Color(0xFF273F62), Color(0xFF111827), Color(0xFF090D18)))).border(1.dp, Ui.Primary.copy(alpha = .8f), CircleShape), contentAlignment = Alignment.Center) {
            Text("N", color = Ui.Text, fontSize = 30.sp, fontWeight = FontWeight.Black, letterSpacing = (-2).sp)
        }
        Box(Modifier.align(Alignment.TopEnd).size(8.dp).clip(CircleShape).background(Ui.Primary))
        Box(Modifier.align(Alignment.BottomStart).size(6.dp).clip(CircleShape).background(Ui.Pink))
    }
}

@Composable
private fun ActionChip(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier = Modifier, violet: Boolean = false, onClick: () -> Unit) {
    val accent = if (violet) Ui.Pink else Ui.Primary
    Row(modifier.clip(RoundedCornerShape(15.dp)).background(accent.copy(alpha = .12f))
        .border(1.dp, accent.copy(alpha = .32f), RoundedCornerShape(15.dp)).clickable(onClick = onClick)
        .padding(horizontal = 11.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Icon(icon, null, tint = accent, modifier = Modifier.size(16.dp))
        Spacer(Modifier.size(7.dp))
        Text(label, color = Ui.Text, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = .8.sp)
    }
}