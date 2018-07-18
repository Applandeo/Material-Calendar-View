package com.applandeo.materialcalendarview.utils;

import android.widget.ImageView;

/**
 * This class is used to load event image in a day cell
 * <p>
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class ImageUtils {
    
    public static void loadResource(ImageView imageView, int resource) {
        if (resource == 0) {
            return;
        }

        imageView.setImageResource(resource);
    }
}
