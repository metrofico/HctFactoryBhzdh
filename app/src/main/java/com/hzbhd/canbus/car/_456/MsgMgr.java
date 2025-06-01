package com.hzbhd.canbus.car._456;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private String consumptionUnit = "l/100km";
   private String distanceUnit = "km";
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mCarDoorData;
   Context mContext;
   int[] mDoorInfo;
   int[] mFrontRadarData;
   int[] mRearRadarData;
   int[] mTrackData;
   private UiMgr mUiMgr;
   public int state60 = 0;
   public int state61 = 0;
   public int state62 = 0;
   public int state63 = 0;
   public int state64 = 0;
   public int state65 = 0;
   public int state66 = 0;

   private String getAirMode(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? this.mContext.getString(2131766407) : this.mContext.getString(2131766409);
      } else {
         return this.mContext.getString(2131766408);
      }
   }

   private String getDriverMode(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               return var1 != 4 ? this.mContext.getString(2131766401) : this.mContext.getString(2131766405);
            } else {
               return this.mContext.getString(2131766404);
            }
         } else {
            return this.mContext.getString(2131766403);
         }
      } else {
         return this.mContext.getString(2131766402);
      }
   }

   private String getGearState(int var1) {
      switch (var1) {
         case 1:
            return "1";
         case 2:
            return "2";
         case 3:
            return "3";
         case 4:
            return "4";
         case 5:
            return "5";
         case 6:
            return "6";
         case 7:
            return "R";
         case 8:
            return "D";
         case 9:
            return "M";
         case 10:
            return "N";
         default:
            return "P";
      }
   }

   private String getLowHighState(int var1) {
      if (var1 == 0) {
         return " ";
      } else {
         return var1 == 1 ? "LOW" : "HIGH";
      }
   }

   private int getRadarRange(int var1) {
      return var1;
   }

   private String getTireTagState(int var1) {
      if (var1 == 7) {
         return this.mContext.getString(2131766412);
      } else if (var1 < 7) {
         return this.mContext.getString(2131766413);
      } else if (var1 == 20) {
         return this.mContext.getString(2131766414);
      } else if (var1 == 30) {
         return this.mContext.getString(2131766415);
      } else {
         return var1 > 30 ? this.mContext.getString(2131766416) : "" + var1;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorInfoChange(int[] var1) {
      if (Arrays.equals(this.mDoorInfo, var1)) {
         return false;
      } else {
         this.mDoorInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setBasicInfo0x01(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         this.setBacklightLevel(var1[3] / 20 + 1);
      }

   }

   private void setDate0x38(int[] var1) {
      ArrayList var7 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_4");
      boolean var4 = DataHandleUtils.getBoolBit2(var1[3]);
      String var6 = "ON";
      String var5;
      if (var4) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_5"), this.getLowHighState(DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 2))));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_6"), this.getDriverMode(DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 4))));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_7"), this.getAirMode(DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 4))));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_8"), this.getGearState(var1[7])));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_9"), this.getTireTagState(var1[8])));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_10"), this.getTireTagState(var1[9])));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_11"), this.getTireTagState(var1[10])));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_12"), this.getTireTagState(var1[11])));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_13");
      if (DataHandleUtils.getBoolBit7(var1[12])) {
         var5 = "ON";
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      var7.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_14"), DataHandleUtils.getIntFromByteWithBit(var1[12], 5, 2) + "LEVEL"));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_15");
      if (DataHandleUtils.getBoolBit4(var1[12])) {
         var5 = var6;
      } else {
         var5 = "OFF";
      }

      var7.add(new DriverUpdateEntity(var2, var3, var5));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
      ArrayList var8 = new ArrayList();
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_0"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[3]))));
      var8.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_1"), var1[2])).setProgress(var1[2] - 16));
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_2"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[3]))));
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_3"), DataHandleUtils.getIntFromByteWithBit(var1[3], 4, 2)));
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_4"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[3]))));
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_5"), var1[5]));
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_60"), Integer.valueOf(DataHandleUtils.getBoolBit7(var1[4]))));
      this.state60 = DataHandleUtils.getBoolBit7(var1[4]);
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_61"), Integer.valueOf(DataHandleUtils.getBoolBit6(var1[4]))));
      this.state61 = DataHandleUtils.getBoolBit6(var1[4]);
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_62"), Integer.valueOf(DataHandleUtils.getBoolBit5(var1[4]))));
      this.state62 = DataHandleUtils.getBoolBit5(var1[4]);
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_63"), Integer.valueOf(DataHandleUtils.getBoolBit4(var1[4]))));
      this.state63 = DataHandleUtils.getBoolBit4(var1[4]);
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_64"), Integer.valueOf(DataHandleUtils.getBoolBit3(var1[4]))));
      this.state64 = DataHandleUtils.getBoolBit3(var1[4]);
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_65"), Integer.valueOf(DataHandleUtils.getBoolBit2(var1[4]))));
      this.state65 = DataHandleUtils.getBoolBit2(var1[4]);
      var8.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_66"), Integer.valueOf(DataHandleUtils.getBoolBit1(var1[4]))));
      this.state66 = DataHandleUtils.getBoolBit1(var1[4]);
      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorInfo0x06(int[] var1) {
      if (this.isDoorInfoChange(var1)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setDrive0x37(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_1"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) / 10.0F)) + this.consumptionUnit));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_2"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[4], var1[5]) / 10.0F)) + this.consumptionUnit));
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_3"), this.formatDecimal2.format((double)((float)DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) / 10.0F)) + this.distanceUnit + "/h"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setRearRadar0x04(int[] var1) {
      if (this.isRearRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = false;
         RadarInfoUtil.setRearRadarLocationData(10, this.getRadarRange(var1[2]), this.getRadarRange(var1[3]), this.getRadarRange(var1[4]), this.getRadarRange(var1[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRearRadar0x05(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = false;
         RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarRange(var1[2]), this.getRadarRange(var1[3]), this.getRadarRange(var1[4]), this.getRadarRange(var1[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSwc0x02(int[] var1) {
      switch (var1[2]) {
         case 0:
            this.realKeyLongClick1(this.mContext, 0, var1[3]);
            break;
         case 1:
            this.realKeyLongClick1(this.mContext, 7, var1[3]);
            break;
         case 2:
            this.realKeyLongClick1(this.mContext, 8, var1[3]);
            break;
         case 3:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 4:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 5:
            this.realKeyLongClick1(this.mContext, 2, var1[3]);
            break;
         case 6:
            this.realKeyLongClick1(this.mContext, 68, var1[3]);
            break;
         case 7:
            this.realKeyLongClick1(this.mContext, 14, var1[3]);
         case 8:
         case 12:
         case 14:
         case 15:
         case 16:
         case 21:
         case 26:
         case 28:
         case 29:
         case 30:
         case 31:
         default:
            break;
         case 9:
            this.realKeyLongClick1(this.mContext, 15, var1[3]);
            break;
         case 10:
            this.realKeyLongClick1(this.mContext, 45, var1[3]);
            break;
         case 11:
            this.realKeyLongClick1(this.mContext, 46, var1[3]);
            break;
         case 13:
            this.realKeyLongClick1(this.mContext, 49, var1[3]);
            break;
         case 17:
            this.realKeyLongClick1(this.mContext, 33, var1[3]);
            break;
         case 18:
            this.realKeyLongClick1(this.mContext, 34, var1[3]);
            break;
         case 19:
            this.realKeyLongClick1(this.mContext, 35, var1[3]);
            break;
         case 20:
            this.realKeyLongClick1(this.mContext, 36, var1[3]);
            break;
         case 22:
            this.realKeyLongClick1(this.mContext, 128, var1[3]);
            break;
         case 23:
            this.realKeyLongClick1(this.mContext, 59, var1[3]);
            break;
         case 24:
            this.realKeyLongClick1(this.mContext, 1, var1[3]);
            break;
         case 25:
            this.realKeyLongClick1(this.mContext, 187, var1[3]);
            break;
         case 27:
            this.realKeyLongClick1(this.mContext, 52, var1[3]);
            break;
         case 32:
            this.realKeyLongClick1(this.mContext, 37, var1[3]);
            break;
         case 33:
            this.realKeyLongClick1(this.mContext, 38, var1[3]);
            break;
         case 34:
            this.realKeyLongClick1(this.mContext, 39, var1[3]);
      }

   }

   private void setTrack0x0A(int[] var1) {
      if (this.isTrackInfoChange(var1)) {
         if (DataHandleUtils.getBoolBit0(var1[2])) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)var1[3], (byte)var1[4], 0, 480, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[3], (byte)var1[4], 0, 480, 16);
            this.updateParkUi((Bundle)null, this.mContext);
         }
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "BT MUSIC".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 4) {
               if (var3 != 5) {
                  if (var3 != 6) {
                     if (var3 != 10) {
                        if (var3 != 53) {
                           if (var3 != 127) {
                              if (var3 != 55) {
                                 if (var3 == 56) {
                                    this.setDate0x38(var4);
                                 }
                              } else {
                                 this.setDrive0x37(var4);
                              }
                           } else {
                              this.updateVersionInfo(this.mContext, this.getVersionStr(var2));
                           }
                        } else {
                           this.updateSpeedInfo(var4[3]);
                        }
                     } else {
                        this.setTrack0x0A(var4);
                     }
                  } else {
                     this.setDoorInfo0x06(var4);
                  }
               } else {
                  this.setRearRadar0x05(var4);
               }
            } else {
               this.setRearRadar0x04(var4);
            }
         } else {
            this.setSwc0x02(var4);
         }
      } else {
         this.setBasicInfo0x01(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var25 = "MUSIC".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, var25));
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte[] var6 = "RADIO".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, var6));
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if ("NULL".equals(var1)) {
         byte[] var2 = "MEDIA OFF".getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, var2));
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = "VIDEO".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, var18));
   }
}
