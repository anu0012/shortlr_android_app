package com.codingblocks.shortlr;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by piyush0 on 11/04/17.
 */

public class Utils {

    public static String getFromClip(Context context) {
        String retVal = null;
        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData abc = myClipboard.getPrimaryClip();
        if (abc != null) {
            ClipData.Item item = abc.getItemAt(0);
            retVal = item.getText().toString();
        }
        return retVal;
    }

    public static String saveToClipboard(String text, Context context) {
        ClipData myClip;

        myClip = ClipData.newPlainText("text", text);
        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(context, "Link copied to clipboard", Toast.LENGTH_SHORT).show();
        return text;
    }

    public static String getHost(String url) {
        if (url == null || url.length() == 0)
            return "";

        int doubleslash = url.indexOf("//");
        if (doubleslash == -1)
            doubleslash = 0;
        else
            doubleslash += 2;

        int end = url.indexOf('/', doubleslash);
        end = end >= 0 ? end : url.length();

        int port = url.indexOf(':', doubleslash);
        end = (port > 0 && port < end) ? port : end;

        return url.substring(doubleslash, end);
    }

    private static final String URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$";

    public static boolean isUrl(String str) {
        Pattern p = Pattern.compile(URL_REGEX);
        Matcher m = p.matcher(str);//replace with string to compare
        return m.find();
    }

}
