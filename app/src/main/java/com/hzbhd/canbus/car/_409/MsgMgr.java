package com.hzbhd.canbus.car._409;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0015H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001a"},
   d2 = {"Lcom/hzbhd/canbus/car/_409/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "array", "", "getArray", "()[Z", "setArray", "([Z)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x1AData", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private boolean[] array;
   public int[] frame;

   public MsgMgr() {
      this.array = new boolean[]{GeneralDoorData.isLeftFrontDoorOpen, GeneralDoorData.isRightFrontDoorOpen, GeneralDoorData.isLeftRearDoorOpen, GeneralDoorData.isRightRearDoorOpen, GeneralDoorData.isBackOpen, GeneralDoorData.isFrontOpen};
   }

   private final void set0x11Data() {
      Context var3 = InitUtilsKt.getMContext();
      int var2 = this.getFrame()[4];
      short var1 = 8;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 != 5) {
                     if (var2 != 8) {
                        if (var2 != 9) {
                           if (var2 != 12) {
                              var1 = 0;
                           } else {
                              var1 = 2;
                           }
                        } else {
                           var1 = 20;
                        }
                     } else {
                        var1 = 21;
                     }
                  } else {
                     var1 = 188;
                  }
               } else {
                  var1 = 187;
               }
            } else {
               var1 = 3;
            }
         }
      } else {
         var1 = 7;
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[5]);
   }

   private final void set0x1AData() {
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[5], this.getFrame()[6]));
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[3]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
      boolean[] var1 = new boolean[]{DataHandleUtils.getBoolBit7(this.getFrame()[3]), DataHandleUtils.getBoolBit6(this.getFrame()[3]), DataHandleUtils.getBoolBit5(this.getFrame()[3]), DataHandleUtils.getBoolBit4(this.getFrame()[3]), DataHandleUtils.getBoolBit3(this.getFrame()[3]), DataHandleUtils.getBoolBit2(this.getFrame()[3])};
      if (!Arrays.equals(var1, this.array)) {
         this.updateDoorView(InitUtilsKt.getMContext());
         this.array = var1;
      }

      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
      if (var2 != null) {
         var2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[5], this.getFrame()[6])));
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
      if (var2 != null) {
         var2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[11], this.getFrame()[12])));
      }

      this.updateDriveDataActivity((Bundle)null);
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._409.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 1, 18});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 26) {
            if (var3 == 240) {
               this.set0xF0Data(var2);
            }
         } else {
            this.set0x1AData();
         }
      } else {
         this.set0x11Data();
      }

   }

   public final boolean[] getArray() {
      return this.array;
   }

   public final int[] getFrame() {
      int[] var1 = this.frame;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("frame");
         return null;
      }
   }

   public final void setArray(boolean[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.array = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
