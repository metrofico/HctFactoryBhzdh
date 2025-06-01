package com.hzbhd.canbus.car._298;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isAirFirst;
   private static int up_dn_btn_data;
   private static int voice_btn_data;
   private final int DATA_TYPE = 1;
   private final String TAG = "_298_MsgMgr";
   TextView content;
   AlertDialog dialog;
   Button i_know;
   private boolean mBeltStatus;
   private byte[] mCanBusBtnPanelInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray;
   int[] mCarDoorData;
   private Context mContext;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   View view;

   private int getFrontRadarInfo(int var1) {
      int var2 = var1;
      if (var1 == 2) {
         var2 = 10;
      }

      return var2;
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBlowModeMatch(int var1, int... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1 == var2[var3]) {
            return true;
         }
      }

      return false;
   }

   private boolean isBtnPanelMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusBtnPanelInfoCopy)) {
         return true;
      } else {
         this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBeltStatus == GeneralDoorData.isSeatBeltTie) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBeltStatus = GeneralDoorData.isSeatBeltTie;
         return true;
      }
   }

   private boolean isFirst() {
      if (isAirFirst) {
         isAirFirst = false;
         if (!GeneralAirData.power) {
            return true;
         }
      }

      return false;
   }

   private void panelButton0x21() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 9) {
            if (var1 != 16) {
               if (var1 != 36) {
                  if (var1 != 43) {
                     if (var1 != 51) {
                        if (var1 != 2) {
                           if (var1 != 3) {
                              if (var1 != 93) {
                                 if (var1 == 94) {
                                    this.realKeyLongClick1(this.mContext, 75, var2[3]);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 4, var2[3]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 20, var2[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 21, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 62, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 52, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 2, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 49, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 3, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[5]);
   }

   private String resolveAirTemperature(Context var1, int var2) {
      if (var2 == 254) {
         return "LOW";
      } else {
         return var2 == 255 ? "HIGH" : (float)var2 / 2.0F + this.getTempUnitC(var1);
      }
   }

   private void set0x18LightInfo() {
      String var1;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getString(2131759223);
      } else {
         var1 = "NULL";
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getString(2131759224);
      }

      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
         var1 = this.mContext.getString(2131759225);
      }

      if (!var1.equals("NULL")) {
         if (this.view == null) {
            this.view = LayoutInflater.from(this.mContext).inflate(2131558513, (ViewGroup)null, true);
         }

         if (this.dialog == null) {
            this.dialog = (new AlertDialog.Builder(this.mContext)).setView(this.view).create();
         }

         if (this.content == null) {
            this.content = (TextView)this.view.findViewById(2131361915);
         }

         this.content.setText(var1);
         if (this.i_know == null) {
            this.i_know = (Button)this.view.findViewById(2131362380);
         }

         this.i_know.setOnClickListener(new View.OnClickListener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.dialog.dismiss();
            }
         });
         this.dialog.setCancelable(false);
         this.dialog.getWindow().setBackgroundDrawableResource(17170445);
         this.dialog.getWindow().setType(2003);
         this.dialog.show();
         (new CountDownTimer(this, 2000L, 1000L) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onFinish() {
               this.this$0.dialog.dismiss();
            }

            public void onTick(long var1) {
            }
         }).start();
      }
   }

   private void setAirData0x31(Context var1) {
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_left_blow_foot = this.isBlowModeMatch(this.mCanBusInfoInt[6], 3, 5, 12);
            GeneralAirData.front_left_blow_head = this.isBlowModeMatch(this.mCanBusInfoInt[6], 5, 6);
            GeneralAirData.front_left_blow_window = this.isBlowModeMatch(this.mCanBusInfoInt[6], 12);
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
            this.updateAirActivity(var1, 1001);
         }
      }
   }

   private void setDoorData0x12(Context var1) {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         this.updateDoorView(var1);
      }

   }

   private void setOriginalPanel0x22() {
      int[] var1 = this.mCanBusInfoInt;
      if (var1[2] == 1) {
         if (var1[3] > voice_btn_data) {
            this.realKeyClick4(7);
            voice_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < voice_btn_data) {
            this.realKeyClick4(8);
            voice_btn_data = this.mCanBusInfoInt[3];
         }
      } else {
         if (var1[3] > up_dn_btn_data) {
            this.realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      }

   }

   private void setRadarData0x41(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var4;
         if (this.mCanBusInfoInt[13] == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            var4 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(8, var4[2], var4[3], var4[4], var4[5]);
            RadarInfoUtil.setFrontRadarLocationData(10, this.getFrontRadarInfo(this.mCanBusInfoInt[6]), this.getFrontRadarInfo(this.mCanBusInfoInt[6]), this.getFrontRadarInfo(this.mCanBusInfoInt[9]), this.getFrontRadarInfo(this.mCanBusInfoInt[9]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.updateParkUi((Bundle)null, this.mContext);
         } else {
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            var4 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(var4[2], var4[3], var4[4], var4[5]);
            var4 = this.mCanBusInfoInt;
            int var3 = var4[6];
            int var2 = var4[9];
            RadarInfoUtil.setFrontRadarDistanceData(var3, var3, var2, var2);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            this.updateParkUi((Bundle)null, this.mContext);
         }

         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void setVehicleTypeData0x26() {
      int[] var1 = this.mCanBusInfoInt;
      String var2;
      if (var1[2] == 38 && var1[3] == 2) {
         var2 = "风光580";
      } else {
         var2 = "NULL";
      }

      Log.i("_298_MsgMgr", "setVehicleTypeData0x26: Car series：" + "NULL" + ", Model:" + var2);
   }

   private void setVersionData0xF0(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKeyData0x11(Context var1) {
      int var2 = this.mCanBusInfoInt[4];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 5) {
                     if (var2 != 23) {
                        switch (var2) {
                           case 12:
                              this.realKeyLongClick1(var1, 2);
                              break;
                           case 13:
                              this.realKeyLongClick1(var1, 45);
                              break;
                           case 14:
                              this.realKeyLongClick1(var1, 46);
                        }
                     } else {
                        this.realKeyLongClick1(var1, 128);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 14);
                  }
               } else {
                  this.realKeyLongClick1(var1, 3);
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

   private void track0x11() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
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
            if (var3 != 24) {
               if (var3 != 38) {
                  if (var3 != 49) {
                     if (var3 != 65) {
                        if (var3 != 240) {
                           if (var3 != 33) {
                              if (var3 == 34) {
                                 if (this.isBtnPanelMsgReturn(var2)) {
                                    return;
                                 }

                                 this.setOriginalPanel0x22();
                              }
                           } else {
                              this.panelButton0x21();
                           }
                        } else {
                           this.setVersionData0xF0(var1);
                        }
                     } else {
                        this.setRadarData0x41(var1);
                     }
                  } else {
                     this.setAirData0x31(var1);
                  }
               } else {
                  this.setVehicleTypeData0x26();
               }
            } else {
               this.set0x18LightInfo();
            }
         } else {
            this.setDoorData0x12(var1);
         }
      } else {
         this.setWheelKeyData0x11(var1);
         this.track0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      if (this.getCurrentCanDifferentId() == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 2, 38});
      } else if (this.getCurrentCanDifferentId() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 9, 38});
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
      this.mCanbusDataArray = new SparseArray();
      GeneralDoorData.isShowSeatBelt = true;
      RadarInfoUtil.mMinIsClose = true;
   }
}
