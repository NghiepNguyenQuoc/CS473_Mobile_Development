package nghiepnguyen.com.phrasebook.common;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Constants {
    public static final String DATABASE_VN_NAME = "phrasebookVN.sqlite";
    public static final String DATABASE_HQ_NAME = "phrasebookHQ.sqlite";
    public static final String DATABASE_NB_NAME = "phrasebookNB.sqlite";
    public static final String DATABASE_TQ_NAME = "phrasebookTQ.sqlite";

    public static final String VIETNAMESE = "vietnamese/";
    public static final String KOREAN = "korean/";
    public static final String JAPANESE = "japanese/";
    public static final String CHINESE = "chinese/";
    public static final String DBLOCATION = "/data/data/nghiepnguyen.com.phrasebook/databases/";

    public static final String BUNDLE_DATABASE = "DATABASE_NAME";
    public static final String BUNDLE_LOCK = "LOCK";
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static int convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int dp = (int) (px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
}
