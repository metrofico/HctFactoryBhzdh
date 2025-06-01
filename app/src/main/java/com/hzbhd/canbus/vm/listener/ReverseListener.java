package com.hzbhd.canbus.vm.listener;

import android.os.SystemProperties;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0005H\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0017"},
   d2 = {"Lcom/hzbhd/canbus/vm/listener/ReverseListener;", "", "()V", "actionBefortViewInit", "Lkotlin/Function0;", "", "getActionBefortViewInit", "()Lkotlin/jvm/functions/Function0;", "setActionBefortViewInit", "(Lkotlin/jvm/functions/Function0;)V", "mReverseState", "", "sysReverse", "getSysReverse", "()I", "setSysReverse", "(I)V", "userReverse", "getUserReverse", "setUserReverse", "isReversing", "", "updateReverse", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ReverseListener {
   private Function0 actionBefortViewInit;
   private int mReverseState = -1;
   private int sysReverse = -1;
   private int userReverse = -1;

   // $FF: synthetic method
   public static void $r8$lambda$jOb82pQyHr0WZSIPFgCw9Fbn_lU(ReverseListener var0, int var1) {
      _init_$lambda_0(var0, var1);
   }

   public ReverseListener() {
      this.sysReverse = ShareDataManager.getInstance().registerShareIntListener("misc.Reverse", new ReverseListener$$ExternalSyntheticLambda0(this));
      this.updateReverse();
   }

   private static final void _init_$lambda_0(ReverseListener var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      var0.sysReverse = var1;
      var0.updateReverse();
   }

   private final void updateReverse() {
      byte var1;
      if (this.sysReverse != 1 && this.userReverse != 1) {
         var1 = 0;
      } else {
         var1 = 1;
      }

      if (LogUtil.log5()) {
         LogUtil.d("reverseState : " + var1 + ", mIsCarBackGpioLevel=" + this.mReverseState);
      }

      if (this.mReverseState != var1) {
         if (LogUtil.log5()) {
            LogUtil.d("GpioOperator=detectCarBackState: mIsCarBackGpioLevel=" + this.mReverseState + ", level=" + var1);
         }

         this.mReverseState = var1;
         if (var1 != 0) {
            if (var1 == 1) {
               if (LogUtil.log5()) {
                  LogUtil.d("start camera ");
               }

               Function0 var2 = this.actionBefortViewInit;
               if (var2 != null) {
                  var2.invoke();
               }

               BhdWindowManager.INSTANCE.addReverseView();
               SystemProperties.set("BackCameraUI", "true");
               HandlerThreadUtilKt.runUi((Function0)null.INSTANCE);
            }
         } else {
            if (LogUtil.log5()) {
               LogUtil.d("stop camera ");
            }

            BhdWindowManager.INSTANCE.removeReverseView();
            SystemProperties.set("BackCameraUI", "false");
            HandlerThreadUtilKt.runUi((Function0)null.INSTANCE);
         }
      }

   }

   public final Function0 getActionBefortViewInit() {
      return this.actionBefortViewInit;
   }

   public final int getSysReverse() {
      return this.sysReverse;
   }

   public final int getUserReverse() {
      return this.userReverse;
   }

   public final boolean isReversing() {
      int var1 = this.mReverseState;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public final void setActionBefortViewInit(Function0 var1) {
      this.actionBefortViewInit = var1;
   }

   public final void setSysReverse(int var1) {
      this.sysReverse = var1;
   }

   public final void setUserReverse(int var1) {
      this.userReverse = var1;
   }
}
