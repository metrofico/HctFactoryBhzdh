package com.hzbhd.canbus.util;

import android.content.Context;

public class ContextUtil {
   private static ContextUtil c;
   private Context mContext;

   private ContextUtil() {
   }

   public static ContextUtil getInstance() {
      if (c == null) {
         c = new ContextUtil();
      }

      return c;
   }

   public Context getContext() {
      return this.mContext;
   }

   public void setContext(Context var1) {
      this.mContext = var1;
   }
}
