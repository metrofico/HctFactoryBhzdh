package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.hzbhd.canbus.util.ActionControlUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\f"},
   d2 = {"Lcom/hzbhd/canbus/receiver/SpeechReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "registerReceiver", "unregisterReceiver", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SpeechReceiver extends BroadcastReceiver {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final String speech_rx_can = "speech.rx.can";
   public static final String speech_to_can = "speech.tx.can";
   private static final String tx_action = "tx_action";
   private static final String type = "type";
   private static final String value = "value";

   public void onReceive(Context var1, Intent var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "intent");
      String var3 = var2.getAction();
      if (Intrinsics.areEqual((Object)var3, (Object)"speech.tx.can")) {
         ActionControlUtil.acControl(var1, var2.getStringExtra("tx_action"), var2.getStringExtra("type"), var2.getStringExtra("value"));
      } else {
         Intrinsics.areEqual((Object)var3, (Object)"speech.rx.can");
      }

   }

   public final void registerReceiver(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      var1.registerReceiver((BroadcastReceiver)this, new IntentFilter("speech.tx.can"));
   }

   public final void unregisterReceiver(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      var1.unregisterReceiver((BroadcastReceiver)this);
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/receiver/SpeechReceiver$Companion;", "", "()V", "speech_rx_can", "", "speech_to_can", "tx_action", "type", "value", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
