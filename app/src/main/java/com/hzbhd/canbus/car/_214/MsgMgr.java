package com.hzbhd.canbus.car._214;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
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
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.proxy.service.ServiceConstants;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static boolean isLastLongClick;
   private static int lastPanelSt;
   private static int lastPanelkey;
   private static int lastSwcKey;
   private static int lastSwcSt;
   private static int longClickCount;
   private static boolean mIsKonbClockwise;
   private static boolean mIsKonbSelClockwise;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private int mData0_26 = 15;
   private int mData0_7 = 0;
   private int mData2_03 = 5;
   private int mData2_47 = 5;
   private boolean mEnable6_1 = false;
   private boolean mEnable6_2 = false;
   private boolean mEnable6_3 = false;
   private boolean mEnable6_4 = false;
   private String mFuelUnit = "mpg";
   private int mKonbCount = 0;
   private int mKonbSelCount = 0;
   private int mLastKonbCount = 0;
   private int mLastSelKonbCount = 0;
   private TimerHelper mRequestTimer;
   private int mStartTime = 0;
   private UiMgr mUiMgr;

   private String carType(int var1) {
      Context var2 = this.mContext;
      return var2.getString(CommUtil.getStrIdByResId(var2, "_213_car" + var1));
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte var3 = (byte)GeneralAmplifierData.volume;
      byte[] var6 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 16)};
      byte[] var7 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 16)};
      byte var4 = (byte)(GeneralAmplifierData.bandBass + 10);
      byte var2 = (byte)(GeneralAmplifierData.bandTreble + 10);
      TimerHelper var5 = new TimerHelper(this);
      var5.startTimer(new TimerTask(this, new byte[][]{{22, -83, 1, var3}, var6, var7, {22, -83, 4, var4}, {22, -83, 6, var2}}, var5) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$amplifierData;
         final TimerHelper val$timerHelper;

         {
            this.this$0 = var1;
            this.val$amplifierData = var2;
            this.val$timerHelper = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$amplifierData;
            if (var1 < var2.length) {
               this.i = var1 + 1;
               CanbusMsgSender.sendMsg(var2[var1]);
            } else {
               this.val$timerHelper.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private void konbKeySel(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         if (mIsKonbSelClockwise) {
            this.realKeyClick4(48);
         } else {
            this.realKeyClick4(47);
         }
      }

   }

   private void konbKeyVol(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         if (mIsKonbClockwise) {
            this.realKeyClick4(7);
         } else {
            this.realKeyClick4(8);
         }
      }

   }

   private void panelBtnKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick3_2(Context var1, int var2, int var3) {
      this.realKeyClick3_2(var1, var2, this.mCanBusInfoInt[2], var3);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveFrontAirTemp(int var1) {
      String var2;
      if (var1 == 254) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void sendAppIconSelect(Context var1, int var2, int var3) {
      if (ConfigUtil.getDeviceId().contains("P0R")) {
         String var4 = System.getString(var1.getContentResolver(), ServiceConstants.KEY_CURRENT_TOP_PACKAGENAME).split(":;:")[1];
         if (!TextUtils.isEmpty(var4) && var4.contains("com.android.launcher3")) {
            String var5;
            if (var2 == 47) {
               var5 = "KEY_APP_SELECT_PREV";
            } else if (var2 == 48) {
               var5 = "KEY_APP_SELECT_NEXT";
            } else {
               if (var2 != 49) {
                  return;
               }

               var5 = "KEY_APP_SELECT_ENTER";
            }

            FutureUtil.instance.sendAppSelect(var5);
         } else if (var2 == 49) {
            this.realKeyClick(var1, var2);
         } else {
            this.realKeyClick3_2(var1, var2, var3);
         }
      }

   }

   private void set0x21PanelKeyData(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 6) {
            if (var2 != 9) {
               if (var2 != 32) {
                  if (var2 != 43) {
                     if (var2 != 45) {
                        if (var2 != 84) {
                           if (var2 != 2) {
                              if (var2 != 3) {
                                 switch (var2) {
                                    case 22:
                                       this.sendAppIconSelect(var1, 49, 1);
                                       break;
                                    case 23:
                                       this.realKeyLongClick1(var1, 45);
                                       break;
                                    case 24:
                                       this.realKeyLongClick1(var1, 46);
                                       break;
                                    case 25:
                                       this.realKeyLongClick1(var1, 47);
                                       break;
                                    case 26:
                                       this.realKeyLongClick1(var1, 48);
                                       break;
                                    case 27:
                                       this.realKeyLongClick1(var1, 128);
                                 }
                              } else {
                                 this.realKeyLongClick1(var1, 20);
                              }
                           } else {
                              this.realKeyLongClick1(var1, 21);
                           }
                        } else {
                           try {
                              Intent var3 = new Intent();
                              ComponentName var4 = new ComponentName("com.google.android.youtube", "com.google.android.youtube.app.honeycomb.Shell$HomeActivity");
                              var3.setComponent(var4);
                              var3.setFlags(268435456);
                              var1.startActivity(var3);
                           } catch (Exception var5) {
                              this.realKeyLongClick1(var1, 79);
                           }
                        }
                     } else {
                        this.realKeyLongClick1(var1, 59);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 52);
                  }
               } else {
                  this.realKeyLongClick1(var1, 128);
               }
            } else {
               this.realKeyLongClick1(var1, 3);
            }
         } else {
            this.realKeyLongClick1(var1, 50);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private void set0x32BodyInfo() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "drive_data");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_214_car_speed1");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[4], var4[5])).append("RPM").toString()));
      var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "drive_data");
      var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_214_car_speed2");
      StringBuilder var6 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var6.append(this.getMsbLsbResult(var7[6], var7[7])).append("KM/H").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setAirData0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
         GeneralAirData.front_left_blow_foot = true;
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
         GeneralAirData.front_left_blow_head = true;
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
         GeneralAirData.front_left_blow_window = true;
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
         GeneralAirData.front_right_blow_foot = true;
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
         GeneralAirData.front_right_blow_head = true;
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7])) {
         GeneralAirData.front_right_blow_window = true;
      }

      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
      GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
      GeneralAirData.front_left_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveFrontAirTemp(this.mCanBusInfoInt[9]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAmplifierData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 16;
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 16;
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 10;
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 10;
         this.saveAmplifierData(this.mContext, this.mCanId);
         this.updateAmplifierActivity(new Bundle());
      }

      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(3, 0, this.mCanBusInfoInt[9]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarType() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(2, 2, this.carType(this.mCanBusInfoInt[3])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorData() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setDriveData() {
      ArrayList var3 = new ArrayList();

      for(int var1 = 0; var1 < 8; ++var1) {
         StringBuilder var4 = new StringBuilder();
         int[] var5 = this.mCanBusInfoInt;
         int var2 = var1 * 2;
         var3.add(new DriverUpdateEntity(0, var1, var4.append(String.valueOf((float)((double)(var5[var2 + 2] * 256 + var5[var2 + 3]) * 0.1 * 10.0) / 10.0F)).append(" ").append(this.mFuelUnit).toString()));
      }

      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData2() {
      ArrayList var4 = new ArrayList();

      for(int var1 = 0; var1 < 15; ++var1) {
         StringBuilder var3 = new StringBuilder();
         int[] var5 = this.mCanBusInfoInt;
         int var2 = var1 * 2;
         var4.add(new DriverUpdateEntity(1, var1, var3.append(String.valueOf((float)((double)(var5[var2 + 2] * 256 + var5[var2 + 3]) * 0.1 * 10.0) / 10.0F)).append(" ").append(this.mFuelUnit).toString()));
      }

      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setHudData() {
      ArrayList var1 = new ArrayList();
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_0_7"), this.mData0_7)).setEnable(this.mEnable6_4));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_0_62"), this.mData0_26 - 15)).setProgress(this.mData0_26).setEnable(this.mEnable6_3));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_2_74"), this.mData2_47 - 5)).setProgress(this.mData2_47).setEnable(this.mEnable6_2));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_2_30"), this.mData2_03 - 5)).setProgress(this.mData2_03).setEnable(this.mEnable6_1));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setHudData0x79() {
      this.mData0_7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      this.mData0_26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 5);
      this.mData2_47 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      this.mData2_03 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      this.setHudData();
   }

   private void setOriginalCarDeviceInfo() {
      ArrayList var4 = new ArrayList();
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(0, var5.append(var6[7] * 256 + var6[8]).append("").toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(1, var5.append(var6[15] * 256 + var6[16]).append("").toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(2, var5.append(var6[9] * 256 + var6[10]).append("").toString()));
      GeneralOriginalCarDeviceData.mList = var4;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131769504);
      } else {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131768042);
      }

      GeneralOriginalCarDeviceData.cd = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      boolean var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_off = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_fold = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_track = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_off = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_fold = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_disc = var3;
      Context var7 = this.mContext;
      GeneralOriginalCarDeviceData.runningState = var7.getString(CommUtil.getStrIdByResId(var7, "_123_divice_status_" + this.mCanBusInfoInt[5]));
      int[] var8 = this.mCanBusInfoInt;
      int var2 = var8[13] * 256 + var8[14];
      int var1 = var8[11] * 256 + var8[12];
      if (var2 == 0 && this.mStartTime != 0) {
         if (this.mRequestTimer == null) {
            this.mRequestTimer = new TimerHelper(this);
         }

         byte[] var9 = new byte[]{22, -14, 10, 0};
         this.mRequestTimer.startTimer(new TimerTask(this, new byte[][]{var9, {22, -14, 10, 1}, {22, -14, 10, 2}}) {
            int i;
            final MsgMgr this$0;
            final byte[][] val$rquestCmdArray;

            {
               this.this$0 = var1;
               this.val$rquestCmdArray = var2;
               this.i = 0;
            }

            public void run() {
               int var1 = this.i;
               byte[][] var2 = this.val$rquestCmdArray;
               if (var1 < var2.length) {
                  this.i = var1 + 1;
                  CanbusMsgSender.sendMsg(var2[var1]);
               } else {
                  this.this$0.mRequestTimer.stopTimer();
               }

            }
         }, 0L, 150L);
      }

      this.mStartTime = var2;
      if (var1 > 0 && var2 <= var1) {
         GeneralOriginalCarDeviceData.startTime = this.startEndTimeMethod(var2);
         GeneralOriginalCarDeviceData.endTime = this.startEndTimeMethod(var1);
         GeneralOriginalCarDeviceData.progress = var2 * 100 / var1;
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setOriginalCarDeviceInfo2() {
      String var1;
      try {
         var1 = new String(DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 3, (byte)0), "GB2312");
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
         var1 = null;
      }

      ArrayList var2 = new ArrayList();
      var2.add(new OriginalCarDeviceUpdateEntity(this.mCanBusInfoInt[2] + 2, var1));
      GeneralOriginalCarDeviceData.mList = var2;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setPanelKnobSel() {
      int[] var4 = this.mCanBusInfoInt;
      if (var4[2] == 2) {
         int var1 = var4[3];
         int var2 = this.mLastSelKonbCount;
         boolean var3;
         if (var1 - var2 >= 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         mIsKonbSelClockwise = var3;
         var1 = Math.abs(var1 - var2);
         this.mKonbSelCount = var1;
         if (var1 != 0) {
            if (this.mCanBusInfoInt[2] == 2) {
               this.konbKeySel(var1);
            }

            this.mLastSelKonbCount = this.mCanBusInfoInt[3];
         }
      }
   }

   private void setPanelKnobVol() {
      if (this.mCanBusInfoInt[2] == 1) {
         byte var2 = this.mCanBusInfoByte[3];
         int var1 = this.mLastKonbCount;
         boolean var3;
         if (var2 - var1 >= 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         mIsKonbClockwise = var3;
         var1 = Math.abs(var2 - var1);
         this.mKonbCount = var1;
         if (var1 != 0) {
            if (this.mCanBusInfoInt[2] == 1) {
               this.konbKeyVol(var1);
            }

            this.mLastKonbCount = this.mCanBusInfoByte[3];
         }
      }
   }

   private void setRadar() {
      int[] var1;
      if (this.mCanBusInfoInt[13] == 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(3, var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         GeneralParkData.radar_distance_disable = new int[]{0, 255};
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
         var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarDistanceData(var1[6], var1[7], var1[8], var1[9]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSwcKey() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.panelBtnKeyClick(0);
            break;
         case 1:
            this.panelBtnKeyClick(7);
            break;
         case 2:
            this.panelBtnKeyClick(8);
            break;
         case 3:
            this.panelBtnKeyClick(3);
            break;
         case 4:
            this.panelBtnKeyClick(187);
            break;
         case 5:
            this.panelBtnKeyClick(14);
            break;
         case 6:
            this.panelBtnKeyClick(15);
         case 7:
         default:
            break;
         case 8:
            this.panelBtnKeyClick(45);
            break;
         case 9:
            this.panelBtnKeyClick(46);
            break;
         case 10:
            this.panelBtnKeyClick(2);
      }

   }

   private void setTrack() {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(2, 0, this.mCanBusInfoInt[3] + this.mContext.getResources().getString(2131770215)));
      int var1 = this.mCanBusInfoInt[7];
      if (var1 > 0 && 100 > var1) {
         var3.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[7] + ""));
      } else if (var1 == 0) {
         var3.add(new DriverUpdateEntity(2, 1, "OFF"));
      } else if (var1 == 100) {
         var3.add(new DriverUpdateEntity(2, 1, "MAX"));
      }

      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void settingInfo() {
      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10]));
      boolean var84 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3);
      int var1 = var2;
      if (var2 > 5) {
         var1 = 5;
      }

      boolean var81 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      int var43 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2);
      boolean var58 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var28 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 2);
      boolean var77 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      int var39 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]));
      boolean var60 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      int var34 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9]));
      boolean var63 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      int var24 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10]));
      boolean var59 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      int var22 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10]));
      boolean var76 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 2);
      boolean var62 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2);
      boolean var85 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      int var21 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[11]));
      boolean var74 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      int var50 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]));
      boolean var65 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12]));
      boolean var79 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]));
      int var40 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]));
      boolean var67 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
      int var44 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2);
      boolean var56 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      int var45 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 3);
      boolean var53 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
      int var37 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 5, 3);
      boolean var78 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      int var42 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 3);
      boolean var69 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[13]));
      boolean var86 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13]));
      boolean var61 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 1, 2);
      boolean var64 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14]));
      boolean var70 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 5, 2);
      boolean var57 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      int var33 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[11]));
      boolean var54 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]);
      int var51 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]));
      int var47 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13]));
      boolean var52 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]);
      int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 3, 2);
      int var23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 1, 2);
      int var49 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[14]));
      int var41 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 2);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2);
      int var29 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[15]));
      int var30 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[15]));
      int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2);
      int var15 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[16]));
      int var32 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[16]));
      int var48 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[16]));
      int var35 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[16]));
      int var31 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[16]));
      boolean var72 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]);
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[16]));
      boolean var80 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
      int var38 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[16], 0, 2);
      boolean var66 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
      int var25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 6, 2);
      boolean var68 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      int var16 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[17]));
      boolean var55 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 3, 2);
      boolean var82 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
      int var26 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[17]));
      boolean var73 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
      int var27 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 0, 2);
      boolean var75 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
      int var36 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 5, 3);
      boolean var83 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      int var46 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 3, 2);
      boolean var71 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
      ArrayList var87 = new ArrayList();

      for(var2 = 0; var2 < 42; ++var2) {
         var87.add((new SettingUpdateEntity(0, var2, (new int[]{var11, var1, var43, var28, var39, var34, var24, var22, var13, var19, var21, var50, var40, var44, var45, var37, var42, var33, var51, var47, var49, var23, var17, var41, var12, var29, var30, var20, var15, var32, var48, var35, var31, var14, var38, var25, var16, var18, var26, var27, var36, var46})[var2])).setEnable((new boolean[]{var84, var81, var58, var77, var60, var63, var59, var76, var62, var85, var74, var65, var67, var56, var53, var78, var69, var54, true, var52, true, true, true, true, true, true, true, true, true, true, true, true, var72, var80, var66, var68, var55, var82, var73, var75, var83, var71})[var2]));
      }

      for(var1 = 0; var1 < 5; ++var1) {
         var87.add((new SettingUpdateEntity(1, var1, (new int[]{var8, var7, var6, var9, var10})[var1])).setEnable((new boolean[]{var86, var61, var64, var70, var57})[var1]));
      }

      for(var1 = 0; var1 < 3; ++var1) {
         var87.add((new SettingUpdateEntity(2, var1, (new int[]{var5, var3, var4})[var1])).setEnable((new boolean[]{true, var79, true})[var1]));
      }

      this.updateGeneralSettingData(var87);
      this.updateSettingActivity((Bundle)null);
      this.mEnable6_4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
      this.mEnable6_3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8]);
      this.mEnable6_2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]);
      this.mEnable6_1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]);
      this.setHudData();
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      var1 = Util.byteMerger(new byte[]{22, -111, 8}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("AUX".getBytes(), 0, var1, 0, "AUX".getBytes().length);
      var1 = Util.byteMerger(new byte[]{22, -111, 12}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), var1);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 20) {
               if (var3 != 21) {
                  if (var3 != 33) {
                     if (var3 != 34) {
                        if (var3 != 38) {
                           if (var3 != 65) {
                              if (var3 != 174) {
                                 if (var3 != 240) {
                                    if (var3 != 49) {
                                       if (var3 != 50) {
                                          if (var3 != 120) {
                                             if (var3 != 121) {
                                                if (var3 != 165) {
                                                   if (var3 == 166) {
                                                      this.setAmplifierData();
                                                   }
                                                } else {
                                                   this.setOriginalCarDeviceInfo2();
                                                }
                                             } else {
                                                this.setHudData0x79();
                                             }
                                          } else {
                                             this.settingInfo();
                                          }
                                       } else {
                                          this.set0x32BodyInfo();
                                       }
                                    } else {
                                       if (this.isAirMsgReturn(var2)) {
                                          return;
                                       }

                                       this.setAirData0x31();
                                    }
                                 } else {
                                    this.setVersionInfo();
                                 }
                              } else {
                                 this.setOriginalCarDeviceInfo();
                              }
                           } else {
                              this.setRadar();
                           }
                        } else {
                           this.setCarType();
                        }
                     } else {
                        this.setPanelKnobVol();
                        this.setPanelKnobSel();
                     }
                  } else {
                     this.set0x21PanelKeyData(var1);
                  }
               } else {
                  this.setDriveData2();
               }
            } else {
               this.setDriveData();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData();
         }
      } else {
         this.setTrack();
         this.setSwcKey();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var16 = (byte)(var2 / 60 % 60);
      byte var15 = (byte)(var2 % 60);
      var11 = (new DecimalFormat("00")).format((long)var16);
      var12 = (new DecimalFormat("00")).format((long)var15);
      var11 = (new DecimalFormat("000")).format((long)var5) + " " + var11 + var12 + "    ";
      byte var14 = 7;
      var1 = var14;
      if (var7 != 1) {
         var1 = var14;
         if (var7 != 2) {
            var1 = var14;
            if (var7 != 3) {
               if (var7 != 6 && var7 != 7) {
                  var1 = 0;
               } else {
                  var1 = 6;
               }
            }
         }
      }

      byte[] var17 = var11.getBytes();
      var17 = Util.byteMerger(new byte[]{22, -111, var1}, var17);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var17);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      var1 = Util.byteMerger(new byte[]{22, -111, 8}, var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      switch (this.getCurrentEachCanId()) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 11, 10});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 9, 10});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 7, 10});
            break;
         case 3:
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 4, 10});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 5, 10});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 10, 10});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 6, 10});
         case 8:
         default:
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 13, 10});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 8, 10});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 12, 10});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 3, 10});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 10});
      }

      this.initAmplifierData(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)var6);
      var12 = (new DecimalFormat("00")).format((long)var7);
      var11 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var11 + ":" + var12 + "   ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var25 = var11.getBytes();
      var25 = Util.byteMerger(new byte[]{22, -111, var1}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 > 6) {
         var5 = 0;
      }

      byte var7 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "  Khz";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3 + "  Khz";
         }
      } else if (var3.length() == 5) {
         var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3 + "Mhz";
      } else {
         var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "Mhz";
      }

      byte var6 = (byte)var7;
      byte[] var8 = var2.getBytes();
      var8 = Util.byteMerger(new byte[]{22, -111, var6}, var8);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var8);
   }

   public void setFuelUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.mFuelUnit = "l/100km";
            }
         } else {
            this.mFuelUnit = "km/l";
         }
      } else {
         this.mFuelUnit = "mpg";
      }

   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + "         ";
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      byte[] var18 = var8.getBytes();
      var18 = Util.byteMerger(new byte[]{22, -111, var1}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
   }

   private class TimerHelper {
      private Timer mTimer;
      private TimerTask mTimerTask;
      final MsgMgr this$0;

      private TimerHelper(MsgMgr var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      TimerHelper(MsgMgr var1, Object var2) {
         this(var1);
      }

      public void startTimer(TimerTask var1, long var2, long var4) {
         Log.i("TimerUtil", "startTimer: " + this);
         if (var1 != null) {
            this.mTimerTask = var1;
            if (this.mTimer == null) {
               this.mTimer = new Timer();
            }

            this.mTimer.schedule(this.mTimerTask, var2, var4);
         }
      }

      public void stopTimer() {
         Log.i("TimerUtil", "stopTimer: " + this);
         TimerTask var1 = this.mTimerTask;
         if (var1 != null) {
            var1.cancel();
            this.mTimerTask = null;
         }

         Timer var2 = this.mTimer;
         if (var2 != null) {
            var2.cancel();
            this.mTimer = null;
         }

      }
   }
}
