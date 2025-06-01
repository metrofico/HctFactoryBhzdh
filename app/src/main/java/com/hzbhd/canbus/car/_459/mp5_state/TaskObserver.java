package com.hzbhd.canbus.car._459.mp5_state;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemProperties;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.constant.share.source.ActivityInfoShare;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.ContextUtilKt;
import com.hzbhd.util.LogUtil;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002&'B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\b\u0010\"\u001a\u00020\u001eH\u0002J\u0012\u0010#\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u000e\u001a\u00060\u000fR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001a\u0010\u001b¨\u0006("},
   d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver;", "", "()V", "MSG_REFRESH_MEMEORY", "", "getMSG_REFRESH_MEMEORY", "()I", "am", "Landroid/app/ActivityManager;", "kotlin.jvm.PlatformType", "getAm", "()Landroid/app/ActivityManager;", "am$delegate", "Lkotlin/Lazy;", "currStatus", "Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatus;", "getCurrStatus", "()Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatus;", "currStatusListener", "Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;", "getCurrStatusListener", "()Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;", "setCurrStatusListener", "(Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "reSendMsg", "", "refreshMemory", "refreshRunning", "refreshStorage", "registerShareDataListener", "updateSourceInfo", "sourceInfo", "", "CurrStatus", "CurrStatusListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class TaskObserver {
   private final int MSG_REFRESH_MEMEORY = 1;
   private final Lazy am$delegate;
   private final CurrStatus currStatus = new CurrStatus(this);
   private CurrStatusListener currStatusListener;
   private final Lazy handler$delegate;

   // $FF: synthetic method
   public static void $r8$lambda$CdNyvUrj7KimUfs50X79rTzgl1o(TaskObserver var0, String var1) {
      registerShareDataListener$lambda_0(var0, var1);
   }

   public TaskObserver() {
      this.am$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      this.handler$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final TaskObserver this$0;

         // $FF: synthetic method
         public static boolean $r8$lambda$gRY6RRGqe4Its_RhWA4Mw2NN_n0(TaskObserver var0, Message var1) {
            return invoke$lambda_0(var0, var1);
         }

         {
            this.this$0 = var1;
         }

         private static final boolean invoke$lambda_0(TaskObserver var0, Message var1) {
            Intrinsics.checkNotNullParameter(var0, "this$0");
            if (var1.what == var0.getMSG_REFRESH_MEMEORY()) {
               var0.refreshStorage();
               var0.refreshMemory();
               var0.refreshRunning();
               var0.reSendMsg();
            }

            return true;
         }

         public final Handler invoke() {
            HandlerThread var1 = new HandlerThread("refreshMemoryInfo");
            var1.start();
            return new Handler(var1.getLooper(), new TaskObserver$handler$2$$ExternalSyntheticLambda0(this.this$0));
         }
      }));
      this.reSendMsg();
      this.registerShareDataListener();
   }

   private final Handler getHandler() {
      return (Handler)this.handler$delegate.getValue();
   }

   private final void reSendMsg() {
      this.getHandler().removeMessages(this.MSG_REFRESH_MEMEORY);
      this.getHandler().sendEmptyMessageDelayed(this.MSG_REFRESH_MEMEORY, 1000L);
   }

   private final void registerShareDataListener() {
      this.updateSourceInfo(ShareDataManager.getInstance().registerShareStringListener("sourcemanager.CurrentSource", new TaskObserver$$ExternalSyntheticLambda0(this)));
   }

   private static final void registerShareDataListener$lambda_0(TaskObserver var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (LogUtil.log5()) {
         LogUtil.d("sourceinfo:" + var1);
      }

      var0.updateSourceInfo(var1);
   }

   private final void updateSourceInfo(String var1) {
      if (var1 != null) {
         SourceConstantsDef.SOURCE_ID var6 = SourceConstantsDef.SOURCE_ID.getType(((ActivityInfoShare)bhdGsonUtils.fromJson(var1, ActivityInfoShare.class)).getId());
         if (LogUtil.log5()) {
            LogUtil.d("onInit: CURRENT_SOURCE_INFO = " + var6);
         }

         boolean var4 = this.currStatus.getMusicRunning();
         SourceConstantsDef.SOURCE_ID var5 = SourceConstantsDef.SOURCE_ID.MUSIC;
         boolean var3 = true;
         boolean var2;
         if (var6 == var5) {
            var2 = true;
         } else {
            var2 = false;
         }

         CurrStatus var8;
         CurrStatusListener var9;
         if (var4 != var2) {
            var8 = this.currStatus;
            if (var6 == SourceConstantsDef.SOURCE_ID.MUSIC) {
               var2 = true;
            } else {
               var2 = false;
            }

            var8.setMusicRunning(var2);
            var9 = this.currStatusListener;
            if (var9 != null) {
               var9.onMusicRunningChange(this.currStatus.getMusicRunning());
            }
         }

         var4 = this.currStatus.getVideoRunning();
         if (var6 == SourceConstantsDef.SOURCE_ID.VIDEO) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var4 != var2) {
            var8 = this.currStatus;
            if (var6 == SourceConstantsDef.SOURCE_ID.VIDEO) {
               var2 = true;
            } else {
               var2 = false;
            }

            var8.setVideoRunning(var2);
            var9 = this.currStatusListener;
            if (var9 != null) {
               var9.onVideoRunningChange(this.currStatus.getVideoRunning());
            }
         }

         var4 = this.currStatus.getRadioRunning();
         if (var6 == SourceConstantsDef.SOURCE_ID.FM) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var4 != var2) {
            var8 = this.currStatus;
            if (var6 == SourceConstantsDef.SOURCE_ID.FM) {
               var2 = var3;
            } else {
               var2 = false;
            }

            var8.setRadioRunning(var2);
            CurrStatusListener var7 = this.currStatusListener;
            if (var7 != null) {
               var7.onRadioRunningChange(this.currStatus.getRadioRunning());
            }
         }
      }

   }

   public final ActivityManager getAm() {
      return (ActivityManager)this.am$delegate.getValue();
   }

   public final CurrStatus getCurrStatus() {
      return this.currStatus;
   }

   public final CurrStatusListener getCurrStatusListener() {
      return this.currStatusListener;
   }

   public final int getMSG_REFRESH_MEMEORY() {
      return this.MSG_REFRESH_MEMEORY;
   }

   public final void refreshMemory() {
      ActivityManager.MemoryInfo var2 = new ActivityManager.MemoryInfo();
      this.getAm().getMemoryInfo(var2);
      if (LogUtil.log5()) {
         LogUtil.d(var2.availMem + "  " + var2.totalMem);
      }

      float var1 = (float)((double)var2.availMem / (double)var2.totalMem);
      if (Math.abs(var1 - this.currStatus.getMemory()) > 0.01F) {
         this.currStatus.setMemory(var1);
         CurrStatusListener var3 = this.currStatusListener;
         if (var3 != null) {
            var3.onMemoryChange(var1);
         }
      }

   }

   public final void refreshRunning() {
      List var5 = ((ActivityManager)ContextUtilKt.getBaseContext().getSystemService(ActivityManager.class)).getRunningAppProcesses();
      String var4 = SystemProperties.get("persist.sys.navi.name");
      Iterator var8 = var5.iterator();
      boolean var2 = false;
      boolean var1 = false;

      while(var8.hasNext()) {
         String var6 = ((ActivityManager.RunningAppProcessInfo)var8.next()).processName;
         Intrinsics.checkNotNullExpressionValue(var6, "info.processName");
         CharSequence var9 = (CharSequence)((String)StringsKt.split$default((CharSequence)var6, new String[]{":"}, false, 0, 6, (Object)null).get(0));
         Intrinsics.checkNotNullExpressionValue(var4, "naviPackage");
         boolean var3 = var2;
         if (StringsKt.contains$default(var9, (CharSequence)var4, false, 2, (Object)null)) {
            var3 = true;
         }

         var2 = var3;
         if (StringsKt.contains$default(var9, (CharSequence)"net.easyconn", false, 2, (Object)null)) {
            var1 = true;
            var2 = var3;
         }
      }

      CurrStatusListener var7;
      if (this.currStatus.getNaviRunning() != var2) {
         this.currStatus.setNaviRunning(var2);
         var7 = this.currStatusListener;
         if (var7 != null) {
            var7.onNaviRunningChange(var2);
         }
      }

      if (this.currStatus.getEasyRunning() != var1) {
         this.currStatus.setEasyRunning(var1);
         var7 = this.currStatusListener;
         if (var7 != null) {
            var7.onEasyRunningChange(var1);
         }
      }

   }

   public final void refreshStorage() {
      File var2 = new File("/storage/emulated/0");
      if (LogUtil.log5()) {
         LogUtil.d("refreshStorage: " + var2.getFreeSpace() + "  " + var2.getTotalSpace());
      }

      float var1 = (float)((double)var2.getFreeSpace() / (double)var2.getTotalSpace());
      if (Math.abs(var1 - this.currStatus.getEmulated()) > 0.01F) {
         this.currStatus.setEmulated(var1);
         CurrStatusListener var3 = this.currStatusListener;
         if (var3 != null) {
            var3.onEmulatedChange(var1);
         }
      }

   }

   public final void setCurrStatusListener(CurrStatusListener var1) {
      this.currStatusListener = var1;
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0014\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"},
      d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatus;", "", "(Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver;)V", "easyRunning", "", "getEasyRunning", "()Z", "setEasyRunning", "(Z)V", "emulated", "", "getEmulated", "()F", "setEmulated", "(F)V", "memory", "getMemory", "setMemory", "musicRunning", "getMusicRunning", "setMusicRunning", "naviRunning", "getNaviRunning", "setNaviRunning", "radioRunning", "getRadioRunning", "setRadioRunning", "videoRunning", "getVideoRunning", "setVideoRunning", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class CurrStatus {
      private boolean easyRunning;
      private float emulated;
      private float memory;
      private boolean musicRunning;
      private boolean naviRunning;
      private boolean radioRunning;
      final TaskObserver this$0;
      private boolean videoRunning;

      public CurrStatus(TaskObserver var1) {
         this.this$0 = var1;
      }

      public final boolean getEasyRunning() {
         return this.easyRunning;
      }

      public final float getEmulated() {
         return this.emulated;
      }

      public final float getMemory() {
         return this.memory;
      }

      public final boolean getMusicRunning() {
         return this.musicRunning;
      }

      public final boolean getNaviRunning() {
         return this.naviRunning;
      }

      public final boolean getRadioRunning() {
         return this.radioRunning;
      }

      public final boolean getVideoRunning() {
         return this.videoRunning;
      }

      public final void setEasyRunning(boolean var1) {
         this.easyRunning = var1;
      }

      public final void setEmulated(float var1) {
         this.emulated = var1;
      }

      public final void setMemory(float var1) {
         this.memory = var1;
      }

      public final void setMusicRunning(boolean var1) {
         this.musicRunning = var1;
      }

      public final void setNaviRunning(boolean var1) {
         this.naviRunning = var1;
      }

      public final void setRadioRunning(boolean var1) {
         this.radioRunning = var1;
      }

      public final void setVideoRunning(boolean var1) {
         this.videoRunning = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000e"},
      d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;", "", "onEasyRunningChange", "", "running", "", "onEmulatedChange", "value", "", "onMemoryChange", "onMusicRunningChange", "onNaviRunningChange", "onRadioRunningChange", "onVideoRunningChange", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface CurrStatusListener {
      void onEasyRunningChange(boolean var1);

      void onEmulatedChange(float var1);

      void onMemoryChange(float var1);

      void onMusicRunningChange(boolean var1);

      void onNaviRunningChange(boolean var1);

      void onRadioRunningChange(boolean var1);

      void onVideoRunningChange(boolean var1);
   }
}
