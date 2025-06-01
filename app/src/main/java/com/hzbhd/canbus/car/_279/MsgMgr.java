package com.hzbhd.canbus.car._279;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private boolean mFrontStatus;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontNow;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearNow;
   private boolean mLeftRearStatus;
   private byte[] mOutDoorTempDataNow;
   private boolean mRightFrontNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearNow;
   private boolean mRightRearStatus;
   private String mTempertureUnit;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private byte[] mVersionInfoNow;

   private String getConsumptionUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : "km/l";
         } else {
            return "mpg";
         }
      } else {
         return "l/100km";
      }
   }

   private String getDriveData(int[] var1) {
      if (var1 != null && var1.length != 0) {
         int var4 = 0;
         int var2 = var1[0];

         int var3;
         for(var3 = 1; var3 < var1.length; ++var3) {
            var2 = (int)((double)var2 + (double)var1[var3] * Math.pow(256.0, (double)var3));
         }

         for(var3 = 1; var4 < var1.length * 8 - 1; ++var4) {
            var3 = (var3 << 1) + 1;
         }

         return var2 == var3 ? "---" : Integer.toString(var2);
      } else {
         return "";
      }
   }

   private String getPlayState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  return var1 != 4 ? "" : CommUtil.getStrByResId(this.mContext, "_123_divice_status_3");
               } else {
                  return CommUtil.getStrByResId(this.mContext, "device_run_status_1");
               }
            } else {
               return CommUtil.getStrByResId(this.mContext, "device_run_status_5");
            }
         } else {
            return CommUtil.getStrByResId(this.mContext, "device_run_status_4");
         }
      } else {
         return CommUtil.getStrByResId(this.mContext, "nissan_infor_s");
      }
   }

   private String getSpeedUnit(int var1) {
      if (var1 != 0) {
         return var1 != 1 ? "" : "mpg";
      } else {
         return "km/h";
      }
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte[] var6 = new byte[]{22, -127, 1};
            byte[] var7 = new byte[]{22, -56, 0, (byte)GeneralAmplifierData.volume};
            byte var4 = (byte)GeneralAmplifierData.bandBass;
            byte var3 = (byte)GeneralAmplifierData.bandTreble;
            byte var5 = (byte)(GeneralAmplifierData.frontRear + 10);
            byte[] var8 = new byte[]{22, -56, 4, (byte)(GeneralAmplifierData.leftRight + 10)};
            this.iterator = Arrays.stream(new byte[][]{var6, var7, {22, -56, 1, var4}, {22, -56, 2, var3}, {22, -56, 3, var5}, var8}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontNow && this.mRightFrontStatus == this.mRightFrontNow && this.mLeftRearStatus == this.mLeftRearNow && this.mRightRearStatus == this.mRightRearNow && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontNow;
         this.mRightFrontStatus = this.mRightFrontNow;
         this.mLeftRearStatus = this.mLeftRearNow;
         this.mRightRearStatus = this.mRightRearNow;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isOutDoorTempChange() {
      if (Arrays.equals(this.mOutDoorTempDataNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mOutDoorTempDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private String resolveOutDoorTem() {
      int var2 = this.mCanBusInfoByte.length - 3;
      byte[] var3 = new byte[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = this.mCanBusInfoByte[var1 + 3];
      }

      return new String(var3) + this.getTempUnitC(this.mContext);
   }

   private void setAmplifier0x39(Context var1) {
      GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[3] - 10;
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 10;
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData(var1, this.getCanId());
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(1, 0, this.mCanBusInfoInt[5]));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCdcData0x3B() {
      GeneralOriginalCarDeviceData.runningState = this.getPlayState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4));
      GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      ArrayList var3 = new ArrayList();
      int var1 = 1;

      while(true) {
         boolean var2 = false;
         if (var1 > 6) {
            GeneralOriginalCarDeviceData.songList = var3;
            ArrayList var5 = new ArrayList();
            StringBuilder var6 = (new StringBuilder()).append(": ");
            var1 = this.mCanBusInfoInt[3];
            String var9 = "---";
            String var8;
            if (var1 == 255) {
               var8 = "---";
            } else {
               var8 = Integer.toString(var1);
            }

            String var10 = var6.append(var8).toString();
            StringBuilder var7 = (new StringBuilder()).append(": ");
            var1 = this.mCanBusInfoInt[4];
            if (var1 == 255) {
               var8 = var9;
            } else {
               var8 = Integer.toString(var1);
            }

            var8 = var7.append(var8).toString();
            var5.add(new OriginalCarDeviceUpdateEntity(0, var10));
            var5.add(new OriginalCarDeviceUpdateEntity(1, var8));
            GeneralOriginalCarDeviceData.mList = var5;
            this.updateOriginalCarDeviceActivity((Bundle)null);
            return;
         }

         SongListEntity var4 = new SongListEntity(CommUtil.getStrByResId(this.mContext, "_279_disc") + " " + var1);
         if (this.mCanBusInfoInt[3] == var1) {
            var2 = true;
         }

         var3.add(var4.setSelected(var2));
         ++var1;
      }
   }

   private void setDashboardData0x35() {
      ArrayList var5 = new ArrayList();
      byte[] var3 = this.mCanBusInfoByte;
      int var1 = var3[8];
      var1 = var3[2] | var1 << 8;
      Log.i("ljq", "setDashboardData0x35: data: " + var1);
      StringBuilder var4 = (new StringBuilder()).append(var1).append(" ");
      String var7;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])) {
         var7 = this.getTempUnitF(this.mContext);
      } else {
         var7 = this.getTempUnitC(this.mContext);
      }

      var5.add(new DriverUpdateEntity(1, 0, var4.append(var7).toString()));
      StringBuilder var6 = (new StringBuilder()).append(this.mCanBusInfoInt[3]).append(" ");
      boolean var2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]);
      String var8 = "mil";
      if (var2) {
         var7 = "mil";
      } else {
         var7 = "km";
      }

      var5.add(new DriverUpdateEntity(1, 1, var6.append(var7).append("/h").toString()));
      StringBuilder var9 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var6 = var9.append(this.getDriveData(new int[]{var10[5], var10[4]})).append(" ");
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12])) {
         var7 = "mil";
      } else {
         var7 = "km";
      }

      var5.add(new DriverUpdateEntity(1, 2, var6.append(var7).toString()));
      var9 = new StringBuilder();
      var10 = this.mCanBusInfoInt;
      var5.add(new DriverUpdateEntity(1, 3, var9.append(this.getDriveData(new int[]{var10[7], var10[6]})).append(" rpm").toString()));
      var9 = new StringBuilder();
      var10 = this.mCanBusInfoInt;
      var6 = var9.append(this.getDriveData(new int[]{var10[11], var10[10], var10[9]})).append(" ");
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12])) {
         var7 = var8;
      } else {
         var7 = "km";
      }

      var5.add(new DriverUpdateEntity(1, 4, var6.append(var7).toString()));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[3]);
   }

   private void setDoorData0x24() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         this.mRightFrontNow = var1;
         GeneralDoorData.isRightFrontDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         this.mLeftFrontNow = var1;
         GeneralDoorData.isLeftFrontDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         this.mRightRearNow = var1;
         GeneralDoorData.isRightRearDoorOpen = var1;
         var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         this.mLeftRearNow = var1;
         GeneralDoorData.isLeftRearDoorOpen = var1;
         if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            var1 = false;
         } else {
            var1 = true;
         }

         GeneralDoorData.isBackOpen = var1;
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         if (this.isDoorDataChange()) {
            this.updateDoorView(this.mContext);
         }
      }

   }

   private void setFuelDataChange0x37() {
      ArrayList var4 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2] * 256 + var2[3];
      String var3 = "---";
      String var5;
      if (var1 == 65535) {
         var5 = "---";
      } else {
         var5 = (float)var1 / 10.0F + " " + this.getConsumptionUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2));
      }

      var4.add(new DriverUpdateEntity(0, 0, var5));
      var2 = this.mCanBusInfoInt;
      var1 = var2[4] * 256 + var2[5];
      if (var1 == 65535) {
         var5 = "---";
      } else {
         var5 = (float)var1 / 10.0F + " " + this.getConsumptionUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2));
      }

      var4.add(new DriverUpdateEntity(0, 1, var5));
      var2 = this.mCanBusInfoInt;
      var1 = var2[6] * 256 + var2[7];
      if (var1 == 65535) {
         var5 = var3;
      } else {
         var5 = (float)var1 / 10.0F + " " + this.getSpeedUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1));
      }

      Log.i("ljq", "setFuelDataChange0x37: " + var5);
      var4.add(new DriverUpdateEntity(0, 2, var5));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginalSourceInfo0x3A() {
      GeneralOriginalCarDeviceData.cdStatus = "Line " + this.mCanBusInfoInt[2];
      byte[] var1 = this.mCanBusInfoByte;
      GeneralOriginalCarDeviceData.discStatus = new String(Arrays.copyOfRange(var1, 3, var1.length - 1));
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setOriginalTimeData0x3C() {
      if (this.mCanBusInfoInt[2] == 2) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }

   }

   private void setVersionInfo0x30() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.wheelKeyClick(0);
            break;
         case 1:
            this.wheelKeyClick(7);
            break;
         case 2:
            this.wheelKeyClick(8);
            break;
         case 3:
            this.wheelKeyClick(14);
            break;
         case 4:
            this.wheelKeyClick(3);
            break;
         case 5:
            this.wheelKeyClick(46);
            break;
         case 6:
            this.wheelKeyClick(45);
            break;
         case 7:
            this.wheelKeyClick(2);
            break;
         case 8:
            this.wheelKeyClick(187);
            break;
         case 9:
            this.wheelKeyClick(15);
      }

   }

   private void setcentralControlData0x38() {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)));
      int[] var1 = this.mCanBusInfoInt;
      SettingUpdateEntity var4 = new SettingUpdateEntity(0, 0, var1[2] * 256 + var1[3]);
      int[] var3 = this.mCanBusInfoInt;
      var2.add(var4.setProgress(var3[2] * 256 + var3[3] - 6));
      String var5;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
         var5 = this.getTempUnitF(this.mContext);
      } else {
         var5 = this.getTempUnitC(this.mContext);
      }

      this.mTempertureUnit = var5;
      var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)));
      var2.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var2.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
         var5 = "12h";
      } else {
         var5 = "24h";
      }

      var2.add((new SettingUpdateEntity(0, 6, var5)).setValueStr(true));
      var2.add(new SettingUpdateEntity(0, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var2.add(new SettingUpdateEntity(0, 10, this.mCanBusInfoInt[9]));
      var1 = this.mCanBusInfoInt;
      SettingUpdateEntity var6 = new SettingUpdateEntity(0, 8, var1[5] * 256 + var1[6]);
      var1 = this.mCanBusInfoInt;
      var2.add(var6.setProgress(var1[5] * 256 + var1[6]));
      var2.add((new SettingUpdateEntity(0, 9, (new DecimalFormat("00")).format((long)this.mCanBusInfoInt[7]) + ":" + (new DecimalFormat("00")).format((long)this.mCanBusInfoInt[8]))).setValueStr(true));
      var2.add(new SettingUpdateEntity(0, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void startTimer(long var1) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1);
      }
   }

   private void startTimer(long var1, long var3) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1, var3);
      }
   }

   private void stopTimer() {
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

   private void wheelKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var15 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var15;
      int var3 = var15[1];
      Exception var10000;
      boolean var10001;
      if (var3 != 32) {
         if (var3 != 36) {
            if (var3 != 48) {
               if (var3 != 53) {
                  switch (var3) {
                     case 55:
                        try {
                           this.setFuelDataChange0x37();
                           return;
                        } catch (Exception var9) {
                           var10000 = var9;
                           var10001 = false;
                           break;
                        }
                     case 56:
                        try {
                           this.setcentralControlData0x38();
                           return;
                        } catch (Exception var8) {
                           var10000 = var8;
                           var10001 = false;
                           break;
                        }
                     case 57:
                        try {
                           this.setAmplifier0x39(var1);
                           return;
                        } catch (Exception var7) {
                           var10000 = var7;
                           var10001 = false;
                           break;
                        }
                     case 58:
                        try {
                           this.setOriginalSourceInfo0x3A();
                           return;
                        } catch (Exception var6) {
                           var10000 = var6;
                           var10001 = false;
                           break;
                        }
                     case 59:
                        try {
                           this.setCdcData0x3B();
                           return;
                        } catch (Exception var5) {
                           var10000 = var5;
                           var10001 = false;
                           break;
                        }
                     case 60:
                        try {
                           this.setOriginalTimeData0x3C();
                           return;
                        } catch (Exception var4) {
                           var10000 = var4;
                           var10001 = false;
                           break;
                        }
                     default:
                        return;
                  }
               } else {
                  try {
                     this.setDashboardData0x35();
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               }
            } else {
               try {
                  this.setVersionInfo0x30();
                  return;
               } catch (Exception var11) {
                  var10000 = var11;
                  var10001 = false;
               }
            }
         } else {
            try {
               this.setDoorData0x24();
               return;
            } catch (Exception var12) {
               var10000 = var12;
               var10001 = false;
            }
         }
      } else {
         try {
            this.setWheelKey0x20();
            return;
         } catch (Exception var13) {
            var10000 = var13;
            var10001 = false;
         }
      }

      Exception var14 = var10000;
      var14.printStackTrace();
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var2, (byte)var3, (byte)var4, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var8, (byte)var6, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)(var10 ^ 1), 0, 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1 || var7 == 5) {
         var4 = var5;
      }

      var1 = (byte)DataHandleUtils.rangeNumber(var4, 0, 255);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, var1, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mTempertureUnit = this.getTempUnitC(var1);
      this.initAmplifier(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var1 = (byte)DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 255);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, var1, 0, 0, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var13;
      if (var2.contains("AM")) {
         var13 = 16;
      } else {
         var13 = 0;
      }

      float var11 = Float.valueOf(var3);
      float var10 = var11;
      if (var2.contains("FM")) {
         var10 = var11 * 100.0F;
      }

      int var12 = (int)var10;
      byte var7 = (byte)var13;
      byte var6 = (byte)(var12 >> 8 & 255);
      byte var8 = (byte)(var12 & 255);
      byte var9 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, var7, var6, var8, var9});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0, 0, 0, 0});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var1 = (byte)DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 255);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, var1, 0, 0, 0});
   }
}
