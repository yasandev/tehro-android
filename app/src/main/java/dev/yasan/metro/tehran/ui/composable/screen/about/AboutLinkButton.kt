package dev.yasan.metro.tehran.ui.composable.screen.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import dev.yasan.helper.library.WebHelper
import dev.yasan.helper.library.isURL
import dev.yasan.metro.tehran.R
import dev.yasan.metro.tehran.ui.theme.grid

@Composable
fun AboutLinkButton(
    icon: Painter,
    url: String,
    contentDescription: String,
    linkTitle: String
) {
    val context = LocalContext.current
    if (url.isNotBlank() && url.isURL()) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = colorResource(id = R.color.text_title),
            modifier = Modifier
                .padding(grid())
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {
                    WebHelper.openWebView(context, url)
                }
                .padding(grid())
        )
    }
}

@Composable
fun AboutLinkButton(
    icon: ImageVector,
    url: String,
    contentDescription: String,
    linkTitle: String
) {
    if (url.isNotBlank() && url.isURL()) {
        val context = LocalContext.current
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = colorResource(id = R.color.text_title),
            modifier = Modifier
                .padding(grid())
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {
                    WebHelper.openWebView(context, url)
                }
                .padding(grid())
        )
    }
}