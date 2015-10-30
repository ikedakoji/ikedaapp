package ikeike.kohkun.ikedaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ikedakoj on 2015/10/30.
 */
public class NetReceiver extends BroadcastReceiver {
    private Observer mObserver;

    public NetReceiver(Observer observer) {
        mObserver = observer;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo == null) {
            mObserver.onDisconnect();
        }else {
            mObserver.onConnect();
        }
    }

    //----- コールバックを定義 -----
    interface Observer {
        void onConnect();
        void onDisconnect();
    }
}
