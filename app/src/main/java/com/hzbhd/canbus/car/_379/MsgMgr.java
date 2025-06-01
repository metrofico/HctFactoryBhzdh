package com.hzbhd.canbus.car._379;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_379_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
   static final String SHARE_379_AMPLIFIER_BASS = "share_122_amplifier_bass";
   static final String SHARE_379_AMPLIFIER_FADE = "share_122_amplifier_fade";
   static final String SHARE_379_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
   static final String SHARE_379_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
   static final String SHARE_379_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
   static final int _379_AMPLIFIER_OFFSET = 1;
   static final int _379_AMPLIFIER_RANGE = 9;
   public boolean CameraDelay;
   public boolean DepressedView;
   int EngineSpeed;
   Boolean EngineSpeedEnable;
   int FactoryCode;
   int InstantaneousSpeed;
   Boolean InstantaneousSpeedEnable;
   int LeftCold;
   int LeftHeat;
   int LeftTemp;
   int Memory;
   int RightCold;
   int RightHeat;
   int RightTemp;
   int WindLevel;
   boolean ac;
   boolean ac_max;
   boolean auto;
   boolean blowfoot;
   boolean blowhead;
   boolean blowwindow;
   int differentId;
   boolean dual;
   int eachId;
   boolean in_out_cycle;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private HashMap mDriveItemHashMap;
   private HashMap mSettingItemHashMap;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private UiMgr mUiMgr;
   boolean mac_heat;
   boolean power;
   boolean rear_defog;
   boolean steering_wheel_heating;

   public MsgMgr() {
      Boolean var1 = false;
      this.EngineSpeedEnable = var1;
      this.InstantaneousSpeedEnable = var1;
   }

   private void RealKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void SetTrackInfo0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void SetWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 10) {
                           if (var1 != 11) {
                              if (var1 != 23) {
                                 if (var1 == 40) {
                                    this.RealKeyClick(187);
                                 }
                              } else {
                                 this.RealKeyClick(4113);
                              }
                           } else {
                              this.RealKeyClick(15);
                           }
                        } else {
                           this.RealKeyClick(14);
                        }
                     } else {
                        this.RealKeyClick(20);
                     }
                  } else {
                     this.RealKeyClick(21);
                  }
               } else {
                  this.RealKeyClick(3);
               }
            } else {
               this.RealKeyClick(8);
            }
         } else {
            this.RealKeyClick(7);
         }
      } else {
         this.RealKeyClick(0);
      }

   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
   }

   private void finishTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private DriverUpdateEntity helperSetDriveDataValue(String var1, String var2) {
      if (!this.mDriveItemHashMap.containsKey(var1)) {
         this.mDriveItemHashMap.put(var1, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
      }

      return ((DriveDataUpdateHelper)this.mDriveItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2, boolean var3) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper((new SettingUpdateEntity(-1, -1, "null_value")).setEnable(false)));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2).setEnable(var3);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2, boolean var3, boolean var4) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper((new SettingUpdateEntity(-1, -1, "null_value")).setEnable(false).setValueStr(false)));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2).setEnable(var3).setValueStr(var4);
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_122_amplifier_volume", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_122_amplifier_balance", 0);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_122_amplifier_fade", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_122_amplifier_bass", 0);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_122_amplifier_middle", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_122_amplifier_treble", 0);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
      byte var2 = (byte)(GeneralAmplifierData.leftRight + 1 + 9);
      byte[] var5 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 1 + 9)};
      byte[] var6 = new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 1)};
      byte var3 = (byte)(GeneralAmplifierData.bandMiddle + 1);
      byte[] var4 = new byte[]{22, -83, 6, (byte)(GeneralAmplifierData.bandTreble + 1)};
      this.mTimerTask = new TimerTask(this, new byte[][]{{22, -83, 2, var2}, var5, var6, {22, -83, 5, var3}, var4}) {
         int index;
         final MsgMgr this$0;
         final byte[][] val$ampCmdArrays;

         {
            this.this$0 = var1;
            this.val$ampCmdArrays = var2;
            this.index = 0;
         }

         public void run() {
            int var1 = this.index;
            byte[][] var2 = this.val$ampCmdArrays;
            if (var1 < var2.length) {
               this.index = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.this$0.finishTimer();
            }

         }
      };
      this.startTimer(100L, 100);
   }

   private void initDriveItem(DriverDataPageUiSet var1) {
      this.mDriveItemHashMap = new HashMap();
      List var6 = var1.getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var5 = ((DriverDataPageUiSet.Page)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var4 = ((DriverDataPageUiSet.Page.Item)var5.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var4, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var5 = var1.getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var7 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var7.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var7.get(var3);
            String var4 = var6.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var6.getMin()));
         }
      }

   }

   private boolean resolveAcMax(int var1) {
      Boolean var2 = false;
      if (var1 == 3) {
         var2 = true;
      }

      return var2;
   }

   private String resolveAirVersion(int[] var1) {
      return var1 + "";
   }

   private int resolveLanguage(int var1) {
      return (new int[]{0, 0, 1, 0, 0, 2, 0, 3})[var1];
   }

   private int resolveSeries(int var1) {
      return (new int[]{0, 0, 1, 0, 0, 2, 3, 4, 5, 6})[var1];
   }

   private String resolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "Low_Temp";
      }

      if (var1 == 255) {
         var2 = "High_Temp";
      }

      return var2;
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_volume", GeneralAmplifierData.volume);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_122_amplifier_middle", GeneralAmplifierData.bandMiddle);
   }

   private void setAirInfo0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      this.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = this.resolveAcMax(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
      this.ac_max = this.resolveAcMax(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
      GeneralAirData.max_heat = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      this.mac_heat = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      this.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      this.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      this.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
      this.LeftHeat = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      this.RightHeat = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      this.LeftCold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      this.RightCold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
      int[] var2 = this.mCanBusInfoInt;
      this.WindLevel = var2[7];
      this.LeftTemp = var2[8];
      this.RightTemp = var2[9];
      int var1 = var2[6];
      if (var1 != 2 && var1 != 4 && var1 != 7 && var1 != 10) {
         this.blowwindow = false;
      } else {
         this.blowwindow = true;
      }

      if (var1 != 3 && var1 != 4 && var1 != 5 && var1 != 10) {
         this.blowfoot = false;
      } else {
         this.blowfoot = true;
      }

      if (var1 != 6 && var1 != 5 && var1 != 7 && var1 != 10) {
         this.blowhead = false;
      } else {
         this.blowhead = true;
      }

      if (var1 != 0) {
         if (var1 != 10) {
            switch (var1) {
               case 2:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 3:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 4:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 5:
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_window = false;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 6:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  break;
               case 7:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = false;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = false;
            }
         } else {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveTemp(this.mCanBusInfoInt[9]);
      this.updateOutDoorTemp(this.mContext, (double)this.mCanBusInfoInt[13] * 0.5 - 40.0 + this.getTempUnitC(this.mContext));
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirVersionInfo0xF1() {
      ArrayList var1 = new ArrayList();
      var1.add(this.checkEntity(this.helperSetValue("_379_item_26", this.resolveAirVersion(this.mCanBusInfoInt), true, true)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAmplifierInfo0xA6() {
      ArrayList var1 = new ArrayList();
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 7;
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 7;
      var1.add(this.checkEntity(this.helperSetValue("speed_linkage_volume_adjustment", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
      var1.add(this.checkEntity(this.helperSetValue("surround_sound", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData();
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var1);
   }

   private void setCarModel0x26() {
      ArrayList var1 = new ArrayList();
      var1.add(this.checkEntity(this.helperSetValue("_379_item_13", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 3)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_14", this.resolveSeries(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)))));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_15", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 1)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_16", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) - 1)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_17", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) - 1)));
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var1);
   }

   private void setDoorInfo0x12() {
      int var1 = this.Memory;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 != var2) {
         this.Memory = var2;
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var2);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setDriveIndo0x320x3F() {
      ArrayList var1 = new ArrayList();
      if (this.EngineSpeedEnable) {
         var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_214_car_speed1", this.EngineSpeed + "")));
      }

      if (this.InstantaneousSpeedEnable) {
         var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_214_car_speed2", this.InstantaneousSpeed + "")));
      }

      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveInfo0x32() {
      int[] var1 = this.mCanBusInfoInt;
      this.EngineSpeed = (var1[4] << 8) + var1[5];
      this.InstantaneousSpeed = (var1[6] << 8) + var1[7];
      this.setDriveIndo0x320x3F();
      var1 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[6], var1[7]));
   }

   private void setDriveInfo0x3F() {
      this.EngineSpeedEnable = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.InstantaneousSpeedEnable = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.setDriveIndo0x320x3F();
   }

   private void setLanguage0x94() {
      ArrayList var1 = new ArrayList();
      var1.add(this.checkEntity(this.helperSetValue("language_setup", this.resolveLanguage(this.mCanBusInfoInt[2]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalCarVideoStatus0xE8() {
      ArrayList var2 = new ArrayList();
      var2.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      var2.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
      this.CameraDelay = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      this.DepressedView = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[5];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     var2.add(new PanoramicBtnUpdateEntity(2, false));
                     var2.add(new PanoramicBtnUpdateEntity(3, false));
                     var2.add(new PanoramicBtnUpdateEntity(4, false));
                     var2.add(new PanoramicBtnUpdateEntity(5, false));
                     var2.add(new PanoramicBtnUpdateEntity(6, true));
                  }
               } else {
                  var2.add(new PanoramicBtnUpdateEntity(2, false));
                  var2.add(new PanoramicBtnUpdateEntity(3, false));
                  var2.add(new PanoramicBtnUpdateEntity(4, false));
                  var2.add(new PanoramicBtnUpdateEntity(5, true));
                  var2.add(new PanoramicBtnUpdateEntity(6, false));
               }
            } else {
               var2.add(new PanoramicBtnUpdateEntity(2, false));
               var2.add(new PanoramicBtnUpdateEntity(3, false));
               var2.add(new PanoramicBtnUpdateEntity(4, true));
               var2.add(new PanoramicBtnUpdateEntity(5, false));
               var2.add(new PanoramicBtnUpdateEntity(6, false));
            }
         } else {
            var2.add(new PanoramicBtnUpdateEntity(2, false));
            var2.add(new PanoramicBtnUpdateEntity(3, true));
            var2.add(new PanoramicBtnUpdateEntity(4, false));
            var2.add(new PanoramicBtnUpdateEntity(5, false));
            var2.add(new PanoramicBtnUpdateEntity(6, false));
         }
      } else {
         var2.add(new PanoramicBtnUpdateEntity(2, true));
         var2.add(new PanoramicBtnUpdateEntity(3, false));
         var2.add(new PanoramicBtnUpdateEntity(4, false));
         var2.add(new PanoramicBtnUpdateEntity(5, false));
         var2.add(new PanoramicBtnUpdateEntity(6, false));
      }

      GeneralParkData.dataList = var2;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRadarInfo0x41() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRemainder0x68() {
      ArrayList var1 = new ArrayList();
      var1.add(this.checkEntity(this.helperSetValue("_379_item_12", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
      var1.add(this.checkEntity(this.helperSetValue("temperature_unit", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var1);
   }

   private void setSettingInfo0x61() {
      new ArrayList();
      ArrayList var1 = new ArrayList();
      var1.add(this.checkEntity(this.helperSetValue("S96ColorTitle", this.mCanBusInfoInt[4])));
      this.FactoryCode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
      var1.add(this.checkEntity(this.helperSetValue("_379_item_01", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4), true, true)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_02", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4), true, true)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_03", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4), true, true)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_04", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) + 1)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_05", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2) + 1)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_06", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4), true, true)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_07", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4), true, true)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_08", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 4), true, true)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_09", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2) + 1)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_10", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2) + 1)));
      var1.add(this.checkEntity(this.helperSetValue("_379_item_11", this.mCanBusInfoInt[11])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            TypeInView.toJudge(this.this$0.FactoryCode);
         }
      });
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void startTimer(long var1, int var3) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1, (long)var3);
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 38) {
               if (var3 != 63) {
                  if (var3 != 65) {
                     if (var3 != 97) {
                        if (var3 != 104) {
                           if (var3 != 148) {
                              if (var3 != 166) {
                                 if (var3 != 232) {
                                    if (var3 != 49) {
                                       if (var3 != 50) {
                                          if (var3 != 240) {
                                             if (var3 == 241) {
                                                this.setAirVersionInfo0xF1();
                                             }
                                          } else {
                                             this.setVersionInfo0xF0();
                                          }
                                       } else {
                                          this.setDriveInfo0x32();
                                       }
                                    } else {
                                       this.setAirInfo0x31();
                                    }
                                 } else {
                                    this.setOriginalCarVideoStatus0xE8();
                                 }
                              } else {
                                 this.setAmplifierInfo0xA6();
                              }
                           } else {
                              this.setLanguage0x94();
                           }
                        } else {
                           this.setRemainder0x68();
                        }
                     } else {
                        this.setSettingInfo0x61();
                     }
                  } else {
                     this.setRadarInfo0x41();
                  }
               } else {
                  this.setDriveInfo0x3F();
               }
            } else {
               this.setCarModel0x26();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.SetWheelKey0x11();
         this.SetTrackInfo0x11();
      }

   }

   protected void forceReverse(Context var1, boolean var2) {
      super.forceReverse(var1, var2);
   }

   public Activity getCurrentActivity() {
      return this.getActivity();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(this.mContext);
      this.initSettingsItem(this.getUiMgr(this.mContext).getSettingUiSet(this.mContext));
      this.initDriveItem(this.getUiMgr(this.mContext).getDriverDataPageUiSet(this.mContext));
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private static class DriveDataUpdateHelper {
      private DriverUpdateEntity entity;

      public DriveDataUpdateHelper(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity getEntity() {
         return this.entity;
      }

      public void setEntity(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity setValue(String var1) {
         this.entity.setValue(var1);
         return this.entity;
      }
   }

   private static class SettingUpdateHelper {
      private SettingUpdateEntity entity;
      private int progressMin;

      public SettingUpdateHelper(SettingUpdateEntity var1) {
         this(var1, 0);
      }

      public SettingUpdateHelper(SettingUpdateEntity var1, int var2) {
         this.entity = var1;
         this.progressMin = var2;
      }

      private SettingUpdateEntity setValueStr(boolean var1) {
         this.entity.setValueStr(var1);
         return this.entity;
      }

      public SettingUpdateEntity getEntity() {
         return this.entity;
      }

      public SettingUpdateEntity setEnable(boolean var1) {
         this.entity.setEnable(var1);
         return this.entity;
      }

      public SettingUpdateEntity setValue(Object var1) {
         if (var1 instanceof Integer) {
            SettingUpdateEntity var2 = this.entity;
            Integer var3 = (Integer)var1;
            var2.setValue(var3 + this.progressMin);
            this.entity.setProgress(var3);
         } else {
            this.entity.setValue(var1);
         }

         return this.entity;
      }
   }
}
