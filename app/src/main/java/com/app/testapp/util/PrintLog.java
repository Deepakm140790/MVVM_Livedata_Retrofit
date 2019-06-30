package com.app.testapp.util;

import android.util.Log;

/**
 * Class contains logger methods and helpful for debugging purpose.
 */
public final class PrintLog {
    private static final String TAG = "MLog->";
    //Print log only in debug mode
    private static boolean debug = true;

    private PrintLog() {
    }

    public static void d(String tag, final String msg) {
        if (debug)
            Log.d(TAG + tag, msg);
    }

    public static void i(String tag, final String msg) {
        if (debug)
            Log.i(TAG + tag, msg);
    }

    public static void e(String tag, final String msg) {
        Log.e(TAG + tag, msg);
    }

    public static void e(String tag, final String msg, Throwable tr) {
        Log.e(TAG, "e: ", tr);
    }

    public static void v(String tag, final String msg) {
        if (debug)
            Log.v(TAG + tag, msg);
    }

    public static void w(String tag, final String msg) {
        Log.w(TAG + tag, msg);
    }
}

