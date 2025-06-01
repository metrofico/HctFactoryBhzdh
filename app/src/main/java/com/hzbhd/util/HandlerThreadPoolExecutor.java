package com.hzbhd.util;

import android.os.Handler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0017\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\b\u0010\u0019\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u000e\u0010\nR\u001b\u0010\u0010\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0011\u0010\nR\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"},
   d2 = {"Lcom/hzbhd/util/HandlerThreadPoolExecutor;", "", "()V", "task1Running", "", "task2Running", "task3Running", "threadHandler1", "Landroid/os/Handler;", "getThreadHandler1", "()Landroid/os/Handler;", "threadHandler1$delegate", "Lkotlin/Lazy;", "threadHandler2", "getThreadHandler2", "threadHandler2$delegate", "threadHandler3", "getThreadHandler3", "threadHandler3$delegate", "workQueue", "Ljava/util/concurrent/BlockingQueue;", "Lkotlin/Function0;", "", "execute", "action", "runHandler", "thread_util_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class HandlerThreadPoolExecutor {
   private boolean task1Running;
   private boolean task2Running;
   private boolean task3Running;
   private final Lazy threadHandler1$delegate;
   private final Lazy threadHandler2$delegate;
   private final Lazy threadHandler3$delegate;
   private final BlockingQueue workQueue = (BlockingQueue)(new LinkedBlockingQueue(100));

   // $FF: synthetic method
   public static void $r8$lambda$M77CMXzvLidXU3J_FQ9OGjTikoE(HandlerThreadPoolExecutor var0) {
      runHandler$lambda_1(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$g2OVzbrJhCK_YLEBtGW2JsRWbuw(HandlerThreadPoolExecutor var0) {
      runHandler$lambda_2(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$rhMwl8dtD7Vv8LiUbwqzu6wpKLY(HandlerThreadPoolExecutor var0) {
      runHandler$lambda_0(var0);
   }

   public HandlerThreadPoolExecutor() {
      this.threadHandler1$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.threadHandler2$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.threadHandler3$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   private final Handler getThreadHandler1() {
      return (Handler)this.threadHandler1$delegate.getValue();
   }

   private final Handler getThreadHandler2() {
      return (Handler)this.threadHandler2$delegate.getValue();
   }

   private final Handler getThreadHandler3() {
      return (Handler)this.threadHandler3$delegate.getValue();
   }

   private final void runHandler() {
      if (!this.task1Running) {
         this.task1Running = true;
         this.getThreadHandler1().post(new HandlerThreadPoolExecutor$$ExternalSyntheticLambda0(this));
      }

      if (!this.task2Running && this.workQueue.size() > 1) {
         this.task2Running = true;
         this.getThreadHandler2().post(new HandlerThreadPoolExecutor$$ExternalSyntheticLambda1(this));
      }

      if (!this.task3Running && this.workQueue.size() > 2) {
         this.task3Running = true;
         this.getThreadHandler3().post(new HandlerThreadPoolExecutor$$ExternalSyntheticLambda2(this));
      }

   }

   private static final void runHandler$lambda_0(HandlerThreadPoolExecutor var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");

      for(Function0 var1 = (Function0)var0.workQueue.poll(1L, TimeUnit.SECONDS); var1 != null; var1 = (Function0)var0.workQueue.poll(1L, TimeUnit.SECONDS)) {
         var1.invoke();
      }

      var0.task1Running = false;
   }

   private static final void runHandler$lambda_1(HandlerThreadPoolExecutor var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");

      for(Function0 var1 = (Function0)var0.workQueue.poll(1L, TimeUnit.SECONDS); var1 != null; var1 = (Function0)var0.workQueue.poll(1L, TimeUnit.SECONDS)) {
         var1.invoke();
      }

      var0.task2Running = false;
   }

   private static final void runHandler$lambda_2(HandlerThreadPoolExecutor var0) {
      Intrinsics.checkNotNullParameter(var0, "this$0");

      for(Function0 var1 = (Function0)var0.workQueue.poll(1L, TimeUnit.SECONDS); var1 != null; var1 = (Function0)var0.workQueue.poll(1L, TimeUnit.SECONDS)) {
         var1.invoke();
      }

      var0.task3Running = false;
   }

   public final void execute(Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "action");
      this.workQueue.put(var1);
      this.runHandler();
   }
}
