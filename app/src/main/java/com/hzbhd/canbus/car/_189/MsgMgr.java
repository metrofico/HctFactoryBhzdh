package com.hzbhd.canbus.car._189;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private int eachId;
   private byte freqHi;
   private byte freqLo;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private String mSongAlbum;
   private String mSongArtist;
   private String mSongTitle;

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private String getCloseOpenStr(int var1) {
      if (var1 == 0) {
         return "close";
      } else {
         return var1 == 1 ? "open" : "set_default";
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy3Bit(int var1) {
      byte var3 = 0;
      byte var2 = var3;
      if (var1 != 11) {
         if (var1 != 27) {
            if (var1 != 267) {
               var2 = var3;
            } else {
               var2 = 2;
            }
         } else {
            var2 = 1;
         }
      }

      return var2;
   }

   private String getMessage(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? this.mContext.getString(2131769909) : this.mContext.getString(2131758679);
      } else {
         return this.mContext.getString(2131758678);
      }
   }

   private void getSettingItem() {
      ((UiMgr)UiMgrFactory.getCanUiMgr(this.mContext)).updateUiByDifferentCar(this.mContext);
   }

   private String getTemperature(int var1) {
      return var1 - 50 + this.mContext.getString(2131770016);
   }

   private TireUpdateEntity getTireEntity(int var1, String[] var2, String var3, String var4) {
      String var7 = CommUtil.getStrByResId(this.mContext, "_189_status_1");
      int var5 = 0;

      byte var6;
      for(var6 = 0; var5 < var2.length; ++var5) {
         if (!var2[var5].equals(var7)) {
            Log.d("lai", "getTireEntity: ");
            var6 = 1;
         }
      }

      return new TireUpdateEntity(var1, var6, new String[]{var3, var4, var2[0], var2[1], var2[2], var2[3]});
   }

   private String getTirePressure(int var1) {
      double var2 = Math.floor((double)var1 * 1.37);
      return var2 + this.mContext.getString(2131769654);
   }

   private String[] getTisWarmMsg(int var1, int var2, boolean var3) {
      String[] var4 = new String[]{"", "", "", ""};
      if (var2 == 1) {
         if (DataHandleUtils.getIntFromByteWithBit(var1, 6, 2) == 0) {
            var4[0] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
         } else if (DataHandleUtils.getIntFromByteWithBit(var1, 6, 2) == 1) {
            var4[0] = CommUtil.getStrByResId(this.mContext, "_189_status_2");
         } else {
            var4[0] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
         }

         if (DataHandleUtils.getIntFromByteWithBit(var1, 5, 1) == 1) {
            var4[1] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
         } else {
            var4[1] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
         }

         if (DataHandleUtils.getIntFromByteWithBit(var1, 4, 1) == 1) {
            var4[2] = CommUtil.getStrByResId(this.mContext, "_189_status_4");
         } else {
            var4[2] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
         }
      } else {
         if (DataHandleUtils.getIntFromByteWithBit(var1, 2, 2) == 0) {
            var4[0] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
         } else if (DataHandleUtils.getIntFromByteWithBit(var1, 6, 2) == 1) {
            var4[0] = CommUtil.getStrByResId(this.mContext, "_189_status_2");
         } else {
            var4[0] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
         }

         if (DataHandleUtils.getIntFromByteWithBit(var1, 1, 1) == 1) {
            var4[1] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
         } else {
            var4[1] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
         }

         if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 1) == 1) {
            var4[2] = CommUtil.getStrByResId(this.mContext, "_189_status_4");
         } else {
            var4[2] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
         }
      }

      if (var3) {
         var4[3] = CommUtil.getStrByResId(this.mContext, "_189_Fast_leak");
      } else {
         var4[3] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
      }

      return var4;
   }

   private boolean isCanPlay() {
      return this.getCurrentCanDifferentId() == 7 || this.getCurrentCanDifferentId() == 9 || this.getCurrentCanDifferentId() == 10 || this.getCurrentCanDifferentId() == 15 || this.getCurrentCanDifferentId() == 22 || this.getCurrentCanDifferentId() == 23 || this.getCurrentCanDifferentId() == 25;
   }

   private boolean isPhoneState() {
      return this.getCurrentCanDifferentId() == 7 || this.getCurrentCanDifferentId() == 15 || this.getCurrentCanDifferentId() == 22 || this.getCurrentCanDifferentId() == 23 || this.getCurrentCanDifferentId() == 25;
   }

   private boolean isSend() {
      return this.getCurrentCanDifferentId() == 15 || this.getCurrentCanDifferentId() == 2 || this.getCurrentCanDifferentId() == 23 || this.getCurrentCanDifferentId() == 25;
   }

   private void realKeyClick(int var1) {
      this.realKeyClick1(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private String resolveCenterWheelValue(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131768910) + this.mContext.getResources().getString(2131768042);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131768910) + " Lv1";
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131768910) + " Lv2";
      } else {
         var2 = this.mContext.getResources().getString(2131768910) + " Lv3";
      }

      return var2;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (127 == var1) {
         var2 = "HI";
      } else if (1 <= var1 && var1 <= 30) {
         var2 = (double)var1 * 0.5 + 17.0 + this.getTempUnitC(this.mContext);
      } else if (32 <= var1 && var1 <= 34) {
         if (var1 == 32) {
            var2 = 16 + this.getTempUnitC(this.mContext);
         } else if (var1 == 33) {
            var2 = 16.5 + this.getTempUnitC(this.mContext);
         } else {
            var2 = 17 + this.getTempUnitC(this.mContext);
         }
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveLeftAndRightTempGe(int var1) {
      String var2;
      if (1 <= var1 && var1 <= 17) {
         var2 = var1 + "格";
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      int var1 = var2;
      if (var2 >= 127) {
         var1 = 127;
      }

      String var3;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var3 = "-" + var1;
      } else {
         var3 = var1 + "";
      }

      return var3 + this.getTempUnitC(this.mContext);
   }

   private int resultRadar(int var1) {
      switch (var1) {
         case 1:
         case 2:
            return 4;
         case 3:
         case 4:
            return 3;
         case 5:
         case 6:
            return 2;
         case 7:
            return 1;
         default:
            return 0;
      }
   }

   private void sendMusic(String var1, String var2, String var3) {
      if (this.isSend()) {
         this.mSongTitle = var1;
         this.mSongAlbum = var2;
         this.mSongArtist = var3;

         try {
            byte[] var5 = DataHandleUtils.exceptBOMHead(var1.getBytes("UnicodeLittle"));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 17}, var5));
            var5 = DataHandleUtils.exceptBOMHead(var2.getBytes("UnicodeLittle"));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 113, 17}, var5));
            var5 = DataHandleUtils.exceptBOMHead(var3.getBytes("UnicodeLittle"));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 114, 17}, var5));
         } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
         }
      }

   }

   private boolean sendMusicFirst(String var1, String var2, String var3) {
      return TextUtils.isEmpty(var1) || TextUtils.isEmpty(var2) || TextUtils.isEmpty(var3) || TextUtils.isEmpty(this.mSongTitle) || TextUtils.isEmpty(this.mSongAlbum) || TextUtils.isEmpty(this.mSongArtist) || !this.mSongTitle.equals(var1) || !this.mSongAlbum.equals(var2) || !this.mSongArtist.equals(var3);
   }

   private void sendPhone(byte[] var1) {
      if (this.isSend()) {
         String var3 = new String(var1);

         try {
            var1 = DataHandleUtils.exceptBOMHead(var3.getBytes("UnicodeLittle"));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -54, 3, 17}, var1));
         } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
         }
      }

   }

   private void set0x7D() {
      if (this.mCanBusInfoInt[2] == 8) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[4], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setAirData0x22() {
      GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      int var1 = this.mCanBusInfoInt[3];
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

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[7]);
      GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      GeneralAirData.center_wheel = this.resolveCenterWheelValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2));
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2);
      int[] var2 = this.mCanBusInfoInt;
      switch (this.getPmValue(var2[9] * 256 + var2[10])) {
         case 1:
            GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(2131769637);
            break;
         case 2:
            GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(2131769638);
            break;
         case 3:
            GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(2131769641);
            break;
         case 4:
            GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(2131769642);
            break;
         case 5:
            GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(2131769639);
            break;
         case 6:
            GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(2131769644);
      }

      var2 = this.mCanBusInfoInt;
      switch (this.getPmValue(var2[11] * 256 + var2[12])) {
         case 1:
            GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(2131769637);
            break;
         case 2:
            GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(2131769638);
            break;
         case 3:
            GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(2131769641);
            break;
         case 4:
            GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(2131769642);
            break;
         case 5:
            GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(2131769639);
            break;
         case 6:
            GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(2131769644);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void setBacklight0x14() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 >= 0 && var1 < 51) {
         this.setBacklightLevel(1);
      } else if (var1 >= 51 && var1 < 102) {
         this.setBacklightLevel(2);
      } else if (var1 >= 102 && var1 < 153) {
         this.setBacklightLevel(3);
      } else if (var1 >= 153 && var1 < 204) {
         this.setBacklightLevel(4);
      } else if (var1 >= 204 && var1 < 255) {
         this.setBacklightLevel(5);
      }

   }

   private void setChargingState0x60() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.getMessage(this.mCanBusInfoInt[2])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadarInfo0x26() {
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.setFrontRadarLocationData(7, this.resultRadar(this.mCanBusInfoInt[2]), this.resultRadar(this.mCanBusInfoInt[3]), this.resultRadar(this.mCanBusInfoInt[4]), this.resultRadar(this.mCanBusInfoInt[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setMaintenance0x53() {
      ArrayList var3 = new ArrayList();
      StringBuilder var2 = (new StringBuilder()).append(this.mContext.getString(2131758683));
      int[] var1 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(0, 0, var2.append(var1[2] * 256 + var1[3]).append(this.mContext.getString(2131758684)).toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOutDoorTem0x28() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setPanoramic0x40() {
      int var1 = this.mCanBusInfoInt[2];
      Context var12 = this.mContext;
      boolean var11;
      if (var1 == 1) {
         var11 = true;
      } else {
         var11 = false;
      }

      this.forceReverse(var12, var11);
      DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      int[] var15 = this.mCanBusInfoInt;
      int var3 = var15[3];
      int var5 = DataHandleUtils.getIntFromByteWithBit(var15[4], 7, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
      ArrayList var16 = new ArrayList();
      byte var13;
      if (this.getCurrentCanDifferentId() == 7) {
         var16.add(new SettingUpdateEntity(1, 0, 0));
         if (var3 == 3) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 1, Integer.valueOf(var13)));
         if (var3 == 4) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 2, Integer.valueOf(var13)));
         if (var3 == 1) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 3, Integer.valueOf(var13)));
         if (var3 == 2) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 4, Integer.valueOf(var13)));
         var16.add(new SettingUpdateEntity(1, 5, var7));
         var16.add(new SettingUpdateEntity(1, 6, var10));
         var16.add(new SettingUpdateEntity(1, 7, var8));
         var16.add(new SettingUpdateEntity(1, 8, var6));
         var16.add(new SettingUpdateEntity(1, 9, var2));
         var16.add(new SettingUpdateEntity(1, 10, var9));
      } else if (this.getCurrentCanDifferentId() == 10) {
         if (var3 == 3) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         byte var14 = 0;
         var16.add(new SettingUpdateEntity(1, 0, Integer.valueOf(var13)));
         if (var3 == 4) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 1, Integer.valueOf(var13)));
         if (var3 == 1) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 2, Integer.valueOf(var13)));
         if (var3 == 2) {
            var13 = 1;
         } else {
            var13 = 0;
         }

         var16.add(new SettingUpdateEntity(1, 3, Integer.valueOf(var13)));
         var13 = var14;
         if (var3 == 5) {
            var13 = 1;
         }

         var16.add(new SettingUpdateEntity(1, 4, Integer.valueOf(var13)));
         var16.add(new SettingUpdateEntity(1, 5, var5));
         var16.add(new SettingUpdateEntity(1, 6, var4));
      }

      this.updateGeneralSettingData(var16);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadarInfo0x25() {
      RadarInfoUtil.mMinIsClose = false;
      RadarInfoUtil.setRearRadarLocationData(7, this.resultRadar(this.mCanBusInfoInt[2]), this.resultRadar(this.mCanBusInfoInt[3]), this.resultRadar(this.mCanBusInfoInt[4]), this.resultRadar(this.mCanBusInfoInt[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingData0x27() {
      int var1 = this.mCanBusInfoInt[2];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(2, 13, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x4E() {
      ArrayList var3 = new ArrayList();
      int var1 = this.eachId;
      if (var1 != 20) {
         int var2;
         int[] var4;
         switch (var1) {
            case 23:
               if (this.mCanBusInfoInt[2] == 8) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 19) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 20) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 40) {
                  var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 41) {
                  var3.add((new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
               }
               break;
            case 24:
               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 28) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 30) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 36) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               var4 = this.mCanBusInfoInt;
               if (var4[2] == 39) {
                  var2 = var4[3];
                  var1 = var2;
                  if (var2 == 255) {
                     var1 = 3;
                  }

                  var3.add(new SettingUpdateEntity(0, 4, var1));
               }

               if (this.mCanBusInfoInt[2] == 42) {
                  var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
               }
               break;
            case 25:
               if (this.mCanBusInfoInt[2] == 8) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 9) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 10) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 28) {
                  var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 31) {
                  var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 32) {
                  var3.add(new SettingUpdateEntity(0, 6, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 41) {
                  var3.add(new SettingUpdateEntity(0, 7, this.mCanBusInfoInt[3]));
               }
               break;
            case 26:
               if (this.mCanBusInfoInt[2] == 8) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 10) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 20) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 28) {
                  var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 33) {
                  var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
               }

               var4 = this.mCanBusInfoInt;
               if (var4[2] == 39) {
                  var2 = var4[3];
                  var1 = var2;
                  if (var2 == 255) {
                     var1 = 3;
                  }

                  var3.add(new SettingUpdateEntity(0, 6, var1));
               }

               if (this.mCanBusInfoInt[2] == 43) {
                  var3.add(new SettingUpdateEntity(0, 7, this.mCanBusInfoInt[3]));
               }
               break;
            case 27:
               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 27) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }
               break;
            case 28:
               if (this.mCanBusInfoInt[2] == 8) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 28) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               var4 = this.mCanBusInfoInt;
               if (var4[2] == 39) {
                  var2 = var4[3];
                  var1 = var2;
                  if (var2 == 255) {
                     var1 = 3;
                  }

                  var3.add(new SettingUpdateEntity(0, 6, var1));
               }

               if (this.mCanBusInfoInt[2] == 43) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 44) {
                  var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 45) {
                  var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
               }
               break;
            case 29:
               if (this.mCanBusInfoInt[2] == 10) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 20) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 30) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 33) {
                  var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 46) {
                  var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
               }
               break;
            case 30:
               if (this.mCanBusInfoInt[2] == 8) {
                  var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 17) {
                  var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 39) {
                  var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 43) {
                  var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 47) {
                  var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
               }

               if (this.mCanBusInfoInt[2] == 48) {
                  var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
               }
         }
      } else {
         if (this.mCanBusInfoInt[2] == 0) {
            var3.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3]));
         }

         if (this.mCanBusInfoInt[2] == 1) {
            var3.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[3]));
         }

         if (this.mCanBusInfoInt[2] == 3) {
            var3.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[3]));
         }

         if (this.mCanBusInfoInt[2] == 8) {
            var3.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
         }

         if (this.mCanBusInfoInt[2] == 33) {
            var3.add(new SettingUpdateEntity(0, 4, this.mCanBusInfoInt[3]));
         }

         if (this.mCanBusInfoInt[2] == 40) {
            var3.add(new SettingUpdateEntity(0, 5, this.mCanBusInfoInt[3]));
         }
      }

      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x4F() {
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
      int var1 = this.mCanBusInfoInt[4];
      ArrayList var15 = new ArrayList();
      var15.add(new SettingUpdateEntity(0, 0, var12));
      var15.add(new SettingUpdateEntity(0, 1, var8));
      var15.add(new SettingUpdateEntity(0, 2, var7));
      var15.add(new SettingUpdateEntity(0, 3, var6));
      var15.add(new SettingUpdateEntity(0, 4, var13));
      var15.add(new SettingUpdateEntity(0, 5, var14));
      var15.add(new SettingUpdateEntity(0, 6, var4));
      var15.add(new SettingUpdateEntity(0, 7, var2));
      var15.add(new SettingUpdateEntity(0, 8, var3));
      var15.add(new SettingUpdateEntity(0, 9, var11));
      var15.add(new SettingUpdateEntity(0, 10, var9));
      var15.add(new SettingUpdateEntity(0, 11, var5));
      var15.add(new SettingUpdateEntity(0, 12, var10));
      if (var1 >= 40 && var1 <= 80) {
         var15.add((new SettingUpdateEntity(0, 13, var1)).setProgress(var1 - 20));
      }

      this.updateGeneralSettingData(var15);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x50() {
      int var20 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      int var37 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
      int var21 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
      int var32 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
      int var18 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var40 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var25 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
      int var34 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      int var24 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
      int var39 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
      int var27 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]));
      int var19 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]));
      int var15 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
      int var36 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]));
      int var29 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]));
      int var35 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]));
      int var13 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]));
      int var33 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]));
      int var30 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]));
      int var22 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]));
      int var38 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]));
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
      int var1 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]));
      int var28 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]));
      int var41 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]));
      int var23 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]));
      int var17 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]));
      int var16 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]));
      int var26 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8]));
      int var31 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]));
      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]));
      ArrayList var42 = new ArrayList();
      switch (this.eachId) {
         case 5:
            var42.add(new SettingUpdateEntity(0, 0, var20));
            var42.add(new SettingUpdateEntity(0, 1, var37));
            var42.add(new SettingUpdateEntity(0, 2, var21));
            var42.add(new SettingUpdateEntity(0, 3, var3));
            var42.add(new SettingUpdateEntity(0, 4, var32));
         case 6:
         case 9:
         case 15:
         case 16:
         case 20:
         case 21:
         default:
            break;
         case 7:
            var42.add(new SettingUpdateEntity(0, 0, var18));
            var42.add(new SettingUpdateEntity(0, 1, var40));
            break;
         case 8:
            var42.add(new SettingUpdateEntity(0, 0, var25));
            var42.add(new SettingUpdateEntity(0, 1, var11));
            var42.add(new SettingUpdateEntity(0, 2, var34));
            var42.add(new SettingUpdateEntity(0, 3, var24));
            var42.add(new SettingUpdateEntity(0, 4, var39));
            var42.add(new SettingUpdateEntity(0, 5, var27));
         case 11:
            var42.add(new SettingUpdateEntity(0, 6, var30));
            var42.add(new SettingUpdateEntity(0, 7, var10));
            var42.add(new SettingUpdateEntity(0, 8, var22));
            var42.add(new SettingUpdateEntity(0, 9, var38));
            var42.add(new SettingUpdateEntity(0, 10, var8));
            var42.add(new SettingUpdateEntity(0, 11, var1));
            var42.add(new SettingUpdateEntity(0, 12, var28));
            break;
         case 10:
            var42.add(new SettingUpdateEntity(0, 0, var36));
            var42.add(new SettingUpdateEntity(0, 1, var29));
            var42.add(new SettingUpdateEntity(0, 2, var2));
            var42.add(new SettingUpdateEntity(0, 3, var35));
            var42.add(new SettingUpdateEntity(0, 4, var13));
            var42.add(new SettingUpdateEntity(0, 5, var33));
            break;
         case 12:
         case 13:
            var42.add(new SettingUpdateEntity(0, 0, var9));
            break;
         case 14:
         case 17:
         case 18:
            var42.add(new SettingUpdateEntity(0, 0, var41));
            var42.add(new SettingUpdateEntity(0, 1, var23));
            var42.add(new SettingUpdateEntity(0, 2, var17));
            var42.add(new SettingUpdateEntity(0, 3, var16));
            var42.add(new SettingUpdateEntity(0, 4, var26));
            var42.add(new SettingUpdateEntity(0, 5, var31));
            var42.add(new SettingUpdateEntity(0, 6, var5));
            var42.add(new SettingUpdateEntity(0, 7, var12));
            break;
         case 19:
            var42.add(new SettingUpdateEntity(0, 0, var9));
            var42.add(new SettingUpdateEntity(0, 1, var6));
            var42.add(new SettingUpdateEntity(0, 2, var4));
            var42.add(new SettingUpdateEntity(0, 3, var15));
            break;
         case 22:
            var42.add(new SettingUpdateEntity(0, 0, var9));
            var42.add(new SettingUpdateEntity(0, 0, var14));
            var42.add(new SettingUpdateEntity(0, 0, var19));
            var42.add(new SettingUpdateEntity(0, 0, var7));
            var42.add(new SettingUpdateEntity(0, 1, var6));
            var42.add(new SettingUpdateEntity(0, 2, var4));
            var42.add(new SettingUpdateEntity(0, 3, var15));
      }

      this.updateGeneralSettingData(var42);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSteeringWheelControls() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     switch (var1) {
                        case 6:
                           this.realKeyClick(3);
                           break;
                        case 7:
                           this.realKeyClick(2);
                           break;
                        case 8:
                           this.realKeyClick(68);
                           break;
                        case 9:
                           this.realKeyClick(14);
                           break;
                        case 10:
                           this.realKeyClick(15);
                           break;
                        case 11:
                           this.realKeyClick(45);
                           break;
                        case 12:
                           this.realKeyClick(46);
                           break;
                        case 13:
                           this.realKeyClick(52);
                           break;
                        case 14:
                           this.realKeyClick(49);
                           break;
                        case 15:
                           this.realKeyClick(200);
                           break;
                        case 16:
                           this.realKeyClick(187);
                           break;
                        default:
                           switch (var1) {
                              case 174:
                                 GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131770002);
                                 this.sendDisplayMsgView(this.mContext);
                                 break;
                              case 175:
                                 GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131770003);
                                 this.sendDisplayMsgView(this.mContext);
                                 break;
                              case 176:
                                 var1 = this.getCurrentCanDifferentId();
                                 if (var1 == 4 || var1 == 6 || var1 == 7 || var1 == 9 || var1 == 10 || var1 == 11 || var1 == 12 || var1 == 13 || var1 == 15 || var1 == 16 || var1 == 17 || var1 == 18 || var1 == 19 || var1 == 21 || var1 == 22 || var1 == 23 || var1 == 24 || var1 == 25) {
                                    Intent var2 = new Intent(this.mContext, SettingActivity.class);
                                    var2.addFlags(268435456);
                                    var2.setAction("setting_open_target_page");
                                    var2.putExtra("left_index ", 0);
                                    var2.putExtra("right_index", 13);
                                    this.mContext.startActivity(var2);
                                 }
                           }
                     }
                  } else {
                     this.realKeyClick(21);
                  }
               } else {
                  this.realKeyClick(20);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setTireData0x52() {
      ArrayList var1 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.getTireEntity(0, this.getTisWarmMsg(var2[2], 1, DataHandleUtils.getBoolBit7(var2[12])), this.getTirePressure(this.mCanBusInfoInt[4]), this.getTemperature(this.mCanBusInfoInt[8])));
      var2 = this.mCanBusInfoInt;
      var1.add(this.getTireEntity(1, this.getTisWarmMsg(var2[2], 0, DataHandleUtils.getBoolBit6(var2[12])), this.getTirePressure(this.mCanBusInfoInt[5]), this.getTemperature(this.mCanBusInfoInt[9])));
      var2 = this.mCanBusInfoInt;
      var1.add(this.getTireEntity(2, this.getTisWarmMsg(var2[3], 1, DataHandleUtils.getBoolBit5(var2[12])), this.getTirePressure(this.mCanBusInfoInt[6]), this.getTemperature(this.mCanBusInfoInt[10])));
      var2 = this.mCanBusInfoInt;
      var1.add(this.getTireEntity(3, this.getTisWarmMsg(var2[3], 0, DataHandleUtils.getBoolBit4(var2[12])), this.getTirePressure(this.mCanBusInfoInt[7]), this.getTemperature(this.mCanBusInfoInt[11])));
      GeneralTireData.dataList = var1;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setTrack0x30() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 5445, 16);
      MyLog.temporaryTracking("ZJ " + GeneralParkData.trackAngle);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setturnlightInfo0x3f() {
      this.switchFCamera(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public void atvDestdroy() {
      super.atvDestdroy();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
      }

   }

   public void atvInfoChange() {
      super.atvInfoChange();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 12, 0});
      }

   }

   public void auxInDestdroy() {
      super.auxInDestdroy();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 12, 0});
      }

   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 0});
      }

   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
      }

   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      if (this.isPhoneState()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 5});
         this.sendPhone(new byte[0]);
      }

   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      if (this.isPhoneState()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1});
         this.sendPhone(var1);
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      if (this.isPhoneState()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 2});
         this.sendPhone(var1);
      }

   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (this.isPhoneState() && !var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 6});
         this.sendPhone(var2);
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      if (this.isPhoneState()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 4});
         this.sendPhone(var1);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      int var3 = this.getCurrentEachCanId();
      this.eachId = var3;
      int var4 = this.mCanBusInfoInt[1];
      if (var4 != 20) {
         if (var4 != 32) {
            if (var4 != 48) {
               if (var4 != 96) {
                  if (var4 != 125) {
                     if (var4 != 127) {
                        if (var4 != 63) {
                           if (var4 != 64) {
                              if (var4 != 82) {
                                 if (var4 != 83) {
                                    switch (var4) {
                                       case 34:
                                          if (this.isAirMsgRepeat(var2)) {
                                             return;
                                          }

                                          this.setAirData0x22();
                                          break;
                                       case 35:
                                          if (this.isAirMsgRepeat(var2)) {
                                             return;
                                          }

                                          this.setAirData0x23();
                                          break;
                                       case 36:
                                          if (this.isDoorMsgRepeat(var2)) {
                                             return;
                                          }

                                          this.setDoorData0x24();
                                          break;
                                       case 37:
                                          this.setRearRadarInfo0x25();
                                          break;
                                       case 38:
                                          if (var3 == 8 || var3 == 21) {
                                             this.setFrontRadarInfo0x26();
                                          }
                                          break;
                                       case 39:
                                          if (var3 == 8) {
                                             this.setSettingData0x27();
                                          }
                                          break;
                                       case 40:
                                          if (this.getCurrentCanDifferentId() == 7) {
                                             this.setOutDoorTem0x28();
                                          }
                                          break;
                                       default:
                                          switch (var4) {
                                             case 78:
                                                this.setSettingData0x4E();
                                                break;
                                             case 79:
                                                if (var3 == 16) {
                                                   this.setSettingData0x4F();
                                                }
                                                break;
                                             case 80:
                                                if (var3 == 5 || var3 == 7 || var3 == 8 || var3 == 10 || var3 == 11 || var3 == 12 || var3 == 13 || var3 == 14 || var3 == 17 || var3 == 18 || var3 == 19 || var3 == 22) {
                                                   this.setSettingData0x50();
                                                }
                                          }
                                    }
                                 } else {
                                    this.setMaintenance0x53();
                                 }
                              } else if (this.getCurrentCanDifferentId() == 8 || this.getCurrentCanDifferentId() == 19) {
                                 this.setTireData0x52();
                              }
                           } else if (var3 == 8 || var3 == 11 || var3 == 23) {
                              this.setPanoramic0x40();
                           }
                        } else {
                           this.setturnlightInfo0x3f();
                        }
                     } else {
                        this.setVersionInfo();
                     }
                  } else {
                     this.set0x7D();
                  }
               } else {
                  this.setChargingState0x60();
               }
            } else {
               this.setTrack0x30();
            }
         } else {
            this.setSteeringWheelControls();
         }
      } else {
         this.setBacklight0x14();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 12, 0});
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 12, 0});
      }

   }

   protected int getPmValue(int var1) {
      if (var1 >= 0 && var1 <= 49) {
         return 1;
      } else if (50 <= var1 && var1 <= 99) {
         return 2;
      } else if (100 <= var1 && var1 <= 149) {
         return 3;
      } else if (150 <= var1 && var1 <= 199) {
         return 4;
      } else if (200 <= var1 && var1 <= 299) {
         return 5;
      } else {
         return 300 <= var1 && var1 <= 999 ? 6 : 0;
      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      GeneralTireData.isHaveSpareTire = false;
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, (byte)this.getCurrentEachCanId()});
   }

   public void mForceReverse(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
         this.sendMusic("", "", "");
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 19});
         if (this.sendMusicFirst(var21, var22, var23)) {
            this.sendMusic(var21, var22, var23);
         }
      }

   }

   public void radioDestroy() {
      super.radioDestroy();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.isCanPlay()) {
         byte var6 = this.getAllBandTypeData(var2, (byte)0, (byte)0, (byte)0, (byte)16, (byte)16);
         this.getFreqByteHiLo(var2, var3);
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, var6, this.freqLo, this.freqHi});
      }

   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoDestroy() {
      super.videoDestroy();
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
         this.sendMusic("", "", "");
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (this.isCanPlay()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 0});
         var8 = var3 + "/" + var4;
         var11 = var11 + ":" + var12 + ":" + var13;
         if (this.sendMusicFirst(var8, var11, " ")) {
            this.sendMusic(var8, var11, " ");
         }
      }

   }
}
