package github.damra.android.satellite.util

import android.content.Context
import okio.IOException
import okio.buffer
import okio.source
import java.nio.charset.Charset

class Utils {

    fun getJson(context: Context, filePath: String): String? {
        try {
            val source = context.assets.open(filePath).source().buffer()
            return source.readByteString().string(Charset.forName("utf-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}