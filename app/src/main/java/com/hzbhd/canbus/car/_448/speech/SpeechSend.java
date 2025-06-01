package com.hzbhd.canbus.car._448.speech;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.util.ContextUtil;

public class SpeechSend {
   private static String speech_rx_can;
   private static String type;
   private static String value;

   public static void acLoop(Boolean var0) {
      if (var0) {
         sendSpeech("air.in.out.cycle.on", (String)null, (String)null);
      } else {
         sendSpeech("air.in.out.cycle.off", (String)null, (String)null);
      }

   }

   public static void acMode(SpeechAction.WindValueEnum var0) {
      sendSpeech("ac.mode", String.valueOf(var0), (String)null);
   }

   public static void acStatus(Boolean var0) {
      if (var0) {
         sendSpeech("air.ac.on", (String)null, (String)null);
      } else {
         sendSpeech("air.ac.off", (String)null, (String)null);
      }

   }

   public static void behindDefrost(Boolean var0) {
      if (var0) {
         sendSpeech("ac.defrost.behind.open", (String)null, (String)null);
      } else {
         sendSpeech("ac.defrost.behind.close", (String)null, (String)null);
      }

   }

   public static void frontDefrost(Boolean var0) {
      if (var0) {
         sendSpeech("ac.defrost.front.open", (String)null, (String)null);
      } else {
         sendSpeech("ac.defrost.front.close", (String)null, (String)null);
      }

   }

   private static void sendSpeech(String var0, String var1, String var2) {
      Intent var3 = new Intent(speech_rx_can);
      var3.putExtra("tx_action", var0);
      if (var1 != null) {
         var3.putExtra(type, var1);
      }

      if (var2 != null) {
         var3.putExtra(value, var2);
      }

      ContextUtil.getInstance().getContext().sendBroadcast(var3);
   }

   public static void sendSpeechTTsBroadcast(Context var0, String var1) {
      Intent var2 = new Intent(speech_rx_can);
      var2.putExtra("tx_action", "speech.speak");
      var2.putExtra("value", var1);
      var0.sendBroadcast(var2);
   }

   public static void tempChange(int var0) {
      sendSpeech("ac.temperature.to", (String)null, String.valueOf(var0));
   }

   public static void windChange(int var0) {
      sendSpeech("ac.windlevel.to", (String)null, String.valueOf(var0));
   }
}
