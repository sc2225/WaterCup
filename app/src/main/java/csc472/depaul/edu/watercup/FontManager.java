package csc472.depaul.edu.watercup;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {

    public static final String ROOT = "fonts/",
    FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
