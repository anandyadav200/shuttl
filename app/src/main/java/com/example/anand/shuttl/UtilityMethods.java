package com.example.anand.shuttl;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Anand on 08-Oct-17.
 */

public class UtilityMethods {
    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
