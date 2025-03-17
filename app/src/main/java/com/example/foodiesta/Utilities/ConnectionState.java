package com.example.foodiesta.Utilities;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import io.reactivex.rxjava3.core.Observable;
public class ConnectionState {
    public static Observable<Boolean> observeInternetConnectivity(Context context) {
        return Observable.create(emitter -> {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {
                emitter.onNext(false);
                return;
            }
                cm.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(Network network) {
                        emitter.onNext(true);
                    }

                    @Override
                    public void onLost(Network network) {
                        emitter.onNext(false);
                    }
                });
        });
    }
}
