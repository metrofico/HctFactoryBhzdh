package com.hzbhd.ui.util;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.util.LogUtil;

public class ToastUtls {
   private static Toast toast;

   public static Toast getToast(Context var0, int var1, int var2) {
      try {
         toast.cancel();
      } catch (Exception var4) {
      }

      toast = Toast.makeText(var0, var1, var2);
      if (LogUtil.log5()) {
         LogUtil.d("getToast: ");
      }

      return toast;
   }

   public static Toast getToast(Context var0, String var1, int var2) {
      try {
         toast.cancel();
      } catch (Exception var4) {
      }

      toast = Toast.makeText(var0, var1, var2);
      if (LogUtil.log5()) {
         LogUtil.d("getToast: ");
      }

      return toast;
   }

   public static void showLongText(Context var0, int var1) {
      Toast var2 = Toast.makeText(var0, var1, 1);
      var2.setGravity(80, 0, 250);
      var2.show();
   }

   public static void showLongText(Context var0, int var1, int var2, int var3) {
      Toast var4 = getToast(var0, var1, 1);
      var4.setGravity(17, 0, 0);
      var4.show();
   }

   public static void showLongText(Context var0, String var1) {
      Toast var2 = Toast.makeText(var0, var1, 1);
      var2.setGravity(80, 0, 250);
      var2.show();
   }

   public static void showShortText(Context var0, int var1) {
      Toast var2 = Toast.makeText(var0, var1, 0);
      var2.setGravity(80, 0, 250);
      var2.show();
   }

   public static void showShortText(Context var0, int var1, int var2, int var3) {
      if (LogUtil.log5()) {
         LogUtil.d("showShortText: " + var1);
      }

      Toast var4 = getToast(var0, var1, 0);
      var4.setGravity(80, var2, var3);
      var4.show();
   }

   public static void showShortText(Context var0, String var1, int var2, int var3) {
      if (LogUtil.log5()) {
         LogUtil.d("showShortText: ");
      }

      Toast var4 = getToast(var0, var1, 0);
      var4.setGravity(80, var2, var3);
      var4.show();
   }

   public static void showShortTextBottom(Context var0, int var1) {
      Toast.makeText(var0, var1, 0).show();
   }
}
