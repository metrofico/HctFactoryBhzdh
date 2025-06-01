package com.hzbhd.canbus.car._118;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static final String SHARE_118_AMPLIFIER_BALANCE = "share_118_amplifier_balance";
   private static final String SHARE_118_AMPLIFIER_BASS = "share_118_amplifier_bass";
   private static final String SHARE_118_AMPLIFIER_FADE = "share_118_amplifier_fade";
   private static final String SHARE_118_AMPLIFIER_MIDDLE = "share_118_amplifier_middle";
   private static final String SHARE_118_AMPLIFIER_TREBLE = "share_118_amplifier_treble";
   private static final String SHARE_118_AMPLIFIER_VOLUME = "share_118_amplifier_volume";
   private static final String SHARE_118_CAR_MODEL = "share_118_car_model";
   static final int _118_AMPLIFIER_BAND_MAX = 1;
   static final int _118_AMPLIFIER_HALF_MAX = 10;
   private final int MEDIA_SOURCE_ID_A2DP = 11;
   private final int MEDIA_SOURCE_ID_AUX = 7;
   private final int MEDIA_SOURCE_ID_BTMUSIC = 5;
   private final int MEDIA_SOURCE_ID_DISC = 3;
   private final int MEDIA_SOURCE_ID_DVBT = 10;
   private final int MEDIA_SOURCE_ID_OFF = 0;
   private final int MEDIA_SOURCE_ID_SD = 6;
   private final int MEDIA_SOURCE_ID_TUNER = 1;
   private final int MEDIA_SOURCE_ID_USB = 4;
   int PlanStatus;
   private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
   int WeekendUtilFull;
   int WorkUtilFull;
   private int[] mAmplifierDataNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   int mCurrentCanDifferentId;
   private Handler mHandler;
   private boolean mIsBackOpen;
   private boolean mIsBackOpenNow;
   private boolean mIsFrontLeftOpen;
   private boolean mIsFrontLeftOpenNow;
   private boolean mIsFrontOpen;
   private boolean mIsFrontOpenNow;
   private boolean mIsFrontRightOpen;
   private boolean mIsFrontRightOpenNow;
   private boolean mIsRearLeftOpen;
   private boolean mIsRearLeftOpenNow;
   private boolean mIsRearRightOpen;
   private boolean mIsRearRightOpenNow;
   private int mOutdoorTemperature;
   private int mPlayingIndex = -1;
   private List mSongList;
   boolean rdm;
   boolean rpt;
   private UiMgr uiMgr;

   private String getAccData(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? this.mContext.getString(2131768099) : this.mContext.getString(2131757165) + " " + this.mContext.getString(2131757163);
            } else {
               return this.mContext.getString(2131757165);
            }
         } else {
            return this.mContext.getString(2131757165) + " " + this.mContext.getString(2131757152);
         }
      } else {
         return this.mContext.getString(2131757164);
      }
   }

   private String getCompassData(int var1) {
      switch (var1) {
         case 15:
            return this.mContext.getString(2131757166);
         case 16:
            return this.mContext.getString(2131769415);
         case 17:
            return this.mContext.getString(2131769416);
         case 18:
            return this.mContext.getString(2131768189);
         case 19:
            return this.mContext.getString(2131769961);
         case 20:
            return this.mContext.getString(2131769960);
         case 21:
            return this.mContext.getString(2131769962);
         case 22:
            return this.mContext.getString(2131770849);
         case 23:
            return this.mContext.getString(2131769417);
         default:
            return "";
      }
   }

   private String getCompassState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : this.mContext.getString(2131757168);
         } else {
            return this.mContext.getString(2131768060);
         }
      } else {
         return this.mContext.getString(2131757167);
      }
   }

   private int getData1(int var1) {
      return var1 >= 0 && var1 <= 18 ? var1 : 0;
   }

   private int getData2(int var1) {
      return var1 >= 0 && var1 <= 59 ? var1 : 0;
   }

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

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private String getOriginalCount(int var1, int var2) {
      return String.valueOf(var1 * 256 + var2);
   }

   private String getRunStatus(int var1) {
      switch (var1) {
         case 0:
            return this.mContext.getString(2131757173);
         case 1:
            return this.mContext.getString(2131757175);
         case 2:
            return this.mContext.getString(2131757176);
         case 3:
            return this.mContext.getString(2131757177);
         case 4:
            return this.mContext.getString(2131757178);
         case 5:
            return this.mContext.getString(2131757179);
         case 6:
            return this.mContext.getString(2131757180);
         case 7:
         default:
            return "";
         case 8:
            return this.mContext.getString(2131757181);
         case 9:
            return this.mContext.getString(2131757182);
      }
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private List getSongList() {
      if (this.mSongList == null) {
         this.mSongList = new ArrayList();
      }

      return this.mSongList;
   }

   private int[] getTimeWithSeconds(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void hybridInfo(Context var1) {
      ArrayList var5 = new ArrayList();
      int var2;
      int[] var8;
      StringBuilder var10;
      if (this.mCanBusInfoByte[2] == 0) {
         ArrayList var6 = new ArrayList();
         String var4 = CommUtil.getStrByResId(var1, "set_default");
         var2 = this.mCanBusInfoInt[3];
         String var7;
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 4) {
                     if (var2 != 8) {
                        var7 = var4;
                     } else {
                        var7 = CommUtil.getStrByResId(var1, "_118_drivingInfo_0_0_4");
                     }
                  } else {
                     var7 = CommUtil.getStrByResId(var1, "_118_drivingInfo_0_0_3");
                  }
               } else {
                  var7 = CommUtil.getStrByResId(var1, "_118_drivingInfo_0_0_2");
               }
            } else {
               var7 = CommUtil.getStrByResId(var1, "_118_drivingInfo_0_0_1");
            }
         } else {
            var7 = CommUtil.getStrByResId(var1, "_118_drivingInfo_0_0_0");
         }

         var6.add(new DriverUpdateEntity(1, 0, var7));
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
            var6.add(new DriverUpdateEntity(1, 1, "--"));
         } else {
            var10 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var6.add(new DriverUpdateEntity(1, 1, var10.append(var8[4] * 256 + var8[5]).append("kW").toString()));
         }

         if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
            var6.add(new DriverUpdateEntity(1, 2, "--"));
         } else {
            var6.add(new DriverUpdateEntity(1, 2, this.mCanBusInfoInt[6] - 125 + "kW"));
         }

         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
            var6.add(new DriverUpdateEntity(1, 3, "--"));
         } else {
            var6.add(new DriverUpdateEntity(1, 3, this.mCanBusInfoInt[7] + "kW"));
         }

         var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_drivingInfo_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_Title16"), this.resolveStatus(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]))));
         var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_drivingInfo_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_Title17"), this.resolveStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]))));
         var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_drivingInfo_0"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_Title18"), this.resolveStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]))));
         this.updateGeneralDriveData(var6);
         this.updateDriveDataActivity((Bundle)null);
      } else {
         var2 = this.mCanBusInfoInt[2];
         int var3;
         if (var2 == 1) {
            ArrayList var9 = new ArrayList();
            var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_DriveInfo_HeadTitle_01");
            var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_DriveInfo_Title02");
            StringBuilder var12 = new StringBuilder();
            int[] var11 = this.mCanBusInfoInt;
            var9.add(new DriverUpdateEntity(var2, var3, var12.append(var11[3] * 256 + var11[4]).append("KM").toString()));
            var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_DriveInfo_HeadTitle_01");
            var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_DriveInfo_Title03");
            var10 = new StringBuilder();
            int[] var13 = this.mCanBusInfoInt;
            var9.add(new DriverUpdateEntity(var2, var3, var10.append(var13[5] * 256 + var13[6]).append("KM").toString()));
            this.updateGeneralDriveData(var9);
            this.updateDriveDataActivity((Bundle)null);
         } else if (var2 == 2) {
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title01"), this.mCanBusInfoInt[3]));
            this.PlanStatus = this.mCanBusInfoInt[3];
            var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title023"), this.resolveTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 5)))).setValueStr(true));
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title04"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
            this.WorkUtilFull = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
            var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title056"), this.resolveEndTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])))).setValueStr(true));
            var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title078"), this.resolveTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 5)))).setValueStr(true));
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title09"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
            this.WeekendUtilFull = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
            var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title1011"), this.resolveEndTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 5), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])))).setValueStr(true));
            var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02");
            var2 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title12");
            var8 = this.mCanBusInfoInt;
            var5.add((new SettingUpdateEntity(var3, var2, this.resolveBatteryTime(var8[8], var8[9]))).setValueStr(true));
            var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02");
            var2 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title13");
            var8 = this.mCanBusInfoInt;
            var5.add((new SettingUpdateEntity(var3, var2, this.resolveBatteryTime(var8[10], var8[11]))).setValueStr(true));
         } else if (var2 == 3) {
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title14"), this.mCanBusInfoInt[3]));
            var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title15"), this.mCanBusInfoInt[4] + "%")).setValueStr(true));
         }
      }

      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_118_amplifier_balance", GeneralAmplifierData.leftRight);
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_118_amplifier_fade", GeneralAmplifierData.frontRear);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_118_amplifier_bass", GeneralAmplifierData.bandBass);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_118_amplifier_middle", GeneralAmplifierData.bandMiddle);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_118_amplifier_treble", GeneralAmplifierData.bandTreble);
         GeneralAmplifierData.volume = SharePreUtil.getIntValue(var1, "share_118_amplifier_volume", GeneralAmplifierData.volume);
      }

      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 10)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 10)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(GeneralAmplifierData.bandBass + 1)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(GeneralAmplifierData.bandMiddle + 1)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(GeneralAmplifierData.bandTreble + 1)});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean isAmplifierDataChange(int[] var1) {
      if (Arrays.equals(var1, this.mAmplifierDataNow)) {
         return false;
      } else {
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      boolean var1 = this.mIsFrontLeftOpen;
      if (var1 == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsFrontOpen == this.mIsFrontOpenNow) {
         return false;
      } else {
         this.mIsFrontLeftOpenNow = var1;
         this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
         this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
         this.mIsRearRightOpenNow = this.mIsRearRightOpen;
         this.mIsBackOpenNow = this.mIsBackOpen;
         this.mIsFrontOpenNow = this.mIsFrontOpen;
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyClick1(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private void rearAirConditionerInfo(Context var1) {
      int var2 = this.mCurrentCanDifferentId;
      if (var2 == 4 || var2 == 13) {
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_left_blow_head = false;
         GeneralAirData.rear_left_blow_foot = false;
         GeneralAirData.rear_right_blow_head = false;
         GeneralAirData.rear_right_blow_foot = false;
         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 == 3) {
                     GeneralAirData.rear_left_blow_head = false;
                     GeneralAirData.rear_left_blow_foot = true;
                     GeneralAirData.rear_right_blow_head = false;
                     GeneralAirData.rear_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_head = true;
                  GeneralAirData.rear_right_blow_foot = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_left_blow_foot = false;
               GeneralAirData.rear_right_blow_head = true;
               GeneralAirData.rear_right_blow_foot = false;
            }
         } else {
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_right_blow_foot = false;
         }

         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.rear_temperature = this.resolveRearTemp(this.mCanBusInfoInt[4]);
         this.updateAirActivity(var1, 1002);
      }
   }

   private String resolveBatteryTime(int var1, int var2) {
      var1 = var1 * 256 + var2;
      var2 = var1 / 60;
      return var2 + this.mContext.getResources().getString(2131768868) + var1 % 60 + this.mContext.getResources().getString(2131769310);
   }

   private String resolveEndTime(int var1, int var2, boolean var3) {
      String var4;
      if (var1 == 0) {
         var4 = "00";
      } else if (var1 == 1) {
         var4 = "15";
      } else if (var1 == 2) {
         var4 = "30";
      } else if (var1 == 3) {
         var4 = "45";
      } else {
         var4 = "";
      }

      var4 = var2 + ":" + var4;
      if (var3) {
         var4 = this.mContext.getResources().getString(2131756941);
      }

      return var4;
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 >= 1 && var1 <= 40) {
         switch (this.mCurrentCanDifferentId) {
            case 0:
            case 1:
            case 5:
            case 13:
               var2 = (float)var1 * 1.0F + this.getTempUnitC(this.mContext);
               break;
            case 2:
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
            case 12:
            case 14:
               var2 = (double)((float)var1 / 2.0F) + 15.5 + this.getTempUnitC(this.mContext);
               break;
            case 6:
            case 7:
            case 9:
               var2 = (float)var1 / 2.0F + 8.0F + this.getTempUnitC(this.mContext);
               break;
            default:
               var2 = "";
         }
      } else {
         var2 = (float)var1 * 1.0F + this.getTempUnitF(this.mContext);
      }

      if (var1 == 0) {
         var2 = "LO";
      }

      if (127 == var1) {
         var2 = "HI";
      }

      return var2;
   }

   private int resolveMode(int var1) {
      return var1 == 0 ? 1 : 0;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[2];
      if (this.mCurrentCanDifferentId == 3) {
         return var1 >= 0 && var1 <= 255 ? var1 - 85 + this.mContext.getString(2131770016) : "";
      } else {
         return var1 >= 0 && var1 <= 125 ? var1 - 40 + this.mContext.getString(2131770016) : "";
      }
   }

   private String resolveRearTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (126 == var1) {
         var2 = "HI";
      } else if (var1 >= 12 && var1 <= 44) {
         var2 = (float)var1 / 2.0F + 8.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveStatus(boolean var1) {
      return var1 ? this.mContext.getResources().getString(2131757084) : this.mContext.getResources().getString(2131757083);
   }

   private String resolveSteering(int var1) {
      if (var1 == 0) {
         return this.mContext.getResources().getString(2131756980);
      } else {
         return var1 == 1 ? this.mContext.getResources().getString(2131756981) : this.mContext.getResources().getString(2131756982);
      }
   }

   private String resolveTime(int var1, int var2) {
      String var3;
      if (var1 == 0) {
         var3 = "00";
      } else if (var1 == 1) {
         var3 = "15";
      } else if (var1 == 2) {
         var3 = "30";
      } else if (var1 == 3) {
         var3 = "45";
      } else {
         var3 = "";
      }

      return var2 + ":" + var3;
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      Handler var5 = this.mHandler;
      if (var5 != null) {
         var5.sendMessageDelayed(var5.obtainMessage(101, var1, 0, var2), var3);
      }
   }

   private void setAir0x05(Context var1) {
      int var2 = this.mOutdoorTemperature;
      int var3 = this.mCanBusInfoInt[9];
      if (var2 != var3) {
         this.mOutdoorTemperature = var3;
         this.updateOutDoorTemp(var1, this.mCanBusInfoInt[9] - 40 + this.getTempUnitC(var1));
      }

      byte[] var5 = this.mCanBusInfoByte;
      var5[9] = 0;
      if (!this.isAirMsgRepeat(var5)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         boolean var4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_auto_wind = var4;
         GeneralAirData.front_right_auto_wind = var4;
         if (var4) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
         } else {
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         }

         GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2);
         GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAmplifier0x31() {
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      int[] var12 = this.mCanBusInfoInt;
      int var1 = var12[3];
      int var11 = var12[4];
      int var4 = var12[5];
      int var7 = var12[6];
      int var5 = var12[7];
      int var6 = DataHandleUtils.getIntFromByteWithBit(var12[8], 1, 2);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1);
      ArrayList var13 = new ArrayList();
      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 0, var2));
      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 1, var9));
      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 2, var10));
      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 3, var8));
      var13.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 4, var6)).setProgress(var6));
      this.updateGeneralSettingData(var13);
      this.updateSettingActivity((Bundle)null);
      GeneralAmplifierData.frontRear = 10 - var11;
      GeneralAmplifierData.leftRight = var1 - 10;
      GeneralAmplifierData.volume = var3;
      GeneralAmplifierData.bandBass = var4 - 1;
      GeneralAmplifierData.bandMiddle = var7 - 1;
      GeneralAmplifierData.bandTreble = var5 - 1;
      if (this.isAmplifierDataChange(new int[]{GeneralAmplifierData.leftRight, GeneralAmplifierData.frontRear, GeneralAmplifierData.bandBass, GeneralAmplifierData.bandMiddle, GeneralAmplifierData.bandTreble, GeneralAmplifierData.volume})) {
         this.updateAmplifierActivity((Bundle)null);
      }

      SharePreUtil.setIntValue(this.mContext, "share_118_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_118_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_118_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_118_amplifier_middle", GeneralAmplifierData.bandMiddle);
      SharePreUtil.setIntValue(this.mContext, "share_118_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_118_amplifier_volume", GeneralAmplifierData.volume);
   }

   private void setCarInfo0x0A(byte[] var1) {
      if (!this.isDoorMsgRepeat(var1)) {
         ArrayList var4 = new ArrayList();
         int var2;
         Context var5;
         if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            var5 = this.mContext;
            var2 = 2131757163;
         } else {
            var5 = this.mContext;
            var2 = 2131757152;
         }

         var4.add(new DriverUpdateEntity(0, 1, var5.getString(var2)));
         var4.add(new DriverUpdateEntity(0, 2, this.getAccData(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3))));
         if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            var5 = this.mContext;
            var2 = 2131769841;
         } else {
            var5 = this.mContext;
            var2 = 2131769410;
         }

         var4.add(new DriverUpdateEntity(0, 3, var5.getString(var2)));
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            var5 = this.mContext;
            var2 = 2131769741;
         } else {
            var5 = this.mContext;
            var2 = 2131769742;
         }

         var4.add(new DriverUpdateEntity(0, 4, var5.getString(var2)));
         this.updateGeneralDriveData(var4);
         this.updateDriveDataActivity((Bundle)null);
         boolean var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftFrontDoorOpen = var3;
         this.mIsFrontLeftOpen = var3;
         var3 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = var3;
         this.mIsFrontRightOpen = var3;
         var3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = var3;
         this.mIsRearLeftOpen = var3;
         var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = var3;
         this.mIsRearRightOpen = var3;
         var3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = var3;
         this.mIsBackOpen = var3;
         if (this.isDoorDataChange()) {
            this.updateDoorView(this.mContext);
         }

      }
   }

   private void setCarSetting0x07() {
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
      int var54 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
      int var48 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
      int var25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1);
      int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
      int var36 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
      int var53 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1);
      int var31 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1);
      int var51 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
      int var39 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1);
      int var45 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1);
      int var37 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2);
      int var52 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1);
      int var43 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 2);
      int var49 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1);
      int var22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 2);
      int var34 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 1);
      int var27 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1);
      DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1);
      int var55 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1);
      int var41 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
      int var46 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2);
      int var35 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
      int var21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 3);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1);
      int var28 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 1);
      int var26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2);
      int var57 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 2);
      int var24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 3, 2);
      int var40 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1);
      int var56 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1);
      int var42 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 5, 2);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 1);
      int var50 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 3, 1);
      int var38 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 1);
      int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2);
      int var59 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 7, 1);
      int var23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 5, 2);
      int var33 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 3, 2);
      int var58 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 3);
      int var47 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1);
      int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 5, 2);
      int var60 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 1);
      int var29 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 3, 1);
      int var30 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 1);
      int var32 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 1, 1);
      int var44 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1);
      int[] var61 = this.mCanBusInfoInt;
      int var1;
      if (var61.length > 16) {
         var1 = var61[16] - 1;
      } else {
         var1 = 0;
      }

      ArrayList var62 = new ArrayList();
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) - 1));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) - 1));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_36"), var35));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_37"), var21));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_38"), var13));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_46"), var24));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_39"), var14));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) - 1));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_40"), var11));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_61"), var60));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_34"), var41));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_35"), var46));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_33"), var55));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_47"), var40));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_9"), var6));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_45"), var57));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_10"), var48));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_11"), var19));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_12"), var25));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_41"), var28));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_13"), var17));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_53"), var38));
      var62.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_58"), var58)).setProgress(var58));
      var62.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_57"), var33)).setProgress(var33));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_62"), var29));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_110"), var54));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_42"), var26));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_14"), var36));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_15"), var53));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_16"), var31));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_17"), var51));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_18"), var39));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_43"), var10));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_63"), var30));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_22"), var7));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_56"), var23));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_4", "_118_setting_title_19"), var45));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_4", "_118_setting_title_20"), var37));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_4"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_4", "_118_setting_title_23"), var43));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_24"), var49));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_25"), var9));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_26"), var22));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_27"), var2));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_28"), var4));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_29"), var15));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_30"), var34));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_68"), var1));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_44"), var3));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_48"), var8));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_49"), var56));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_52"), var50));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_55"), var59));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_7", "_118_setting_title_66"), var5));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_7"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_7", "_118_setting_title_67"), var18));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_54"), var20));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_59"), var47));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_60"), var16));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_50"), var42));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_51"), var12));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_64"), var32));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_31"), var27));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_65"), var44));
      var62.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_21"), var52));
      this.updateGeneralSettingData(var62);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarSetting0x19() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title19"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title20"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title21"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title22"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title23"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title24"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title25"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title26"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCompass0x0B(int[] var1) {
      if (this.isDataChange(var1)) {
         ArrayList var3 = new ArrayList();
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_91"), 0, this.getCompassData(var1[2]))).setValueStr(true));
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_91"), 1, this.getCompassState(DataHandleUtils.getIntFromByteWithBit(var1[3], 6, 2)))).setValueStr(true));
         int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 6);
         var3.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_91"), 2, var2)).setProgress(var2 - 1));
         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void setFrontRadar0x23() {
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(5, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private String setLightData(int var1) {
      if (var1 == 34) {
         return this.mContext.getString(2131757161);
      } else if (var1 == 200) {
         return this.mContext.getString(2131757162);
      } else {
         return var1 > 34 && var1 < 200 ? String.valueOf(var1) : this.mContext.getString(2131768099);
      }
   }

   private void setLights0x02() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.setLightData(this.mCanBusInfoInt[2])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginal0x10() {
      DecimalFormat var4 = new DecimalFormat("00");
      GeneralOriginalCarDeviceData.cdStatus = "CD";
      GeneralOriginalCarDeviceData.runningState = this.getRunStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
      GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.rdm = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      int var1 = this.getData1(this.mCanBusInfoInt[9]) * 60 * 60 + this.getData2(this.mCanBusInfoInt[10]) * 60 + this.getData2(this.mCanBusInfoInt[11]);
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[7] * 256 + var3[8];
      GeneralOriginalCarDeviceData.startTime = var4.format((long)this.getData1(this.mCanBusInfoInt[9])) + ":" + var4.format((long)this.getData2(this.mCanBusInfoInt[10])) + ":" + var4.format((long)this.getData2(this.mCanBusInfoInt[11]));
      GeneralOriginalCarDeviceData.endTime = var4.format((long)this.getHour(var2)) + ":" + var4.format((long)this.getMinute(var2)) + ":" + var4.format((long)this.getSecond(var2));
      if (var2 >= var1 && var2 > 0) {
         GeneralOriginalCarDeviceData.progress = var1 * 100 / var2;
      }

      ArrayList var5 = new ArrayList();
      int[] var6 = this.mCanBusInfoInt;
      var5.add(new OriginalCarDeviceUpdateEntity(0, this.getOriginalCount(var6[3], var6[4])));
      var6 = this.mCanBusInfoInt;
      var5.add(new OriginalCarDeviceUpdateEntity(1, this.getOriginalCount(var6[5], var6[6])));
      GeneralOriginalCarDeviceData.mList = var5;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setOriginalData0x11() {
      // $FF: Couldn't be decompiled
   }

   private void setOutTemp0x15() {
      int var1 = this.mCurrentCanDifferentId;
      if (var1 == 1 || var1 == 3 || var1 == 5 || var1 == 7 || var1 == 8) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }
   }

   private void setPanelButton0x04() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick(0);
            break;
         case 1:
            this.realKeyClick(152);
            break;
         case 2:
            this.realKeyClick(50);
            break;
         case 3:
            this.realKeyClick(3);
            break;
         case 4:
            this.realKeyClick(49);
            break;
         case 5:
            this.realKeyClick(8);
            break;
         case 6:
            this.realKeyClick(7);
            break;
         case 7:
            this.realKeyClick(47);
            break;
         case 8:
            this.realKeyClick(48);
            break;
         case 9:
            this.realKeyClick(1);
            break;
         case 10:
            this.realKeyClick(1);
            break;
         case 11:
            Intent var1 = new Intent(this.mContext, SettingActivity.class);
            var1.addFlags(268435456);
            var1.setAction("setting_open_target_page");
            var1.putExtra("left_index ", 0);
            var1.putExtra("right_index", 9);
            this.mContext.startActivity(var1);
      }

   }

   private void setPerformanceControl0x32() {
      ArrayList var5 = new ArrayList();
      ArrayList var4 = new ArrayList();
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_111"), this.mCanBusInfoInt[2]));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_112"), this.resolveMode(this.mCanBusInfoInt[3])));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_114"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_115"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_116"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_117"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 2)));
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_114");
      String var3;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         var3 = this.mContext.getResources().getString(2131756980);
      } else {
         var3 = this.mContext.getResources().getString(2131756981);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_115");
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
         var3 = this.mContext.getResources().getString(2131757152);
      } else {
         var3 = this.mContext.getResources().getString(2131757163);
      }

      var4.add(new DriverUpdateEntity(var1, var2, var3));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_116");
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
         var3 = this.mContext.getResources().getString(2131756980);
      } else {
         var3 = this.mContext.getResources().getString(2131756981);
      }

      var4.add(new DriverUpdateEntity(var2, var1, var3));
      var4.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_117"), this.resolveSteering(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2))));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private void setRearRadar0x22() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3.length;
      int var2 = 0;
      if (var1 > 4) {
         var1 = var3[4] - 1;
      } else {
         var1 = 0;
      }

      if (var3.length > 5) {
         var2 = var3[5] - 1;
      }

      RadarInfoUtil.mMinIsClose = true;
      var3 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(5, var3[2], var3[3], var1, var2);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private String setSpeedData(int var1, int var2) {
      return String.format("%.1f", (float)(var1 * 256 + var2) / 10.0F);
   }

   private void setSteeringWheelControls0x01() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 6) {
            switch (var1) {
               case 17:
                  this.realKeyClick(7);
                  break;
               case 18:
                  this.realKeyClick(8);
                  break;
               case 19:
                  this.realKeyClick(45);
                  break;
               case 20:
                  this.realKeyClick(46);
                  break;
               case 21:
                  this.realKeyClick(200);
                  break;
               case 22:
                  this.realKeyClick(20);
                  break;
               case 23:
                  this.realKeyClick(201);
                  break;
               case 24:
                  this.realKeyClick(14);
                  break;
               case 25:
                  this.realKeyClick(15);
                  break;
               case 26:
                  this.realKeyClick(2);
                  break;
               case 27:
                  this.realKeyClick(62);
                  break;
               case 28:
               case 31:
                  this.realKeyClick(48);
                  break;
               case 29:
               case 30:
                  this.realKeyClick(47);
                  break;
               case 32:
                  this.realKeyClick(49);
            }
         } else {
            this.realKeyClick(3);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setTrack0x09() {
      byte[] var1;
      if (this.mCurrentCanDifferentId == 3) {
         var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 1792, 2991, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void songListSetSelected() {
      int[] var1 = this.mCanBusInfoInt;
      this.mPlayingIndex = var1[3] * 256 + var1[4];
      if (GeneralOriginalCarDeviceData.songList != null) {
         Iterator var2 = GeneralOriginalCarDeviceData.songList.iterator();

         while(var2.hasNext()) {
            ((SongListEntity)var2.next()).setSelected(false);
         }

         ((SongListEntity)GeneralOriginalCarDeviceData.songList.get(this.mPlayingIndex)).setSelected(true);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 7});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 5});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 10});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 8});
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 9});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 4) {
               if (var3 != 5) {
                  if (var3 != 6) {
                     if (var3 != 7) {
                        if (var3 != 16) {
                           if (var3 != 17) {
                              if (var3 != 21) {
                                 if (var3 != 25) {
                                    if (var3 != 34) {
                                       if (var3 != 35) {
                                          switch (var3) {
                                             case 9:
                                                this.setTrack0x09();
                                                return;
                                             case 10:
                                                this.setCarInfo0x0A(var2);
                                                return;
                                             case 11:
                                                this.setCompass0x0B(var4);
                                                return;
                                             default:
                                                switch (var3) {
                                                   case 48:
                                                      this.setVersionInfo();
                                                      return;
                                                   case 49:
                                                      this.setAmplifier0x31();
                                                      return;
                                                   case 50:
                                                      this.setPerformanceControl0x32();
                                                      return;
                                                   case 51:
                                                      this.hybridInfo(var1);
                                                }
                                          }
                                       } else {
                                          this.setFrontRadar0x23();
                                       }

                                       return;
                                    }
                                 } else {
                                    var3 = this.mCurrentCanDifferentId;
                                    if (var3 != 5 && var3 != 6 && var3 != 7 && var3 != 8) {
                                       this.setCarSetting0x19();
                                    }
                                 }

                                 this.setRearRadar0x22();
                              } else {
                                 this.setOutTemp0x15();
                              }
                           } else {
                              this.setOriginalData0x11();
                           }
                        } else {
                           this.setOriginal0x10();
                        }
                     } else {
                        var3 = this.mCurrentCanDifferentId;
                        if (var3 == 9 || var3 == 10 || var3 == 12 || var3 == 14) {
                           return;
                        }

                        this.setCarSetting0x07();
                     }
                  } else {
                     this.rearAirConditionerInfo(var1);
                  }
               } else {
                  this.setAir0x05(var1);
               }
            } else {
               this.setPanelButton0x04();
            }
         } else {
            this.setLights0x02();
         }
      } else {
         this.setSteeringWheelControls0x01();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)DataHandleUtils.setIntByteWithBit(var8, 7, var10 ^ true), (byte)var6, (byte)var7, 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         var4 = var5;
      }

      int[] var18 = this.getTimeWithSeconds(var2);
      byte var14 = (byte)var4;
      byte var17 = (byte)var6;
      byte var15 = (byte)var18[0];
      byte var16 = (byte)var18[1];
      var1 = (byte)var18[2];
      byte[] var19 = DataHandleUtils.byteMerger(new byte[]{22, -112, 3, (byte)255}, new byte[]{0, var14, var17, var15, var16, var1});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var19);
   }

   public Activity getCurrentActivity() {
      return this.getActivity();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifierData(var1);
      this.mCurrentCanDifferentId = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 96, (byte)SharePreUtil.getIntValue(var1, "share_118_car_model", 0)});
      this.updateCarModel(var1, SharePreUtil.getIntValue(var1, "share_118_car_model", 0));
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 6;
      } else {
         var25 = 4;
      }

      var2 = (byte)var25;
      CanbusMsgSender.sendMsg(new byte[]{22, -112, var2});
      var1 = (byte)var3;
      byte[] var26 = DataHandleUtils.byteMerger(new byte[]{22, -112, var2, (byte)255}, new byte[]{var1, var9, 0, var5, var6, var7});
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var7 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)1, (byte)1, (byte)2, (byte)2);
      this.getFreqByteHiLo(var2, var3);
      byte var6 = (byte)var7;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -112, var6});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -112, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   public void updateCarModel(Context var1, int var2) {
      SharePreUtil.setIntValue(var1, "share_118_car_model", var2);
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(9, 0, var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
