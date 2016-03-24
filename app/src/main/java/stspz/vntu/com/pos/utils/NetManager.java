package stspz.vntu.com.pos.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Alexander on 29.02.2016.
 */

public class NetManager {

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            return (manager.getActiveNetworkInfo().isAvailable() && manager
                    .getActiveNetworkInfo().isConnected());
        }
        return false;
    }

}
