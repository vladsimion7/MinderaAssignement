package com.vlad.simion.designsystem.widgets

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign? = null,
    textDecoration: TextDecoration? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    fontWeight: FontWeight? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = overflow,
        maxLines = maxLines,
        minLines = minLines
    )
}

@Composable
fun MTextDisplayLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.displayLarge,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextDisplayMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.displayMedium,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextDisplaySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.displaySmall,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextHeadlineLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextHeadlineMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextHeadlineSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextTitleLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextTitleMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextTitleSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextBodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextLabelLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextLabelMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun MTextLabelSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE
) {
    MText(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        maxLines = maxLines
    )
}
