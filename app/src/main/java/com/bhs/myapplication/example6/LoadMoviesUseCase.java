package com.bhs.myapplication.example6;

import android.content.Context;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LoadMoviesUseCase {

    List<Movie> execute(@NonNull final Context context, @ArrayRes final int stringArray) {
        final List<String> arrayList = new ArrayList<>(Arrays.asList(
                context.getResources().getStringArray(stringArray)));

        final List<Movie> movieList = new ArrayList<>();

        for (String str : arrayList) {
            String[] array = str.split("\\|");
            movieList.add(new Movie(array[0], array[1]));
        }

        return movieList;
    }
}
