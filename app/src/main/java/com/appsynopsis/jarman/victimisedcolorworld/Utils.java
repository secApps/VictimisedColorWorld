package com.appsynopsis.jarman.victimisedcolorworld;

import android.net.Uri;

import java.io.File;

/**
 * Created by nila on 11/16/15.
 */
public class Utils {
    public static Uri getImageUri(String path) {
        return Uri.fromFile(new File(path));
    }
}
