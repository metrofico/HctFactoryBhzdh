package com.hzbhd.canbus.car._207;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static int volKnobValue;
   private static int volKnobValueRadio;
   private final int DATA_TYPE = 1;
   private final String TAG = "_207_MsgMgr";
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private boolean mFrontStatus;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private byte[] mRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   int mTime1Hour;
   int mTime1Minute;
   int mTime2Hour;
   int mTime2Minute;
   private byte[] mTrackData;
   private int mVehicleSpeed;

   private void PanelKnobClick(int var1, int var2) {
      this.realKeyClick3(this.mContext, var1, var2, 1);
   }

   private void Panelkeys0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 9) {
                  if (var1 != 32) {
                     if (var1 != 36) {
                        if (var1 != 47) {
                           if (var1 != 51) {
                              if (var1 != 57) {
                                 if (var1 == 76) {
                                    this.panelBtnKeyClick(188);
                                 }
                              } else {
                                 this.panelBtnKeyClick(1);
                              }
                           } else {
                              this.panelBtnKeyClick(62);
                           }
                        } else {
                           this.panelBtnKeyClick(151);
                        }
                     } else {
                        this.panelBtnKeyClick(59);
                     }
                  } else {
                     this.panelBtnKeyClick(128);
                  }
               } else {
                  this.panelBtnKeyClick(3);
               }
            } else {
               this.panelBtnKeyClick(50);
            }
         } else {
            this.panelBtnKeyClick(1);
         }
      } else {
         this.panelBtnKeyClick(0);
      }

   }

   private void Panelknob0x22() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var1 = volKnobValueRadio - this.mCanBusInfoByte[3];
               if (var1 < 0) {
                  this.PanelKnobClick(48, Math.abs(var1));
               } else if (var1 > 0) {
                  this.PanelKnobClick(47, Math.abs(var1));
               }

               volKnobValueRadio = this.mCanBusInfoByte[3];
            }
         } else {
            var1 = volKnobValue - this.mCanBusInfoByte[3];
            if (var1 < 0) {
               this.PanelKnobClick(7, Math.abs(var1));
            } else if (var1 > 0) {
               this.PanelKnobClick(8, Math.abs(var1));
            }

            volKnobValue = this.mCanBusInfoByte[3];
         }
      } else {
         volKnobValue = 0;
         volKnobValueRadio = 0;
      }

   }

   private String ResolveDaTa(Boolean var1) {
      String var2;
      if (var1) {
         var2 = CommUtil.getStrByResId(this.mContext, "_207_setting_8");
      } else {
         var2 = CommUtil.getStrByResId(this.mContext, "_207_setting_9");
      }

      return var2;
   }

   private int ResolveData(int var1) {
      boolean var2 = DataHandleUtils.getBoolBit0(var1);
      return DataHandleUtils.getBoolBit1(var1) & var2 ? 1 : 0;
   }

   private String ResolveHeatLevel(int var1) {
      return var1 + CommUtil.getStrByResId(this.mContext, "level");
   }

   private void TimeInfo0xC2() {
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isRadarDataChange() {
      byte[] var1 = this.mCanBusInfoByte;
      var1 = Arrays.copyOfRange(var1, 8, var1.length);
      Log.i("_207_MsgMgr", "isRadarDataChange: " + Arrays.toString(var1));
      if (Arrays.equals(this.mRadarData, var1)) {
         return false;
      } else {
         this.mRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackDataChange() {
      byte[] var1 = new byte[2];
      byte[] var2 = this.mCanBusInfoByte;
      var1[0] = var2[6];
      var1[1] = var2[7];
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, 2);
         return true;
      }
   }

   private void panelBtnKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveOutdoorTemperature(int var1) {
      return (double)var1 * 0.5 - 40.0 + this.getTempUnitC(this.mContext);
   }

   private void set0x15CarInfo() {
      int[] var8 = this.mCanBusInfoInt;
      int var7 = var8[2];
      int var2 = var8[3];
      int var3 = var8[4];
      int var5 = var8[5];
      int var6 = var8[10];
      int var4 = var8[11];
      float var1 = (float)(var7 << 8 | var2) * 0.1F;
      String var9 = "invalid";
      String var11;
      if (var1 <= 400.0F) {
         var11 = (new DecimalFormat("0.0")).format((double)var1) + " 1L/100KM";
      } else {
         var11 = "invalid";
      }

      var1 = (float)(var4 | var6 << 8) * 0.1F;
      if (var1 <= 123.0F) {
         var9 = (new DecimalFormat("0.0")).format((double)var1) + " KM/H";
      }

      ArrayList var10 = new ArrayList();
      var10.add(new DriverUpdateEntity(0, 1, var11));
      var10.add(new DriverUpdateEntity(0, 2, DataHandleUtils.rangeNumber(var3 << 8 | var5, 4000) + " KM"));
      var10.add(new DriverUpdateEntity(0, 3, var9));
      this.updateGeneralDriveData(var10);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x72CarBaseData(Context var1) {
      int var3 = this.mVehicleSpeed;
      int var4 = this.mCanBusInfoInt[3];
      if (var3 != var4) {
         this.mVehicleSpeed = var4;
         ArrayList var5 = new ArrayList();
         var5.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

      var3 = this.mKeyStatus;
      var4 = this.mCanBusInfoInt[4];
      if (var3 != var4) {
         this.mKeyStatus = var4;
      }

      if (var4 != 0) {
         if (var4 != 1) {
            if (var4 != 2) {
               if (var4 != 3) {
                  if (var4 != 5) {
                     if (var4 != 6) {
                        if (var4 != 24) {
                           switch (var4) {
                              case 8:
                                 this.realKeyLongClick2(var1, 45);
                                 break;
                              case 9:
                                 this.realKeyLongClick2(var1, 46);
                                 break;
                              case 10:
                                 this.realKeyLongClick2(var1, 2);
                           }
                        } else {
                           this.realKeyLongClick2(var1, 364);
                        }
                     } else {
                        this.realKeyLongClick2(var1, 15);
                     }
                  } else {
                     this.realKeyLongClick2(var1, 14);
                  }
               } else {
                  this.realKeyLongClick2(var1, 3);
               }
            } else {
               this.realKeyLongClick2(var1, 8);
            }
         } else {
            this.realKeyLongClick2(var1, 7);
         }
      } else {
         this.realKeyLongClick2(var1, 0);
      }

      if (this.isTrackDataChange()) {
         byte var2 = this.mCanBusInfoByte[6];
         if (var2 != 0) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2, (byte)0, 0, 140, 16);
         }

         var2 = this.mCanBusInfoByte[7];
         if (var2 != 0) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2, (byte)0, 0, 140, 16);
         }

         Log.i("_207_MsgMgr", "set0x72CarBaseData: GeneralParkData.trackAngle  " + GeneralParkData.trackAngle);
         this.updateParkUi((Bundle)null, var1);
      }

      if (this.isRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var6 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(255, var6[8], var6[9], var6[10], var6[11]);
         var6 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(255, var6[12], var6[13], var6[14], var6[15]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x73AirData(Context var1) {
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(2, 0, this.ResolveHeatLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)))).setValueStr(true));
      var2.add((new SettingUpdateEntity(2, 1, this.ResolveHeatLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)))).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
      this.updateOutDoorTemp(var1, this.resolveOutdoorTemperature(this.mCanBusInfoInt[8]));
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0xCCParkingVentilation() {
      int[] var1 = this.mCanBusInfoInt;
      this.mTime1Hour = var1[4];
      this.mTime1Minute = var1[5];
      this.mTime2Hour = var1[6];
      this.mTime2Minute = var1[7];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, this.ResolveData(this.mCanBusInfoInt[2])));
      var2.add((new SettingUpdateEntity(0, 1, this.ResolveDaTa(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])))).setValueStr(true));
      var2.add((new SettingUpdateEntity(0, 2, this.ResolveDaTa(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])))).setValueStr(true));
      var2.add((new SettingUpdateEntity(0, 3, this.mTime1Hour)).setProgress(this.mTime1Hour).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ^ true));
      var2.add((new SettingUpdateEntity(0, 4, this.mTime1Minute)).setProgress(this.mTime1Minute).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ^ true));
      var2.add((new SettingUpdateEntity(0, 5, this.mTime2Hour)).setProgress(this.mTime2Hour).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ^ true));
      var2.add((new SettingUpdateEntity(0, 6, this.mTime2Minute)).setProgress(this.mTime2Minute).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ^ true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xF0VersionDate() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 21) {
         if (var3 != 194) {
            if (var3 != 204) {
               if (var3 != 240) {
                  if (var3 != 33) {
                     if (var3 != 34) {
                        if (var3 != 114) {
                           if (var3 == 115) {
                              this.set0x73AirData(var1);
                           }
                        } else {
                           this.set0x72CarBaseData(var1);
                        }
                     } else {
                        this.Panelknob0x22();
                     }
                  } else {
                     this.Panelkeys0x21();
                  }
               } else {
                  this.set0xF0VersionDate();
               }
            } else {
               this.set0xCCParkingVentilation();
            }
         } else {
            this.TimeInfo0xC2();
         }
      } else {
         this.set0x15CarInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, 0, 0, (byte)var2, (byte)var3, (byte)var4, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }
}
