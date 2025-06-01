package com.hzbhd.canbus.car._348;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0018\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u0019J \u0010\u001a\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"},
   d2 = {"Lcom/hzbhd/canbus/car/_348/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getMContext", "()Landroid/content/Context;", "setMContext", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "getMParkPageUiSet", "()Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "msgMgr", "Lcom/hzbhd/canbus/car/_348/MsgMgr;", "tag", "", "getTag", "()I", "setTag", "(I)V", "uiMgr", "getMsgMgr", "context", "getSettingLeftIndexes", "titleSrn", "", "getSettingRightIndex", "leftTitleSrn", "rightTitleStn", "getUiMgr", "sendAirCmd", "", "bytes", "", "sendWindMode", "updateUiByDifferentCar", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private final ParkPageUiSet mParkPageUiSet;
   private MsgMgr msgMgr;
   private int tag;
   private UiMgr uiMgr;

   // $FF: synthetic method
   public static void $r8$lambda$1tOzFPFVX_F__iee1WIaOxprjlQ(UiMgr var0, SettingPageUiSet var1, int var2, int var3, int var4) {
      _init_$lambda_4(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void $r8$lambda$8y_QMgW8fDMavUs_hoBuQd_QKPc(UiMgr var0, int var1) {
      _init_$lambda_3(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$IoqAA2tbZVdyRsozR_qHRnKcBEg(UiMgr var0, int var1) {
      _init_$lambda_0(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$Jx__natToe_i_0zUM1RXJ3TWDZ4(int var0, int var1, MotionEvent var2) {
      _init_$lambda_5(var0, var1, var2);
   }

   // $FF: synthetic method
   public static void $r8$lambda$MLEqcDtq_yYd8LUtZMuXLIAS1Pg(UiMgr var0, int var1) {
      _init_$lambda_1(var0, var1);
   }

   // $FF: synthetic method
   public static void $r8$lambda$uLNBtLuMQ2bRUSsJr8YvxU_dqXU(UiMgr var0, int var1) {
      _init_$lambda_2(var0, var1);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "mContext");
      super();
      this.mContext = var1;
      ParkPageUiSet var2 = this.getParkPageUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "getParkPageUiSet(mContext)");
      this.mParkPageUiSet = var2;
      this.getAirUiSet(this.mContext).getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda0(this), new UiMgr$$ExternalSyntheticLambda1(this), new UiMgr$$ExternalSyntheticLambda2(this), new UiMgr$$ExternalSyntheticLambda3(this)});
      this.getAirUiSet(this.mContext).getFrontArea().setOnAirSeatClickListener((OnAirSeatClickListener)(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendWindMode();
         }

         public void onRightSeatClick() {
            this.this$0.sendWindMode();
         }
      }));
      this.getAirUiSet(this.mContext).getFrontArea().setSetWindSpeedViewOnClickListener((OnAirWindSpeedUpDownClickListener)(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCmd(new byte[]{22, -88, 12, 1});
         }

         public void onClickRight() {
            this.this$0.sendAirCmd(new byte[]{22, -88, 11, 1});
         }
      }));
      this.getAirUiSet(this.mContext).getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{(OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCmd(new byte[]{22, -88, 14, 1});
         }

         public void onClickUp() {
            this.this$0.sendAirCmd(new byte[]{22, -88, 13, 1});
         }
      }), null, (OnAirTemperatureUpDownClickListener)(new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCmd(new byte[]{22, -88, 16, 1});
         }

         public void onClickUp() {
            this.this$0.sendAirCmd(new byte[]{22, -88, 15, 1});
         }
      })});
      if (this.getEachId() == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1});
      }

      if (this.getEachId() == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 2});
      }

      if (this.getEachId() == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 3});
      }

      if (this.getEachId() == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 4});
      }

      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda4(this, var3));
      var2.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda5(this.mContext.getResources().getDisplayMetrics().widthPixels, this.mContext.getResources().getDisplayMetrics().heightPixels));
   }

   private static final void _init_$lambda_0(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 != 0) {
         if (var1 == 1) {
            var0.sendAirCmd(new byte[]{22, -88, 0, 1});
         }
      } else {
         var0.sendAirCmd(new byte[]{22, -88, 1, 1});
      }

   }

   private static final void _init_$lambda_1(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirCmd(new byte[]{22, -88, 6, 1});
      }

   }

   private static final void _init_$lambda_2(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 0) {
         var0.sendAirCmd(new byte[]{22, -88, 23, 1});
      }

   }

   private static final void _init_$lambda_3(UiMgr var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      if (var1 == 1) {
         var0.sendAirCmd(new byte[]{22, -88, 2, 1});
      }

   }

   private static final void _init_$lambda_4(UiMgr var0, SettingPageUiSet var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      MsgMgr var5 = var0.getMsgMgr(var0.mContext);
      Intrinsics.checkNotNull(var5);
      var5.updateSetting(var2, var3, var4);
      if (Intrinsics.areEqual((Object)var6, (Object)"car_light_set")) {
         var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
         if (Intrinsics.areEqual((Object)var6, (Object)"ceiling_light_delay")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -128, 0, (byte)var4});
         } else if (Intrinsics.areEqual((Object)var6, (Object)"power_saving_time")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -128, 1, (byte)var4});
         }
      } else if (Intrinsics.areEqual((Object)var6, (Object)"car_lock_set")) {
         var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
         if (var6 != null) {
            switch (var6) {
               case "_282_07_0_6":
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 5, (byte)var4});
                  break;
               case "_1193_setting_1_8":
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 6, (byte)var4});
                  break;
               case "remote_lock_feedback":
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 4, (byte)var4});
                  break;
               case "automatic_relock":
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 3, (byte)var4});
                  break;
               case "speed_lock":
                  CanbusMsgSender.sendMsg(new byte[]{22, -128, 2, (byte)var4});
            }
         }
      }

   }

   private static final void _init_$lambda_5(int var0, int var1, MotionEvent var2) {
      int var3 = (int)(var2.getX() * (800.0F / (float)var0));
      int var4 = (int)(var2.getY() * (480.0F / (float)var1));
      if (var2.getAction() == 0) {
         lambda_5$sendPanoramaFrame(1, DataHandleUtils.getMsb(var3), DataHandleUtils.getLsb(var3), DataHandleUtils.getMsb(var4), DataHandleUtils.getLsb(var4));
      } else if (var2.getAction() == 1) {
         lambda_5$sendPanoramaFrame(0, DataHandleUtils.getMsb(var3), DataHandleUtils.getLsb(var3), DataHandleUtils.getMsb(var4), DataHandleUtils.getLsb(var4));
      }

      Log.i("lyn", " x:" + var3 + ", y:" + var4 + " \n width:" + var0 + ", high:" + var1);
   }

   private final MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         MsgMgrInterface var2 = MsgMgrFactory.getCanMsgMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._348.MsgMgr");
         this.msgMgr = (MsgMgr)var2;
      }

      return this.msgMgr;
   }

   private final UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._348.UiMgr");
         this.uiMgr = (UiMgr)var2;
      }

      return this.uiMgr;
   }

   private static final void lambda_5$sendPanoramaFrame(int var0, int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var0, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   private final void sendAirCmd(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   private final void sendWindMode() {
      int var1 = this.tag;
      if (var1 == 0) {
         this.sendAirCmd(new byte[]{22, -88, 7, 1});
         this.tag = 1;
      } else if (var1 == 1) {
         this.sendAirCmd(new byte[]{22, -88, 8, 1});
         this.tag = 2;
      } else if (var1 == 2) {
         this.sendAirCmd(new byte[]{22, -88, 9, 1});
         this.tag = 3;
      } else if (var1 == 3) {
         this.sendAirCmd(new byte[]{22, -88, 10, 1});
         this.tag = 0;
      }

   }

   public final Context getMContext() {
      return this.mContext;
   }

   public final ParkPageUiSet getMParkPageUiSet() {
      return this.mParkPageUiSet;
   }

   public final int getSettingLeftIndexes(Context var1, String var2) {
      Intrinsics.checkNotNullParameter(var2, "titleSrn");
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var6.iterator();
      int var4 = var6.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         if (Intrinsics.areEqual((Object)var2, (Object)((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
   }

   public final int getSettingRightIndex(Context var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var2, "leftTitleSrn");
      Intrinsics.checkNotNullParameter(var3, "rightTitleStn");
      List var8 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var10 = var8.iterator();
      int var6 = var8.size();

      for(int var4 = 0; var4 < var6; ++var4) {
         SettingPageUiSet.ListBean var11 = (SettingPageUiSet.ListBean)var10.next();
         if (Intrinsics.areEqual((Object)var2, (Object)var11.getTitleSrn())) {
            var8 = var11.getItemList();
            Iterator var9 = var8.iterator();
            int var7 = var8.size();

            for(int var5 = 0; var5 < var7; ++var5) {
               if (Intrinsics.areEqual((Object)var3, (Object)((SettingPageUiSet.ListBean.ItemListBean)var9.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public final int getTag() {
      return this.tag;
   }

   public final void setMContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mContext = var1;
   }

   public final void setTag(int var1) {
      this.tag = var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      if (this.getEachId() == 2) {
         this.removeMainPrjBtnByName(this.mContext, "air");
      }

      if (this.getEachId() != 1 && this.getEachId() != 3) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

   }
}
