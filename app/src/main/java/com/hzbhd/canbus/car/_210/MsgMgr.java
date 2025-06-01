package com.hzbhd.canbus.car._210;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.view.ParkAssistFloatView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static boolean angleWide;
   private static boolean isDoorFirst;
   private static boolean isParkingFirst;
   private static int outDoorTemp;
   static boolean overlook;
   private static int rearTemp;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
   private final String IS_REAR_AIR_POWER = "is_rear_air_power";
   private final String IS_REAR_AIR_TEMP_CHANGE_FORD = "is_rear_air_temp_change_ford";
   private final String IS_REAR_AIR_WIND_LV_CHANGE = "is_rear_air_wind_lv_change";
   private final String IS_REAR_LOCK = "is_rear_lock";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private int ford_park_assist_info;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwcInfoCopy;
   private int mCanId;
   private Context mContext;
   private byte[] mDoorInfoCopy;
   private boolean mIsAirFirst = true;
   private ParkAssistFloatView mParkAssistFloatView;
   private byte[] mParkingInfoCopy;

   private long bytesToLong(byte[] var1) {
      long var3 = 0L;

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3 = (long)((double)var3 + (double)(var1[var2] & 255) * Math.pow(256.0, (double)var2));
      }

      return var3;
   }

   private void dccOffOnCtrl(byte[] var1) {
      if (this.mCanBusInfoInt[2] == 18 && !this.isSwcMsgReturn(var1)) {
         Intent var2 = new Intent(this.mContext, MainActivity.class);
         var2.addFlags(268435456);
         this.mContext.startActivity(var2);
      }

   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte[] var5 = new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume};
      byte[] var7 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 7)};
      byte[] var3 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 7)};
      byte[] var4 = new byte[]{22, -83, 4, (byte)GeneralAmplifierData.bandBass};
      byte var2 = (byte)GeneralAmplifierData.bandMiddle;
      byte[] var8 = new byte[]{22, -83, 6, (byte)GeneralAmplifierData.bandTreble};
      TimerHelper var6 = new TimerHelper(this);
      var6.startTimer(new TimerTask(this, new byte[][]{var5, var7, var3, var4, {22, -83, 5, var2}, var8}, var6) {
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

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mDoorInfoCopy)) {
         return true;
      } else {
         this.mDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return true;
      } else {
         return false;
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen;
      }
   }

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   private boolean isOnlyRearAirDataChange() {
      if (SharePreUtil.getIntValue(this.mContext, "is_rear_air_temp_change_ford", 0) != rearTemp) {
         return true;
      } else if (SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_lv_change", 0) != GeneralAirData.rear_wind_level) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_rear_air_power", false) != GeneralAirData.rear_power) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_rear_lock", false) != GeneralAirData.rear_lock;
      }
   }

   private boolean isParkingMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mParkingInfoCopy)) {
         return true;
      } else {
         this.mParkingInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isParkingFirst) {
            isParkingFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 8) {
         if (var1 != 9) {
            if (var1 != 98) {
               if (var1 != 101) {
                  switch (var1) {
                     case 0:
                        this.realKeyClick(0);
                        break;
                     case 1:
                        this.realKeyClick(7);
                        break;
                     case 2:
                        this.realKeyClick(8);
                        break;
                     case 3:
                        this.realKeyClick(3);
                        break;
                     case 4:
                        this.realKeyClick(187);
                        break;
                     case 5:
                        this.realKeyClick(207);
                        break;
                     case 6:
                        this.realKeyClick(206);
                        break;
                     default:
                        switch (var1) {
                           case 13:
                              this.realKeyClick(45);
                              break;
                           case 14:
                              this.realKeyClick(46);
                              break;
                           case 15:
                              this.realKeyClick(49);
                        }
                  }
               } else {
                  this.realKeyClick(31);
               }
            } else {
               this.realKeyClick(17);
            }
         } else {
            this.realKeyClick(47);
         }
      } else {
         this.realKeyClick(48);
      }

   }

   private void languageSettingInfo() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = 2;
      int var2 = var3[2];
      if (var2 != 27) {
         if (var2 != 0) {
            var1 = var2 - 1;
         } else {
            var1 = 0;
         }
      }

      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void playPresetChann(int var1) throws RemoteException {
      FutureUtil.instance.playPresetFreq(var1);
      Intent var2 = new Intent();
      var2.setAction("com.hzbhd.intent.action.fm");
      this.mContext.startActivity(var2);
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick1(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void realKeyClick2(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void refreshFordParkAssistFlowView(int[] var1) {
      int var2 = var1[3];
      if (var2 != 0 && this.ford_park_assist_info != var2) {
         this.ford_park_assist_info = var2;
         if (this.mParkAssistFloatView == null) {
            this.mParkAssistFloatView = new ParkAssistFloatView(this.mContext);
         }

         this.runOnUi(new CallBackInterface(this, var2) {
            final MsgMgr this$0;
            final int val$cmd;

            {
               this.this$0 = var1;
               this.val$cmd = var2;
            }

            public void callback() {
               Bundle var1 = new Bundle();
               var1.putByte("PARK_ASSIST_REFRESH_UI_BUNDLE_KEY", (byte)this.val$cmd);
               this.this$0.mParkAssistFloatView.refreshUi(var1);
            }
         });
      }

   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (GeneralAirData.fahrenheit_celsius) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = (int)((float)var1 * 0.5F) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (GeneralAirData.fahrenheit_celsius) {
         var2 = (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
      } else {
         var2 = (float)(((double)var1 * 0.5 - 40.0) * 1.8 + 32.0) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private String resolveRearTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "Close";
      } else if (1 == var1) {
         var2 = "Lo";
      } else if (9 == var1) {
         var2 = "Hi";
      } else if (5 == var1) {
         var2 = "Mid";
      } else {
         var2 = "" + var1;
      }

      return var2;
   }

   private void setAmplifierData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 7;
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 7;
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
         this.saveAmplifierData(this.mContext, this.mCanId);
         this.updateAmplifierActivity(new Bundle());
      }

      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2)));
      var1.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarDoorInfo() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]) && this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         this.updateDoorView(this.mContext);
      }

   }

   private void setCarStatus() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      byte[] var2 = this.mCanBusInfoByte;
      var1.add(new DriverUpdateEntity(0, 2, var3.append(this.bytesToLong(new byte[]{var2[5], var2[4]})).append("Rpm").toString()));
      StringBuilder var4 = new StringBuilder();
      byte[] var5 = this.mCanBusInfoByte;
      var1.add(new DriverUpdateEntity(0, 3, var4.append(this.bytesToLong(new byte[]{var5[7], var5[6]})).append("Km/h").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatusInfo() {
      int var1 = this.mCanBusInfoInt[2];
      String var3 = "";
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 255) {
                     var2 = "";
                  } else {
                     var2 = this.mContext.getResources().getString(2131767387);
                  }
               } else {
                  var2 = this.mContext.getResources().getString(2131767384);
               }
            } else {
               var2 = this.mContext.getResources().getString(2131767388);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131767383);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131767385);
      }

      var1 = this.mCanBusInfoInt[3];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     var3 = "D";
                  }
               } else {
                  var3 = "R";
               }
            } else {
               var3 = "N";
            }
         } else {
            var3 = "P";
         }
      } else {
         var3 = this.mContext.getResources().getString(2131768909);
      }

      ArrayList var4 = new ArrayList();
      var4.add(new DriverUpdateEntity(0, 0, var2));
      var4.add(new DriverUpdateEntity(0, 1, var3));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarType() {
      ArrayList var1 = new ArrayList();
      if (this.mCanBusInfoInt[3] == 20) {
         var1.add(new DriverUpdateEntity(0, 4, this.mContext.getResources().getString(2131768272)));
      }

      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarVolumeCtrl() {
      RemoteException var10000;
      label104: {
         int[] var2;
         boolean var10001;
         try {
            var2 = this.mCanBusInfoInt;
         } catch (RemoteException var16) {
            var10000 = var16;
            var10001 = false;
            break label104;
         }

         StringBuilder var3;
         FutureUtil var17;
         switch (var2[2]) {
            case 1:
               int var1 = var2[3];
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           return;
                        }

                        try {
                           this.changeBandAm2();
                        } catch (RemoteException var12) {
                           var10000 = var12;
                           var10001 = false;
                           break;
                        }
                     } else {
                        try {
                           this.changeBandAm1();
                        } catch (RemoteException var13) {
                           var10000 = var13;
                           var10001 = false;
                           break;
                        }
                     }
                  } else {
                     try {
                        this.changeBandFm2();
                     } catch (RemoteException var14) {
                        var10000 = var14;
                        var10001 = false;
                        break;
                     }
                  }
               } else {
                  try {
                     this.changeBandFm1();
                  } catch (RemoteException var15) {
                     var10000 = var15;
                     var10001 = false;
                     break;
                  }
               }

               return;
            case 2:
               try {
                  this.changeBandAm1();
                  SystemClock.sleep(500L);
                  var17 = FutureUtil.instance;
                  var3 = new StringBuilder();
                  var17.setCurrentFreq(var3.append(this.mCanBusInfoByte[3] & 255).append("").append(this.mCanBusInfoByte[4] & 255).append("").toString());
                  return;
               } catch (RemoteException var11) {
                  var10000 = var11;
                  var10001 = false;
                  break;
               }
            case 3:
               try {
                  this.changeBandFm1();
                  SystemClock.sleep(500L);
                  var17 = FutureUtil.instance;
                  var3 = new StringBuilder();
                  var17.setCurrentFreq(var3.append(this.mCanBusInfoByte[3] & 255).append(".").append(this.mCanBusInfoByte[4] & 255).toString());
                  return;
               } catch (RemoteException var10) {
                  var10000 = var10;
                  var10001 = false;
                  break;
               }
            case 4:
               try {
                  this.playPresetChann(this.mCanBusInfoByte[3] - 1 & 255);
                  return;
               } catch (RemoteException var9) {
                  var10000 = var9;
                  var10001 = false;
                  break;
               }
            case 5:
               if (var2[3] == 1) {
                  try {
                     this.realKeyClick(this.mContext, 45);
                  } catch (RemoteException var7) {
                     var10000 = var7;
                     var10001 = false;
                     break;
                  }
               } else {
                  try {
                     this.realKeyClick(this.mContext, 46);
                  } catch (RemoteException var8) {
                     var10000 = var8;
                     var10001 = false;
                     break;
                  }
               }

               return;
            case 6:
               try {
                  FutureUtil.instance.gotoTrack(this.mCanBusInfoInt[3]);
                  return;
               } catch (RemoteException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break;
               }
            case 7:
               if (var2[3] == 1) {
                  try {
                     FutureUtil.instance.playMpeg();
                  } catch (RemoteException var4) {
                     var10000 = var4;
                     var10001 = false;
                     break;
                  }
               } else {
                  try {
                     FutureUtil.instance.pauseMpeg();
                  } catch (RemoteException var5) {
                     var10000 = var5;
                     var10001 = false;
                     break;
                  }
               }

               return;
            default:
               return;
         }
      }

      RemoteException var18 = var10000;
      var18.printStackTrace();
   }

   private void setEnvironmentLight() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setFrontAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
            int var1 = this.mCanBusInfoInt[6];
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           switch (var1) {
                              case 11:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 break;
                              case 12:
                                 GeneralAirData.front_left_blow_foot = true;
                                 GeneralAirData.front_right_blow_foot = true;
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 break;
                              case 13:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_right_blow_head = true;
                                 break;
                              case 14:
                                 GeneralAirData.front_left_blow_foot = true;
                                 GeneralAirData.front_right_blow_foot = true;
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_right_blow_head = true;
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
               } else {
                  GeneralAirData.front_defog = true;
               }
            } else {
               GeneralAirData.front_left_auto_wind = true;
               GeneralAirData.front_right_auto_wind = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]) ^ true;
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
            int[] var4 = this.mCanBusInfoInt;
            var1 = var4[12];
            rearTemp = var1;
            outDoorTemp = var4[13];
            GeneralAirData.rear_temperature = this.resolveRearTemp(var1);
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
            boolean var2 = this.isOnlyRearAirDataChange();
            boolean var3 = this.isOnlyOutDoorDataChange();
            if (var2 && !this.isOnlyOutDoorDataChange()) {
               SharePreUtil.setIntValue(this.mContext, "is_rear_air_temp_change_ford", rearTemp);
               SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_lv_change", GeneralAirData.rear_wind_level);
               SharePreUtil.setBoolValue(this.mContext, "is_rear_air_power", GeneralAirData.rear_power);
               SharePreUtil.setBoolValue(this.mContext, "is_rear_lock", GeneralAirData.rear_lock);
               this.updateAirActivity(this.mContext, 1002);
            }

            if (!var2 && !var3) {
               this.updateAirActivity(this.mContext, 1001);
            }

            if (var3) {
               SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", (float)outDoorTemp);
               this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
            }

         }
      }
   }

   private void setFrontRearRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[6], var1[7], var1[8], var1[9]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setProbeRadarLocationData(7, var1[10], var1[11], 0, 0);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLightSetting() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(2, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit(2, this.mCanBusInfoInt[3]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalCameraStatusInfo() {
      angleWide = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      overlook = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      ArrayList var1 = new ArrayList();
      var1.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setOriginalPanel() {
      int var5 = this.mCanBusInfoInt[2];
      int var1 = 0;
      byte var3 = 0;
      byte var4 = 0;
      byte var2 = 0;
      if (var5 == 1) {
         if (this.mCanBusInfoByte[3] > 0) {
            for(var1 = var2; var1 < this.mCanBusInfoByte[3]; ++var1) {
               this.realKeyClick1(7);
            }
         } else {
            while(this.mCanBusInfoByte[3] < var1) {
               this.realKeyClick1(8);
               --var1;
            }
         }
      } else {
         var1 = var4;
         if (this.mCanBusInfoByte[3] > 0) {
            for(var1 = var3; var1 < this.mCanBusInfoByte[3]; ++var1) {
               this.realKeyClick1(46);
            }
         } else {
            while(this.mCanBusInfoByte[3] < var1) {
               this.realKeyClick1(45);
               --var1;
            }
         }
      }

   }

   private void setPanelBtnInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 36) {
                     if (var1 != 60) {
                        if (var1 == 61) {
                           this.realKeyClick2(47);
                        }
                     } else {
                        this.realKeyClick2(48);
                     }
                  } else {
                     this.realKeyClick2(59);
                  }
               } else {
                  this.realKeyClick2(46);
               }
            } else {
               this.realKeyClick2(45);
            }
         } else {
            this.realKeyClick2(134);
         }
      } else {
         this.realKeyClick2(0);
      }

   }

   private void setReversingVideo() {
      int var1 = this.mCanBusInfoInt[5];
      Context var3 = this.mContext;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      this.forceReverse(var3, var2);
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(3, 0, this.mCanBusInfoInt[4]));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTipsInfo() {
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
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
                     if (var3 != 65) {
                        if (var3 != 97) {
                           if (var3 != 148) {
                              if (var3 != 166) {
                                 if (var3 != 224) {
                                    if (var3 != 232) {
                                       if (var3 != 240) {
                                          if (var3 != 49) {
                                             if (var3 != 50) {
                                                if (var3 != 103) {
                                                   if (var3 == 104) {
                                                      this.setTipsInfo();
                                                   }
                                                } else {
                                                   this.setLightSetting();
                                                }
                                             } else {
                                                this.setCarStatus();
                                             }
                                          } else {
                                             this.setFrontAirData();
                                          }
                                       } else {
                                          this.setVersionInfo();
                                       }
                                    } else {
                                       this.setReversingVideo();
                                       this.setOriginalCameraStatusInfo();
                                    }
                                 } else {
                                    this.setCarVolumeCtrl();
                                 }
                              } else {
                                 this.setAmplifierData();
                              }
                           } else {
                              this.languageSettingInfo();
                           }
                        } else {
                           if (this.isParkingMsgReturn(var2)) {
                              return;
                           }

                           this.setEnvironmentLight();
                           this.refreshFordParkAssistFlowView(this.mCanBusInfoInt);
                        }
                     } else {
                        this.setFrontRearRadar();
                     }
                  } else {
                     this.setCarType();
                  }
               } else {
                  this.setOriginalPanel();
               }
            } else {
               this.setPanelBtnInfo();
               this.dccOffOnCtrl(var2);
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setCarDoorInfo();
            this.setCarStatusInfo();
         }
      } else {
         this.keyControl0x11();
         this.setTrackInfo();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -108});
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 3});
      this.updateOrignalSetting();
      this.initAmplifierData(var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateOrignalSetting() {
      int var1 = SharePreUtil.getIntValue(this.mContext, "_210_park_assess_item", 0);
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(3, 1, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
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
