package com.hzbhd.canbus.car._395;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   int nowLight = -1;
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");

   private String ResolveOutDoorTemp(int var1) {
      String var2 = (double)var1 * 0.5 - 40.0 + this.getTempUnitC(this.mContext);
      if (var1 == 255) {
         var2 = "---";
      }

      return var2;
   }

   private String ResolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "LOW_TEMP";
      }

      if (var1 == 255) {
         var2 = "HIGH_TEMP";
      }

      return var2;
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private int getRadarData(int var1) {
      int var2 = var1;
      if (var1 == 255) {
         var2 = 0;
      }

      return var2;
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private Object getTireState(int var1) {
      return var1 == 1 ? "Resetting..." : "Reset successful";
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void setAirInfo0x31() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 3) {
            if (var1 != 12) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
         this.updateOutDoorTemp(this.mContext, this.ResolveOutDoorTemp(this.mCanBusInfoInt[13]));
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void setBacklight() {
      int var1 = this.nowLight;
      int var2 = this.mCanBusInfoInt[7];
      if (var1 != var2) {
         this.nowLight = var2;
         this.setBacklightLevel(var2 / 20 + 1);
      }
   }

   private void setCarSetting0x66() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting5"), this.getTireState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)))).setValueStr(true).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_394_seting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_394_seting", "_394_seting8"), " ")).setValueStr(true).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setDoorInfo0x12() {
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setDriveInfo0x32() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var4.append(this.getMsbLsbResult(var5[4], var5[5])).append(" RPM").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var4.append(this.getMsbLsbResult(var5[6], var5[7])).append(" KM/H").toString()));
      var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_394_drive_v"), this.oneDecimal.format((double)this.mCanBusInfoInt[8] * 0.1) + " V"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var6[6], var6[7]));
   }

   private void setRadar0x41() {
      if (this.isRearRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(7, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setTrackDate0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 != 11) {
                                 if (var1 == 24) {
                                    this.realKeyClick0x11(58);
                                 }
                              } else {
                                 this.realKeyClick0x11(2);
                              }
                           } else {
                              this.realKeyClick0x11(46);
                           }
                        } else {
                           this.realKeyClick0x11(45);
                        }
                     } else {
                        this.realKeyClick0x11(188);
                     }
                  } else {
                     this.realKeyClick0x11(187);
                  }
               } else {
                  this.realKeyClick0x11(3);
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

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 49) {
               if (var3 != 50) {
                  if (var3 != 65) {
                     if (var3 == 102) {
                        this.setCarSetting0x66();
                     }
                  } else {
                     this.setRadar0x41();
                  }
               } else {
                  this.setDriveInfo0x32();
               }
            } else {
               this.setAirInfo0x31();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
         this.setTrackDate0x11();
         this.setBacklight();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, (byte)var10, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void toast(String var1) {
      Toast.makeText(this.mContext, var1, 0).show();
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
}
