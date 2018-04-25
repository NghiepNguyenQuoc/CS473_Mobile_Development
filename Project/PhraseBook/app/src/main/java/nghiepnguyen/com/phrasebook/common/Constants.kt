package nghiepnguyen.com.phrasebook.common

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

object Constants {
    val DATABASE_VN_NAME = "phrasebookVN.sqlite"
    val DATABASE_HQ_NAME = "phrasebookHQ.sqlite"
    val DATABASE_NB_NAME = "phrasebookNB.sqlite"
    val DATABASE_TQ_NAME = "phrasebookTQ.sqlite"

    val VIETNAMESE = "vietnamese/"
    val KOREAN = "korean/"
    val JAPANESE = "japanese/"
    val CHINESE = "chinese/"
    val DBLOCATION = "/data/data/nghiepnguyen.com.phrasebook/databases/"

    val BUNDLE_DATABASE = "DATABASE_NAME"
    val BUNDLE_LANGUAGE = "BUNDLE_LANGUAGE"
    val BUNDLE_LOCK = "LOCK"
    val BUNDLE_LINK = "BUNDLE_LINK"
    val BUNDLE_CATEGORY = "BUNDLE_CATEGORY"
    val BUNDLE_CATEGORY_TEXT = "BUNDLE_CATEGORY_TEXT"
    val VOICE_RECOGNITION_REQUEST_CODE = 1234

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    fun convertDpToPixel(dp: Float, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    fun convertPixelsToDp(px: Float, context: Context): Int {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }
}
