package com.applandeo.materialcalendarview.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * This class is used to load event image in a day cell
 * <p>
 * Created by Mateusz Kornakiewicz on 23.05.2017.
 */

public class ImageUtils {

    /**
     * This method asynchronously loads image resource to ImageView object using Glide library.
     * If resource is 0 then method is finished before loading.
     *
     * @param imageView An ImageView that contains an image
     * @param resource  An image resource loaded to ImageView
     */
    public static void loadResource(ImageView imageView, int resource) {
        if (resource == 0) {
            return;
        }

        Glide.with(imageView.getContext()).load(resource).into(imageView);
    }
}
