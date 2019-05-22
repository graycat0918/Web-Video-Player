package com.dry.webvideoplayer;

import android.content.Context;
import android.content.res.TypedArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ResourceHelper {

    public static List<String[]> get2DResArray(Context context, String key) {
        List<String[]> array = new ArrayList<String[]>();

        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 0;

            do {
                field = res.getField(key + "_" + counter);
                // array.add(context.getResources().obtainTypedArray(field.getInt(null)));
                counter++;
                array.add(context.getResources().getStringArray(field.getInt(null)));
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return array;
    }
}
