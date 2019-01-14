package com.jmucientes.udacity.bakingapp.data.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.jmucientes.udacity.bakingapp.BakingApp;

import javax.inject.Inject;

/**
 * Helper class used to determine if the app is connected to the Internet
 */
public class ConnectionState extends BroadcastReceiver {

    private final Context mContex;
    private final ConnectivityManager mConnectivityManager;
    private boolean isConnected;

    @Inject
    public ConnectionState(BakingApp context) {
        mContex = context;
        mConnectivityManager = (ConnectivityManager) mContex.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&  activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&  activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(context, "Lost connectivity.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isConnected() {
        return isConnected;
    }
}
