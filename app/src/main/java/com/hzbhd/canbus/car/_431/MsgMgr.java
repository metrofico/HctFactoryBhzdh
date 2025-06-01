package com.hzbhd.canbus.car._431;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
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
   int[] mFrontRadarData;
   int[] mPInfo;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int nowValue = -1;
   SystemButton systemButton;

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

   private boolean isNotPanoramicInfoChange() {
      if (Arrays.equals(this.mPInfo, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPInfo = Arrays.copyOf(var1, var1.length);
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

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void setAirInfo0x31() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 3) {
            if (var1 != 12) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
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

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[8], 254, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[9], 254, 255);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void setCarCamera0xE8() {
      boolean var2;
      boolean var3;
      Context var5;
      label45: {
         var5 = this.mContext;
         int[] var4 = this.mCanBusInfoInt;
         int var1 = var4[4];
         var3 = false;
         if (var1 != 1 && var4[5] != 1) {
            var1 = var4[6];
            if (var1 != 1 && var1 != 1 && var4[7] != 1 && var4[8] != 1) {
               var2 = false;
               break label45;
            }
         }

         var2 = true;
      }

      this.forceReverse(var5, var2);
      ArrayList var6 = new ArrayList();
      if (this.mCanBusInfoInt[7] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(0, var2));
      if (this.mCanBusInfoInt[8] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(1, var2));
      if (this.mCanBusInfoInt[6] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(2, var2));
      if (this.mCanBusInfoInt[4] == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.add(new PanoramicBtnUpdateEntity(3, var2));
      var2 = var3;
      if (this.mCanBusInfoInt[5] == 1) {
         var2 = true;
      }

      var6.add(new PanoramicBtnUpdateEntity(4, var2));
      GeneralParkData.dataList = var6;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setCarSetting0x61() {
      this.getUiMgr(this.mContext);
      UiMgr.now_model1_R = this.mCanBusInfoInt[3];
      this.getUiMgr(this.mContext);
      UiMgr.now_model1_G = this.mCanBusInfoInt[4];
      this.getUiMgr(this.mContext);
      UiMgr.now_model1_B = this.mCanBusInfoInt[5];
      this.getUiMgr(this.mContext);
      UiMgr.now_model2_R = this.mCanBusInfoInt[7];
      this.getUiMgr(this.mContext);
      UiMgr.now_model2_G = this.mCanBusInfoInt[8];
      this.getUiMgr(this.mContext);
      UiMgr.now_model2_B = this.mCanBusInfoInt[9];
      this.getUiMgr(this.mContext);
      UiMgr.now_model3_R = this.mCanBusInfoInt[11];
      this.getUiMgr(this.mContext);
      UiMgr.now_model3_G = this.mCanBusInfoInt[12];
      this.getUiMgr(this.mContext);
      UiMgr.now_model3_B = this.mCanBusInfoInt[13];
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_r"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_g"), this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_b"), this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_l"), this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_r"), this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_g"), this.mCanBusInfoInt[8])).setProgress(this.mCanBusInfoInt[8]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_b"), this.mCanBusInfoInt[9])).setProgress(this.mCanBusInfoInt[9]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_l"), this.mCanBusInfoInt[10])).setProgress(this.mCanBusInfoInt[10]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_r"), this.mCanBusInfoInt[11])).setProgress(this.mCanBusInfoInt[11]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_g"), this.mCanBusInfoInt[12])).setProgress(this.mCanBusInfoInt[12]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_b"), this.mCanBusInfoInt[13])).setProgress(this.mCanBusInfoInt[13]));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_l"), this.mCanBusInfoInt[14])).setProgress(this.mCanBusInfoInt[14]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarType0x26() {
   }

   private void setDoorInfo0x12() {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setKnob0x22() {
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
            this.realKeyClick4(this.mContext, 46);
         } else {
            this.realKeyClick4(this.mContext, 45);
         }

         this.nowValue = this.mCanBusInfoInt[3];
      }
   }

   private void setPanelButton0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 21) {
                     if (var1 != 22) {
                        if (var1 != 36) {
                           if (var1 != 40) {
                              if (var1 != 51) {
                                 if (var1 != 57) {
                                    if (var1 != 67) {
                                       if (var1 != 62) {
                                          if (var1 != 63) {
                                             switch (var1) {
                                                case 9:
                                                   this.buttonKey2(3);
                                                   break;
                                                case 10:
                                                   this.buttonKey2(33);
                                                   break;
                                                case 11:
                                                   this.buttonKey2(34);
                                                   break;
                                                case 12:
                                                   this.buttonKey2(35);
                                                   break;
                                                case 13:
                                                   this.buttonKey2(36);
                                                   break;
                                                case 14:
                                                   this.buttonKey2(37);
                                                   break;
                                                case 15:
                                                   this.buttonKey2(38);
                                             }
                                          } else {
                                             this.buttonKey2(187);
                                          }
                                       } else {
                                          this.buttonKey2(128);
                                       }
                                    } else {
                                       this.buttonKey2(30);
                                    }
                                 } else {
                                    this.buttonKey2(134);
                                 }
                              } else {
                                 this.buttonKey2(62);
                              }
                           } else {
                              this.buttonKey2(188);
                           }
                        } else {
                           this.buttonKey2(59);
                        }
                     } else {
                        this.buttonKey2(49);
                     }
                  } else {
                     this.buttonKey2(75);
                  }
               } else {
                  this.buttonKey2(20);
               }
            } else {
               this.buttonKey2(21);
            }
         } else {
            this.buttonKey2(1);
         }
      } else {
         this.buttonKey2(0);
      }

   }

   private void setRadarInfo0x41() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 255) {
         var1[2] = 0;
      }

      if (var1[3] == 255) {
         var1[3] = 0;
      }

      if (var1[4] == 255) {
         var1[4] = 0;
      }

      if (var1[5] == 255) {
         var1[5] = 0;
      }

      if (var1[6] == 255) {
         var1[6] = 0;
      }

      if (var1[7] == 255) {
         var1[7] = 0;
      }

      if (var1[8] == 255) {
         var1[8] = 0;
      }

      if (var1[9] == 255) {
         var1[9] = 0;
      }

      if (var1[13] == 0) {
         RadarInfoUtil.mMinIsClose = true;
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(3, var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarDistanceData(var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSwc0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 48) {
                           switch (var1) {
                              case 8:
                                 this.buttonKey(45);
                                 break;
                              case 9:
                                 this.buttonKey(46);
                                 break;
                              case 10:
                                 this.buttonKey(151);
                                 break;
                              default:
                                 switch (var1) {
                                    case 12:
                                       this.buttonKey(2);
                                       break;
                                    case 13:
                                       this.buttonKey(47);
                                       break;
                                    case 14:
                                       this.buttonKey(48);
                                 }
                           }
                        } else {
                           this.buttonKey(3);
                        }
                     } else {
                        this.buttonKey(15);
                     }
                  } else {
                     this.buttonKey(14);
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
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
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
      String var2 = "AUX Playing";

      for(int var1 = 0; var1 < 1; ++var1) {
         var2 = var2 + " ";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(12, var2.getBytes());
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void buttonKey2(int var1) {
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
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 38) {
                     if (var3 != 49) {
                        if (var3 != 65) {
                           if (var3 != 97) {
                              if (var3 != 232) {
                                 if (var3 == 240) {
                                    this.setVersion0xF0();
                                 }
                              } else {
                                 this.setCarCamera0xE8();
                              }
                           } else {
                              this.setCarSetting0x61();
                           }
                        } else {
                           this.setRadarInfo0x41();
                        }
                     } else {
                        this.setAirInfo0x31();
                     }
                  } else {
                     this.setCarType0x26();
                  }
               } else {
                  this.setKnob0x22();
               }
            } else {
               this.setPanelButton0x21();
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
      this.getUiMgr(this.mContext).sendTimeInfo(var5, var6, var10, var1 - 2000, var3, var4);
   }

   public void forceReverse(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void hideButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "360", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramic0xFD(4, 1);
                  }
               });
            }

            this.this$0.systemButton.hide();
         }
      });
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(this.mContext).carType(this.eachId);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = var5 + var12 + var13;
      var4 = var11.length();

      for(var3 = 0; var3 < 12 - var4; ++var3) {
         var11 = var11 + " ";
      }

      if (var1 == 9) {
         this.getUiMgr(this.mContext).sendMediaInfo0x91(25, var11.getBytes());
      } else {
         this.getUiMgr(this.mContext).sendMediaInfo0x91(13, var11.getBytes());
      }

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

   public void showButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "360", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramic0xFD(4, 1);
                  }
               });
            }

            this.this$0.systemButton.show();
         }
      });
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

      if (var1 == 9) {
         this.getUiMgr(this.mContext).sendMediaInfo0x91(25, var8.getBytes());
      } else {
         this.getUiMgr(this.mContext).sendMediaInfo0x91(13, var8.getBytes());
      }

   }
}
