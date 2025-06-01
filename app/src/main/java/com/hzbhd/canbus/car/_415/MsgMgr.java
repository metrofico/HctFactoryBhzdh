package com.hzbhd.canbus.car._415;

import android.content.Context;
import android.os.Bundle;
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
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mDoorData;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int nowValue = -1;

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

   private String getTemperature(int var1, int var2, int var3) {
      if (var1 == var2) {
         return "LO";
      } else {
         return var1 == var3 ? "HI" : this.df_2Decimal.format((double)var1 * 0.5) + this.getTempUnitC(this.mContext);
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

   private boolean isNotDoor0x12DataChange() {
      if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mDoorData = this.mCanBusInfoInt;
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

   private void set0x34Info() {
      ArrayList var7 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data0");
      StringBuilder var4 = new StringBuilder();
      DecimalFormat var5 = this.df_2Decimal;
      int[] var6 = this.mCanBusInfoInt;
      int var1 = var6[6];
      var7.add(new DriverUpdateEntity(var2, var3, var4.append(var5.format((double)((var6[7] & 255) << 8 | (var1 & 255) << 16 | this.mCanBusInfoByte[8] & 255) * 0.1)).append("KM").toString()));
      this.updateGeneralDriveData(var7);
      this.updateDriveDataActivity((Bundle)null);
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

   private void setAir0x31() {
      this.updateOutDoorTemp(this.mContext, this.df_2Decimal.format((double)this.mCanBusInfoInt[13] * 0.5 - 40.0) + this.getTempUnitC(this.mContext));
      this.mCanBusInfoInt[13] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  if (var1 != 11) {
                     if (var1 == 12) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[8], 239, 255);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void setDoorInfo0x12() {
      if (!this.isNotDoor0x12DataChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setDoorSetting0x66() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting", "_415_setting0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting", "_415_setting1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setLight0x67() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting2", "_415_setting3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting2", "_415_setting4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setPanelKeyInfo0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 36) {
                     if (var1 != 37) {
                        if (var1 != 66) {
                           if (var1 != 67) {
                              if (var1 != 69) {
                                 if (var1 != 70) {
                                    if (var1 != 77) {
                                       if (var1 != 78) {
                                          switch (var1) {
                                             case 6:
                                                this.knobKey(50);
                                                break;
                                             case 9:
                                                this.knobKey(3);
                                                break;
                                             case 16:
                                                this.knobKey(17);
                                                break;
                                             case 19:
                                                this.knobKey(39);
                                                break;
                                             case 22:
                                                this.knobKey(40);
                                                break;
                                             case 32:
                                                this.knobKey(128);
                                                break;
                                             case 40:
                                                this.knobKey(14);
                                                break;
                                             case 42:
                                                this.knobKey(49);
                                                break;
                                             case 47:
                                                this.knobKey(151);
                                                break;
                                             case 51:
                                                this.knobKey(62);
                                                break;
                                             case 55:
                                                this.knobKey(58);
                                                break;
                                             case 57:
                                                this.knobKey(134);
                                                break;
                                             case 75:
                                                this.knobKey(62);
                                                break;
                                             case 94:
                                                this.knobKey(75);
                                                break;
                                             default:
                                                switch (var1) {
                                                   case 60:
                                                      this.knobKey(29);
                                                      break;
                                                   case 61:
                                                      this.knobKey(139);
                                                      break;
                                                   case 62:
                                                      this.knobKey(68);
                                                }
                                          }
                                       } else {
                                          this.knobKey(46);
                                       }
                                    } else {
                                       this.knobKey(45);
                                    }
                                 } else {
                                    this.knobKey(8);
                                 }
                              } else {
                                 this.knobKey(7);
                              }
                           } else {
                              this.knobKey(30);
                           }
                        } else {
                           this.knobKey(4);
                        }
                     } else {
                        this.knobKey(27);
                     }
                  } else {
                     this.knobKey(2);
                  }
               } else {
                  this.knobKey(20);
               }
            } else {
               this.knobKey(21);
            }
         } else {
            this.knobKey(1);
         }
      } else {
         this.knobKey(0);
      }

   }

   private void setPanelKnob0x22() {
      int var1 = this.nowValue;
      if (var1 == -1) {
         this.nowValue = this.mCanBusInfoInt[3];
      } else {
         int[] var2 = this.mCanBusInfoInt;
         if (var2[2] == 1) {
            if (var2[3] < var1) {
               this.realKeyClick4(this.mContext, 8);
            } else {
               this.realKeyClick4(this.mContext, 7);
            }
         }

         this.nowValue = this.mCanBusInfoInt[3];
      }
   }

   private void setSwc0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 8) {
                        if (var1 == 9) {
                           this.buttonKey(46);
                        }
                     } else {
                        this.buttonKey(45);
                     }
                  } else {
                     this.buttonKey(188);
                  }
               } else {
                  this.buttonKey(187);
               }
            } else {
               this.buttonKey(8);
            }
         } else {
            this.buttonKey(7);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void setTrack0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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

   public void auxInInfoChange() {
      super.auxInInfoChange();
      String var2 = "Aux";

      for(int var1 = 0; var1 < 9; ++var1) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(12, var2.getBytes());
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      String var2 = "BT music";

      for(int var1 = 0; var1 < 4; ++var1) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(21, var2.getBytes());
   }

   public void buttonKey(int var1) {
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
                     if (var3 != 52) {
                        if (var3 != 65) {
                           if (var3 != 240) {
                              if (var3 != 102) {
                                 if (var3 == 103) {
                                    this.setLight0x67();
                                 }
                              } else {
                                 this.setDoorSetting0x66();
                              }
                           } else {
                              this.setVersion0xF0();
                           }
                        } else {
                           this.set0x41RadarInfo();
                        }
                     } else {
                        this.set0x34Info();
                     }
                  } else {
                     this.setAir0x31();
                  }
               } else {
                  this.setPanelKnob0x22();
               }
            } else {
               this.setPanelKeyInfo0x21();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setSwc0x11();
         this.setTrack0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xCBTimeInfo(var5, var6, var10);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = var5 + var12 + var13;
      var4 = var11.length();

      for(var3 = 0; var3 < 12 - var4; ++var3) {
         var11 = var11 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(22, var11.getBytes());
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      boolean var7 = var2.equals("FM1");
      byte var8 = 1;
      if (var7) {
         var2 = var3 + "MHz";
      } else if (var2.equals("FM2")) {
         var8 = 2;
         var2 = var3 + "MHz";
      } else if (var2.equals("FM3")) {
         var8 = 3;
         var2 = var3 + "MHz";
      } else if (var2.equals("AM1")) {
         var8 = 4;
         var2 = var3 + "KHz";
      } else if (var2.equals("AM2")) {
         var8 = 5;
         var2 = var3 + "KHz";
      } else {
         var2 = "nothing";
      }

      int var6 = var2.length();

      for(var5 = 0; var5 < 12 - var6; ++var5) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(var8, var2.getBytes());
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
      var8 = var5 + var12 + var13;
      var4 = var8.length();

      for(var3 = 0; var3 < 12 - var4; ++var3) {
         var8 = var8 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(22, var8.getBytes());
   }
}
