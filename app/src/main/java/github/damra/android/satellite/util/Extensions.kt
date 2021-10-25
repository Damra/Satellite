package github.damra.android.satellite.util

import android.text.Html
import android.widget.TextView

infix fun <T> Boolean.then(param: () -> T): T? = if (this) param() else null

fun TextView.fromHtml(html: String) {
    text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
}
