package com.hzbhd.commontools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzbhd.util.LogUtil;
import java.lang.reflect.Type;

public class bhdGsonUtils {
   private static final Gson mGson = (new GsonBuilder()).create();

   public static Object fromFilePath(String var0, Class var1) {
      if (LogUtil.log5()) {
         LogUtil.d("fromFilePath() called with: filePath = [" + var0 + "], classOfT = [" + var1 + "]");
      }

      Object var3 = fromFilePath_real(var0, var1);
      Object var2 = var3;
      if (var3 == null) {
         var2 = fromFilePath_frombakfile_andsave(var0, var1);
      }

      return var2;
   }

   public static Object fromFilePath_frombackupfold_savebak(String var0, Class var1) {
      if (LogUtil.log5()) {
         LogUtil.d("fromFilePath_frombackupfold_baksave() called with: filePath = [" + var0 + "], classOfT = [" + var1 + "]");
      }

      String var2 = var0 + ".bak";
      Object var3 = fromFilePath_real(var0.replace("/bhd/appconfig/", "/bhd/backup/appconfig/"), var1);
      if (var3 != null) {
         toRealFileJson(var2, var3);
      }

      return var3;
   }

   public static Object fromFilePath_frombakfile_andsave(String var0, Class var1) {
      if (LogUtil.log5()) {
         LogUtil.d("fromFilePath_frombakfile_andsave() called with: filePath = [" + var0 + "], classOfT = [" + var1 + "]");
      }

      Object var3 = fromFilePath_real(var0 + ".bak", var1);
      Object var2 = var3;
      if (var3 == null) {
         var2 = fromFilePath_frombackupfold_savebak(var0, var1);
      }

      if (var2 != null) {
         toRealFileJson(var0, var2);
      }

      return var2;
   }

   public static Object fromFilePath_real(String param0, Class param1) {
      // $FF: Couldn't be decompiled
   }

   public static Object fromJson(String param0, Class param1) {
      // $FF: Couldn't be decompiled
   }

   public static Object fromJson(String var0, String var1, Class var2) {
      if (LogUtil.log5()) {
         LogUtil.d("fromJson() called with: dir = [" + var0 + "], fileName = [" + var1 + "], classOfT = [" + var2 + "]");
      }

      var0 = var0 + var1;
      if (LogUtil.log5()) {
         LogUtil.d("fromjson file path:" + var0);
      }

      return fromFilePath(var0, var2);
   }

   public static Object fromJson(String param0, Type param1) {
      // $FF: Couldn't be decompiled
   }

   public static boolean toFileJson(String var0, Object var1) {
      toRealFileJson(var0, var1);
      toRealFileJson(var0 + "bak", var1);
      return true;
   }

   public static String toJson(Object param0) {
      // $FF: Couldn't be decompiled
   }

   public static boolean toJson(String var0, String var1, Object var2) {
      return toFileJson(var0 + var1, var2);
   }

   public static boolean toRealFileJson(String param0, Object param1) {
      // $FF: Couldn't be decompiled
   }
}
