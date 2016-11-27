package id.co.flipbox.mvvmstarter.views.customviews;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by bukhoriaqid on 11/11/16.
 */

public class FontCache
{
    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface (String fontpath, Context context)
    {
        Typeface typeface = fontCache.get(fontpath);

        if (typeface == null)
        {
            try
            {
                typeface = Typeface.createFromAsset(context.getAssets(), fontpath);
            }
            catch (Exception e)
            {
                return null;
            }

            fontCache.put(fontpath, typeface);
        }

        return typeface;
    }
}
