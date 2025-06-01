package com.hzbhd.canbus.car._445;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;

public class AutoParkVoiceManger {
   private final String TAG = "AutoParkVoiceManger";
   private MediaPlayer mMediaPlayer;

   public AutoParkVoiceManger() {
      this.initMediaPlayer();
   }

   private void initMediaPlayer() {
      MediaPlayer var1 = new MediaPlayer();
      this.mMediaPlayer = var1;
      var1.setOnCompletionListener(new AutoParkVoiceManger$$ExternalSyntheticLambda0(this));
      this.mMediaPlayer.setOnPreparedListener(new AutoParkVoiceManger$$ExternalSyntheticLambda1(this));
   }

   // $FF: synthetic method
   void lambda$initMediaPlayer$0$com_hzbhd_canbus_car__445_AutoParkVoiceManger(MediaPlayer var1) {
      Log.i("AutoParkVoiceManger", "onCompletion: mp " + var1);
      this.mMediaPlayer.stop();
      Log.i("AutoParkVoiceManger", "onCompletion: stop");
   }

   // $FF: synthetic method
   void lambda$initMediaPlayer$1$com_hzbhd_canbus_car__445_AutoParkVoiceManger(MediaPlayer var1) {
      Log.i("AutoParkVoiceManger", "onPrepared: mp -> " + var1);
      this.mMediaPlayer.start();
      Log.i("AutoParkVoiceManger", "onPrepared: start");
   }

   void play(Context var1, String var2) {
      if (!TextUtils.isEmpty(var2)) {
         try {
            this.mMediaPlayer.reset();
            Log.i("AutoParkVoiceManger", "play: reset");
            AssetFileDescriptor var4 = var1.getAssets().openFd(var2);
            this.mMediaPlayer.setDataSource(var4.getFileDescriptor(), var4.getStartOffset(), var4.getLength());
            StringBuilder var5 = new StringBuilder();
            Log.i("AutoParkVoiceManger", var5.append("play: setDataSource \"").append(var2).append("\"").toString());
            if (this.mMediaPlayer.isPlaying()) {
               this.mMediaPlayer.stop();
            }

            this.mMediaPlayer.prepareAsync();
            Log.i("AutoParkVoiceManger", "play: prepareAsync");
         } catch (Exception var3) {
            var3.printStackTrace();
            Log.i("AutoParkVoiceManger", "play: " + var3.toString());
         }

      }
   }
}
