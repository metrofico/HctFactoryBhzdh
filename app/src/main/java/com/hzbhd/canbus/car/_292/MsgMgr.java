package com.hzbhd.canbus.car._292;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.fragment.OnStartPhoneFragment;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private static int outDoorTemp;
   private int FreqInt;
   private byte bandType;
   private byte freqHi;
   private byte freqLo;
   private byte[] mCanInfoByte;
   private int[] mCanInfoInt;
   private Context mContext;

   private void carBodyStatus() {
      GeneralDoorData.isShowCarBody = false;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]) ^ true;
         GeneralDoorData.isSubSeatBeltTie = true ^ DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]);
      } else {
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]) ^ true;
         GeneralDoorData.isSubSeatBeltTie = true ^ DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]);
      }

      this.updateDoorView(this.mContext);
   }

   private void carSetting1() {
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanInfoInt[4])) {
         var2 = "- ";
      } else {
         var2 = " ";
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 0, 7);
      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(1, 0, (float)(this.mCanInfoInt[3] / 10) + ""));
      var3.add(new DriverUpdateEntity(1, 1, var2 + var1 + "℃"));
      if (DataHandleUtils.getBoolBit7(this.mCanInfoInt[5])) {
         var2 = this.getResString(2131769504);
      } else {
         var2 = this.getResString(2131768042);
      }

      var3.add(new DriverUpdateEntity(1, 2, var2));
      if (DataHandleUtils.getBoolBit6(this.mCanInfoInt[5])) {
         var2 = this.getResString(2131769504);
      } else {
         var2 = this.getResString(2131768042);
      }

      var3.add(new DriverUpdateEntity(1, 3, var2));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanInfoInt;
      var3.add(new DriverUpdateEntity(1, 4, var5.append(var4[6] * 256 * 256 * 256 + var4[7] * 256 * 256 + var4[8] * 256 + var4[9]).append("Km").toString()));
      if (DataHandleUtils.getBoolBit7(this.mCanInfoInt[2])) {
         var2 = this.getResString(2131769504);
      } else {
         var2 = this.getResString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 3, var2));
      if (DataHandleUtils.getBoolBit6(this.mCanInfoInt[2])) {
         var2 = this.getResString(2131769504);
      } else {
         var2 = this.getResString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 4, var2));
      if (DataHandleUtils.getBoolBit5(this.mCanInfoInt[2])) {
         var2 = this.getResString(2131769504);
      } else {
         var2 = this.getResString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 5, var2));
      if (DataHandleUtils.getBoolBit4(this.mCanInfoInt[2])) {
         var2 = this.getResString(2131769504);
      } else {
         var2 = this.getResString(2131768042);
      }

      var3.add(new DriverUpdateEntity(0, 6, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void carSetting2() {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var1 = this.mCanInfoInt;
      var2.add(new DriverUpdateEntity(1, 5, var3.append((double)((float)(var1[4] * 256 + var1[5])) * 0.1).append(" L/100km").toString()));
      var2.add(new DriverUpdateEntity(1, 6, this.mCanInfoInt[6] + "%"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void centerCtrlSetting() {
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 7, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 5, 2);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 4, 1);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 3, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 1, 2);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 7, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 5, 2);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 4, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 3, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 1, 1);
      ArrayList var13 = new ArrayList();

      for(int var1 = 0; var1 < 11; ++var1) {
         var13.add(new SettingUpdateEntity(0, var1, (new int[]{var11, var9, var3, var12, var5, var6, var8, var2, var7, var4, var10})[var1]));
      }

      this.updateGeneralSettingData(var13);
      this.updateSettingActivity((Bundle)null);
   }

   private void centerCtrlSetting2() {
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 7, 1);
      int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 6, 1);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 5, 1);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 4, 1);
      int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 3, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 2, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 2);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 7, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 6, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 5, 1);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 4, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 3, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 2, 1);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 0, 2);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 3, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 2, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 0, 1);
      ArrayList var19 = new ArrayList();

      for(int var1 = 0; var1 < 16; ++var1) {
         var19.add(new SettingUpdateEntity(1, var1, (new int[]{var7, var16, var14, var15, var17, var8, var5, var6, var9, var2, var11, var4, var13, var18, var10, var3})[var1]));
      }

      if (var12 == 3) {
         var19.add(new SettingUpdateEntity(1, 16, 2));
      } else {
         var19.add(new SettingUpdateEntity(1, 16, var12));
      }

      this.updateGeneralSettingData(var19);
      this.updateSettingActivity((Bundle)null);
   }

   private void epsSetting() {
      byte[] var1 = this.mCanInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 7800, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void frontRadar() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("AM1")) {
         if (var1.equals("FM1") || var1.equals("FM2")) {
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

   private int getHigh4Bit(int var1) {
      return DataHandleUtils.getIntFromByteWithBit(var1, 4, 4);
   }

   private int getLow4Bit(int var1) {
      return DataHandleUtils.getIntFromByteWithBit(var1, 0, 4);
   }

   private String getResString(int var1) {
      return this.mContext.getResources().getString(var1);
   }

   private boolean isOutDoorTempChange() {
      return SharePreUtil.getIntValue(this.mContext, "_291_out_door_temp", 0) != outDoorTemp;
   }

   private void onStarPhoneInfo() {
      GeneralOnStartData.mOnStarPhoneNum = this.phoneNum();
      this.updateOnStarActivity(1001);
   }

   private void onStarStatusInfo() {
      if (this.mCanInfoInt[2] == 0) {
         this.exitAuxIn2();
         Log.d("cwh", "anjixng name = " + OnStarActivity.class.getName());
         if (SystemUtil.isForeground(this.mContext, OnStarActivity.class.getName())) {
            this.realKeyClick(this.mContext, 52);
         }
      } else {
         this.enterAuxIn2(this.mContext, Constant.OnStarActivity);
         this.openOnStarPhoneInfoFragment();
      }

      GeneralOnStartData.mOnStarStatus = this.mCanInfoInt[2];
      this.updateOnStarActivity(1001);
   }

   private void openOnStarPhoneInfoFragment() {
      if (!SystemUtil.isForeground(this.mContext, Constant.OnStarActivity.getClassName())) {
         Intent var1 = new Intent();
         var1.setComponent(Constant.OnStarActivity);
         var1.setFlags(268435456);
         var1.putExtra("bundle_open_fragment", OnStartPhoneFragment.class);
         this.mContext.startActivity(var1);
      }
   }

   private String phoneNum() {
      StringBuilder var2 = new StringBuilder();

      for(int var1 = 2; var1 <= 11 && this.getLow4Bit(this.mCanInfoInt[var1]) != 15 && this.getHigh4Bit(this.mCanInfoInt[var1]) != 15; ++var1) {
         if (this.getHigh4Bit(this.mCanInfoInt[var1]) == 10 || this.getLow4Bit(this.mCanInfoInt[var1]) == 10) {
            var2.append("*");
         }

         if (this.getHigh4Bit(this.mCanInfoInt[var1]) != 11 && this.getLow4Bit(this.mCanInfoInt[var1]) != 11) {
            var2.append(this.getHigh4Bit(this.mCanInfoInt[var1]));
            var2.append(this.getLow4Bit(this.mCanInfoInt[var1]));
         } else {
            var2.append("#");
         }

         Log.d("cwh", var1 + " = " + this.getHigh4Bit(this.mCanInfoInt[var1]) + "、" + this.getLow4Bit(this.mCanInfoInt[var1]));
      }

      return var2.toString();
   }

   private void radarOnOffInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void realKeyClick2(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanInfoInt;
      this.realKeyClick2(var3, var1, var2[2], var2[3]);
   }

   private void rearPanelBtn() {
   }

   private void rearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (255 == var1) {
         var2 = "--";
      } else if (var1 == 0) {
         var2 = "LO";
      } else if (30 == var1) {
         var2 = "HI";
      } else if (var1 >= 1 && var1 <= 28) {
         var2 = (float)(var1 + 33) * 0.5F + this.getTempUnitC(this.mContext);
      } else if (var1 == 29) {
         var2 = 16 + this.getTempUnitC(this.mContext);
      } else if (var1 == 31) {
         var2 = 16.5 + this.getTempUnitC(this.mContext);
      } else if (var1 == 32) {
         var2 = 15 + this.getTempUnitC(this.mContext);
      } else if (var1 == 33) {
         var2 = 15.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void setAirInfo() {
      // $FF: Couldn't be decompiled
   }

   private void setCarStatus() {
      ArrayList var2 = new ArrayList();
      int var1;
      if (DataHandleUtils.getBoolBit2(this.mCanInfoInt[3])) {
         var1 = 2131769504;
      } else {
         var1 = 2131768042;
      }

      var2.add(new DriverUpdateEntity(0, 1, this.getResString(var1)));
      if (DataHandleUtils.getBoolBit1(this.mCanInfoInt[3])) {
         var1 = 2131768423;
      } else {
         var1 = 2131768424;
      }

      var2.add(new DriverUpdateEntity(0, 2, this.getResString(var1)));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setLanguage() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 0, this.mCanInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setPanelBtnKey() {
      int var1 = this.mCanInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 21) {
                        this.realKeyClick2(75);
                        return;
                     }

                     if (var1 == 22) {
                        this.realKeyClick2(49);
                        return;
                     }

                     if (var1 == 64) {
                        this.realKeyClick2(141);
                        return;
                     }

                     if (var1 == 80) {
                        this.realKeyClick2(52);
                        return;
                     }

                     if (var1 == 81) {
                        this.realKeyClick2(2);
                        return;
                     }

                     switch (var1) {
                        case 6:
                           this.realKeyClick2(50);
                           return;
                        case 7:
                           this.realKeyClick2(76);
                           return;
                        case 8:
                           this.realKeyClick2(130);
                           return;
                        case 9:
                           this.realKeyClick2(3);
                           return;
                        case 10:
                           this.realKeyClick2(33);
                           return;
                        case 11:
                           this.realKeyClick2(34);
                           return;
                        case 12:
                           this.realKeyClick2(35);
                           return;
                        case 13:
                           this.realKeyClick2(36);
                           return;
                        case 14:
                           this.realKeyClick2(37);
                           return;
                        case 15:
                           this.realKeyClick2(38);
                           return;
                        default:
                           switch (var1) {
                              case 17:
                                 this.realKeyClick2(31);
                                 return;
                              case 18:
                                 break;
                              case 19:
                                 this.realKeyClick2(196);
                                 return;
                              default:
                                 switch (var1) {
                                    case 25:
                                       this.realKeyClick2(48);
                                       return;
                                    case 26:
                                       this.realKeyClick2(47);
                                       return;
                                    case 27:
                                       this.realKeyClick2(17);
                                       return;
                                    case 28:
                                       this.realKeyClick2(7);
                                       return;
                                    case 29:
                                       this.realKeyClick2(46);
                                       return;
                                    default:
                                       return;
                                 }
                           }
                     }
                  }

                  this.realKeyClick2(185);
               } else {
                  this.realKeyClick2(20);
               }
            } else {
               this.realKeyClick2(21);
            }
         } else {
            this.realKeyClick2(1);
         }
      } else {
         this.realKeyClick2(0);
      }

   }

   private void setSwc() {
      switch (this.mCanInfoInt[2]) {
         case 0:
            this.realKeyClick2(0);
            break;
         case 1:
            this.realKeyClick2(7);
            break;
         case 2:
            this.realKeyClick2(8);
            break;
         case 3:
            this.realKeyClick2(45);
            break;
         case 4:
            this.realKeyClick2(46);
            break;
         case 5:
            this.realKeyClick2(2);
            break;
         case 6:
            this.realKeyClick2(201);
            break;
         case 7:
            this.realKeyClick2(184);
      }

   }

   private void setVwRadioInfo(String var1, String var2) {
      if (!var1.equals("AM1") && !var1.equals("AM2")) {
         if (var1.equals("FM1")) {
            this.bandType = 1;
         } else if (var1.equals("FM2")) {
            this.bandType = 2;
         }
      } else {
         int var3 = Integer.parseInt(var2);
         this.freqHi = (byte)(var3 >> 8);
         this.freqLo = (byte)(var3 & 255);
         this.bandType = 16;
      }

      this.getFreqByteHiLo(var1, var2);
   }

   private void smallLight() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 7);
      String var2;
      if (var1 == 0) {
         var2 = this.getResString(2131769222);
      } else if (var1 == 17) {
         var2 = this.getResString(2131769223);
      } else {
         var2 = var1 + "";
      }

      if (DataHandleUtils.getBoolBit7(this.mCanInfoInt[2])) {
         var2 = this.getResString(2131769504) + "   " + this.getResString(2131767875) + ": " + var2;
      } else {
         var2 = this.getResString(2131768042);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void warningVolumeStatus() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(2, 1, this.mCanInfoInt[2])).setProgress(this.mCanInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OnStarActivity);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 64});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      super.canbusInfoChange(var1, var2);
      this.mContext = var1;
      this.mCanInfoInt = this.getByteArrayToIntArray(var2);
      this.mCanInfoByte = var2;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      int[] var4 = this.mCanInfoInt;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 4) {
                  if (var3 != 38) {
                     if (var3 != 97) {
                        if (var3 != 98) {
                           switch (var3) {
                              case 6:
                                 this.centerCtrlSetting();
                                 break;
                              case 7:
                                 this.radarOnOffInfo();
                                 break;
                              case 8:
                                 this.onStarPhoneInfo();
                                 break;
                              case 9:
                                 this.onStarStatusInfo();
                                 break;
                              case 10:
                                 this.centerCtrlSetting2();
                                 break;
                              case 11:
                                 this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[2], var4[3]));
                                 break;
                              case 12:
                                 this.setLanguage();
                                 break;
                              case 13:
                                 this.warningVolumeStatus();
                                 break;
                              default:
                                 switch (var3) {
                                    case 34:
                                       this.rearRadar();
                                       break;
                                    case 35:
                                       this.frontRadar();
                                       break;
                                    case 36:
                                       this.setCarStatus();
                                       break;
                                    default:
                                       switch (var3) {
                                          case 48:
                                             this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanInfoByte));
                                             break;
                                          case 49:
                                             this.carSetting1();
                                             break;
                                          case 50:
                                             this.carSetting2();
                                       }
                                 }
                           }
                        } else {
                           this.rearPanelBtn();
                        }
                     } else {
                        this.carBodyStatus();
                     }
                  } else {
                     this.epsSetting();
                  }
               } else {
                  this.smallLight();
               }
            } else {
               this.setAirInfo();
            }
         } else {
            this.setPanelBtnKey();
         }
      } else {
         this.setSwc();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 33});
         CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var5, (byte)var6, 0, 0, (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 17});
         CanbusMsgSender.sendMsg(new byte[]{22, -61, 1, (byte)var4, 0, 0, (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 48});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte)this.getCurrentEachCanId()});
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, 16});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 19});
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1});
      this.setVwRadioInfo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte)var1});
      CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, 32});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 32});
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, 0, 0});
   }
}
