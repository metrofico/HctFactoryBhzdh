package com.hzbhd.canbus.car._304;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._304.activity.AirActivity;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   public static final String SHARE_304_WARN_COUNT = "share_304_warn_count";
   public static final int VEHICLE_TYPE_HIGH_CONFIG = 1;
   public static final int VEHICLE_TYPE_LOW_CONFIG = 0;
   private final int DATA_TYPE = 1;
   private final int EPB_STATUS_APPLYING = 4;
   private final int EPB_STATUS_COMPLETELYRELEASED = 3;
   private final int EPB_STATUS_PARKAPPLIED = 1;
   private final int EPB_STATUS_RELEASED = 2;
   private final int EPB_STATUS_RELEASING = 5;
   private final boolean IS_SHOW_TRACK = true;
   private final int SHIFT_POSITION_DRIVE = 3;
   private final int SHIFT_POSITION_ECO = 4;
   private final int SHIFT_POSITION_NEUTRAL = 2;
   private final int SHIFT_POSITION_PARKING = 0;
   private final int SHIFT_POSITION_REVERSE = 1;
   private final String TAG = "_304_MsgMgr";
   private final int TRACK_MAX = 510;
   private final int TRACK_MID = 7200;
   private String YYY_VOICE_BROADCAST_11 = "动力系统准备就绪";
   private String YYY_VOICE_BROADCAST_12 = "动力电池电量低，请充电";
   private String YYY_VOICE_BROADCAST_13 = "请系好安全带";
   private String YYY_VOICE_BROADCAST_14 = "您已超速，请减速慢行";
   private String YYY_VOICE_BROADCAST_15 = "车门未关，请注意";
   private String YYY_VOICE_BROADCAST_2 = "请拉起电子手刹";
   private String YYY_VOICE_BROADCAST_3 = "小灯未关闭";
   private String YYY_VOICE_BROADCAST_4 = "请踩制动启动";
   private String YYY_VOICE_BROADCAST_5 = "请挂驻车档启动";
   private String YYY_VOICE_BROADCAST_6 = "请挂驻车档";
   private String YYY_VOICE_BROADCAST_7 = "电机过热，请注意";
   private int mBreakSystemFaultData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray;
   private int mDiiferentId;
   private int mEachId;
   private int mEpsFualtStatus;
   private boolean mIsShiftParking;
   private boolean mIsVoiceBcFinished = false;
   private HashMap mLocationMap = new HashMap();
   private List mOnWindLevelChangeListenerList = new ArrayList();
   CusPanoramicView mPanoramicView;
   private int[] mRadarFaults;
   private int[] mRadarInfos;
   private WarningManger mWmAirInfo;
   private WarningManger mWmBaseInfo;
   private WarningManger mWmBattery;
   private WarningManger mWmBrakeSystem;
   private WarningManger mWmEpb;
   private WarningManger mWmOther;
   private WarningManger mWmPowerSystem;
   private WarningManger mWmRadar;
   private WarningManger mWmTpmsInfo;
   private WarningManger mWmTrackInfo;
   private SparseArray voiceBoardcastItemArray;

   private List addAll(Context var1, List... var2) {
      ArrayList var8 = new ArrayList();
      int var6 = var2.length;
      int var5 = 0;

      int var3;
      int var4;
      for(var4 = 0; var5 < var6; var4 = var3) {
         List var7 = var2[var5];
         var3 = var4;
         if (var7.size() > 1) {
            var8.addAll(var7);
            var3 = var4 + (var7.size() - 1);
         }

         ++var5;
      }

      SharePreUtil.setIntValue(var1, "share_304_warn_count", var4);
      return var8;
   }

   private boolean equal(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1.equals(var2[var3])) {
            return true;
         }
      }

      return false;
   }

   private String getFualt(Context var1, int var2) {
      return var1.getString(var2) + var1.getString(2131767718);
   }

   private CusPanoramicView getMyPanoramicView(Context var1) {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (CusPanoramicView)UiMgrFactory.getCanUiMgr(var1).getCusPanoramicView(var1);
      }

      return this.mPanoramicView;
   }

   private void initVoiceBoardcastItems() {
      Log.i("_304_MsgMgr", "initVoiceBoardcastItems: ");
      SparseArray var1 = new SparseArray();
      this.voiceBoardcastItemArray = var1;
      var1.put(2, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_2));
      this.voiceBoardcastItemArray.append(3, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_3));
      this.voiceBoardcastItemArray.append(4, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_4));
      this.voiceBoardcastItemArray.append(5, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_5));
      this.voiceBoardcastItemArray.append(6, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_6));
      this.voiceBoardcastItemArray.append(7, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_7));
      this.voiceBoardcastItemArray.append(19, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_11));
      this.voiceBoardcastItemArray.append(20, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_12));
      this.voiceBoardcastItemArray.append(21, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_13));
      this.voiceBoardcastItemArray.append(22, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_14));
      this.voiceBoardcastItemArray.append(23, new VoiceBoardcastItem(this, this.YYY_VOICE_BROADCAST_15));
   }

   private void initWarningData(Context var1) {
      this.mWmRadar = new WarningManger(this, var1.getString(2131761999), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(7, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761956)));
            this.alarmArray.append(6, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761957)));
            this.alarmArray.append(5, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762005)));
            this.alarmArray.append(4, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762004)));
            this.alarmArray.append(3, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762006)));
            this.alarmArray.append(2, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762007)));
            this.alarmArray.append(1, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761993)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            for(int var2 = 7; var2 > 0; --var2) {
               if ((var1[8] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            this.setEntityList(var3);
         }
      };
      this.mWmBaseInfo = new WarningManger(this, var1.getString(2131761974), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(6, new WarningEntity("\t" + this.val$context.getString(2131761932)));
            this.alarmArray.append(4, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762010)));
            this.alarmArray.append(3, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761972)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            for(int var2 = 3; var2 < 7; ++var2) {
               if (var2 != 5 && (var1[5] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            this.setEntityList(var3);
         }
      };
      this.mWmTpmsInfo = new WarningManger(this, var1.getString(2131762028), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(7, new WarningEntity("\t" + this.val$context.getString(2131762009) + this.val$context.getString(2131762025) + this.val$context.getString(2131762019)));
            this.alarmArray.append(6, new WarningEntity("\t" + this.val$context.getString(2131761971) + this.val$context.getString(2131762025) + this.val$context.getString(2131762019)));
            this.alarmArray.append(5, new WarningEntity("\t" + this.val$context.getString(2131762008) + this.val$context.getString(2131762025) + this.val$context.getString(2131762019)));
            this.alarmArray.append(4, new WarningEntity("\t" + this.val$context.getString(2131761970) + this.val$context.getString(2131762025) + this.val$context.getString(2131762019)));
            this.alarmArray.append(3, new WarningEntity("\t" + this.val$context.getString(2131762029)));
            this.alarmArray.append(19, new WarningEntity("\t" + this.val$context.getString(2131762009) + this.val$context.getString(2131762024) + this.val$context.getString(2131762022)));
            this.alarmArray.append(18, new WarningEntity("\t" + this.val$context.getString(2131761971) + this.val$context.getString(2131762024) + this.val$context.getString(2131762022)));
            this.alarmArray.append(17, new WarningEntity("\t" + this.val$context.getString(2131762008) + this.val$context.getString(2131762024) + this.val$context.getString(2131762022)));
            this.alarmArray.append(16, new WarningEntity("\t" + this.val$context.getString(2131761970) + this.val$context.getString(2131762024) + this.val$context.getString(2131762022)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            int var2;
            for(var2 = 3; var2 < 8; ++var2) {
               if ((var1[2] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            for(var2 = 0; var2 < 4; ++var2) {
               if ((var1[3] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2 + 16));
               }
            }

            this.setEntityList(var3);
         }
      };
      this.mWmAirInfo = new WarningManger(this, var1.getString(2131768606), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(0, new WarningEntity("\t" + this.val$context.getString(2131761944)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var2 = new ArrayList();
            if (DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanBusInfoInt[6], 4, 2) == 2) {
               var2.add((WarningEntity)this.alarmArray.get(0));
            }

            this.setEntityList(var2);
         }
      };
      this.mWmTrackInfo = new WarningManger(this, var1.getString(2131761949), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(1, new WarningEntity("\t" + this.val$context.getString(2131761952)));
            this.alarmArray.append(2, new WarningEntity("\t" + this.val$context.getString(2131761950)));
            this.alarmArray.append(3, new WarningEntity("\t" + this.val$context.getString(2131761951)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            WarningEntity var3 = (WarningEntity)this.alarmArray.get(DataHandleUtils.getIntFromByteWithBit(this.this$0.mCanBusInfoInt[4], 6, 2));
            if (var3 != null) {
               ArrayList var2 = new ArrayList();
               var2.add(var3);
               this.setEntityList(var2);
            }

         }
      };
      this.mWmPowerSystem = new WarningManger(this, var1.getString(2131761996), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(6, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762033)));
            this.alarmArray.append(7, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761987)));
            this.alarmArray.append(0, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761947)));
            this.alarmArray.append(1, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761986)));
            this.alarmArray.append(2, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131762031)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            int var2;
            for(var2 = 6; var2 < 8; ++var2) {
               if ((var1[2] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            for(var2 = 0; var2 < 3; ++var2) {
               if ((var1[3] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            this.setEntityList(var3);
         }
      };
      this.mWmBrakeSystem = new WarningManger(this, var1.getString(2131761934), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(7, new WarningEntity("\t" + this.val$context.getString(2131761934)));
            this.alarmArray.append(5, new WarningEntity("\t" + this.val$context.getString(2131761953)));
            this.alarmArray.append(4, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761930)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            for(int var2 = 4; var2 < 8; ++var2) {
               if (var2 != 6 && (var1[2] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            this.setEntityList(var3);
         }
      };
      this.mWmBattery = new WarningManger(this, var1.getString(2131761929), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(7, new WarningEntity("\t" + this.val$context.getString(2131761929)));
            this.alarmArray.append(6, new WarningEntity("\t" + this.val$context.getString(2131761968)));
            this.alarmArray.append(5, new WarningEntity("\t" + this.val$context.getString(2131761937)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            for(int var2 = 5; var2 < 8; ++var2) {
               if ((var1[2] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            this.setEntityList(var3);
         }
      };
      this.mWmOther = new WarningManger(this, var1.getString(2131761991), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(6, new WarningEntity("\t" + this.val$context.getString(2131761928)));
            this.alarmArray.append(5, new WarningEntity("\t" + this.val$context.getString(2131762018)));
            this.alarmArray.append(4, new WarningEntity("\t" + this.this$0.getFualt(this.val$context, 2131761916)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList((int[])null);
            ArrayList var3 = new ArrayList();

            for(int var2 = 4; var2 < 7; ++var2) {
               if (var2 != 5 && (var1[2] & 1 << var2) != 0) {
                  var3.add((WarningEntity)this.alarmArray.get(var2));
               }
            }

            if (!DataHandleUtils.getBoolBit5(var1[2])) {
               var3.add((WarningEntity)this.alarmArray.get(5));
            }

            this.setEntityList(var3);
         }
      };
      this.mWmEpb = new WarningManger(this, var1.getString(2131761948), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void initAlarmArray() {
            this.alarmArray.append(4, new WarningEntity("\t" + this.val$context.getString(2131761948)));
         }

         public void updateWarningList(int[] var1) {
            super.updateWarningList(var1);
            ArrayList var2 = new ArrayList();
            if (DataHandleUtils.getBoolBit4(this.this$0.mCanBusInfoInt[3])) {
               var2.add((WarningEntity)this.alarmArray.get(4));
            }

            this.setEntityList(var2);
         }
      };
   }

   private void set0x1ERearRadarData(Context var1) {
      if (this.mRadarInfos != Arrays.copyOfRange(this.mCanBusInfoInt, 2, 8)) {
         this.mRadarInfos = Arrays.copyOfRange(this.mCanBusInfoInt, 2, 8);
         RadarInfoUtil.mMinIsClose = true;
         this.mLocationMap.put(Constant.RadarLocation.REAR_LEFT, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[2], 3));
         this.mLocationMap.put(Constant.RadarLocation.REAR_MID_LEFT, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[3], 4));
         this.mLocationMap.put(Constant.RadarLocation.REAR_MID_RIGHT, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[4], 4));
         this.mLocationMap.put(Constant.RadarLocation.REAR_RIGHT, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[5], 3));
         this.mLocationMap.put(Constant.RadarLocation.FRONT_RIGHT, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[6], 3));
         this.mLocationMap.put(Constant.RadarLocation.FRONT_LEFT, DataHandleUtils.rangeNumber(this.mCanBusInfoInt[7], 3));
         MyGeneralData.mRadarLocationMap = this.mLocationMap;
         this.updateRadar(var1);
      }

      if (this.mRadarFaults != Arrays.copyOfRange(this.mCanBusInfoInt, 8, 10)) {
         this.mRadarFaults = Arrays.copyOfRange(this.mCanBusInfoInt, 8, 10);
         this.mWmRadar.updateWarningList(this.mCanBusInfoInt);
         this.updateWarnActivity(var1);
      }

   }

   private void set0x24VehicleBaseInfo(Context var1) {
      this.mWmBaseInfo.updateWarningList(this.mCanBusInfoInt);
      this.updateWarnActivity(var1);
   }

   private void set0x25TpmsData(Context var1) {
      this.mWmTpmsInfo.updateWarningList(this.mCanBusInfoInt);
      this.updateWarnActivity(var1);
   }

   private void set0x28AirData(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      var2[4] = 0;
      var2[7] = 0;
      if (!this.isAirMsgRepeat(var2)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         MyGeneralData.mBlowMode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 3);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         MyGeneralData.ptc = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         MyGeneralData.mTemperatureGear = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 5), 1, 15);
         MyGeneralData.mTemperature = MyGeneralData.mTemperatureGear + 17 + this.getTempUnitC(var1);
         MyGeneralData.etc_power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
         MyGeneralData.etc_status = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 2);
         MyGeneralData.etc_close_ptc_ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
         MyGeneralData.compressor_status = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
         MyGeneralData.evaporator_temperature = (new DecimalFormat("0.0")).format((double)((float)this.mCanBusInfoInt[7] * 0.1F)) + this.getTempUnitC(var1);
         this.updateAirActivity(var1);
         Iterator var5 = this.mOnWindLevelChangeListenerList.iterator();

         while(var5.hasNext()) {
            OnWindLevelChangeListener var3 = (OnWindLevelChangeListener)var5.next();
            if (var3 != null) {
               try {
                  var3.onChange();
               } catch (Exception var4) {
                  Log.i("ljqdebug", "set0x28AirData: " + var4.toString());
               }
            }
         }

         this.mWmAirInfo.updateWarningList(this.mCanBusInfoInt);
         this.updateWarnActivity(var1);
      }
   }

   private void set0x29TrackData(Context var1) {
      byte[] var4 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var4[3], var4[2], 7200, 12300, 16);
      this.updateParkUi((Bundle)null, var1);
      int var3 = this.mEpsFualtStatus;
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[4];
      if (var3 != var2) {
         this.mEpsFualtStatus = var2;
         this.mWmTrackInfo.updateWarningList(var5);
         this.updateWarnActivity(var1);
      }

   }

   private void set0x50PowerSystemData(Context var1) {
   }

   private void set0x51brakeSystemData(Context var1) {
      int var2 = this.mBreakSystemFaultData;
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[2];
      if (var2 != var3) {
         this.mBreakSystemFaultData = var3;
         this.mWmBrakeSystem.updateWarningList(var4);
         this.updateWarnActivity(var1);
      }

      int[] var5 = this.mCanBusInfoInt;
      var2 = var5[2];
      if ((var5[3] | var2 << 8) * 5626 / 100000 > 20) {
         FutureUtil.instance.setParking(false);
      }

   }

   private void set0x52BatteryData(Context var1) {
      this.mWmBattery.updateWarningList(this.mCanBusInfoInt);
      this.updateWarnActivity(var1);
   }

   private void set0x53WindowLightData(Context var1) {
      MyGeneralData.mIsRoofInit = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      MyGeneralData.mRoofStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
      MyGeneralData.mIsRoofAntipinchActive = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      MyGeneralData.mRoofPosition = this.mCanBusInfoInt[3];
      MyGeneralData.mIsAtmosLampOn = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      MyGeneralData.mAtmosLampR = this.mCanBusInfoInt[5];
      MyGeneralData.mAtmosLampG = this.mCanBusInfoInt[6];
      MyGeneralData.mAtmosLampB = this.mCanBusInfoInt[7];
      MyGeneralData.mAtmosLampBrightness = this.mCanBusInfoInt[8];
      String var5;
      if (MyGeneralData.mIsRoofInit) {
         var5 = var1.getString(2131761967);
      } else {
         var5 = var1.getString(2131759954);
      }

      int var2 = MyGeneralData.mRoofStatus;
      String var4 = "";
      String var3;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  var3 = "";
               } else {
                  var3 = var1.getString(2131759850);
               }
            } else {
               var3 = var1.getString(2131757430);
            }
         } else {
            var3 = var1.getString(2131761943);
         }
      } else {
         var3 = var1.getString(2131761990);
      }

      String var6;
      if (MyGeneralData.mIsRoofAntipinchActive) {
         var6 = var1.getString(2131760626);
      } else {
         var6 = var1.getString(2131761988);
      }

      if (MyGeneralData.mRoofPosition >= 0 && MyGeneralData.mRoofPosition <= 99) {
         var4 = var1.getString(2131761966);
      } else if (MyGeneralData.mRoofPosition == 100) {
         var4 = var1.getString(2131761965);
      } else if (MyGeneralData.mRoofPosition >= 101 && MyGeneralData.mRoofPosition <= 200) {
         var4 = var1.getString(2131761918);
      } else if (MyGeneralData.mRoofPosition == 255) {
         var4 = var1.getString(2131762030);
      }

      ArrayList var7 = new ArrayList();
      var7.add(new DriverUpdateEntity(5, 0, var5));
      var7.add(new DriverUpdateEntity(5, 1, var3));
      var7.add(new DriverUpdateEntity(5, 2, var6));
      var7.add(new DriverUpdateEntity(5, 3, var4));
      GeneralDriveData.dataList = var7;
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x54OtherFaultData(Context var1) {
      this.mWmOther.updateWarningList(this.mCanBusInfoInt);
      this.updateWarnActivity(var1);
   }

   private void set0x55VoiceBoardcast() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         for(int var1 = 0; var1 < 2; ++var1) {
            for(int var2 = 0; var2 < 8; ++var2) {
               int var4 = var1 << 4 | var2;
               if (this.voiceBoardcastItemArray.get(var4) != null) {
                  int var3 = this.mCanBusInfoInt[var1 + 2];
                  boolean var5 = true;
                  if ((var3 & 1 << var2) == 0) {
                     var5 = false;
                  }

                  ((VoiceBoardcastItem)this.voiceBoardcastItemArray.get(var4)).setPlay(var5);
               }
            }
         }
      }

   }

   private void set0x56EpbInfo(Context var1) {
      Log.i("_304_MsgMgr", "set0x56EpbInfo: position <--> " + MyGeneralData.mShiftLevelPosition + ", mIsShiftParking <--> " + this.mIsShiftParking);
      Log.i("_304_MsgMgr", "set0x56EpbInfo: epbStatus <--> " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 3));
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 3);
      boolean var3 = true;
      boolean var4;
      if (var2 == 2) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!this.mIsShiftParking || !var4) {
         var3 = false;
      }

      ((VoiceBoardcastItem)this.voiceBoardcastItemArray.get(2)).setPlay(var3);
      this.mWmEpb.updateWarningList(this.mCanBusInfoInt);
      this.updateWarnActivity(var1);
   }

   private void set0x57Vcu255Data(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      boolean var3 = false;
      MyGeneralData.mShiftLevelPosition = DataHandleUtils.getIntFromByteWithBit(var2, 0, 3);
      Log.i("_304_MsgMgr", "set0x57Vcu255Data: position <--> " + MyGeneralData.mShiftLevelPosition);
      if (MyGeneralData.mShiftLevelPosition == 0) {
         var3 = true;
      }

      this.mIsShiftParking = var3;
      if (var3) {
         FutureUtil.instance.setParking(true);
      }

      this.mWmPowerSystem.updateWarningList(this.mCanBusInfoInt);
      this.updateWarnActivity(var1);
   }

   private void set0x59Tbox249Data(Context var1) {
   }

   private void set0x7FVersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void startVoiceBoardcastThread() {
      (new Thread(this, this.voiceBoardcastItemArray.size()) {
         final MsgMgr this$0;
         final int val$size;

         {
            this.this$0 = var1;
            this.val$size = var2;
         }

         public void run() {
            super.run();
            int var1 = 0;

            while(true) {
               int var2 = var1;

               label37: {
                  Exception var10000;
                  label43: {
                     boolean var10001;
                     label35: {
                        try {
                           if (var1 < this.val$size) {
                              break label35;
                           }
                        } catch (Exception var6) {
                           var10000 = var6;
                           var10001 = false;
                           break label43;
                        }

                        var2 = 0;
                     }

                     try {
                        VoiceBoardcastItem var3 = (VoiceBoardcastItem)this.this$0.voiceBoardcastItemArray.valueAt(var2);
                        if (var3.isPlay()) {
                           this.this$0.updateVoiceBroadcast((Bundle)null, var3.getInfo());
                           sleep((long)(var3.getInfo().length() * 400));
                        }
                     } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                        break label43;
                     }

                     try {
                        if (SystemClock.elapsedRealtime() % 3000L == 0L) {
                           StringBuilder var8 = new StringBuilder();
                           Log.i("_304_MsgMgr", var8.append("run: alive ").append(SystemClock.elapsedRealtime()).toString());
                        }
                        break label37;
                     } catch (Exception var4) {
                        var10000 = var4;
                        var10001 = false;
                     }
                  }

                  Exception var7 = var10000;
                  Log.i("_304_MsgMgr", "run: error <--> " + var7.toString());
                  return;
               }

               var1 = var2 + 1;
            }
         }
      }).start();
   }

   private void updateAirActivity(Context var1) {
      if (this.getActivity() instanceof AirActivity) {
         this.updateActivity((Bundle)null);
      }

   }

   private void updateRadar(Context var1) {
      this.getMyPanoramicView(var1).refreshRadar();
   }

   private void updateWarnActivity(Context var1) {
      GeneralWarningDataData.dataList = this.addAll(var1, this.mWmRadar.getEntityList(), this.mWmBaseInfo.getEntityList(), this.mWmTpmsInfo.getEntityList(), this.mWmAirInfo.getEntityList(), this.mWmTrackInfo.getEntityList(), this.mWmPowerSystem.getEntityList(), this.mWmBrakeSystem.getEntityList(), this.mWmBattery.getEntityList(), this.mWmOther.getEntityList(), this.mWmEpb.getEntityList());
      this.updateWarningActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mDiiferentId = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      MyGeneralData.mPedestrianRemind = true;
      this.mCanbusDataArray = new SparseArray();
      this.initWarningData(var1);
      this.initVoiceBoardcastItems();
      this.startVoiceBoardcastThread();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 30) {
         if (var3 != 89) {
            if (var3 != 127) {
               if (var3 != 36) {
                  if (var3 != 37) {
                     if (var3 != 40) {
                        if (var3 != 41) {
                           if (var3 != 81) {
                              if (var3 != 82) {
                                 switch (var3) {
                                    case 84:
                                       this.set0x54OtherFaultData(var1);
                                       break;
                                    case 85:
                                       this.set0x55VoiceBoardcast();
                                       break;
                                    case 86:
                                       this.set0x56EpbInfo(var1);
                                       break;
                                    case 87:
                                       this.set0x57Vcu255Data(var1);
                                 }
                              } else {
                                 this.set0x52BatteryData(var1);
                              }
                           } else {
                              this.set0x51brakeSystemData(var1);
                           }
                        } else {
                           this.set0x29TrackData(var1);
                        }
                     } else {
                        this.set0x28AirData(var1);
                     }
                  } else {
                     this.set0x25TpmsData(var1);
                  }
               } else {
                  this.set0x24VehicleBaseInfo(var1);
               }
            } else {
               this.set0x7FVersionData(var1);
            }
         } else {
            this.set0x59Tbox249Data(var1);
         }
      } else {
         this.set0x1ERearRadarData(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 46, 0});
   }

   public void registOnWindLevelChangeListener(OnWindLevelChangeListener var1) {
      Log.i("ljqdebug", "registOnWindLevelChangeListener: " + var1);
      this.mOnWindLevelChangeListenerList.add(var1);
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      Log.i("_304_MsgMgr", "voiceControlInfo: " + var1);
      if ("voice_broadcast_finished".equals(var1)) {
         this.mIsVoiceBcFinished = true;
      }

   }

   public interface OnWindLevelChangeListener {
      void onChange();
   }

   private class VoiceBoardcastItem {
      private final String info;
      private boolean isPlay;
      final MsgMgr this$0;

      public VoiceBoardcastItem(MsgMgr var1, String var2) {
         this.this$0 = var1;
         this.info = var2;
      }

      public String getInfo() {
         return this.info;
      }

      public boolean isPlay() {
         return this.isPlay;
      }

      public void setPlay(boolean var1) {
         Log.i("_304_MsgMgr", "setPlay: \ninfo <--> " + this.info + "\nisPlay <--> " + var1);
         this.isPlay = var1;
      }
   }

   private interface WarningHelper {
      void initAlarmArray();

      void updateWarningList(int[] var1);
   }

   private abstract class WarningManger implements WarningHelper {
      public SparseArray alarmArray;
      private List entityList;
      final MsgMgr this$0;

      public WarningManger(MsgMgr var1, String var2) {
         this.this$0 = var1;
         Log.i("_304_MsgMgr", "WarningManger: title:" + var2);
         this.entityList = new ArrayList();
         SparseArray var3 = new SparseArray();
         this.alarmArray = var3;
         var3.put(-1, new WarningEntity(var2));
         this.initAlarmArray();
      }

      public List getEntityList() {
         return this.entityList;
      }

      public void setEntityList(List var1) {
         this.entityList.addAll(var1);
      }

      public void updateWarningList(int[] var1) {
         this.entityList.clear();
         this.entityList.add((WarningEntity)this.alarmArray.get(-1));
      }
   }
}
