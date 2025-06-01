package com.hzbhd.canbus.car._412;

import android.content.Context;
import android.os.Bundle;
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
   int nowValue = -1;

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
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
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

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
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

   private void set0x11CarInfo() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  switch (var1) {
                     case 8:
                        this.buttonKey2(45);
                        break;
                     case 9:
                        this.buttonKey2(46);
                        break;
                     case 10:
                        this.buttonKey2(2);
                  }
               } else {
                  this.buttonKey2(3);
               }
            } else {
               this.buttonKey2(8);
            }
         } else {
            this.buttonKey2(7);
         }
      } else {
         this.buttonKey2(0);
      }

      int[] var2 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var2[9], (byte)var2[8], 0, 10000, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x12CarInfo() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void set0x21PanoroKey() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 21) {
                     if (var1 != 22) {
                        if (var1 != 37) {
                           if (var1 != 60) {
                              if (var1 != 61) {
                                 switch (var1) {
                                    case 9:
                                       this.buttonKey(3);
                                       break;
                                    case 10:
                                       this.buttonKey(33);
                                       break;
                                    case 11:
                                       this.buttonKey(34);
                                       break;
                                    case 12:
                                       this.buttonKey(35);
                                       break;
                                    case 13:
                                       this.buttonKey(36);
                                       break;
                                    case 14:
                                       this.buttonKey(37);
                                       break;
                                    case 15:
                                       this.buttonKey(37);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 51:
                                             this.buttonKey(62);
                                             break;
                                          case 52:
                                             this.buttonKey(14);
                                             break;
                                          case 53:
                                             this.buttonKey(15);
                                       }
                                 }
                              } else {
                                 this.buttonKey(139);
                              }
                           } else {
                              this.buttonKey(29);
                           }
                        } else {
                           this.buttonKey(128);
                        }
                     } else {
                        this.buttonKey(58);
                     }
                  } else {
                     this.buttonKey(75);
                  }
               } else {
                  this.buttonKey(20);
               }
            } else {
               this.buttonKey(21);
            }
         } else {
            this.buttonKey(1);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void set0x22PanoroKnob() {
      int var1 = this.nowValue;
      if (var1 == -1) {
         this.nowValue = this.mCanBusInfoInt[3];
      } else {
         int[] var2 = this.mCanBusInfoInt;
         if (var2[2] == 1) {
            if (var1 < var2[3]) {
               this.realKeyClick4(this.mContext, 7);
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         } else if (var1 < var2[3]) {
            this.realKeyClick4(this.mContext, 48);
         } else {
            this.realKeyClick4(this.mContext, 47);
         }

         this.nowValue = this.mCanBusInfoInt[3];
      }
   }

   private void set0x31Air() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 0) {
            if (var1 != 3) {
               if (var1 != 12) {
                  if (var1 != 5) {
                     if (var1 == 6) {
                        GeneralAirData.front_left_blow_window = false;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_right_blow_window = false;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = false;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_window = false;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_window = false;
               GeneralAirData.front_right_blow_head = false;
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
         GeneralAirData.front_left_temperature = this.mCanBusInfoInt[8] + "LEVEL";
         GeneralAirData.front_right_temperature = this.mCanBusInfoInt[8] + "LEVEL";
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void set0x32CarInfo() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_412_drive");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_412_drive1");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[4], var4[5])).append("RPM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_412_drive");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_412_drive2");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[6], var4[7])).append("KM/H").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var6[6], var6[7]));
   }

   private void set0x41RadarInfo() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
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

   public void buttonKey2(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 49) {
                     if (var3 != 50) {
                        if (var3 != 65) {
                           if (var3 == 240) {
                              this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
                           }
                        } else {
                           this.set0x41RadarInfo();
                        }
                     } else {
                        this.set0x32CarInfo();
                     }
                  } else {
                     this.set0x31Air();
                  }
               } else {
                  this.set0x22PanoroKnob();
               }
            } else {
               this.set0x21PanoroKey();
            }
         } else {
            this.set0x12CarInfo();
         }
      } else {
         this.set0x11CarInfo();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      if (this.getCurrentCanDifferentId() == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 1, 0});
      }

   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
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
