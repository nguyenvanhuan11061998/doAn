package com.example.informationrecognize.Utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class AppBinding {

    @BindingAdapter("checked")
    public static void setChecked(ImageView imageView, boolean isChecked) {
        imageView.setSelected(isChecked);
    }

    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String image) {
        Glide.with(imageView)
                .load(image)
                .into(imageView);
    }
}
