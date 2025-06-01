package com.hzbhd.canbus.car._240;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.System;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0014\u0010\u001e\u001a\u00020\u00122\n\u0010\u001f\u001a\u00020\n\"\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"},
   d2 = {"Lcom/hzbhd/canbus/car/_240/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mCanBusInfoByte", "", "getMCanBusInfoByte", "()[B", "setMCanBusInfoByte", "([B)V", "mCanBusInfoInt", "", "getMCanBusInfoInt", "()[I", "setMCanBusInfoInt", "([I)V", "mContext", "Landroid/content/Context;", "canbusInfoChange", "", "context", "canbusInfo", "getIndexBy2Bit", "", "bit", "", "getIndexBy6Bit", "resolveLeftAndRightTemp", "", "value", "resolveLeftAndRightTempNum", "sendMediaSource", "b0tx", "setAirData0x23", "setCornerData", "setDoorData0x28", "setPanoramaData", "setSettingData0x40", "setVersionInfo", "setWheelKeyInfo", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public byte[] mCanBusInfoByte;
   public int[] mCanBusInfoInt;
   private Context mContext;

   private final int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private final int getIndexBy6Bit(int var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < 61; ++var2) {
         if (var1 == var2) {
            var3 = var1;
         }
      }

      return var3;
   }

   private final String resolveLeftAndRightTemp(int var1) {
      String var4;
      if (var1 == 0) {
         var4 = "LO";
      } else if (var1 == 30) {
         var4 = "HI";
      } else if (var1 == 255) {
         Context var5 = this.mContext;
         Intrinsics.checkNotNull(var5);
         var4 = var5.getString(2131769395);
         Intrinsics.checkNotNullExpressionValue(var4, "{\n                mConte…no_display)\n            }");
      } else {
         boolean var3 = false;
         boolean var2 = var3;
         if (1 <= var1) {
            var2 = var3;
            if (var1 < 30) {
               var2 = true;
            }
         }

         if (var2) {
            var4 = (float)(var1 + 35) * 0.5F + this.getTempUnitC(this.mContext);
         } else {
            var4 = "";
         }
      }

      return var4;
   }

   private final String resolveLeftAndRightTempNum(int var1) {
      boolean var3 = false;
      boolean var2 = var3;
      if (1 <= var1) {
         var2 = var3;
         if (var1 < 16) {
            var2 = true;
         }
      }

      String var4 = "";
      if (var2) {
         var4 = var1 + "";
      }

      return var4;
   }

   private final void sendMediaSource(int... var1) {
      Collection var4 = (Collection)(new ArrayList(var1.length));
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var4.add((byte)var1[var2]);
      }

      byte[] var5 = CollectionsKt.toByteArray((Collection)((List)var4));
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -60}, var5));
   }

   private final void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getMCanBusInfoInt()[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.getMCanBusInfoInt()[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getMCanBusInfoInt()[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.getMCanBusInfoInt()[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.getMCanBusInfoInt()[2]);
      GeneralAirData.front_wind_level = this.getMCanBusInfoInt()[4];
      int var1 = this.getMCanBusInfoInt()[3];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      if (this.getCurrentCanDifferentId() == 2) {
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.getMCanBusInfoInt()[6]);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.getMCanBusInfoInt()[6]);
      } else {
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTempNum(this.getMCanBusInfoInt()[5]);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTempNum(this.getMCanBusInfoInt()[5]);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private final void setCornerData() {
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.getMCanBusInfoByte()[3], this.getMCanBusInfoByte()[2], 7744, 2039, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void setDoorData0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getMCanBusInfoInt()[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getMCanBusInfoInt()[2]);
      this.updateDoorView(this.mContext);
   }

   private final void setPanoramaData() {
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[2], 7, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[2], 0, 4);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[3], 6, 1);
      ArrayList var5 = new ArrayList(8);

      for(int var1 = 0; var1 < 8; ++var1) {
         var5.add(new PanoramicBtnUpdateEntity(var1, false));
      }

      GeneralParkData.dataList = (List)var5;
      List var6 = GeneralParkData.dataList;
      if (var4 == 1) {
         var6.set(0, new PanoramicBtnUpdateEntity(0, true));
      } else {
         var6.set(1, new PanoramicBtnUpdateEntity(1, true));
      }

      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  var6.set(5, new PanoramicBtnUpdateEntity(5, true));
               }
            } else {
               var6.set(4, new PanoramicBtnUpdateEntity(4, true));
            }
         } else {
            var6.set(3, new PanoramicBtnUpdateEntity(3, true));
         }
      } else {
         var6.set(2, new PanoramicBtnUpdateEntity(2, true));
      }

      Object var7;
      if (var3 == 1) {
         var7 = var6.set(6, new PanoramicBtnUpdateEntity(6, true));
      } else {
         var7 = var6.set(7, new PanoramicBtnUpdateEntity(7, true));
      }

      PanoramicBtnUpdateEntity var8 = (PanoramicBtnUpdateEntity)var7;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void setSettingData0x40() {
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[2]));
      int var2 = this.getIndexBy6Bit(DataHandleUtils.getIntFromByteWithBit(this.getMCanBusInfoInt()[2], 0, 7));
      int var1 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.getMCanBusInfoInt()[3]));
      int var4 = this.getMCanBusInfoInt()[4];
      List var5 = (List)(new ArrayList());
      if (this.getCurrentCanDifferentId() == 3) {
         var5.add(new SettingUpdateEntity(0, 0, var3));
         var5.add(new SettingUpdateEntity(0, 1, "" + var2 + 's'));
         var5.add(new SettingUpdateEntity(0, 2, var4));
      } else {
         var5.add(new SettingUpdateEntity(0, 0, var3));
         var5.add(new SettingUpdateEntity(0, 1, "" + var2 + 's'));
         var5.add(new SettingUpdateEntity(0, 2, var1));
      }

      Context var6 = this.mContext;
      Intrinsics.checkNotNull(var6);
      System.putInt(var6.getContentResolver(), "d0b7", var3);
      var6 = this.mContext;
      Intrinsics.checkNotNull(var6);
      System.putInt(var6.getContentResolver(), "d0b0t6", var2);
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private final void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.getMCanBusInfoByte()));
   }

   private final void setWheelKeyInfo() {
      switch (this.getMCanBusInfoInt()[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, this.getMCanBusInfoInt()[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 7, this.getMCanBusInfoInt()[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 8, this.getMCanBusInfoInt()[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 21, this.getMCanBusInfoInt()[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 20, this.getMCanBusInfoInt()[3]);
         case 5:
         default:
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 3, this.getMCanBusInfoInt()[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 223, this.getMCanBusInfoInt()[3]);
            break;
         case 8:
            this.realKeyLongClick1(this.mContext, 14, this.getMCanBusInfoInt()[3]);
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 14, this.getMCanBusInfoInt()[3]);
            break;
         case 10:
            this.realKeyLongClick1(this.mContext, 15, this.getMCanBusInfoInt()[3]);
            break;
         case 11:
            this.realKeyLongClick1(this.mContext, 187, this.getMCanBusInfoInt()[3]);
            break;
         case 12:
            this.realKeyLongClick1(this.mContext, 50, this.getMCanBusInfoInt()[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 1, this.getMCanBusInfoInt()[3]);
            break;
         case 14:
            this.realKeyLongClick1(this.mContext, 52, this.getMCanBusInfoInt()[3]);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      this.setMCanBusInfoByte(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setMCanBusInfoInt(var4);
      this.mContext = var1;
      int var3 = this.getMCanBusInfoInt()[1];
      if (var3 != 33) {
         if (var3 != 35) {
            if (var3 != 64) {
               if (var3 != 80) {
                  if (var3 != 127) {
                     if (var3 != 40) {
                        if (var3 == 41) {
                           this.setCornerData();
                        }
                     } else {
                        if (this.isDoorMsgRepeat(var2)) {
                           return;
                        }

                        this.setDoorData0x28();
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.setPanoramaData();
               }
            } else if (this.getCurrentCanDifferentId() == 2 || this.getCurrentCanDifferentId() == 3) {
               this.setSettingData0x40();
            }
         } else {
            if (this.isAirMsgRepeat(var2)) {
               return;
            }

            this.setAirData0x23();
         }
      } else {
         this.setWheelKeyInfo();
      }

   }

   public final byte[] getMCanBusInfoByte() {
      byte[] var1 = this.mCanBusInfoByte;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
         return null;
      }
   }

   public final int[] getMCanBusInfoInt() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
         return null;
      }
   }

   public final void setMCanBusInfoByte(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mCanBusInfoByte = var1;
   }

   public final void setMCanBusInfoInt(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mCanBusInfoInt = var1;
   }
}
