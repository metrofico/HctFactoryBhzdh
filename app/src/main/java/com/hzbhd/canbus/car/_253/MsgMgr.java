package com.hzbhd.canbus.car._253;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_123_OUTDOOR_TEMPERATURE_UNIT = "share_123_outdoor_temperature_unit";
   static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
   static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
   private int AutoAcState = 0;
   private int DualState = 0;
   private final int MEDIA_SOURCE_ID_AUX = 7;
   private final int MEDIA_SOURCE_ID_USB = 8;
   private int RearDefogState = 0;
   private int eachId;
   private int frontDefogState = 0;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferentId;
   private byte mFreqHi;
   private byte mFreqLo;
   private boolean mFrontStatus;
   private boolean mHandBrake;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private UiMgr mUiMgr;
   private MsgMgr msgMgr;

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var5 = new byte[var4];

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var3 < var2) {
            var5[var3] = var1[var3];
         } else {
            var5[var3] = var1[var3 + 1];
         }
      }

      return var5;
   }

   private byte getAllBandTypeData(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 0;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.mFreqHi = (byte)(var3 >> 8);
            this.mFreqLo = (byte)(var3 & 255);
         }
      } else {
         this.mFreqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.mFreqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private UiMgr getmUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mHandBrake = GeneralDoorData.isHandBrakeUp;
         return true;
      }
   }

   private boolean isEnlarge(int var1) {
      return var1 == 5 || var1 == 6 || var1 == 7 || var1 == 8;
   }

   private boolean isFront(int var1) {
      return var1 == 1 || var1 == 5;
   }

   private boolean isLeft(int var1) {
      return var1 == 3 || var1 == 7;
   }

   private boolean isNarrow(int var1) {
      return var1 == 1 || var1 == 2 || var1 == 3 || var1 == 4;
   }

   private boolean isRear(int var1) {
      return var1 == 2 || var1 == 6;
   }

   private boolean isRight(int var1) {
      return var1 == 4 || var1 == 8;
   }

   private boolean isSupport0x21() {
      switch (this.eachId) {
         case 1:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 9:
         case 10:
         case 12:
         case 13:
            return false;
         case 2:
         case 8:
         case 11:
         default:
            return true;
      }
   }

   private boolean isSupport0x22() {
      switch (this.eachId) {
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
            return false;
         default:
            return true;
      }
   }

   private boolean isSupport0x23() {
      int var1 = this.eachId;
      return var1 != 9 && var1 != 10;
   }

   private boolean isSupport0x26() {
      int var1 = this.eachId;
      return var1 != 9 && var1 != 10;
   }

   private boolean isSupport0x27() {
      return this.eachId != 10;
   }

   private boolean isSupport0x39() {
      switch (this.eachId) {
         case 11:
         case 12:
         case 13:
            return false;
         default:
            return true;
      }
   }

   private void reakkeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveCenterWheel(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = " ";
            } else {
               var2 = this.mContext.getResources().getString(2131759938);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131759937);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131759936);
      }

      return var2;
   }

   private String resolveLeftAndRightTemp(int var1) {
      int var2 = this.eachId;
      String var3;
      if (var2 != 3) {
         if (var2 != 4 && var2 != 5 && var2 != 6) {
            if (var1 == 0) {
               var3 = "LO";
            } else if (var1 == 255) {
               var3 = "HI";
            } else {
               if (GeneralAirData.fahrenheit_celsius) {
                  if (var1 >= 1 && var1 <= 33) {
                     var3 = var1 + 60 + this.getTempUnitF(this.mContext);
                     return var3;
                  }
               } else if (var1 >= 1 && var1 <= 31) {
                  var3 = (double)((float)(var1 + 32) / 2.0F) - 0.5 + this.getTempUnitC(this.mContext);
                  return var3;
               }

               var3 = "---";
            }
         } else {
            var3 = Integer.toString(DataHandleUtils.rangeNumber(var1, 1, 15)) + "LEVER";
         }
      } else {
         var3 = Integer.toString(DataHandleUtils.rangeNumber(var1, 1, 9)) + "LEVER";
      }

      return var3;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[9];
      String var2;
      if (var1 >= 0 && var1 <= 180) {
         if (GeneralAirData.fahrenheit_celsius) {
            var2 = (new DecimalFormat("0.0")).format((double)(((float)var1 / 2.0F - 40.0F) * 9.0F / 5.0F + 32.0F)) + this.getTempUnitF(this.mContext);
         } else {
            var2 = (float)var1 / 2.0F - 40.0F + this.getTempUnitC(this.mContext);
         }
      } else {
         var2 = " ";
      }

      return var2;
   }

   private void sendMusic(String var1) {
      try {
         byte[] var3 = DataHandleUtils.exceptBOMHead(var1.getBytes("Unicode"));
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 1}, var3));
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
      }

   }

   private void set0x14BackLight() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 1) {
         this.setBacklightLevel(var1[3] / 41 + 1);
      }

   }

   private void set0x20WheelKeyInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 16) {
         if (var1 != 17) {
            switch (var1) {
               case 0:
                  this.realKeyLongClick1(this.mContext, 0, var2[3]);
                  break;
               case 1:
                  this.realKeyLongClick1(this.mContext, 7, var2[3]);
                  break;
               case 2:
                  this.realKeyLongClick1(this.mContext, 8, var2[3]);
                  break;
               case 3:
                  this.realKeyLongClick1(this.mContext, 45, var2[3]);
                  break;
               case 4:
                  this.realKeyLongClick1(this.mContext, 46, var2[3]);
                  break;
               case 5:
                  if (this.getCurrentCanDifferentId() != 0 && this.getCurrentCanDifferentId() != 8) {
                     this.realKeyLongClick1(this.mContext, 14, this.mCanBusInfoInt[3]);
                  } else {
                     this.realKeyLongClick1(this.mContext, 188, this.mCanBusInfoInt[3]);
                  }
                  break;
               case 6:
                  if (this.getCurrentCanDifferentId() == 2) {
                     this.realKeyLongClick1(this.mContext, 15, this.mCanBusInfoInt[3]);
                  } else {
                     this.realKeyLongClick1(this.mContext, 184, this.mCanBusInfoInt[3]);
                  }
                  break;
               case 7:
                  this.realKeyLongClick1(this.mContext, 2, var2[3]);
                  break;
               case 8:
                  this.realKeyLongClick1(this.mContext, 7, var2[3]);
                  break;
               case 9:
                  this.realKeyLongClick1(this.mContext, 8, var2[3]);
                  break;
               case 10:
                  this.realKeyLongClick1(this.mContext, 1, var2[3]);
                  break;
               default:
                  switch (var1) {
                     case 22:
                        this.realKeyLongClick1(this.mContext, 3, var2[3]);
                        break;
                     case 23:
                        this.realKeyLongClick1(this.mContext, 52, var2[3]);
                        break;
                     case 24:
                        this.realKeyLongClick1(this.mContext, 50, var2[3]);
                        break;
                     case 25:
                        this.realKeyLongClick1(this.mContext, 128, var2[3]);
                        break;
                     default:
                        switch (var1) {
                           case 32:
                              this.realKeyLongClick1(this.mContext, 134, var2[3]);
                              break;
                           case 33:
                              this.realKeyLongClick1(this.mContext, 2, var2[3]);
                              break;
                           case 34:
                              this.realKeyLongClick1(this.mContext, 187, var2[3]);
                              break;
                           case 35:
                              this.realKeyLongClick1(this.mContext, 49, var2[3]);
                        }
                  }
            }
         } else {
            this.realKeyLongClick1(this.mContext, 15, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 14, var2[3]);
      }

   }

   private void setAirData0x21() {
      if (!this.isSupport0x21()) {
         byte[] var2 = this.bytesExpectOneByte(this.mCanBusInfoByte, 9);
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         this.setOutDoorTemp();
         GeneralAirData.center_wheel = this.resolveCenterWheel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2));
         if (!this.isAirMsgRepeat(var2)) {
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
            GeneralAirData.ion = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
            int var1 = this.eachId;
            if (var1 == 3 || var1 == 13) {
               GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
            }

            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            if (GeneralAirData.in_out_auto_cycle != 2) {
               GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
            }

            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setCarSettingState0x39() {
      int var1 = this.mCanBusInfoInt[2];
      ArrayList var2 = new ArrayList();
      switch (this.mCanBusInfoInt[2]) {
         case 2:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_initial_perspective"), this.mCanBusInfoInt[3] - 1));
            break;
         case 3:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_front_radar_switch"), this.mCanBusInfoInt[3]));
            break;
         case 4:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_rear_radar_switch"), this.mCanBusInfoInt[3]));
            break;
         case 5:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Lane_deviation_warning"), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Blind_spot_warning"), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
            break;
         case 6:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Language"), this.mCanBusInfoInt[3]));
            break;
         case 7:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Unit_of_speed_and_fuel_consumption"), this.mCanBusInfoInt[3]));
            break;
         case 8:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Temperature_unit"), this.mCanBusInfoInt[3]));
            break;
         case 9:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Theme"), this.mCanBusInfoInt[3]));
            break;
         case 10:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_HeadLight_delay"), this.mCanBusInfoInt[3]));
            break;
         case 11:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Remote_lock_reminder"), this.mCanBusInfoInt[3]));
            break;
         case 12:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Ambient_light_Lightness"), this.mCanBusInfoInt[3]));
            break;
         case 13:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Tire_Pressure_Monitoring"), this.mCanBusInfoInt[3]));
            break;
         case 14:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Automatic_folding_Rearview_mirror"), this.mCanBusInfoInt[3]));
            break;
         case 15:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Parking_unlocked"), this.mCanBusInfoInt[3]));
            break;
         case 16:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Ambient_light_mode"), this.mCanBusInfoInt[3]));
            break;
         case 17:
            var2.add((new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Rearview_mirror_backlight_brightness"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
            break;
         case 18:
            var2.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Rearview_mirror_automatically_anti_glare"), this.mCanBusInfoInt[3]));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDAS0x27() {
      if (!this.isSupport0x27()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Lane_deviation_warning"), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
         var1.add(new SettingUpdateEntity(this.getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Blind_spot_warning"), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void setDoorData0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (this.isDoorChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadar0x23() {
      if (!this.isSupport0x23()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = false;
         if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_front_radar_enable", true)) {
            int[] var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         } else {
            RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
         }

         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setOutDoorTemp() {
      if (this.mCanBusInfoInt.length >= 7) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }
   }

   private void setPanaramic0x26() {
      if (!this.isSupport0x26()) {
         Context var4 = this.mContext;
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         boolean var2;
         if (var1 == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.forceReverse(var4, var2);
         ArrayList var5 = new ArrayList();
         if (this.mCanBusInfoInt[3] == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var5.add(new PanoramicBtnUpdateEntity(0, var2));
         var2 = var3;
         if (this.mCanBusInfoInt[3] == 1) {
            var2 = true;
         }

         var5.add(new PanoramicBtnUpdateEntity(1, var2));
         var5.add(new PanoramicBtnUpdateEntity(2, this.isFront(this.mCanBusInfoInt[4])));
         var5.add(new PanoramicBtnUpdateEntity(3, this.isRear(this.mCanBusInfoInt[4])));
         var5.add(new PanoramicBtnUpdateEntity(4, this.isLeft(this.mCanBusInfoInt[4])));
         var5.add(new PanoramicBtnUpdateEntity(5, this.isRight(this.mCanBusInfoInt[4])));
         var5.add(new PanoramicBtnUpdateEntity(6, this.isEnlarge(this.mCanBusInfoInt[4])));
         var5.add(new PanoramicBtnUpdateEntity(7, this.isNarrow(this.mCanBusInfoInt[4])));
         GeneralParkData.dataList = var5;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setPanelKey0x25() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               switch (var1) {
                  case 9:
                     this.reakkeyClick(33);
                     break;
                  case 10:
                     this.reakkeyClick(34);
                     break;
                  case 11:
                     this.reakkeyClick(35);
                     break;
                  case 12:
                     this.reakkeyClick(36);
                     break;
                  case 13:
                     this.reakkeyClick(37);
                     break;
                  case 14:
                     this.reakkeyClick(38);
                     break;
                  case 15:
                     this.reakkeyClick(39);
                     break;
                  case 16:
                     this.reakkeyClick(40);
                     break;
                  case 17:
                     this.realKeyClick3_1(this.mContext, 8, 0, var2[3]);
                     break;
                  case 18:
                     this.realKeyClick3_1(this.mContext, 7, 0, var2[3]);
                     break;
                  case 19:
                     this.reakkeyClick(49);
                     break;
                  case 20:
                     this.reakkeyClick(68);
                     break;
                  case 21:
                     this.reakkeyClick(59);
                     break;
                  case 22:
                     this.reakkeyClick(76);
                     break;
                  case 23:
                     this.reakkeyClick(52);
                     break;
                  case 24:
                     this.reakkeyClick(58);
                     break;
                  case 25:
                     this.reakkeyClick(1);
               }
            } else {
               this.reakkeyClick(50);
            }
         } else {
            this.reakkeyClick(128);
         }
      } else {
         this.reakkeyClick(0);
      }

   }

   private void setRearRadar0x22() {
      if (!this.isSupport0x22()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = false;
         if (SharePreUtil.getBoolValue(this.mContext, "share_key_253_rear_radar_enable", true)) {
            int[] var1 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         } else {
            RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
         }

         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setTrackData0x29() {
      label28: {
         byte[] var2;
         label27: {
            int var1 = this.eachId;
            if (var1 != 1) {
               if (var1 == 2 || var1 == 3) {
                  break label27;
               }

               switch (var1) {
                  case 8:
                  case 11:
                  case 12:
                  case 13:
                     break label27;
                  case 9:
                  case 10:
                     break;
                  default:
                     return;
               }
            }

            var2 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 7936, 13055, 16);
            break label28;
         }

         var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 7808, 13708, 16);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void tokingNowTime(int var1) {
      int var2 = var1 % 3600;
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)(var2 % 60), (byte)(var2 / 60), (byte)(var1 / 3600), 0});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 1});
      this.tokingNowTime(0);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1, 1});
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      this.tokingNowTime(var4);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 41) {
            if (var3 != 48) {
               if (var3 != 57) {
                  switch (var3) {
                     case 32:
                        this.set0x20WheelKeyInfo();
                        break;
                     case 33:
                        this.setAirData0x21();
                        break;
                     case 34:
                        this.setRearRadar0x22();
                        break;
                     case 35:
                        this.setFrontRadar0x23();
                        break;
                     case 36:
                        this.setDoorData0x24();
                        break;
                     case 37:
                        this.setPanelKey0x25();
                        break;
                     case 38:
                        this.setPanaramic0x26();
                        break;
                     case 39:
                        this.setDAS0x27();
                  }
               } else {
                  this.setCarSettingState0x39();
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            this.setTrackData0x29();
         }
      } else {
         this.set0x14BackLight();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)(var1 - 2020), (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
   }

   public void destroyCommand() {
      super.destroyCommand();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   void initRadar() {
      RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
      RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.mContext != null) {
         byte var6 = this.getAllBandTypeData(var2);
         this.getFreqByteHiLo(var2, var3);
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, (byte)var6, this.mFreqLo, this.mFreqHi, (byte)var1, 0, 0});
      }
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
      }
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      int var3 = this.eachId;
      byte var2 = 13;
      if (var3 == 12 || var3 == 13) {
         label133: {
            var1.hashCode();
            switch (var1) {
               case "front_defog":
                  var2 = 0;
                  break label133;
               case "air.ac.on":
                  var2 = 1;
                  break label133;
               case "ac.temperature.max":
                  var2 = 2;
                  break label133;
               case "ac.temperature.min":
                  var2 = 3;
                  break label133;
               case "ac.open":
                  var2 = 4;
                  break label133;
               case "air.in.out.cycle.off":
                  var2 = 5;
                  break label133;
               case "air.ac.off":
                  var2 = 6;
                  break label133;
               case "air_right_temperature_up":
                  var2 = 7;
                  break label133;
               case "ac.windlevel.max":
                  var2 = 8;
                  break label133;
               case "ac.windlevel.min":
                  var2 = 9;
                  break label133;
               case "rear_defog":
                  var2 = 10;
                  break label133;
               case "auto":
                  var2 = 11;
                  break label133;
               case "dual":
                  var2 = 12;
                  break label133;
               case "air_left_temperature_down":
               case "ac.close":
                  var2 = 14;
                  break label133;
               case "ac.windlevel.down":
                  var2 = 15;
                  break label133;
               case "air_left_temperature_up":
                  var2 = 16;
                  break label133;
               case "ac.windlevel.up":
                  var2 = 17;
                  break label133;
               case "air.in.out.cycle.on":
                  var2 = 18;
                  break label133;
               case "air_right_temperature_down":
                  var2 = 19;
                  break label133;
            }

            var2 = -1;
         }

         switch (var2) {
            case 0:
               if (this.frontDefogState == 0) {
                  this.frontDefogState = 1;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(6, 1);
               } else {
                  this.frontDefogState = 0;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(6, 0);
               }
               break;
            case 1:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(2, 1);
               break;
            case 2:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(10, 204);
               break;
            case 3:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(10, 221);
               break;
            case 4:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(1, 1);
               break;
            case 5:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(4, 1);
               break;
            case 6:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(2, 0);
               break;
            case 7:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(11, 170);
               break;
            case 8:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(9, 204);
               break;
            case 9:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(9, 221);
               break;
            case 10:
               if (this.RearDefogState == 0) {
                  this.RearDefogState = 1;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(7, 1);
               } else {
                  this.RearDefogState = 0;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(7, 0);
               }
               break;
            case 11:
               if (this.AutoAcState == 0) {
                  this.AutoAcState = 1;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(3, 1);
               } else {
                  this.AutoAcState = 0;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(3, 0);
               }
               break;
            case 12:
               if (this.DualState == 0) {
                  this.DualState = 1;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(8, 1);
               } else {
                  this.DualState = 0;
                  this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(8, 0);
               }
               break;
            case 13:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(10, 187);
               break;
            case 14:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(1, 0);
               break;
            case 15:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(9, 187);
               break;
            case 16:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(10, 170);
               break;
            case 17:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(9, 170);
               break;
            case 18:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(4, 0);
               break;
            case 19:
               this.getmUigMgr(this.mContext).sendVoiceControlInfoAir(11, 187);
         }

      }
   }
}
