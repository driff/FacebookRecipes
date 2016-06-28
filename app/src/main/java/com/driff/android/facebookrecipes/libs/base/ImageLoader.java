package com.driff.android.facebookrecipes.libs.base;

import android.widget.ImageView;

/**
 * Created by johnj on 22/6/2016.
 */
public interface ImageLoader {

    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);

}
