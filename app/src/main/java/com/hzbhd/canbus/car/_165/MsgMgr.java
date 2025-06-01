package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final int CAR_ELFA = 50;
   private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private static final int SEND_NORMAL_MESSAGE = 2;
   static final int SHARE_165_AMPLIFIER_BAND_OFFSET = 2;
   static final int SHARE_165_AMPLIFIER_FADE_OFFSET = 7;
   static final String SHARE_165_IS_HAVE_SPARE_TIRE = "share_165_is_have_spare_tire";
   static final String SHARE_165_IS_SUPPORT_TPMS = "share_165_is_suppot_tire";
   static final String SHARE_165_IS_SUPPOT_HYBRID = "share_165_is_suppot_hybrid";
   static final int VEHICLE_TYPE_14_PRADO_HIGH_PANORAMIC = 34;
   static final int VEHICLE_TYPE_16_RAV4 = 1;
   static final int VEHICLE_TYPE_18_CAMRY_AUTO_AC = 49;
   static final int VEHICLE_TYPE_CAMRY = 48;
   static final int VEHICLE_TYPE_HIGHLANDER = 17;
   static final int VEHICLE_TYPE_LAND_CRUISER_4_ZONE = 33;
   static final int VEHICLE_TYPE_LAND_CRUISER_PRADO = 32;
   static final int VEHICLE_TYPE_TUNDRA = 19;
   private final int DATA_TYPE = 1;
   private final int INVAILE_VALUE = -1;
   private final String TAG = "_165_MsgMgr";
   private int[] mAirFrontDataNow;
   private int[] mAirRearDataNow;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private SparseArray mCanbusDataArray;
   private Context mContext;
   private DecimalFormat mDecimalFormat0p0;
   private int mDifferent;
   private int mEachId;
   private boolean mFrontStatus;
   private Handler mHandler;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mPanoramicStatusNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private boolean mSubSeatBeltStatus;
   private int mTireAlertStatus;

   private boolean compare(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var2[var3].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   private int getAirWhat() {
      int[] var4 = this.mCanBusInfoInt;
      int var1 = var4.length;
      byte var2 = 0;
      if (var1 > 11) {
         var1 = var4[11];
      } else {
         var1 = 0;
      }

      int[] var6 = new int[]{var4[2], var4[3] & 239, var4[4], var4[5], 0, 0};
      int var3 = var4[6];
      var6[4] = var3 & 245;
      var6[5] = var1;
      int[] var5 = new int[]{var3 & 2, var4[8], var4[9], var4[10]};
      short var7;
      if (!Arrays.equals(this.mAirFrontDataNow, var6)) {
         var7 = 1001;
      } else {
         var7 = var2;
         if (!Arrays.equals(this.mAirRearDataNow, var5)) {
            var7 = 1002;
         }
      }

      this.mAirFrontDataNow = Arrays.copyOf(var6, 6);
      this.mAirRearDataNow = Arrays.copyOf(var5, 4);
      return var7;
   }

   private int getDriveData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private String getDriveData(int var1, int var2, String var3) {
      var1 = var1 * 256 + var2;
      return var1 == 65535 ? " " : var1 + var3;
   }

   private String getFuelUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " MPG";
      } else if (var1 == 1) {
         var2 = " KM/L";
      } else if (var1 == 2) {
         var2 = " L/100KM";
      } else if (var1 == 3) {
         var2 = " MPG(UK)";
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getMediaStatus() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      if (var1 != 15) {
         switch (var1) {
            case 0:
               return "IDLE";
            case 1:
               return "LOAD";
            case 2:
               return "WAIT";
            case 3:
               return "DISC READING";
            case 4:
               return "PLAY";
            case 5:
               return "EJECT";
            case 6:
               return "PAUSE";
            case 7:
               return "STOP";
            case 8:
               return "UNRECOGNIZED";
            default:
               return " ";
         }
      } else {
         return "ERROR " + (this.mCanBusInfoInt[3] & 15);
      }
   }

   private String getMileageUnit(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = " MILE";
      } else if (var1 == 2) {
         var2 = " KM";
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getPlayMode() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 255) {
         switch (var1) {
            case 0:
               return "OFF";
            case 1:
               return "DISC";
            case 2:
               return "DISC CD";
            case 3:
               return "DISC DVD";
            case 4:
               return "SD";
            case 5:
               return "USB";
            case 6:
               return "A/V";
            default:
               return " ";
         }
      } else {
         return "UNKNOW";
      }
   }

   private float getTireRule(int var1) {
      if (var1 != 0) {
         return var1 != 2 ? 1.0F : 0.4F;
      } else {
         return 10.0F;
      }
   }

   private String getTireUnit(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            return var1 != 2 ? "" : " KPA";
         } else {
            return " PSI";
         }
      } else {
         return " BAR";
      }
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)SharePreUtil.getIntValue(var1, "share_165_language", 0)});
      }

      ArrayList var2 = new ArrayList();
      var2.add(new byte[]{22, -127, 1});
      var2.add(new byte[]{22, -124, 1, (byte)(GeneralAmplifierData.frontRear + 7)});
      var2.add(new byte[]{22, -124, 2, (byte)(GeneralAmplifierData.leftRight + 7)});
      var2.add(new byte[]{22, -124, 4, (byte)(GeneralAmplifierData.bandBass + 2)});
      var2.add(new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandTreble + 2)});
      var2.add(new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandMiddle + 2)});
      var2.add(new byte[]{22, -124, 7, (byte)GeneralAmplifierData.volume});
      var2.add(new byte[]{22, -124, 8, 1});
      if (this.mDifferent >= 16) {
         var2.add(new byte[]{22, -30, (byte)this.mEachId});
      }

      TimerUtil var3 = new TimerUtil();
      var3.startTimer(new TimerTask(this, var2, var3) {
         int i;
         final MsgMgr this$0;
         final List val$commandList;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$commandList = var2;
            this.val$timerUtil = var3;
            this.i = 0;
         }

         public void run() {
            if (this.i < this.val$commandList.size()) {
               CanbusMsgSender.sendMsg((byte[])this.val$commandList.get(this.i));
               ++this.i;
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 100L, 100L);
   }

   private void initHandler(Context var1) {
      this.mHandler = new Handler(this, Looper.getMainLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 2) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private void intiSettingItem(SettingPageUiSet var1) {
      this.mSettingItemIndeHashMap = new HashMap();
      List var6 = var1.getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var4 = ((SettingPageUiSet.ListBean)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var4.get(var3);
            String var5 = var7.getTitleSrn();
            if (var7.getStyle() == 2) {
               this.mSettingItemIndeHashMap.put(var5, new SettingProgressItem(this, var5, var2, var3, var7.getMin()));
            } else {
               this.mSettingItemIndeHashMap.put(var5, new SettingNormalItem(this, var5, var2, var3));
            }
         }
      }

   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mSubSeatBeltStatus == GeneralDoorData.isSubSeatBeltTie) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         this.mSubSeatBeltStatus = GeneralDoorData.isSubSeatBeltTie;
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      boolean var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
         return true;
      }
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private String resolveAirTemp(Context var1, int var2) {
      if (var2 == 0) {
         return "LOW";
      } else if (var2 == 31) {
         return "HIGH";
      } else {
         String var3;
         if (GeneralAirData.fahrenheit_celsius) {
            if (var2 >= 1 && var2 <= 29) {
               var3 = var2 + 64 + this.getTempUnitF(var1);
               return var3;
            }

            if (var2 >= 33 && var2 <= 38) {
               var3 = var2 + 26 + this.getTempUnitF(var1);
               return var3;
            }
         } else {
            if (var2 >= 1 && var2 <= 29) {
               var3 = (float)(var2 + 35) / 2.0F + this.getTempUnitC(var1);
               return var3;
            }

            if (var2 >= 33 && var2 <= 38) {
               var3 = (float)(var2 - 3) / 2.0F + this.getTempUnitC(var1);
               return var3;
            }
         }

         var3 = " ";
         return var3;
      }
   }

   private int resolveAmpData(int var1, int var2) {
      return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1], var2, 4);
   }

   private String resolveOutDoorTemperature(Context var1) {
      float var2 = (float)this.mCanBusInfoInt[7] / 2.0F - 40.0F;
      if (GeneralAirData.fahrenheit_celsius) {
         var2 = var2 * 9.0F / 5.0F;
         return this.mDecimalFormat0p0.format((double)(var2 + 32.0F)) + this.getTempUnitF(var1);
      } else {
         return var2 + this.getTempUnitC(var1);
      }
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = Message.obtain();
         var5.what = 1;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void set0x16VehicleSpeed() {
      ArrayList var2 = new ArrayList();
      StringBuilder var4 = new StringBuilder();
      DecimalFormat var3 = this.mDecimalFormat0p0;
      int[] var5 = this.mCanBusInfoInt;
      int var1 = var5[3];
      var2.add(new DriverUpdateEntity(0, 0, var4.append(var3.format((double)((float)(var5[2] | var1 << 8) / 16.0F))).append(" km/h").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var6[3], var6[2]));
   }

   private void set0x1DFrontRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x1ERearRadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var5 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var5[2], var5[3], var5[4], var5[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1);
         String var6;
         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            var6 = "open";
         } else {
            var6 = "close";
         }

         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3);
         this.setSettingData(new String[]{"_283_rear_radar_alert_distance", "_283_front_radar_alert_distance", "_283_radar_switch", "radar_volume"}, new Object[]{var3, var2, var6, var4});
      }

   }

   private void set0x1FHybrid() {
      SharePreUtil.setBoolValue(this.mContext, "share_165_is_suppot_hybrid", DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      this.updateHybirdActivity((Bundle)null);
      int[] var3 = this.mCanBusInfoInt;
      if (var3.length > 4) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(var3[4], 0, 1);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1);
         this.setSettingData(new String[]{"_165_eco_mode", "_165_ev_mode"}, new Object[]{var1, var2});
      }

   }

   private void set0x20WheelKey(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     switch (var2) {
                        case 7:
                           this.realKeyLongClick1(var1, 2);
                           break;
                        case 8:
                           this.realKeyLongClick1(var1, 187);
                           break;
                        case 9:
                           this.realKeyLongClick1(var1, 14);
                           break;
                        case 10:
                           this.realKeyLongClick1(var1, 15);
                           break;
                        default:
                           switch (var2) {
                              case 19:
                                 this.realKeyLongClick1(var1, 45);
                                 break;
                              case 20:
                                 this.realKeyLongClick1(var1, 46);
                                 break;
                              case 21:
                                 this.realKeyLongClick1(var1, 50);
                                 break;
                              case 22:
                                 this.realKeyLongClick1(var1, 49);
                                 break;
                              default:
                                 switch (var2) {
                                    case 129:
                                       this.realKeyLongClick1(var1, 7);
                                       break;
                                    case 130:
                                       this.realKeyLongClick1(var1, 8);
                                       break;
                                    case 131:
                                       this.realKeyLongClick1(var1, 45);
                                       break;
                                    case 132:
                                       this.realKeyLongClick1(var1, 46);
                                       break;
                                    case 133:
                                       this.realKeyLongClick1(var1, 21);
                                       break;
                                    case 134:
                                       this.realKeyLongClick1(var1, 20);
                                       break;
                                    case 135:
                                       this.realKeyLongClick1(var1, 134);
                                       break;
                                    case 136:
                                       this.realKeyLongClick1(var1, 2);
                                 }
                           }
                     }
                  } else {
                     this.realKeyLongClick1(var1, 47);
                  }
               } else {
                  this.realKeyLongClick1(var1, 48);
               }
            } else {
               this.realKeyLongClick1(var1, 8);
            }
         } else {
            this.realKeyLongClick1(var1, 7);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private void set0x21TripInfoOne() {
      String var5 = this.getMileageUnit(this.mCanBusInfoInt[8]);
      ArrayList var4 = new ArrayList();
      int[] var6 = this.mCanBusInfoInt;
      int var1 = this.getDriveData(var6[2], var6[3]);
      var6 = this.mCanBusInfoInt;
      int var3 = this.getDriveData(var6[4], var6[5]);
      var6 = this.mCanBusInfoInt;
      int var2 = this.getDriveData(var6[6], var6[7]);
      var1 = DataHandleUtils.rangeNumber(var1, 9999);
      var3 = DataHandleUtils.rangeNumber(var3, 5999);
      var2 = DataHandleUtils.rangeNumber(var2, 9999);
      var4.add(new DriverUpdateEntity(0, 1, (float)var1 / 10.0F + var5 + "/H"));
      var4.add(new DriverUpdateEntity(0, 4, var3 / 60 + " H " + var3 % 60 + " M"));
      var4.add(new DriverUpdateEntity(0, 5, var2 + var5));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x22TripInfoTwo() {
      String var2 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var4 = new ArrayList();
      int[] var3 = this.mCanBusInfoInt;
      int var1 = this.getDriveData(var3[3], var3[4]);
      var4.add(new DriverUpdateEntity(0, 3, (float)var1 / 10.0F + var2));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x23HistoricalFuel() {
      String var4 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var5 = new ArrayList();

      for(int var1 = 0; var1 < 6; ++var1) {
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var1 * 2;
         var2 = this.getDriveData(var3[var2 + 3], var3[var2 + 4]);
         String var6 = (float)var2 / 10.0F + var4;
         if (var2 == 65535) {
            var6 = "";
         }

         var5.add(new DriverUpdateEntity(1, var1, var6));
      }

      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x24DoorData() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var1;
      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1 = false;
      } else {
         var1 = true;
      }

      GeneralDoorData.isBackOpen = var1;
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x25TireData(Context var1) {
      boolean var4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      SharePreUtil.setBoolValue(this.mContext, "share_165_is_suppot_tire", var4);
      if (var4) {
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         if (this.mTireAlertStatus != var3) {
            this.mTireAlertStatus = var3;
            if (var3 == 1) {
               this.startTireActivity(var1);
            }
         }

         String var6 = this.getTireUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
         float var2 = this.getTireRule(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
         GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         ArrayList var5 = new ArrayList();
         var5.add(new TireUpdateEntity(0, var3, new String[]{(float)this.mCanBusInfoInt[3] / var2 + var6}));
         var5.add(new TireUpdateEntity(1, var3, new String[]{(float)this.mCanBusInfoInt[4] / var2 + var6}));
         var5.add(new TireUpdateEntity(2, var3, new String[]{(float)this.mCanBusInfoInt[5] / var2 + var6}));
         var5.add(new TireUpdateEntity(3, var3, new String[]{(float)this.mCanBusInfoInt[6] / var2 + var6}));
         if (GeneralTireData.isHaveSpareTire) {
            var5.add(new TireUpdateEntity(4, var3, new String[]{(float)this.mCanBusInfoInt[7] / var2 + var6}));
         }

         GeneralTireData.dataList = var5;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void set0x26VehicleSettings() {
      int[] var22 = this.mCanBusInfoInt;
      int var1;
      if (var22.length > 6) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var22[6], 0, 2);
      } else {
         var1 = 0;
      }

      int var2 = var1;
      if (var1 == 3) {
         var2 = 2;
      }

      var22 = this.mCanBusInfoInt;
      if (var22.length > 7) {
         var1 = DataHandleUtils.getIntFromByteWithBit(var22[7], 0, 3);
      } else {
         var1 = 0;
      }

      int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
      int var21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
      int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
      int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      this.setSettingData(new String[]{"light_ctrl_3", "_18_vehicle_setting_item_3_210", "_18_vehicle_setting_item_3_43", "_55_0x67_data1_bit32", "_55_0x65_data1_bit54_item1", "_18_vehicle_setting_item_1_2", "_18_vehicle_setting_item_1_3", "_18_vehicle_setting_item_2_6", "hiworld_jeep_123_0x60_data1_65", "_18_vehicle_setting_item_2_4", "_18_vehicle_setting_item_1_4", "_18_vehicle_setting_item_1_5", "_18_vehicle_setting_item_2_5", "_18_vehicle_setting_item_2_7", "_11_0x26_data2_bit20", "_18_vehicle_setting_item_1_1", "_18_vehicle_setting_item_1_0", "_11_0x26_data3_bit32", "_55_0x65_data1_bit21", "_11_0x26_data4_bit65", "_165_sterring_column"}, new Object[]{var16, var12, var11, var4, var6, var15, var9, var21, var10, var18, var13, var3, var8, var20, var7, var14, var19, var5, var17, var2, var1});
   }

   private void set0x27TripInfoThree() {
      String var4 = this.getFuelUnit(this.mCanBusInfoInt[2]);
      ArrayList var5 = new ArrayList();

      for(int var1 = 0; var1 < 15; ++var1) {
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var1 * 2;
         var2 = this.getDriveData(var3[31 - var2], var3[32 - var2]);
         String var6 = (float)var2 / 10.0F + var4;
         if (var2 == 65535) {
            var6 = "";
         }

         var5.add(new DriverUpdateEntity(2, var1, var6));
      }

      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x28AirData(Context var1) {
      GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      GeneralAirData.rear_dual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      this.updateOutDoorTemp(var1, this.resolveOutDoorTemperature(var1));
      int var4 = this.getAirWhat();
      byte[] var9 = this.mCanBusInfoByte;
      Integer var8 = 3;
      var9[3] = (byte)(var9[3] & 239);
      boolean var6 = false;
      var9[7] = 0;
      if (!this.isAirMsgRepeat(var9)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[5]);
         GeneralAirData.rear_left_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[8]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
         GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         if (this.mDifferent == 33) {
            GeneralAirData.rear_right_temperature = this.resolveAirTemp(var1, this.mCanBusInfoInt[10]);
         } else {
            GeneralAirData.rear_right_temperature = GeneralAirData.rear_left_temperature;
         }

         if (this.mDifferent == 33) {
            int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
            int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
            boolean var7 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
            GeneralAirData.front_left_blow_head = this.compare(var2, 1, 2);
            boolean var5;
            if (!this.compare(var2, 2, var8) && !var7) {
               var5 = false;
            } else {
               var5 = true;
            }

            label24: {
               GeneralAirData.front_left_blow_foot = var5;
               GeneralAirData.front_left_blow_window = var7;
               GeneralAirData.front_right_blow_head = this.compare(var3, 1, 2);
               if (!this.compare(var3, 2, var8)) {
                  var5 = var6;
                  if (!var7) {
                     break label24;
                  }
               }

               var5 = true;
            }

            GeneralAirData.front_right_blow_foot = var5;
            GeneralAirData.front_right_blow_window = var7;
         }

         this.updateAirActivity(this.mContext, var4);
      }
   }

   private void set0x29TrackData() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 380, 12);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x30VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void set0x31AmplifierData() {
      GeneralAmplifierData.frontRear = this.resolveAmpData(2, 4) - 7;
      GeneralAmplifierData.leftRight = this.resolveAmpData(2, 0) - 7;
      GeneralAmplifierData.bandBass = this.resolveAmpData(3, 4) - 2;
      GeneralAmplifierData.bandTreble = this.resolveAmpData(3, 0) - 2;
      GeneralAmplifierData.bandMiddle = this.resolveAmpData(4, 4) - 2;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData(this.mContext, this.mCanId);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1);
      this.setSettingData(new String[]{"_186_asl", "_18_surround"}, new Object[]{var2, var1});
   }

   private void set0x32SystemInfo() {
      if (this.isPanoramicStatusChange()) {
         this.forceReverse(this.mContext, this.mPanoramicStatusNow);
      }

      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
      this.setSettingData(new String[]{"amplifier_switch"}, new Object[]{var1});
   }

   private void set0x35SeatHeatStatus(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4), 4);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4), 4);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4), 4);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4), 4);
         this.updateAirActivity(var1, 1001);
      }

   }

   private void set0x50EnginSpeed() {
      ArrayList var3 = new ArrayList();
      StringBuilder var4 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[3];
      var3.add(new DriverUpdateEntity(0, 2, var4.append(var2[2] | var1 << 8).append(" rpm").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x66CeilingScreen() {
      GeneralOriginalCarDeviceData.cdStatus = this.getPlayMode();
      GeneralOriginalCarDeviceData.runningState = this.getMediaStatus();
      GeneralOriginalCarDeviceData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralOriginalCarDeviceData.lock = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setSettingData(String[] var1, Object[] var2) {
      if (var1.length == var2.length) {
         ArrayList var4 = new ArrayList();

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (this.mSettingItemIndeHashMap.containsKey(var1[var3])) {
               var4.add(((SettingItem)this.mSettingItemIndeHashMap.get(var1[var3])).getEntity(var2[var3]));
            }
         }

         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      } else {
         Log.i("_165_MsgMgr", "setSettingData: Unequal length");
      }

   }

   private void startTireActivity(Context var1) {
      if (!SystemUtil.isForeground(var1, Constant.TireInfoActivity.getClassName())) {
         Intent var2 = new Intent();
         var2.setComponent(Constant.TireInfoActivity);
         var2.setFlags(268435456);
         var1.startActivity(var2);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      AppEnableUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.mCanbusDataArray = new SparseArray();
      this.mDecimalFormat0p0 = new DecimalFormat("0.0");
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      this.initHandler(var1);
      this.intiSettingItem(UiMgrFactory.getCanUiMgr(var1).getSettingUiSet(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 22) {
         if (var3 != 53) {
            if (var3 != 80) {
               if (var3 != 102) {
                  switch (var3) {
                     case 29:
                        this.set0x1DFrontRadarData(var1);
                        break;
                     case 30:
                        this.set0x1ERearRadarData(var1);
                        break;
                     case 31:
                        this.set0x1FHybrid();
                        break;
                     case 32:
                        this.set0x20WheelKey(var1);
                        break;
                     case 33:
                        this.set0x21TripInfoOne();
                        break;
                     case 34:
                        this.set0x22TripInfoTwo();
                        break;
                     case 35:
                        this.set0x23HistoricalFuel();
                        break;
                     case 36:
                        this.set0x24DoorData();
                        break;
                     case 37:
                        this.set0x25TireData(var1);
                        break;
                     case 38:
                        this.set0x26VehicleSettings();
                        break;
                     case 39:
                        this.set0x27TripInfoThree();
                        break;
                     case 40:
                        this.set0x28AirData(var1);
                        break;
                     case 41:
                        this.set0x29TrackData();
                        break;
                     default:
                        switch (var3) {
                           case 48:
                              this.set0x30VersionInfo();
                              break;
                           case 49:
                              this.set0x31AmplifierData();
                              break;
                           case 50:
                              this.set0x32SystemInfo();
                        }
                  }
               } else {
                  this.set0x66CeilingScreen();
               }
            } else {
               this.set0x50EnginSpeed();
            }
         } else {
            this.set0x35SeatHeatStatus(var1);
         }
      } else {
         this.set0x16VehicleSpeed();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initAmplifier(var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private abstract class SettingItem {
      private int left;
      private int right;
      final MsgMgr this$0;
      private String title;

      public SettingItem(MsgMgr var1, String var2, int var3, int var4) {
         this.this$0 = var1;
         this.title = var2;
         this.left = var3;
         this.right = var4;
      }

      public abstract SettingUpdateEntity getEntity(Object var1);

      public int getLeft() {
         return this.left;
      }

      public int getRight() {
         return this.right;
      }

      public String getTitle() {
         return this.title;
      }
   }

   private class SettingNormalItem extends SettingItem {
      final MsgMgr this$0;

      public SettingNormalItem(MsgMgr var1, String var2, int var3, int var4) {
         super(var1, var2, var3, var4);
         this.this$0 = var1;
      }

      public SettingUpdateEntity getEntity(Object var1) {
         return new SettingUpdateEntity(this.getLeft(), this.getRight(), var1);
      }
   }

   private class SettingProgressItem extends SettingItem {
      private int min;
      final MsgMgr this$0;

      public SettingProgressItem(MsgMgr var1, String var2, int var3, int var4, int var5) {
         super(var1, var2, var3, var4);
         this.this$0 = var1;
         this.min = var5;
      }

      public SettingUpdateEntity getEntity(Object var1) {
         return (new SettingUpdateEntity(this.getLeft(), this.getRight(), var1)).setProgress((Integer)var1 - this.min);
      }
   }
}
