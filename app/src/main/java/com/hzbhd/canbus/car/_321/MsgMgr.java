package com.hzbhd.canbus.car._321;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
   private int mKeyStatus;
   private byte[] mRadarData;
   private byte[] mTrackData;

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 31) {
         return "HI";
      } else if (var1 >= 1 && var1 <= 17) {
         float var2 = (float)(var1 + 35);
         return this.mDecimalFormat0p0.format((double)(var2 / 2.0F)) + this.getTempUnitC(this.mContext);
      } else {
         return "";
      }
   }

   private void set0x20WheelKey(Context var1) {
      int var2 = this.mKeyStatus;
      int var3 = this.mCanBusInfoInt[2];
      if (var2 != var3) {
         this.mKeyStatus = var3;
      }

      switch (var3) {
         case 0:
            this.realKeyLongClick1(var1, 0);
            break;
         case 1:
            this.realKeyLongClick1(var1, 7);
            break;
         case 2:
            this.realKeyLongClick1(var1, 8);
            break;
         case 3:
            this.realKeyLongClick1(var1, 45);
            break;
         case 4:
            this.realKeyLongClick1(var1, 46);
            break;
         case 5:
            this.realKeyLongClick1(var1, 14);
            break;
         case 6:
            this.realKeyLongClick1(var1, 3);
            break;
         case 7:
            this.realKeyLongClick1(var1, 2);
      }

   }

   private void set0x21AirData(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      var2[3] = (byte)(var2[3] & 239);
      if (!this.isAirMsgRepeat(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto_wind_strong = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
         this.updateAirActivity(var1, 1001);
      }
   }

   private void set0x22RearRadarData(Context var1) {
      if (this.is0x22DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(10, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x23FrontRadarData(Context var1) {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(10, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      switch (var3[1]) {
         case 32:
            this.set0x20WheelKey(var1);
            break;
         case 33:
            this.set0x21AirData(var1);
            break;
         case 34:
            this.set0x22RearRadarData(var1);
            break;
         case 35:
            this.set0x23FrontRadarData(var1);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }
}
