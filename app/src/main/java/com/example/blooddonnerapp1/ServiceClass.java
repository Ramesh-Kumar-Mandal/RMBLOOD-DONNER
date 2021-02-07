package com.example.blooddonnerapp1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.messaging.RemoteMessage;

public class ServiceClass extends Service {
    private MediaPlayer player;
    public ServiceClass() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player=MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
