package com.applandeo.materialcalendarview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Mateusz Kornakiewicz on 03.08.2018.
 */

public final class CalendarUtils {

    /*
    Utils method to create drawable containing text
     */
    public static Drawable getDrawableText(Context context, String text, Typeface typeface, int color, int size) {
        Resources resources = context.getResources();
        Bitmap bitmap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setTypeface(typeface != null ? typeface : Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(ContextCompat.getColor(context, color));

        float scale = resources.getDisplayMetrics().density;
        paint.setTextSize((int) (size * scale));

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width()) / 2;
        int y = (bitmap.getHeight() + bounds.height()) / 2;
        canvas.drawText(text, x, y, paint);

        return new BitmapDrawable(context.getResources(), bitmap);
    }

    private CalendarUtils() {
    }
}
