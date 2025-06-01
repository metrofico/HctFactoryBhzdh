package com.hzbhd.commontools.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CommUtil {
   private static final String TAG = "CommUtil";
   private static Context mContext;
   private static SharedPreferences mShare;

   public static Context getContext() {
      return mContext;
   }

   public static SharedPreferences getShare() {
      return mShare;
   }

   public static void init(Context var0) {
      mContext = var0;
      mShare = var0.getSharedPreferences("dsp_save", 0);
   }

   public static void startTestMode(int var0) {
      try {
         ((TestInterface)Class.forName("com.hzbhd.service.test.MyTest").newInstance()).startTest(var0);
      } catch (Exception var2) {
      }

   }

   public interface TestInterface {
      String TAG = "AUTO_TEST";

      void startTest(int var1);
   }
}
