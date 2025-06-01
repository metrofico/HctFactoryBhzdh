package com.hzbhd.canbus.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class RealKeyTimerManager {
   private static final int MSG_DELAY_KEY_A = 1;
   private static final int MSG_DELAY_KEY_B = 2;
   private static final int MSG_DELAY_KEY_C = 3;
   private static final int MSG_DELAY_KEY_D = 4;
   private static final int MSG_DELAY_KEY_E = 5;
   private static final int MSG_DELAY_KEY_F = 6;
   private static final int MSG_DELAY_KEY_G = 7;
   private static final int Time10 = 10000;
   private static Handler mHander = new Handler(Looper.getMainLooper()) {
      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         switch (var1.what) {
            case 1:
               MyLog.temporaryTracking("delay 10S A");
               RealKeyUtil.realKeyLongClick((Context)var1.obj, var1.arg1, 0);
               break;
            case 2:
               MyLog.temporaryTracking("delay 10S B");
               RealKeyUtil.realKeyClick5((Context)var1.obj, var1.arg1, var1.arg2, 0);
               break;
            case 3:
               MyLog.temporaryTracking("delay 10S C");
               RealKeyUtil.realKeyClick3_2((Context)var1.obj, var1.arg1, var1.arg2, 0);
               break;
            case 4:
               MyLog.temporaryTracking("delay 10S D");
               RealKeyUtil.realKeyClick3_1((Context)var1.obj, var1.arg1, var1.arg2, 0);
               break;
            case 5:
               MyLog.temporaryTracking("delay 10S E");
               RealKeyUtil.realKeyClick3((Context)var1.obj, var1.arg1, var1.arg2, 0);
               break;
            case 6:
               MyLog.temporaryTracking("delay 10S F");
               RealKeyUtil.realKeyClick2((Context)var1.obj, var1.arg1, var1.arg2, 0);
               break;
            case 7:
               MyLog.temporaryTracking("delay 10S G");
               RealKeyUtil.realKeyClick1((Context)var1.obj, var1.arg1, var1.arg2, 0);
         }

      }
   };

   public static void realKeyClick1(Context var0, int var1, int var2, int var3) {
      if (var1 == 8 || var1 == 7) {
         if (var3 == 1 || var3 == 2) {
            MyLog.temporaryTracking("count down G");
            mHander.removeMessages(7);
            Message var4 = mHander.obtainMessage();
            var4.what = 7;
            var4.obj = var0;
            var4.arg1 = var1;
            var4.arg2 = var2;
            mHander.sendEmptyMessageDelayed(7, 10000L);
         }

         if (var3 == 0) {
            MyLog.temporaryTracking("clear G");
            mHander.removeMessages(7);
         }
      }

   }

   public static void realKeyClick2(Context var0, int var1, int var2, int var3) {
      if (var1 == 8 || var1 == 7) {
         if (var3 == 1 || var3 == 2) {
            MyLog.temporaryTracking("count down F");
            mHander.removeMessages(6);
            Message var4 = mHander.obtainMessage();
            var4.what = 6;
            var4.obj = var0;
            var4.arg1 = var1;
            var4.arg2 = var2;
            mHander.sendEmptyMessageDelayed(6, 10000L);
         }

         if (var3 == 0) {
            MyLog.temporaryTracking("clear F");
            mHander.removeMessages(6);
         }
      }

   }

   public static void realKeyClick3(Context var0, int var1, int var2, int var3) {
      if (var1 == 8 || var1 == 7) {
         if (var3 == 1 || var3 == 2) {
            MyLog.temporaryTracking("count down E");
            mHander.removeMessages(5);
            Message var4 = mHander.obtainMessage();
            var4.what = 5;
            var4.obj = var0;
            var4.arg1 = var1;
            var4.arg2 = var2;
            mHander.sendEmptyMessageDelayed(5, 10000L);
         }

         if (var3 == 0) {
            MyLog.temporaryTracking("clear E");
            mHander.removeMessages(5);
         }
      }

   }

   public static void realKeyClick3_1(Context var0, int var1, int var2, int var3) {
      if (var1 == 8 || var1 == 7) {
         if (var3 == 1 || var3 == 2) {
            MyLog.temporaryTracking("count down D");
            mHander.removeMessages(4);
            Message var4 = mHander.obtainMessage();
            var4.what = 4;
            var4.obj = var0;
            var4.arg1 = var1;
            var4.arg2 = var2;
            mHander.sendEmptyMessageDelayed(4, 10000L);
         }

         if (var3 == 0) {
            MyLog.temporaryTracking("clear D");
            mHander.removeMessages(4);
         }
      }

   }

   public static void realKeyClick3_2(Context var0, int var1, int var2, int var3) {
      if (var1 == 8 || var1 == 7) {
         if (var3 == 1 || var3 == 2) {
            MyLog.temporaryTracking("count down C");
            mHander.removeMessages(3);
            Message var4 = mHander.obtainMessage();
            var4.what = 3;
            var4.obj = var0;
            var4.arg1 = var1;
            var4.arg2 = var2;
            mHander.sendEmptyMessageDelayed(3, 10000L);
         }

         if (var3 == 0) {
            MyLog.temporaryTracking("clear C");
            mHander.removeMessages(3);
         }
      }

   }

   public static void realKeyClick5(Context var0, int var1, int var2, int var3) {
      if (var1 == 8 || var1 == 7) {
         if (var3 == 1 || var3 == 2) {
            MyLog.temporaryTracking("count down B");
            mHander.removeMessages(2);
            Message var4 = mHander.obtainMessage();
            var4.what = 2;
            var4.obj = var0;
            var4.arg1 = var1;
            var4.arg2 = var2;
            mHander.sendEmptyMessageDelayed(2, 10000L);
         }

         if (var3 == 0) {
            MyLog.temporaryTracking("clear B");
            mHander.removeMessages(2);
         }
      }

   }

   public static void realKeyLongClick(Context var0, int var1, int var2) {
      if (var1 == 8 || var1 == 7) {
         if (var2 == 1 || var2 == 2) {
            MyLog.temporaryTracking("count down A");
            mHander.removeMessages(1);
            Message var3 = mHander.obtainMessage();
            var3.what = 1;
            var3.arg1 = var1;
            var3.obj = var0;
            mHander.sendEmptyMessageDelayed(1, 10000L);
         }

         if (var2 == 0) {
            MyLog.temporaryTracking("clear A");
            mHander.removeMessages(1);
         }
      }

   }
}
