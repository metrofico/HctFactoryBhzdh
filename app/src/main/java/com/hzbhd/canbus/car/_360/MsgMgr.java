package com.hzbhd.canbus.car._360;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010!\u001a\u00020\"2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010#\u001a\u00020\"2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\"H\u0002J\u0010\u0010'\u001a\u00020\"2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\b\u0010(\u001a\u00020\"H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R6\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019j\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b`\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006)"},
   d2 = {"Lcom/hzbhd/canbus/car/_360/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frameData", "", "getFrameData", "()[I", "setFrameData", "([I)V", "lastD10", "", "getLastD10", "()I", "setLastD10", "(I)V", "lastD9", "getLastD9", "setLastD9", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/ui_set/DriverDataPageUiSet$Page$Item;", "Lkotlin/collections/HashMap;", "getMDriveItemIndexHashMap", "()Ljava/util/HashMap;", "setMDriveItemIndexHashMap", "(Ljava/util/HashMap;)V", "afterServiceNormalSetting", "", "canbusInfoChange", "canbusInfo", "", "carBodyStatus", "initItemsIndexHashMap", "steeringWheelKeys", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frameData;
   private int lastD10 = 255;
   private int lastD9 = 255;
   private HashMap mDriveItemIndexHashMap = new HashMap();

   private final void carBodyStatus() {
      this.updateSpeedInfo(this.getFrameData()[2]);
      int var6 = this.getFrameData()[2];
      int var4 = this.getFrameData()[3] * 256 + this.getFrameData()[4];
      int var5 = this.getFrameData()[5];
      int var1 = this.getFrameData()[6];
      int var2 = this.getFrameData()[7] * 256 + this.getFrameData()[8];
      int var3 = this.getFrameData()[9] * 256 + this.getFrameData()[10];
      int var8 = this.getFrameData()[11];
      int var9 = this.getFrameData()[12];
      boolean var11 = DataHandleUtils.getBoolBit7(this.getFrameData()[12]);
      boolean var16 = DataHandleUtils.getBoolBit6(this.getFrameData()[12]);
      boolean var15 = DataHandleUtils.getBoolBit5(this.getFrameData()[12]);
      boolean var10 = DataHandleUtils.getBoolBit4(this.getFrameData()[12]);
      boolean var13 = DataHandleUtils.getBoolBit3(this.getFrameData()[12]);
      boolean var12 = DataHandleUtils.getBoolBit2(this.getFrameData()[12]);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.getFrameData()[13], 0, 4);
      boolean var14 = DataHandleUtils.getBoolBit4(this.getFrameData()[13]);
      Object var17 = this.mDriveItemIndexHashMap.get("D360_d0");
      Intrinsics.checkNotNull(var17);
      ((DriverDataPageUiSet.Page.Item)var17).setValue(var6 + " Km/H");
      var17 = this.mDriveItemIndexHashMap.get("D360_d1t2");
      Intrinsics.checkNotNull(var17);
      DriverDataPageUiSet.Page.Item var18 = (DriverDataPageUiSet.Page.Item)var17;
      String var19;
      if (var4 != 65535) {
         var19 = var4 + " Km";
      } else {
         var19 = "----";
      }

      var18.setValue(var19);
      var17 = this.mDriveItemIndexHashMap.get("D360_d3t4");
      Intrinsics.checkNotNull(var17);
      ((DriverDataPageUiSet.Page.Item)var17).setValue(var5 * 256 + var1 + " Km/H");
      var17 = this.mDriveItemIndexHashMap.get("D360_d5t6");
      Intrinsics.checkNotNull(var17);
      var18 = (DriverDataPageUiSet.Page.Item)var17;
      if (var2 != 65535) {
         var19 = var2 / 10 + " L/100Km";
      } else {
         var19 = "----";
      }

      var18.setValue(var19);
      var17 = this.mDriveItemIndexHashMap.get("D360_d7t8");
      Intrinsics.checkNotNull(var17);
      var18 = (DriverDataPageUiSet.Page.Item)var17;
      if (var3 != 65535) {
         var19 = var3 / 10 + " L/100Km";
      } else {
         var19 = "----";
      }

      var18.setValue(var19);
      if (!carBodyStatus$isOutTempMsgRepeat(var8, this)) {
         this.updateOutDoorTemp(this.getContext(), (byte)var8 + " °C");
      }

      if (!carBodyStatus$isDoorMsgRepeat(var9, this)) {
         GeneralDoorData.isRightFrontDoorOpen = var11;
         GeneralDoorData.isLeftFrontDoorOpen = var16;
         GeneralDoorData.isRightRearDoorOpen = var15;
         GeneralDoorData.isLeftRearDoorOpen = var10;
         GeneralDoorData.isBackOpen = var13;
         GeneralDoorData.isFrontOpen = var12;
         this.updateDoorView(this.getContext());
      }

      var17 = this.mDriveItemIndexHashMap.get("D360_d11b0t3");
      Intrinsics.checkNotNull(var17);
      var18 = (DriverDataPageUiSet.Page.Item)var17;
      if (var7 != 1) {
         if (var7 != 2) {
            if (var7 != 3) {
               if (var7 != 4) {
                  var19 = "无效";
               } else {
                  var19 = "START";
               }
            } else {
               var19 = "ON";
            }
         } else {
            var19 = "ACC";
         }
      } else {
         var19 = "OFF";
      }

      var18.setValue(var19);
      var17 = this.mDriveItemIndexHashMap.get("D360_d11b4");
      Intrinsics.checkNotNull(var17);
      var18 = (DriverDataPageUiSet.Page.Item)var17;
      if (var14) {
         var19 = "行驶状态";
      } else {
         var19 = "静止状态";
      }

      var18.setValue(var19);
      this.updateDriveDataActivity((Bundle)null);
   }

   private static final boolean carBodyStatus$isDoorMsgRepeat(int var0, MsgMgr var1) {
      boolean var2;
      if (var0 == var1.lastD10) {
         var2 = true;
      } else {
         var1.lastD10 = var0;
         var2 = false;
      }

      return var2;
   }

   private static final boolean carBodyStatus$isOutTempMsgRepeat(int var0, MsgMgr var1) {
      boolean var2;
      if (var0 == var1.lastD9) {
         var2 = true;
      } else {
         var1.lastD9 = var0;
         var2 = false;
      }

      return var2;
   }

   private final void steeringWheelKeys() {
      int var2 = this.getFrameData()[3];
      int var1 = this.getFrameData()[2];
      if (var1 != 128) {
         switch (var1) {
            case 0:
               this.realKeyLongClick1(this.getContext(), 0, var2);
               break;
            case 1:
               this.realKeyLongClick1(this.getContext(), 7, var2);
               break;
            case 2:
               this.realKeyLongClick1(this.getContext(), 8, var2);
               break;
            case 3:
               this.realKeyLongClick1(this.getContext(), 45, var2);
               break;
            case 4:
               this.realKeyLongClick1(this.getContext(), 46, var2);
               break;
            case 5:
               this.realKeyLongClick1(this.getContext(), 2, var2);
               break;
            case 6:
               DataHandleUtils.knob(this.getContext(), 45, var2);
               break;
            case 7:
               DataHandleUtils.knob(this.getContext(), 46, var2);
         }
      } else {
         this.startDrivingDataActivity(this.getContext(), 0);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      this.initItemsIndexHashMap(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      if (var1 != null && var2 != null) {
         this.setContext(var1);
         int[] var4 = this.getByteArrayToIntArray(var2);
         Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
         this.setFrameData(var4);
         int var3 = this.getFrameData()[1];
         if (var3 != 1) {
            if (var3 == 32) {
               this.steeringWheelKeys();
            }
         } else {
            this.carBodyStatus();
         }
      }

   }

   public final Context getContext() {
      Context var1 = this.context;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("context");
         return null;
      }
   }

   public final int[] getFrameData() {
      int[] var1 = this.frameData;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("frameData");
         return null;
      }
   }

   public final int getLastD10() {
      return this.lastD10;
   }

   public final int getLastD9() {
      return this.lastD9;
   }

   public final HashMap getMDriveItemIndexHashMap() {
      return this.mDriveItemIndexHashMap;
   }

   public final void initItemsIndexHashMap(Context var1) {
      UiMgrInterface var4 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type com.hzbhd.canbus.car._360.UiMgr");
      List var10 = ((UiMgr)var4).getDriverDataPageUiSet(var1).getList();
      Iterator var5 = var10.iterator();

      for(int var2 = 0; var5.hasNext(); ++var2) {
         Iterator var9 = ((DriverDataPageUiSet.Page)var5.next()).getItemList().iterator();

         for(int var3 = 0; var9.hasNext(); ++var3) {
            DriverDataPageUiSet.Page.Item var7 = (DriverDataPageUiSet.Page.Item)var9.next();
            Map var6 = (Map)this.mDriveItemIndexHashMap;
            String var8 = var7.getTitleSrn();
            Intrinsics.checkNotNullExpressionValue(var8, "item.titleSrn");
            Object var11 = ((DriverDataPageUiSet.Page)var10.get(var2)).getItemList().get(var3);
            Intrinsics.checkNotNullExpressionValue(var11, "this[pageIndex].itemList[itemIndex]");
            var6.put(var8, var11);
         }
      }

   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrameData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frameData = var1;
   }

   public final void setLastD10(int var1) {
      this.lastD10 = var1;
   }

   public final void setLastD9(int var1) {
      this.lastD9 = var1;
   }

   public final void setMDriveItemIndexHashMap(HashMap var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mDriveItemIndexHashMap = var1;
   }
}
