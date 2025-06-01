package com.hzbhd.util;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(
   d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u001a\b\u0010\u001c\u001a\u00020\u0007H\u0002\u001a\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 \u001a\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 \u001a\u0014\u0010\"\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010(\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a$\u0010)\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u0014\u0010*\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u0014\u0010+\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010,\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010-\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a$\u0010.\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\u0005\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\r\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0005\u001a\u0004\b\u000e\u0010\u000b\"\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001b\u0010\u0011\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0005\u001a\u0004\b\u0012\u0010\u000b\"\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0005\u001a\u0004\b\u0016\u0010\u0017\"\u001b\u0010\u0019\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0005\u001a\u0004\b\u001a\u0010\u000b¨\u0006/"},
   d2 = {"backThread", "Landroid/os/HandlerThread;", "getBackThread", "()Landroid/os/HandlerThread;", "backThread$delegate", "Lkotlin/Lazy;", "handlerWhatIndex", "", "msgUIHandler", "Landroid/os/Handler;", "getMsgUIHandler", "()Landroid/os/Handler;", "msgUIHandler$delegate", "threadHandler", "getThreadHandler", "threadHandler$delegate", "threadIndex", "threadMsgHandler", "getThreadMsgHandler", "threadMsgHandler$delegate", "threadPoolExecutor", "Lcom/hzbhd/util/HandlerThreadPoolExecutor;", "getThreadPoolExecutor", "()Lcom/hzbhd/util/HandlerThreadPoolExecutor;", "threadPoolExecutor$delegate", "uiHandler", "getUiHandler", "uiHandler$delegate", "getHandlerWhat", "removeBackMsg", "", "msgString", "", "removeUiMsg", "runBack", "action", "Lkotlin/Function0;", "runBackDelay", "time", "", "runBackMsg", "runBackMsgDelay", "runFast", "runUi", "runUiDelay", "runUiMsg", "runUiMsgDelay", "thread_util_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class HandlerThreadUtilKt {
   private static final Lazy backThread$delegate;
   private static int handlerWhatIndex;
   private static final Lazy msgUIHandler$delegate;
   private static final Lazy threadHandler$delegate;
   private static int threadIndex;
   private static final Lazy threadMsgHandler$delegate;
   private static final Lazy threadPoolExecutor$delegate;
   private static final Lazy uiHandler$delegate;

   static {
      uiHandler$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      msgUIHandler$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      backThread$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      threadPoolExecutor$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      threadHandler$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      threadMsgHandler$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   public static final HandlerThread getBackThread() {
      return (HandlerThread)backThread$delegate.getValue();
   }

   private static final int getHandlerWhat() {
      int var0 = handlerWhatIndex + 1;
      handlerWhatIndex = var0;
      return var0;
   }

   private static final Handler getMsgUIHandler() {
      return (Handler)msgUIHandler$delegate.getValue();
   }

   private static final Handler getThreadHandler() {
      return (Handler)threadHandler$delegate.getValue();
   }

   private static final Handler getThreadMsgHandler() {
      return (Handler)threadMsgHandler$delegate.getValue();
   }

   public static final HandlerThreadPoolExecutor getThreadPoolExecutor() {
      return (HandlerThreadPoolExecutor)threadPoolExecutor$delegate.getValue();
   }

   private static final Handler getUiHandler() {
      return (Handler)uiHandler$delegate.getValue();
   }

   public static final void removeBackMsg(String var0) {
      Intrinsics.checkNotNullParameter(var0, "msgString");
      if (var0.length() <= 5) {
         Handler var1 = getThreadMsgHandler();
         Locale var2 = Locale.getDefault();
         Intrinsics.checkNotNullExpressionValue(var2, "getDefault()");
         var0 = var0.toLowerCase(var2);
         Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toLowerCase(locale)");
         var1.removeMessages(Integer.parseInt(var0, CharsKt.checkRadix(36)));
      } else {
         throw new Exception("msg length > 5 error");
      }
   }

   public static final void removeUiMsg(String var0) {
      Intrinsics.checkNotNullParameter(var0, "msgString");
      if (var0.length() <= 5) {
         Handler var1 = getMsgUIHandler();
         Locale var2 = Locale.getDefault();
         Intrinsics.checkNotNullExpressionValue(var2, "getDefault()");
         var0 = var0.toLowerCase(var2);
         Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toLowerCase(locale)");
         var1.removeMessages(Integer.parseInt(var0, CharsKt.checkRadix(36)));
      } else {
         throw new Exception("msg length > 5 error");
      }
   }

   public static final void runBack(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "action");
      getThreadHandler().sendMessage(getThreadHandler().obtainMessage(getHandlerWhat(), var0));
   }

   public static final void runBackDelay(long var0, Function0 var2) {
      Intrinsics.checkNotNullParameter(var2, "action");
      getThreadHandler().sendMessageDelayed(getThreadHandler().obtainMessage(getHandlerWhat(), var2), var0);
   }

   public static final void runBackMsg(String var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "msgString");
      Intrinsics.checkNotNullParameter(var1, "action");
      if (var0.length() <= 5) {
         Handler var2 = getThreadMsgHandler();
         Locale var3 = Locale.getDefault();
         Intrinsics.checkNotNullExpressionValue(var3, "getDefault()");
         String var4 = var0.toLowerCase(var3);
         Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).toLowerCase(locale)");
         var2.removeMessages(Integer.parseInt(var4, CharsKt.checkRadix(36)));
         getThreadMsgHandler().sendMessage(getThreadMsgHandler().obtainMessage(Integer.parseInt(var0, CharsKt.checkRadix(36)), var1));
      } else {
         throw new Exception("msg length > 5 error");
      }
   }

   public static final void runBackMsgDelay(String var0, long var1, Function0 var3) {
      Intrinsics.checkNotNullParameter(var0, "msgString");
      Intrinsics.checkNotNullParameter(var3, "action");
      if (var0.length() <= 5) {
         Handler var4 = getThreadMsgHandler();
         Locale var5 = Locale.getDefault();
         Intrinsics.checkNotNullExpressionValue(var5, "getDefault()");
         String var6 = var0.toLowerCase(var5);
         Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String).toLowerCase(locale)");
         var4.removeMessages(Integer.parseInt(var6, CharsKt.checkRadix(36)));
         getThreadMsgHandler().sendMessageDelayed(getThreadMsgHandler().obtainMessage(Integer.parseInt(var0, CharsKt.checkRadix(36)), var3), var1);
      } else {
         throw new Exception("msg length > 5 error");
      }
   }

   public static final void runFast(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "action");
      getThreadPoolExecutor().execute(var0);
   }

   public static final void runUi(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "action");
      getUiHandler().sendMessage(getUiHandler().obtainMessage(getHandlerWhat(), var0));
   }

   public static final void runUiDelay(long var0, Function0 var2) {
      Intrinsics.checkNotNullParameter(var2, "action");
      getUiHandler().sendMessageDelayed(getUiHandler().obtainMessage(getHandlerWhat(), var2), var0);
   }

   public static final void runUiMsg(String var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "msgString");
      Intrinsics.checkNotNullParameter(var1, "action");
      if (var0.length() <= 5) {
         Handler var2 = getMsgUIHandler();
         Locale var3 = Locale.getDefault();
         Intrinsics.checkNotNullExpressionValue(var3, "getDefault()");
         String var4 = var0.toLowerCase(var3);
         Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).toLowerCase(locale)");
         var2.removeMessages(Integer.parseInt(var4, CharsKt.checkRadix(36)));
         getMsgUIHandler().sendMessage(getMsgUIHandler().obtainMessage(Integer.parseInt(var0, CharsKt.checkRadix(36)), var1));
      } else {
         throw new Exception("msg length > 5 error");
      }
   }

   public static final void runUiMsgDelay(String var0, long var1, Function0 var3) {
      Intrinsics.checkNotNullParameter(var0, "msgString");
      Intrinsics.checkNotNullParameter(var3, "action");
      if (var0.length() <= 5) {
         Handler var4 = getMsgUIHandler();
         Locale var5 = Locale.getDefault();
         Intrinsics.checkNotNullExpressionValue(var5, "getDefault()");
         String var6 = var0.toLowerCase(var5);
         Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String).toLowerCase(locale)");
         var4.removeMessages(Integer.parseInt(var6, CharsKt.checkRadix(36)));
         getMsgUIHandler().sendMessageDelayed(getMsgUIHandler().obtainMessage(Integer.parseInt(var0, CharsKt.checkRadix(36)), var3), var1);
      } else {
         throw new Exception("msg length > 5 error");
      }
   }
}
