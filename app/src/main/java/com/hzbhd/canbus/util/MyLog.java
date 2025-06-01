package com.hzbhd.canbus.util;

import android.util.Log;

public class MyLog {
   private static String OoO;

   public static void d(String var0, String var1) {
      if (OoO.equals("ON")) {
         Log.d("<OoO> " + var0, "————————>" + var1);
      }

   }

   public static void e(String var0, String var1) {
      if (OoO.equals("ON")) {
         Log.e("<OoO> " + var0, "————————>" + var1);
      }

   }

   public static void i(String var0, String var1) {
      if (OoO.equals("ON")) {
         Log.i("<OoO> " + var0, "————————>" + var1);
      }

   }

   public static void s(String var0) {
      if (OoO.equals("ON")) {
         System.out.println("<OoO> ————————>" + var0);
      }

   }

   public static void temporaryTracking(String var0) {
      if (OoO.equals("ON")) {
         Log.e("<OoO> TRACK", "————————>" + var0);
      }

   }

   public static void turnOFF() {
      OoO = "OFF";
   }

   public static void turnOn() {
      OoO = "ON";
   }

   public static void v(String var0, String var1) {
      if (OoO.equals("ON")) {
         Log.v("<OoO> " + var0, "————————>" + var1);
      }

   }

   public static void w(String var0, String var1) {
      if (OoO.equals("ON")) {
         Log.w("<OoO> " + var0, "————————>" + var1);
      }

   }
}
