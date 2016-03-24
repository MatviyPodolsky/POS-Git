package stspz.vntu.com.pos.utils;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Alexander on 04.03.2016.
 */

public class DialogUtils {

    public static void showDialog(Context context, String message, String btnText, DialogInterface.OnClickListener callback) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle(message)
                .setCancelable(false)
                .setPositiveButton(btnText, callback);
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
