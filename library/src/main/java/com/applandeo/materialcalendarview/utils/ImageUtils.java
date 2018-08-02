package com.applandeo.materialcalendarview.utils;

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
import android.widget.ImageView;

/**
 * This class is used to load event image in a day cell
 * <p>
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class ImageUtils {

    public static void loadImage(ImageView imageView, Drawable resource) {
        if (resource == null) {
            return;
        }

        imageView.setImageDrawable(resource);
    }

    public static Drawable getDrawableText(Context context, String text, Typeface typeface, int color, int size) {
        Resources resources = context.getResources();
        Bitmap bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);

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


    private ImageUtils() {
    }
}
