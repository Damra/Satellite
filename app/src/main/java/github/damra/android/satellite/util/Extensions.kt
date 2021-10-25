package github.damra.android.satellite.util

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT

infix fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null

fun TextView.fromHtml(html: String) {
    text = HtmlCompat.fromHtml(html, FROM_HTML_MODE_COMPACT)
}