package com.hzbhd.canbus.vm.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\bJ\u0006\u0010\u0012\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"},
   d2 = {"Lcom/hzbhd/canbus/vm/util/BhdWindowManager;", "", "()V", "mIsAddReverseView", "", "mMainHandler", "Landroid/os/Handler;", "mReverseViewParams", "Landroid/view/WindowManager$LayoutParams;", "mWindowManager", "Landroid/view/WindowManager;", "addReverseView", "", "init", "context", "Landroid/content/Context;", "initReverseWindowParams", "params", "removeReverseView", "HANDLER_MAIN_MSG", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BhdWindowManager {
   public static final BhdWindowManager INSTANCE = new BhdWindowManager();
   private static boolean mIsAddReverseView;
   private static final Handler mMainHandler = (Handler)(new Handler(Looper.getMainLooper()) {
      public void handleMessage(Message var1) {
         Intrinsics.checkNotNullParameter(var1, "msg");
         HANDLER_MAIN_MSG var3 = HANDLER_MAIN_MSG.values()[var1.what];
         if (LogUtil.log5()) {
            LogUtil.d("bhd window manager :" + var3);
         }

         int var2 = null.WhenMappings.$EnumSwitchMapping$0[var3.ordinal()];
         WindowManager var4;
         BhdWindowManager var5;
         if (var2 != 1) {
            if (var2 == 2 && BhdWindowManager.mIsAddReverseView) {
               var4 = BhdWindowManager.mWindowManager;
               Intrinsics.checkNotNull(var4);
               var4.removeView((View)Vm.Companion.getVm().getReverseMainView());
               var5 = BhdWindowManager.INSTANCE;
               BhdWindowManager.mIsAddReverseView = false;
            }
         } else if (!BhdWindowManager.mIsAddReverseView) {
            if (LogUtil.log5()) {
               LogUtil.d("handleMessage: " + Vm.Companion.getVm().getReverseMainView() + "  ,  " + BhdWindowManager.mReverseViewParams);
            }

            var4 = BhdWindowManager.mWindowManager;
            Intrinsics.checkNotNull(var4);
            var4.addView((View)Vm.Companion.getVm().getReverseMainView(), (ViewGroup.LayoutParams)BhdWindowManager.mReverseViewParams);
            var5 = BhdWindowManager.INSTANCE;
            BhdWindowManager.mIsAddReverseView = true;
         }

      }

      @Metadata(
         k = 3,
         mv = {1, 7, 1},
         xi = 48
      )
      public final class WhenMappings {
         public static final int[] $EnumSwitchMapping$0;

         static {
            int[] var0 = new int[HANDLER_MAIN_MSG.values().length];
            var0[HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal()] = 1;
            var0[HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal()] = 2;
            $EnumSwitchMapping$0 = var0;
         }
      }
   });
   private static WindowManager.LayoutParams mReverseViewParams;
   private static WindowManager mWindowManager;

   private BhdWindowManager() {
   }

   public final void addReverseView() {
      Handler var1 = mMainHandler;
      var1.removeMessages(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
      var1.removeMessages(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
      var1.sendEmptyMessage(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
   }

   public final void init(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      mWindowManager = (WindowManager)var1.getSystemService(WindowManager.class);
   }

   public final void initReverseWindowParams(WindowManager.LayoutParams var1) {
      mReverseViewParams = var1;
   }

   public final void removeReverseView() {
      Handler var1 = mMainHandler;
      var1.removeMessages(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
      var1.removeMessages(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
      var1.sendEmptyMessage(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/canbus/vm/util/BhdWindowManager$HANDLER_MAIN_MSG;", "", "(Ljava/lang/String;I)V", "NULL", "MSG_ADD_REVERSE_VIEW", "MSG_REMOVE_REVERSE_VIEW", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum HANDLER_MAIN_MSG {
      private static final HANDLER_MAIN_MSG[] $VALUES = $values();
      MSG_ADD_REVERSE_VIEW,
      MSG_REMOVE_REVERSE_VIEW,
      NULL;

      // $FF: synthetic method
      private static final HANDLER_MAIN_MSG[] $values() {
         return new HANDLER_MAIN_MSG[]{NULL, MSG_ADD_REVERSE_VIEW, MSG_REMOVE_REVERSE_VIEW};
      }
   }
}
