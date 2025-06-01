package com.hzbhd.canbus.park.radar.track;

import android.content.Context;
import java.io.InputStream;

public class TrackData {
   private byte[] mTrackData = new byte[20000];
   private int mTrackDataLen = 20000;
   private int mType = 6;

   private void getTrackData(byte[] var1) {
      int var3 = this.mType * 20000 + 2000;
      int var4 = var1.length;
      int var2 = this.mTrackDataLen;
      if (var4 >= var3 + var2) {
         System.arraycopy(var1, var3, this.mTrackData, 0, var2);
      }

   }

   public boolean readAngleData(int var1, byte[] var2) {
      int var3 = var1;
      if (var1 < 0) {
         var3 = -var1;
      }

      try {
         System.arraycopy(this.mTrackData, (var3 + 54) * 200, var2, 0, 200);
         return true;
      } catch (Exception var4) {
         return false;
      }
   }

   public void readFile(Context var1) {
      try {
         InputStream var4 = var1.getResources().getAssets().open("track/track.bin");
         byte[] var2 = new byte[var4.available()];
         var4.read(var2);
         var4.close();
         this.getTrackData(var2);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void setType(int var1) {
      this.mType = var1;
   }
}
