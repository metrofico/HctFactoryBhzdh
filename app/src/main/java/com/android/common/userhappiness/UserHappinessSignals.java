package com.android.common.userhappiness;

import android.content.Context;
import android.content.Intent;

public class UserHappinessSignals {
   private static boolean mHasVoiceLoggingInfo = false;

   public static void setHasVoiceLoggingInfo(boolean var0) {
      mHasVoiceLoggingInfo = var0;
   }

   public static void userAcceptedImeText(Context var0) {
      if (mHasVoiceLoggingInfo) {
         Intent var1 = new Intent("com.android.common.speech.LOG_EVENT");
         var1.putExtra("app_name", "voiceime");
         var1.putExtra("extra_event", 21);
         var1.putExtra("", var0.getPackageName());
         var1.putExtra("timestamp", System.currentTimeMillis());
         var0.sendBroadcast(var1);
         setHasVoiceLoggingInfo(false);
      }

   }
}
