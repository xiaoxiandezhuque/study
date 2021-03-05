package com.xh.study.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.xh.study.IMyAidlInterface;

public class AidlService extends Service {




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                RemoteCallbackList<IMyAidlInterface> list = new RemoteCallbackList<>();
                list.register(this);

                list.beginBroadcast();
                list.getBroadcastItem(0);
                list.finishBroadcast();


            }
        };
    }
}
