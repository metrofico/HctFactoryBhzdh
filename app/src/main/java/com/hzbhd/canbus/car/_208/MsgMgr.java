package com.hzbhd.canbus.car._208;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isWarnFirst;
   private final int DATA_TYPE = 1;
   boolean a;
   boolean b;
   boolean c;
   private byte[] m0xD2Command;
   private byte[] m0xE2Command;
   private byte[] mAirData;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusWarnInfoCopy;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private boolean mFrontStatus;
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private byte[] mRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private byte[] mTrackData;
   private int mVehicleSpeed;
   private int[] mWarningDataNow;
   int memory = 0;

   private String getWarning(boolean var1, int var2) {
      String var3;
      if (var1) {
         var3 = CommUtil.getStrByResId(this.mContext, "_208_warning_" + var2);
      } else {
         var3 = "";
      }

      return var3;
   }

   private boolean isAirDataChange() {
      byte[] var1 = this.mCanBusInfoByte;
      var1[9] = 0;
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         var1 = this.mCanBusInfoByte;
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorChange() {
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

   private boolean isWarningDataChange() {
      if (Arrays.equals(this.mWarningDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mWarningDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWarningMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusWarnInfoCopy)) {
         return true;
      } else {
         this.mCanBusWarnInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isWarnFirst) {
            isWarnFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private String resolverAirTemperature(int var1) {
      if (var1 == 0) {
         return "";
      } else if (var1 == 1) {
         return "LOW";
      } else if (var1 == 255) {
         return "HIGH";
      } else {
         return 18 <= var1 && var1 <= 26 ? var1 + this.getTempUnitC(this.mContext) : "";
      }
   }

   private String resolverOutdoorTemperature(Context var1, int var2) {
      return (double)var2 * 0.5 - 40.0 + this.getTempUnitC(var1);
   }

   private void set0x12CarInfo(Context var1) {
      String var3 = this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6];
      String var4 = this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11];
      ArrayList var5 = new ArrayList();
      var5.add(new DriverUpdateEntity(0, 1, var3 + " L/100KM"));
      var5.add(new DriverUpdateEntity(0, 2, this.mCanBusInfoInt[9] + " L"));
      var5.add(new DriverUpdateEntity(0, 3, var4 + " V"));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      int var2 = this.mCanBusInfoInt[8];
      if (var2 > 0) {
         if (var2 == this.memory) {
            return;
         }

         this.memory = var2;
         ArrayList var6 = new ArrayList();
         var6.add(new WarningEntity(this.getWarning(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]), 1)));
         var6.add(new WarningEntity(this.getWarning(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]), 2)));
         var6.add(new WarningEntity(this.getWarning(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]), 3)));
         var6.add(new WarningEntity(this.getWarning(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]), 4)));
         if (var6.size() > 0) {
            this.startWarningActivity(var1);
         }

         GeneralWarningDataData.dataList = var6;
         this.updateWarningActivity((Bundle)null);
         this.mHandler.postDelayed(new Runnable(this, var1) {
            final MsgMgr this$0;
            final Context val$context;

            {
               this.this$0 = var1;
               this.val$context = var2;
            }

            public void run() {
               if (SystemUtil.isForeground(this.val$context, WarningActivity.class.getName())) {
                  this.this$0.finishActivity();
               }

            }
         }, 5000L);
      }

   }

   private void set0x13ParkingVentilation() {
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      int var1 = var5[3];
      int var4 = var5[10];
      int var3 = var5[11];
      ArrayList var6 = new ArrayList();
      var6.add(new DriverUpdateEntity(0, 4, (var2 << 8 | var1) + " KM"));
      var6.add(new DriverUpdateEntity(0, 5, (var3 | var4 << 8) + " RPM"));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x42RadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.mDisableData2 = 255;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRightRadarLocationData(255, var2[2], var2[3], var2[4], var2[5]);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setLeftRadarLocationData(255, var2[6], var2[7], var2[8], var2[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x47MasterAssistData(Context var1) {
      this.a = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]);
      this.b = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15]);
      this.c = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15]);
      ArrayList var2 = new ArrayList();
      var2.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15])));
      var2.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15])));
      var2.add(new PanoramicBtnUpdateEntity(2, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15])));
      GeneralParkData.dataList = var2;
      this.updateParkUi((Bundle)null, var1);
   }

   private void set0x72CarBaseData(Context var1) {
      int var2 = this.mVehicleSpeed;
      int var3 = this.mCanBusInfoInt[3];
      if (var2 != var3) {
         this.mVehicleSpeed = var3;
         ArrayList var4 = new ArrayList();
         var4.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
         this.updateGeneralDriveData(var4);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

      var3 = this.mKeyStatus;
      var2 = this.mCanBusInfoInt[4];
      if (var3 != var2) {
         this.mKeyStatus = var2;
      }

      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 5) {
                     if (var2 != 6) {
                        if (var2 != 15) {
                           switch (var2) {
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
                           this.realKeyLongClick2(var1, 187);
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
         if (this.mCanBusInfoInt[6] != 0) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[6], (byte)0, 0, 255, 16);
         }

         if (this.mCanBusInfoInt[7] != 0) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte)0, 0, 255, 16);
         }

         this.updateParkUi((Bundle)null, var1);
      }

      if (this.isRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.mDisableData2 = 255;
         int[] var5 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(255, var5[8], var5[9], var5[10], var5[11]);
         var5 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(255, var5[12], var5[13], var5[14], var5[15]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x73AirData(Context var1) {
      if (this.isAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.auto_2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         Log.i("cbc", "AC Date: " + GeneralAirData.ac);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         GeneralAirData.front_left_temperature = this.resolverAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolverAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
         GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
         this.updateOutDoorTemp(var1, this.resolverOutdoorTemperature(var1, this.mCanBusInfoInt[8]));
         this.updateAirActivity(var1, 1001);
      }

      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9]);
      if (this.isDoorChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0xF0VersionDate() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      var1 = this.m0xE2Command;
      if (var1 != null) {
         CanbusMsgSender.sendMsg(var1);
      }

      var1 = this.m0xD2Command;
      if (var1 != null) {
         CanbusMsgSender.sendMsg(var1);
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -29, 0}, var1), 15));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -29, 0}, var1), 15));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 18) {
         if (var3 == 19) {
            this.set0x13ParkingVentilation();
            return;
         }

         if (var3 == 66) {
            this.set0x42RadarData(var1);
            return;
         }

         if (var3 != 71) {
            if (var3 != 240) {
               if (var3 != 114) {
                  if (var3 == 115) {
                     this.set0x73AirData(var1);
                     return;
                  }
               } else {
                  this.set0x72CarBaseData(var1);
               }

               return;
            } else {
               this.set0xF0VersionDate();
               return;
            }
         }

         this.set0x47MasterAssistData(var1);
      }

      this.set0x12CarInfo(var1);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.m0xE2Command = new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), this.m0xE2Command);
      byte var14 = 6;
      if (var7 != 6) {
         if (var7 == 1) {
            var14 = 7;
         } else {
            var14 = 0;
         }
      }

      var11 = String.format("%03d/%03d     ", var5, var6);
      var1 = (byte)var14;
      byte[] var15 = var11.getBytes();
      this.m0xD2Command = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var15);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), this.m0xD2Command);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public Activity getCurrentActivity() {
      return this.getActivity();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      byte var26;
      if (var1 == 9) {
         var26 = 14;
      } else {
         var26 = 13;
      }

      var11 = String.format("%03d/%03d     ", var9 * 256 + var3, var4);
      var1 = (byte)var26;
      byte[] var25 = var11.getBytes();
      this.m0xD2Command = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), this.m0xD2Command);
      var25 = String.format("%02d:%02d:%02d    ", var5, var6, var7).getBytes();
      this.m0xE2Command = DataHandleUtils.byteMerger(new byte[]{22, -30, 0}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), this.m0xE2Command);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var9;
      label34: {
         super.radioInfoChange(var1, var2, var3, var4, var5);
         boolean var8 = var2.contains("FM1");
         var4 = "KHz";
         if (var8) {
            var9 = 1;
         } else if (var2.contains("FM2")) {
            var9 = 2;
         } else if (var2.contains("FM3")) {
            var9 = 3;
         } else {
            if (var2.contains("AM1")) {
               var9 = 4;
               var2 = var4;
               break label34;
            }

            if (var2.contains("AM2")) {
               var9 = 5;
               var2 = var4;
               break label34;
            }

            var9 = 0;
         }

         var2 = "MHz";
      }

      var2 = var3 + var2;
      int var7 = var2.length();

      for(var5 = 0; var5 < 12 - var7; ++var5) {
         var2 = var2 + " ";
      }

      byte var6 = (byte)var9;
      byte[] var10 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var10));
      var10 = "Radio Media!".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -30, 0}, var10));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      byte var19;
      if (var1 == 9) {
         var19 = 14;
      } else {
         var19 = 13;
      }

      var8 = String.format("%03d/%03d     ", var9 * 256 + var3, var4);
      var1 = (byte)var19;
      byte[] var18 = var8.getBytes();
      this.m0xD2Command = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), this.m0xD2Command);
      var18 = String.format("%02d:%02d:%02d    ", var5, var6, var7).getBytes();
      this.m0xE2Command = DataHandleUtils.byteMerger(new byte[]{22, -30, 0}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), this.m0xE2Command);
   }
}
