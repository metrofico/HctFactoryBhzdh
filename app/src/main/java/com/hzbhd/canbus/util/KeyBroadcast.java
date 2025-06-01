package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;

public class KeyBroadcast {
   private static Intent speechIntent;

   public static void sendSpeechBroadcast(Context var0) {
      if (speechIntent == null) {
         speechIntent = new Intent("speech.rx.can");
      }

      speechIntent.putExtra("tx_action", "speech.dialog.show");
      var0.sendBroadcast(speechIntent);
   }
}
