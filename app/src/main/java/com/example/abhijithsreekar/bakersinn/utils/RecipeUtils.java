package com.example.abhijithsreekar.bakersinn.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.abhijithsreekar.bakersinn.R;

public class RecipeUtils {

    public static int[] getRecipeImages(Context context) {
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.recipeImages);
        int count = typedArray.length();
        int[] ids = new int[count];
        for (int i = 0; i < count; i++) {
            ids[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();

        return ids;
    }

    public static void setupRecyclerView(Context context, RecyclerView recyclerView, int layoutType) {
        switch (layoutType) {
            case 0:
                recyclerView.setLayoutManager(new GridLayoutManager(context, getSpanCount(context)));
                break;
            case 1:
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                break;
            case 2:
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                break;
        }
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
    }

    private static int getSpanCount(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }

    public static boolean isInLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isOnTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

}
