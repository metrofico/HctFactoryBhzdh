package com.hzbhd.canbus.adapter.util;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings.System;

public class PA6Utils {
   public static final String AIR_SEAT_SHOW = "air_seat_show";
   public static final String AIR_SHOW = "air_show";
   public static final String BLUETOOTH_NAME = "blueTooth_name";
   public static final String RADAR_SHOW = "radar_show";
   public static final String WallpaperPicker_Main = "WallpaperPicker_Main";
   public static final String WallpaperPicker_Now_ID = "WallpaperPicker_Now";

   public static boolean getAirSeatShow() {
      return SystemProperties.getBoolean("air_seat_show", true);
   }

   public static boolean getAirShow(Context var0) {
      return Boolean.parseBoolean(System.getString(var0.getContentResolver(), "air_show"));
   }

   public static String getBtName(Context var0) {
      return System.getString(var0.getContentResolver(), "blueTooth_name");
   }

   public static int getIntData() {
      return 1;
   }

   public static boolean getRadarShow(Context var0) {
      return Boolean.parseBoolean(System.getString(var0.getContentResolver(), "radar_show"));
   }

   public static void setAirSeatShow(boolean var0) {
      SystemProperties.set("air_seat_show", var0 + "");
   }

   public static void setAirShow(Context var0, boolean var1) {
      System.putString(var0.getContentResolver(), "air_show", var1 + "");
   }

   public static void setBtName(Context var0, String var1) {
      System.putString(var0.getContentResolver(), "blueTooth_name", var1);
   }

   public static void setIntData(int var0) {
      SystemProperties.set("WallpaperPicker_Now", String.valueOf(var0));
   }

   public static void setRadarShow(Context var0, boolean var1) {
      System.putString(var0.getContentResolver(), "radar_show", var1 + "");
   }
}
