package com.hzbhd.canbus.ui_mgr;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010*\u001a\u0004\u0018\u00010+2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u0012\u0010,\u001a\u00020-2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006."},
   d2 = {"Lcom/hzbhd/canbus/ui_mgr/UiMgrInterfaceUtil;", "Lcom/hzbhd/canbus/interfaces/UiMgrInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mUiMgrInterface", "getMUiMgrInterface", "()Lcom/hzbhd/canbus/interfaces/UiMgrInterface;", "mUiMgrInterface$delegate", "Lkotlin/Lazy;", "getAirUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "getAmplifierPageUiSet", "Lcom/hzbhd/canbus/ui_set/AmplifierPageUiSet;", "getBubbleUiSet", "Lcom/hzbhd/canbus/ui_set/BubbleUiSet;", "getCusPanoramicView", "Landroid/view/View;", "getDriverDataPageUiSet", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet;", "getHybirdPageUiSet", "Lcom/hzbhd/canbus/ui_set/HybirdPageUiSet;", "getMainUiSet", "Lcom/hzbhd/canbus/ui_set/MainPageUiSet;", "getOnStartPageUiSet", "Lcom/hzbhd/canbus/ui_set/OnStartPageUiSet;", "getOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getPageUiSet", "Lcom/hzbhd/canbus/ui_set/PageUiSet;", "getPanelKeyPageUiSet", "Lcom/hzbhd/canbus/ui_set/PanelKeyPageUiSet;", "getParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getSettingUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "getSyncPageUiSet", "Lcom/hzbhd/canbus/ui_set/SyncPageUiSet;", "getTireUiSet", "Lcom/hzbhd/canbus/ui_set/TirePageUiSet;", "getWarningPageUiSet", "Lcom/hzbhd/canbus/ui_set/WarningPageUiSet;", "updateUiByDifferentCar", "", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgrInterfaceUtil implements UiMgrInterface {
   private final Context context;
   private final Lazy mUiMgrInterface$delegate;

   public UiMgrInterfaceUtil(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      this.context = var1;
      this.mUiMgrInterface$delegate = LazyKt.lazy((Function0)(new Function0(this) {
         final UiMgrInterfaceUtil this$0;

         {
            this.this$0 = var1;
         }

         public final UiMgrInterface invoke() {
            UiMgrInterface var1;
            try {
               StringBuilder var4 = new StringBuilder();
               LogUtil.showLog(var4.append("getCanUiMgr:").append(CanbusConfig.INSTANCE.getCanType()).toString());
               var4 = new StringBuilder();
               Object var5 = Class.forName(var4.append("com.hzbhd.canbus.car._").append(CanbusConfig.INSTANCE.getCanType()).append(".UiMgr").toString()).getConstructors()[0].newInstance(this.this$0.getContext());
               Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type com.hzbhd.canbus.interfaces.UiMgrInterface");
               var1 = (UiMgrInterface)var5;
            } catch (Exception var3) {
               LogUtil.showLog("getCanUiMgr:e:" + var3);
               var3.printStackTrace();
               var1 = null;
               UiMgrInterface var2 = (UiMgrInterface)null;
            }

            return var1;
         }
      }));
   }

   public AirPageUiSet getAirUiSet(Context var1) {
      AirPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getAirUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public AmplifierPageUiSet getAmplifierPageUiSet(Context var1) {
      AmplifierPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getAmplifierPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public BubbleUiSet getBubbleUiSet(Context var1) {
      BubbleUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getBubbleUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public final Context getContext() {
      return this.context;
   }

   public View getCusPanoramicView(Context var1) {
      View var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getCusPanoramicView(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public DriverDataPageUiSet getDriverDataPageUiSet(Context var1) {
      DriverDataPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getDriverDataPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public HybirdPageUiSet getHybirdPageUiSet(Context var1) {
      HybirdPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getHybirdPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public final UiMgrInterface getMUiMgrInterface() {
      return (UiMgrInterface)this.mUiMgrInterface$delegate.getValue();
   }

   public MainPageUiSet getMainUiSet(Context var1) {
      MainPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getMainUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public OnStartPageUiSet getOnStartPageUiSet(Context var1) {
      OnStartPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getOnStartPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      OriginalCarDevicePageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getOriginalCarDevicePageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public PageUiSet getPageUiSet(Context var1) {
      PageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public PanelKeyPageUiSet getPanelKeyPageUiSet(Context var1) {
      PanelKeyPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getPanelKeyPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public ParkPageUiSet getParkPageUiSet(Context var1) {
      ParkPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getParkPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public SettingPageUiSet getSettingUiSet(Context var1) {
      SettingPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getSettingUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public SyncPageUiSet getSyncPageUiSet(Context var1) {
      SyncPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getSyncPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public TirePageUiSet getTireUiSet(Context var1) {
      TirePageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getTireUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public WarningPageUiSet getWarningPageUiSet(Context var1) {
      WarningPageUiSet var2 = null;

      Exception var10000;
      label27: {
         boolean var10001;
         UiMgrInterface var3;
         try {
            var3 = this.getMUiMgrInterface();
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
            break label27;
         }

         if (var3 == null) {
            return var2;
         }

         try {
            var2 = var3.getWarningPageUiSet(var1);
            return var2;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var6 = var10000;
      var6.printStackTrace();
      return null;
   }

   public void updateUiByDifferentCar(Context param1) {
      // $FF: Couldn't be decompiled
   }
}
