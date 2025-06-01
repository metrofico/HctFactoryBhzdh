package com.hzbhd.canbus.car._375;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SetTimeView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private int FrontLeftAlert = 0;
   private int FrontRightAlert = 0;
   private int RearLeftAlert = 0;
   private int RearRightAlert = 0;
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   TextView content;
   CountDownTimer countDownTimer;
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   AlertDialog dialog;
   int differentId;
   int eachId;
   int endHour = -1;
   int endMin = -1;
   int energyTag = 0;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mEnergyInfo;
   int[] mFrontRadarData;
   int[] mLeftRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mRightData;
   int[] mTireInfo;
   int[] mTireInfo2;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private String myF_Distance = "";
   private String myR_Distance = "";
   int parkState = 1;
   Button park_1;
   Button park_2;
   Button park_3;
   Button park_4;
   int startHour = -1;
   int startMin = -1;
   private List tyreInfoList = new ArrayList();
   View view;

   private void adjustBrightness() {
      int var1 = FutureUtil.instance.getBrightness();
      if (var1 == 5) {
         FutureUtil.instance.setBrightness(0);
      } else {
         FutureUtil.instance.setBrightness(var1 + 1);
      }

   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private int getIntFromByteWithBit(int var1, int var2, int var3) {
      return var1 >> var2 & (1 << var3) - 1;
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getParkingStr(int var1) {
      return CommUtil.getStrByResId(this.mContext, "_375_auto_park" + var1);
   }

   private int getRadarData(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  MyLog.temporaryTracking("走默认");
                  return 0;
               } else {
                  MyLog.temporaryTracking("走3");
                  return 1;
               }
            } else {
               MyLog.temporaryTracking("走5");
               return 5;
            }
         } else {
            MyLog.temporaryTracking("走10");
            return 10;
         }
      } else {
         MyLog.temporaryTracking("走0");
         return 0;
      }
   }

   private int getRight(String var1) {
      return this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_car_setting", var1);
   }

   private int getState() {
      return this.mCanBusInfoInt[3] == 1 ? 1 : 0;
   }

   private int getState(int var1) {
      if (var1 != 10) {
         if (var1 != 16) {
            return var1 != 32 ? 0 : 3;
         } else {
            return 2;
         }
      } else {
         return 1;
      }
   }

   private int getState2(int var1) {
      return var1 == 0 ? 1 : 0;
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isNotBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotCarSetting2() {
      if (Arrays.equals(this.mTireInfo2, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo2 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotEnergyChange() {
      if (this.energyTag == 0) {
         this.energyTag = 1;
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         if (var1[2] == 0 && var1[3] == 0 && var1[4] == 15 && var1[5] == 254) {
            return true;
         } else if (var1[6] == 0 && var1[7] == 0 && var1[8] == 31 && var1[9] == 252) {
            return true;
         } else if (Arrays.equals(this.mEnergyInfo, var1)) {
            return true;
         } else {
            var1 = this.mCanBusInfoInt;
            this.mEnergyInfo = Arrays.copyOf(var1, var1.length);
            return false;
         }
      }
   }

   private boolean isNotFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotLeftRadarDataChange() {
      if (Arrays.equals(this.mLeftRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mLeftRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotRightRadarDataChange() {
      if (Arrays.equals(this.mRightData, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRightData = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isNotTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void parkClick(Button var1, Button var2, Button var3, Button var4) {
      var1.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            MsgMgr var2;
            if (this.this$0.parkState == 2) {
               this.this$0.parkState = 1;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(1);
            } else {
               this.this$0.parkState = 2;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(2);
            }

         }
      });
      var2.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            MsgMgr var2;
            if (this.this$0.parkState == 3) {
               this.this$0.parkState = 1;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(1);
            } else {
               this.this$0.parkState = 3;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(3);
            }

         }
      });
      var3.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            MsgMgr var2;
            if (this.this$0.parkState == 5) {
               this.this$0.parkState = 1;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(1);
            } else {
               this.this$0.parkState = 5;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(5);
            }

         }
      });
      var4.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            MsgMgr var2;
            if (this.this$0.parkState == 4) {
               this.this$0.parkState = 1;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(1);
            } else {
               this.this$0.parkState = 4;
               var2 = this.this$0;
               var2.getUiMgr(var2.mContext).sendAutoParkingModel(4);
            }

         }
      });
   }

   private void set0X23FrontRadarInfo() {
      if (!this.isNotFrontRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(10, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0X27FrontRadarInfo() {
      this.myF_Distance = "Front L:" + this.mCanBusInfoInt[2] + "cm LM:" + this.mCanBusInfoInt[3] + "cm RM:" + this.mCanBusInfoInt[4] + "cm R:" + this.mCanBusInfoInt[5] + "cm";
      GeneralParkData.isShowLeftTopOneDistanceUi = true;
      GeneralParkData.strOnlyOneDistance = this.myF_Distance + "\n" + this.myR_Distance;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0X38TireInfo() {
      if (!this.isNotTireInfoChange()) {
         GeneralTireData.isHaveSpareTire = false;
         List var1 = this.tyreInfoList;
         if (var1 != null) {
            var1.clear();
         }

         this.arr0[0] = this.mContext.getString(2131764862) + (this.mCanBusInfoInt[2] - 40) + this.getTempUnitC(this.mContext);
         this.arr0[1] = this.mContext.getString(2131764856) + this.df_1Decimal.format((double)this.mCanBusInfoInt[6] * 0.014 - 0.1) + "bar";
         this.arr1[0] = this.mContext.getString(2131764862) + (this.mCanBusInfoInt[3] - 40) + this.getTempUnitC(this.mContext);
         this.arr1[1] = this.mContext.getString(2131764856) + this.df_1Decimal.format((double)this.mCanBusInfoInt[7] * 0.014 - 0.1) + "bar";
         this.arr2[0] = this.mContext.getString(2131764862) + (this.mCanBusInfoInt[4] - 40) + this.getTempUnitC(this.mContext);
         this.arr2[1] = this.mContext.getString(2131764856) + this.df_1Decimal.format((double)this.mCanBusInfoInt[8] * 0.014 - 0.1) + "bar";
         this.arr3[0] = this.mContext.getString(2131764862) + (this.mCanBusInfoInt[5] - 40) + this.getTempUnitC(this.mContext);
         this.arr3[1] = this.mContext.getString(2131764856) + this.df_1Decimal.format((double)this.mCanBusInfoInt[9] * 0.014 - 0.1) + "bar";
         this.tyreInfoList.add(new TireUpdateEntity(0, this.FrontRightAlert, this.arr0));
         this.tyreInfoList.add(new TireUpdateEntity(1, this.FrontLeftAlert, this.arr1));
         this.tyreInfoList.add(new TireUpdateEntity(2, this.RearRightAlert, this.arr2));
         this.tyreInfoList.add(new TireUpdateEntity(3, this.RearLeftAlert, this.arr3));
         GeneralTireData.dataList = this.tyreInfoList;
         this.updateTirePressureActivity((Bundle)null);
      }
   }

   private void set0X3BBackLightInfi() {
      int var1 = this.mCanBusInfoInt[2] / 4;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
                     this.setBacklightLevel(5);
                  }
               } else {
                  this.setBacklightLevel(4);
               }
            } else {
               this.setBacklightLevel(3);
            }
         } else {
            this.setBacklightLevel(2);
         }
      } else {
         this.setBacklightLevel(1);
      }

   }

   private void set0x20WheelKeyInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 136) {
         switch (var1) {
            case 0:
               this.buttonKey(0);
               break;
            case 1:
               this.buttonKey(7);
               break;
            case 2:
               this.buttonKey(8);
               break;
            case 3:
               this.buttonKey(45);
               break;
            case 4:
               this.buttonKey(46);
               break;
            case 5:
               this.buttonKey(3);
               break;
            case 6:
               this.buttonKey(2);
               break;
            case 7:
               this.buttonKey(467);
               break;
            case 8:
               this.buttonKey(468);
               break;
            case 9:
               this.buttonKey(76);
               break;
            case 10:
               this.buttonKey(134);
               break;
            default:
               switch (var1) {
                  case 17:
                     this.buttonKey(7);
                     break;
                  case 18:
                     this.buttonKey(8);
                     break;
                  case 19:
                     this.buttonKey(52);
                     break;
                  case 20:
                     this.buttonKey(59);
                     break;
                  case 21:
                     this.buttonKey(2);
                     break;
                  case 22:
                     this.buttonKey(62);
                     break;
                  case 23:
                     this.buttonKey(43);
                     break;
                  case 24:
                     this.buttonKey(63);
                     break;
                  case 25:
                     this.buttonKey(4113);
                     break;
                  case 26:
                     this.buttonKey(33);
                     break;
                  case 27:
                     this.buttonKey(34);
                     break;
                  case 28:
                     this.buttonKey(35);
                     break;
                  case 29:
                     this.buttonKey(36);
                     break;
                  case 30:
                     this.buttonKey(37);
                     break;
                  case 31:
                     this.buttonKey(38);
                     break;
                  default:
                     switch (var1) {
                        case 33:
                           this.knobKey(7);
                           break;
                        case 34:
                           this.knobKey(8);
                           break;
                        case 35:
                           this.buttonKey(49);
                           break;
                        case 36:
                           this.knobKey(45);
                           break;
                        case 37:
                           this.knobKey(46);
                           break;
                        case 38:
                           this.buttonKey(47);
                           break;
                        case 39:
                           this.buttonKey(48);
                           break;
                        case 40:
                           this.buttonKey(58);
                           break;
                        case 41:
                           this.buttonKey(128);
                           break;
                        case 42:
                           this.buttonKey(182);
                           break;
                        case 43:
                           this.buttonKey(30);
                           break;
                        case 44:
                           this.buttonKey(39);
                           break;
                        case 45:
                           this.buttonKey(14);
                           break;
                        case 46:
                           this.buttonKey(40);
                           break;
                        case 47:
                           this.buttonKey(41);
                           break;
                        case 48:
                           this.buttonKey(151);
                           break;
                        case 49:
                           this.buttonKey(49);
                           break;
                        case 50:
                           this.buttonKey(472);
                           break;
                        case 51:
                           this.buttonKey(367);
                     }
               }
         }
      } else {
         this.buttonKey(187);
      }

   }

   private void set0x21AirInfo() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.rear = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_wind_level = this.mCanBusInfoInt[3];
         if (this.mCanBusInfoInt[3] == 0) {
            GeneralAirData.power = false;
         } else {
            GeneralAirData.power = true;
         }

         int var1 = this.mCanBusInfoInt[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        GeneralAirData.front_left_blow_head = false;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_head = false;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_head = false;
                     GeneralAirData.front_right_blow_foot = false;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = false;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_head = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_window = false;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_window = false;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
         }

         var1 = this.mCanBusInfoInt[5];
         if (var1 == 0) {
            GeneralAirData.front_left_temperature = "LO";
            GeneralAirData.front_right_temperature = "LO";
         } else if (var1 == 30) {
            GeneralAirData.front_left_temperature = "HI";
            GeneralAirData.front_right_temperature = "HI";
         } else {
            GeneralAirData.front_left_temperature = (double)(this.mCanBusInfoInt[5] - 1) * 0.5 + 18.0 + this.getTempUnitC(this.mContext);
            GeneralAirData.front_right_temperature = (double)(this.mCanBusInfoInt[5] - 1) * 0.5 + 18.0 + this.getTempUnitC(this.mContext);
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void set0x22RearRadarInfo() {
      if (!this.isNotRearRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(10, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x24LeftRadarInfo() {
      if (!this.isNotLeftRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setLeftRadarLocationData(10, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x25RightRadarInfo() {
      if (!this.isNotRightRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRightRadarLocationData(10, this.getRadarData(this.mCanBusInfoInt[2]), this.getRadarData(this.mCanBusInfoInt[3]), this.getRadarData(this.mCanBusInfoInt[4]), this.getRadarData(this.mCanBusInfoInt[5]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void set0x26RearRadarInfo() {
      this.myR_Distance = "Rear  L:" + this.mCanBusInfoInt[2] + "cm LM:" + this.mCanBusInfoInt[3] + "cm RM:" + this.mCanBusInfoInt[4] + "cm R:" + this.mCanBusInfoInt[5] + "cm";
      GeneralParkData.isShowLeftTopOneDistanceUi = true;
      GeneralParkData.strOnlyOneDistance = this.myF_Distance + "\n" + this.myR_Distance;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x29EspInfo() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 5500, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x30RightCamera() {
      this.switchFCamera(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
   }

   private void set0x31PanoraSwitch() {
      this.forceReverse(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
   }

   private void set0x32PanoiraInfo() {
      if (this.isPanoramicInfoChange()) {
         ArrayList var4 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         boolean var2;
         if (var1 == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var2));
         if (this.mCanBusInfoInt[2] == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var2));
         if (this.mCanBusInfoInt[2] == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var2));
         if (this.mCanBusInfoInt[2] == 4) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(3, var2));
         if (this.mCanBusInfoInt[2] == 5) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(4, var2));
         if (this.mCanBusInfoInt[2] == 6) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(5, var2));
         if (this.mCanBusInfoInt[2] == 7) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(6, var2));
         if (this.mCanBusInfoInt[2] == 8) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(7, var2));
         var2 = var3;
         if (this.mCanBusInfoInt[2] == 9) {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(8, var2));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x33ParkInfo() {
      Context var2 = this.mContext;
      if (SharePreUtil.getIntValue(var2, this.getUiMgr(var2).KEY_AUTO_PARKING_VIEW, 0) != 1) {
         if (this.view == null) {
            this.view = LayoutInflater.from(this.getActivity()).inflate(2131558514, (ViewGroup)null, true);
         }

         if (this.dialog == null) {
            this.dialog = (new AlertDialog.Builder(this.getActivity())).setView(this.view).create();
         }

         if (this.content == null) {
            this.content = (TextView)this.view.findViewById(2131362967);
         }

         if (this.park_1 == null) {
            this.park_1 = (Button)this.view.findViewById(2131362963);
         }

         if (this.park_2 == null) {
            this.park_2 = (Button)this.view.findViewById(2131362964);
         }

         if (this.park_3 == null) {
            this.park_3 = (Button)this.view.findViewById(2131362965);
         }

         if (this.park_4 == null) {
            this.park_4 = (Button)this.view.findViewById(2131362966);
         }

         this.parkClick(this.park_1, this.park_2, this.park_3, this.park_4);
         int var1 = this.mCanBusInfoInt[2];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     this.park_1.setTextColor(this.mContext.getResources().getColor(2131100061));
                     this.park_2.setTextColor(this.mContext.getResources().getColor(2131100061));
                     this.park_3.setTextColor(this.mContext.getResources().getColor(2131100061));
                     this.park_4.setTextColor(this.mContext.getResources().getColor(2131100061));
                  } else {
                     this.park_1.setTextColor(this.mContext.getResources().getColor(2131100061));
                     this.park_2.setTextColor(this.mContext.getResources().getColor(2131100061));
                     this.park_3.setTextColor(this.mContext.getResources().getColor(2131100061));
                     this.park_4.setTextColor(this.mContext.getResources().getColor(2131099699));
                  }
               } else {
                  this.park_1.setTextColor(this.mContext.getResources().getColor(2131100061));
                  this.park_2.setTextColor(this.mContext.getResources().getColor(2131100061));
                  this.park_3.setTextColor(this.mContext.getResources().getColor(2131099699));
                  this.park_4.setTextColor(this.mContext.getResources().getColor(2131100061));
               }
            } else {
               this.park_1.setTextColor(this.mContext.getResources().getColor(2131100061));
               this.park_2.setTextColor(this.mContext.getResources().getColor(2131099699));
               this.park_3.setTextColor(this.mContext.getResources().getColor(2131100061));
               this.park_4.setTextColor(this.mContext.getResources().getColor(2131100061));
            }
         } else {
            this.park_1.setTextColor(this.mContext.getResources().getColor(2131099699));
            this.park_2.setTextColor(this.mContext.getResources().getColor(2131100061));
            this.park_3.setTextColor(this.mContext.getResources().getColor(2131100061));
            this.park_4.setTextColor(this.mContext.getResources().getColor(2131100061));
         }

         this.content.setText(this.getParkingStr(this.mCanBusInfoInt[3]));
         this.dialog.setCancelable(true);
         this.dialog.getWindow().setBackgroundDrawableResource(17170445);
         this.dialog.getWindow().setType(2003);
         this.dialog.show();
         CountDownTimer var3 = this.countDownTimer;
         if (var3 != null) {
            var3.cancel();
         }

         var3 = new CountDownTimer(this, 5000L, 1000L) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onFinish() {
               this.this$0.dialog.dismiss();
            }

            public void onTick(long var1) {
            }
         };
         this.countDownTimer = var3;
         var3.start();
      }
   }

   private void set0x39TireAlert() {
      List var1 = this.tyreInfoList;
      if (var1 != null) {
         var1.clear();
      }

      GeneralTireData.isHaveSpareTire = false;
      this.arr0[2] = "";
      this.arr1[2] = "";
      this.arr2[2] = "";
      this.arr3[2] = "";
      this.setItem3Info();
      if (this.mCanBusInfoInt[2] != 0) {
         this.FrontRightAlert = 1;
         this.tyreInfoList.add(new TireUpdateEntity(0, this.FrontRightAlert, this.arr0));
      } else {
         this.FrontRightAlert = 0;
         this.tyreInfoList.add(new TireUpdateEntity(0, this.FrontRightAlert, this.arr0));
         this.arr0[2] = this.mContext.getString(2131764846) + this.mContext.getString(2131764855);
      }

      if (this.mCanBusInfoInt[3] != 0) {
         this.FrontLeftAlert = 1;
         this.tyreInfoList.add(new TireUpdateEntity(1, this.FrontLeftAlert, this.arr1));
      } else {
         this.FrontLeftAlert = 0;
         this.tyreInfoList.add(new TireUpdateEntity(1, this.FrontLeftAlert, this.arr0));
         this.arr1[2] = this.mContext.getString(2131764846) + this.mContext.getString(2131764855);
      }

      if (this.mCanBusInfoInt[4] != 0) {
         this.RearRightAlert = 1;
         this.tyreInfoList.add(new TireUpdateEntity(2, this.RearRightAlert, this.arr2));
      } else {
         this.RearRightAlert = 0;
         this.tyreInfoList.add(new TireUpdateEntity(2, this.RearRightAlert, this.arr0));
         this.arr2[2] = this.mContext.getString(2131764846) + this.mContext.getString(2131764855);
      }

      if (this.mCanBusInfoInt[5] != 0) {
         this.RearLeftAlert = 1;
         this.tyreInfoList.add(new TireUpdateEntity(3, this.RearLeftAlert, this.arr3));
      } else {
         this.RearLeftAlert = 0;
         this.tyreInfoList.add(new TireUpdateEntity(3, this.RearLeftAlert, this.arr0));
         this.arr3[2] = this.mContext.getString(2131764846) + this.mContext.getString(2131764855);
      }

      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void set0x3ADoorInfo() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_375_car_info");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_375_tire_door_info");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(this.getMsbLsbResult(var4[3], var4[4])).append("Km").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      var6[3] = 0;
      var6[4] = 0;
      if (!this.isNotBasicInfoChange()) {
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            GeneralDoorData.skyWindowOpenLevel = 2;
         } else {
            GeneralDoorData.skyWindowOpenLevel = 0;
         }

         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x52TireReset() {
      if (!this.isNotCarSetting2()) {
         int var1 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_car_setting");
         ArrayList var3 = new ArrayList();
         int var2 = this.mCanBusInfoInt[2];
         if (var2 != 128) {
            switch (var2) {
               case 1:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting3"), this.getState()));
                  break;
               case 2:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting4"), this.getState()));
                  break;
               case 3:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting5"), this.getState()));
                  break;
               case 4:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting6"), this.getState()));
                  break;
               case 5:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting7"), this.getState()));
                  break;
               case 6:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting8"), this.getState()));
                  break;
               case 7:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting9"), this.getState()));
                  break;
               case 8:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting10"), this.getState()));
                  break;
               case 9:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting11"), this.getState()));
                  break;
               case 10:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting12"), this.mCanBusInfoInt[3]));
                  break;
               case 11:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting13"), this.mCanBusInfoInt[3]));
                  break;
               case 12:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting14"), this.getState()));
                  break;
               case 13:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting15"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 14:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting16"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 15:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting17"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 16:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting18"), this.getState()));
                  break;
               case 17:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting180"), this.getState()));
                  break;
               case 18:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting19"), this.mCanBusInfoInt[3]));
                  break;
               case 19:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting20"), this.getState()));
                  break;
               case 20:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting21"), this.getState()));
                  break;
               case 21:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting22"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 22:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting23"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 23:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting24"), this.getState()));
                  break;
               case 24:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting25"), this.getState()));
                  break;
               case 25:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting26"), this.getState()));
                  break;
               case 26:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting27"), this.getState()));
                  break;
               case 27:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting28"), this.getState()));
                  break;
               case 28:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting29"), this.mCanBusInfoInt[3]));
                  break;
               case 29:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting30"), this.getState()));
                  break;
               case 30:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting31"), this.mCanBusInfoInt[3]));
                  break;
               case 31:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting32"), this.getState()));
                  break;
               case 32:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting33"), this.mCanBusInfoInt[3]));
                  break;
               case 33:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting34"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 34:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting35"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 35:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting36"), this.getState()));
                  break;
               case 36:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting37"), this.getState()));
                  break;
               case 37:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting370"), this.getState()));
                  break;
               case 38:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting371"), this.getState()));
                  break;
               case 39:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting372"), this.mCanBusInfoInt[3]));
                  break;
               case 40:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting373"), this.mCanBusInfoInt[3]));
                  break;
               case 41:
                  var3.add((new SettingUpdateEntity(var1, this.getRight("_375_car_setting374"), this.mCanBusInfoInt[3] - 10)).setProgress(this.mCanBusInfoInt[3]));
                  break;
               case 42:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting375"), this.getState()));
                  break;
               case 43:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting38"), this.getState()));
                  break;
               case 44:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting39"), this.getState()));
                  break;
               case 45:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting40"), this.getState()));
                  break;
               case 46:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting41"), this.getState()));
                  break;
               case 47:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting42"), this.getState()));
                  break;
               case 48:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting43"), this.getState()));
                  break;
               case 49:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting44"), this.getState()));
                  break;
               case 50:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting45"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 51:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting46"), this.getState()));
                  break;
               case 52:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting47"), this.getState()));
                  break;
               case 53:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting48"), this.getState()));
                  break;
               case 54:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting49"), this.getState()));
                  break;
               case 55:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting50"), this.getState()));
                  break;
               case 56:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting51"), this.getState()));
                  break;
               case 57:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting53"), this.getState()));
                  break;
               case 58:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting54"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 59:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting55"), this.getState()));
                  break;
               case 60:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting56"), this.getState()));
                  break;
               case 61:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting57"), this.getState()));
                  break;
               case 62:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting58"), this.getState()));
                  break;
               case 63:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting59"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 64:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting60"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 65:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting61"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 66:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting62"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 67:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting63"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 68:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting64"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 69:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting65"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 70:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting66"), this.mCanBusInfoInt[3] - 1));
                  break;
               case 71:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting67"), this.getState()));
                  break;
               case 72:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting68"), this.getState()));
                  break;
               case 73:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting69"), this.getState()));
                  break;
               case 74:
                  var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting70"), this.getState()));
            }
         } else {
            var3.add(new SettingUpdateEntity(var1, this.getRight("_375_car_setting2"), this.getState()));
         }

         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void set0x53EnergyInfo() {
      ArrayList var13 = new ArrayList();
      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting1"), this.getState2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1))));
      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting2"), this.getState2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 1))));
      int var1 = this.mCanBusInfoInt[11];
      if (var1 == 60) {
         var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting3"), 0));
      } else if (var1 == 100) {
         var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting3"), 1));
      }

      var13.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting4"), this.getState(this.mCanBusInfoInt[12])));
      int var2 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging");
      var1 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting_xuhang");
      StringBuilder var12 = new StringBuilder();
      int[] var11 = this.mCanBusInfoInt;
      var13.add((new SettingUpdateEntity(var2, var1, var12.append((double)(var11[13] * 256 + var11[14]) * 0.125).append("km").toString())).setValueStr(true));
      this.updateGeneralSettingData(var13);
      this.updateSettingActivity((Bundle)null);
      var11 = this.mCanBusInfoInt;
      var11[10] = 0;
      var11[11] = 0;
      var11[12] = 0;
      var11[13] = 0;
      var11[14] = 0;
      if (!this.isNotEnergyChange()) {
         var11 = this.mCanBusInfoInt;
         var1 = var11[2];
         var2 = var11[3];
         int var3 = var11[4];
         int var5 = var11[5] | var1 << 24 | var2 << 16 | var3 << 8;
         var2 = this.getIntFromByteWithBit(var5, 24, 7);
         var3 = this.getIntFromByteWithBit(var5, 20, 4);
         var1 = this.getIntFromByteWithBit(var5, 12, 5);
         int var4 = this.getIntFromByteWithBit(var5, 7, 5);
         var5 = this.getIntFromByteWithBit(var5, 1, 6);
         var11 = this.mCanBusInfoInt;
         int var6 = var11[6];
         int var7 = var11[7];
         int var10 = var6 << 24 | var7 << 16 | var7 << 8 | var11[9];
         var6 = this.getIntFromByteWithBit(var10, 25, 7);
         int var9 = this.getIntFromByteWithBit(var10, 21, 4);
         int var8 = this.getIntFromByteWithBit(var10, 13, 5);
         var7 = this.getIntFromByteWithBit(var10, 8, 5);
         var10 = this.getIntFromByteWithBit(var10, 2, 6);
         (new SetAlertView()).showDialog(this.getActivity(), this.mContext.getString(2131764797) + ":" + (var2 + 2000) + "/" + var3 + "/" + var1 + " " + var4 + ":" + var5 + "___" + this.mContext.getString(2131764796) + ":" + (var6 + 2000) + "/" + var9 + "/" + var8 + " " + var7 + ":" + var10);
      }
   }

   private void set0xFFVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setItem3Info() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764854);
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764853);
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764852);
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764851);
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764850);
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764849);
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764848);
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764847);
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764854);
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764853);
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764852);
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764851);
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764850);
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764849);
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764848);
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
         this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764847);
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764854);
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764853);
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764852);
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764851);
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764850);
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764849);
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764848);
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
         this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764847);
      }

      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764854);
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764853);
      }

      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764852);
      }

      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764851);
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764850);
      }

      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764849);
      }

      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764848);
      }

      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5])) {
         this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(2131764846) + this.mContext.getString(2131764847);
      }

   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0xC0Str(7, "Aux Playing!");
      this.getUiMgr(this.mContext).send0x75LCD(5, 0, 0, 0, 0, 0, 0, 0);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).send0xC0Str(11, "Bluetooth Music Playing!");
      this.getUiMgr(this.mContext).send0x75LCD(6, 0, 0, 0, 0, 0, 0, 0);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC0PhoneInfo(0, 0, 0, var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC0PhoneInfo(1, 0, 0, var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC0PhoneInfo(3, 0, 0, var1);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      this.getUiMgr(this.mContext).send0xC0PhoneInfo(2, 0, 0, var1);
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 41) {
         if (var3 != 255) {
            if (var3 != 82) {
               if (var3 != 83) {
                  switch (var3) {
                     case 32:
                        this.set0x20WheelKeyInfo();
                        break;
                     case 33:
                        this.set0x21AirInfo();
                        break;
                     case 34:
                        this.set0x22RearRadarInfo();
                        break;
                     case 35:
                        this.set0X23FrontRadarInfo();
                        break;
                     case 36:
                        this.set0x24LeftRadarInfo();
                        break;
                     case 37:
                        this.set0x25RightRadarInfo();
                        break;
                     case 38:
                        this.set0x26RearRadarInfo();
                        break;
                     case 39:
                        this.set0X27FrontRadarInfo();
                        break;
                     default:
                        switch (var3) {
                           case 48:
                              this.set0x30RightCamera();
                              break;
                           case 49:
                              this.set0x31PanoraSwitch();
                              break;
                           case 50:
                              this.set0x32PanoiraInfo();
                              break;
                           case 51:
                              this.set0x33ParkInfo();
                              break;
                           default:
                              switch (var3) {
                                 case 56:
                                    this.set0X38TireInfo();
                                    break;
                                 case 57:
                                    this.set0x39TireAlert();
                                    break;
                                 case 58:
                                    this.set0x3ADoorInfo();
                                    break;
                                 case 59:
                                    this.set0X3BBackLightInfi();
                              }
                        }
                  }
               } else {
                  this.set0x53EnergyInfo();
               }
            } else {
               this.set0x52TireReset();
            }
         } else {
            this.set0xFFVersionInfo();
         }
      } else {
         this.set0x29EspInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      this.getUiMgr(this.mContext).sendTimeInfo(var6, var5, var10);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).makeConnection();
      this.getUiMgr(this.mContext).getCanBoxVersion();
   }

   public void knobKey(int var1) {
      this.realKeyClick3(this.mContext, var1, 0, this.mCanBusInfoInt[3]);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         this.getUiMgr(this.mContext).send0xC0Str(9, var21);
      } else {
         this.getUiMgr(this.mContext).send0xC0Str(8, var21);
      }

      this.getUiMgr(this.mContext).send0x75LCD(4, 0, 0, 0, 0, 0, 0, 0);
   }

   public void myForceReverse(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void panoramicSwitch(boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      boolean var8 = var2.equals("FM");
      byte var9 = 0;
      if (!var8) {
         if (var2.equals("FM1")) {
            var9 = 1;
         } else if (var2.equals("FM2")) {
            var9 = 2;
         } else if (var2.equals("FM3")) {
            var9 = 3;
         } else if (var2.equals("AM")) {
            var9 = 16;
         } else if (var2.equals("AM1")) {
            var9 = 17;
         } else if (var2.equals("AM2")) {
            var9 = 18;
         } else if (var2.equals("AM3")) {
            var9 = 19;
         }
      }

      int var6;
      int var7;
      if (!var2.equals("FM") && !var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         var6 = this.getMsb((int)Double.parseDouble(var3));
         var7 = this.getLsb((int)Double.parseDouble(var3));
      } else {
         var6 = this.getMsb((int)(Double.parseDouble(var3) * 100.0));
         var7 = this.getLsb((int)(Double.parseDouble(var3) * 100.0));
      }

      this.getUiMgr(this.mContext).send0xC0RadioInfo(var9, var7, var6, var1);
      byte var10;
      if (!var2.equals("FM") && !var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         var7 = this.getMsb((int)Double.parseDouble(var3));
         var5 = this.getLsb((int)Double.parseDouble(var3));
         var10 = 3;
      } else {
         var7 = this.getMsb((int)(Double.parseDouble(var3) * 100.0));
         var5 = this.getLsb((int)(Double.parseDouble(var3) * 100.0));
         var10 = 1;
      }

      this.getUiMgr(this.mContext).send0x75LCD(1, var10, var7, var5, var1, 0, 0, 0);
   }

   public void setEndTime() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new SetTimeView()).showDialog(this.this$0.getActivity(), this.this$0.mContext.getString(2131764796), false, false, false, true, true, new SetTimeView.TimeResultListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void result(int var1, int var2, int var3, int var4, int var5) {
                  this.this$1.this$0.endHour = var4;
                  this.this$1.this$0.endMin = var5;
                  this.this$1.this$0.updateSettingsStyle0(this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).getSettingLeftIndexes(this.this$1.this$0.mContext, "_375_reservation_charging"), this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).getSettingRightIndex(this.this$1.this$0.mContext, "_375_reservation_charging", "_375_reservation_charging_end"), this.this$1.this$0.df_2Integer.format((long)var4) + ":" + this.this$1.this$0.df_2Integer.format((long)var5));
                  if (this.this$1.this$0.startHour != -1 && this.this$1.this$0.startMin != -1) {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).send0x84CarInfo(1, this.this$1.this$0.startHour, this.this$1.this$0.startMin, this.this$1.this$0.endHour, this.this$1.this$0.endMin);
                  } else {
                     Toast.makeText(this.this$1.this$0.getActivity(), this.this$1.this$0.mContext.getString(2131764794), 0).show();
                  }

               }
            });
         }
      });
   }

   public void setStartTime() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            (new SetTimeView()).showDialog(this.this$0.getActivity(), this.this$0.mContext.getString(2131764797), false, false, false, true, true, new SetTimeView.TimeResultListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void result(int var1, int var2, int var3, int var4, int var5) {
                  this.this$1.this$0.startHour = var4;
                  this.this$1.this$0.startMin = var5;
                  this.this$1.this$0.updateSettingsStyle0(this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).getSettingLeftIndexes(this.this$1.this$0.mContext, "_375_reservation_charging"), this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).getSettingRightIndex(this.this$1.this$0.mContext, "_375_reservation_charging", "_375_reservation_charging_start"), this.this$1.this$0.df_2Integer.format((long)var4) + ":" + this.this$1.this$0.df_2Integer.format((long)var5));
                  if (this.this$1.this$0.endHour != -1 && this.this$1.this$0.endMin != -1) {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).send0x84CarInfo(1, this.this$1.this$0.startHour, this.this$1.this$0.startMin, this.this$1.this$0.endHour, this.this$1.this$0.endMin);
                  } else {
                     Toast.makeText(this.this$1.this$0.getActivity(), this.this$1.this$0.mContext.getString(2131764795), 0).show();
                  }

               }
            });
         }
      });
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (var1) {
         this.getUiMgr(this.mContext).send0xC0Str(11, "Media OFF");
      }

   }

   public void toast(String var1) {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$content;

         {
            this.this$0 = var1;
            this.val$content = var2;
         }

         public void callback() {
            Toast.makeText(this.this$0.getActivity(), this.val$content, 0);
         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettingsStyle0(int var1, int var2, String var3) {
      ArrayList var4 = new ArrayList();
      var4.add((new SettingUpdateEntity(var1, var2, var3)).setValueStr(true));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         this.getUiMgr(this.mContext).send0xC0Str(9, "CD Video Playing!");
      } else {
         this.getUiMgr(this.mContext).send0xC0Str(8, "USB Video Playing!");
      }

      this.getUiMgr(this.mContext).send0x75LCD(3, 0, 0, 0, 0, 0, 0, 0);
   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1391366141:
            if (var1.equals("skylight.open")) {
               var2 = 0;
            }
            break;
         case -1326915554:
            if (var1.equals("ac.temperature.max")) {
               var2 = 1;
            }
            break;
         case -1226270570:
            if (var1.equals("ac.open")) {
               var2 = 2;
            }
            break;
         case -891288852:
            if (var1.equals("rear.right.window.open")) {
               var2 = 3;
            }
            break;
         case -866529054:
            if (var1.equals("air.in.out.cycle.off")) {
               var2 = 4;
            }
            break;
         case -655752154:
            if (var1.equals("ac.windlevel.max")) {
               var2 = 5;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 6;
            }
            break;
         case -193868961:
            if (var1.equals("skylight.close")) {
               var2 = 7;
            }
            break;
         case 345504582:
            if (var1.equals("front.left.window.open")) {
               var2 = 8;
            }
            break;
         case 629126444:
            if (var1.equals("ac.close")) {
               var2 = 9;
            }
            break;
         case 925454385:
            if (var1.equals("front.right.window.open")) {
               var2 = 10;
            }
            break;
         case 1496068108:
            if (var1.equals("air.in.out.cycle.on")) {
               var2 = 11;
            }
            break;
         case 1949467947:
            if (var1.equals("rear.left.window.open")) {
               var2 = 12;
            }
      }

      switch (var2) {
         case 0:
            MessageSender.sendVoiceMsg(2);
            break;
         case 1:
            MessageSender.sendVoiceMsg(9);
            break;
         case 2:
            MessageSender.sendVoiceMsg(4);
            break;
         case 3:
            MessageSender.sendVoiceMsg(13);
            break;
         case 4:
            MessageSender.sendVoiceMsg(6);
            break;
         case 5:
            MessageSender.sendVoiceMsg(8);
            break;
         case 6:
            MessageSender.sendVoiceMsg(7);
            break;
         case 7:
            MessageSender.sendVoiceMsg(1);
            break;
         case 8:
            MessageSender.sendVoiceMsg(10);
            break;
         case 9:
            MessageSender.sendVoiceMsg(3);
            break;
         case 10:
            MessageSender.sendVoiceMsg(11);
            break;
         case 11:
            MessageSender.sendVoiceMsg(5);
            break;
         case 12:
            MessageSender.sendVoiceMsg(12);
      }

   }
}
