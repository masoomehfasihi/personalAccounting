package ir.masoumeh.myaccount;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.firebase.database.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;

public class UserInfo {
    public static int getID(@NotNull Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("id", 0);

    }

    public static String getEmail(@NotNull Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        return sharedPreferences.getString("email", null);

    }

    public static String getUser(@NotNull Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        return sharedPreferences.getString("user", null);

    }

    public static boolean isLogin(@NotNull Activity activity) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogin", false);

    }

    public static int getInt(Activity activity, String key) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        return sharedPreferences.getInt(key, 1000);
    }

    public static String getString(Activity activity, String key) {

        SharedPreferences sharedPreferences = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void saveToPreference(@NotNull Activity activity, String key, String value) {

        SharedPreferences pref = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();

    }

    public static void saveEmail(@NotNull Activity activity, String value) {

        SharedPreferences pref = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", value);
        editor.apply();

    }

    public static void saveUser(@NotNull Activity activity, String value) {

        SharedPreferences pref = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user", value);
        editor.apply();

    }

    public static void saveBoolPreference(@NotNull Activity activity, String key, boolean value) {

        SharedPreferences pref = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();

    }

    public static void saveIntPreference(@NotNull Activity activity, String key, int value) {

        SharedPreferences pref = activity.getSharedPreferences("appInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();

    }

}
