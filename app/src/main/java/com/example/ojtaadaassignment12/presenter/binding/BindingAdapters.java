package com.example.ojtaadaassignment12.presenter.binding;

import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.example.ojtaadaassignment12.R;
import com.squareup.picasso.Picasso;

public class BindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load("https://image.tmdb.org/t/p/original/" + imageUrl)
                .into(view);
    }

    @BindingAdapter("customStarTint")
    public static void setCustomStarTint(ImageView view, boolean isFavorite) {
        int color = isFavorite
                ? ContextCompat.getColor(view.getContext(), R.color.orange)
                : ContextCompat.getColor(view.getContext(), R.color.gray);
        view.setColorFilter(color);
    }
}

