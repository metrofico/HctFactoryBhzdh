package com.hzbhd.ui.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001:\u0002./B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\tJ\u0006\u0010%\u001a\u00020#J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u000eH\u0016J\b\u0010(\u001a\u00020#H\u0016J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0016J\u000e\u0010+\u001a\u00020#2\u0006\u0010$\u001a\u00020\tJ\b\u0010,\u001a\u00020#H\u0016J\b\u0010-\u001a\u00020#H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00060"},
   d2 = {"Lcom/hzbhd/ui/util/FullScreenHelper;", "", "()V", "AUTO_HIDE_SYSTEMUI_TIME", "", "getAUTO_HIDE_SYSTEMUI_TIME", "()J", "activitys", "Ljava/util/ArrayList;", "Landroid/app/Activity;", "Lkotlin/collections/ArrayList;", "getActivitys", "()Ljava/util/ArrayList;", "isFullScreen", "", "()Z", "setFullScreen", "(Z)V", "lastMsg", "Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;", "getLastMsg", "()Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;", "setLastMsg", "(Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;)V", "listener", "Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;", "getListener", "()Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;", "setListener", "(Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;)V", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "addAvtivity", "", "activity", "finish", "fullScreenChange", "fullScreen", "fullScreenChangeInit", "hideSystemUI", "refreshSystemUI", "removeAvtivity", "showOrHideSystemUI", "showSystemUI", "MAIN_MSG", "OnFullScreenChangeListener", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class FullScreenHelper {
   private final long AUTO_HIDE_SYSTEMUI_TIME = 5000L;
   private final ArrayList activitys = new ArrayList();
   private boolean isFullScreen;
   private MAIN_MSG lastMsg;
   private OnFullScreenChangeListener listener;
   private final Handler mainHandler = (Handler)(new Handler(this, Looper.getMainLooper()) {
      final FullScreenHelper this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         Intrinsics.checkNotNullParameter(var1, "msg");
         MAIN_MSG var3 = MAIN_MSG.values()[var1.what];
         if (this.this$0.getLastMsg() != var3) {
            if (LogUtil.log5()) {
               LogUtil.d("handleMessage: " + this.this$0.getLastMsg() + " != " + var3);
            }

         } else {
            if (LogUtil.log5()) {
               LogUtil.d("handleMessage: " + var3);
            }

            int var2 = null.WhenMappings.$EnumSwitchMapping$0[var3.ordinal()];
            OnFullScreenChangeListener var4;
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3 && this.this$0.isFullScreen()) {
                     var4 = this.this$0.getListener();
                     if (var4 != null) {
                        var4.notFullScreen();
                     }

                     this.this$0.fullScreenChange(false);
                  }
               } else if (!this.this$0.isFullScreen()) {
                  var4 = this.this$0.getListener();
                  if (var4 != null) {
                     var4.fullScreen();
                  }

                  this.this$0.fullScreenChange(true);
               }
            } else {
               if (this.this$0.isFullScreen()) {
                  var4 = this.this$0.getListener();
                  if (var4 != null) {
                     var4.notFullScreen();
                  }

                  this.this$0.fullScreenChange(false);
               }

               this.this$0.setLastMsg(MAIN_MSG.SYSTEMUI_HIDE);
               this.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_HIDE.ordinal(), this.this$0.getAUTO_HIDE_SYSTEMUI_TIME());
            }

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
            int[] var0 = new int[MAIN_MSG.values().length];
            var0[MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal()] = 1;
            var0[MAIN_MSG.SYSTEMUI_HIDE.ordinal()] = 2;
            var0[MAIN_MSG.SYSTEMUI_SHOW.ordinal()] = 3;
            $EnumSwitchMapping$0 = var0;
         }
      }
   });

   public FullScreenHelper() {
      this.lastMsg = MAIN_MSG.NULL;
   }

   public final void addAvtivity(Activity var1) {
      Intrinsics.checkNotNullParameter(var1, "activity");
      if (!this.activitys.contains(var1)) {
         this.activitys.add(var1);
      }

   }

   public final void finish() {
      Iterator var1 = this.activitys.iterator();

      while(var1.hasNext()) {
         ((Activity)var1.next()).finish();
      }

   }

   public void fullScreenChange(boolean var1) {
      this.isFullScreen = var1;
      if (LogUtil.log5()) {
         LogUtil.d("fullScreenChange: " + var1);
      }

      Iterator var2 = this.activitys.iterator();

      while(var2.hasNext()) {
         Activity var3 = (Activity)var2.next();
         if (!var3.isInMultiWindowMode()) {
            WindowManager.LayoutParams var4;
            if (var1) {
               var4 = var3.getWindow().getAttributes();
               var4.flags |= 1024;
               var3.getWindow().setAttributes(var4);
            } else {
               var4 = var3.getWindow().getAttributes();
               var4.flags &= -1025;
               var3.getWindow().setAttributes(var4);
            }
         }
      }

   }

   public void fullScreenChangeInit() {
      if (LogUtil.log5()) {
         LogUtil.d("fullScreenChangeInit");
      }

      Iterator var1 = this.activitys.iterator();

      while(var1.hasNext()) {
         Activity var2 = (Activity)var1.next();
         var2.getWindow().addFlags(512);
         var2.getWindow().addFlags(256);
      }

   }

   public final long getAUTO_HIDE_SYSTEMUI_TIME() {
      return this.AUTO_HIDE_SYSTEMUI_TIME;
   }

   public final ArrayList getActivitys() {
      return this.activitys;
   }

   public final MAIN_MSG getLastMsg() {
      return this.lastMsg;
   }

   public final OnFullScreenChangeListener getListener() {
      return this.listener;
   }

   public final Handler getMainHandler() {
      return this.mainHandler;
   }

   public void hideSystemUI() {
      this.lastMsg = MAIN_MSG.SYSTEMUI_HIDE;
      if (LogUtil.log5()) {
         LogUtil.d("hideSystemUI: ");
      }

      this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
      this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
      this.mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
      this.mainHandler.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_HIDE.ordinal(), 10L);
   }

   public final boolean isFullScreen() {
      return this.isFullScreen;
   }

   public void refreshSystemUI() {
      this.lastMsg = MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE;
      if (LogUtil.log5()) {
         LogUtil.d("refreshSystemUI: ");
      }

      this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
      this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
      this.mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
      this.mainHandler.sendEmptyMessageDelayed(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal(), 10L);
   }

   public final void removeAvtivity(Activity var1) {
      Intrinsics.checkNotNullParameter(var1, "activity");
      if (this.activitys.contains(var1)) {
         this.activitys.remove(var1);
      }

   }

   public final void setFullScreen(boolean var1) {
      this.isFullScreen = var1;
   }

   public final void setLastMsg(MAIN_MSG var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.lastMsg = var1;
   }

   public final void setListener(OnFullScreenChangeListener var1) {
      this.listener = var1;
   }

   public void showOrHideSystemUI() {
      if (this.isFullScreen) {
         this.refreshSystemUI();
      } else {
         this.hideSystemUI();
      }

   }

   public void showSystemUI() {
      this.lastMsg = MAIN_MSG.SYSTEMUI_SHOW;
      if (LogUtil.log5()) {
         LogUtil.d("showSystemUI: ");
      }

      this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
      this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
      this.mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
      this.mainHandler.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_SHOW.ordinal(), 10L);
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"},
      d2 = {"Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;", "", "(Ljava/lang/String;I)V", "NULL", "REFRESH_SYSTEMUI_VISIABLE", "SYSTEMUI_HIDE", "SYSTEMUI_SHOW", "java-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum MAIN_MSG {
      private static final MAIN_MSG[] $VALUES = $values();
      NULL,
      REFRESH_SYSTEMUI_VISIABLE,
      SYSTEMUI_HIDE,
      SYSTEMUI_SHOW;

      // $FF: synthetic method
      private static final MAIN_MSG[] $values() {
         return new MAIN_MSG[]{NULL, REFRESH_SYSTEMUI_VISIABLE, SYSTEMUI_HIDE, SYSTEMUI_SHOW};
      }
   }

   @Metadata(
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"},
      d2 = {"Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;", "", "fullScreen", "", "notFullScreen", "java-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface OnFullScreenChangeListener {
      void fullScreen();

      void notFullScreen();
   }
}
