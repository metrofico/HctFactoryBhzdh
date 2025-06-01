package com.hzbhd.canbus.car._226;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private static int mAmb;
   private static int mOutDoorTemp;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private boolean isOnlyOutDoorDataChange() {
      if (SharePreUtil.getFloatValue(this.mContext, "_226_out_door_temp", 0.0F) != (float)mOutDoorTemp) {
         return true;
      } else {
         return SharePreUtil.getIntValue(this.mContext, "_226_amb", 0) != mAmb;
      }
   }

   private int pinJie2ByteToInt(int var1, int var2) {
      return var1 << 8 | var2;
   }

   private String resolveLeftTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (127 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (31 <= var1 && 63 >= var1) {
         var2 = (double)(var1 - 31) * 0.5 + 15.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void resolveOutDoorTem(byte[] var1) {
      int[] var3 = this.getByteArrayToIntArray(var1);
      mOutDoorTemp = DataHandleUtils.getIntFromByteWithBit(var3[6], 0, 7);
      String var2 = DataHandleUtils.getIntFromByteWithBit(var3[6], 0, 7) + this.getTempUnitC(this.mContext);
      String var4 = var2;
      if (DataHandleUtils.getBoolBit7(var3[6])) {
         var4 = "-" + var2;
      }

      this.updateOutDoorTemp(this.mContext, var4);
   }

   private String resolveRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (127 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (31 <= var1 && 63 >= var1) {
         var2 = (double)(var1 - 31) * 0.5 + 15.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void set0x01WheelKeyInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        switch (var1) {
                           case 19:
                              this.realKeyLongClick1(this.mContext, 3, var2[3]);
                              break;
                           case 20:
                              this.realKeyLongClick1(this.mContext, 14, var2[3]);
                              break;
                           case 21:
                              this.realKeyLongClick1(this.mContext, 15, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 2, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 21, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 20, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setVehicleInfo0x7d() {
      ArrayList var5 = new ArrayList();
      String var2 = this.mContext.getString(2131770199);
      String var3;
      if (this.mCanBusInfoInt[2] == 255) {
         var3 = var2;
      } else {
         var3 = this.mCanBusInfoInt[2] + "KM/H";
      }

      var5.add(new DriverUpdateEntity(0, 0, var3));
      int[] var6 = this.mCanBusInfoInt;
      int var1 = this.pinJie2ByteToInt(var6[3], var6[4]);
      String var4 = "----";
      if (var1 == 65535) {
         var3 = var2;
      } else if (var1 == 65534) {
         var3 = "----";
      } else {
         var3 = (new DecimalFormat("0.0")).format((double)var1 * 0.1);
         var3 = var3 + "L/100KM";
      }

      var5.add(new DriverUpdateEntity(0, 1, var3));
      var6 = this.mCanBusInfoInt;
      var1 = this.pinJie2ByteToInt(var6[5], var6[6]);
      if (var1 == 65535) {
         var3 = var2;
      } else if (var1 == 65534) {
         var3 = var4;
      } else {
         var3 = (new DecimalFormat("0.0")).format((double)var1 * 0.1);
         var3 = var3 + "L/100KM";
      }

      var5.add(new DriverUpdateEntity(0, 2, var3));
      var6 = this.mCanBusInfoInt;
      var1 = this.pinJie2ByteToInt(var6[7], var6[8]);
      if (var1 == 65535) {
         var3 = var2;
      } else {
         var3 = var1 + "KM";
      }

      var5.add(new DriverUpdateEntity(0, 3, var3));
      if (this.mCanBusInfoInt[9] == 255) {
         var3 = var2;
      } else {
         var3 = this.mCanBusInfoInt[9] + "KM/H";
      }

      var5.add(new DriverUpdateEntity(0, 4, var3));
      var6 = this.mCanBusInfoInt;
      if (var6[10] * 256 + var6[11] == 65535) {
         var3 = var2;
      } else {
         StringBuilder var7 = new StringBuilder();
         var6 = this.mCanBusInfoInt;
         var3 = var7.append(var6[10] * 256 + var6[11]).append("rpm").toString();
      }

      var5.add(new DriverUpdateEntity(0, 5, var3));
      if (this.mCanBusInfoInt[12] == 255) {
         var3 = var2;
      } else {
         var3 = (float)((double)this.mCanBusInfoInt[12] * 0.1) + "V";
      }

      var5.add(new DriverUpdateEntity(0, 6, var3));
      if (this.mCanBusInfoInt[13] != 255) {
         var2 = this.mCanBusInfoInt[13] - 40 + "℃";
      }

      var5.add(new DriverUpdateEntity(0, 7, var2));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14])) {
         var2 = String.format(this.mContext.getResources().getString(2131759528), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 7));
      } else {
         var2 = this.mContext.getResources().getString(2131759529);
      }

      var5.add(new DriverUpdateEntity(0, 8, var2));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void updateAirInfoPanel() {
      if (this.getCurrentCanDifferentId() == 0) {
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.eco = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_temperature = this.resolveLeftTemp(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveRightTemp(this.mCanBusInfoInt[5]);
      } else {
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      }

      mAmb = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            this.resolveOutDoorTem(this.mCanBusInfoByte);
         } else {
            this.updateOutDoorTemp(this.mContext, "  ");
         }
      } else {
         this.resolveOutDoorTem(this.mCanBusInfoByte);
      }

      if (this.isOnlyOutDoorDataChange()) {
         SharePreUtil.setFloatValue(this.mContext, "_226_out_door_temp", (float)mOutDoorTemp);
         SharePreUtil.setIntValue(this.mContext, "_226_amb", mAmb);
         Log.d("cwh", "室外温度");
         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            this.resolveOutDoorTem(this.mCanBusInfoByte);
         } else {
            this.updateOutDoorTemp(this.mContext, "  ");
         }
      } else {
         Log.d("cwh", "空调");
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 125) {
               if (var3 == 127) {
                  this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
               }
            } else {
               this.setVehicleInfo0x7d();
            }
         } else {
            if (this.isAirMsgRepeat(var2)) {
               return;
            }

            this.updateAirInfoPanel();
         }
      } else {
         this.set0x01WheelKeyInfo();
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = "DTV".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var12 = "Video  " + var6 + ":" + (new DecimalFormat("00")).format((long)var7);
      Context var18 = this.mContext;
      var8 = SourceConstantsDef.SOURCE_ID.VIDEO.toString();
      byte[] var19 = var12.getBytes();
      this.sendMediaMsg(var18, var8, DataHandleUtils.byteMerger(new byte[]{22, -125}, var19));
      Log.d("cwh", "video");
   }
}
