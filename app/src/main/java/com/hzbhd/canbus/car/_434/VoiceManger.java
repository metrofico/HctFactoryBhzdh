package com.hzbhd.canbus.car._434;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.text.TextUtils;
import com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr;

public class VoiceManger {
   private final int MAX_VOLUME = 100;
   private MediaPlayer mMediaPlayer;

   public VoiceManger() {
      this.initMediaPlayer();
   }

   private void initMediaPlayer() {
      MediaPlayer var1 = new MediaPlayer();
      this.mMediaPlayer = var1;
      var1.setOnCompletionListener(new VoiceManger$$ExternalSyntheticLambda0(this));
      this.mMediaPlayer.setOnPreparedListener(new VoiceManger$$ExternalSyntheticLambda1(this));
   }

   // $FF: synthetic method
   void lambda$initMediaPlayer$0$com_hzbhd_canbus_car__434_VoiceManger(MediaPlayer var1) {
      var1 = this.mMediaPlayer;
      if (var1 != null && var1.isPlaying()) {
         this.mMediaPlayer.stop();
      }

      MsAbstractMsgMgr.releaseVoiceSource();
   }

   // $FF: synthetic method
   void lambda$initMediaPlayer$1$com_hzbhd_canbus_car__434_VoiceManger(MediaPlayer var1) {
      var1 = this.mMediaPlayer;
      if (var1 != null) {
         var1.start();
      }

   }

   void play(Context var1, String var2) {
      if (!TextUtils.isEmpty(var2)) {
         try {
            this.mMediaPlayer.reset();
            AssetFileDescriptor var4 = var1.getAssets().openFd(var2);
            this.mMediaPlayer.setDataSource(var4.getFileDescriptor(), var4.getStartOffset(), var4.getLength());
            if (this.mMediaPlayer.isPlaying()) {
               this.mMediaPlayer.stop();
            }

            this.mMediaPlayer.prepareAsync();
         } catch (Exception var3) {
            var3.printStackTrace();
         }

      }
   }

   public void setVolume(int var1) {
      float var2 = (float)(1.0 - Math.log((double)(100 - var1)) / Math.log(100.0));
      this.mMediaPlayer.setVolume(var2, var2);
   }
}
