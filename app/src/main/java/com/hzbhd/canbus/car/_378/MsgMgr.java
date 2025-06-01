package com.hzbhd.canbus.car._378;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   int Memory;
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private HashMap mDriveItemHashMap;
   private HashMap mSettingItemHashMap;
   private UiMgr mUiMgr;

   private void RealKeyClick(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
   }

   private int getBandData(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 3;
      } else if ("AM1".equals(var1)) {
         return 4;
      } else {
         return "AM2".equals(var1) ? 5 : 0;
      }
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
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

   private void initDriveItem(DriverDataPageUiSet var1) {
      this.mDriveItemHashMap = new HashMap();
      List var6 = var1.getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var4 = ((DriverDataPageUiSet.Page)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            String var5 = ((DriverDataPageUiSet.Page.Item)var4.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var5, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var7 = var1.getList();

      for(int var2 = 0; var2 < var7.size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var7.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var5.get(var3);
            String var4 = var6.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var6.getMin()));
         }
      }

   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = var1;
         return true;
      }
   }

   private String resolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 - 40.0 + this.getTempUnitC(this.mContext);
      if (var1 == 1) {
         var2 = "LOW";
      }

      if (var1 == 255) {
         var2 = "HIGH";
      }

      return var2;
   }

   private void setAirInfo0x73(int[] var1) {
      int var2 = DataHandleUtils.blockBit(var1[2], 7);
      var1[2] = var2;
      var2 = DataHandleUtils.blockBit(var2, 5);
      var1[2] = var2;
      var2 = DataHandleUtils.blockBit(var2, 1);
      var1[2] = var2;
      var1[2] = DataHandleUtils.blockBit(var2, 0);
      var2 = DataHandleUtils.blockBit(var1[3], 7);
      var1[3] = var2;
      var2 = DataHandleUtils.blockBit(var2, 5);
      var1[3] = var2;
      var2 = DataHandleUtils.blockBit(var2, 3);
      var1[3] = var2;
      var2 = DataHandleUtils.blockBit(var2, 2);
      var1[3] = var2;
      var2 = DataHandleUtils.blockBit(var2, 1);
      var1[3] = var2;
      var1[3] = DataHandleUtils.blockBit(var2, 0);
      var1[6] = DataHandleUtils.blockBit(var1[6], 7);
      var1[7] = 0;
      var1[8] = 0;
      var1[9] = 0;
      if (this.isAirDataChange(var1)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.mono = DataHandleUtils.getBoolBit2(var1[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(var1[3]);
         GeneralAirData.front_left_temperature = this.resolveTemp(var1[4]);
         GeneralAirData.front_right_temperature = this.resolveTemp(var1[5]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(var1[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(var1[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(var1[6]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(var1[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 4);
         this.updateAirActivity(this.mContext, 1001);
      }

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

   private void setDriveInfo0x32() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("engine_speed", var3.append((var2[4] << 8) + var2[5]).append("").toString())));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_279_dashboard_data2", var4.append((var5[6] << 8) + var5[7]).append("").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRadarInfo0x72() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[8], var1[9], var1[10], var1[11]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarDistanceData(var1[12], var1[13], var1[14], var1[15]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSpeedInfo0x72() {
      ArrayList var1 = new ArrayList();
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("car_speed", this.mCanBusInfoInt[3] + "")));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setTrackData0x72() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[6];
      if (var1 != 0) {
         GeneralParkData.trackAngle = -(var1 / 10);
      } else {
         GeneralParkData.trackAngle = var2[7] / 10;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x72() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 32) {
                           switch (var1) {
                              case 8:
                                 this.RealKeyClick(21);
                                 break;
                              case 9:
                                 this.RealKeyClick(20);
                                 break;
                              case 10:
                                 this.RealKeyClick(151);
                           }
                        } else {
                           this.startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
                        }
                     } else {
                        this.RealKeyClick(468);
                     }
                  } else {
                     this.RealKeyClick(467);
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 12}, 14));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 11}, 14));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 18) {
         if (var3 != 50) {
            if (var3 != 240) {
               if (var3 != 114) {
                  if (var3 == 115) {
                     this.setAirInfo0x73(var4);
                  }
               } else {
                  this.updateSpeedInfo(var4[3]);
                  this.setWheelKey0x72();
                  this.setSpeedInfo0x72();
                  this.setTrackData0x72();
                  this.setRadarInfo0x72();
               }
            } else {
               this.setVersionInfo0xF0();
            }
         } else {
            this.setDriveInfo0x32();
         }
      } else {
         this.setDoorInfo0x12();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initDriveItem(this.getUiMgr(this.mContext).getDriverDataPageUiSet(this.mContext));
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      var11 = String.format("     %04d   ", var9 << 8 | var3);
      var1 = (byte)var25;
      byte[] var26 = var11.getBytes();
      var26 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var26);
      CanbusMsgSender.sendMsg(var26);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var1 = this.getBandData(var2);
      this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -46, (byte)var1});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (var1) {
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 0}, 14));
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 14;
      } else {
         var18 = 13;
      }

      var8 = String.format("     %04d   ", var9 << 8 | var3);
      var1 = (byte)var18;
      byte[] var19 = var8.getBytes();
      var19 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var19);
      CanbusMsgSender.sendMsg(var19);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
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
