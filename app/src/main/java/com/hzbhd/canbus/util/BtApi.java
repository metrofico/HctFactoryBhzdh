package com.hzbhd.canbus.util;

import com.hzbhd.adapter.BtAdapter;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\nJ\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\rJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\rJ\u0006\u0010 \u001a\u00020\u0004J\u000e\u0010!\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\r¨\u0006#"},
   d2 = {"Lcom/hzbhd/canbus/util/BtApi;", "", "()V", "a2dpNext", "", "a2dpPause", "a2dpPlay", "a2dpPrev", "answer", "autoConn", "", "call", "num", "", "connA2dp", "address", "connHfp", "disConnA2dp", "disConnHfp", "getHfpDeviceName", "handUp", "muteMic", "mute", "pair", "scan", "sendEQKey", "sendKey", "key", "setMicOut", "out", "setName", "name", "syncPhoneBook", "unPair", "Companion", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BtApi {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final Lazy instance$delegate;

   static {
      instance$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   public final void a2dpNext() {
      BtAdapter.INSTANCE.a2dpNext();
   }

   public final void a2dpPause() {
      BtAdapter.INSTANCE.a2dpPause();
   }

   public final void a2dpPlay() {
      BtAdapter.INSTANCE.a2dpPlay();
   }

   public final void a2dpPrev() {
      BtAdapter.INSTANCE.a2dpPrev();
   }

   public final void answer() {
      BtAdapter.INSTANCE.answer();
   }

   public final void autoConn(boolean var1) {
      BtAdapter.INSTANCE.setAutoConn(var1);
   }

   public final void call(String var1) {
      Intrinsics.checkNotNullParameter(var1, "num");
      BtAdapter.INSTANCE.call(var1);
   }

   public final void connA2dp(String var1) {
      Intrinsics.checkNotNullParameter(var1, "address");
      BtAdapter.INSTANCE.connectA2dp(var1, true);
   }

   public final void connHfp(String var1) {
      Intrinsics.checkNotNullParameter(var1, "address");
      BtAdapter.INSTANCE.connectHfp(var1, true);
   }

   public final void disConnA2dp(String var1) {
      Intrinsics.checkNotNullParameter(var1, "address");
      BtAdapter.INSTANCE.connectA2dp(var1, false);
   }

   public final void disConnHfp(String var1) {
      Intrinsics.checkNotNullParameter(var1, "address");
      BtAdapter.INSTANCE.connectHfp(var1, false);
   }

   public final String getHfpDeviceName() {
      return BtAdapter.INSTANCE.getHfpDeviceName();
   }

   public final void handUp() {
      BtAdapter.INSTANCE.hangup();
   }

   public final void muteMic(boolean var1) {
      BtAdapter.INSTANCE.muteMic(var1);
   }

   public final void pair(String var1) {
      Intrinsics.checkNotNullParameter(var1, "address");
      BtAdapter.INSTANCE.pair(var1, true);
   }

   public final void scan(boolean var1) {
      BtAdapter.INSTANCE.scan(var1);
   }

   public final void sendEQKey() {
      SendKeyManager.getInstance().setKeyEvent(4, HotKeyConstant.KeyState.CLICK, false);
   }

   public final void sendKey(String var1) {
      Intrinsics.checkNotNullParameter(var1, "key");
      BtAdapter.INSTANCE.sendKey(var1);
   }

   public final void setMicOut(boolean var1) {
      BtAdapter.INSTANCE.setMicOut(var1);
   }

   public final void setName(String var1) {
      Intrinsics.checkNotNullParameter(var1, "name");
      BtAdapter.INSTANCE.setBtName(var1);
   }

   public final void syncPhoneBook() {
      BtAdapter.INSTANCE.syncPhoneBook();
   }

   public final void unPair(String var1) {
      Intrinsics.checkNotNullParameter(var1, "address");
      BtAdapter.INSTANCE.pair(var1, false);
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"},
      d2 = {"Lcom/hzbhd/canbus/util/BtApi$Companion;", "", "()V", "instance", "Lcom/hzbhd/canbus/util/BtApi;", "getInstance", "()Lcom/hzbhd/canbus/util/BtApi;", "instance$delegate", "Lkotlin/Lazy;", "CanBusInfo_release"},
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

      public final BtApi getInstance() {
         return (BtApi)BtApi.instance$delegate.getValue();
      }
   }
}
