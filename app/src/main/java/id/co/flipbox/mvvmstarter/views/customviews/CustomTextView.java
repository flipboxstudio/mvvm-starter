package id.co.flipbox.mvvmstarter.views.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import id.co.flipbox.mvvmstarter.R;

/**
 * Created by bukhoriaqid on 11/11/16. based on https://futurestud.io/tutorials/custom-fonts-on-android-extending-textview
 * https://developer.android.com/training/custom-views/create-view.html
 */

public class CustomTextView extends TextView
{

    public CustomTextView (Context context)
    {
        super(context);
        applyCustomFont(context, "font/Roboto-Regular.ttf"); // default font
    }

    public CustomTextView (Context context, AttributeSet attrs)
    {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomTextView (Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    public void applyCustomFont (Context context, String fontPath)
    {
        Typeface customFont = FontCache.getTypeface(fontPath, context);
        setTypeface(customFont);
    }

    public void applyCustomFont (Context context, AttributeSet attrs)
    {
        String     fontPath = "font/Roboto-Regular.ttf";
        TypedArray temp     = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView, 0, 0);
        try
        {
            fontPath = temp.getString(R.styleable.CustomTextView_fontPath);
        }
        finally
        {
            applyCustomFont(context, fontPath);
            temp.recycle();
        }
    }
}
