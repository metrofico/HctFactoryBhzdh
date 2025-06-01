package com.hzbhd.canbus.car._195;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   static final String CAN_195_SAVE_RADAR_DISP = "__195_SAVE_RADAR_DISP";
   static int cam3603dMode;
   static int cam3603dModeLast;
   static int cam360Guideline;
   static boolean isCam360LowSpeedOpen;
   private static int lastPm25Lev;
   private static int lastSwcKey;
   private static int lastSwcSt;
   private static int mDifferentId;
   private static boolean mIsBackLast;
   private static boolean mIsBelt;
   private static boolean mIsFLDoorLast;
   private static boolean mIsFRDoorLast;
   private static boolean mIsFrontLast;
   private static boolean mIsHandBreak;
   private static boolean mIsRLDoorLast;
   private static boolean mIsRRDoorLast;
   static boolean mLast360st;
   private static int nowPm25Lev;
   private AirPageUiSet mAirPageUiSet;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private ParkPageUiSet mParkPageUiSet;

   private AirPageUiSet getAirPageUiSet() {
      if (this.mAirPageUiSet == null) {
         this.mAirPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getAirUiSet(this.mContext);
      }

      return this.mAirPageUiSet;
   }

   private ParkPageUiSet getmParkPageUiSet() {
      if (this.mParkPageUiSet == null) {
         this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getParkPageUiSet(this.mContext);
      }

      return this.mParkPageUiSet;
   }

   private boolean isNoDoor() {
      return this.getCurrentCanDifferentId() == 2 || this.getCurrentCanDifferentId() == 10;
   }

   private void keyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void otherKnobTurn(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var3, var1, var2[2], var2[3]);
   }

   private void recEi5_2019_0x41() {
      if (this.getCurrentCanDifferentId() == 11 || this.getCurrentCanDifferentId() == 12) {
         ArrayList var4 = new ArrayList();
         var4.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var4.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var4.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var4.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
         var4.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
         String var3;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 0) {
            var3 = this.mContext.getResources().getString(2131758939);
         } else {
            var3 = this.mContext.getResources().getString(2131759015);
         }

         var4.add((new SettingUpdateEntity(3, 0, var3)).setValueStr(true));
         var4.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
         int var2 = this.mCanBusInfoInt[7];
         int var1 = var2;
         if (var2 >= 1) {
            var1 = var2 - 1;
         }

         var4.add(new SettingUpdateEntity(5, 0, var1));
         this.updateGeneralSettingData(var4);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void recI5_0x41() {
      if (this.getCurrentCanDifferentId() == 10) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
         var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var1.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         var1.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
         var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var1.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
         var1.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
         var1.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
         var1.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
         var1.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2)));
         var1.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)));
         var1.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2)));
         var1.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1)));
         var1.add(new SettingUpdateEntity(3, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
         var1.add(new SettingUpdateEntity(3, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1)));
         var1.add(new SettingUpdateEntity(3, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2)));
         var1.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1)));
         var1.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 2)));
         var1.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2)));
         var1.add(new SettingUpdateEntity(5, 0, this.mCanBusInfoInt[11]));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void recI6_2016_2017_0x42() {
      if (this.getCurrentCanDifferentId() == 6 || this.getCurrentCanDifferentId() == 7 || this.getCurrentCanDifferentId() == 3 || this.getCurrentCanDifferentId() == 4 || this.getCurrentCanDifferentId() == 5) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
         var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
         String var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 0) {
            var1 = this.mContext.getResources().getString(2131758939);
         } else {
            var1 = this.mContext.getResources().getString(2131759015);
         }

         var2.add((new SettingUpdateEntity(1, 0, var1)).setValueStr(true));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
            var1 = this.mContext.getResources().getString(2131758939);
         } else {
            var1 = this.mContext.getResources().getString(2131759015);
         }

         var2.add((new SettingUpdateEntity(2, 0, var1)).setValueStr(true));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void recI6_2019_0x41() {
      if (this.getCurrentCanDifferentId() == 8 || this.getCurrentCanDifferentId() == 9) {
         ArrayList var3 = new ArrayList();
         var3.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var3.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var3.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var3.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         int var1;
         Resources var2;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
            var2 = this.mContext.getResources();
            var1 = 2131758939;
         } else {
            var2 = this.mContext.getResources();
            var1 = 2131759015;
         }

         var3.add((new SettingUpdateEntity(1, 0, var2.getString(var1))).setValueStr(true));
         var3.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
         var3.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2)));
         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void recRx3_2018_other_0x41() {
      if (this.getCurrentCanDifferentId() == 0 || this.getCurrentCanDifferentId() == 1 || this.getCurrentCanDifferentId() == 2) {
         ArrayList var2 = new ArrayList();
         var2.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
         var2.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
         var2.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
         var2.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
         var2.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
         var2.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var2.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
         var2.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
         String var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
            var1 = this.mContext.getResources().getString(2131758939);
         } else {
            var1 = this.mContext.getResources().getString(2131759015);
         }

         var2.add((new SettingUpdateEntity(2, 0, var1)).setValueStr(true));
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 0) {
            var1 = this.mContext.getResources().getString(2131758939);
         } else {
            var1 = this.mContext.getResources().getString(2131759015);
         }

         var2.add((new SettingUpdateEntity(3, 0, var1)).setValueStr(true));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (15 == var1) {
         var2 = "HI";
      } else if (16 == var1) {
         var2 = 16 + this.getTempUnitC(this.mContext);
      } else if (1 <= var1 && 14 >= var1) {
         var2 = var1 + 16 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "--";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      boolean var2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      String var5 = "";
      String var3;
      if (var2) {
         var3 = "-" + var1;
      } else {
         var3 = var1 + "";
      }

      String var4 = var5;
      if (this.getCurrentCanDifferentId() != 4) {
         var4 = var5;
         if (this.getCurrentCanDifferentId() != 3) {
            var4 = var5;
            if (this.getCurrentCanDifferentId() != 5) {
               var4 = var5;
               if (this.getCurrentCanDifferentId() != 7) {
                  if (this.getCurrentCanDifferentId() == 9) {
                     var4 = var5;
                  } else {
                     var4 = var3;
                  }
               }
            }
         }
      }

      return var4 + this.getTempUnitC(this.mContext);
   }

   private void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      boolean var2 = false;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 15) {
                        GeneralAirData.front_auto_wind_model = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      if (GeneralAirData.front_wind_level == 15) {
         var2 = true;
      }

      GeneralAirData.front_auto_wind_speed = var2;
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      GeneralAirData.econ = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 2);

      try {
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
      } catch (Exception var4) {
         Log.e("[CAR_194]", "<AIR> erro=" + var4);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarIdCmd() {
      switch (this.getCurrentCanDifferentId()) {
         case 6:
         case 7:
         case 8:
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 4});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 2});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 3});
            break;
         default:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 0});
      }

   }

   private void setDoorData0x24() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatBeltTie = true ^ DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen || mIsHandBreak != GeneralDoorData.isHandBrakeUp || mIsBelt != GeneralDoorData.isSeatBeltTie) {
         if (this.isNoDoor()) {
            GeneralDoorData.isRightFrontDoorOpen = false;
            GeneralDoorData.isLeftFrontDoorOpen = false;
            GeneralDoorData.isRightRearDoorOpen = false;
            GeneralDoorData.isLeftRearDoorOpen = false;
            GeneralDoorData.isBackOpen = false;
            GeneralDoorData.isFrontOpen = false;
         }

         this.updateDoorView(this.mContext);
      }

      mIsFLDoorLast = GeneralDoorData.isLeftFrontDoorOpen;
      mIsFRDoorLast = GeneralDoorData.isRightFrontDoorOpen;
      mIsRLDoorLast = GeneralDoorData.isLeftRearDoorOpen;
      mIsRRDoorLast = GeneralDoorData.isRightRearDoorOpen;
      mIsFrontLast = GeneralDoorData.isFrontOpen;
      mIsBackLast = GeneralDoorData.isBackOpen;
      mIsHandBreak = GeneralDoorData.isHandBrakeUp;
      mIsBelt = GeneralDoorData.isSeatBeltTie;
   }

   private void setPanoramic_0x50() {
      if (this.isHaveCam360()) {
         boolean var3 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         boolean var4 = false;
         boolean var1;
         if (var3) {
            var1 = true;
         } else {
            var1 = false;
         }

         Context var5 = this.mContext;
         if (var1) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.forceReverse(var5, var3);
         ArrayList var6 = new ArrayList();
         ParkPageUiSet var8 = this.getmParkPageUiSet();
         cam360Guideline = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
         cam3603dMode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
         isCam360LowSpeedOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         int var7 = cam360Guideline;
         if (var7 != 0) {
            if (var7 != 1) {
               if (var7 != 2) {
                  if (var7 == 3) {
                     ((ParkPageUiSet.Bean)var8.getPanoramicBtnList().get(1)).setTitleSrn("_194_car_line_static_dynamic");
                  }
               } else {
                  ((ParkPageUiSet.Bean)var8.getPanoramicBtnList().get(1)).setTitleSrn("_194_car_line_dynamic");
               }
            } else {
               ((ParkPageUiSet.Bean)var8.getPanoramicBtnList().get(1)).setTitleSrn("_194_car_line_static");
            }
         } else {
            ((ParkPageUiSet.Bean)var8.getPanoramicBtnList().get(1)).setTitleSrn("_194_car_line_off");
         }

         var7 = cam3603dMode;
         if (var7 != 0) {
            if (var7 == 1) {
               ((ParkPageUiSet.Bean)var8.getPanoramicBtnList().get(2)).setTitleSrn("_194_3d");
               if (cam3603dModeLast != cam3603dMode) {
                  var8.getPanoramicBtnList().remove(7);
                  var8.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_front_left", ""));
                  var8.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_front_right", ""));
                  var8.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_rear_left", ""));
                  var8.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_rear_right", ""));
               }
            }
         } else {
            ((ParkPageUiSet.Bean)var8.getPanoramicBtnList().get(2)).setTitleSrn("_194_2d");
            if (cam3603dModeLast != cam3603dMode) {
               var8.getPanoramicBtnList().remove(7);
               var8.getPanoramicBtnList().remove(7);
               var8.getPanoramicBtnList().remove(7);
               var8.getPanoramicBtnList().remove(7);
            }
         }

         int var2 = cam3603dModeLast;
         var7 = cam3603dMode;
         if (var2 != var7 && var7 == 1) {
            var8.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
         }

         var8.setPanoramicBtnList(var8.getPanoramicBtnList());
         if (var8.getPanoramicBtnList() != null) {
            var6.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
            if (this.mCanBusInfoInt[3] == 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.add(new PanoramicBtnUpdateEntity(3, var3));
            if (this.mCanBusInfoInt[3] == 4) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.add(new PanoramicBtnUpdateEntity(4, var3));
            if (this.mCanBusInfoInt[3] == 2) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.add(new PanoramicBtnUpdateEntity(5, var3));
            if (this.mCanBusInfoInt[3] == 6) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.add(new PanoramicBtnUpdateEntity(6, var3));
            if (cam3603dMode == 1) {
               if (this.mCanBusInfoInt[3] == 1) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var6.add(new PanoramicBtnUpdateEntity(7, var3));
               if (this.mCanBusInfoInt[3] == 7) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var6.add(new PanoramicBtnUpdateEntity(8, var3));
               if (this.mCanBusInfoInt[3] == 3) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var6.add(new PanoramicBtnUpdateEntity(9, var3));
               var3 = var4;
               if (this.mCanBusInfoInt[3] == 5) {
                  var3 = true;
               }

               var6.add(new PanoramicBtnUpdateEntity(10, var3));
            }
         }

         GeneralParkData.dataList = var6;
         this.updateParkUi((Bundle)null, this.mContext);
         mLast360st = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         cam3603dModeLast = cam3603dMode;
      }
   }

   private void setPm25() {
      if (this.getCurrentCanDifferentId() != 2 && this.getCurrentCanDifferentId() != 9 && this.getCurrentCanDifferentId() != 8) {
         this.getAirPageUiSet().getFrontArea().setShowCenterWheel(false);
         GeneralAirData.center_wheel = "";
      } else {
         this.getAirPageUiSet().getFrontArea().setShowCenterWheel(true);
         int var1 = this.mCanBusInfoInt[2];
         if (var1 >= 0 && var1 <= 37) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(2131769637);
            nowPm25Lev = 0;
         } else if (var1 >= 38 && var1 <= 75) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(2131769638);
            nowPm25Lev = 1;
         } else if (var1 >= 76 && var1 <= 125) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(2131769641);
            nowPm25Lev = 2;
         } else if (var1 >= 126 && var1 <= 250) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(2131769642);
            nowPm25Lev = 3;
         } else if (var1 >= 251 && var1 <= 255) {
            GeneralAirData.center_wheel = this.mContext.getResources().getString(2131769639);
            nowPm25Lev = 4;
         }

         if (nowPm25Lev != lastPm25Lev) {
            this.updateAirActivity(this.mContext, 1001);
         }

         lastPm25Lev = nowPm25Lev;
      }
   }

   private void setRearRadar0x22() {
      GeneralParkData.isShowLeftTopOneDistanceUi = true;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[6] + this.mContext.getResources().getString(2131770204);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSwcKey() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 50) {
                        switch (var1) {
                           case 6:
                              this.keyClick(14);
                              break;
                           case 7:
                              this.keyClick(2);
                              break;
                           case 8:
                              this.keyClick(187);
                              break;
                           case 9:
                              this.keyClick(14);
                              break;
                           default:
                              switch (var1) {
                                 case 16:
                                    this.keyClick(187);
                                    break;
                                 case 17:
                                    this.keyClick(52);
                                    break;
                                 case 18:
                                    this.keyClick(50);
                                    break;
                                 case 19:
                                    this.keyClick(52);
                                    break;
                                 case 20:
                                    this.updateAirActivity(this.mContext, 1001);
                                    break;
                                 case 21:
                                    this.keyClick(185);
                                    break;
                                 case 22:
                                    this.keyClick(3);
                                    break;
                                 default:
                                    switch (var1) {
                                       case 31:
                                          this.keyClick(58);
                                          break;
                                       case 32:
                                          this.otherKnobTurn(47);
                                          break;
                                       case 33:
                                          this.otherKnobTurn(48);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 128:
                                                this.keyClick(134);
                                                break;
                                             case 129:
                                                this.volKnobTurn(7);
                                                break;
                                             case 130:
                                                this.volKnobTurn(8);
                                          }
                                    }
                              }
                        }
                     } else {
                        this.keyClick(128);
                     }
                  } else {
                     this.keyClick(48);
                  }
               } else {
                  this.keyClick(47);
               }
            } else {
               this.keyClick(8);
            }
         } else {
            this.keyClick(7);
         }
      } else {
         this.keyClick(0);
      }

   }

   private void setTrack() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2] * 256 + var2[3];
      if (var1 > 24870 && var1 <= 32768) {
         GeneralParkData.trackAngle = ('耀' - var1) * 40 / 7898;
      } else {
         if (var1 <= 32768 || var1 >= 40531) {
            if (var1 == 0) {
               GeneralParkData.trackAngle = 0;
               this.updateParkUi((Bundle)null, this.mContext);
            }

            return;
         }

         GeneralParkData.trackAngle = -(var1 - '耀') * 40 / 7763;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void volKnobTurn(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var3, var1, var2[2], var2[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 36) {
         if (var3 != 39) {
            if (var3 != 41) {
               if (var3 != 80) {
                  if (var3 != 127) {
                     switch (var3) {
                        case 32:
                           this.setSwcKey();
                           break;
                        case 33:
                           if (this.isAirMsgRepeat(var2)) {
                              return;
                           }

                           this.setAirData0x23();
                           break;
                        case 34:
                           this.setRearRadar0x22();
                           break;
                        default:
                           switch (var3) {
                              case 65:
                                 this.recI5_0x41();
                                 this.recEi5_2019_0x41();
                                 this.recI6_2019_0x41();
                                 this.recRx3_2018_other_0x41();
                                 break;
                              case 66:
                                 this.recI6_2016_2017_0x42();
                                 break;
                              case 67:
                                 this.setPm25();
                           }
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.setPanoramic_0x50();
               }
            } else {
               this.setTrack();
            }
         } else {
            this.setAirData0x27();
         }
      } else {
         this.setDoorData0x24();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      short var19;
      if (var10) {
         var19 = 0;
      } else {
         var19 = 128;
      }

      if (var10) {
         var5 = var8;
      }

      byte var16 = (byte)(var5 | var19);
      byte var17 = (byte)var2;
      byte var14 = (byte)var3;
      byte var15 = (byte)var4;
      byte var18 = (byte)var6;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -90}, new byte[]{var17, var14, var15, var16, var18}));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      mDifferentId = this.getCurrentCanDifferentId();
      this.setCarIdCmd();
   }

   void initRadarDisp(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "__195_SAVE_RADAR_DISP", 0);
      if (this.getCurrentCanDifferentId() == 10) {
         ArrayList var3 = new ArrayList();
         var3.add(new SettingUpdateEntity(6, 0, var2));
         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
      }
   }

   boolean isHaveCam360() {
      return mDifferentId == 10;
   }
}
