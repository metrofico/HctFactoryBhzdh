package com.hzbhd.canbus.car._315;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   public static String UPDATE_SETTING_ACTION;
   private final int DATA_TYPE = 1;
   private final String TAG = "_315_MsgMgr";
   private byte[] m0x41Data = new byte[4];
   private BroadcastReceiver mBroadcastReceiver;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mDifferent;
   private List mTireInfoList = new ArrayList();
   private List mTireUpdateEntityList = new ArrayList();
   private List mTireWarningList = new ArrayList();

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

   private boolean getIsUseFunit() {
      Context var2 = this.mContext;
      boolean var1 = false;
      if (SharePreUtil.getIntValue(var2, "share_pre_is_use_f_unit", 0) == 1) {
         var1 = true;
      }

      return var1;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (36 == var1) {
         var2 = "LO";
      } else if (64 == var1) {
         var2 = "HI";
      } else if (255 == var1) {
         var2 = this.mContext.getString(2131769395);
      } else if (37 <= var1 && var1 <= 63) {
         if (this.getIsUseFunit()) {
            var2 = this.tempCToTempF((double)(var1 - 1) * 0.5 + 0.5) + this.getTempUnitF(this.mContext);
         } else {
            var2 = (double)(var1 - 1) * 0.5 + 0.5 + this.getTempUnitC(this.mContext);
         }
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      double var3 = (double)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      double var1 = var3;
      if (var3 >= 127.0) {
         var1 = 127.0;
      }

      var3 = var1;
      if (this.getIsUseFunit()) {
         var3 = this.tempCToTempF(var1);
      }

      String var5;
      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var5 = var3 + "";
      } else {
         var5 = "-" + var3;
      }

      if (this.getIsUseFunit()) {
         var5 = var5 + this.getTempUnitF(this.mContext);
      } else {
         var5 = var5 + this.getTempUnitC(this.mContext);
      }

      return var5;
   }

   private String resolveTempLevel(int var1) {
      String var3 = "";
      String var2;
      if (1 == var1) {
         var2 = "LO";
      } else if (15 == var1) {
         var2 = "HI";
      } else {
         var2 = var3;
         if (2 <= var1) {
            var2 = var3;
            if (var1 <= 14) {
               var2 = var1 + "";
            }
         }
      }

      return var2;
   }

   private void set0x20WheelKeyData(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      short var2 = 2;
      switch (var3[2]) {
         case 1:
            var2 = 7;
            break;
         case 2:
            var2 = 8;
            break;
         case 3:
            var2 = 46;
            break;
         case 4:
            var2 = 45;
            break;
         case 5:
         case 8:
         default:
            var2 = 0;
            break;
         case 6:
            var2 = 3;
         case 7:
            break;
         case 9:
            var2 = 14;
            break;
         case 10:
            var2 = 202;
      }

      this.realKeyLongClick1(var1, var2, var3[3]);
   }

   private void set0x21InterfaceKeyData(Context var1) {
      int var2;
      int[] var3;
      label68: {
         var3 = this.mCanBusInfoInt;
         var2 = 2;
         switch (var3[2]) {
            case 1:
               var2 = 45;
               break label68;
            case 2:
               var2 = 46;
            case 3:
               break label68;
            case 4:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 59;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 185;
                  break label68;
               }
               break;
            case 5:
            case 8:
               var2 = 3;
               break label68;
            case 6:
               var2 = 8;
               break label68;
            case 7:
               var2 = 7;
               break label68;
            case 9:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 52;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 33;
                  break label68;
               }
               break;
            case 10:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 50;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 34;
                  break label68;
               }
               break;
            case 11:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 58;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 35;
                  break label68;
               }
               break;
            case 12:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 14;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 36;
                  break label68;
               }
               break;
            case 13:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 128;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 37;
                  break label68;
               }
               break;
            case 14:
               var2 = this.mDifferent;
               if (var2 == 2) {
                  var2 = 129;
                  break label68;
               }

               if (var2 == 1) {
                  var2 = 38;
                  break label68;
               }
         }

         var2 = 0;
      }

      this.realKeyLongClick1(var1, var2, var3[3]);
   }

   private void set0x22RearRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], this.mCanBusInfoByte[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x23FrontRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], this.mCanBusInfoByte[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24AirData() {
      int[] var2 = this.mCanBusInfoInt;
      var2[2] &= 239;
      if (this.isDataChange(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         LogUtil.showLog("power:" + GeneralAirData.power);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         LogUtil.showLog("in_out_cycle:" + GeneralAirData.in_out_cycle);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
         GeneralAirData.front_wind_level = var1;
         LogUtil.showLog("windSpeed:" + var1);
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }

         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 7));
         } else {
            GeneralAirData.front_left_temperature = this.resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
         }

         if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7));
         } else {
            GeneralAirData.front_right_temperature = this.resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
         }

         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x28BaseData() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void set0x29TrackData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var2 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte)var2[3], (byte)var2[2], 0, 5570, 16);
         Log.i("cbc", "set0x29 !!!!!!!!!!!!!!!!!!!! " + GeneralParkData.trackAngle);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x36AirData() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void set0x38TirePressureData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         Iterator var4 = this.mTireUpdateEntityList.iterator();

         while(var4.hasNext()) {
            int var1 = ((TireUpdateEntity)var4.next()).getWhichTire();
            List var5 = (List)this.mTireInfoList.get(var1);
            var5.clear();
            int var2 = this.mCanBusInfoInt[var1 + 2];
            String var3 = CommUtil.getStrByResId(this.mContext, "_284_tire_temperature_default");
            if (var2 != 255) {
               var3 = var2 - 40 + this.getTempUnitC(this.mContext);
            }

            var5.add(var3);
            var2 = this.mCanBusInfoInt[var1 + 6];
            var3 = CommUtil.getStrByResId(this.mContext, "_284_tire_pressure_default");
            if (var2 != 255) {
               var3 = (new DecimalFormat("0.0")).format((double)((float)var2 * 1.373F)) + "KPa";
            }

            var5.add(var3);
            var5.addAll((Collection)this.mTireWarningList.get(var1));
            ((TireUpdateEntity)this.mTireUpdateEntityList.get(var1)).setList(var5);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void set0x39TirePressureWarning() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         Iterator var4 = this.mTireUpdateEntityList.iterator();

         while(var4.hasNext()) {
            TireUpdateEntity var5 = (TireUpdateEntity)var4.next();
            int var3 = var5.getWhichTire();
            ArrayList var7 = new ArrayList();
            var7.addAll((Collection)this.mTireInfoList.get(var3));
            List var6 = (List)this.mTireWarningList.get(var3);
            var6.clear();
            int var1 = 0;
            var5.setTireStatus(0);
            int var2 = 2;
            if (var3 != 0) {
               if (var3 != 1) {
                  if (var3 != 2) {
                     if (var3 == 3) {
                        var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 3);
                     }
                  } else {
                     var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 3);
                  }
               } else {
                  var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 3);
               }
            } else {
               var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
            }

            for(; var2 >= 0; --var2) {
               if ((var1 >> var2 & 1) == 1) {
                  var6.add(CommUtil.getStrByResId(this.mContext, "_315_tire_alarm_" + var2));
                  var5.setTireStatus(1);
               }
            }

            if (var6.size() == 0) {
               var6.add(CommUtil.getStrByResId(this.mContext, "_315_tire_normal"));
            }

            while(3 > var6.size()) {
               var6.add("");
            }

            var7.addAll(var6);
            var5.setList(var7);
         }

         GeneralTireData.dataList = this.mTireUpdateEntityList;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void set0x7fVersionData() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setSettingData() {
      byte var1 = this.getIsUseFunit();
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, Integer.valueOf(var1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSystemInfoData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
         byte[] var8 = this.mCanBusInfoByte;
         byte var7 = var8[4];
         byte var2 = var8[5];
         byte var5 = var8[6];
         byte var6 = var8[7];
         byte var3 = var8[8];
         byte var1 = var8[9];
         ArrayList var9 = new ArrayList();
         var9.add((new SettingUpdateEntity(0, 0, var4)).setProgress(var4 - 1));
         var9.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var9.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         var9.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[3]));
         var9.add(new SettingUpdateEntity(0, 4, Integer.valueOf(var7)));
         var9.add((new SettingUpdateEntity(0, 5, Integer.valueOf(var2))).setProgress(var2));
         var9.add((new SettingUpdateEntity(0, 6, Integer.valueOf(var5))).setProgress(var5));
         var9.add(new SettingUpdateEntity(0, 7, Integer.valueOf(var6)));
         var9.add(new SettingUpdateEntity(0, 8, Integer.valueOf(var3)));
         var9.add((new SettingUpdateEntity(0, 9, Integer.valueOf(var1))).setProgress(var1));
         this.updateGeneralSettingData(var9);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private double tempCToTempF(double var1) {
      LogUtil.showLog("tempCToTempF:" + var1);

      try {
         DecimalFormat var3 = new DecimalFormat("0.0");
         var1 = Double.valueOf(var3.format(var1 * 1.8 + 32.0));
         return var1;
      } catch (Exception var4) {
         LogUtil.showLog("Exception:" + var4);
         return 0.0;
      }
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();

      for(int var2 = 0; var2 < 4; ++var2) {
         this.mTireUpdateEntityList.add(new TireUpdateEntity(var2, 0, new String[0]));
         ArrayList var3 = new ArrayList();
         var3.add(CommUtil.getStrByResId(var1, "_284_tire_temperature_default"));
         var3.add(CommUtil.getStrByResId(var1, "_284_tire_pressure_default"));
         this.mTireInfoList.add(var3);
         this.mTireWarningList.add(new ArrayList());
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -58, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -58, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 40) {
         if (var3 != 41) {
            if (var3 != 54) {
               if (var3 != 65) {
                  if (var3 != 127) {
                     if (var3 != 56) {
                        if (var3 != 57) {
                           switch (var3) {
                              case 32:
                                 this.set0x20WheelKeyData(var1);
                                 break;
                              case 33:
                                 this.set0x21InterfaceKeyData(var1);
                                 break;
                              case 34:
                                 this.set0x22RearRadarData(var1);
                                 break;
                              case 35:
                                 this.set0x23FrontRadarData(var1);
                                 break;
                              case 36:
                                 this.set0x24AirData();
                           }
                        } else {
                           this.set0x39TirePressureWarning();
                        }
                     } else {
                        this.set0x38TirePressureData();
                     }
                  } else {
                     this.set0x7fVersionData();
                  }
               } else {
                  this.setSystemInfoData();
               }
            } else {
               this.set0x36AirData();
            }
         } else {
            this.set0x29TrackData(var1);
         }
      } else {
         if (this.isDoorMsgRepeat(var2)) {
            return;
         }

         this.set0x28BaseData();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)DataHandleUtils.rangeNumber(var1, 30)});
   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 3) {
         return false;
      } else {
         this.realKeyClick(var1, 134);
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -55, (byte)var6, (byte)var5, (byte)var10});
   }

   byte[] get0x41Data() {
      return this.m0x41Data;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
      BroadcastReceiver var2 = new BroadcastReceiver(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onReceive(Context var1, Intent var2) {
            this.this$0.setSettingData();
         }
      };
      this.mBroadcastReceiver = var2;
      var1.registerReceiver(var2, new IntentFilter(UPDATE_SETTING_ACTION));
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 3;
      } else {
         var25 = 2;
      }

      var3 = DataHandleUtils.rangeNumber(var9 << 8 | var3, 255);
      var1 = (byte)var25;
      var5 = (byte)(var3 >> 8);
      var2 = (byte)(var3 & 255);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -58, var1, var5, var2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var10 = var2.contains("AM");
      int[] var11 = this.getFreqByteHiLo(var2, var3);
      byte var6 = (byte)var10;
      byte var7 = (byte)var1;
      byte var9 = (byte)var11[0];
      byte var8 = (byte)var11[1];
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -58, var6, 0, 0, 0, 0, 0, 0, 0, 0, var7, 0, var9, var8, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -58, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   void updateSettingItem(int var1, int var2, Object var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 3;
      } else {
         var18 = 2;
      }

      var3 = DataHandleUtils.rangeNumber(var9 << 8 | var3, 255);
      var5 = (byte)var18;
      var2 = (byte)(var3 >> 8);
      var1 = (byte)(var3 & 255);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -58, var5, var2, var1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }
}
