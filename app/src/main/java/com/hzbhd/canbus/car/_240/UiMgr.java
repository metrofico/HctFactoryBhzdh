package com.hzbhd.canbus.car._240;

import android.content.Context;
import android.provider.Settings.System;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"},
   d2 = {"Lcom/hzbhd/canbus/car/_240/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mOnAirBtnClickListenerBottom", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "mOnAirBtnClickListenerCenter1", "mOnAirBtnClickListenerCenter2", "mOnAirBtnClickListenerTop", "mOnAirPageStatusListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirPageStatusListener;", "mOnSettingItemSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "mOnSettingPageStatusListener", "Lcom/hzbhd/canbus/interfaces/OnSettingPageStatusListener;", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getMParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "monUpDownClickListenerLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "monUpDownClickListenerRight", "onSettingItemSeekbarSelectListener", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "setWindSpeedViewOnClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "sendPanoramaData", "", "d0", "", "updateUiByDifferentCar", "context", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final Context mContext;
   private final OnAirBtnClickListener mOnAirBtnClickListenerBottom;
   private final OnAirBtnClickListener mOnAirBtnClickListenerCenter1;
   private final OnAirBtnClickListener mOnAirBtnClickListenerCenter2;
   private final OnAirBtnClickListener mOnAirBtnClickListenerTop;
   private final OnAirPageStatusListener mOnAirPageStatusListener;
   private final OnSettingItemSelectListener mOnSettingItemSelectListener;
   private final OnSettingPageStatusListener mOnSettingPageStatusListener;
   private final ParkPageUiSet mParkPageUiSet;
   private final OnAirTemperatureUpDownClickListener monUpDownClickListenerLeft;
   private final OnAirTemperatureUpDownClickListener monUpDownClickListenerRight;
   private final OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
   private final OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener;

   // $FF: synthetic method
   public static void $r8$lambda$1mLAhMr_Ch573HFXuvClLTuRT4c(SettingPageUiSet var0, UiMgr var1, int var2, int var3) {
      _init_$lambda_8(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$6pdOo2N97xvd3nS2ZDsY7jtWBGw(UiMgr var0, int var1, int var2, int var3) {
      mOnSettingItemSelectListener$lambda_6(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void $r8$lambda$AVSYo0ZlntI6f7HGzIZC6uCkE2o(UiMgr var0, int var1) {
      mOnSettingPageStatusListener$lambda_5(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$CHruQUGgd3IduSs__KeTFFr8OMY(int var0) {
      mOnAirBtnClickListenerTop$lambda_1(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$L94edXL9FxDh26inxAEchxFcfls(int var0) {
      mOnAirBtnClickListenerCenter2$lambda_3(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$RhxC_gOEyD_c9mnXVkcDlnhpsNE(int var0) {
      mOnAirBtnClickListenerCenter1$lambda_2(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$UU5_FJrtQ2JrWoh0uPqxOARonTs(UiMgr var0, int var1) {
      _init_$lambda_9(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$daWYzySoBjQoA0TiwJI_SUMWVtE(UiMgr var0, int var1) {
      mOnAirPageStatusListener$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$nBNUQvzaP8zWQa2OkD15i4Okznc(int var0) {
      mOnAirBtnClickListenerBottom$lambda_4(var0);
   }

   // $FF: synthetic method
   public static void $r8$lambda$zP78kvVrf2ZuGUc_i09xytl00Kg(UiMgr var0, int var1, int var2, int var3) {
      onSettingItemSeekbarSelectListener$lambda_7(var0, var1, var2, var3);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "mContext");
      super();
      this.mContext = var1;
      OnAirWindSpeedUpDownClickListener var2 = (OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener() {
         public void onClickLeft() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
         }

         public void onClickRight() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 1, 0});
         }
      });
      this.setWindSpeedViewOnClickListener = var2;
      UiMgr$$ExternalSyntheticLambda0 var3 = new UiMgr$$ExternalSyntheticLambda0(this);
      this.mOnAirPageStatusListener = var3;
      OnAirTemperatureUpDownClickListener var9 = (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
         }

         public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
         }
      });
      this.monUpDownClickListenerLeft = var9;
      OnAirTemperatureUpDownClickListener var6 = (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener() {
         public void onClickDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
         }

         public void onClickUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 2, 0});
         }
      });
      this.monUpDownClickListenerRight = var6;
      UiMgr$$ExternalSyntheticLambda1 var4 = new UiMgr$$ExternalSyntheticLambda1();
      this.mOnAirBtnClickListenerTop = var4;
      UiMgr$$ExternalSyntheticLambda2 var5 = new UiMgr$$ExternalSyntheticLambda2();
      this.mOnAirBtnClickListenerCenter1 = var5;
      UiMgr$$ExternalSyntheticLambda3 var8 = new UiMgr$$ExternalSyntheticLambda3();
      this.mOnAirBtnClickListenerCenter2 = var8;
      UiMgr$$ExternalSyntheticLambda4 var7 = new UiMgr$$ExternalSyntheticLambda4();
      this.mOnAirBtnClickListenerBottom = var7;
      UiMgr$$ExternalSyntheticLambda5 var11 = new UiMgr$$ExternalSyntheticLambda5(this);
      this.mOnSettingPageStatusListener = var11;
      UiMgr$$ExternalSyntheticLambda6 var14 = new UiMgr$$ExternalSyntheticLambda6(this);
      this.mOnSettingItemSelectListener = var14;
      UiMgr$$ExternalSyntheticLambda7 var13 = new UiMgr$$ExternalSyntheticLambda7(this);
      this.onSettingItemSeekbarSelectListener = var13;
      ParkPageUiSet var10 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var10, "getParkPageUiSet(mContext)");
      this.mParkPageUiSet = var10;
      SettingPageUiSet var12 = this.getSettingUiSet(var1);
      var12.setOnSettingItemSelectListener(var14);
      var12.setOnSettingPageStatusListener(var11);
      var12.setOnSettingItemSeekbarSelectListener(var13);
      var12.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda8(var12, this));
      AirPageUiSet var15 = this.getAirUiSet(var1);
      var15.setOnAirPageStatusListener(var3);
      var15.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var9, null, var6});
      var15.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{var4, var5, var8, var7});
      var15.getFrontArea().setSetWindSpeedViewOnClickListener(var2);
      var10.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda9(this));
   }

   private static final void _init_$lambda_8(SettingPageUiSet var0, UiMgr var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "this$0");
      String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      if (Intrinsics.areEqual((Object)var4, (Object)"low_car")) {
         lambda_8$sendCarData(0);
      } else if (Intrinsics.areEqual((Object)var4, (Object)"high_car")) {
         lambda_8$sendCarData(1);
      }

      Toast.makeText(var1.mContext, (CharSequence)"Success!", 0).show();
   }

   private static final void _init_$lambda_9(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      switch (var1 + 1) {
         case 1:
            var0.sendPanoramaData(1);
            break;
         case 2:
            var0.sendPanoramaData(2);
            break;
         case 3:
            var0.sendPanoramaData(3);
            break;
         case 4:
            var0.sendPanoramaData(4);
            break;
         case 5:
            var0.sendPanoramaData(5);
            break;
         case 6:
            var0.sendPanoramaData(6);
            break;
         case 7:
            var0.sendPanoramaData(7);
            break;
         case 8:
            var0.sendPanoramaData(8);
      }

   }

   private static final void lambda_8$sendCarData(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var0});
   }

   private static final void mOnAirBtnClickListenerBottom$lambda_4(int var0) {
      byte var1 = 2;
      if (var0 != 0) {
         if (var0 == 1) {
            if (!GeneralAirData.in_out_cycle) {
               var1 = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, (byte)var1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 5, 0});
         }
      } else {
         if (!GeneralAirData.ac) {
            var1 = 1;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, (byte)var1});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 4, 0});
      }

   }

   private static final void mOnAirBtnClickListenerCenter1$lambda_2(int var0) {
      LogUtil.showLog("position:" + var0);
      if (var0 == 0) {
         byte var1 = 1;
         if (GeneralAirData.auto) {
            var1 = 2;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, (byte)var1});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 9, 0});
      }

   }

   private static final void mOnAirBtnClickListenerCenter2$lambda_3(int var0) {
      LogUtil.showLog("position:" + var0);
      if (var0 == 0 && GeneralAirData.power) {
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 0, 0});
      }

   }

   private static final void mOnAirBtnClickListenerTop$lambda_1(int var0) {
      byte var1 = 2;
      if (var0 != 0) {
         if (var0 == 1) {
            if (!GeneralAirData.rear_defog) {
               var1 = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, (byte)var1});
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 6, 0});
         }
      } else {
         if (!GeneralAirData.front_defog) {
            var1 = 1;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, (byte)var1});
         CanbusMsgSender.sendMsg(new byte[]{22, -118, 7, 0});
      }

   }

   private static final void mOnAirPageStatusListener$lambda_0(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 1) {
         var0.sendData(new byte[]{22, -112, 35});
      }

   }

   private static final void mOnSettingItemSelectListener$lambda_6(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 == 1 && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var3});
         }
      } else if (var2 != 0) {
         if (var2 == 2) {
            if (var0.getCurrentCarId() == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 2, (byte)var3});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -128, 3, (byte)var3});
            }
         }
      } else {
         var1 = System.getInt(var0.mContext.getContentResolver(), "data0_06", 0);
         boolean var4;
         if (var3 == 1) {
            var4 = true;
         } else {
            var4 = false;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, -128, 1, (byte)DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var4), var1, 0, 7)});
      }

   }

   private static final void mOnSettingPageStatusListener$lambda_5(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendData(new byte[]{22, -112, 64});
      }

   }

   private static final void onSettingItemSeekbarSelectListener$lambda_7(UiMgr var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      boolean var4;
      if (System.getInt(var0.mContext.getContentResolver(), "data0_7", 0) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -128, 1, (byte)DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var4), var3, 0, 7)});
   }

   private final void sendPanoramaData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1});
   }

   public final ParkPageUiSet getMParkPageUiSet() {
      return this.mParkPageUiSet;
   }

   public void updateUiByDifferentCar(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0 && var2 != 1) {
         if (var2 != 2) {
            if (var2 == 3) {
               this.removeSettingLeftItem(var1, 1, 1);
               this.removeSettingRightItem(var1, 0, 2, 3);
               this.removeMainPrjBtn(var1, 0, 1);
            }
         } else {
            this.removeSettingRightItem(var1, 0, 3, 3);
         }
      } else {
         this.removeMainPrjBtn(var1, 1, 1);
      }

   }
}
