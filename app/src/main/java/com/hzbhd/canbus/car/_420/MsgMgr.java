package com.hzbhd.canbus.car._420;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final boolean $assertionsDisabled = false;
   private Boolean HighPressure0001;
   private Boolean HighPressure0010;
   private Boolean HighPressure0100;
   private Boolean HighPressure1000;
   private Boolean LowPressure0001;
   private Boolean LowPressure0010;
   private Boolean LowPressure0100;
   private Boolean LowPressure1000;
   List MTire0;
   List MTire1;
   List MTire2;
   List MTire3;
   private Boolean SenseStatus0001;
   private Boolean SenseStatus0010;
   private Boolean SenseStatus0100;
   private Boolean SenseStatus1000;
   private String TirePressure0001 = "NO INFO";
   private String TirePressure0010 = "NO INFO";
   private String TirePressure0100 = "NO INFO";
   private String TirePressure1000 = "NO INFO";
   private String TireTempture0001 = "NO INFO";
   private String TireTempture0010 = "NO INFO";
   private String TireTempture0100 = "NO INFO";
   private String TireTempture1000 = "NO INFO";
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int mDifferentID;

   public MsgMgr() {
      Boolean var1 = false;
      this.SenseStatus1000 = var1;
      this.SenseStatus0100 = var1;
      this.SenseStatus0010 = var1;
      this.SenseStatus0001 = var1;
      this.LowPressure1000 = var1;
      this.LowPressure0100 = var1;
      this.LowPressure0010 = var1;
      this.LowPressure0001 = var1;
      this.HighPressure1000 = var1;
      this.HighPressure0100 = var1;
      this.HighPressure0010 = var1;
      this.HighPressure0001 = var1;
      this.MTire0 = new ArrayList();
      this.MTire1 = new ArrayList();
      this.MTire2 = new ArrayList();
      this.MTire3 = new ArrayList();
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, boolean var4, boolean var5, boolean var6) {
      byte var7;
      String var9;
      if (var4) {
         var9 = CommUtil.getStrByResId(this.mContext, "_420_item_2");
         var7 = 1;
      } else {
         var9 = CommUtil.getStrByResId(this.mContext, "_420_item_3");
         var7 = 0;
      }

      String var8;
      if (var5) {
         var8 = CommUtil.getStrByResId(this.mContext, "_420_item_4");
      } else {
         if (!var6) {
            var8 = CommUtil.getStrByResId(this.mContext, "no_warning_msg");
            return new TireUpdateEntity(var1, var7, new String[]{var2, var3, var9, var8});
         }

         var8 = CommUtil.getStrByResId(this.mContext, "_420_item_5");
      }

      var7 = 1;
      return new TireUpdateEntity(var1, var7, new String[]{var2, var3, var9, var8});
   }

   protected static List mergeList(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void setDoorInfo0x12() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setTireInfo() {
      GeneralTireData.isHaveSpareTire = false;
      this.MTire0.add(this.getTireEntity(0, this.TirePressure1000, this.TireTempture1000, this.SenseStatus1000, this.LowPressure1000, this.HighPressure1000));
      this.MTire1.add(this.getTireEntity(1, this.TirePressure0100, this.TireTempture0100, this.SenseStatus0100, this.LowPressure0100, this.HighPressure0100));
      this.MTire2.add(this.getTireEntity(2, this.TirePressure0010, this.TireTempture0010, this.SenseStatus0010, this.LowPressure0010, this.HighPressure0010));
      this.MTire3.add(this.getTireEntity(3, this.TirePressure0001, this.TireTempture0001, this.SenseStatus0001, this.LowPressure0001, this.HighPressure0001));
      GeneralTireData.dataList = mergeList(this.MTire0, this.MTire1, this.MTire2, this.MTire3);
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setTireInfo0x38() {
      this.TirePressure1000 = this.mCanBusInfoInt[6] + "kpa";
      this.TirePressure0100 = this.mCanBusInfoInt[7] + "kpa";
      this.TirePressure0010 = this.mCanBusInfoInt[8] + "kpa";
      this.TirePressure0001 = this.mCanBusInfoInt[9] + "kpa";
      this.TireTempture1000 = this.mCanBusInfoInt[2] + this.getTempUnitC(this.mContext);
      this.TireTempture0100 = this.mCanBusInfoInt[3] + this.getTempUnitC(this.mContext);
      this.TireTempture0010 = this.mCanBusInfoInt[4] + this.getTempUnitC(this.mContext);
      this.TireTempture0001 = this.mCanBusInfoInt[5] + this.getTempUnitC(this.mContext);
      this.setTireInfo();
   }

   private void setTireInfo0x39() {
      this.SenseStatus1000 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.SenseStatus0100 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.SenseStatus0010 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      this.SenseStatus0001 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      this.LowPressure1000 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.LowPressure0100 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      this.LowPressure0010 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      this.LowPressure0001 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      this.HighPressure1000 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.HighPressure0100 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.HighPressure0010 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      this.HighPressure0001 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      this.setTireInfo();
   }

   private void setTrackDate0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 8) {
                  if (var1 != 9) {
                     if (var1 == 12) {
                        this.realKeyClick0x11(2);
                     }
                  } else {
                     this.realKeyClick0x11(46);
                  }
               } else {
                  this.realKeyClick0x11(45);
               }
            } else {
               this.realKeyClick0x11(8);
            }
         } else {
            this.realKeyClick0x11(7);
         }
      } else {
         this.realKeyClick0x11(0);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mDifferentID = this.getCurrentCanDifferentId();
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 56) {
               if (var3 != 57) {
                  if (var3 == 240) {
                     this.setVersionInfo0xF0();
                  }
               } else {
                  this.setTireInfo0x39();
               }
            } else {
               this.setTireInfo0x38();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
         this.setTrackDate0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      int var2 = this.getCurrentCanDifferentId();
      this.mDifferentID = var2;
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, (byte)var2});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
   }
}
