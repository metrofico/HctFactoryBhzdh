package com.hzbhd.canbus.car._283;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.PA6Utils;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MainActivity;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.activity.AirActivity;
import com.hzbhd.canbus.car_cus._283.activity.AirCleanActivity;
import com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingActivity;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingPersonalActivity;
import com.hzbhd.canbus.car_cus._283.activity.MeterActivity;
import com.hzbhd.canbus.car_cus._283.bean.DriveData;
import com.hzbhd.canbus.car_cus._283.bean.TimeSyncMode;
import com.hzbhd.canbus.car_cus._283.smart.SmartPowerActivity;
import com.hzbhd.canbus.car_cus._283.view.AirSeatView;
import com.hzbhd.canbus.car_cus._283.view.RadarView;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.BtApi;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.VoiceControlData;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   public static boolean CHANG_TIME;
   public static final ComponentName PA6SAirActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._283.activity.AirActivity");
   private static final String SAVE_METER_COLOR_B = "_283_meter_colorB";
   private static final String SAVE_METER_COLOR_G = "_283_meter_colorG";
   private static final String SAVE_METER_COLOR_R = "_283_meter_colorR";
   private static final String SAVE_METER_METER_LEFT_INT = "_283_meter_leftRotateInt";
   private static final String SAVE_METER_METER_MODE = "_283_meter_meter_mode";
   private static final String SAVE_METER_METER_RIGHT_INT = "_283_meter_rightRotateInt";
   public static final String SHARE_283_AIR_SYNC = "_283_air_sync";
   public static final String SHARE_283_DRIVER_MODE = "_283_driver_mode";
   private static final String SHARE_283_SMARTPOWER_DATA3 = "_283_smart_data3";
   private static final String SHARE_283_SMARTPOWER_DATA4 = "_283_smart_data4";
   private static final String SHARE_283_SMARTPOWER_DATA5 = "_283_smart_data5";
   private static final String SHARE_283_SMARTPOWER_DATA6 = "_283_smart_data6";
   private static final String SHARE_283_SMARTPOWER_MODE = "_283_smart_mode";
   private static final int WHAT_UPDATE_BT_NAME = 0;
   private static final int WHAT_UPDATE_BT_OperatorName = 1;
   int a = 0;
   int b = 0;
   private int data3;
   private int data4;
   private int data5;
   private int data6;
   private boolean isFirstAirSeat = true;
   private int left_front_hot;
   private int left_front_ventilate;
   private AMapBroadcast mAMapBroadcast;
   int[] mAirData;
   private AirSeatView mAirSeatView;
   private String mBlueToothName = "";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusKeyCopy;
   private byte[] mCanbusKeyModeCopy;
   private Context mContext;
   DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
   private Handler mHandler = new Handler(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 == 1) {
               this.this$0.sendOperatorName();
            }
         } else if (this.this$0.mContext != null) {
            String var3 = PA6Utils.getBtName(this.this$0.mContext);
            Log.d("scyscyscy", "-------->联系人名称：" + var3);
            if (!TextUtils.isEmpty(var3)) {
               byte[] var4 = var3.getBytes();
               CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -107, 0}, var4), 27));
            } else {
               this.this$0.mHandler.sendEmptyMessageDelayed(0, 2500L);
            }
         }

      }
   };
   GPSTimeManager.OnGPSTimeCallBack mOnGPSTimeCallBack = new GPSTimeManager.OnGPSTimeCallBack(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onTimeCallBack(int var1, int var2, int var3, int var4, int var5, int var6) {
         this.this$0.sendTimeCommonds(var1, var2, var3, var4, var5, true);
      }
   };
   private String mOperatorName = "";
   private CusPanoramicView mPanoramicView;
   private RadarView mRadarView;
   private int mSystemDateFormat = 2;
   private int mode;
   private int mode_int;
   int now0x12Data2 = 0;
   private int nowVlume;
   byte[] nowWayNameByte;
   byte[] otherInfoByte;
   int result;
   private int right_front_hot;
   private int right_front_ventilate;
   private int sendTimeTag = 0;
   private int tempDrivingMode = -1;
   private int version;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void addTemp(byte var1, String var2, float var3) {
      Exception var10000;
      label51: {
         boolean var10001;
         try {
            if (var2.equals("HI")) {
               return;
            }
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label51;
         }

         float var4;
         label44: {
            label43: {
               try {
                  if (!GeneralDzData.fahrenheit_celsius) {
                     return;
                  }

                  if (var2.equals("LO")) {
                     break label43;
                  }
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label51;
               }

               try {
                  var4 = Float.valueOf(var2.replace(this.mContext.getString(2131770016), ""));
                  break label44;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label51;
               }
            }

            var4 = 16.0F;
         }

         var3 = (var4 + var3) * 2.0F;
         if (var3 >= 59.0F) {
            try {
               MessageSender.sendMsg(new byte[]{22, 58, var1, -1});
               return;
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
            }
         } else {
            try {
               MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
               return;
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
            }
         }
      }

      Exception var10 = var10000;
      var10.printStackTrace();
   }

   private void addTempCommonds(String var1) {
      (new Thread(new MsgMgr$$ExternalSyntheticLambda2(this, var1))).start();
   }

   private void canBusInfo0x55(byte[] var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[1];
      if (var2 != 23) {
         if (var2 != 24) {
            if (var2 != 30) {
               if (var2 != 31) {
                  if (var2 != 49) {
                     if (var2 != 53) {
                        if (var2 != 100) {
                           if (var2 != 118) {
                              if (var2 != 136) {
                                 if (var2 != 232) {
                                    if (var2 != 240) {
                                       if (var2 != 65) {
                                          if (var2 != 66) {
                                             if (var2 != 133) {
                                                if (var2 != 134) {
                                                   if (var2 != 193) {
                                                      if (var2 != 194) {
                                                         switch (var2) {
                                                            case 17:
                                                               this.setCarData0x11();
                                                               this.keyControl0x11();
                                                               this.updateSpeedInfo(this.mCanBusInfoInt[3]);
                                                               break;
                                                            case 18:
                                                               this.setCarData0x12();
                                                               this.setDoor0x12();
                                                               break;
                                                            case 19:
                                                               this.setCarData0x13();
                                                               break;
                                                            case 20:
                                                               this.setCarData0x14();
                                                               break;
                                                            case 21:
                                                               this.setCarData0x15();
                                                               break;
                                                            default:
                                                               switch (var2) {
                                                                  case 69:
                                                                     CommUtil.printHexString("111111收：", var1);
                                                                     this.setParking0x45();
                                                                     break;
                                                                  case 70:
                                                                     this.setTmps0x46();
                                                                     break;
                                                                  case 71:
                                                                     this.setDriverAssistant0x47();
                                                                     break;
                                                                  case 72:
                                                                     this.setTmps0x48();
                                                                     break;
                                                                  case 73:
                                                                     if (!this.isDataChange(var3)) {
                                                                        return;
                                                                     }

                                                                     this.setHybrid0x49();
                                                                     break;
                                                                  default:
                                                                     switch (var2) {
                                                                        case 103:
                                                                           this.setLight0x67();
                                                                           break;
                                                                        case 104:
                                                                           this.setLight0x68();
                                                                           break;
                                                                        case 105:
                                                                           this.setRear0x69();
                                                                     }
                                                               }
                                                         }
                                                      } else {
                                                         this.setTime0xC2();
                                                      }
                                                   } else {
                                                      this.setUnit0xC1();
                                                   }
                                                } else {
                                                   CommUtil.printHexString("scyscyscy:Mode DCC 收 ---------->", var1);
                                                   this.setDrivingMode0x86();
                                                }
                                             } else {
                                                this.setSport0x85();
                                             }
                                          } else {
                                             this.setDyLeftRightRadar0x42();
                                             this.setPublicLeftRightRadar0x42();
                                          }
                                       } else {
                                          this.setDyFrontRearRadar0x41();
                                          this.setPublicFrontRearRadar0x41();
                                       }
                                    } else {
                                       this.setCanVersion0xF0();
                                    }
                                 } else {
                                    if (!this.isDataChange(var3)) {
                                       return;
                                    }

                                    this.setCamera0xE8();
                                 }
                              } else {
                                 this.setHybrid0x88();
                              }
                           } else {
                              this.setCombination0x76();
                           }
                        } else {
                           this.setDoor0x64();
                        }
                     } else {
                        if (this.isAirMsgRepeat(var1)) {
                           return;
                        }

                        this.setAir0x35();
                     }
                  } else {
                     if (this.isAirDataNoChange()) {
                        return;
                     }

                     this.setAir0x31();
                  }
               } else {
                  this.setUpKeep0x1F();
               }
            } else {
               this.setUpKeep0x1E();
            }
         } else {
            this.setHybridView0x18();
         }
      } else {
         this.setDriveStatusHybrid0x17();
      }

   }

   private void canBusInfo0x57(byte[] var1) {
      GeneralDzSmartData.mode = this.mCanBusInfoInt[1];
      GeneralDzSmartData.mode_int = this.getModeInt(this.mCanBusInfoInt);
      GeneralDzSmartData.data3 = this.mCanBusInfoInt[2];
      GeneralDzSmartData.data4 = this.mCanBusInfoInt[3];
      GeneralDzSmartData.data5 = this.mCanBusInfoInt[4];
      GeneralDzSmartData.data6 = this.mCanBusInfoInt[5];
      GeneralDzSmartData.version = this.mCanBusInfoInt[7];
      if (this.mode != GeneralDzSmartData.mode || this.mode_int != GeneralDzSmartData.mode_int || this.data3 != GeneralDzSmartData.data3 || this.data4 != GeneralDzSmartData.data4 || this.data5 != GeneralDzSmartData.data5 || this.data6 != GeneralDzSmartData.data6 || this.version != GeneralDzSmartData.version) {
         GeneralDzSmartData.show = true;
         this.mode = GeneralDzSmartData.mode;
         this.mode_int = GeneralDzSmartData.mode_int;
         this.data3 = GeneralDzSmartData.data3;
         this.data4 = GeneralDzSmartData.data4;
         this.data5 = GeneralDzSmartData.data5;
         this.data6 = GeneralDzSmartData.data6;
         this.version = GeneralDzSmartData.version;
         this.updateSmartActivity();
         SharePreUtil.setIntValue(this.mContext, "_283_smart_mode", GeneralDzSmartData.mode);
         SharePreUtil.setIntValue(this.mContext, "_283_smart_data3", GeneralDzSmartData.data3);
         SharePreUtil.setIntValue(this.mContext, "_283_smart_data4", GeneralDzSmartData.data4);
         SharePreUtil.setIntValue(this.mContext, "_283_smart_data5", GeneralDzSmartData.data5);
         SharePreUtil.setIntValue(this.mContext, "_283_smart_data6", GeneralDzSmartData.data6);
      }

      this.updateSystemUIDrivingPattern(GeneralDzSmartData.mode);
   }

   private void canBusInfo0x59(byte[] var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[6];
      if (var2 != 32) {
         if (var2 == 33) {
            GeneralDzData.colorR = var3[9];
            GeneralDzData.colorG = this.mCanBusInfoInt[10];
            GeneralDzData.colorB = this.mCanBusInfoInt[11];
         }
      } else {
         GeneralDzData.meter_mode = var3[9];
         GeneralDzData.leftRotateInt = this.mCanBusInfoInt[10];
         GeneralDzData.rightRotateInt = this.mCanBusInfoInt[11];
      }

      this.updateMeterActivity();
      this.saveMeterData();
   }

   private String getBandUnit(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
         case 1:
            return "KHz";
         case 2:
         case 3:
         case 4:
            return "MHz";
         default:
            return "";
      }
   }

   private String getCarImei(byte[] var1) {
      try {
         String var3 = new String(var1, "ascii");
         return var3;
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         return "";
      }
   }

   private String getEnergy(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131760636);
         case 2:
            return this.mContext.getString(2131760637);
         case 3:
            return this.mContext.getString(2131760638);
         case 4:
            return this.mContext.getString(2131760639);
         case 5:
            return this.mContext.getString(2131760640);
         case 6:
            return this.mContext.getString(2131760641);
         case 7:
            return this.mContext.getString(2131760642);
         default:
            return this.mContext.getString(2131760635);
      }
   }

   public static int getLsb(int var0) {
      return (var0 & -1) >> 0 & 255;
   }

   public static int getMidLsb(int var0) {
      return (var0 & -1) >> 8 & 255;
   }

   public static int getMidMsb(int var0) {
      return (var0 & -1) >> 16 & 255;
   }

   private int getModeInt(int[] var1) {
      if (var1.length <= 4) {
         return GeneralDzSmartData.mode;
      } else {
         int var4 = var1[1];
         int var2 = var1[2];
         byte var3 = 0;
         if (var4 == 3 || var4 == 4) {
            var2 = var1[3];
         }

         if (var4 == 3 || var4 == 0) {
            var3 = 4;
         }

         return DataHandleUtils.getIntFromByteWithBit(var2, var3, 4) - 1;
      }
   }

   public static int getMsb(int var0) {
      return (var0 & -1) >> 24 & 255;
   }

   private CusPanoramicView getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (CusPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      return this.mPanoramicView;
   }

   private String getOperator(Bundle var1) {
      if (var1 == null) {
         return " ";
      } else {
         String var2 = var1.getString("operatorName");
         if (var2.equals("CHN-UNICOM")) {
            return this.mContext.getString(2131760711);
         } else if (var2.equals("CMCC")) {
            return this.mContext.getString(2131760712);
         } else {
            String var3 = var2;
            if (var2.equals("CHN-CT")) {
               var3 = this.mContext.getString(2131760713);
            }

            return var3;
         }
      }
   }

   private int getTwoData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private String getTwoDataTime(int var1, int var2) {
      var1 = var1 * 256 + var2;
      return var1 / 60 + "  h  " + var1 % 60;
   }

   private String getTwoDataVehicle(int var1, int var2) {
      return String.format("%.1f", (float)(var1 * 256 + var2) * 0.1F);
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isOtherInfoChange(byte[] var1) {
      if (Arrays.equals(this.otherInfoByte, var1)) {
         return false;
      } else {
         this.otherInfoByte = var1;
         return true;
      }
   }

   private boolean isWayNameChange(byte[] var1) {
      if (Arrays.equals(this.nowWayNameByte, var1)) {
         return false;
      } else {
         this.nowWayNameByte = var1;
         return true;
      }
   }

   private void keyControl0x11() {
      int var2 = this.mCanBusInfoInt[4];
      byte var1 = 8;
      switch (var2) {
         case 0:
            this.realKeyClick(0);
            this.realKeyLongClick(0);
            this.isKeyModeMsgRepeat(new byte[]{0, 0});
            break;
         case 1:
            this.realKeyLongClick(7);
            this.realKeyClick(7);
            break;
         case 2:
            this.realKeyLongClick(8);
            this.realKeyClick(8);
            break;
         case 3:
            this.realKeyClick(3);
            break;
         case 4:
            this.realKeyClick(187);
            break;
         case 5:
            this.realKeyClick(188);
         case 6:
         case 7:
         default:
            break;
         case 8:
            this.realKeyClick(466);
            break;
         case 9:
            this.realKeyClick(465);
            break;
         case 10:
         case 11:
            this.realKeyClick(2);
            break;
         case 12:
            CommUtil.printHexString("scyscyscy:Mode DCC key 1---------->", this.mCanBusInfoByte);
            byte[] var3 = this.mCanBusInfoByte;
            if (!this.isKeyModeMsgRepeat(new byte[]{var3[4], var3[5]})) {
               CommUtil.printHexString("scyscyscy:Mode DCC key 2---------->", this.mCanBusInfoByte);
               if (this.mCanBusInfoInt[5] != 0) {
                  CommUtil.printHexString("scyscyscy:Mode DCC key 3---------->", this.mCanBusInfoByte);
                  SystemUtil.isForeground(this.mContext, AirSettingActivity.class.getName());
                  var2 = GeneralDzData.drivingMode;
                  Log.d("scyscyscy", "scyscyscy:Mode DCC key 4---------->" + var2 + "<----->" + this.tempDrivingMode);
                  if (var2 != -1 && var2 != this.tempDrivingMode) {
                     switch (var2) {
                        case 1:
                           var1 = 6;
                           break;
                        case 2:
                           var1 = 1;
                        case 3:
                           break;
                        case 4:
                           var1 = 5;
                           break;
                        case 5:
                           var1 = 4;
                           break;
                        case 6:
                           var1 = 3;
                           break;
                        case 7:
                           var1 = 2;
                           break;
                        default:
                           var1 = 0;
                     }

                     var3 = new byte[]{22, -117, (byte)var1, 0, 0};
                     CanbusMsgSender.sendMsg(var3);
                     CommUtil.printHexString("scyscyscy:Mode DCC 发 ", var3);
                     this.tempDrivingMode = var2;
                  }
               }
            }
      }

   }

   // $FF: synthetic method
   static void lambda$sendAirCommonds$4(float var0) {
      byte var1 = (byte)((int)(var0 * 2.0F));
      MessageSender.sendMsg(new byte[]{22, 58, 20, var1});

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      MessageSender.sendMsg(new byte[]{22, 58, 21, var1});

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

      MessageSender.sendMsg(new byte[]{22, 58, 22, var1});
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick5(var2, var1, var3[4], var3[5]);
   }

   private void realKeyLongClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void refreRadarUiData() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.getMyPanoramicView().refreRadarUi();
         }
      });
   }

   private String resolveAutoTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (GeneralDzData.fahrenheit_celsius) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = (int)((float)var1 * 0.5F) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTemp() {
      int var1 = this.mCanBusInfoInt[13];
      return (float)var1 * 0.5F - 40.0F + this.getTempUnitC(this.mContext);
   }

   private void saveMeterData() {
      SharePreUtil.setIntValue(this.mContext, "_283_meter_colorR", GeneralDzData.colorR);
      SharePreUtil.setIntValue(this.mContext, "_283_meter_colorG", GeneralDzData.colorG);
      SharePreUtil.setIntValue(this.mContext, "_283_meter_colorB", GeneralDzData.colorB);
      SharePreUtil.setIntValue(this.mContext, "_283_meter_meter_mode", GeneralDzData.meter_mode);
      SharePreUtil.setIntValue(this.mContext, "_283_meter_leftRotateInt", GeneralDzData.leftRotateInt);
      SharePreUtil.setIntValue(this.mContext, "_283_meter_rightRotateInt", GeneralDzData.rightRotateInt);
   }

   private void sendAirCommonds(float var1) {
      (new Thread(new MsgMgr$$ExternalSyntheticLambda4(var1))).start();
   }

   private void sendBlueToothName() {
      byte[] var1 = new byte[0];

      label20: {
         byte[] var2;
         try {
            if (TextUtils.isEmpty(this.mBlueToothName)) {
               this.mBlueToothName = "";
            }

            var2 = this.mBlueToothName.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            break label20;
         }

         var1 = var2;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -107, 0}, var1), 27));
   }

   private void sendGdOtherInfo(byte[] var1) {
      if (this.isOtherInfoChange(var1)) {
         ++this.b;
         MyLog.temporaryTracking("其他信息：" + this.b);
         CanbusMsgSender.sendMsg(var1);
      }

   }

   private void sendHandler(int var1) {
      this.mHandler.removeMessages(var1);
      this.mHandler.sendEmptyMessage(var1);
   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)var2});
   }

   private void sendOperatorName() {
      Log.d("scyscyscy", "mOperatorName---------->" + this.mOperatorName);
      byte[] var1 = new byte[0];

      label20: {
         byte[] var2;
         try {
            if (TextUtils.isEmpty(this.mOperatorName)) {
               this.mOperatorName = "";
            }

            var2 = this.mOperatorName.getBytes("UTF-8");
         } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            break label20;
         }

         var1 = var2;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -106, 0}, var1), 27));
   }

   private void sendSmartPowerMode(int var1) {
      MessageSender.sendDzMsg(var1, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
   }

   private void sendSourceMsg2(String var1, int var2) {
      byte[] var3 = var1.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte)var2, 0}, var3), 27));
   }

   private void sendTimeCommonds(int var1, int var2, int var3, int var4, int var5, boolean var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var6 ^ true), 6, true), (byte)var4, (byte)var5, 0, 0, 1, (byte)var1, (byte)var2, (byte)var3, (byte)this.mSystemDateFormat});
   }

   private void sendWayName(byte[] var1) {
      if (this.isWayNameChange(var1)) {
         ++this.a;
         MyLog.temporaryTracking("道路名称：" + this.a);
         CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -27, 0}, var1));
      }

   }

   private void setAir0x31() {
      GeneralDzData.right_front_ventilate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
      GeneralDzData.left_front_ventilate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
      GeneralDzData.right_front_hot = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      GeneralDzData.left_front_hot = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      GeneralDzData.right_rear_hot = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
      if (this.right_front_ventilate != GeneralDzData.right_front_ventilate || this.left_front_ventilate != GeneralDzData.left_front_ventilate || this.right_front_hot != GeneralDzData.right_front_hot || this.left_front_hot != GeneralDzData.left_front_hot) {
         this.right_front_ventilate = GeneralDzData.right_front_ventilate;
         this.left_front_ventilate = GeneralDzData.left_front_ventilate;
         this.right_front_hot = GeneralDzData.right_front_hot;
         this.left_front_hot = GeneralDzData.left_front_hot;
         if (!this.isFirstAirSeat) {
            this.updateDZAirSeatView(this.mContext);
         } else {
            this.isFirstAirSeat = false;
         }
      }

      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDzData.air_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDzData.max_ac = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDzData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDzData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDzData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralDzData.fahrenheit_celsius = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDzData.steering_wheel_heating = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
      GeneralDzData.auto_wind_power = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralDzData.air_front_left_temp = this.resolveAutoTemp(this.mCanBusInfoInt[8]);
      GeneralDzData.air_front_right_temp = this.resolveAutoTemp(this.mCanBusInfoInt[9]);
      GeneralDzData.air_rear_temp = this.resolveAutoTemp(this.mCanBusInfoInt[12]);
      GeneralDzData.front_defog = this.mCanBusInfoInt[6];
      GeneralDzData.air_front_wind = this.mCanBusInfoInt[6];
      GeneralDzData.air_rear_wind = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
      if (var1) {
         if (!SystemUtil.isForeground(this.mContext, AirActivity.class.getName()) && !SystemUtil.isForeground(this.mContext, AirCleanActivity.class.getName()) && !SystemUtil.isForeground(this.mContext, AirSettingActivity.class.getName())) {
            Intent var2 = new Intent();
            var2.addFlags(268435456);
            var2.setComponent(PA6SAirActivity);
            var2.putExtra("type", "SETUP");
            this.mContext.startActivity(var2);
         }
      } else {
         ComponentName var3 = ((ActivityManager.RunningTaskInfo)((ActivityManager)this.mContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity;
         MyLog.temporaryTracking(var3.getClassName());
         if (var3.getClassName().trim().equals("com.hzbhd.canbus.car_cus._283.activity.AirActivity")) {
            this.realKeyClick(this.mContext, 50);
         } else if (var3.getClassName().trim().equals("com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity")) {
            this.realKeyClick(this.mContext, 50);
            this.realKeyClick(this.mContext, 50);
         }
      }

      this.updateAirActivity();
      SharePreUtil.setBoolValue(this.mContext, "_283_air_sync", GeneralDzData.sync);
      GeneralDzData.air_clean = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      GeneralDzData.air_clean_progress = this.mCanBusInfoInt[10];
      this.updateAirCleanActivity();
      GeneralDzData.wheel_seat_hot_sync = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      this.updateAirSettingActivity();
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTemp());
   }

   private void setAir0x35() {
      GeneralDzData.auto_defogging = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
      GeneralDzData.auto_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      this.updateAirSettingActivity();
   }

   private void setCamera0xE8() {
      boolean var1;
      if (this.mCanBusInfoInt[3] == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      GeneralDzData.camera_wide = var1;
      if (this.mCanBusInfoInt[3] == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      GeneralDzData.camera_standard = var1;
      if (this.mCanBusInfoInt[3] == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      GeneralDzData.camera_overlook = var1;
      if (this.mCanBusInfoInt[3] == 9) {
         var1 = true;
      } else {
         var1 = false;
      }

      GeneralDzData.camera_margin = var1;
      GeneralDzData.camera_light = this.mCanBusInfoInt[4];
      GeneralDzData.camera_contrast = this.mCanBusInfoInt[5];
      GeneralDzData.camera_color = this.mCanBusInfoInt[6];
      this.updateAllRadarView();
      if (this.mCanBusInfoInt[7] != 0 && !this.getReverseState()) {
         this.forceReverse(this.mContext, true);
      } else {
         this.forceReverse(this.mContext, false);
      }

   }

   private void setCanVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setCarData0x11() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.getMyPanoramicView().updateParkUi();
         }
      });
      GeneralDzData.handBrake = DataHandleUtils.getBoolBit3(this.mCanBusInfoByte[2]);
      GeneralDzData.gears = DataHandleUtils.getBoolBit2(this.mCanBusInfoByte[2]);
      GeneralDzData.speed = this.mCanBusInfoByte[3];
      MessageSender.sendMsg((byte)74, (byte)11, GeneralDzData.handBrake);
      this.refreRadarUiData();
      this.updateDZRadarView(this.mContext);
   }

   private void setCarData0x12() {
      GeneralDzData.parking_off_road = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
      this.updateMainSettingActivity(5);
   }

   private void setCarData0x13() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralDzData.launch_oil = this.getTwoDataVehicle(var1[2], var1[3]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.launch_residu = String.valueOf(this.getTwoData(var1[4], var1[5]));
      var1 = this.mCanBusInfoInt;
      GeneralDzData.launch_mileage = this.getTwoDataVehicle(var1[6], var1[7]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.launch_time = this.getTwoDataTime(var1[8], var1[9]);
      GeneralDzData.launch_speed = String.valueOf(this.mCanBusInfoInt[10]);
      this.updateMainActivity(1);
   }

   private void setCarData0x14() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralDzData.longtime_oil = this.getTwoDataVehicle(var1[2], var1[3]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.longtime_residu = String.valueOf(this.getTwoData(var1[4], var1[5]));
      var1 = this.mCanBusInfoInt;
      GeneralDzData.longtime_mileage = this.getTwoDataVehicle(var1[6], var1[7]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.longtime_time = this.getTwoDataTime(var1[8], var1[9]);
      GeneralDzData.longtime_speed = String.valueOf(this.mCanBusInfoInt[10]);
      this.updateMainActivity(2);
   }

   private void setCarData0x15() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralDzData.refuel_oil = this.getTwoDataVehicle(var1[2], var1[3]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.refuel_residu = String.valueOf(this.getTwoData(var1[4], var1[5]));
      var1 = this.mCanBusInfoInt;
      GeneralDzData.refuel_mileage = this.getTwoDataVehicle(var1[6], var1[7]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.refuel_time = this.getTwoDataTime(var1[8], var1[9]);
      GeneralDzData.refuel_speed = String.valueOf(this.mCanBusInfoInt[10]);
      this.updateMainActivity(3);
   }

   private void setCombination0x76() {
      GeneralDzData.combination_current_consumption = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_average_consumption = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_convenience_consumer = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_travelling_time = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_distance_travelled = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_average_speed = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_digital_speed_display = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      GeneralDzData.combination_speed_warning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      GeneralDzData.combination_oil_temperature = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      this.updateMainSettingActivity(2);
   }

   private void setDoor0x12() {
      int var2 = this.now0x12Data2;
      int var1 = this.mCanBusInfoInt[4];
      if (var2 != var1) {
         this.now0x12Data2 = var1;
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         CanbusInfoChangeListener.getInstance().reportDoorInfo(this.mContext);
      }

   }

   private void setDoor0x64() {
      GeneralDzData.door_open = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
      GeneralDzData.door_unlock = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
      GeneralDzData.door_auto_lock = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      GeneralDzData.door_intelligence = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
      GeneralDzData.door_boot = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      GeneralDzData.door_voice = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
      this.updateMainSettingActivity(3);
   }

   private void setDriveStatusHybrid0x17() {
      byte[] var13 = this.mCanBusInfoByte;
      double var1 = (double)(var13[2] << 8 & -256 | var13[3] & 255);
      int[] var14 = this.mCanBusInfoInt;
      int var10 = var14[4];
      int var9 = var14[5];
      double var3 = (double)(var13[6] << 8 & -256 | var13[7] & 255);
      int var8 = var14[8];
      int var7 = var14[9];
      int var11 = var13[10];
      double var5 = (double)(var13[11] & 255 | var11 << 8 & -256);
      var11 = var14[12];
      int var12 = var14[13];
      GeneralDzData.hybrid_electricity_data_1 = (float)(var1 * 0.1) + " kWh/100km";
      GeneralDzData.hybrid_electricity_data_2 = (float)(var3 * 0.1) + " kWh/100km";
      GeneralDzData.hybrid_electricity_data_3 = (float)(var5 * 0.1) + " kWh/100km";
      GeneralDzData.hybrid_travelled_data_1 = (float)(var10 * 256 + var9) + " km";
      GeneralDzData.hybrid_travelled_data_2 = (float)(var8 * 256 + var7) + " km";
      GeneralDzData.hybrid_travelled_data_3 = (float)(var11 * 256 + var12) + " km";
      this.updateMainActivity(8);
   }

   private void setDriverAssistant0x47() {
      GeneralDzData.pa_driving = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2);
      GeneralDzData.pa_last = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
      GeneralDzData.pa_distance = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5);
      GeneralDzData.pa_front_assist = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
      GeneralDzData.pa_front_warning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
      GeneralDzData.pa_front_distance = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]);
      GeneralDzData.pa_lane = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
      GeneralDzData.pa_traffice = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]);
      GeneralDzData.pa_driverAlertSystem = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13]);
      GeneralDzData.pa_dead_zone = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13]);
      GeneralDzData.pa_proactive = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14]);
      GeneralDzData.pa_lane_keeping = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]);
      this.updateMainSettingActivity(6);
   }

   private void setDrivingMode0x86() {
      GeneralDzData.drivingMode_comfort = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDzData.drivingMode_normal = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDzData.drivingMode_sport = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDzData.drivingMode_eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDzData.drivingMode_indivdual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDzData.drivingMode_cross_country = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDzData.drivingMode_snowfield = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      boolean var2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      boolean var5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      boolean var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      boolean var1 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      boolean var4 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      boolean var6 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (var7) {
         GeneralDzData.drivingMode = 7;
      }

      if (var2) {
         GeneralDzData.drivingMode = 6;
      }

      if (var5) {
         GeneralDzData.drivingMode = 5;
      }

      if (var3) {
         GeneralDzData.drivingMode = 4;
      }

      if (var1) {
         GeneralDzData.drivingMode = 3;
      }

      if (var4) {
         GeneralDzData.drivingMode = 2;
      }

      if (var6) {
         GeneralDzData.drivingMode = 1;
      }

      DriveData.Comfort = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      DriveData.Normal = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      DriveData.Sport = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      DriveData.Eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      DriveData.Indivdual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      DriveData.xuedi = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      DriveData.yueye = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      DriveData.yueye_personal = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      DriveData.DCC = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
      DriveData.Dynamic_Bend_lighting = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
      DriveData.Engine = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      DriveData.ACC = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      DriveData.Air_Conditioning = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
      DriveData.Steering = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
      SharePreUtil.setIntValue(this.mContext, "_283_driver_mode", GeneralDzData.drivingMode);
      this.updateMainSettingPersonalActivity(16);
      this.updateMainActivity(4);
   }

   private void setDyFrontRearRadar0x41() {
      GeneralDzData.radar_rear_l = this.mCanBusInfoInt[2];
      GeneralDzData.radar_rear_ml = this.mCanBusInfoInt[3];
      GeneralDzData.radar_rear_mr = this.mCanBusInfoInt[4];
      GeneralDzData.radar_rear_r = this.mCanBusInfoInt[5];
      GeneralDzData.radar_front_l = this.mCanBusInfoInt[6];
      GeneralDzData.radar_front_ml = this.mCanBusInfoInt[7];
      GeneralDzData.radar_front_mr = this.mCanBusInfoInt[8];
      GeneralDzData.radar_front_r = this.mCanBusInfoInt[9];
      if (this.getReverseState()) {
         GeneralDzData.show_radar = false;
         this.refreRadarUiData();
      } else {
         this.updateDZRadarView(this.mContext);
      }

   }

   private void setDyLeftRightRadar0x42() {
      GeneralDzData.radar_right_f = this.mCanBusInfoInt[2];
      GeneralDzData.radar_right_mf = this.mCanBusInfoInt[3];
      GeneralDzData.radar_right_mr = this.mCanBusInfoInt[4];
      GeneralDzData.radar_right_r = this.mCanBusInfoInt[5];
      GeneralDzData.radar_left_f = this.mCanBusInfoInt[6];
      GeneralDzData.radar_left_mf = this.mCanBusInfoInt[7];
      GeneralDzData.radar_left_mr = this.mCanBusInfoInt[8];
      GeneralDzData.radar_left_r = this.mCanBusInfoInt[9];
      if (this.getReverseState()) {
         GeneralDzData.show_radar = false;
         this.refreRadarUiData();
      } else {
         this.updateDZRadarView(this.mContext);
      }

   }

   private void setDyVoiceAction(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1950015300:
            if (var1.equals("air_preset_mode_on_to_ac_max")) {
               var2 = 0;
            }
            break;
         case -1785642657:
            if (var1.equals("air_rear_power_on")) {
               var2 = 1;
            }
            break;
         case -1607857989:
            if (var1.equals("air_preset_mode_on_to_manual")) {
               var2 = 2;
            }
            break;
         case -1585736007:
            if (var1.equals("air_rear_right_seat_heat_to")) {
               var2 = 3;
            }
            break;
         case -1567175431:
            if (var1.equals("air_wind_blow_foot")) {
               var2 = 4;
            }
            break;
         case -1567125909:
            if (var1.equals("air_wind_blow_head")) {
               var2 = 5;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 6;
            }
            break;
         case -1412208249:
            if (var1.equals("air.ac.on")) {
               var2 = 7;
            }
            break;
         case -1409945485:
            if (var1.equals("air_left_seat_heat_to")) {
               var2 = 8;
            }
            break;
         case -1326915554:
            if (var1.equals("ac.temperature.max")) {
               var2 = 9;
            }
            break;
         case -1326915316:
            if (var1.equals("ac.temperature.min")) {
               var2 = 10;
            }
            break;
         case -1256523009:
            if (var1.equals("air_left_seat_cold_to")) {
               var2 = 11;
            }
            break;
         case -1226270570:
            if (var1.equals("ac.open")) {
               var2 = 12;
            }
            break;
         case -1092999012:
            if (var1.equals("air.in.out.cycle.auto")) {
               var2 = 13;
            }
            break;
         case -940325874:
            if (var1.equals("air_sync_on")) {
               var2 = 14;
            }
            break;
         case -866529054:
            if (var1.equals("air.in.out.cycle.off")) {
               var2 = 15;
            }
            break;
         case -854978899:
            if (var1.equals("air_rear_lock_on")) {
               var2 = 16;
            }
            break;
         case -828782905:
            if (var1.equals("air.ac.off")) {
               var2 = 17;
            }
            break;
         case -734542239:
            if (var1.equals("air_rear_lock_off")) {
               var2 = 18;
            }
            break;
         case -553279150:
            if (var1.equals("air_steering_wheel_off")) {
               var2 = 19;
            }
            break;
         case -112216342:
            if (var1.equals("air_clean_on")) {
               var2 = 20;
            }
            break;
         case 103110984:
            if (var1.equals("air_right_seat_heat_to")) {
               var2 = 21;
            }
            break;
         case 256533460:
            if (var1.equals("air_right_seat_cold_to")) {
               var2 = 22;
            }
            break;
         case 479652335:
            if (var1.equals("air_rear_power_off")) {
               var2 = 23;
            }
            break;
         case 629126444:
            if (var1.equals("ac.close")) {
               var2 = 24;
            }
            break;
         case 816260548:
            if (var1.equals("air_clean_off")) {
               var2 = 25;
            }
            break;
         case 890880226:
            if (var1.equals("air_rear_left_seat_heat_to")) {
               var2 = 26;
            }
            break;
         case 914668832:
            if (var1.equals("air_sync_off")) {
               var2 = 27;
            }
            break;
         case 956867879:
            if (var1.equals("air_rear_wind_level_to")) {
               var2 = 28;
            }
            break;
         case 1218099172:
            if (var1.equals("air_preset_mode_on_to_auto")) {
               var2 = 29;
            }
            break;
         case 1225772921:
            if (var1.equals("ac.windlevel.to")) {
               var2 = 30;
            }
            break;
         case 1377338037:
            if (var1.equals("air_rear_auto")) {
               var2 = 31;
            }
            break;
         case 1481217153:
            if (var1.equals("ac.temperature.to")) {
               var2 = 32;
            }
            break;
         case 1496068108:
            if (var1.equals("air.in.out.cycle.on")) {
               var2 = 33;
            }
            break;
         case 1733069433:
            if (var1.equals("air_preset_mode_on_to_max_front")) {
               var2 = 34;
            }
            break;
         case 1921814940:
            if (var1.equals("air_steering_wheel_on")) {
               var2 = 35;
            }
            break;
         case 1959044539:
            if (var1.equals("air_wind_blow_window")) {
               var2 = 36;
            }
      }

      switch (var2) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 1});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 3});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 44, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 4:
            if (GeneralAirData.front_left_blow_foot) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 1});
            }
            break;
         case 5:
            if (GeneralAirData.front_left_blow_head) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 1});
            }
            break;
         case 6:
            if (GeneralAirData.front_defog) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 1});
            }
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 1});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -1});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -1});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -1});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -2});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -2});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -2});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
            break;
         case 13:
            if (GeneralAirData.in_out_auto_cycle == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 1});
            }
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 1});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 2});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 1});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 0});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 0});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 0});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 1});
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 0});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 0});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 43, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 0});
            break;
         case 28:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 29:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
            break;
         case 30:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte)Integer.parseInt(VoiceControlData.voiceValue)});
            break;
         case 31:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 40, 1});
            break;
         case 32:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte)(Integer.parseInt(VoiceControlData.voiceValue) * 2)});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte)(Integer.parseInt(VoiceControlData.voiceValue) * 2)});
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte)(Integer.parseInt(VoiceControlData.voiceValue) * 2)});
            break;
         case 33:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 1});
            break;
         case 34:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 2});
            break;
         case 35:
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 1});
            break;
         case 36:
            if (GeneralAirData.front_left_blow_window) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 1});
            }
      }

   }

   private void setHybrid0x49() {
      GeneralDzData.hybrid_big_charge_current = this.mCanBusInfoInt[3];
      int var1 = this.mCanBusInfoInt[5];
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      GeneralDzData.hybrid_air_use_electricity = var2;
      GeneralDzData.hybrid_low_charge = this.mCanBusInfoInt[6];
      GeneralDzData.hybrid_temperature_in = this.mCanBusInfoInt[4] / 2;
      this.updateMainSettingActivity(13);
   }

   private void setHybrid0x88() {
      GeneralDzData.hybrid_mode_e = DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[3]);
      GeneralDzData.hybrid_mode_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoByte[3]);
      GeneralDzData.hybrid_mode_keep = DataHandleUtils.getBoolBit5(this.mCanBusInfoByte[3]);
      GeneralDzData.hybrid_mode_charge = DataHandleUtils.getBoolBit4(this.mCanBusInfoByte[3]);
      this.updateMainActivity(9);
   }

   private void setHybridView0x18() {
      GeneralDzData.hybrid_energy_flow = this.getEnergy(this.mCanBusInfoInt[4]);
      StringBuilder var1 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      GeneralDzData.hybrid_endurance = var1.append(var2[2] * 256 + var2[3]).append("km").toString();
      StringBuilder var4 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      GeneralDzData.hybrid_travelled = var4.append(var3[5] * 256 + var3[6]).append("km").toString();
      var1 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      GeneralDzData.hybrid_zero_travelled = var1.append(var2[7] * 256 + var2[8]).append("km").toString();
      GeneralDzData.hybrid_zero_persents = this.mCanBusInfoInt[9] + "%";
      GeneralDzData.hybrid_electricity = this.mCanBusInfoInt[10] + "%";
      this.updateMainActivity(7);
   }

   private void setLight0x67() {
      GeneralDzData.light_big = this.mCanBusInfoInt[3];
      GeneralDzData.light_ambient = this.mCanBusInfoInt[4];
      GeneralDzData.light_ambient_in = this.mCanBusInfoInt[5];
      GeneralDzData.light_ambient_right = this.mCanBusInfoInt[6];
      GeneralDzData.light_system = this.mCanBusInfoInt[7];
      GeneralDzData.light_ambient_all = this.mCanBusInfoInt[8];
      this.updateMainSettingActivity(4);
      this.updateMeterActivity();
   }

   private void setLight0x68() {
      GeneralDzData.light_assis = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      GeneralDzData.light_bend = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      GeneralDzData.light_openTime = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
      GeneralDzData.light_automatic = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      GeneralDzData.light_change = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
      GeneralDzData.light_travel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1);
      GeneralDzData.light_day = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
      GeneralDzData.light_instrument = this.mCanBusInfoInt[6];
      GeneralDzData.light_door = this.mCanBusInfoInt[7];
      GeneralDzData.light_footwell = this.mCanBusInfoInt[8];
      GeneralDzData.light_coming = this.mCanBusInfoInt[9];
      GeneralDzData.light_leaving = this.mCanBusInfoInt[10];
      this.updateMainSettingActivity(4);
   }

   private void setMode1(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      Log.d("fxHouTime", "模式1 时间：" + var2 + " " + var3 + " " + var4 + " " + var5 + " " + var6);
      if (CHANG_TIME && var7 == 0) {
         CHANG_TIME = false;
         this.sendTimeCommonds(var2, var3, var4, var5, var6, false);
         Log.d("fxHouTime", "模式1__成功");
      }

   }

   private void setMode2(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      Log.d("fxHouTime", "模式2 时间：" + var2 + " " + var3 + " " + var4 + " " + var5 + " " + var6);
      var1 = this.sendTimeTag;
      if (var1 < 10) {
         this.sendTimeTag = var1 + 1;
         Log.d("fxHouTime", "模式2__成功");
         this.sendTimeCommonds(var2, var3, var4, var5, var6, false);
      }

   }

   private void setMode3(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      Log.d("fxHouTime", "模式3__成功 时间：" + var2 + " " + var3 + " " + var4 + " " + var5 + " " + var7);
      this.sendTimeCommonds(var2, var3, var4, var5, var6, false);
   }

   private void setParking0x45() {
      GeneralDzData.parking_mode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2);
      GeneralDzData.parking_front_volume = this.mCanBusInfoInt[4];
      GeneralDzData.parking_front_tone = this.mCanBusInfoInt[5];
      GeneralDzData.parking_rear_volume = this.mCanBusInfoInt[6];
      GeneralDzData.parking_rear_tone = this.mCanBusInfoInt[7];
      GeneralDzData.parking_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralDzData.parking_out = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralDzData.parking_function = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDzData.parking_switch = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDzData.parking_radar_voice = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
      this.updateMainSettingActivity(5);
      this.updateAllRadarView();
   }

   private void setPublicFrontRearRadar0x41() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(10, var1[2] / 16 + 1, var1[3] / 16 + 1, var1[4] / 16 + 1, var1[5] / 16 + 1);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(10, var1[6] / 16 + 1, var1[7] / 16 + 1, var1[8] / 16 + 1, var1[9] / 16 + 1);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setPublicLeftRightRadar0x42() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRightRadarLocationData(10, var1[2] / 16 + 1, var1[3] / 16 + 1, var1[4] / 16 + 1, var1[5] / 16 + 1);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setLeftRadarLocationData(10, var1[6] / 16 + 1, var1[7] / 16 + 1, var1[8] / 16 + 1, var1[9] / 16 + 1);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRear0x69() {
      GeneralDzData.rear_sync = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDzData.rear_down = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDzData.rear_in = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDzData.rear_auto_wiper = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      GeneralDzData.rear_wiper = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      this.updateMainSettingActivity(7);
   }

   private void setSport0x85() {
      GeneralDzData.esc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
      this.updateMainSettingActivity(1);
   }

   private void setTemp(String var1) {
      float var4 = Float.parseFloat(var1);
      double var2 = (double)var4;
      if (var2 < 16.0) {
         this.sendAirCommonds(254.0F);
      } else if (var2 > 29.5) {
         this.sendAirCommonds(255.0F);
      } else if (var2 >= 16.0 && var2 < 29.5) {
         this.sendAirCommonds(var4);
      }

   }

   private void setTime0xC2() {
      GeneralDzData.time_source = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      GeneralDzData.time_hour = this.mCanBusInfoInt[3];
      GeneralDzData.time_minute = this.mCanBusInfoInt[4];
      GeneralDzData.time_date3 = this.mCanBusInfoInt[5];
      GeneralDzData.time_date4 = this.mCanBusInfoInt[6];
      GeneralDzData.time_format = this.mCanBusInfoInt[7];
      GeneralDzData.date_year = this.mCanBusInfoInt[8];
      GeneralDzData.date_month = this.mCanBusInfoInt[9];
      GeneralDzData.date_day = this.mCanBusInfoInt[10];
      GeneralDzData.date_format = this.mCanBusInfoInt[11];
      this.updateMainSettingActivity(9);
   }

   private void setTmps0x46() {
      GeneralDzData.tmpsChoose = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
      this.updateMainActivity(5);
      GeneralDzData.tmpsAlarm = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralDzData.tmpsAlarmSpeed = this.mCanBusInfoInt[4];
      this.updateMainSettingActivity(10);
   }

   private void setTmps0x48() {
      GeneralDzData.tmpsUnit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      GeneralDzData.left_front_tmps_exception = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralDzData.right_front_tmps_exception = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralDzData.left_rear_tmps_exception = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralDzData.right_rear_tmps_exception = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      int[] var1 = this.mCanBusInfoInt;
      GeneralDzData.left_front_tmps_reality = (float)this.getTwoData(var1[4], var1[5]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.right_front_tmps_reality = (float)this.getTwoData(var1[6], var1[7]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.left_rear_tmps_reality = (float)this.getTwoData(var1[8], var1[9]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.right_rear_tmps_reality = (float)this.getTwoData(var1[10], var1[11]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.left_front_tmps_reference = (float)this.getTwoData(var1[12], var1[13]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.right_front_tmps_reference = (float)this.getTwoData(var1[14], var1[15]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.left_rear_tmps_reference = (float)this.getTwoData(var1[16], var1[17]) / 10.0F;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.right_rear_tmps_reference = (float)this.getTwoData(var1[18], var1[19]) / 10.0F;
      this.updateMainActivity(6);
   }

   private void setUnit0xC1() {
      GeneralDzData.unit_distance = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
      GeneralDzData.unit_speed = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
      GeneralDzData.unit_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
      GeneralDzData.unit_volume = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2);
      GeneralDzData.unit_consumption = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2);
      GeneralDzData.unit_electric = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
      GeneralDzData.unit_pressure = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
      this.updateMainSettingActivity(11);
   }

   private void setUpKeep0x1E() {
      GeneralDzData.upkeep_unit = this.mCanBusInfoInt[2];
      GeneralDzData.upkeep_car_day_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
      GeneralDzData.upkeep_car_distance_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
      GeneralDzData.upkeep_oil_day_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
      GeneralDzData.upkeep_oil_distance_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      int[] var1 = this.mCanBusInfoInt;
      GeneralDzData.upkeep_car_day = this.getTwoData(var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.upkeep_car_distance = this.getTwoData(var1[6], var1[7]) * 100;
      var1 = this.mCanBusInfoInt;
      GeneralDzData.upkeep_oil_day = this.getTwoData(var1[8], var1[9]);
      var1 = this.mCanBusInfoInt;
      GeneralDzData.upkeep_oil_distance = this.getTwoData(var1[10], var1[11]) * 100;
      this.updateMainSettingActivity(12);
   }

   private void setUpKeep0x1F() {
      GeneralDzData.car_imei = this.getCarImei(DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 0, (byte)0));
      this.updateMainSettingActivity(12);
   }

   private void subTemp(byte var1, String var2, float var3) {
      Exception var10000;
      label51: {
         boolean var10001;
         try {
            if (var2.equals("LO")) {
               return;
            }
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label51;
         }

         float var4;
         label44: {
            label43: {
               try {
                  if (!GeneralDzData.fahrenheit_celsius) {
                     return;
                  }

                  if (var2.equals("HI")) {
                     break label43;
                  }
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label51;
               }

               try {
                  var4 = Float.valueOf(var2.replace(this.mContext.getString(2131770016), ""));
                  break label44;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label51;
               }
            }

            var4 = 29.5F;
         }

         var3 = (var4 - var3) * 2.0F;
         if (var3 <= 32.0F) {
            try {
               MessageSender.sendMsg(new byte[]{22, 58, var1, -2});
               return;
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
            }
         } else {
            try {
               MessageSender.sendMsg(new byte[]{22, 58, var1, (byte)((int)var3)});
               return;
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
            }
         }
      }

      Exception var10 = var10000;
      var10.printStackTrace();
   }

   private void subTempCommonds(String var1) {
      (new Thread(new MsgMgr$$ExternalSyntheticLambda0(this, var1))).start();
   }

   private void updateAirActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof AirActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateAirCleanActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof AirCleanActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateAirSettingActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof AirSettingActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateAllRadarView() {
      this.updateDZRadarView(this.mContext);
      this.refreRadarUiData();
   }

   private void updateMainActivity(int var1) {
      Bundle var2 = new Bundle();
      var2.putInt("bundle_dezhong_what", var1);
      if (this.getActivity() != null && this.getActivity() instanceof MainActivity) {
         this.updateActivity(var2);
      }

   }

   private void updateMainSettingActivity(int var1) {
      Bundle var2 = new Bundle();
      var2.putInt("_283_CarSetting_what", var1);
      if (this.getActivity() != null && this.getActivity() instanceof MainSettingActivity) {
         this.updateActivity(var2);
      }

   }

   private void updateMainSettingPersonalActivity(int var1) {
      Bundle var2 = new Bundle();
      var2.putInt("_283_CarSetting_what", var1);
      Log.d("mww getActivity()", "" + this.getActivity());
      if (this.getActivity() != null && this.getActivity() instanceof MainSettingPersonalActivity) {
         this.updateActivity(var2);
      }

   }

   private void updateMeterActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof MeterActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateSmartActivity() {
      Bundle var1 = new Bundle();
      if (this.getActivity() != null && this.getActivity() instanceof SmartPowerActivity) {
         this.updateActivity(var1);
      }

   }

   private void updateSystemUIDrivingPattern(int var1) {
      if (var1 >= 0 && var1 <= 4) {
         Intent var2 = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
         var2.putExtra("_283_car_mode", String.valueOf(var1));
         this.mContext.sendBroadcastAsUser(var2, UserHandle.ALL);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      AMapUtils.getInstance().startAMap(var1);
      if (this.mAMapBroadcast == null) {
         Log.d("scyscyscy", "---------->开启导航广播监听");
         this.mAMapBroadcast = new AMapBroadcast();
         IntentFilter var2 = new IntentFilter();
         var2.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
         var2.addAction("AUDIO_PLAY_NAME_AND_STAUS");
         var1.registerReceiver(this.mAMapBroadcast, var2);
      }

      this.updateMainActivity(3);
      this.updateMainActivity(2);
      this.updateMainActivity(1);
      GPSTimeManager.getInstance().start(var1);
      GPSTimeManager.getInstance().setOnGPSTimeCallBack(this.mOnGPSTimeCallBack);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      Log.d("scyscyscy", "---------->auxInInfoChange");
      MeterManager.send0xE6Null(2);
      MeterManager.sendMediaMeterData("AUX", " ", " ", " ");
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      byte[] var4 = new byte[0];

      byte[] var8;
      try {
         var8 = DataHandleUtils.exceptBOMHead(var1.getBytes("UTF-8"));
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
         var8 = var4;
      }

      var8 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var8), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), var8);
      var8 = new byte[0];

      byte[] var9;
      label31: {
         try {
            var9 = DataHandleUtils.exceptBOMHead(var2.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
            break label31;
         }

         var8 = var9;
      }

      var8 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var8), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), var8);
      var8 = new byte[0];

      label26: {
         try {
            var9 = DataHandleUtils.exceptBOMHead(var3.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
            break label26;
         }

         var8 = var9;
      }

      var8 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -109, 0}, var8), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), var8);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      Log.d("scyscyscy", "---------->btMusiceDestdroy");
      MeterManager.send0xE6Null(4);
      MeterManager.sendMediaMeterData(" ", " ", " ", " ");
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      Log.d("scyscyscy", "---------->btPhoneHangUpInfoChange");
      PA6Utils.setBtName(this.mContext, "");
      this.mHandler.removeMessages(0);
      this.sendBlueToothName();
      this.sendHandler(1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      Log.d("scyscyscy", "btPhoneIncomingInfoChange: " + new String(var1));
      this.mHandler.sendEmptyMessageDelayed(0, 1000L);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -106, 0}, var1), 27));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      Log.d("scyscyscy", "btPhoneOutGoingInfoChange: " + new String(var1));
      this.mHandler.sendEmptyMessageDelayed(0, 1000L);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -106, 0}, var1), 27));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var1 == 6) {
         this.mOperatorName = this.getOperator(var9);
         this.sendHandler(1);
      } else if (var1 == 0) {
         if (!var3) {
            this.mOperatorName = "";
            this.sendHandler(1);
         }

         (new Thread(new MsgMgr$$ExternalSyntheticLambda3(this, var3))).start();
         MeterManager.m0xE6Data[2] = (byte)DataHandleUtils.setIntByteWithBit(0, 6, var3);
         MeterManager.m0xE6Data[4] = (byte)var7;
         MeterManager.m0xE6Data[5] = (byte)var8;
         MeterManager.send0xE6();
      }
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[0];
      if (var3 == 85) {
         this.canBusInfo0x55(var2);
      } else if (var3 == 87) {
         this.canBusInfo0x57(var2);
      } else if (var3 == 89) {
         this.canBusInfo0x59(var2);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      Log.d("scyscyscy", var1 + "<----currentVolumeInfoChange------>" + var2);
      int var3;
      if (var2) {
         var3 = DataHandleUtils.setIntByteWithBit(4, 7, true);
      } else {
         var3 = 0;
      }

      MeterManager.m0xE6Data[3] = (byte)var1;
      MeterManager.m0xE6Data[8] = (byte)var3;
      if (this.nowVlume != var1) {
         this.nowVlume = var1;
         MeterManager.send0xE6();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);

      label46: {
         Settings.SettingNotFoundException var10000;
         label50: {
            Context var15;
            boolean var10001;
            try {
               var15 = this.mContext;
            } catch (Settings.SettingNotFoundException var18) {
               var10000 = var18;
               var10001 = false;
               break label50;
            }

            if (var15 == null) {
               return;
            }

            int var14;
            try {
               var14 = android.provider.Settings.System.getInt(var15.getContentResolver(), "283.time.sync.mode");
               if (var14 == TimeSyncMode.MODE_1) {
                  this.setMode1(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
                  break label46;
               }
            } catch (Settings.SettingNotFoundException var17) {
               var10000 = var17;
               var10001 = false;
               break label50;
            }

            try {
               if (var14 == TimeSyncMode.MODE_2) {
                  this.setMode2(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
                  break label46;
               }
            } catch (Settings.SettingNotFoundException var19) {
               var10000 = var19;
               var10001 = false;
               break label50;
            }

            try {
               if (var14 == TimeSyncMode.MODE_3) {
                  this.setMode3(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
               }
               break label46;
            } catch (Settings.SettingNotFoundException var16) {
               var10000 = var16;
               var10001 = false;
            }
         }

         Settings.SettingNotFoundException var20 = var10000;
         var20.printStackTrace();
         this.setMode1(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      }

      this.mSystemDateFormat = var9;
   }

   public void destroyCommand() {
      super.destroyCommand();
      Log.d("scyscyscy", "destroyCommand: ");
      AMapBroadcast var1 = this.mAMapBroadcast;
      if (var1 != null) {
         this.mContext.unregisterReceiver(var1);
      }

      GPSTimeManager.getInstance().unregister();
      MeterManager.send0xE6Null(8);
      MeterManager.sendMediaMeterData(" ", " ", " ", " ");
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      Log.d("scyscyscy", "---------->discInfoChange");
      boolean var15;
      if (var7 == 1) {
         var15 = true;
      } else {
         var15 = false;
      }

      byte var17 = (byte)(var2 / 3600);
      byte var16 = (byte)(var2 / 60 % 60);
      byte var14 = (byte)(var2 % 60);
      var12 = Util.numCompensateZero(var17) + ":" + Util.numCompensateZero(var16) + ":" + Util.numCompensateZero(var14);
      MeterManager.m0xE6Data[6] = (byte)DataHandleUtils.setIntByteWithBit(4, 7, true);
      MeterManager.send0xE6();
      if (var15) {
         var11 = "DVD";
      } else {
         var11 = "CD";
      }

      MeterManager.sendMediaMeterData(var11, var12, " ", " ");
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      Log.d("scyscyscy", "---------->dtvInfoChange");
      MeterManager.send0xE6Null(3);
      MeterManager.sendMediaMeterData("DTV", " ", " ", " ");
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      (new Thread(new MsgMgr$$ExternalSyntheticLambda1(this, var1))).start();
   }

   protected boolean isKeyModeMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusKeyModeCopy)) {
         return true;
      } else {
         this.mCanbusKeyModeCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   protected boolean isKeyMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusKeyCopy)) {
         return true;
      } else {
         this.mCanbusKeyCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   // $FF: synthetic method
   void lambda$addTempCommonds$3$com_hzbhd_canbus_car__283_MsgMgr(String var1) {
      this.addTemp((byte)20, GeneralDzData.air_front_left_temp, Float.parseFloat(var1));

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      this.addTemp((byte)21, GeneralDzData.air_front_right_temp, Float.parseFloat(var1));

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

      this.addTemp((byte)22, GeneralDzData.air_rear_temp, Float.parseFloat(var1));
   }

   // $FF: synthetic method
   void lambda$btPhoneStatusInfoChange$1$com_hzbhd_canbus_car__283_MsgMgr(boolean var1) {
      try {
         Thread.sleep(1000L);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

      if (!var1) {
         this.mBlueToothName = "";
      } else {
         this.mBlueToothName = BtApi.Companion.getInstance().getHfpDeviceName();
      }

      this.sendBlueToothName();
   }

   // $FF: synthetic method
   void lambda$initCommand$0$com_hzbhd_canbus_car__283_MsgMgr(Context var1) {
      try {
         GeneralDzSmartData.mode = SharePreUtil.getIntValue(var1, "_283_smart_mode", 1);
         GeneralDzSmartData.data3 = SharePreUtil.getIntValue(var1, "_283_smart_data3", 0);
         GeneralDzSmartData.data4 = SharePreUtil.getIntValue(var1, "_283_smart_data4", 0);
         GeneralDzSmartData.data5 = SharePreUtil.getIntValue(var1, "_283_smart_data5", 0);
         GeneralDzSmartData.data6 = SharePreUtil.getIntValue(var1, "_283_smart_data6", 0);
         MessageSender.sendDzMsg(GeneralDzSmartData.mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
         this.sendMsg((byte)17, SharePreUtil.getBoolValue(var1, "_283_air_sync", false));
         if (!FutureUtil.instance.getCurrentValidSource().name().equals(SourceConstantsDef.SOURCE_ID.FM.name())) {
            MeterManager.send0xE6Null(7);
            MeterManager.sendMediaMeterData(" ", " ", " ", " ");
         }

         GeneralDzData.colorR = SharePreUtil.getIntValue(var1, "_283_meter_colorR", 0);
         GeneralDzData.colorG = SharePreUtil.getIntValue(var1, "_283_meter_colorG", 0);
         GeneralDzData.colorB = SharePreUtil.getIntValue(var1, "_283_meter_colorB", 0);
         GeneralDzData.meter_mode = SharePreUtil.getIntValue(var1, "_283_meter_meter_mode", 0);
         GeneralDzData.leftRotateInt = SharePreUtil.getIntValue(var1, "_283_meter_leftRotateInt", 0);
         GeneralDzData.rightRotateInt = SharePreUtil.getIntValue(var1, "_283_meter_rightRotateInt", 0);
         GeneralDzData.drivingMode = SharePreUtil.getIntValue(var1, "_283_driver_mode", -1);
      } catch (Exception var2) {
         MessageSender.sendDzMsg(1, 0, 0, 0, 0);
         var2.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$subTempCommonds$2$com_hzbhd_canbus_car__283_MsgMgr(String var1) {
      this.subTemp((byte)20, GeneralDzData.air_front_left_temp, Float.parseFloat(var1));

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      this.subTemp((byte)21, GeneralDzData.air_front_right_temp, Float.parseFloat(var1));

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var3) {
         var3.printStackTrace();
      }

      this.subTemp((byte)22, GeneralDzData.air_rear_temp, Float.parseFloat(var1));
   }

   public void musicDestroy() {
      super.musicDestroy();
      Log.d("scyscyscy", "---------->musicDestroy");
      MeterManager.send0xE6Null(5);
      MeterManager.sendMediaMeterData(" ", " ", " ", " ");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte[] var28 = new byte[0];

      byte[] var29;
      label33: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var21.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var27) {
            var27.printStackTrace();
            break label33;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var28), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label28: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var23.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var26) {
            var26.printStackTrace();
            break label28;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var28), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
      var28 = new byte[0];

      label23: {
         try {
            var29 = DataHandleUtils.exceptBOMHead(var22.getBytes("UTF-8"));
         } catch (UnsupportedEncodingException var25) {
            var25.printStackTrace();
            break label23;
         }

         var28 = var29;
      }

      var28 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -109, 0}, var28), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var28);
   }

   public void onAMapCallBack(AMapEntity var1) {
      super.onAMapCallBack(var1);
      if (var1.getDestinationDistance() != 0) {
         int var2 = var1.getCarDirection();
         byte var4 = 0;
         byte var5 = 0;
         byte var8;
         if (var2 == 7) {
            var8 = 7;
         } else if (var1.getCarDirection() == 8) {
            var8 = 8;
         } else if (var1.getCarDirection() == 1) {
            var8 = 1;
         } else if (var1.getCarDirection() == 2) {
            var8 = 2;
         } else if (var1.getCarDirection() == 3) {
            var8 = 3;
         } else if (var1.getCarDirection() == 4) {
            var8 = 4;
         } else if (var1.getCarDirection() == 5) {
            var8 = 5;
         } else if (var1.getCarDirection() == 6) {
            var8 = 6;
         } else {
            var8 = 0;
         }

         int var3;
         if (var1.getIcon() == 9) {
            var3 = 1;
         } else if (var1.getIcon() == 5) {
            var3 = 2;
         } else if (var1.getIcon() == 3) {
            var3 = 3;
         } else if (var1.getIcon() == 7) {
            var3 = 4;
         } else if (var1.getIcon() == 6) {
            var3 = 6;
         } else if (var1.getIcon() == 2) {
            var3 = 7;
         } else if (var1.getIcon() == 4) {
            var3 = 8;
         } else {
            var3 = 0;
         }

         if (var1.getDestinationDistance() == 0) {
            this.result = 0;
         } else {
            this.result = Integer.parseInt(this.mDecimalFormat.format((long)(var1.getNextDistance() * 100 / var1.getDestinationDistance())));
         }

         this.sendGdOtherInfo(new byte[]{22, -28, -128, -128, (byte)getMsb(var1.getNextDistance() * 10), (byte)getMidMsb(var1.getNextDistance() * 10), (byte)getMidLsb(var1.getNextDistance() * 10), (byte)getLsb(var1.getNextDistance() * 10), (byte)this.result, (byte)getMsb(var1.getDestinationDistance() * 10), (byte)getMidMsb(var1.getDestinationDistance() * 10), (byte)getMidLsb(var1.getDestinationDistance() * 10), (byte)getLsb(var1.getDestinationDistance() * 10), (byte)var8, (byte)Integer.parseInt(var1.getPlanTime().substring(0, var1.getPlanTime().indexOf(":"))), (byte)Integer.parseInt(var1.getPlanTime().substring(var1.getPlanTime().indexOf(":") + 1)), (byte)Integer.parseInt(var1.getSurplusAllTimeStr().substring(0, var1.getSurplusAllTimeStr().indexOf(":"))), (byte)Integer.parseInt(var1.getSurplusAllTimeStr().substring(var1.getSurplusAllTimeStr().indexOf(":") + 1)), (byte)var3, 0, 0});
         byte[] var7 = var1.getNextWayName().trim().getBytes(StandardCharsets.UTF_8);
         byte[] var6 = new byte[30];
         if (var7.length > 30) {
            for(var2 = var5; var2 < 30; ++var2) {
               var6[var2] = var7[var2];
            }

            this.sendWayName(var6);
         } else if (var7.length < 30) {
            var3 = var7.length;

            for(var2 = var4; var2 < 30 - var3; ++var2) {
               var7 = this.SplicingByte(var7, " ".getBytes(StandardCharsets.UTF_8));
            }

            this.sendWayName(var7);
         } else {
            this.sendWayName(var7);
         }

      }
   }

   public void radioDestroy() {
      super.radioDestroy();
      Log.d("scyscyscy", "---------->radioDestroy");
      MeterManager.send0xE6Null(1);
      MeterManager.sendMediaMeterData(" ", " ", " ", " ");
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte[] var7 = (var3 + " " + this.getBandUnit(var2)).getBytes();
      var7 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, var7), 28);
      byte[] var6 = var2.getBytes();
      var6 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, var6), 27);
      CanbusMsgSender.sendMsg(var7);
      CanbusMsgSender.sendMsg(var6);
      this.sendSourceMsg2(" ", 147);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      Log.d("scyscyscy", "---------->sourceSwitchNoMediaInfoChange");
      MeterManager.send0xE6Null(9);
      MeterManager.sendMediaMeterData(" ", " ", " ", " ");
   }

   protected void updateDZAirSeatView(Context var1) {
      if (this.mAirSeatView == null) {
         this.mAirSeatView = new AirSeatView(var1);
      }

      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mAirSeatView.refreshUi();
         }
      });
   }

   protected void updateDZRadarView(Context var1) {
      if (this.mRadarView == null) {
         this.mRadarView = new RadarView(var1);
      }

      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.mRadarView.refreshUi();
         }
      });
   }

   public void videoDestroy() {
      super.videoDestroy();
      Log.d("scyscyscy", "---------->videoDestroy");
      MeterManager.send0xE6Null(6);
      MeterManager.sendMediaMeterData(" ", " ", " ", " ");
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = (var6 + ":" + var7 + "   ").getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, var18), 28);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      var18 = ((var9 & 255) * 256 + (var3 & 255) + "/" + (var4 & 255)).getBytes();
      var18 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -110, 0}, var18), 27);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      this.sendSourceMsg2(" ", 147);
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      MyLog.temporaryTracking("CanBus收到语音Action：" + var1);
      this.setDyVoiceAction(var1);
   }
}
