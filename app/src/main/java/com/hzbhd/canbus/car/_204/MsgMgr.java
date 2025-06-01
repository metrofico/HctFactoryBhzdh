package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static int volKnobValue;
   private final int DATA_TYPE = 1;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mEachId;
   private boolean mFrontStatus;
   private int mFrontViewStatus;
   private int mKeyStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private int mPanoramicStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private UiMgr mUiMgr;

   private UiMgr getmUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), this.getmUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      byte[] var8 = new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume};
      byte var3 = (byte)(GeneralAmplifierData.leftRight + 10);
      byte var2 = (byte)(GeneralAmplifierData.frontRear + 10);
      byte var5 = (byte)GeneralAmplifierData.bandBass;
      byte var4 = (byte)GeneralAmplifierData.bandMiddle;
      byte[] var6 = new byte[]{22, -83, 6, (byte)GeneralAmplifierData.bandTreble};
      TimerUtil var7 = new TimerUtil();
      var7.startTimer(new TimerTask(this, new byte[][]{var8, {22, -83, 2, var3}, {22, -83, 3, var2}, {22, -83, 4, var5}, {22, -83, 5, var4}, var6}, var7) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$amplifierData;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$amplifierData = var2;
            this.val$timerUtil = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$amplifierData;
            if (var1 < var2.length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 0L, 100L);
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

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick03(int var1, int var2) {
      this.realKeyClick3_1(this.mContext, var1, 0, var2);
   }

   private void realKeyClick2(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private void set0X32CarBodyData() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[4] << 8 | var3[5];
      int var2 = var3[6];
      var2 = var3[7] | var2 << 8;
      String var4 = "无效值";
      String var6;
      if (var1 == 65535) {
         var6 = "无效值";
      } else {
         var6 = var1 + " RPM";
      }

      if (var2 != 65535) {
         var4 = var2 + " KM/H";
         this.updateSpeedInfo(var2);
      }

      ArrayList var5 = new ArrayList();
      var5.add(new DriverUpdateEntity(0, 0, var6));
      var5.add(new DriverUpdateEntity(0, 1, var4));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x11CarBaseData(Context var1) {
      int var2 = this.mKeyStatus;
      int var3 = this.mCanBusInfoInt[4];
      if (var2 != var3) {
         this.mKeyStatus = var3;
      }

      if (var3 != 0) {
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 5) {
                  if (var3 != 103) {
                     switch (var3) {
                        case 8:
                           this.realKeyClick(45);
                           break;
                        case 9:
                           this.realKeyClick(46);
                           break;
                        case 10:
                           this.realKeyClick(2);
                     }
                  } else {
                     this.realKeyClick(220);
                  }
               } else {
                  this.realKeyClick(14);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var4 = this.mCanBusInfoInt;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var4[9], (byte)var4[8], 0, 540, 16);
      }

      this.updateParkUi((Bundle)null, var1);
   }

   private void set0x12CarDetailedData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x22KnobData() {
      int var2 = this.mKeyStatus;
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2];
      if (var2 != var1) {
         this.mKeyStatus = var1;
      }

      if (var1 == 1) {
         var1 = volKnobValue - var3[3];
         if (var1 < 0) {
            this.realKeyClick03(7, Math.abs(var1));
         } else if (var1 > 0) {
            this.realKeyClick03(8, Math.abs(var1));
         }

         volKnobValue = this.mCanBusInfoInt[3];
      }

   }

   private void set0x24WheelKeyData() {
      int var2 = this.mKeyStatus;
      int var1 = this.mCanBusInfoInt[2];
      if (var2 != var1) {
         this.mKeyStatus = var1;
      }

      if (var1 != 6) {
         if (var1 != 9) {
            if (var1 != 37) {
               switch (var1) {
                  case 64:
                     this.startMainActivity(this.mContext);
                     break;
                  case 65:
                     this.realKeyClick2(this.mContext, 76);
                     break;
                  case 66:
                     this.realKeyClick2(this.mContext, 4);
               }
            } else {
               this.realKeyClick2(this.mContext, 128);
            }
         } else {
            this.realKeyClick2(this.mContext, 3);
         }
      } else {
         this.realKeyClick2(this.mContext, 50);
      }

   }

   private void set0x41FrontRearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      RadarInfoUtil.mDisableData2 = 255;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationDataType2(2, var1[6], 3, var1[7], 3, var1[8], 4, var1[9]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationDataType2(2, var1[2], 4, var1[3], 4, var1[4], 2, var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x42LeftRightRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      RadarInfoUtil.mDisableData2 = 255;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRightRadarLocationData(2, var1[2], 0, 0, var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setLeftRadarLocationData(2, var1[6], 0, 0, var1[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x62CarSetupDate() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         ArrayList var4 = new ArrayList();
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
         String var3 = "valid";
         String var2;
         if (var1 == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(2, 0, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(2, 1, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(2, 2, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(2, 3, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(2, 4, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(3, 0, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(3, 1, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(3, 2, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1) == 1) {
            var2 = "valid";
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(3, 3, var2));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1) == 1) {
            var2 = var3;
         } else {
            var2 = "invalid";
         }

         var4.add(new SettingUpdateEntity(3, 4, var2));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0xA6AmplifierData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 10;
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 10;
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
         this.saveAmplifierData(this.mContext, this.getCanId());
         this.updateAmplifierActivity(new Bundle());
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2)));
         var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0xE8VehicleScreenStatusData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = this.mFrontViewStatus;
         int var2 = this.mCanBusInfoInt[4];
         boolean var4 = false;
         boolean var3;
         Context var5;
         if (var1 != var2) {
            this.mFrontViewStatus = var2;
            if (!CommUtil.isMiscReverse()) {
               var5 = this.mContext;
               if (this.mFrontViewStatus == 1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               this.switchFCamera(var5, var3);
            }
         }

         var2 = this.mPanoramicStatus;
         var1 = this.mCanBusInfoInt[5];
         if (var2 != var1) {
            this.mPanoramicStatus = var1;
            var5 = this.mContext;
            if (var1 == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.switchFCamera(var5, var3);
         }

         ArrayList var6 = new ArrayList();
         if (this.mCanBusInfoInt[7] == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.add(new PanoramicBtnUpdateEntity(0, var3));
         if (this.mCanBusInfoInt[8] == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.add(new PanoramicBtnUpdateEntity(1, var3));
         if (this.mCanBusInfoInt[6] == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.add(new PanoramicBtnUpdateEntity(2, var3));
         if (this.mCanBusInfoInt[4] == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.add(new PanoramicBtnUpdateEntity(3, var3));
         var3 = var4;
         if (this.mCanBusInfoInt[5] == 1) {
            var3 = true;
         }

         var6.add(new PanoramicBtnUpdateEntity(4, var3));
         GeneralParkData.dataList = var6;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0xF0Version() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mEachId = this.getCurrentEachCanId();
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
                  if (var3 != 50) {
                     if (var3 != 98) {
                        if (var3 != 166) {
                           if (var3 != 232) {
                              if (var3 != 240) {
                                 if (var3 != 65) {
                                    if (var3 == 66) {
                                       this.set0x42LeftRightRadar();
                                    }
                                 } else {
                                    this.set0x41FrontRearRadar();
                                 }
                              } else {
                                 this.set0xF0Version();
                              }
                           } else {
                              this.set0xE8VehicleScreenStatusData();
                           }
                        } else {
                           this.set0xA6AmplifierData();
                        }
                     } else {
                        this.set0x62CarSetupDate();
                     }
                  } else {
                     this.set0X32CarBodyData();
                  }
               } else {
                  this.set0x22KnobData();
               }
            } else {
               this.set0x24WheelKeyData();
            }
         } else {
            this.set0x12CarDetailedData(var1);
         }
      } else {
         this.set0x11CarBaseData(var1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 17});
      this.initAmplifierData(var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateSettings(int var1, int var2, Object var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }
}
