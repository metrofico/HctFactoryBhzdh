package com.hzbhd.canbus.adapter.externel360camera;

import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.commontools.utils.FgeString;

public class VZ360Constance {
   public static int CANCEL;
   public static int DOWN;
   public static int ENTER;
   public static int FOCUS_add;
   public static int FOCUS_minus;
   public static int INFO;
   public static int IRIS_add;
   public static int IRIS_minus;
   public static int LEFT;
   public static int LOGIN;
   public static int NEXT;
   public static int PINP;
   public static int PLAY;
   public static int POWER;
   public static int PREV;
   public static int PTZAUTO;
   public static int RECORD;
   public static int RETURN;
   public static int RIGHT;
   public static int STOP;
   static String TAG;
   public static int UP;
   public static int ZOOM_add;
   public static int ZOOM_minus;
   public static int comds_0;
   public static int comds_1;
   public static int comds_2;
   public static int comds_3;
   public static int comds_4;
   public static int comds_5;
   public static int comds_6;
   public static int comds_7;
   public static int comds_8;
   public static int comds_9;
   public static int comds_add;
   public static int comds_minus;
   public static int headCode;
   public static byte[] mcu_protocol = new byte[]{-56, 6};

   public static byte[] byteMerger(byte[] var0, byte[] var1, byte[] var2) {
      int var3 = var0.length + var1.length + var2.length;
      byte[] var4 = new byte[var3];
      HzbhdLog.d(TAG, var3 + " , byteMerger : " + var0.length + " , " + var1.length + " , " + var2.length);
      System.arraycopy(var0, 0, var4, 0, var0.length);
      System.arraycopy(var1, 0, var4, var0.length, var1.length);
      System.arraycopy(var2, 0, var4, var0.length + var1.length, var2.length);
      return var4;
   }

   public static byte[] getBytes(int var0) {
      return new byte[]{(byte)(var0 >> 8), (byte)(var0 & 255)};
   }

   public static Boolean isVZ360Camera() {
      int var0 = FutureUtil.instance.is360External();
      HzbhdLog.d(TAG, "external360CamType " + var0);
      boolean var1;
      if (var0 == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static void sendMDs(int var0) {
      byte[] var2 = getBytes(headCode);
      byte[] var1 = getBytes(var0);
      var1 = byteMerger(mcu_protocol, var2, var1);
      HzbhdLog.d(TAG, "sendMDs : " + FgeString.bytes2HexString(var1, var1.length));
      FutureUtil.instance.reqMcuKey(var1);
   }
}
